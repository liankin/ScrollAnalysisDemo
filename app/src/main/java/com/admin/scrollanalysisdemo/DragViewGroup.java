package com.admin.scrollanalysisdemo;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * 通过ViewDragHelper实现QQ侧滑功能
 */

public class DragViewGroup extends FrameLayout{

    private ViewDragHelper mViewDragHelper;
    private View mMenuView,mMainView;
    private int mWidth;
    private int mDragRange;
    private int mMainLeft;

    public DragViewGroup(@NonNull Context context) {
        super(context);
        initView();
    }

    public DragViewGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public DragViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mMenuView = getChildAt(0);
        mMainView = getChildAt(1);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = mMenuView.getMeasuredWidth();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 将触摸事件传递给ViewDragHelper，此操作必不可少
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    private void initView(){
        mViewDragHelper = ViewDragHelper.create(this,callback);
    }

    private ViewDragHelper.Callback callback =
            new ViewDragHelper.Callback() {
                // 何时开始检测触摸事件
                // 决定child是否可被拖拽，返回true则进行拖拽
                @Override
                public boolean tryCaptureView(View child, int pointerId) {
                    // 如果当前触摸的child是mMainView时开始检测
                    return mMainView == child;
                }

                // 处理垂直滑动
                @Override
                public int clampViewPositionVertical(View child, int top, int dy) {
                    return 0;
                }

                // 处理水平滑动
                // 此处设置view的拖拽范围（实际移动还未发生）
                @Override
                public int clampViewPositionHorizontal(View child, int left, int dx) {
                    return left;
                }

                // 拖动结束后调用
                @Override
                public void onViewReleased(View releasedChild, float xvel, float yvel) {
                    super.onViewReleased(releasedChild, xvel, yvel);
                    // 手指抬起后缓慢移动到指定位置
                    if(mMainView.getLeft() < 500){
                        // 关闭菜单
                        // 相当于Scroll的startScroll方法
                        mViewDragHelper.smoothSlideViewTo(mMainView,0,0);
                        ViewCompat.postInvalidateOnAnimation(DragViewGroup.this);
                    }else {
                        // 打开菜单
                        mViewDragHelper.smoothSlideViewTo(mMainView,mWidth,0);
                        ViewCompat.postInvalidateOnAnimation(DragViewGroup.this);
                    }
                }

                // 在位置改变时回调，常用于滑动时更改scale进行缩放等效果
                // 高频实时的调用，在这里设置左右面板的联动
                @Override
                public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                    super.onViewPositionChanged(changedView, left, top, dx, dy);
                }

                // 在用户触摸到view后回调
                // 当captureChild被拖拽时
                @Override
                public void onViewCaptured(View capturedChild, int activePointerId) {
                    super.onViewCaptured(capturedChild, activePointerId);
                }

                //在拖拽状态改变时回调，比如idle,dragging等状态
                @Override
                public void onViewDragStateChanged(int state) {
                    super.onViewDragStateChanged(state);
                }

                // 横向拖拽的范围，大于0时可拖拽，等于0无法拖拽
                // 此方法用于计算如view释放速度，敏感度等
                // 实际拖拽范围由clampViewPositionHorizontal方法设置
                @Override
                public int getViewHorizontalDragRange(View child) {
                    return super.getViewHorizontalDragRange(child);
                }
            };

    @Override
    public void computeScroll() {
        if(mViewDragHelper.continueSettling(true)){
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }
}
