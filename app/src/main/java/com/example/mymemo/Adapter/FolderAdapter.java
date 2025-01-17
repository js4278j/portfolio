package com.example.mymemo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mymemo.Data.MemoFolderData;
import com.example.mymemo.Activity.FolderEditActivity;
import com.example.mymemo.R;
import com.example.mymemo.SwipeAndDragHelper;

import java.util.ArrayList;

public class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.ViewHolder>
         implements SwipeAndDragHelper.ActionCompletionContract {

    //편집 버튼 눌렀을때 view
    public static final int VIEWTYPE_NORMAL = 0;
    public static final int VIEWTYPE_EDIT = 1;
    int mItemViewType;
    private ItemTouchHelper touchHelper;

    public void setItemViewType(int viewType) {
        mItemViewType = viewType;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        //return super.getItemViewType(position);
        return mItemViewType;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    private OnItemClickListener mListener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    private ArrayList<MemoFolderData> mData;

    // 생성자에서 데이터 리스트 객체를 전달받음.
    public FolderAdapter(ArrayList<MemoFolderData> list) {
        mData = list;
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView fdTitle, fdCount;
        ImageView fdOption, fdMove, fdRightArrow;

        ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        if (mListener != null) {
                            mListener.onItemClick(v, pos);
                        }
                    }
                }
            });

            // 뷰 객체에 대한 참조. (hold strong reference)
            if (mItemViewType == 0) {
                fdCount = itemView.findViewById(R.id.fdCount);
                fdRightArrow = itemView.findViewById(R.id.fdRightArrow);
            } else {
                fdOption = itemView.findViewById(R.id.fdOption);
                fdMove = itemView.findViewById(R.id.fdMove);
            }

            fdTitle = itemView.findViewById(R.id.fdTitle);
        }
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public FolderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //base
        /*Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.item_adapter_folder, parent, false) ;
        FolderAdapter.ViewHolder vh = new FolderAdapter.ViewHolder(view) ;

        return vh ;*/

        View view;
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (viewType == VIEWTYPE_NORMAL) {
            view = inflater.inflate(R.layout.item_adapter_folder, parent, false);
        } else {
            view = inflater.inflate(R.layout.item_adapter_folder_editmode, parent, false);
        }
        FolderAdapter.ViewHolder vh = new FolderAdapter.ViewHolder(view);
        return vh;

    }


    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(final FolderAdapter.ViewHolder holder, final int position) {

        //임시로 .. 나중에 map을 통해서 데이터 다 받아와야함
        //String text = mData.get(position);
        final String title = mData.get(position).getFdTitle();
        holder.fdTitle.setText(title);
        //int count = mData.get(position).getFdCount();
        if (mItemViewType == 0) {
            int count = 0;
            if (mData.get(position).getMemoList() != null) {
                count = mData.get(position).getMemoList().size();
            }
            holder.fdCount.setText(String.valueOf(count));
        } else {

            holder.fdMove.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if(event.getActionMasked() == MotionEvent.ACTION_DOWN){
                        touchHelper.startDrag(holder);
                    }
                    return false;
                }
            });


            holder.fdOption.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Context context = v.getContext();
                    Intent intent = new Intent(context, FolderEditActivity.class);
                    intent.putExtra("title",title);
                    intent.putExtra("position",position);
                    (context).startActivity(intent);
                    /*Context context = v.getContext();

                    ((MainActivity)v.getContext()).onClickItem(position);
                    ((MainActivity)v.getContext()).changeOptionType(position);*/

                }
            });

        }

    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        if (mData == null) {
            return 0;
        }
        return mData.size();
    }


    @Override
    public void onViewMoved(int from_position, int to_position) {
        //이동할 객체 저장
        MemoFolderData memoFolderData = mData.get(from_position);
        //이동할 객체 삭제
        mData.remove(from_position);
        //이동하고 싶은 position에 추가
        mData.add(to_position, memoFolderData);
        //Adapter에 데이터 이동알림
        notifyItemMoved(from_position, to_position);

    }

    @Override
    public void onViewSwiped(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }

    public void setTouchHelper(ItemTouchHelper touchHelper){
        this.touchHelper = touchHelper;
    }


}
