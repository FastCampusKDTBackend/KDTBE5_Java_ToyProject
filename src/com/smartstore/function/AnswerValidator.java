package com.smartstore.function;

import java.io.IOException;

public interface AnswerValidator extends Readable{
    default boolean isAnswerYes(String msg){
        System.out.println(msg);
        String value = "";
        while (true){
            try {
                System.out.print("Wait for Input Y or N: ");
                value = br.readLine();
            } catch (IOException e) {
                System.out.println("Please Input Y or N");
            }
            if("y".equalsIgnoreCase(value)){
                return true;
            }
            if("n".equalsIgnoreCase(value)){
                return false;
            }
            System.out.println("Please Input Y or N");
        }
    }
}
