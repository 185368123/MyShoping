package qf.com.shoping.view;

import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


/**
 * Created by li on 2017/2/18.
 */

public class HalfLinearLayout extends LinearLayout {
    //可滑动的子控件
    View childView;
    //当前高度
    int currentH;
    //最高的高度
    int maxH = -1;
    //最低高度
    int minH;
    //手指按下时的起点坐标
    private PointF startPoint;
    private int childViewId;

    public HalfLinearLayout(Context context) {
        super(context);
    }

    public HalfLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (maxH == -1) {
            /*maxH = getResources().getDisplayMetrics().heightPixels;
            minH = maxH * 2/3;*/
            minH=getMeasuredHeight();
            maxH=minH*3/2;
            currentH = minH;

        }

    }

    /**
     * 设置可滑动的子控件
     *
     * @param viewid
     */
    public void setScrollView(int viewid) {
        childView = findViewById(viewid);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startPoint = new PointF(ev.getX(), ev.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                        //手指移动的高度
                        float length = ev.getY() - startPoint.y;
                        currentH = (int) (currentH - length);
                        if (currentH> maxH) {
                            setH(maxH);
                        } else if (currentH < minH) {
                            setH(minH);
                        } else {
                            setH(currentH);
                        }
                break;
        }

            return super.dispatchTouchEvent(ev);

    }

    //控件设置高度
    private void setH(int h) {
        currentH = h;
        ViewGroup.LayoutParams params = getLayoutParams();
        params.height = currentH;
        setLayoutParams(params);
    }

    //判断子控件是否滑动到顶
    public boolean canScrollViewUp(View view) {
        if (view == null) {
            return false;
        }
        //控件是否可以在垂直方向滚动
        //-1:表示向下滚动
        return view.canScrollVertically(1);
    }

}
