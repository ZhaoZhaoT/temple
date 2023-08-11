package com.example.temple.network;


import com.example.temple.bean.BaseResponse;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Response;
import rxhttp.wrapper.annotation.Parser;
import rxhttp.wrapper.exception.ParseException;
import rxhttp.wrapper.parse.TypeParser;
import rxhttp.wrapper.utils.Converter;
import rxhttp.wrapper.utils.GsonUtil;

/**
 * Created by lh on 12/25/025
 */
@Parser(name = "Response")
public class ResponseParser<T> extends TypeParser<BaseResponse<T>> {

    protected ResponseParser() {
        super();
    }

    public ResponseParser(Type type) {
        super(type);
    }

   /* @Override
    public BaseResponse<T> onParse(okhttp3.Response response) throws IOException {
        final Type type = ParameterizedTypeImpl.get(BaseResponse.class, mType); //获取泛型类型

        BaseResponse<T> data = Converter.convert(response, type);
        T t = data.getData(); //获取data字段
        if (data.getCode() != 200) {//这里假设code不等于200，代表数据不正确，抛出异常
            throw new ParseException(String.valueOf(data.getCode()), data.getMessage(), response);
        }
        return data;
    }*/

    @Override
    public BaseResponse<T> onParse(Response response) throws IOException {
        BaseResponse<String> data = Converter.convertTo(response, BaseResponse.class, String.class);
        T t = null;
        if (data.getSuccess()) {
            if(types[0]==String.class){
                t=(T) data.getData();
            }else{
                t = GsonUtil.getObject(data.getData(), types[0]);
            }
        }
        if (t == null && types[0] == String.class) {
            t = (T) "";
        }
        if (data.getSuccess()==false) {
            throw new ParseException(String.valueOf(data.getCode()), data.getMessage(), response);
        }

        BaseResponse<T> data2=new BaseResponse<T>();
        data2.setData(t);
        data2.setCode(data.getCode());
        data2.setMessage(data.getMessage());
        return data2;
    }


}