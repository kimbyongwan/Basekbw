package com.example.byongwankim.basekbw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private static final String KBWLOG = "log check";

    Button addbtn;
    EditText editurl, editmemo;
    String url, memo;
    final public CustomAddapter mCustomAddapter = new CustomAddapter();
    int count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listView);
        addbtn = (Button) findViewById(R.id.addbtn);

        final Intent intent;
        final Intent detail_intent;
        intent = new Intent(getApplicationContext(), InsertActivity.class);
        detail_intent = new Intent(getApplicationContext(), DetailActivity.class);
        mListView.setAdapter(mCustomAddapter);

        addbtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent.putExtra("MODE", "1");
                startActivityForResult(intent, 1);
                url = "";
                memo = "";
                Log.e(KBWLOG, "1" + url);
                Log.e(KBWLOG, "2" + memo);
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item a = mCustomAddapter.mItems.get(position);
                final Bundle b = new Bundle();
                b.putString("data_url", a.getUrl());
                b.putString("data_memo", a.getMemo());
               /* String aa = a.getMemo();
                String bb = a.getUrl();;
                detail_intent.putExtra("data_url", bb);
                detail_intent.putExtra("data_memo", aa);  */
                detail_intent.putExtras(b);
                /*intent.putExtra("MODE", 2);*/
                //String pass_memo = view.findViewById(R.id.contents).toString();
                //Object vo = (Object)parent.getAdapter().getItem(position);
                /*String vo = (String)parent.getAdapter().getItem(position);
                Log.e(KBWLOG,vo);*/

                startActivityForResult(detail_intent, 2);

                count = position;

            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            //int countmode = data.getIntExtra("data_mode", 1);

            if (requestCode == 1) {
                url = data.getStringExtra("data_url");
                memo = data.getStringExtra("data_memo");
                mCustomAddapter.addItem(url, memo);
                mCustomAddapter.notifyDataSetChanged();
                Log.e(KBWLOG, url);
                Log.e(KBWLOG, memo);
                //countmode =0;
            } else if (requestCode == 2) {
                int return_mode = data.getIntExtra("return_mode", 1);
                if (return_mode == 1) {
                    url = data.getStringExtra("return_url");
                    memo = data.getStringExtra("return_memo");
                    mCustomAddapter.editItem(url, memo, count);
                    mCustomAddapter.notifyDataSetChanged();
                } else {

                }
            }
        }
    }
}
