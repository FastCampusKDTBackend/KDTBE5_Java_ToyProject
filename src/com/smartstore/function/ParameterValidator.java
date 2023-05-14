package com.smartstore.function;

import com.smartstore.membership.MembershipType;

import java.io.IOException;

public interface ParameterValidator extends Readable{
    default String getParameter(String[] values) {
        String valueName="";
        boolean isExit = false;
        if(values.length > 0) {
            while (!isExit) {
                try {
                    System.out.print("Input : ");
                    valueName = br.readLine();
                    if("end".equals(valueName)){
                        isExit = true;
                        break;
                    }
                    for (String membershipName : values) {
                        if (MembershipType.valueOf(membershipName.toUpperCase()).isMatchedName(valueName)) {
                            isExit = true;
                            break;
                        }
                    }
                } catch (IOException | IllegalArgumentException | NullPointerException e) {
                    System.out.println("Invalid Parameter");
                }
            }
        }
        return valueName;
    }
}
