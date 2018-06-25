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
用clipChildren+给viewpager上margin来实现的两边侧漏效果
缺点是控制了viewpager的实际大小 viewpager外面的margin区域 无法滑动
 */
public class MainActivity2 extends AppCompatActivity {

    ViewPager viewPager;
    ArrayList<View> viewArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        viewPager = findViewById(R.id.vp);
        viewArrayList.add(new PagerView(this,R.drawable.img11));
        viewArrayList.add(new PagerView(this,R.drawable.img2));
        viewArrayList.add(new PagerView(this,R.drawable.img3));
        viewArrayList.add(new PagerView(this,R.drawable.img4));
        viewArrayList.add(new PagerView(this,R.drawable.img5));
        viewArrayList.add(new PagerView(this,R.drawable.img7));
        viewArrayList.add(new PagerView(this,R.drawable.img8));
        viewArrayList.add(new PagerView(this,R.drawable.img12));

        viewPager.setAdapter(new MyPagerAdapter());

        viewPager.setOffscreenPageLimit(2);
        viewPager.setPageTransformer(true,new ZoomOutPageTransformer());

        findViewById(R.id.left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = viewPager.getCurrentItem();
                if(current == 0){
                    return;
                }
                viewPager.setCurrentItem(current-1);
            }
        });
        findViewById(R.id.right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = viewPager.getCurrentItem();
                if(current == viewArrayList.size()){
                    return;
                }
                viewPager.setCurrentItem(current+1);
            }
        });
    }

    public static class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static float MIN_SCALE = 0.85f;

        private static float MIN_ALPHA = 0.5f;

        @Override
        public void transformPage(View view, float position) {
            //左侧 -1 当前0 右侧 1
            PagerView pagerView = (PagerView) view;
            L.i(pagerView.flag() + "  position:"+position);
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
//                view.setAlpha(0);
            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to
                // shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;

//                if (position < 0) {
//                    view.setTranslationX(horzMargin - vertMargin / 2);
//                } else {
//                    view.setTranslationX(-horzMargin + vertMargin / 2);
//                }
//                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
//                view.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE)
//                        / (1 - MIN_SCALE) * (1 - MIN_ALPHA));
            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
//                view.setAlpha(0);
            }
        }
    }

    class MyPagerAdapter extends PagerAdapter{

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
