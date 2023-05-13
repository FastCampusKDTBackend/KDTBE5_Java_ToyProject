package com.smartstore.function;

import java.io.IOException;

public interface IntegerWithEndValidator extends Readable{

    //change to validate name of user
    default String getIntegerValueOrEnd(){
        String value;
        while (true){
            try {
                System.out.print("Wait for Input : ");
                value = br.readLine();
                if("end".equalsIgnoreCase(String.valueOf(value))){
                    break;
                }
                if(Integer.parseInt(value) < 0){
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
