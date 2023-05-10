package com.smartstore.function;

import com.smartstore.function.menu.Screen;
import com.smartstore.util.CustomList;

public interface FunctionHandler <T extends Enum<T>> extends MenuHandler{

    default String[] getEnumValues(Class<T> type){
        CustomList<String> keyList = new CustomList<>();
        for(T enumKey : type.getEnumConstants()){
            keyList.add(enumKey.name());
        }
        return keyList.toArray(String[].class);
    }

}
