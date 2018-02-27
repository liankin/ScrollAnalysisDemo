package com.admin.scrollanalysisdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.admin.scrollanalysisdemo.draglayout.MainActivity;
import com.nineoldandroids.view.ViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 滑动分析
 * 实现滑动的七种方法
 */
public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.id_content_tv)
    TextView textView;
    @BindView(R.id.drag_view_group)
    DragViewGroup dragViewGroup;
    @BindView(R.id.topbar_left_button)
    ImageView topbarLeftButton;
    @BindView(R.id.topbar)
    RelativeLayout topbar;
    @BindView(R.id.layout_menu)
    RelativeLayout layoutMenu;
    @BindView(R.id.layout_main_content)
    LinearLayout layoutMainContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        layoutMainContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dragViewGroup.getStatus() == 1){
                    dragViewGroup.setStatus(0);
                }
            }
        });

//        /*layoutContent.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                switch (motionEvent.getAction()){
//                    case MotionEvent.ACTION_DOWN:
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        if(dragViewGroup.getStatus() != DragViewGroup.Status.Close){
//                            dragViewGroup.close();
//                        }
//                        break;
//                }
//                return true;
//            }
//        });*/
    }

    @OnClick({R.id.layout_menu, R.id.id_content_tv, R.id.topbar_left_button})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.layout_menu:
                break;
            case R.id.id_content_tv:
                intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.topbar_left_button:
                dragViewGroup.open();
                break;
        }
    }
}
