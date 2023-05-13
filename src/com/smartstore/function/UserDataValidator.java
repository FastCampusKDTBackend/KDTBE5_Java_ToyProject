package com.smartstore.function;

import java.io.IOException;

public interface UserDataValidator extends Readable {
    /**
     *
     * @param type : Type of input data
     *        isRequired : is Data Required
     * @return <T> input value from user
     */

    default <T> String getUserData(Class<T> type, boolean isRequired, String msg){
        String value = null;
        while (true){
            try {
                System.out.print(msg);
                value = br.readLine();
            } catch (IOException e) {
                System.out.println("Please Input Validate Data");
            }

            if(isRequired == true && !value.isBlank()){
                break;
            }
            System.out.println("Please Input Validate Data, This Element Can't be Empty or Black");
        }
        return value;
    }
}
