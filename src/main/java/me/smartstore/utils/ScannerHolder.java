package me.smartstore.utils;

import me.smartstore.exceptions.StoreException;

import java.util.NoSuchElementException;
import java.util.Scanner;

import static me.smartstore.exceptions.StoreErrorCode.EMPTY_INPUT;
import static me.smartstore.exceptions.StoreErrorCode.INTERNAL_ERROR;

public class ScannerHolder {
  private static final Scanner scanner = new Scanner(System.in);

  public static int getIntegerInputSafely() {
    String converted = scanner.nextLine().replaceAll("[^0-9]", "");
    return "".equals(converted) ? -1 : Integer.parseInt(converted);
  }

  public static String getInput() {
    try {
      return scanner.nextLine();
    } catch (NoSuchElementException e) {
      throw new StoreException(EMPTY_INPUT);
    } catch (IllegalStateException e) {
      throw new StoreException(INTERNAL_ERROR);
    }
  }
}
