package com.example.byongwankim.basekbw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by byongwankim on 2017. 11. 28..
 */

public class DetailAddapter extends BaseAdapter {

    private static final String KBWLOG = "log detail_listview";
    public ArrayList<Item> detail_Items = new ArrayList<>();
    Item detail_Item = new Item();
    Bitmap bitmap;
    ImageView img;
    TextView contents;

    @Override
    public int getCount() {
        return detail_Items.size();
    }

    @Override
    public Object getItem(int position) {
        return detail_Items.get(position);
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
            convertView = inflater.inflate(R.layout.detail_custom, parent, false);
        }
        final Item it = detail_Items.get(position);
        if(it!=null){
            contents = (TextView) convertView.findViewById(R.id.detail_contents);
            img = (ImageView) convertView.findViewById(R.id.detail_img);
            contents.setText(it.getMemo());
        }
        Log.e(KBWLOG,"detail-1");


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
        return convertView;
    }
    public void addItem(String url, String memo){
        detail_Item.setUrl(url);
        detail_Item.setMemo(memo);

        detail_Items.add(detail_Item);
        }
        public void editItem(String url, String memo, int count){
        detail_Item.setUrl(url);
        detail_Item.setMemo(memo);

        detail_Items.set(count,detail_Item);

        }
}
