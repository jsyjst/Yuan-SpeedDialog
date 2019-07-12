package com.example.yuan_dialog.adapter;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yuan_dialog.R;
import com.example.yuan_dialog.listener.OnMenuItemClickListener;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/07/12
 *     desc   :
 * </pre>
 */

public class BottomDialogAdapter extends RecyclerView.Adapter<BottomDialogAdapter.ViewHolder> {
    private static final String TAG = "BottomDialogAdapter";
    private List<String> mMenuNameList;
    private OnMenuItemClickListener mMenuItemClickListener;
    private Dialog mDialog;

    public BottomDialogAdapter(List<String> menuNameList){
        mMenuNameList = menuNameList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_bottom_recycler,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String menuName = mMenuNameList.get(i);
        viewHolder.menuTv.setText(menuName);
        viewHolder.menuItem.setOnClickListener(view1 -> {
            if(mMenuItemClickListener !=null) {
                mDialog.cancel();
                mMenuItemClickListener.onClick(mDialog,i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMenuNameList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView menuTv;
        View menuItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            menuTv = itemView.findViewById(R.id.menuTv);
            menuItem = itemView;
        }
    }

    public void setMenuItemClickListener(OnMenuItemClickListener menuItemClickListener, Dialog dialog){
        mDialog = dialog;
        mMenuItemClickListener = menuItemClickListener;
    }
}
