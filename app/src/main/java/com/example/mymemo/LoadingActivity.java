package com.example.mymemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.mymemo.Manager.MemoFolderManager;
import com.example.mymemo.Manager.MemoManager;

public class LoadingActivity extends Activity {

    MemoManager memoMgr = MemoManager.getInstance();

    //한번만 실행된다. 기본적인 정보를 넣고 투명화 시킨다.
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        memoMgr.defaultFolder();
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
}
