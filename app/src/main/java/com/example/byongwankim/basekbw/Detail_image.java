package com.example.byongwankim.basekbw;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by byongwankim on 2017. 12. 10..
 */

public class Detail_image extends AppCompatActivity {

    private static final String KBWLOG = "Detail Imageview";

    ImageView detail_image;
    Intent intent = new Intent();
    String image_url;
    Bitmap bitmap;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_imageview);

        detail_image= (ImageView)findViewById(R.id.detail_image);
        intent = getIntent();

        image_url = intent.getStringExtra("d-imageview");
        Log.e(KBWLOG, "11");


        Thread mThread = new Thread(){

            @Override
            public void run(){
                try{
                    URL url = new URL(image_url);
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();

                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);

                }catch (IOException ex){

                }
            }
        };
        mThread.start();
        try{
            mThread.join();
            detail_image.setImageBitmap(bitmap);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

