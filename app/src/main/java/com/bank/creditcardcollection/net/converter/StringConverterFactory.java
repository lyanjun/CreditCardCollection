package com.bank.creditcardcollection.net.converter;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * 作者： LYJ
 * 功能： String类型的转换器(后台返回的Json并不规范，
 * 所以还是自行解析Json)
 * 创建日期： 2017/7/19
 */

public final class StringConverterFactory extends Converter.Factory {
    public static StringConverterFactory create() {
        return new StringConverterFactory();
    }

    private StringConverterFactory() {
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (type == String.class) {
            return StringResponseBodyConverter.INSTANCE;
        }
        return null;
    }

    private static class StringResponseBodyConverter implements Converter<ResponseBody, String> {
        static final StringResponseBodyConverter INSTANCE = new StringResponseBodyConverter();

        @Override
        public String convert(@NonNull ResponseBody value) throws IOException {
            String jsonStr = value.string();
            return jsonStr.substring(1,jsonStr.length()-1).trim();
        }
    }
}
