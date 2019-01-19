package com.example.robin.angrynerds_wip.data.repository.filesystem;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.example.robin.angrynerds_wip.data.models.utils.Image;
import com.example.robin.angrynerds_wip.data.repository.RepositoryConstants;
import com.example.robin.angrynerds_wip.data.repository.database.DatabaseManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileManager {

    private Context context;

    public FileManager() {
        context = DatabaseManager.context;
    }

    public void saveImageToDirectory(Image pImage) throws IOException {
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        FileOutputStream fos = null;

        Bitmap bitmap = pImage.getBitmap();
        File image = new File(storageDir, pImage.getId() + ".jpg");

        if (!image.exists()) {
            Log.i("NoteRemake", "Image " + pImage.getId() + " was saved!");
            fos = new FileOutputStream(image);

            bitmap.compress(Bitmap.CompressFormat.JPEG, 75, fos);
            fos.flush();
            fos.close();
        }
    }

    public Image readImageFromDirectory(Image image) {
        String directoryPath = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath();
        String filePath = directoryPath + "/" + image.getId() + ".jpg";
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        Log.i("NoteRemake", "" + new File(filePath).exists());
        Image result = new Image(image.getId(), bitmap);
        return result;
    }

    public void deleteImageFromDirectory(String path) {
        File file = new File(path);
        Log.i("NoteRemake", "Gelöschtes Bild existiert: " + file.exists());
        file.delete();
    }

    public void deleteImageFromDirectory(Image image) {
        ImageDeleter imageDeleter = new ImageDeleter(this);
        imageDeleter.execute(image);
    }

    public void renameImage(String currentPhotoPath, String futureID) {
        String directoryPath = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath();
        String futurePath = directoryPath + "/" + futureID + ".jpg";

        File oldName = new File(currentPhotoPath);
        File newFile = new File(futurePath);
        oldName.renameTo(newFile);

    }

    public Context getContext() {
        return context;
    }
}
