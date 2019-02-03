package com.example.robin.angrynerds_wip.modules.share;

import android.app.Activity;
import android.content.Intent;

import com.example.robin.angrynerds_wip.data.models.tens.Note;

public class ShareModule {

    public static void shareNote(Activity pActivity, Note pNote) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        String shareText = pNote.getTitle();
        shareText = shareText + "\n\n" + pNote.getDescription();

        if(!pNote.getTags().isEmpty()){
            String tags = "";
            for(String tag : pNote.getTags())
                tags = tags + "#" + tag + " ";
            shareText = shareText + "\n\n" + tags;
        }

        shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
        pActivity.startActivity(Intent.createChooser(shareIntent, "Teilen mit"));
    }
}
