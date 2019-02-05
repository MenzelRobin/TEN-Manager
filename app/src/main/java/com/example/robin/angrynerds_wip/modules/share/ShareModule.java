package com.example.robin.angrynerds_wip.modules.share;

import android.app.Activity;
import android.content.Intent;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.activities.event.EventActivity;
import com.example.robin.angrynerds_wip.data.models.tens.Event;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.tens.Todo;

public class ShareModule {

    public static void shareEvent(EventActivity pActivity, Event pEvent, String pDate, String pTime) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        String eventSbj = pEvent.getTitle(); //Subject eg for Mails
        String eventTxt = pActivity.getString(R.string.event_share_text).replace("TITLE", pEvent.getTitle());
        eventTxt = eventTxt.replace("DATE", pDate);
        eventTxt = eventTxt.replace("TIME", pTime);
        eventTxt = eventTxt.replace("LOCATION", pEvent.getAddress());
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, eventSbj);
        shareIntent.putExtra(Intent.EXTRA_TEXT, eventTxt);
        pActivity.startActivity(Intent.createChooser(shareIntent, "Teilen mit"));
    }

    public static void shareTodo(Activity pActivity, Todo pTodo, String pStartDate, String pEndDate)
    {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        String shareText = pTodo.getTitle() + "\n\n" + pTodo.getNote() + "\n\n" + pStartDate + "\nbis " + pEndDate + "\n\n";

        String tasks = (int)(pTodo.getProgress() * 100) + "% erledigt:";
        for (int i = 0; i < pTodo.getTasks().size() - 1; i++)
        {
            // falls True (X), falls False ( ) in den String schreiben
            if (pTodo.getTasks().get(i).getStatus())
            {
                tasks += "\n\n(X) ";
            }
            else
            {
                tasks += "\n\n(  ) ";
            }

            tasks += pTodo.getTasks().get(i).getDescription() + " ";

        }

        shareText += tasks + "\n\n Geteilt vom geilsten TEN-Manager by Angry Nerds.";

        shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
        pActivity.startActivity(Intent.createChooser(shareIntent, "Teilen mit"));
    }

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
