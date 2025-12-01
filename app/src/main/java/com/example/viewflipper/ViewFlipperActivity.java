package com.example.viewflipper;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ViewFlipperActivity extends AppCompatActivity {

    ViewFlipper viewFlipper;

    //Khởi tạo activity
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Slide Images with ViewFlipper");
        //Liên kết với file giao diaạn vào Activity này
        setContentView(R.layout.activity_main);
        viewFlipper = findViewById(R.id.viewFlipperMain);
        ActionViewFlipperMain();
    }

    private void ActionViewFlipperMain() {
        List<String> arraylistFlipper = new ArrayList<>();
        arraylistFlipper.add("https://img.tripi.vn/cdn-cgi/image/width=700,height=700/https://gcs.tripi.vn/public-tripi/tripi-feed/img/482659YWl/anh-mo-ta.png");
        arraylistFlipper.add("https://images.pexels.com/photos/312418/pexels-photo-312418.jpeg?cs=srgb&dl=pexels-chevanon-312418.jpg&fm=jpg");
        arraylistFlipper.add("https://thepizzacompany.vn/images/thumbs/000/0002224_hawaii_500.png");
        arraylistFlipper.add("https://thepizzacompany.vn/images/uploaded/50PizzaT2_WebBanner_(W1200xH480)px.jpg");

        for (int i=0; i<arraylistFlipper.size(); i++){
            //Tạo mới một ImageView bằng code (thay vì vẽ trong XML)
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(arraylistFlipper.get(i)).into(imageView);
            //Chỉnh cách hiển thị ảnh: FIT_XY nghĩa là kéo dãn ảnh cho vừa khít khung hình
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            //Thêm ImageView này vào trong ViewFlipper
            viewFlipper.addView(imageView);
        }

        viewFlipper.setFlipInterval(3000); //thời gian chờ giữa các lần chuyển ảnh
        viewFlipper.setAutoStart(true); //tự động chạy ngay lập tức mà không cần người dùng thao tác

        //Thiết lập animoation cho Flipper
        Animation slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(slide_in);
        viewFlipper.setOutAnimation(slide_out);
    }
}
