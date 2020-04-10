package com.example.mymemo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mymemo.Manager.MemoManager;
import com.example.mymemo.R;

import java.util.Objects;

public class NameModifyActivity extends AppCompatActivity {

    MemoManager memoMgr;
    TextView cancelTxt,saveTxt,folderDel;
    EditText folderNameEdit;
    String title;
    int position;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_name);

        init();

        folderNameEdit.setText(title);

        folderDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        saveTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = folderNameEdit.getText().toString();
                //null 뜸
                memoMgr.modifyFolder(title,position);
                finish();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        cancelTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void init(){
        overridePendingTransition(R.anim.slide_up, R.anim.slide_up);

        memoMgr = MemoManager.getInstance();

        cancelTxt = findViewById(R.id.cancelTxt);
        saveTxt = findViewById(R.id.saveTxt);
        folderDel = findViewById(R.id.folderDel);
        folderNameEdit = findViewById(R.id.folderNameEdit);

        Intent intent = getIntent();
        if(intent.hasExtra("title") || intent.hasExtra("position")){
            title = Objects.requireNonNull(intent.getExtras()).getString("title");
            position = intent.getExtras().getInt(("position"));
        }


    }
}
