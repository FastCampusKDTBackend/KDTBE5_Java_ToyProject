package com.smartstore.util;

import java.util.Arrays;

public interface EnumValueProvider<T extends Enum<T> & MenuProvider> {

    default String[] getMenuListFromEnum(Class<T> type){
        T[] enumConstants = type.getEnumConstants();
        String[] menus = new String[enumConstants.length];
        for (int i = 0; i < enumConstants.length; i++) {
            menus[i] = enumConstants[i].getMenuText();
        }
        return menus;
    }
    default String[] getMenuListFromEnum(Class<T> type, int fromIndex, int toIndex){
        T[] enumConstants = type.getEnumConstants();
        String[] menus = new String[enumConstants.length];
        int count = 0;
        for (int i = fromIndex; i <= toIndex; i++) {
            menus[count] = enumConstants[i].getMenuText();
            count++;
        }
        menus = Arrays.copyOf(menus,count);
        return menus;
    }

}