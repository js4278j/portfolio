package com.example.mymemo.Manager;

import com.example.mymemo.Data.MemoData;
import com.example.mymemo.Data.MemoFolderData;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MemoFolderManager  {

    private static volatile MemoFolderManager mMoleInstance;

    public static MemoFolderManager getInstance() {
        if (mMoleInstance == null) { //if there is no instance available... create new one
            synchronized (MemoFolderManager.class) {
                if (mMoleInstance == null) mMoleInstance = new MemoFolderManager();
            }
        }
        return mMoleInstance;
    }

    /*MemoFolderData memoFolderData;
    ArrayList<MemoFolderData> folderList = new ArrayList<>();

    //Map<String, MemoData> memoDataMap = new HashMap<>();

    MemoManager memoManager = MemoManager.getInstance();
    ArrayList<MemoData> memoList = memoManager.getMemoList();

    //getFolderList를 가져와야 기본 데이터가 넣어짐 .
    public ArrayList<MemoFolderData> getFolderList() {
        //defaultFolder();
        return folderList;
    }

    public void setFolderList(ArrayList<MemoFolderData> folderList) {

        this.folderList = folderList;
    }




    public void defaultFolder(){

        int[] id = {1,2};
        String[] title = {"모든 나의 iPhone","메모"};
        int[] count = {0,0};


        for(int i=0;i<id.length;i++){
            memoFolderData = new MemoFolderData(id[i],title[i],count[i],memoList);
            folderList.add(memoFolderData);
        }
    }



    public void insert(int id,String title,int count){

        memoFolderData = new MemoFolderData(id,title,count,memoList);
        folderList.add(memoFolderData);
    }

    public void insertMemo(){

    }*/


}
