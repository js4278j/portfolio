package com.example.mymemo.Dialog;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.mymemo.R;

public class WarningDialog extends AppCompatActivity {

    TextView okTxt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_warning);

        init();

        okTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void init(){
        okTxt = findViewById(R.id.okTxt);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //return super.onTouchEvent(event);
        //return false 로 할 경우 외부 액티비티 터치시 닫힘 방지 할 수 있음 .
        return false;
    }

}
