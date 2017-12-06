package com.example.fenrir_cd.scrolltest;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.fenrir_cd.scrolltest.databinding.ActivityMainBinding;
import com.example.fenrir_cd.scrolltest.databinding.ViewpagerItemBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private List<String> data = new ArrayList<>();
    private View[] views = new View[2];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        ViewpagerItemBinding binding1 = ViewpagerItemBinding.inflate(LayoutInflater.from(this));
        ViewpagerItemBinding binding2 = ViewpagerItemBinding.inflate(LayoutInflater.from(this));

        views[0] = binding1.getRoot();
        views[1] = binding2.getRoot();
        binding.viewpager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(views[position]);
                return views[position];
            }


            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });

        for (int i = 0; i < 12; i++) {
            data.add(Integer.toString(i));
        }
        setData(binding1.recycler);
        setData(binding1.recycler1);
        setData(binding1.recycler2);
        setData(binding2.recycler);
        setData(binding2.recycler1);
        setData(binding2.recycler2);
    }

    private void setData(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(new RecyclerAdapter(data));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        // todo 如果不添加这个属性将导致behavior的滚动回到方法不执行
        recyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
