package com.example.byongwankim.basekbw;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by byongwankim on 2017. 11. 23..
 */

public class InsertActivity extends AppCompatActivity {

    private static final String KBWLOG = "log insertActivity";
    EditText insert_url, insert_memo;
    Button insert_add;
    int modeint;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_layout);

        insert_url = (EditText) findViewById(R.id.edit_url);
        insert_memo = (EditText) findViewById(R.id.edit_memo);
        insert_add = (Button) findViewById(R.id.btn_add);

        final Intent intent = getIntent();
        modeint = intent.getIntExtra("MODE", 1);
        if (modeint == 2) {
            insert_add.setText("수   정");
        }

        insert_add.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (modeint == 1) {
                    Log.e(KBWLOG, "3");
                    Log.e(KBWLOG, String.valueOf(modeint));
                    intent.putExtra("data_url", insert_url.getText().toString());
                    Log.e(KBWLOG, "4");
                    intent.putExtra("data_memo", insert_memo.getText().toString());
                    intent.putExtra("data_mode", 1);
                    Log.e(KBWLOG, "5");
                    setResult(RESULT_OK, intent);
                    Log.e(KBWLOG, insert_memo.getText().toString());
                    Log.e(KBWLOG, insert_url.getText().toString());
                    finish();
                } else if (modeint == 2) {
                    intent.putExtra("data_url", insert_url.getText().toString());
                    intent.putExtra("data_memo", insert_memo.getText().toString());
                    intent.putExtra("data_mode", 2);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

    }
}
