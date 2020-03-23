package com.example.mymemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mymemo.Data.MemoData;
import com.example.mymemo.Data.MemoFolderData;
import com.example.mymemo.Manager.MemoManager;

import java.util.ArrayList;
import java.util.Objects;

public class CreateContentActivity extends AppCompatActivity {

    public int fdID;
    public String fdTitle;
    public int fdCount;

    LinearLayout backLout;
    EditText contextTxt;
    TextView okTxt,memoName;
    String category,modifyId;
    int position,memoPos;

    MemoManager memoMgr = MemoManager.getInstance();

    //true일 때 add말고 set을 이용해 수정해주어야함.
    boolean modifyMode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_content);

        //init 안에다 모두다 집어넣어준다.
        init();

        Intent intent = getIntent();
        category = Objects.requireNonNull(intent.getExtras()).getString("title");
        position = intent.getExtras().getInt("position");
        modifyMode = intent.getExtras().getBoolean("modifyMod");
        memoPos = intent.getExtras().getInt("memoPos");


        backLout = findViewById(R.id.backLout);
        contextTxt = findViewById(R.id.contextTxt);
        okTxt = findViewById(R.id.okTxt);
        memoName = findViewById(R.id.memoName);

        memoName.setText(intent.getExtras().getString("title"));

        //modifyMod
        if(modifyMode){
            String modifyContent = intent.getExtras().getString("modifyContent");
            modifyId = intent.getExtras().getString("modifyId");
            contextTxt.setText(modifyContent);

            // 나중에 검색할때 폴더 아이디로 검색할거기떄문에 메모내용 검색은 map 으로 검색해야한다.
        }


        backLout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        okTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MemoManager memoMgr = MemoManager.getInstance();
                //title은 \n으로 subString 해서 첫줄을 제목으로 한다.  두번째 줄 부제목
                //String title = contextTxt.getText().toString();
                String content = contextTxt.getText().toString();
                String[] splitCt = contextTxt.getText().toString().split("\\n");
                String title = splitCt[0];

                String subTitle = "조건사항없음";

                if (splitCt.length >= 2) {
                    subTitle = splitCt[1];
                }

                String id = memoMgr.createKey();
                String modifyDate = memoMgr.setModifyDate();
                MemoData memoData = new MemoData(id,category,title,subTitle,content,modifyDate);

                if(modifyMode){
                    memoMgr.getFolderList().get(position).getMemoList().set(memoPos,memoData);
                    //문제점 메모는 바뀌는데 모든iPhone은 바뀌지 않음.
                    //indexOf가 걸러지지 않음 .. index 값으로 변경시키려고 함
                    //getAllMemoList.set(position)에 값을 모른다 . 알아내야함
                    //고유 id 값 가지고 allMemoList index 값을 알아내야함
                    int allMemoIndex = memoMgr.searchList(modifyId);
                    memoMgr.getAllMemoList().set(allMemoIndex,memoData);

                    /*for(int i=0;i<memoMgr.getAllMemoList().size();i++){
                        if(memoMgr.getFolderList().get(position).getMemoList().get(memoPos).get.equals()){
                            int index = i;
                            memoMgr.getAllMemoList().set(index,memoData);
                        }
                    }*/
                    /*if(memoMgr.getAllMemoList().indexOf(memoMgr.getFolderList().get(position).getFdTitle()) != -1){
                        int index = memoMgr.getAllMemoList().indexOf(title);
                        memoMgr.getAllMemoList().set(index,memoData);
                    }*/
                } else {
                    memoMgr.createMemo(memoData,position);
                    memoMgr.getAllMemoList().add(memoData);

                }


                //folderList.get(position).getMemoList().add(memoData);
                //folderList.get(position).getMemoList().set(0,memoData);
                //add 할 때 둘다 add 됨

                /*memoList.add(memoData);
                MemoFolderData memoFolderData = new MemoFolderData(fdID,fdTitle,fdCount,memoList);
                folderList.set(position,memoFolderData);*/

                //memoData = new MemoData(category,title,subTitle,content,modifyDate);

                //folderList.get(position).getMemoList().
                //folderList.get(position).getMemoList().get(position).set
                //folderList에 모든 메모리스트에 저장됨
                Intent intent = new Intent(getApplicationContext(), MemoListActivity.class);
                //intent.putExtra("nullCheck",true);
                /*intent.putExtra("content",content);
                intent.putExtra("title",title);
                intent.putExtra("subTitle",subTitle);*/
                intent.putExtra("title",category);
                intent.putExtra("position",position);

                startActivity(intent);
            }
        });

    }


    private void init(){
        fdID = memoMgr.getFolderList().get(position).getFdID();
        fdTitle = memoMgr.getFolderList().get(position).getFdTitle();
        fdCount = memoMgr.getFolderList().get(position).getFdCount();

    }

}
