package com.lyan.tools.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.lyan.tools.R;
import com.lyan.tools.utils.DensityUtils;
import com.lyan.tools.utils.ViewTextUtils;


/**
 * 方框输入框
 * Created by liyanjun on 2017/7/21.
 */

public final class InputBox extends FrameLayout implements TextWatcher ,View.OnClickListener{

    /**
     * 接口回调监听
     */
    public interface OnInputTextChangeListener {
        void onBoxTextChanged(String message);
    }
    public interface OnInputBoxClickListener{
        void onInputBoxClick(InputBox box);
    }

    //属性
    private Context mContext;
    private OnInputTextChangeListener textChangeListener;
    private OnInputBoxClickListener onInputBoxClickListener;
    //默认属性值
    private static final int DEFAULT_TEXT_COLOR = 0xFFAAAAAA;//默认字体颜色
    private static final int DEFAULT_BOX_COLOR = 0xFFAAAAAA;//默认方框颜色
    private static final int DEFAULT_INPUT_COUNT = 4;//默认输入的个数
    private static final int DEFAULT_TEXT_SIZE = 14;//默认字体的大小
    private static final int DEFAULT_LINE_SIZE = 20;//默认的边长
    private static final int DEFAULT_LINE_WIDTH = 1;//默认的线宽

    private static final int INPUT_TEXT = 0;
    private static final int INPUT_NUMBER = 1;
    private static final int INPUT_WORD = 2;
    //输入框
    private BoxEditText mInputView;//输入控件
    private String mInputText;//输入的内容
    //设置属性
    private int mInputCount;//输入的个数
    private int mInputLineSize;//边框长度
    private int mInputLineWidth;//边框线的宽度
    private int mInputBoxColor;//边框颜色
    private int mInputTextSize;//输入文字的大小
    private int mInputTextColor;//输入文字的颜色
    private int mInputTextType;//输入文本类型
    private boolean mInoutTextWord;//是否只能输入字母
    //其他常量
    private Paint mPaint;//画笔
    private RectF mBoxRect;//边框

    /**
     * 构造器
     *
     * @param context
     */

    public InputBox(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);//初始化
    }

    /**
     * 初始化
     *
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs) {
        this.mContext = context;//获取上下文
        obtainInputBoxAttribute(attrs);//获取属性
        setWillNotDraw(false);//让ViewGroup可绘制
        initDraw();//初始化绘制相关
        initInputView();//初始化输入框
    }

    private void initDraw() {
        //画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);//抗锯齿
        mPaint.setDither(true);//抗抖动
        mPaint.setTextAlign(Paint.Align.CENTER);//文字居中
        //边框
        mBoxRect = new RectF();
    }

    /**
     * 初始化输入控件
     */
    private void initInputView() {
        mInputView = new BoxEditText(mContext);//输入域
        mInputView.setLayoutParams(new ViewGroup.LayoutParams(mInputLineSize * mInputCount, mInputLineSize));
        mInputView.setBackgroundResource(0);//无背景
        mInputView.setCursorVisible(false);//无光标
        mInputView.setTextColor(Color.TRANSPARENT);//文字透明
        if (mInoutTextWord) {
            String digits = getResources().getString(R.string.input_word);
            mInputView.setKeyListener(DigitsKeyListener.getInstance(digits));
        }
        mInputView.setFilters(new InputFilter[]{new InputFilter.LengthFilter(mInputCount)});//限制可输入字符的长度
        mInputView.setInputType(mInputTextType);
        mInputView.addTextChangedListener(this);//输入框的监听
        addView(mInputView);//添加子View
    }

    /**
     * 获取自定义属性
     */
    private void obtainInputBoxAttribute(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.InputBox);//获取属性
        mInputCount = typedArray.getInteger(R.styleable.InputBox_ib_input_count, DEFAULT_INPUT_COUNT);//输入框个数
        mInputLineSize = (int) typedArray.getDimension(R.styleable.InputBox_ib_box_line_size, DensityUtils.dp2px(mContext, DEFAULT_LINE_SIZE));//输入边框长度
        mInputLineWidth = (int) typedArray.getDimension(R.styleable.InputBox_ib_box_line_width, DensityUtils.dp2px(mContext, DEFAULT_LINE_WIDTH));//线的宽度
        mInputTextSize = (int) typedArray.getDimension(R.styleable.InputBox_ib_input_text_size, DensityUtils.sp2px(mContext, DEFAULT_TEXT_SIZE));//线的宽度
        mInputBoxColor = typedArray.getColor(R.styleable.InputBox_ib_box_line_color, DEFAULT_BOX_COLOR);
        mInputTextColor = typedArray.getColor(R.styleable.InputBox_ib_input_text_color, DEFAULT_TEXT_COLOR);
        mInputTextType = typedArray.getInt(R.styleable.InputBox_ib_input_text_type, InputType.TYPE_TEXT_VARIATION_NORMAL);
