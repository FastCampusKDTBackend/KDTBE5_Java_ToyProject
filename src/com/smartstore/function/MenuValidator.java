package com.smartstore.function;

import java.io.IOException;

public interface MenuValidator extends Readable{
    default String getMenuNumber(String[] values) {
        int menu = -1;
        while (true){
            try {
                System.out.print("Input : ");
                menu = Integer.parseInt(br.readLine());
                if (menu <= 0 || menu > values.length) {
                    // TODO: 2023-05-08 throw other exception, catch it
                    throw new NumberFormatException("Invalid Menu");
                }
                break;

            } catch (IOException | NumberFormatException e) {
                System.out.println("Invalid Menu");
            }
        }
        return String.valueOf(menu);
    }
}
