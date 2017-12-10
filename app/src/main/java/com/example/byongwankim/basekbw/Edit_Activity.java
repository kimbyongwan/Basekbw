package com.example.byongwankim.basekbw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by byongwankim on 2017. 12. 6..
 */

public class Edit_Activity extends AppCompatActivity {
    Intent intent = new Intent();
    Bundle bb = new Bundle();
    String edit_url_1, edit_memo_1;
    Button edit_btn;
    EditText edit_text_url, edit_text_memo;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_layout);

        edit_text_url = (EditText)findViewById(R.id.edit_url);
        edit_text_memo = (EditText)findViewById(R.id.edit_memo);
        edit_btn = (Button)findViewById(R.id.btn_add);

        intent = getIntent();
        edit_url_1 = intent.getStringExtra("url");
        edit_memo_1 = intent.getStringExtra("memo");

        edit_text_url.setText(edit_url_1);
        edit_text_memo.setText(edit_memo_1);
        edit_btn.setText("수   정");

        edit_btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("edit-url", edit_text_url.getText().toString());
                intent.putExtra("edit-memo",edit_text_memo.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });




    }
}
