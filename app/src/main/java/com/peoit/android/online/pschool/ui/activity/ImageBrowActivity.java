package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.easemob.chatuidemo.widget.photoview.PhotoViewAttacher;
import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.utils.BitmapUtils;


public class ImageBrowActivity extends AppCompatActivity {

    private ImageView ivImg;
    private PhotoViewAttacher mAttacher;

    public static void startThisActivity1(Activity mAc, String imgPath, boolean isUrl) {
        Intent intent = new Intent(mAc, ImageBrowActivity.class);
        mAc.startActivity(intent.putExtra("img", imgPath).putExtra("isUrl", isUrl));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_image_brow);
        // 经测试在代码里直接声明透明状态栏更有效
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        String imgPath = getIntent().getStringExtra("img");
        boolean isUrl = getIntent().getBooleanExtra("isUrl", false);
        if (TextUtils.isEmpty(imgPath)) {
            Toast.makeText(this, "数据传输错误", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        ivImg = (ImageView) findViewById(R.id.iv_img);

        if (isUrl) {
            Glide.with(this).load(imgPath).into(ivImg);
            ivImg.setScaleType(ImageView.ScaleType.FIT_CENTER);
//            ivImg.setImageDrawable(getResources().getDrawable(R.drawable.user_avater));
//            ImageRequest request = new ImageRequest(imgPath, new Response.Listener<Bitmap>() {
//                @Override
//                public void onResponse(Bitmap response) {
//                    ivImg.setImageBitmap(response);
//                }
//            }, CommonUtil.w_screeen * , CommonUtil.h_screeen, null, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//
//                }
//            });
//            Volley.newRequestQueue(this).add(request);
        } else {
            ivImg.setImageBitmap(BitmapUtils.compressBitmap(imgPath));
        }
        mAttacher = new PhotoViewAttacher(ivImg);
    }

}
