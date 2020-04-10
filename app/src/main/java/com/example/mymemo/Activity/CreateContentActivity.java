package com.example.mymemo.Activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mymemo.Data.MemoData;
import com.example.mymemo.Manager.MemoManager;
import com.example.mymemo.R;
import com.example.mymemo.layout.CheckLayout;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class CreateContentActivity extends AppCompatActivity {

    //photo
    public static final int REQUEST_IMAGE_CAPTURE = 1001;
    File file = null;

    public int fdID;
    public String fdTitle;
    public int fdCount;

    InputMethodManager imm;

    ImageView photoImg, checkboxImg, shareImg;
    LinearLayout backLout, screenLout;
    EditText contextTxt;
    TextView okTxt, memoName;
    String category, modifyId;
    int position, memoPos;

    MemoManager memoMgr = MemoManager.getInstance();

    //true일 때 add말고 set을 이용해 수정해주어야함.
    boolean modifyMode;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_content);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        init();

        shareImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //카카오톡 공유하기 추가
                Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                intent.setType("text/plain");

                // Set default text message
                // 카톡, 이메일, MMS 다 이걸로 설정 가능
                //String subject = "문자의 제목";
                String text = contextTxt.getText().toString();
                //intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, text);

                // Title of intent
                Intent chooser = Intent.createChooser(intent, "친구에게 공유하기");
                startActivity(chooser);

            }
        });


        photoImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                photoIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                if (photoIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(photoIntent, REQUEST_IMAGE_CAPTURE);
                }

            }
        });


        screenLout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contextTxt.setCursorVisible(true);
                contextTxt.requestFocus();
                imm.showSoftInput(contextTxt, 0);
            }
        });

        checkboxImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckLayout checkLayout = new CheckLayout(getApplicationContext());
                screenLout.addView(checkLayout);
            }
        });

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

                if(contextTxt.getText().length() == 0){
                    okTxt.setClickable(false);
                }

                String content = contextTxt.getText().toString();
                String[] splitCt = contextTxt.getText().toString().split("\\n");
                String title = splitCt[0];

                String subTitle = "조건사항없음";

                if (splitCt.length >= 2) {
                    subTitle = splitCt[1];
                }

                String id = memoMgr.createKey();
                String modifyDate = memoMgr.setModifyDate();
                MemoData memoData = new MemoData(id, category, title, subTitle, content, modifyDate);

                if (modifyMode) {
                    memoMgr.getFolderList().get(position).getMemoList().set(memoPos, memoData);
                    int allMemoIndex = memoMgr.searchList(modifyId);
                    memoMgr.getAllMemoList().set(allMemoIndex, memoData);

                } else {
                    memoMgr.createMemo(memoData, position);
                    memoMgr.getAllMemoList().add(memoData);

                }

                Intent intent = new Intent(getApplicationContext(), MemoListActivity.class);
                intent.putExtra("title", category);
                intent.putExtra("position", position);

                startActivity(intent);
            }
        });

    }


    private void init() {

        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        shareImg = findViewById(R.id.shareImg);
        photoImg = findViewById(R.id.photoImg);
        checkboxImg = findViewById(R.id.checkboxImg);

        fdID = memoMgr.getFolderList().get(position).getFdID();
        fdTitle = memoMgr.getFolderList().get(position).getFdTitle();
        fdCount = memoMgr.getFolderList().get(position).getFdCount();
        screenLout = findViewById(R.id.screenLout);

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
        if (modifyMode) {
            String modifyContent = intent.getExtras().getString("modifyContent");
            modifyId = intent.getExtras().getString("modifyId");
            contextTxt.setText(modifyContent);

        }

        try {
            file = createFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private File createFile() throws IOException {
        String imageFileName = "test.jpg";
        File storageDir = Environment.getExternalStorageDirectory();
        File curFile = new File(storageDir, imageFileName);

        return curFile;
    }

}
