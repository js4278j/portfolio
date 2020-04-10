package com.example.mymemo.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.AnimationUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.mymemo.Adapter.MemoAdapter;
import com.example.mymemo.Manager.MemoManager;
import com.example.mymemo.R;

import java.util.Objects;

public class MemoEditActivity extends AppCompatActivity {

    LinearLayout backLout;
    TextView memoSelect,folderName;

    LayoutInflater inflater;
    RadioButton memoRadio;
    View view;

    String title;
    int position;

    Animation animTransAlpha = null;

    MemoManager memoManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_memo);
        init();

        backLout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        memoSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent 로 스트링값만 보내주고 안에서 교체시켜주기
                //view.startAnimation(animTransAlpha);
                MemoListActivity memoListActivity = (MemoListActivity)memoManager.getValue("MemoListActivity");


                if(View.VISIBLE == memoListActivity.editIcon.getVisibility()){
                    memoListActivity.memoAdapter.setItemViewType(MemoAdapter.VIEWTYPE_EDIT);
                } else{
                    memoListActivity.memoAdapter.setItemViewType(MemoAdapter.VIEWTYPE_NORMAL);
                }
                finish();
            }
        });

        folderName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),NameModifyActivity.class);
                intent.putExtra("title",title);
                intent.putExtra("position",position);
                startActivity(intent);
                finish();
            }
        });
    }


    private void init(){

        memoManager = MemoManager.getInstance();

        this.folderName = findViewById(R.id.folderName);
        this.backLout = findViewById(R.id.backLout);
        this.memoSelect = findViewById(R.id.memoSelect);
        this.inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = getLayoutInflater().inflate(R.layout.item_adapter_memo,null,false);
        //view = inflater.inflate(R.layout.item_adapter_memo, null ,false);
        //LinearLayout linearLayout = (LinearLayout)inflater.inflate(R.layout.item_adapter_memo,null);
        this.memoRadio = view.findViewById(R.id.memoRadio);
        animTransAlpha = AnimationUtils.loadAnimation(this,R.anim.anim_scale_alpha);

        Intent intent = getIntent();
        if(intent.hasExtra("title") || intent.hasExtra("position")){
            title = Objects.requireNonNull(intent.getExtras()).getString("title");
            position = intent.getExtras().getInt(("position"));
        }

    }


}
