package com.witcher.testviewpager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class PagerView extends FrameLayout{

    int res;
    ImageView iv;

    public PagerView(@NonNull Context context,int res){
        super(context);
        this.res = res;
        init();
    }

    public PagerView(@NonNull Context context) {
        super(context);
        init();
    }

    public PagerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PagerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.pager_view,this,true);
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

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        setMeasuredDimension(1080,MeasureSpec.getSize(heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
//        L.i("left :"+left+"  right:"+right+"  top:"+top+"   bottom:"+bottom);
    }
}
