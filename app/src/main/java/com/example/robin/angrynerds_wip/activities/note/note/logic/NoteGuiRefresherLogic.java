package com.example.robin.angrynerds_wip.activities.note.note.logic;

import android.support.v4.content.ContextCompat;
import android.widget.LinearLayout;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.activities.note.note.data.NoteData;
import com.example.robin.angrynerds_wip.activities.note.note.gui.IconContainer;
import com.example.robin.angrynerds_wip.activities.note.note.gui.ImageContainer;
import com.example.robin.angrynerds_wip.activities.note.note.gui.NoteGui;
import com.example.robin.angrynerds_wip.activities.note.note.logic.listener_watcher.ClickListener;
import com.example.robin.angrynerds_wip.data.models.utils.Image;

import java.util.ArrayList;

public class NoteGuiRefresherLogic {

    private NoteApplicationLogic mNoteApplicationLogic;

    private NoteData mNoteData;


    public NoteGuiRefresherLogic(NoteApplicationLogic pNoteApplicationLogic){
        this.mNoteApplicationLogic = pNoteApplicationLogic;
        this.mNoteData = pNoteApplicationLogic.getNoteData();
    }

    public void dataToGui() {
        //Set colors
        mNoteApplicationLogic.getNoteGui().setColors(mNoteData.getNote().getColor(), mNoteData.getNote().getAccentColor());
        //Set content
        mNoteApplicationLogic.getNoteGui().setNoteTitle(mNoteData.getNote().getTitle());
        mNoteApplicationLogic.getNoteGui().setNoteDescription(mNoteData.getNote().getDescription());
        mNoteApplicationLogic.getNoteGui().setNoteTags(mNoteData.getNote().getTags());

        refreshImages();
    }

    public void refreshImages() {
        mNoteApplicationLogic.getNoteGui().getNoteImageContainer().removeAllViews();

        ArrayList<Image> previewImages = mNoteData.getNotePreviewImages();
        ClickListener clickListener = mNoteApplicationLogic.getNoteListenerInitializer().getClickListener();
        LinearLayout noteImageContainerLayout = mNoteApplicationLogic.getNoteGui().getNoteImageContainer();

        for(int i = 1; i <= previewImages.size(); i++){
            ImageContainer imageContainer = new ImageContainer(mNoteData.getActivity(), i, previewImages.get(i-1));
            imageContainer.getImageContainer().setOnClickListener(clickListener);
            noteImageContainerLayout.addView(imageContainer.getImageContainer());
        }

        IconContainer iconContainer = new IconContainer(mNoteData.getActivity(), 0, ContextCompat.getDrawable(mNoteData.getActivity(), R.drawable.ic_add_a_photo_grey_24dp));
        iconContainer.getImageContainer().setOnClickListener(clickListener);
        noteImageContainerLayout.addView(iconContainer.getImageContainer());

        //mNoteApplicationLogic.getNoteGui().setNoteImageContainer(noteImageContainerLayout);
    }
}
