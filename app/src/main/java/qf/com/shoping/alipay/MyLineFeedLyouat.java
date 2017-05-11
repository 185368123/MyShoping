package qf.com.shoping.alipay;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class MyLineFeedLyouat extends RadioGroup {
    List<List<View>> views = new ArrayList<List<View>>();
    List<Integer> lineHeights = new ArrayList<Integer>();

    public MyLineFeedLyouat(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public MyLineFeedLyouat(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub

    }

    //测量方法，在绘制控件之前，一定会先回调这个方法来确定当前控件空间的大小
    //参数就是携带宽高信息的整形数
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //测量父布局的宽高
        //测算该布局容器的宽高
        //首先测算出父容器为其制定的宽高
        int childCount = this.getChildCount();//子控件数量
        for (int i = 0; i < childCount; i++) {
            View child = this.getChildAt(i);//获取每一个子控件
            //先测量子空间(调用一次，以后都可以不用)
            measureChild(child, widthMeasureSpec, widthMeasureSpec);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // 按规则放在父布局中（布局规则）
        //开始布局
        views.clear();
        lineHeights.clear();
        //获取自己的宽度
        int width = this.getWidth();
        //每一行累加宽度
        int linewidth = 0;
        //每一行的高度，取最高的高度
        int lineHeight = 0;
        int childCount = this.getChildCount();//子控件数量
        //每一行的子控件集合
        List<View> lineViews = new ArrayList<View>();
        //遍历每个子控件，累加它的宽度，如果大于布局的宽度，就换行
        for (int i = 0; i < childCount; i++) {
            //开始遍历子空间
            View child = this.getChildAt(i);
            //获取子控件的布局属性
            MarginLayoutParams mlp = (MarginLayoutParams) child.getLayoutParams();
            //难点记住，必须是显示在界面才能用getwidth()，getHeight() 现在还没有显示
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            if ((childWidth + mlp.leftMargin + mlp.rightMargin + linewidth) > width) {
                //当当前子控件宽度加上累加的宽度大于布局宽度时，换行，
                //把当前高度加到集合中
                lineHeights.add(lineHeight);
                //把当前子控件集合加入到集合中
                views.add(lineViews);
                //又是新的一行，重置
                linewidth = 0;
                lineHeight = 0;
                lineViews = new ArrayList<View>();
            }
            //没有换行，获取当前行最高的作为该行的高度
            lineHeight = Math.max(lineHeight, (childHeight + mlp.topMargin + mlp.bottomMargin));
            //没有换行，累加宽度
            linewidth += childWidth + mlp.leftMargin + mlp.rightMargin;
            //没有换行，把子控件加入到该行的集合中
            lineViews.add(child);
        }
        //最后一行
        lineHeights.add(lineHeight);
        views.add(lineViews);
        //开始布局
        int left = 0;//左距离累加值
        int top = 0;//高度累加值
        int lines = views.size();//总行数
        for (int i = 0; i < lines; i++) {
            //一行一行遍历
            lineViews = views.get(i);//每一行子控件集合
            lineHeight = lineHeights.get(i);//每行的高度
            for (int j = 0; j < lineViews.size(); j++) {
                //得到该行的每一个子控件
                View child = lineViews.get(j);
                //获取它的布局属性
                MarginLayoutParams mlp = (MarginLayoutParams) child.getLayoutParams();
                //设置左、上、右、下距离
                int lc = left + mlp.leftMargin;
                int tc = top + mlp.topMargin;
                int rc = lc + child.getMeasuredWidth();
                int bc = tc + child.getMeasuredHeight();
                //开始布局
                child.layout(lc, tc, rc, bc);
                //一行中，左距离累加
                left += child.getMeasuredWidth() + mlp.rightMargin + mlp.leftMargin;
            }
            //换行，左距离清零
            left = 0;
            //换行，高度累加
            top += lineHeight;
        }
    }


}