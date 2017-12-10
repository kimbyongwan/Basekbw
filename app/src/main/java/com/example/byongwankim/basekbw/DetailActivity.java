package com.example.byongwankim.basekbw;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

/**
 * Created by byongwankim on 2017. 11. 28..
 */

public class DetailActivity extends AppCompatActivity {

    private static final String KBWLOG = "log check";

    ListView detail_listview;
    Button detail_button, detail_list;

    Intent intent = new Intent();
    Intent edit_intent = new Intent();
    Intent detail_imageview_intent = new Intent();

    String detail_url, detail_memo;
    String edit_url, edit_memo;
    int custom_count = 0;

    final public DetailAddapter detailAddapter = new DetailAddapter();


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_main);

        intent = new Intent(getApplicationContext(), InsertActivity.class);
        edit_intent = new Intent(getApplicationContext(), Edit_Activity.class);
        detail_imageview_intent = new Intent(getApplicationContext(), Detail_image.class);
        final Intent intent = getIntent();
        Log.e(KBWLOG, "22222");

        detail_url = intent.getStringExtra("data_url");
        Log.e(KBWLOG, detail_url);
        detail_memo = intent.getStringExtra("data_memo");
        Log.e(KBWLOG, detail_memo);


        detail_listview = (ListView) findViewById(R.id.detail_listView);
        detail_listview.setAdapter(detailAddapter);
        detailAddapter.addItem(detail_url, detail_memo);
        detailAddapter.notifyDataSetChanged();
        custom_count = detailAddapter.getCount();

        setResult(RESULT_OK, intent);

        detail_button = (Button) findViewById(R.id.detail_edit);
        detail_list = (Button) findViewById(R.id.detail_list);

        detail_button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(KBWLOG, "edit-111");
                edit_intent.putExtra("url", detail_url);
                Log.e(KBWLOG, "edit-222");
                edit_intent.putExtra("memo", detail_memo);
                Log.e(KBWLOG, "edit-333");
                startActivityForResult(edit_intent, 1);
            }
        });
        detail_list.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit_url != null && edit_memo != null) {
                    intent.putExtra("return_url", edit_url);
                    Log.e(KBWLOG, "re-1");
                    intent.putExtra("return_memo", edit_memo);
                    Log.e(KBWLOG, "re-1");
                    intent.putExtra("return_mode", 1);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    intent.putExtra("return_mode", 2);
                    finish();
                }
            }
        });
        detail_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                detail_imageview_intent.putExtra("d-imageview", detail_url);
                Log.e(KBWLOG, detail_url);
                startActivity(detail_imageview_intent);
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {

                edit_url = data.getStringExtra("edit-url");
                edit_memo = data.getStringExtra("edit-memo");
                detailAddapter.editItem(edit_url, edit_memo, custom_count - 1);
                detailAddapter.notifyDataSetChanged();
            }
        }
    }
    public void onBackPressed(){
        super.onBackPressed();
    }
}
