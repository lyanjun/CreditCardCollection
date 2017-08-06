package com.lyan.tools.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.lyan.tools.R;
import com.lyan.tools.utils.DensityUtils;

/**
 * Created by liyanjun on 2017/8/6.
 */

public class ImageTextHintView extends CustomView {
    /**
     * 属性
     */
    private String hintTextMessage;//提示文本内容
    private int hintTextColor;//提示文字的颜色
    private int hintTextSize;//提示文字的字体大小
    private Bitmap hintImage;//提示图片
    private int hintInterval;//间隔
    private Rect drawImageRect;//绘制图片的区域
    private Rect drawTextRect;//绘制文字的区域

    /**
     * 构造器
     *
     * @param context
     */
    public ImageTextHintView(Context context) {
        super(context);
    }

    public ImageTextHintView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageTextHintView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
    }

    /**
     * 初始化设置之后
     */
    @Override
    public void afterInitialize() {
        if (hintTextMessage == null || hintImage == null) return;
        int imageWidth = (int) DensityUtils.px2dp(context, hintImage.getWidth());//图片的宽度
        int textWidth = (int) DensityUtils.px2dp(context, paint.measureText(hintTextMessage));//文字的宽度
        defaultWidth = imageWidth > textWidth ? imageWidth : textWidth;//设置宽度
        int imageHeight = (int) DensityUtils.px2dp(context, hintImage.getHeight());//图片的高度
        int textHeight = (int) (-paint.ascent() + paint.descent());//文字高度
        defaultHeight = (int) (imageHeight + hintInterval + DensityUtils.px2dp(context, textHeight));
        //设置绘制图片的区域
        int imageOffset = (defaultWidth - hintImage.getWidth()) / 2;//绘制图片的偏移量
        drawImageRect = new Rect(imageOffset, 0, defaultWidth - imageOffset, hintImage.getHeight());
        //设置绘制文字的区域
        int textOffset = (defaultWidth - textWidth) / 2;//设置绘制文字的偏移量
        drawTextRect = new Rect(textOffset, defaultHeight - textHeight, defaultWidth - textOffset, defaultHeight);
    }

    /**
     * 获取属性
     *
     * @param attrs
     */
    private void getAttribute(AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ImageTextHintView);
        hintTextSize = (int) typedArray.getDimension(R.styleable.ImageTextHintView_it_hint_text_size, DensityUtils.sp2px(context, 14));
        hintTextMessage = typedArray.getString(R.styleable.ImageTextHintView_it_hint_text_message);//获取提示文本内容
        hintTextColor = typedArray.getColor(R.styleable.ImageTextHintView_it_hint_text_color, Color.BLACK);
        hintImage = drawableToBitmap(typedArray.getDrawable(R.styleable.ImageTextHintView_it_hint_image_src));
        hintInterval = (int) typedArray.getDimension(R.styleable.ImageTextHintView_it_hint_interval, DensityUtils.dp2px(context, 0));
        typedArray.recycle();//释放资源
    }

    /**
     * 设置画笔
     */
    @Override
    protected void setDrawPaint() {
        paint.setColor(hintTextColor);
        paint.setTextSize(hintTextSize);
        paint.setStyle(Paint.Style.FILL);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
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
        canvas.drawColor(Color.BLUE);
        //绘制图片
        if (null != hintImage) {
            canvas.drawBitmap(hintImage, drawTextRect, drawTextRect, null);
        }
        //绘制文字
        if (null != hintTextMessage) {
            canvas.drawText(hintTextMessage, defaultWidth / 2,
                    baseLine(defaultHeight, defaultHeight - (drawTextRect.bottom - drawTextRect.top)), paint);
        }
    }

    /**
     * 获取Bitmap
     *
     * @param drawable
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                        : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}
