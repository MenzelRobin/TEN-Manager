package com.example.robin.angrynerds_wip.data.models.tens;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

import com.example.robin.angrynerds_wip.data.models.utils.BundleKeys;
import com.example.robin.angrynerds_wip.data.models.utils.Image;
import com.example.robin.angrynerds_wip.data.repository.RepositoryConstants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Note extends TEN {

    private String description;
    private ArrayList<String> tags;
    private ArrayList<Image> pictures;
    public int imageIDCounter;

    //Constructors
    public Note() {
        super();
        this.tags = new ArrayList<>();
        this.pictures = new ArrayList<>();
        this.imageIDCounter = 0;
    }

    public Note(String title) {
        super(title);
        this.tags = new ArrayList<String>();
        this.pictures = new ArrayList<Image>();
    }

    public Note(String title, String description) {
        super(title);
        this.description = description;
        this.tags = new ArrayList<String>();
        this.pictures = new ArrayList<Image>();
    }

    //all Attributes for complete Reconstruction
    public Note(String title, String ID, int color, int accentColor, Date dateOfCreation, String description, ArrayList<String> tags, ArrayList<Image> pictures, int imageIDCounter) {
        super(title, ID, color, accentColor, dateOfCreation);
        this.description = description;
        this.tags = tags;
        this.pictures = pictures;
        this.imageIDCounter = imageIDCounter;
    }

    //Getter and Setter
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public Image addImage(Bitmap bitmap) {
        this.imageIDCounter++;
        String imageID = this.getID() + RepositoryConstants.IMAGE_CORE_ID + this.imageIDCounter;
        Image image = new Image(imageID, bitmap);
        Log.i("cool", image.getId());
        this.pictures.add(image);
        return image;
    }

    public void addImage(Image pImage) {
        Log.i("NoteRemake", "Pictures Size: " + pictures.size());
        for (int i = 0; i < pictures.size(); i++) {
            if (pImage.getId().equals(pictures.get(i).getId())) {
                pictures.set(i, pImage);
            }
        }
    }

    public ArrayList<Image> getPictures() {
        return pictures;
    }

    public Bundle getBundle() {
        Bundle bundle = super.getBundle();
        bundle.putString(BundleKeys.keyNoteDescription, description);
        bundle.putStringArrayList(BundleKeys.keyNoteTags, tags);
        if (!pictures.isEmpty())
            bundle.putString(BundleKeys.keyNotePictures, pictures.get(0).getId());
        return bundle;
    }

    public void imageNotFound(Image image) {
        for (int i = 0; i < this.getPictures().size(); i++) {
            if (image.getId().equals(this.getPictures().get(i).getId())) {
                this.getPictures().remove(i);
            }
        }
    }
}
