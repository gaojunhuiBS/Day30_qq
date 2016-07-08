package com.gaojunhui.day6_28qq;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.image.QualityInfo;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2016/6/30.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //更改网络请求方式
        OkHttpClient client=new OkHttpClient();
        //渐进式加载
        ProgressiveJpegConfig jpegConfig= new ProgressiveJpegConfig() {

            @Override
            public int getNextScanNumberToDecode(int scanNumber) {
                return scanNumber+2 ;
            }

            @Override
            public QualityInfo getQualityInfo(int scanNumber) {
                boolean isGoodEnough = (scanNumber >= 5);
                return ImmutableQualityInfo.of(scanNumber, isGoodEnough, false);
            }
        };
        ImagePipelineConfig config= OkHttpImagePipelineConfigFactory.newBuilder(this,client)
                .setProgressiveJpegConfig(jpegConfig)
                .build();
        Fresco.initialize(this,config);
    }
}
