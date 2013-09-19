package com.futurice.kaiinkinen.yetanotherapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by kink on 2013.09.19.
 */
public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

    private final ImageView view;

    /**
     * @param view
     */
    public DownloadImageTask(final ImageView view) {
        this.view = view;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        String url = urls[0]; // Just support one for now
        Bitmap bitmap = null;


        try {
            InputStream inputStream = new URL(url).openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Problem when parsing the url and opening the stream");
        }

        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        view.setImageBitmap(result);
        view.setVisibility(View.VISIBLE);
    }
}
