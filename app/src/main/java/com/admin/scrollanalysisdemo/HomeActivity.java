package com.admin.scrollanalysisdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.admin.scrollanalysisdemo.draglayout.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 实现滑动的七种方法
 */
public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.id_content_tv)
    TextView textView;
    @BindView(R.id.id_container_menu)
    RelativeLayout idContainerMenu;

    private int lastX;
    private int lastY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return false;
                // 如果return true,那么表示该方法消费了此次事件;
                // 如果return false，那么表示该方法并未处理完全，该事件仍然需要以某种方式传递下去继续等待处理;
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 获取当前输入点的X、Y坐标（视图坐标）
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 处理输入的按下事件
                lastX = (int) event.getX();
                lastY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                //处理输入的移动事件
                break;
            case MotionEvent.ACTION_UP:
                // 处理输入的离开事件
                break;
        }
        return true;
        //return super.onTouchEvent(event);
    }

    @OnClick({R.id.id_container_menu, R.id.id_content_tv})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.id_container_menu:
                break;
            case R.id.id_content_tv:
                intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
