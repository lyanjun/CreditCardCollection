package com.lyan.tools.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

import com.lyan.tools.R;
import com.lyan.tools.utils.DensityUtils;

/**
 * 方框输入框
 * Created by liyanjun on 2017/7/21.
 */

public class InputBox extends FrameLayout {
    private Context mContext;
    private int mDefaultHeight;//默认高
    private int mDefaultWidth;//默认宽
    //默认属性值
    private static final int DEFAULT_TEXT_COLOR = 0xFFF7F6F6;//默认字体颜色
    private static final int DEFAULT_BOX_COLOR = 0xFFF7F6F6;//默认方框颜色
    private static final int DEFAULT_INPUT_COUNT = 4;//默认输入的个数
    private static final int DEFAULT_TEXT_SIZE = 14;//默认字体的大小
    private static final int DEFAULT_LINE_SIZE = 20;//默认的边长
    private static final int DEFAULT_LINE_WIDTH = 1;//默认的线宽
    //设置属性
    private int mInputCount;//输入的个数
    //其他常量
    private Paint mPaint;//画笔

    /**
     * 构造器
     * @param context
     */

    public InputBox(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);//初始化
    }
    /**
     * 初始化
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs) {
        this.mContext = context;//获取上下文
        obtainInputBoxAttribute(attrs);//获取
        setWillNotDraw(false);//让ViewGroup可绘制
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);//抗锯齿
        mDefaultHeight = DensityUtils.dp2px(mContext,DEFAULT_LINE_SIZE);
        mDefaultWidth = mDefaultHeight * mInputCount;
    }

    /**
     * 获取自定义属性
     */
    private void obtainInputBoxAttribute(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.InputBox);//获取属性
        mInputCount = typedArray.getInteger(R.styleable.InputBox_ib_input_count,DEFAULT_INPUT_COUNT);//输入框个数
        typedArray.recycle();//释放资源
    }

    /**
     * 绘制
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.RED);
    }

    /**
     * 测量
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 获取测量结果
     * @param mode
     * @param size
     * @param setValues
     * @return
     */
    private int size(int mode,int size ,int setValues){
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
     * 确定控件大小
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }
}
