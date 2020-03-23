package com.example.mymemo.Data;

import android.support.annotation.ArrayRes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MemoFolderData {

    private int fdID;
    private String fdTitle;
    private int fdCount;

    //Map<String,MemoData> memoDataMap;
    ArrayList<MemoData> memoList;

    public MemoFolderData(int fdID, String fdTitle, int fdCount, ArrayList<MemoData> memoList) {
        this.fdID = fdID;
        this.fdTitle = fdTitle;
        this.fdCount = fdCount;
        this.memoList = memoList;
    }

    public int getFdID() {
        return fdID;
    }

    public void setFdID(int fdID) {
        this.fdID = fdID;
    }

    public String getFdTitle() {
        return fdTitle;
    }

    public void setFdTitle(String fdTitle) {
        this.fdTitle = fdTitle;
    }

    public int getFdCount() {
        return fdCount;
    }

    public void setFdCount(int fdCount) {
        this.fdCount = fdCount;
    }

    public ArrayList<MemoData> getMemoList() {
        return memoList;
    }

    public void setMemoList(ArrayList<MemoData> memoList) {
        this.memoList = memoList;
    }

}
