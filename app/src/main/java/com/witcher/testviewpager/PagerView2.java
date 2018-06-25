package com.witcher.testviewpager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

/*
正常的 没调整过尺寸的
 */

public class PagerView2 extends FrameLayout{

    int res;
    ImageView iv;

    public PagerView2(@NonNull Context context, int res){
        super(context);
        this.res = res;
        init();
    }

    public PagerView2(@NonNull Context context) {
        super(context);
        init();
    }

    public PagerView2(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PagerView2(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.pager_view2,this,true);
        iv = findViewById(R.id.iv);
        iv.setImageResource(res);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                L.i("width:"+getWidth()+"   height:"+getHeight());
                L.i("MeasuredWidth:"+getMeasuredWidth()+"   MeasuredHeight:"+getMeasuredHeight());
            }
        });
    }
    public String flag(){
        switch (res){
            case R.drawable.img11:{return "img1";}
            case R.drawable.img2:{return "img2";}
            case R.drawable.img3:{return "img3";}
            case R.drawable.img4:{return "img4";}
            case R.drawable.img5:{return "img5";}
            case R.drawable.img7:{return "img6";}
            case R.drawable.img8:{return "img7";}
            case R.drawable.img12:{return "img8";}
        }
        return "";
    }

}
