package com.witcher.testviewpager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {


    MyViewPager viewPager;
    ArrayList<View> viewArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        viewPager = findViewById(R.id.vp);
        RelativeLayout historyView = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.history_view,null,false);
        viewArrayList.add(historyView);
        viewArrayList.add(new PagerView(this, R.drawable.img11));
        viewArrayList.add(new PagerView(this, R.drawable.img2));
        viewArrayList.add(new PagerView(this, R.drawable.img3));
        viewArrayList.add(new PagerView(this, R.drawable.img4));
        viewArrayList.add(new PagerView(this, R.drawable.img5));
        viewArrayList.add(new PagerView(this, R.drawable.img7));
        viewArrayList.add(new PagerView(this, R.drawable.img8));
        viewArrayList.add(new PagerView(this, R.drawable.img12));


        viewPager.setOffscreenPageLimit(5);
        viewPager.setAdapter(new MyPagerAdapter1());
        viewPager.setCurrentItem(1);
    }
    class MyPagerAdapter1 extends MyPagerAdapter {

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
