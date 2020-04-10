package com.example.mymemo.Dialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mymemo.Data.MemoFolderData;
import com.example.mymemo.Manager.MemoManager;
import com.example.mymemo.R;

import java.util.ArrayList;


public class CreateFolderDialog extends AppCompatActivity{

    MemoManager memoMgr;
    EditText editFolder;
    TextView cancelTxt,saveTxt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_folder_create);

        memoMgr = MemoManager.getInstance();

        editFolder = findViewById(R.id.editFolder);
        cancelTxt = findViewById(R.id.cancelTxt);
        saveTxt = findViewById(R.id.saveTxt);


        saveTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editFolder.getText() == null){
                    saveTxt.setClickable(false);
                } else {

                    int id = 3;
                    String memoTitle = editFolder.getText().toString();

                    //중복체크 메소드화 시켜야함 .
                    //boolean 으로 true 반환하면 중복 false이면 중복아님
                    if(memoMgr.checkFolder(memoTitle)){
                        Intent intent = new Intent(getApplicationContext(),WarningDialog.class);
                        startActivity(intent);
                        finish();
                    } else {
                        memoMgr.insert(id,memoTitle,0);
                        finish();
                    }
                }
            }
        });

        cancelTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //return super.onTouchEvent(event);
        //return false 로 할 경우 외부 액티비티 터치시 닫힘 방지 할 수 있음 .
        return false;
    }
}


