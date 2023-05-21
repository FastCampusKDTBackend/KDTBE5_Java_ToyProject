package me.smartstore.util;

import java.util.Arrays;
import java.util.Objects;

public class UtilMethod {
  public static boolean isAnyNUll(Object... objects) {
    return Arrays.<Object>stream(objects).anyMatch(Objects::isNull);
  }
  
  public static boolean isAllNUll(Object... objects) {
    return Arrays.<Object>stream(objects).allMatch(Objects::isNull);
  }
}
