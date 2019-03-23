package com.example.robin.angrynerds_wip.activities.web_image_import;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class WebImportLogic {
    private WebImportData mWebImportData;
    private WebImportGui mWebImportGui;
    private ArrayList<String> originalUrls;


    public WebImportLogic(WebImportData pWebImportData, WebImportGui pWebImportGui) {
        this.originalUrls = new ArrayList<>();
        this.mWebImportData = pWebImportData;
        this.mWebImportGui = pWebImportGui;
        initClickListener();
        dataToGui();
    }

    public void initClickListener() {
        mWebImportGui.getSearchTriggerButton().setOnClickListener(new WebImportClickListener(this));
        mWebImportGui.getToolbar().setNavigationOnClickListener(new WebImportClickListener(this));
    }

    private void dataToGui() {
        this.mWebImportGui.setSearchTerm(this.mWebImportData.getSearchterm());
        this.mWebImportGui.setTopBarColor(this.mWebImportData.getColor());
    }

    public void closeActivity() {
        //TODO define return code & value
        this.mWebImportData.getActivity().finish();
    }

    public void searchImages() {
        String searchTerm = mWebImportGui.getSearchTermBar().getText().toString();
        mWebImportGui.resetURLs();
        this.originalUrls = new ArrayList<String>();

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this.mWebImportData.getActivity().getApplicationContext());
        String url = "https://api.cognitive.microsoft.com/bing/v7.0/images/search?q=" + searchTerm + "&count=64";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject responseObject = new JSONObject(response);
                            JSONArray values = responseObject.getJSONArray("value");
                            for (int i = 0; i < values.length(); i++) {
                                mWebImportGui.addUrl(values.getJSONObject(i).getString("thumbnailUrl"));
                                originalUrls.add(values.getJSONObject(i).getString("contentUrl"));
                            }
                            mWebImportGui.initRecyclerView(mWebImportData.getActivity());
                        } catch (JSONException e) {
                            Log.e("JSON", "Could not parse ImageSearchResult");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Ocp-Apim-Subscription-Key", "a62132e6d1174a4591d80c343259681a");

                return params;
            }
        };

// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    public void loadImage(int id) {
        String url = this.originalUrls.get(id);
        Intent intent = new Intent();
        intent.putExtra("url", url);
        mWebImportData.getActivity().setResult(Activity.RESULT_OK, intent);
        mWebImportData.getActivity().finish();
        Log.d("imageClick", "url: " + url);
    }
}
