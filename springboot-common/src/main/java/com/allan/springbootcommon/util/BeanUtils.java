package com.allan.springbootcommon.util;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 实体辅助类
 * */
public class BeanUtils {

    /**
     * 2个bean之间的值拷贝
     * */
    public void beanCopy(Object target, Object source) throws Exception{
        BeanInfo targetInfo = Introspector.getBeanInfo(target.getClass());
        BeanInfo sourceInfo = Introspector.getBeanInfo(source.getClass());
        PropertyDescriptor[] propertyDesc = targetInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDesc.length; i++) {
            if(propertyDesc[i].getName().compareToIgnoreCase("class")==0){
                continue;
            }
            //String strValue = (String)userMap.get((String)propertyDesc[i].getName());
            String strValue = null;
            if(strValue != null){
                Object[] oParam = new Object[]{};
                Method mr = propertyDesc[i].getWriteMethod();
                if( mr != null){
                    oParam = new String[]{(strValue)};
                    try{
                        //注意这里的参数。
                        mr.invoke(source, oParam);
                    }catch(IllegalArgumentException iea){
                        System.out.println("参数错误。");
                        iea.printStackTrace();
                    }

                }
            }

        }
    }
    /*对象转map*/
   /* public static Map<?, ?> objectToMap(Object obj) {
        if (obj == null)
            return null;
        return new org.apache.commons.beanutils.BeanMap(obj);
    }*/



    //自定义Strig适配器
    public static final TypeAdapter<String> STRING = new TypeAdapter<String>()
    {
        public String read(JsonReader reader) throws IOException
        {
            if (reader.peek() == JsonToken.NULL)
            {
                reader.nextNull();
                return "";
            }
            return reader.nextString();
        }

        public void write(JsonWriter writer, String value) throws IOException
        {
            if (value == null)
            {
                // 在这里处理null改为空字符串
                writer.value("");
                return;
            }
            writer.value(value);
        }
    };
}
