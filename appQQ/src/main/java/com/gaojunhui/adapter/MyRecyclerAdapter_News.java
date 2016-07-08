package com.gaojunhui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gaojunhui.day6_28qq.R;

import java.util.List;

import bean.News;

/**
 * Created by Administrator on 2016/6/29.
 */
public class MyRecyclerAdapter_News extends RecyclerView.Adapter<MyRecyclerAdapter_News.MyViewHolder> {
    private List<News> list;
    private Context context;
    public interface ItemOnClickListener{
        void onImageClick(View v,int position);
    }
    private ItemOnClickListener listener;
    public void setListener(ItemOnClickListener listener){
        this.listener=listener;
    }
    public MyRecyclerAdapter_News(List<News> list,Context context){
        this.context=context;
        this.list=list;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View  view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_news,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String imageUrl=list.get(position).getImgsrc();
        Glide.with(context).load(imageUrl).into(holder.iv_news);
        holder.tv_ptime.setText(list.get(position).getPtime());
        holder.tv_digest.setText(list.get(position).getTitle());
        holder.tv_soucre.setText(list.get(position).getSource());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_news;
        TextView tv_digest,tv_soucre,tv_ptime;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv_news= (ImageView) itemView.findViewById(R.id.iv_news);
            tv_digest= (TextView) itemView.findViewById(R.id.tv_digest);
            tv_soucre= (TextView) itemView.findViewById(R.id.tv_soucre);
            tv_ptime= (TextView) itemView.findViewById(R.id.tv_ptime);
            iv_news.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "图片被点击", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
