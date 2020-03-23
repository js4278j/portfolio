package com.example.mymemo.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mymemo.Data.MemoData;
import com.example.mymemo.Data.MemoFolderData;
import com.example.mymemo.R;

import java.util.ArrayList;
import java.util.Locale;

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.ViewHolder> {

    Context context;
    private ArrayList<MemoData> mData;
    private ArrayList<MemoData> arrayList;

    /*public MemoAdapter(Context context , ArrayList<MemoData> list) {
        this.context = context;
        this.mData = list ;
        this.arrayList = new ArrayList<>();
        this.arrayList.addAll(mData);
    }*/

    public MemoAdapter(ArrayList<MemoData> list){
        this.mData = list;
        this.arrayList = new ArrayList<>();
        this.arrayList.addAll(mData);
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    private OnItemClickListener mListener = null;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }


    //private ArrayList<MemoFolderData> mData;

    // 생성자에서 데이터 리스트 객체를 전달받음.


    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView memoTitle,timeTxt,infoTxt;

        ViewHolder(View itemView) {
            super(itemView) ;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        if(mListener != null){
                            mListener.onItemClick(v,pos);
                        }
                    }
                }
            });

            // 뷰 객체에 대한 참조. (hold strong reference)
            memoTitle = itemView.findViewById(R.id.memoTitle);
            timeTxt = itemView.findViewById(R.id.timeTxt);
            infoTxt = itemView.findViewById(R.id.infoTxt);

        }
    }



    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public MemoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.item_adapter_memo, parent, false) ;
        MemoAdapter.ViewHolder vh = new MemoAdapter.ViewHolder(view) ;

        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(MemoAdapter.ViewHolder holder, int position) {
        //임시로 .. 나중에 map을 통해서 데이터 다 받아와야함
        //String text = mData.get(position);

        /*String title = mData.get(position).getFdTitle();
        int count = mData.get(position).getFdCount();*/

        String title = mData.get(position).getTitle();
        //현재 시간은 format 해준 값을 가져와준다.
        String time = mData.get(position).getModifyDate();
        //getContent에서 split 해주고 일부분만 보여준다.
        String info = mData.get(position).getSubTitle();

        holder.memoTitle.setText(title);
        holder.timeTxt.setText(time);
        holder.timeTxt.setText(info);

        /*String title ="";
        String time = "";
        String info ="";

        for(int i =0; i< mData.get(position).getMemoList().size();i++){
             title = mData.get(position).getMemoList().get(i).getTitle();
             time = mData.get(position).getMemoList().get(i).getModifyDate();
             info = mData.get(position).getMemoList().get(i).getSubTitle();

        }*/


        /*mData.get(position).getMemoList().

        String title = mData.get().getMemoList().get(position).getTitle();
        String time = mData.get().getMemoList().get(position).getModifyDate();
        String info = mData.get().getMemoList().get(position).getSubTitle();*/




        /*holder.fdTitle.setText(title);
        holder.fdCount.setText(String.valueOf(count));*/

    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {

        if(mData == null){
            return 0;
        }
        return mData.size() ;
    }


    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        mData.clear();
        if (charText.length() == 0) {
            mData.addAll(arrayList);
        } else {
            for (MemoData memoData : arrayList) {
                //String name = context.getResources().getString(potion.name);
                String name = memoData.getTitle();
                if (name.toLowerCase().contains(charText)) {
                    mData.add(memoData);
                }
            }
        }
        notifyDataSetChanged();
    }
}
