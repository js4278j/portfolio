package com.example.mymemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.TextView;

import com.example.mymemo.Adapter.FolderAdapter;
import com.example.mymemo.Data.MemoFolderData;
import com.example.mymemo.Manager.MemoManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //MemoFolderManager fdMgr;
    MemoManager memoMgr;
    ArrayList<MemoFolderData> folderList;
    RecyclerView fdRcView;
    FolderAdapter fdAdapter;
    TextView newFolder,editTxt;

    ItemTouchHelper helper;
    SwipeAndDragHelper swipeAndDragHelper;

    public SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = getSharedPreferences("Pref",MODE_PRIVATE);
        memoMgr = MemoManager.getInstance();

        editTxt = findViewById(R.id.editTxt);
        //기본정보 삽입 folderList 0,1 이 같이 공유됨
        //memoMgr.getMemo();

        //folderList = fdMgr.getFolderList();
        //fdMgr.defaultFolder();
        //checkFirstRun();

        /*DefaultThread defaultThread = new DefaultThread();
        defaultThread.run();*/

        fdRcView = findViewById(R.id.folderRcView);
        fdRcView.setLayoutManager(new LinearLayoutManager(this));
        fdAdapter = new FolderAdapter(memoMgr.getFolderList());
        swipeAndDragHelper = new SwipeAndDragHelper(fdAdapter);
        helper = new ItemTouchHelper(swipeAndDragHelper);
        fdAdapter.setTouchHelper(helper);
        fdRcView.setAdapter(fdAdapter);
        //RecyclerView에 ItemTouchHelper 붙이기
        helper.attachToRecyclerView(fdRcView);
        fdRcView.setNestedScrollingEnabled(false);
        fdAdapter.notifyDataSetChanged();

        //편집모드
        editTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editTxt.getText().toString().equals("편집")){
                    fdAdapter.setItemViewType(FolderAdapter.VIEWTYPE_EDIT);
                    editTxt.setText("완료");

                } else if(editTxt.getText().toString().equals("완료")) {
                    fdAdapter.setItemViewType(FolderAdapter.VIEWTYPE_NORMAL);
                    editTxt.setText("편집");

                }
            }
        });


        //새로운 폴더
        newFolder = findViewById(R.id.newFolder);
        newFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),CreateFolderDialog.class);
                startActivity(intent);

            }
        });



        fdAdapter.setOnItemClickListener(new FolderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

                /*if(pos == 1 || pos == 2){
                    //add..
                }*/
                //int x = (int)fdAdapter.getItemId(pos);

                String title = memoMgr.getFolderList().get(pos).getFdTitle();

                //모든나의iPhone은 따로 처리 pos 0 값.
                Intent intent = new Intent(getApplicationContext(),MemoListActivity.class);
                intent.putExtra("title",title);
                intent.putExtra("position",pos);
                //intent.putExtra("null",false);
                startActivity(intent);

            }
        });

    }


    public void checkFirstRun(){
        boolean isFirstRun = prefs.getBoolean("isFirstRun",true);
        if(isFirstRun){
            /*Intent newIntent = new Intent();
            startActivity(newIntent);*/

            //defaultFolder를 한번만 구동 - SharedPreference 사용 SharedPreference
            //fdMgr.defaultFolder();

            prefs.edit().putBoolean("isFirstRun",false).apply();
        }
    }


}
