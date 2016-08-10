package com.rxl.marqueeview;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

/**
 * RenXL
 * 2016/7/5 0005
 */
public class MarqueeView extends ViewFlipper {

    private Context context;
    private int mvAnimDuration, mvInterval;
    private int mvTextSize;
    private int mvTextColor;
    private boolean singleLine;
    private List<String> notices;

    public MarqueeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initParams(context, attrs);
    }


    private void initParams(Context context, AttributeSet attrs) {
        this.context = context;
        if (notices == null)
            notices = new ArrayList<>();
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MarequeeView);

        mvAnimDuration = ta.getInt(R.styleable.MarequeeView_mvAnimDuration, 500);
        mvInterval = ta.getInt(R.styleable.MarequeeView_mvInterval, 2000);
        mvTextColor = ta.getColor(R.styleable.MarequeeView_mvTextColor, 0xffffff);
        singleLine = ta.getBoolean(R.styleable.MarequeeView_mvSingleLine, false);

        if (ta.hasValue(R.styleable.MarequeeView_mvTextSize))
            mvTextSize = px2sp(context, ta.getDimension(R.styleable.MarequeeView_mvTextSize, 14));

        Animation in = AnimationUtils.loadAnimation(context, R.anim.anim_marquee_in);
        Animation out = AnimationUtils.loadAnimation(context, R.anim.anim_marquee_out);

        in.setDuration(mvAnimDuration);
        out.setDuration(mvAnimDuration);

        setInAnimation(in);
        setOutAnimation(out);

    }

    public void startWithText(final String notice) {
        if (TextUtils.isEmpty(notice)) return;
        //当在一个视图树中全局布局发生改变或者视图树中的某个视图的可视状态发生改变时，所要调用的回调函数的接口类
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
                startWithFixedWidth(notice, getWidth());
            }
        });

    }

    public void startWithList(List<String> notices){
        setNotices(notices);
        start();
    }

    /**
     * 根据宽度和公告字符串启动轮播
     *
     * @param notice
     * @param width
     */
    private void startWithFixedWidth(String notice, int width) {
        // 1 获取字符串长度
        // 2 将控件宽度转化为dp
        // 3 首先判断控件宽度是不是0，如果是0直接抛出异常
        // 4 控件宽度不是0，判断控件一行可以放的字符串数量和此条字符串长度
        // 5 如果控件宽度大于字符串长度则该字符串需要占一条公告
        // 6 如果控件宽度小于字符串长度，首先判断字符串需要占据几行
        // 7 将字符串按照占据行数分割并添加到公告集合
        // 分割字符串时需要计算分一次分割的起始位置和终止位置
        // 如果是最有一条，需要判断最后一条的终止位置是不是末尾位置

        int noticeLength = notice.length();
        int dpW = px2dip(context, width);
        if (dpW == 0) throw new RuntimeException("Please Set Marqueen Width");
        int limit = dpW / mvTextSize;
        if (limit >= noticeLength) {
            notices.add(notice);
        } else {
            int size = (noticeLength / limit) + (noticeLength % limit == 0 ? 0 : 1);
            for (int i = 0; i < size; i++) {
                int startIndex = i * limit;
                int endIndex = ((i + 1) * limit >= noticeLength ? noticeLength : (i + 1) * limit);
                notices.add(notice.substring(startIndex, endIndex));
            }
        }
        start();
    }

    /**
     * 启动轮播
     */
    private void start() {
        // 1 如果notices为null或者为空，则直接返回
        // 2 使用for循环遍历notices集合，添加TextView进MarqueeView
        // 3 开启轮播

        if (notices == null || notices.size() == 0) return;
        removeAllViews();
        for (int i = 0; i < notices.size(); i++) {
            TextView tv = createTextView(notices.get(i), i);
            addView(tv);
        }
        if (notices.size() > 1) {
            startFlipping();
        }
    }

    private TextView createTextView(String text, int position) {
        TextView tv = new TextView(context);
        tv.setTextSize(mvTextSize);
        tv.setTextColor(mvTextColor);
        tv.setGravity(Gravity.CENTER_VERTICAL);
        tv.setSingleLine(singleLine);
        tv.setText(text);
        tv.setTag(position);
        return tv;
    }


    /**
     * 将px值转换为sp值，保证文字大小不变
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public int getPosition() {
        return (int) getCurrentView().getTag();
    }

    public void setNotices(List<String> notices) {
        this.notices = notices;
    }

    public List getNotices() {
        return notices;
    }


}
