package com.example.mymemo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mymemo.R;

import java.util.Objects;

public class FolderEditActivity extends AppCompatActivity {

    LinearLayout backLout;
    TextView folderName,folderDel,folderCancel;
    String title;
    int position;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_folder);

        init();

        folderCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        backLout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        folderName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(title != null){
                    Intent intent = new Intent(getApplicationContext(),NameModifyActivity.class);
                    intent.putExtra("title",title);
                    intent.putExtra("position",position);
                    startActivity(intent);
                    finish();
                }

            }
        });

    }

    public void init(){
        this.folderName = findViewById(R.id.folderName);
        this.folderDel = findViewById(R.id.folderDel);
        this.folderCancel = findViewById(R.id.folderCancel);
        this.backLout = findViewById(R.id.backLout);
        overridePendingTransition(R.anim.slide_up, R.anim.slide_up);


        Intent intent = getIntent();
        if(intent.hasExtra("title") || intent.hasExtra("position")){
            title = Objects.requireNonNull(intent.getExtras()).getString("title");
            position = intent.getExtras().getInt(("position"));
        }
    }

}
