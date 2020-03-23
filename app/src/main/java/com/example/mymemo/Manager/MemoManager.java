package com.example.mymemo.Manager;

import android.widget.Toast;

import com.example.mymemo.Data.MemoData;
import com.example.mymemo.Data.MemoFolderData;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MemoManager {

    private static volatile MemoManager mMoleInstance;

    public static MemoManager getInstance() {
        if (mMoleInstance == null) { //if there is no instance available... create new one
            synchronized (MemoManager.class) {
                if (mMoleInstance == null) mMoleInstance = new MemoManager();
            }
        }
        return mMoleInstance;
    }

    private ArrayList<MemoFolderData> folderList = new ArrayList<>();
    public ArrayList<MemoData> memoList;


    public ArrayList<MemoFolderData> getFolderList() {
        return folderList;
    }

    public void setFolderList(ArrayList<MemoFolderData> folderList) {
        this.folderList = folderList;
    }


    public void insert(int id, String title, int count ){
        memoList = new ArrayList<>();
        MemoFolderData memoFolderData = new MemoFolderData(id,title,count,memoList);
        folderList.add(memoFolderData);
    }

    public void createMemo(MemoData memoData,int position){

            if(folderList.get(position) != null){
            folderList.get(position).getMemoList().add(memoData);
        }
    }


    //private ArrayList<MemoData> allMemoList = allMemoList();
    private ArrayList<MemoData> allMemoList = new ArrayList<>();

    public ArrayList<MemoData> getAllMemoList() {
        return allMemoList;
    }

    public void setAllMemoList(ArrayList<MemoData> allMemoList) {
        this.allMemoList = allMemoList;
    }

    public void defaultFolder(){
        memoList = new ArrayList<>();
        //같은 memoList 라 같이 공유된다 . 모든 아이폰을 눌렀을때는 모든 리스트가 다나오게 따로 만들어야한다.
        //allFolderData 는 모든 memoList를 담아야 한다.
        //allMemoList = allMemoList();
        MemoFolderData allFolderData = new MemoFolderData(0,"모든 나의 iPhone",0,allMemoList);
        MemoFolderData defaultFolder = new MemoFolderData(1,"메모",0,memoList);

        folderList.add(0,allFolderData);
        folderList.add(1,defaultFolder);
    }

    //모든 메모리스트가 안가져와 진다. 디버깅 떄려봐야함 allMemoList defaultFolder가 처음에만 걸려서 allMemoList가 계속 실행이 안된다 모든 Iphone을 눌렀을 때 발동되어야함 어댑터 또는 다른곳에 설정해야함
    public ArrayList<MemoData> allMemoList2(){

        // 안하면 null.
        //allMemoList = new ArrayList<>();

        //getAllMemoList 안에 add 와 get을 잘사용해야함 계속 add 시킴
        for(int i = 1;i<folderList.size();i++){
            for(int j = 0; j< folderList.get(i).getMemoList().size();j++){
                if(folderList.get(j).getMemoList().size() > 0){
                    MemoData memoData = new MemoData(folderList.get(j).getMemoList().get(j).getId(),folderList.get(j).getMemoList().get(j).getCategory(),folderList.get(j).getMemoList().get(j).getTitle(),folderList.get(j).getMemoList().get(j).getSubTitle(),folderList.get(j).getMemoList().get(j).getContent(),folderList.get(j).getMemoList().get(j).getModifyDate());
                    allMemoList.add(memoData);
                }
            }
            /*if(folderList.get(i).getMemoList().size() != 0){
                MemoData memoData = new MemoData(folderList.get(i).getMemoList().get(i).getId(),folderList.get(i).getMemoList().get(i).getTitle(),folderList.get(i).getMemoList().get(i).getSubTitle(),folderList.get(i).getMemoList().get(i).getContent(),folderList.get(i).getMemoList().get(i).getModifyDate());
                memoList.add(memoData);

            }*/
        }
        return allMemoList;


        /*if(folderList.size() != 0 ){
            for(int i =0;i<folderList.size();i++){
                MemoData memoData = new MemoData(folderList.get(i).getMemoList().get(i).getId(),folderList.get(i).getMemoList().get(i).getTitle(),folderList.get(i).getMemoList().get(i).getSubTitle(),folderList.get(i).getMemoList().get(i).getContent(),folderList.get(i).getMemoList().get(i).getModifyDate());
                memoList.add(memoData);
                //folderList.get(i).getMemoList()
            }
        }*/

        //return memoList;
        /*ArrayList<MemoData> memoList = new ArrayList<>();

        if(folderList.size() != 0){
            for(int i=0;i<folderList.size();i++){
                for(int j=0;j<folderList.get(i).getMemoList().size();j++){
                    memoList = folderList.get(i).getMemoList();
                    String id = memoList.get(j).getId();
                    String title = memoList.get(j).getTitle();
                    String subTitle = memoList.get(j).getSubTitle();
                    String content = memoList.get(j).getContent();
                    String date = memoList.get(j).getModifyDate();
                    MemoData memoData = new MemoData(id,title,subTitle,content,date);
                    memoList.add(j,memoData);
                }
            }
            return memoList;
        } else {
            return new ArrayList<>();
        }*/



        /*ArrayList<MemoData> memoList = new ArrayList<>();


        for(MemoFolderData memoFolderData : folderList){
            memoFolderData.getMemoList();
        }*/


    }


    public String createKey(){

        String id;
        long time = System.nanoTime();
        id = longToBase64(time);

        return id;
        /*for(int i=0; i<1000; i++) {
            long time =  System.nanoTime();
            System.out.println(time + " -> " + longToBase64(time));
        }*/
    }

    //고유값 설정
    private String longToBase64(long v) {
        final char[] digits = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
                'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D',
                'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
                'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
                'Y', 'Z', '#', '$'
        };

        int shift = 6;
        char[] buf = new char[64];
        int charPos = 64;
        int radix = 1 << shift;
        long mask = radix - 1;
        long number = v;

        do {
            buf[--charPos] = digits[(int) (number & mask)];
            number >>>= shift;
        } while (number != 0);

        return new String(buf, charPos, (64 - charPos));
    }

    public String setModifyDate(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");

        String modifyDate = sdf.format(date);

        return modifyDate;
    }


    //allMemoList 에있는 타이틀 제목과 folderList 안에 메모리스트 타이틀을 비교해준후 그 값이 같으면 set? 해준다 .
    public void setAllList(String content){

        for(int i=0;i<allMemoList.size();i++){
            /*if(allMemoList.get(i).getTitle().equals()){
                allMemoList.set
            }*/
        }

        /*for(int i =0;i<folderList.size();i++){
            for(int j=0;j<folderList.get(i).getMemoList().size();j++) {
                if (!folderList.get(i).getMemoList().get(j).getTitle().equals(title)) {
                    allMemoList.add(folderList.get(i))
                }
            }



                for(int k=0;i<allMemoList.size();k++){
                    if(allMemoList.get(k).getfolderList.get(i).getFdID())
                }


                allMemoList.add(folderList.get(i).getMemoList().get(j));
            }
        }*/
        //allMemoList.add(folderList.get(0).getMemoList().get(0));
    }

    public int searchList(String id){

        //allMemoLIst에 position 값
        int index = -1;
        //allMemoList 가 하나씩 늘어남 무한 반복함 다른 함수가 적욛됨. 함수 사용 시 조심 함수와 멤버 변수를 같이 쓰면안된다 return값이 똑같을 경우
        for(int i=0;i<allMemoList.size();i++){
            if(allMemoList.get(i).getId().equals(id)){
                index = i;
            }
        }


        return index;

    }


    public boolean checkFolder(String fdTitle){

        boolean checkFolder = false;
        int check = -1;

        for(int i=0;i<getFolderList().size();i++){
            if(getFolderList().get(i).getFdTitle().equals(fdTitle)){
                //중복이면 0
                check = 0;
            }
        }

        if(check == 0){
            checkFolder = true;
        }

        return checkFolder;
    }

    //private ArrayList<MemoData> memoList = new ArrayList<>();


    /*public MemoManager() {
        memoList = new ArrayList<>();
    }

    public ArrayList<MemoData> getMemoList() {

        return memoList;
    }

    public void setMemoList(ArrayList<MemoData> memoList) {

        this.memoList = memoList;
    }


    public void insert(String id, String title, String subTitle, String content){
        String curTime = addDate();

        //id는 폴더이름과 같게 한다.

        MemoData memoData = new MemoData(id ,title,subTitle,content,curTime);
        memoList.add(memoData);
        setMemoList(memoList);

        getFolder(id);
    }

    MemoFolderManager memoFolderMgr = MemoFolderManager.getInstance();

    public void getFolder(String id){

        for(int i=0;i<memoFolderMgr.getFolderList().size();i++){
            if(memoFolderMgr.getFolderList().get(i).getFdTitle().equals(id)){
                //memoFolderMgr.getFolderList().set(i,)
            }
        }
    }

    public void search(String title){

    }


    private String addDate(){

        Date time = new Date();

        SimpleDateFormat format1 = new SimpleDateFormat ( "HH:mm");
        SimpleDateFormat format2 = new SimpleDateFormat ( "yyyy.MM.dd");

        String time1 = format1.format(time);
        //String time2 = format2.format(time);

        *//*System.out.println(time1);
        System.out.println(time2);*//*
        return time1;
    }

    public boolean nullCheck(String[] check){
        boolean hasValue = false;

        for(String var:check){
            if(var.length() == 2){
                hasValue = true;
                break;
            }
        }

        return hasValue;
    }*/


}
