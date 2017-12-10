package com.example.byongwankim.basekbw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by byongwankim on 2017. 11. 20..
 */

public class CustomAddapter extends BaseAdapter {

    private static final String KBWLOG = "log CustomAdapter";
    public ArrayList<Item> mItems = new ArrayList<>();
    public Item mItem = new Item();
    Bitmap bitmap;

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Context context = parent.getContext();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_custom, parent, false);
        }
        final Item it = mItems.get(position);
        if (it != null) {
            TextView contents = (TextView) convertView.findViewById(R.id.contents);
            ImageView img = (ImageView) convertView.findViewById(R.id.img);

            try {
                if (it.getMemo().length() < 1) {
                    contents.setText("");
                } else {
                    contents.setText(it.getMemo());
                    Log.e(KBWLOG, "8" + it.getUrl());
                    Log.e(KBWLOG, "9" + it.getMemo());
                    Log.e(KBWLOG, "10" + position);

                    Thread mThread = new Thread() {

                        @Override
                        public void run() {
                            try {
                                URL url = new URL(it.getUrl());
                                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                conn.setDoInput(true);
                                conn.connect();

                                InputStream is = conn.getInputStream();
                                bitmap = BitmapFactory.decodeStream(is);

                            } catch (IOException ex) {

                            }
                        }
                    };
                    mThread.start();
                    try {
                        mThread.join();
                        img.setImageBitmap(bitmap);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } catch (NullPointerException e) {

            }
        }
        return convertView;
    }

    public void addItem(String url, String memo) {
        mItem.setUrl(url);
        mItem.setMemo(memo);

        Log.e(KBWLOG, "6" + url);
        Log.e(KBWLOG, "7" + memo);

        mItems.add(mItem);

    }

    public void editItem(String url, String memo, int count) {
        mItem.setUrl(url);
        mItem.setMemo(memo);
        mItems.set(count, mItem);

    }
}
