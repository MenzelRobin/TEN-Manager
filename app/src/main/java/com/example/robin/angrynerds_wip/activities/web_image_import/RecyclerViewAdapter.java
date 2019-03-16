package com.example.robin.angrynerds_wip.activities.web_image_import;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.activities.note.note.data.gui_oriented.PreviewImageCreator;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mImages1 = new ArrayList<>();
    private Context mContext;
    private WebImportActivity webImportActivity;

    public RecyclerViewAdapter(ArrayList<String> mImages1, Context mContext, WebImportActivity webImportActivity) {
        this.mImages1 = mImages1;
        this.mContext = mContext;
        this.webImportActivity = webImportActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_row, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        ImageView target = holder.mImageView1;

        int baseIndex = position * 4;
        for (int i = 0; i < 4; i++) {
            if (baseIndex + i < mImages1.size()) {
                switch (i) {
                    case 0:
                        target = holder.mImageView1;
                        target.setId(baseIndex+i);
                        break;
                    case 1:
                        target = holder.mImageView2;
                        target.setId(baseIndex+i);
                        break;
                    case 2:
                        target = holder.mImageView3;
                        target.setId(baseIndex+i);
                        break;
                    case 3:
                        target = holder.mImageView4;
                        target.setId(baseIndex+i);
                        break;
                }
                loadFromGlide(mImages1, baseIndex+i, target);

                target.setOnClickListener(new WebImportImageClickListener(this.webImportActivity.getmWebImportLogic()));
            }
        }



    }

    @Override
    public int getItemCount() {
        return mImages1.size()/4;
    }

    private void loadFromGlide(ArrayList<String> url, int index, final ImageView target) {
        final PreviewImageCreator previewImageCreator = new PreviewImageCreator();
        Glide.with(mContext)
                .asBitmap()
                .load(url.get(index))
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        Bitmap bitmap1 = previewImageCreator.getPreviewImage(resource);
                        target.setImageBitmap(bitmap1);
                    }
                });
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView1;
        ImageView mImageView2;
        ImageView mImageView3;
        ImageView mImageView4;
        LinearLayoutCompat mLinearLayoutCompat;

        public ViewHolder(View itemView) {
            super(itemView);

            mImageView1 = itemView.findViewById(R.id.id_web_import_image_1);
            mImageView2 = itemView.findViewById(R.id.id_web_import_image_2);
            mImageView3 = itemView.findViewById(R.id.id_web_import_image_3);
            mImageView4 = itemView.findViewById(R.id.id_web_import_image_4);

            mLinearLayoutCompat = itemView.findViewById(R.id.id_web_import_list_item_linear_layout);
        }
    }
}
