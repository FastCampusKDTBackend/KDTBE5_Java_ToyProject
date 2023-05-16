package me.smartstore.utils;

import me.smartstore.exceptions.StoreException;

import java.util.NoSuchElementException;
import java.util.Scanner;

import static me.smartstore.exceptions.StoreErrorCode.EMPTY_INPUT;
import static me.smartstore.exceptions.StoreErrorCode.INTERNAL_ERROR;

/**
 * 사용자로부터 입력을 받기 위한 유틸리티
 *
 * @author YongHo Shin
 * @version v1.0
 * @since 2023-05-10
 */
public class ScannerUtility {
  private static final Scanner scanner = new Scanner(System.in);

  /**
   * @return 정수로 변환된 결과. 공백일 경우 -1.
   */
  public static int getIntegerInputSafely() {
    String converted = scanner.nextLine().replaceAll("[^0-9]", "");
    return "".equals(converted) ? -1 : Integer.parseInt(converted);
  }

  public static String getInput() throws StoreException {
    try {
      return scanner.nextLine();
    } catch (NoSuchElementException e) {
      throw new StoreException(EMPTY_INPUT);
    } catch (IllegalStateException e) {
      throw new StoreException(INTERNAL_ERROR);
    }
  }
}
