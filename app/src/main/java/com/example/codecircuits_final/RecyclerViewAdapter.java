package com.example.codecircuits_final;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<MainClass> mainList;

    public RecyclerViewAdapter(Context mContext, ArrayList<MainClass> mainList) {
        this.mContext = mContext;
        this.mainList = mainList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.main_design,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        MainClass currentItem = mainList.get(position);
        String contestName = currentItem.getmContestName();
        String endTime = currentItem.getmEndTime();
        String platform = currentItem.getmPlatform();

        viewHolder.mTVplatform.setText(platform);
        viewHolder.mTVendTime.setText(endTime);
        viewHolder.mTVcontestName.setText(contestName);

    }

    @Override
    public int getItemCount() {
        return mainList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mTVcontestName;
        private TextView mTVendTime;
        private TextView mTVplatform;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTVcontestName = itemView.findViewById(R.id.contestName);
            mTVendTime = itemView.findViewById(R.id.endTime);
            mTVplatform = itemView.findViewById(R.id.platform);
        }
    }
}
