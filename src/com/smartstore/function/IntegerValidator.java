package com.smartstore.function;

import java.io.IOException;

public interface IntegerValidator extends Readable{
    default int getIntegerValue(){
        int value;
        while (true){
            try {
                value = Integer.parseInt(br.readLine());
                if(value < 0){
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
