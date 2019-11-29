package com.bw.movie.view.adapter.cinema;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bw.movie.R;
import com.bw.movie.model.bean.cinema.FindAllCinemaCommentBean;
import com.bw.movie.model.bean.details.MovieCommentBean;
import com.bw.movie.view.dateutils.DateUtils;

import java.util.ArrayList;
import java.util.List;


public class RecyclerAllCinemaCommentAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<FindAllCinemaCommentBean.ResultBean> datas = new ArrayList<>();

    public RecyclerAllCinemaCommentAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate
                (R.layout.item_find_all_cinema_comment, viewGroup,false);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MyViewHolder myViewHolder = (MyViewHolder) viewHolder;
        myViewHolder.tv_commentUserName.setText(datas.get(i).getCommentUserName()+"");
        myViewHolder.tv_commentContent.setText(datas.get(i).getCommentContent()+"");
        String times = DateUtils.times(datas.get(i).getCommentTime());
        myViewHolder.tv_commentTime.setText(times);
        Glide.with(context).load(datas.get(i).getCommentHeadPic())
                .transform(new CircleCrop())
                .into(myViewHolder.iv_commentHeadPic);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void addData(List<FindAllCinemaCommentBean.ResultBean> result) {
        if (result!=null && result.size()>0){
            datas.addAll(result);
        }
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_commentTime;
        private final TextView tv_commentUserName;
        private final TextView tv_commentContent;
        private final ImageView iv_commentHeadPic;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_commentTime = itemView.findViewById(R.id.tv_commentTime);
            tv_commentContent = itemView.findViewById(R.id.tv_commentContent);
            iv_commentHeadPic = itemView.findViewById(R.id.iv_commentHeadPic);
            tv_commentUserName = itemView.findViewById(R.id.tv_commentUserName);
        }
    }
}
