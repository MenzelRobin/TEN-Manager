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
    private ArrayList<String> mImages2 = new ArrayList<>();
    private ArrayList<String> mImages3 = new ArrayList<>();
    private ArrayList<String> mImages4 = new ArrayList<>();

    private Context mContext;

    public RecyclerViewAdapter(ArrayList<String> mImages1, ArrayList<String> mImages2, ArrayList<String> mImages3, ArrayList<String> mImages4, Context mContext) {
        this.mImages1 = mImages1;
        this.mImages2 = mImages2;
        this.mImages3 = mImages3;
        this.mImages4 = mImages4;
        this.mContext = mContext;
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
        final PreviewImageCreator previewImageCreator = new PreviewImageCreator();

        loadFromGlide(mImages1, position, holder.mImageView1);
        loadFromGlide(mImages2, position, holder.mImageView2);
        loadFromGlide(mImages3, position, holder.mImageView3);
        loadFromGlide(mImages4, position, holder.mImageView4);

        holder.mLinearLayoutCompat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.d(TAG, "onClick: clicked on: " + position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImages1.size();
    }

    private void loadFromGlide(ArrayList<String> url, int position, final ImageView target){
        final PreviewImageCreator previewImageCreator = new PreviewImageCreator();
        Glide.with(mContext)
                .asBitmap()
                .load(url.get(position))
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
