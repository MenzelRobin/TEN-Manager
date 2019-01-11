package com.example.robin.angrynerds_wip.activities.note.note;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.utils.MockData;
import com.example.robin.angrynerds_wip.data.repository.DatabaseConstants;
import com.example.robin.angrynerds_wip.data.repository.Repository;
import com.example.robin.angrynerds_wip.data.services.Read;

import java.util.ArrayList;

class NoteData {

    private NoteActivity mActivity;
    private Note mNote;
    private ArrayList<IContainer> mNoteImageContainers;

    //TODO gegen Note und Images aus Database austauschen
    NoteData(NoteActivity activity) {
        mActivity = activity;
        mNoteImageContainers = new ArrayList<>();

        //Testcode Jan
        Repository repository = new Repository();
        String noteId = "56f5b3bb-d2b5-4059-a7da-34d776d5cff5";
        this.mNote = repository.getNoteByID(noteId);
        int numberOfImages = Read.getNumberOfImages(noteId);

        for (int i = 1; i <= numberOfImages; i++) {
            String imageId = DatabaseConstants.IMAGE_CORE_ID + i;
            LoadImageTask loadImageTask = new LoadImageTask();
            loadImageTask.execute(noteId, imageId);
        }
        //Ende Testcode Jan

//
        // int i = 1;
        // for (Bitmap bm : mNote.getPictures()) {
        //     mNoteImageContainers.add(new ImageContainer(mActivity, i++, bm));
        // }
        //mNoteImageContainers.add(new ImageContainer(mActivity,1, BitmapFactory.decodeResource(mActivity.getResources(), R.drawable.note_sample_image1)));
        //mNoteImageContainers.add(new ImageContainer(mActivity,2, BitmapFactory.decodeResource(mActivity.getResources(), R.drawable.note_sample_image2)));
        //mNoteImageContainers.add(new ImageContainer(mActivity,3, BitmapFactory.decodeResource(mActivity.getResources(), R.drawable.note_sample_image3)));

        addImageButton();
    }

    //Adds the addImageButton to mNoteContainers
    void addImageButton() {
        if (mNoteImageContainers.size() > 0) {
            if (mNoteImageContainers.get(mNoteImageContainers.size() - 1) instanceof IconContainer) {
                mNoteImageContainers.remove(mNoteImageContainers.size() - 1);
            }
        }
        Drawable drawable = ContextCompat.getDrawable(mActivity, R.drawable.ic_add_a_photo_grey_24dp);
        mNoteImageContainers.add(new IconContainer(mActivity, 0, drawable));
    }

    private void updateImageContainer() {

    }

    void saveDataInBundle(Bundle outState) {
        //outState = mNote.getBundle();
    }

    void restoreDataFromBundle(Bundle savedInstanceState) {
    }

    ArrayList<IContainer> getNoteImageContainers() {
        return mNoteImageContainers;
    }

    IContainer getImageContainer(int id) {
        return mNoteImageContainers.get(id);
    }

    NoteActivity getActivity() {
        return mActivity;
    }

    Note getNote() {
        return mNote;
    }

    void setTitle(String title) {
        mNote.setTitle(title);
    }

    void setDescription(String title) {
        mNote.setDescription(title);
    }

    //returns image from mNoteImageContainers
    Bitmap getImage(int id) {
        //TODO get original sized image from database
        return mNoteImageContainers.get(id - 1).getImage();
    }

    //Checks ImageContainer for specific ID
    boolean checkImageID(int id) {
        for (IContainer mImage : mNoteImageContainers) {
            if (mImage.getImageContainer().getId() == id)
                return true;
        }
        return false;
    }

    //Adds image as ImageContainer to mNoteImageContainers
    void saveImage(Bitmap image) {
        ImageContainer imageContainer = new ImageContainer(mActivity, mNoteImageContainers.size(), image);
        mNoteImageContainers.add(mNoteImageContainers.size() - 1, imageContainer);
    }

    //Deletes specific ImageContainer from mNoteImageContainers
    void deleteImage(int id) {
        mNoteImageContainers.remove(id - 1);
        for (int i = 1; i < mNoteImageContainers.size(); i++) {
            mNoteImageContainers.get(i - 1).setImageContainerId(i);
        }
    }

    public class LoadImageTask extends AsyncTask<String, Integer, Bitmap> {

        private final String LOG_TAG = LoadImageTask.class.getSimpleName();

        @Override
        protected Bitmap doInBackground(String... strings) {

            Bitmap bitmap = Read.getImageOfNote(strings[0], strings[1]);

            return bitmap;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {


        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {

            mNote.getPictures().add(bitmap);

            // Hintergrundberechnungen sind jetzt beendet, darüber informieren wir den Benutzer
            Toast.makeText(getActivity(), "Bild geladen!",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
