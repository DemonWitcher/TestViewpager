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
        viewPager.setPageMargin(50);
    }

    public static class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static float MIN_SCALE = 0.85f;

        @Override
        public void transformPage(View view, float position) {
            //左侧 -1 当前0 右侧 1
            PagerView2 pagerView = (PagerView2) view;
            //这里的偏移量是0.1716418  0.1716418这个值是正中间的
            float absPosition = Math.abs(position);
            float scale = 1 - (absPosition * (1 - MIN_SCALE));
            L.i(pagerView.flag() + "  position:" + position + "  scale:" + scale);
            pagerView.setScaleX(scale);
            pagerView.setScaleY(scale);
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
