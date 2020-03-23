package com.example.mymemo;

import android.content.Context;
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

public class MemoEditActivity extends AppCompatActivity {

    LinearLayout backLout;
    TextView memoSelect;

    LayoutInflater inflater;
    RadioButton memoRadio;
    View view;


    Animation animTransAlpha = null;

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
                finish();
            }
        });
    }

    /*LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

    View view = inflater.inflate(R.layout.item_adapter_folder, parent, false) ;*/

    private void init(){
        this.backLout = findViewById(R.id.backLout);
        this.memoSelect = findViewById(R.id.memoSelect);
        this.inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = getLayoutInflater().inflate(R.layout.item_adapter_memo,null,false);
        //view = inflater.inflate(R.layout.item_adapter_memo, null ,false);
        //LinearLayout linearLayout = (LinearLayout)inflater.inflate(R.layout.item_adapter_memo,null);
        this.memoRadio = view.findViewById(R.id.memoRadio);
        animTransAlpha = AnimationUtils.loadAnimation(this,R.anim.anim_scale_alpha);


    }


}
