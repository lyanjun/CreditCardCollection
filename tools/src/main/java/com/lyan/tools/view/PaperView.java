package com.lyan.tools.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.lyan.tools.R;
import com.lyan.tools.utils.DensityUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * 信纸
 * Created by liyanjun on 2017/7/27.
 */

public class PaperView extends CustomView {
    private String showText;//显示的文本
    private int showTextColor;//文字的字体颜色
    private int showTextSize;//文字的字体大小
    private int showTextRows;//文字的列数
    private int showTextLines;//文字的行数
    private int singleTextSize;//单个文字的绘制区域边长
    private List<Rect> rects;//绘制区域
    private float translate;//平移

    public PaperView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 初始化
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    @Override
    protected void initialize(Context context, AttributeSet attrs, int defStyleAttr) {
        getAttribute(attrs);//获取属性
        if (null != showText) {
            showTextLines = showText.length() / showTextRows;//整行
            showTextLines = showText.length() % showTextRows == 0 ? showTextLines : showTextLines + 1;
        } else {
            showTextLines = 1;//一行
        }
        Logger.t("显示几行文字").i(showTextLines + "行");

    }

    /**
     * 获取属性
     *
     * @param attrs
     */
    private void getAttribute(AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PaperView);
        showText = typedArray.getString(R.styleable.PaperView_pt_show_text);
        showTextColor = typedArray.getColor(R.styleable.PaperView_pt_show_text_color, Color.BLACK);
        showTextSize = (int) typedArray.getDimension(R.styleable.PaperView_pt_show_text_size, DensityUtils.sp2px(context, 14));
        showTextRows = typedArray.getInt(R.styleable.PaperView_pt_show_text_rows, 10);
        typedArray.recycle();//释放资源
    }

    /**
     * 设置画笔
     */
    @Override
    protected void setDrawPaint() {
        paint.setColor(showTextColor);
        paint.setTextSize(showTextSize);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
//        paint.setStrokeWidth(DensityUtils.dp2px(context,1));
        paint.setDither(true);
    }

    /**
     * 绘制
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(translate, 0);
        //绘制显示的文字
        if (null != showText) {
            Rect rect;
            int drawTextX;
            int drawTextY;
            for (int i = 0; i < showText.length(); i++) {
                rect = rects.get(i);
                //绘制边框
//                paint.setColor(Color.BLACK);
//                canvas.drawRect(rect, paint);
                drawTextX = rect.right - singleTextSize / 2;
                drawTextY = baseLine(rect.bottom,rect.top);
                //绘制文字
                paint.setColor(showTextColor);
                canvas.drawText(String.valueOf(showText.charAt(i)),drawTextX,drawTextY,paint);
            }
        }
    }

    /**
     * 测量
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);//控件宽度的测量模式
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);//控件宽度的测量值
        widthSize = size(widthMode, widthSize, DensityUtils.dp2px(context, defaultWidth));
//        Logger.t("测量").i(widthSize + "*********************" + (widthSize / showTextRows * showTextLines) + "");
//        Logger.t("测量").w(showTextLines + "");
        setMeasuredDimension(widthSize, widthSize / showTextRows * showTextLines);
    }


    /**
     * 确定宽高
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (null != showText) {
            singleTextSize = width / showTextRows;
            translate = (width - singleTextSize * showTextRows) / 2;
            rects = new ArrayList<>();
            Rect rect;
            for (int l = 0; l < showTextLines; l++) {
                //绘制每一行
                for (int r = 0; r < showTextRows; r++) {
                    //绘制每一列
                    rect = new Rect(r * singleTextSize, l * singleTextSize, (r + 1) * singleTextSize, (l + 1) * singleTextSize);
                    rects.add(rect);
                }
            }
        }
    }
}
