package com.example.robin.angrynerds_wip.activities.web_image_import;

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

import java.util.HashMap;
import java.util.Map;


public class WebImportLogic {
    private WebImportData mWebImportData;
    private WebImportGui mWebImportGui;


    public WebImportLogic(WebImportData pWebImportData, WebImportGui pWebImportGui) {
        this.mWebImportData = pWebImportData;
        this.mWebImportGui = pWebImportGui;
        initClickListener();
        dataToGui();
    }

    public void initClickListener(){
        Log.d("clicked", "test");
        Log.d("clicked", "test "+mWebImportGui.getSearchTermBar().getId());
        mWebImportGui.getSearchTriggerButton().setOnClickListener(new WebImportClickListener(this));
    }

    private void dataToGui(){
        this.mWebImportGui.setSearchTerm(this.mWebImportData.getSearchterm());
    }

    public void searchImages() {
        String searchTerm = mWebImportGui.getSearchTermBar().getText().toString();
        mWebImportGui.resetURLs();

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this.mWebImportData.getActivity().getApplicationContext());
        String url ="https://api.cognitive.microsoft.com/bing/v7.0/images/search?q="+searchTerm+"&count=32";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject responseObject = new JSONObject(response);
                            JSONArray values = responseObject.getJSONArray("value");
                            for(int i = 0; i<values.length(); i++){
                                mWebImportGui.addUrl(values.getJSONObject(i).getString("thumbnailUrl"));
                            }
                            mWebImportGui.initRecyclerView(mWebImportData.getActivity());
                        }catch (JSONException e){
                            Log.e("JSON", "Could not parse ImageSearchResult");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Ocp-Apim-Subscription-Key", "a62132e6d1174a4591d80c343259681a");

                return params;
            }
        };

// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
}
