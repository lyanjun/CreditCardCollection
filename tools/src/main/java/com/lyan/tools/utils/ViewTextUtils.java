package com.lyan.tools.utils;

import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.lyan.tools.view.InputBox;

import java.util.List;

/**
 * Created by Lyan on 17/2/11.
 */

public class ViewTextUtils {

    /**
     * 获取文本
     * @param view
     * @param <T>
     * @return
     */
    public static <T extends TextView> String getTextFromView(T view){
        return view.getText().toString();
    }

    /**
     * 获取文本去掉开头和结尾的空格
     * @param view
     * @param <T>
     * @return
     */
    public static <T extends TextView> String getTextTrim(T view){
        return view.getText().toString().trim();
    }

    /**
     * 添加内容
     * @param view
     * @param text
     * @param <T>
     */
    @NonNull
    public static  <T extends TextView> String setText(T view,String text){
        view.setText(text);//设置内容
        return autoSplitText(view);//对内容重新排列
    }
    /**
     * 内容填满再折行
     * @param textView
     * @return
     */
    private static String autoSplitText(TextView textView) {
        final String rawText = textView.getText().toString(); //原始文本
        final Paint tvPaint = textView.getPaint(); //paint，包含字体等信息
        final float tvWidth = textView.getWidth() - textView.getPaddingLeft() - textView.getPaddingRight(); //控件可用宽度

        //将原始文本按行拆分
        String [] rawTextLines = rawText.replaceAll("\r", "").split("\n");
        StringBuilder sbNewText = new StringBuilder();
        for (String rawTextLine : rawTextLines) {
            if (tvPaint.measureText(rawTextLine) <= tvWidth) {
                //如果整行宽度在控件可用宽度之内，就不处理了
                sbNewText.append(rawTextLine);
            } else {
                //如果整行宽度超过控件可用宽度，则按字符测量，在超过可用宽度的前一个字符处手动换行
                float lineWidth = 0;
                for (int cnt = 0; cnt != rawTextLine.length(); ++cnt) {
                    char ch = rawTextLine.charAt(cnt);
                    lineWidth += tvPaint.measureText(String.valueOf(ch));
                    if (lineWidth <= tvWidth) {
                        sbNewText.append(ch);
                    } else {
                        sbNewText.append("\n");
                        lineWidth = 0;
                        --cnt;
                    }
                }
            }
            sbNewText.append("\n");
        }

        //把结尾多余的\n去掉
        if (!rawText.endsWith("\n")) {
            sbNewText.deleteCharAt(sbNewText.length() - 1);
        }
        textView.setText(sbNewText);
        return sbNewText.toString();
    }

    /**
     * 将指定日期放入文本中
     * @param dateStr
     * @param textViews
     */
    public static void setDateToView(String dateStr,List<TextView> textViews){
        String date[] = dateStr.split("-");
        for (int i = 0; i < date.length; i++){
            textViews.get(i).setText(String.valueOf(date[i]));
        }
    }

    /**
     * 设置文本域的内容为空
     * @param textView
     * @param <T>
     */
    public static <T extends TextView> void setTextViewEmpty(T textView){
        textView.setText("");
    }

    /**
     * 设置文本域的内容为空
     * @param textViews
     * @param <T>
     */
    public static <T extends TextView> void setTextViewEmpty(List<T> textViews){
        for (int i = 0; i < textViews.size(); i++) {
            setTextViewEmpty(textViews.get(i));
        }
    }

    /**
     * 设置文本域的内容为空
     * @param inputBox
     */
    public static void setTextViewEmpty(InputBox inputBox){
        inputBox.setTextEmpty();
    }
}
