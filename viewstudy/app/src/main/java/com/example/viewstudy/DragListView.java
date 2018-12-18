package com.example.viewstudy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * Created by gejun on 18-12-18.
 */

public class DragListView extends ListView {
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mLayoutParams;
    private Context mContext;
    private int mDragPointX;
    private int mDragPointY;
    private int mDragOffsetX;
    private int mDragOffSetY;

    public DragListener l;
    public void setListener(DragListener listener){
        l = listener;
    }
    public interface DragListener {
        void changeItem(int origin, int dest);
    }

    public DragListView(Context context) {
        super(context);
        init(context);
    }

    public DragListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DragListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context){
        mContext = context;
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    }

    private int itemNum;
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                final int x = (int) ev.getX();
                final int y = (int) ev.getY();
                itemNum = pointToPosition(x, y);
                Log.i("gejun","item" + itemNum+",firstVisiblePosition = "+getFirstVisiblePosition());
                if(itemNum == AdapterView.INVALID_POSITION){
                    break;
                }
                final View item = (View) getChildAt(itemNum - getFirstVisiblePosition());
                Log.i("gejun","getLeft = "+item.getLeft()+", getTop = "+item.getTop());
                Log.i("gejun","x = "+x+", y = "+y);
                Log.i("gejun","rawx = "+ev.getRawX()+", rawy = "+ev.getRawY());
                mDragPointX = x - item.getLeft();
                mDragPointY = y - item.getTop();
                mDragOffsetX = ((int) ev.getRawX()) - x;
                mDragOffSetY = ((int) ev.getRawY()) - y;

                item.setOnLongClickListener(new OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        final int height = getHeight();
//                        mUpperBound = Math.min(y - mScaledTouchSlop, height / 3);
//                        mLowerBound = Math.max(y + mScaledTouchSlop, height * 2 / 3);
//                        mDragCurrentPostion = mDragStartPosition = itemNum;

                        item.setDrawingCacheEnabled(true);
                        Bitmap bitmap = Bitmap.createBitmap(item.getDrawingCache());
                        startDragging(bitmap, x, y);
                        return true;
                    }
                });
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    private void startDragging(Bitmap bitm, int x, int y){
//        stopDragging();

        mLayoutParams = new WindowManager.LayoutParams();
        mLayoutParams.gravity = Gravity.TOP | Gravity.LEFT;
        mLayoutParams.x = x - mDragPointX + mDragOffsetX;
        mLayoutParams.y = y - mDragPointY + mDragOffSetY;
        mLayoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
        mLayoutParams.format = PixelFormat.TRANSLUCENT;
        mLayoutParams.windowAnimations = 0;

        ImageView imageView = new ImageView(mContext);
        imageView.setImageBitmap(bitm);
        imageView.setBackgroundColor(Color.parseColor("#33ff0000"));
        imageView.setPadding(0, 0, 0, 0);
        mWindowManager.addView(imageView, mLayoutParams);
        mDragView = imageView;
    }


    private void stopDragging(){
        if(mDragView != null){
            mWindowManager.removeView(mDragView);
            mDragView.setImageDrawable(null);
            mDragView = null;
        }
    }


    private ImageView mDragView;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(mDragView != null){
            switch(ev.getAction()){
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_MOVE:
                    int x = (int) ev.getX();
                    int y = (int) ev.getY();
//                    mLayoutParams.alpha = mAlpha;
                    mLayoutParams.y = y - mDragPointY + mDragOffSetY;
                    mLayoutParams.x = x - mDragPointX + mDragOffsetX;
                    mWindowManager.updateViewLayout(mDragView, mLayoutParams);
                    break;
                case MotionEvent.ACTION_UP:
                    stopDragging();
                    int a = (int) ev.getX();
                    int b = (int) ev.getY();
                    int changeItem = pointToPosition(a,b);
                    l.changeItem(itemNum,changeItem);
                    break;
            }
            return true;
        }
        return super.onTouchEvent(ev);
    }
}

