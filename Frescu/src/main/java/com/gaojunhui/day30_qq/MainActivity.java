package com.gaojunhui.day30_qq;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

public class MainActivity extends AppCompatActivity {
    private SimpleDraweeView draweeView;
//    private String url="http://img4.imgtn.bdimg.com/it/u=451539363,4246055291&fm=21&gp=0.jpg";
    private String url="http://diy.yh31.com/gif/247.gif";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        draweeView= (SimpleDraweeView) findViewById(R.id.image_simple);
        Uri uri=Uri.parse(url);
//        draweeView.setImageURI(uri);
        DraweeController controller= Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build();
        draweeView.setController(controller);
    }
}
