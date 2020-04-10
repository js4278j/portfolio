package com.example.mymemo.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mymemo.Adapter.MemoAdapter;
import com.example.mymemo.Data.MemoData;
import com.example.mymemo.Data.MemoFolderData;
import com.example.mymemo.Manager.MemoManager;
import com.example.mymemo.R;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class MemoListActivity extends AppCompatActivity {


    RecyclerView memoRcView;
    MemoAdapter memoAdapter;
    MemoManager memoMgr = MemoManager.getInstance();

    ArrayList<MemoData> memoList;
    ArrayList<MemoFolderData> folderList;

    ImageView createImg;
    LinearLayout backLout;
    TextView memoTitle,cancelTxt;
    EditText searchBar;

    String title;
    int position;

    Activity activity;
    ImageView editIcon;

    MemoManager memoManager;

    MemoListActivity self = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_list);
        init();

        //글을 수정할 때.
        memoAdapter.setOnItemClickListener(new MemoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

                String modifyContent = memoMgr.getFolderList().get(position).getMemoList().get(pos).getContent();
                String modifyId = memoMgr.getFolderList().get(position).getMemoList().get(pos).getId();

                Intent loadContent = new Intent(getApplicationContext(), CreateContentActivity.class);
                loadContent.putExtra("title",title);
                loadContent.putExtra("position",position);
                loadContent.putExtra("memoPos",pos);
                loadContent.putExtra("modifyId",modifyId);
                loadContent.putExtra("modifyContent",modifyContent);
                loadContent.putExtra("modifyMod",true);
                startActivity(loadContent);
            }
        });


        createImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CreateContentActivity.class);
                intent.putExtra("title",title);
                intent.putExtra("position",position);
                intent.putExtra("modifyMod",false);
                startActivity(intent);
            }
        });

        backLout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                //flag 써야할 것 같다.
                startActivity(intent);
            }
        });


        editIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //변수로 집어넣어야함 MemoListActivity로 넣으면 getValue 할 때  캐스팅 오류
                memoManager.register("MemoListActivity",self);

                Intent editIntent = new Intent(activity,MemoEditActivity.class);
                editIntent.putExtra("title",title);
                editIntent.putExtra("position",position);
                //editIntent.putExtra("viewType",MemoAdapter.VIEWTYPE_EDIT);
                startActivity(editIntent);
                overridePendingTransition(R.anim.slide_up, R.anim.slide_up);
            }
        });


        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                //대문자가 있다면 소문자로 변환.
                String text = searchBar.getText().toString().toLowerCase(Locale.getDefault());
                memoAdapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });
    }

    public void init(){

        memoManager = MemoManager.getInstance();

        this.activity = this;
        this.memoTitle = findViewById(R.id.memoTitle);
        this.editIcon = findViewById(R.id.editIcon);
        this.searchBar = findViewById(R.id.searchBar);
        this.cancelTxt = findViewById(R.id.cancelTxt);

        //intent null check
        Intent intent = getIntent();
        if(intent.hasExtra("title") || intent.hasExtra("position")){
            title = Objects.requireNonNull(intent.getExtras()).getString("title");
            position = intent.getExtras().getInt("position");
        }

        memoTitle.setText(title);

        //position 값이 0일 때 allMemoList를 가져오게 해주어야한다.
        //리스트를 모두다 보여주기만 하면된다 0을 눌렀을 떄 .
        if(position == 0){
            //memoList = memoMgr.allMemoList();
            memoList = memoMgr.getAllMemoList();
        }
        memoList = memoMgr.getFolderList().get(position).getMemoList();

        memoRcView = findViewById(R.id.memoRcView);
        memoRcView.setLayoutManager(new LinearLayoutManager(this));
        memoAdapter = new MemoAdapter(memoList);
        memoRcView.setNestedScrollingEnabled(false);
        memoRcView.setAdapter(memoAdapter);
        memoAdapter.notifyDataSetChanged();

        createImg = findViewById(R.id.createImg);
        backLout = findViewById(R.id.backLout);

    }

}
