package com.witcher.testviewpager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/*
用clipPadding 然后给viewpager一个padding来实现的两边侧漏效果  缺点是transformPage里面
当前的页面处于居中状态时 position不为0 会有偏移量
 */
public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    ArrayList<View> viewArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.vp);
        viewArrayList.add(new PagerView2(this, R.drawable.img11));
        viewArrayList.add(new PagerView2(this, R.drawable.img2));
        viewArrayList.add(new PagerView2(this, R.drawable.img3));
        viewArrayList.add(new PagerView2(this, R.drawable.img4));
        viewArrayList.add(new PagerView2(this, R.drawable.img5));
        viewArrayList.add(new PagerView2(this, R.drawable.img7));
        viewArrayList.add(new PagerView2(this, R.drawable.img8));
        viewArrayList.add(new PagerView2(this, R.drawable.img12));

        viewPager.setAdapter(new MyPagerAdapter());

        viewPager.setOffscreenPageLimit(2);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
//        viewPager.setPageMargin(50);
    }

    public static class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static float MIN_SCALE = 0.85f;
        private static float MIN_SCALE2 = 0.35f;
        private ViewPager mViewPager;

        @Override
        public void transformPage(View view, float position) {
//            //左侧 -1 当前0 右侧 1
            PagerView2 pagerView = (PagerView2) view;


            if (mViewPager == null) {
                mViewPager = (ViewPager) view.getParent();
            }
            //left是138 表示是中心view 大于138是右侧 小于138是左侧
            int leftInScreen = view.getLeft() - mViewPager.getScrollX();//view离屏幕左侧的位置
            int centerXInViewPager = leftInScreen + view.getMeasuredWidth() / 2;//view在屏幕上的中心点 1344
            int offsetX = centerXInViewPager - mViewPager.getMeasuredWidth() / 2;//804 view的中心点和屏幕中心点的距离
            float absPosition =  Math.abs(offsetX)/(float)view.getMeasuredWidth();
            float scale = 1 - (absPosition * (1 - MIN_SCALE));
            L.i("flag:" + pagerView.flag() + "  scale:"+scale+"  offsetX:"+offsetX);
            /*
            当 offsetX 等于view宽度时候 是缩放最大值
            当 offsetX 等于0的时候      是缩放最小值 不缩放
            offsetX 区间 -view.width  -  0  -  view.width
            取offsetX 的绝对值 除以view.width 计算缩放比
            abs
             */
//            if (scaleFactor > 0) {
                view.setScaleX(scale);
                view.setScaleY(scale);
//            page.setTranslationX(-mMaxTranslateOffsetX * offsetRate);
//            }
        }
    }

    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return viewArrayList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View view = viewArrayList.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(viewArrayList.get(position));
        }
    }

}
