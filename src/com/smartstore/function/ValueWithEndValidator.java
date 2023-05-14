package com.smartstore.function;

import java.io.IOException;

public interface ValueWithEndValidator extends Readable{

    default <T> String getValueOrEnd(String msg, Class<T> type){
        System.out.println(Number.class.isAssignableFrom(type));
        String value;
        while (true){
            try {
                System.out.println(msg);
                value = br.readLine();
                if("end".equalsIgnoreCase(String.valueOf(value))){
                    break;
                }
                if(Number.class.isAssignableFrom(type) && Integer.parseInt(value) < 0){
                    throw new IllegalArgumentException();
                }else {
                    break;
                }
            } catch (IOException | IllegalArgumentException e) {
                System.out.println("Input valid Integer Data 1 ~ Integer.Max");
            }
        }
        return value;
    }
}