//        mInputTextType = mInputTextType == INPUT_NUMBER ? InputType.TYPE_CLASS_NUMBER : InputType.TYPE_CLASS_TEXT;
        if (mInputTextType == INPUT_TEXT) {
            mInputTextType = InputType.TYPE_CLASS_TEXT;
        } else if (mInputTextType == INPUT_NUMBER) {
            mInputTextType = InputType.TYPE_CLASS_NUMBER;
        } else if (mInputTextType == INPUT_WORD) {
            mInputTextType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS;
        } else {
            mInputTextType = InputType.TYPE_TEXT_VARIATION_NORMAL;
        }
        mInoutTextWord = typedArray.getBoolean(R.styleable.InputBox_ib_input_text_word, false);
        typedArray.recycle();//释放资源
    }

    /**
     * 绘制
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        /**
         * 绘制方框
         */
        mPaint.setStrokeWidth(mInputLineWidth * 2);
        mPaint.setStyle(Paint.Style.STROKE);
        //绘制外框
        mPaint.setColor(mInputBoxColor);
        canvas.drawRoundRect(mBoxRect, 0, 0, mPaint);
        //绘制分割线
        mPaint.setStrokeWidth(mInputLineWidth);
        for (int l = 1; l < mInputCount; l++) {
            canvas.drawLine(l * mInputLineSize, 0, l * mInputLineSize, mInputLineSize, mPaint);
        }
        /**
         * 绘制文字
         */
        if (null == mInputText) return;
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mInputTextColor);
        mPaint.setTextSize(mInputTextSize);
        for (int i = 0; i < mInputText.length(); i++) {
            canvas.drawText(String.valueOf(mInputText.charAt(i)),
                    mInputLineSize / 2 + mInputLineSize * i,
                    baseLine(mInputLineSize, 0), mPaint);
        }
    }

    /**
     * 确定控件大小
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBoxRect.set(0, 0, w, h);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    /**
     * 文本更改结束
     *
     * @param s
     */
    @Override
    public void afterTextChanged(Editable s) {
        mInputText = s.toString();//获取输入的内容
//        Logger.t("输入框").i("输入的内容" + mInputText);
//        if (null != mInputText) {
//            for (int i = 0; i < mInputText.length(); i++) {
//                Logger.t("输入内容 -> " + i).i(String.valueOf(mInputText.charAt(i)));
//            }
//        }
        if (null != textChangeListener) textChangeListener.onBoxTextChanged(mInputText);
        invalidate();
    }

    /**
     * 获取文字绘制的基线
     *
     * @param bottom
     * @param top
     * @return
     */
    protected final int baseLine(int bottom, int top) {
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        return (int) ((bottom + top - fontMetrics.bottom - fontMetrics.top) / 2);
    }

    /**
     * 获取输入的内容
     *
     * @return
     */
    public String getInputText() {
        return ViewTextUtils.getTextTrim(mInputView);
    }

//    /**
//     * 事件分发时先让子控件获取焦点
//     *
//     * @param ev
//     * @return
//     */
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        mInputView.setFocusable(true);
//        mInputView.setFocusableInTouchMode(true);
//        mInputView.requestFocus();
//        return super.dispatchTouchEvent(ev);
//    }

    /**
     * 设置文字监听
     *
     * @param textChangeListener
     */
    public void setOnTextChangeListener(OnInputTextChangeListener textChangeListener) {
        this.textChangeListener = textChangeListener;
    }

    /**
     * 清空文本
     */
    public void setTextEmpty() {
        ViewTextUtils.setTextViewEmpty(mInputView);
    }


    /**
     * 设置文本
     */
    public void setTextToBox(String text) {
        mInputView.setText(text);
    }

    /**
     * 设置无焦点
     */
    public void setFocusNone() {
        ViewTextUtils.setFocusNone(mInputView);
    }


    public void addOnInputBoxClickListener(OnInputBoxClickListener onInputBoxClickListener) {
        this.onInputBoxClickListener = onInputBoxClickListener;
        mInputView.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        onInputBoxClickListener.onInputBoxClick(this);
    }
}
