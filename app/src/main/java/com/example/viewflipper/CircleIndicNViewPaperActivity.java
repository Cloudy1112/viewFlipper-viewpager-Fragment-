package com.example.viewflipper;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class CircleIndicNViewPaperActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private List<Images> imagesList;

    //Auto run
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (viewPager.getCurrentItem() == imagesList.size() -1){
                viewPager.setCurrentItem(0);
            } else {
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("CircleIndicator và ViewPager");
        setContentView(R.layout.circleindandviewpaper);
        viewPager = findViewById(R.id.viewpage);
        circleIndicator = findViewById(R.id.circle_indicator);
        imagesList = getListImages();
        ImagesViewPageAdapter adapter = new ImagesViewPageAdapter(imagesList);
        viewPager.setAdapter(adapter);

        //Liên kết viewpage và indicator
        circleIndicator.setViewPager(viewPager);

        //Gọi Runable
        handler.postDelayed(runnable, 3000);
        //Lắng nghe ViewPage chuyển động
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 3000);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private List<Images> getListImages() {
        List<Images> list = new ArrayList<>();
        list.add(new Images(R.drawable.ad));
        list.add(new Images(R.drawable.menu));
        list.add(new Images(R.drawable.store));
        list.add(new Images(R.drawable.cups));
        return list;
    }

    //Để tránh ứng dụng vẫn chạy ngầm gây tốn pin hoặc lỗi khi người dùng thoát ra ngoài
    @Override
    protected void onPause() {
        super.onPause();
        // Khi thoát ứng dụng hoặc ẩn xuống, xóa luồng chạy tự động
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Khi quay lại ứng dụng, kích hoạt lại luồng chạy sau 3 giây
        handler.postDelayed(runnable, 3000);
    }
}
