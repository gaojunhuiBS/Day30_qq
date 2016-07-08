package com.gaojunhui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.gaojunhui.day6_28qq.R;
import com.gaojunhui.utils.HttpUtils;

import java.util.List;
import bean.Video;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import utils.LengthToWan;
import utils.SecondToMill;

/**
 * Created by Administrator on 2016/6/29.
 */
public class MyRecyclerAdapter_Video extends RecyclerView.Adapter<MyRecyclerAdapter_Video.MyViewHolder> {
    private List<Video> list;
    private Context context;
    private String imagUrl;
    private Bitmap bm;
    private MyViewHolder holder;
    private String videoUrl;
    public interface ItemClickLisener{
        void onImageClick(View v,int position);
    }
    private ItemClickLisener lisener;
    public void setLisener(ItemClickLisener lisener){
        this.lisener=lisener;
    }
    public MyRecyclerAdapter_Video(List<Video> list,Context context){
        this.list=list;
        this.context=context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View coverView= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_video,parent,false);
        holder=new MyViewHolder(coverView);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        //底图
        String title1=list.get(position).getSectiontitle();
        imagUrl=list.get(position).getCover();
        videoUrl=list.get(position).getMp4_url();
        holder.video.setUp(videoUrl,title1);
       Glide.with(context).load(imagUrl).into(holder.video.thumbImageView);
//        holder.video.getInstance().displayImage("http://cos.myqcloud.com/1000264/qcloud_video_attachment/842646334/vod_cover/cover1458036374.jpg", videoController.ivThumb);
//        Uri uri=Uri.parse(imagUrl);
//        if (imagUrl!=null){
//            holder.video.setUp(imagUrl,"ss");
//        }
//        GenericDraweeHierarchy hierarchy= GenericDraweeHierarchyBuilder
//                .newInstance(context.getResources())
//                .setPlaceholderImage(R.mipmap.ic_launcher)
//                .setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP)
//                .setPlaceholderImageScaleType(ScalingUtils.ScaleType.CENTER_CROP)
//                .setFailureImage(R.drawable.yyy)
//                .setFailureImageScaleType(ScalingUtils.ScaleType.CENTER_CROP)
//                .setRetryImage(R.drawable.miaomiao)
//                .setRetryImageScaleType(ScalingUtils.ScaleType.CENTER_CROP)
////                .setOverlay(context.getResources().getDrawable(R.drawable.nvnv))
////                .setProgressBarImage(new MyProgress(){
////                    @Override
////                    protected boolean onLevelChange(int level) {
////                        if (level==10000){
////                            holder.progressBar.setVisibility(View.INVISIBLE);
////                        }
////                        return super.onLevelChange(level);
////                    }
////                })
//                .build();
//        DraweeController controller= Fresco.newDraweeControllerBuilder()
//                .setUri(uri).build();
//        holder.iv.setHierarchy(hierarchy);
//        holder.iv.setController(controller);
//        holder.iv.setImageURI(uri);
        //用户头像
        String userUrl=list.get(position).getTopicImg();
        Uri uri_user=Uri.parse(userUrl);
        holder.iv_user.setImageURI(uri_user);

        String time= SecondToMill.secToTime(list.get(position).getLength());//秒转换成xx:xx
        holder.tv_time.setText(time);
        String count= LengthToWan.lengByWan(list.get(position).getPlayCount());//转换成xx+播放
        holder.tv_count.setText("/"+count+"次播放");

        holder.tv_user_name.setText(list.get(position).getTopicName());
        holder.tv_title.setText(list.get(position).getTitle());
        holder.tv_description.setText(list.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        FrameLayout frameLayout;
        SimpleDraweeView iv_user,iv;
        JCVideoPlayerStandard video;
//        ImageView iv_open;
        TextView tv_title,tv_description,tv_user_name,tv_time,tv_count;
//        ProgressBar progressBar;
        public MyViewHolder(View itemView) {
            super(itemView);
//            progressBar= (ProgressBar) itemView.findViewById(R.id.progressBar);
            iv= (SimpleDraweeView) itemView.findViewById(R.id.iv_video);
            video= (JCVideoPlayerStandard) itemView.findViewById(R.id.video);
            iv_user= (SimpleDraweeView) itemView.findViewById(R.id.iv_user);
            tv_user_name= (TextView) itemView.findViewById(R.id.tv_user_name);
            tv_time= (TextView) itemView.findViewById(R.id.tv_time);
            tv_count= (TextView) itemView.findViewById(R.id.tv_count);
            tv_title= (TextView) itemView.findViewById(R.id.tv_title);
            tv_description= (TextView) itemView.findViewById(R.id.tv_description);
            frameLayout= (FrameLayout) itemView.findViewById(R.id.framLayout);
//            iv_open= (ImageView) itemView.findViewById(R.id.iv_open);
            video.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (lisener != null) {
                        lisener.onImageClick(v, getLayoutPosition());
//                        holder.iv.setUp(imagUrl, "ss");
//                        iv.setImageBitmap(null);

                    }
                }
            });
        }
    }
}
