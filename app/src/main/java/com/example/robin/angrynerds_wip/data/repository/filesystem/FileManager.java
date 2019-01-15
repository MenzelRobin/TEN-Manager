package com.example.robin.angrynerds_wip.data.repository.filesystem;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.example.robin.angrynerds_wip.activities.note.note.data.PreviewImageCreator;
import com.example.robin.angrynerds_wip.data.models.utils.Image;
import com.example.robin.angrynerds_wip.data.repository.Repository;
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

    public void saveImageToDirectory(Image pImage ) throws IOException {
        PreviewImageCreator previewImageCreator = new PreviewImageCreator();
        String[] folders = {RepositoryConstants.IMAGE_ORIGINAL_FOLDER, RepositoryConstants.IMAGE_PREVIEW_FOLDER};
        for (String folder : folders) {

            String folderPath = Environment.DIRECTORY_PICTURES + "/" + folder;
            String imageName = pImage.getId() + ".jpg";

            File storageDir = context.getExternalFilesDir(folderPath);
            FileOutputStream fos = null;
            Bitmap bitmap = pImage.getBitmap();
            File image = new File(storageDir, imageName);

            if (folder.equals(RepositoryConstants.IMAGE_PREVIEW_FOLDER)) {
                bitmap = previewImageCreator.getPreviewImage(bitmap);
            }


            if (!image.exists()) {
                Log.i("NoteRemake", "Image " + pImage.getId() + " was saved!");
                fos = new FileOutputStream(image);

                bitmap.compress(Bitmap.CompressFormat.JPEG, 75, fos);
                fos.flush();
                fos.close();
            }
        }

    }

    public Image readImageFromDirectory(Image image, String directory) {
        String directoryPath = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/" + directory + "/";
        String filePath = directoryPath + image.getId() + ".jpg";
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

    public Context getContext() {
        return context;
    }
}
