package com.smartstore.function;

import com.smartstore.function.mainmenu.MainMenuHandler;
import com.smartstore.util.CustomList;

public interface EnumValueProvider<T extends Enum<T>> extends MainMenuHandler {

    default String[] enumValuesToStringArray(Class<T> type){
        CustomList<String> keyList = new CustomList<>();
        for(T enumKey : type.getEnumConstants()){
            keyList.add(enumKey.name());
        }
        return keyList.toArray(String[].class);
    }

}
