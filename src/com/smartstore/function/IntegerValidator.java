package com.smartstore.function;

import java.io.IOException;

public interface IntegerValidator extends Readable{
    default int getIntegerValue(){
        int value;
        while (true){
            try {
                System.out.println("Wait for Input : ");
                value = Integer.parseInt(br.readLine());
                if(value < 0){
                    throw new IllegalArgumentException();
                }else {
                    break;
                }
            } catch (IOException | IllegalArgumentException e) {
                System.out.print("Input valid Integer Data 1 ~ Integer.Max");
            }
        }
        return value;
    }
}
