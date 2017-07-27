package com.lyan.tools.utils;

import android.graphics.Paint;

/**
 * 绘制工具类
 * Created by liyanjun on 2017/7/27.
 */

public class DrawUtils {
    /**
     * 获取绘制文字的基线
     * @param bottom
     * @param top
     * @param mPaint
     * @return
     */
    public static int getBaseLine(int bottom, int top , Paint mPaint) {
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        return (int) ((bottom + top - fontMetrics.bottom - fontMetrics.top) / 2);
    }
}
