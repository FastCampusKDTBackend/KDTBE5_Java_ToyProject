package com.smartstore.function.customer.add;

import com.smartstore.function.*;

public interface UpdateCustomerHandler extends Handleable,MenuPrintable, MenuValidator, IntegerValidator, UserDataValidator {

    default <T> String processSetElement(Class<T> type, boolean isRequired, String msg) {
        return getUserData(type, isRequired, msg);
    }


    @Override
    default int getIntegerValue() {
        return IntegerValidator.super.getIntegerValue();
    }
}