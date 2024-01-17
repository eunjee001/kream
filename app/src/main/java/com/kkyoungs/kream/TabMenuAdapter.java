package com.kkyoungs.kream;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class TabMenuAdapter extends RecyclerView.Adapter<TabMenuAdapter.TabMenuViewHolder> {

    private OnTabSelectedListener mListener;
    private ArrayList<String> tabMenuList;
    private Context mContext;
    private int mScreenWidth = 0;
    private int mSelectedPosition=0;

    public void setTabMenuList(ArrayList<String> tabMenuList){
        this.tabMenuList = tabMenuList;
        notifyDataSetChanged();
    }

    public void setSelectedPosition(int mSelectedPosition){
        this.mSelectedPosition = mSelectedPosition;
        notifyDataSetChanged();
    }

    public void setOnTabSelectedListener(OnTabSelectedListener mListener){
        this.mListener = mListener;
    }

    public void setScreenWidth(int screenWidth){
        this.mScreenWidth = screenWidth;
    }

    @NonNull
    @Override
    public TabMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.tab_menu_item_layout, parent,false);
        return new TabMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TabMenuViewHolder holder, int position) {
        if (tabMenuList.size() == 1){
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.START;
            holder.llTabRoot.setLayoutParams(params);
            holder.llTabRoot.setPadding(42,0,0,0);

            holder.tvTabTitle.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }else{
            int tabWidth = 0;
            if (mScreenWidth > 0 && tabMenuList.size() > 0){
                tabWidth = mScreenWidth / 5;
            }
            holder.llTabRoot.setLayoutParams(new LinearLayout.LayoutParams(tabWidth, ViewGroup.LayoutParams.WRAP_CONTENT));
        }

        holder.tvTabTitle.setText(tabMenuList.get(position));

        if (mSelectedPosition == position){
            holder.divider.setVisibility(View.VISIBLE);
            holder.tvTabTitle.setTextColor(ContextCompat.getColor(mContext, R.color.color_222222));
//            holder.tvTabTitle.setTypeface(ResourcesCompat.getFont(mContext, R.font.nanum_square_eb), Typeface.BOLD);
        }else{
            holder.divider.setVisibility(View.GONE);
            holder.tvTabTitle.setTextColor(ContextCompat.getColor(mContext, R.color.color_7e7e7e));
        }

        holder.llTabRoot.setOnClickListener(view -> {
            if (mListener != null){
                mListener.onTabSelected(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tabMenuList == null ? 0 : tabMenuList.size();
    }

    public class TabMenuViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTabTitle;
        private View divider;
        private LinearLayout llTabRoot;

        public TabMenuViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTabTitle = itemView.findViewById(R.id.tv_tab_title);
            divider    = itemView.findViewById(R.id.tab_divider);
            llTabRoot  = itemView.findViewById(R.id.ll_tab_root);
        }
    }

    public interface OnTabSelectedListener{
        void onTabSelected(int position);
    }
}
