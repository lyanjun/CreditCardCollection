package com.lyan.tools.view;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.lyan.tools.utils.DensityUtils;
import com.lyan.tools.utils.DrawUtils;


/**
 * 作者： LYJ
 * 功能： 自定义View的基类
 * 创建日期： 2017/3/22
 */

public abstract class CustomView extends View {
    private static final int DEFAULT_WIDTH = 200;//默认宽度
    private static final int DEFAULT_HEIGHT = 100;//默认高度
    protected Paint paint;//画笔
    protected int width,height;//控件宽度和高度
    protected Context context;//上下文
    protected int defaultWidth = DEFAULT_WIDTH;//默认宽
    protected int defaultHeight = DEFAULT_HEIGHT;//默认高
    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initialize(context, attrs, defStyleAttr);//初始化方法
        paint = new Paint();//初始化画笔
        setDrawPaint();//设置画笔
    }

    /**
     * 初始化方法
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    protected abstract void initialize(Context context, AttributeSet attrs, int defStyleAttr);

    /**
     * 设置画笔
     */
    protected abstract void setDrawPaint();

    /**
     * 测量控件的大小
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);//控件宽度的测量模式
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);//控件宽度的测量值
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);//控件高度的测量模式
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);//控件高度的测量值
        setMeasuredDimension(size(widthMode,widthSize, DensityUtils.dp2px(context,defaultWidth))
                ,size(heightMode,heightSize, DensityUtils.dp2px(context,defaultHeight)));
    }

    /**
     * 获取文字绘制的基线
     * @param bottom
     * @param top
     * @return
     */
    protected final int baseLine(int bottom, int top){
//        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
//        return (int) ((bottom + top - fontMetrics.bottom - fontMetrics.top)/2);
        return DrawUtils.getBaseLine(bottom,top,paint);
    }
    /**
     * 获取测量结果
     * @param mode
     * @param size
     * @param setValues
     * @return
     */
    protected final int size(int mode,int size ,int setValues){
        int result = 0;
        switch (mode){
            case MeasureSpec.EXACTLY:
                result = size;
                break;
            case MeasureSpec.AT_MOST:
                result = Math.min(size,setValues);
                break;
        }
        return result;
    }
    /**
     * 确定控件的大小
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.width = w;
        this.height = h;
    }
}
