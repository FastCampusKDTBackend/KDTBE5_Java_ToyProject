package me.smartstore.core.view;

import static me.smartstore.enums.SmartStoreMessage.*;
import static me.smartstore.enums.SmartStoreMessage.PRESS_END_MSG;
import static me.smartstore.exceptions.StoreErrorCode.*;

import me.smartstore.core.domain.Parameter;
import me.smartstore.exceptions.StoreErrorCode;
import me.smartstore.exceptions.StoreException;
import me.smartstore.utils.ScannerUtility;

/**
 * 고객 유형별 기준 설정 메뉴
 *
 * @author YongHo Shin
 * @version v1.0
 * @since 2023-05-10
 */
public class ParameterSubMenu extends AbstractMenu {
  private static ParameterSubMenu parameterSubMenu = new ParameterSubMenu();

  private ParameterSubMenu() {
    super(new String[] {"Minimum Spent Time", "Minimum Pay Amount", "Back"});
  }

  /**
   * @return Singleton 객체 반환
   */
  public static ParameterSubMenu getInstance() {
    if(parameterSubMenu == null) {
      parameterSubMenu = new ParameterSubMenu();
    }
    return parameterSubMenu;
  }

  /**
   * @return 입력받은 고객 유형 분류 기준
   */
  public Parameter inputParameter() {
    Integer minimumSpentTime = null;
    Integer minimumPayAmount = null;

    while (true) {
      parameterSubMenu.show();

      try {
        switch (parameterSubMenu.selectMenuNumber()) {
          case 1 -> minimumSpentTime = inputMinimumSpentTime();
          case 2 -> minimumPayAmount = inputMinimumPayAmount();
          case 3 -> {
            return new Parameter(minimumSpentTime, minimumPayAmount);
          }
        }
      } catch (StoreException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  /**
   * @return 입력받은 최소 이용시간
   * @throws StoreException 종료 선택시.
   */
  private int inputMinimumSpentTime() throws StoreException {
    while (true) {
      System.out.println(INPUT_MINIMUM_SPENT_TIME_MSG + "\n" + PRESS_END_MSG);

      String input = ScannerUtility.getInput();

      if ("end".equals(input)) throw new StoreException(INPUT_END);

      try {
        return Integer.parseInt(input);
      } catch (NumberFormatException e) {
        System.out.println(StoreErrorCode.INVALID_FORMAT.getMessage());
      }
    }
  }

  /**
   * @return 입력받은 최소 결제금액
   * @throws StoreException 종료 선택시.
   */
  private int inputMinimumPayAmount() throws StoreException {
    while (true) {
      System.out.println(INPUT_MINIMUM_PAY_AMOUNT_MSG + "\n" + PRESS_END_MSG);

      String input = ScannerUtility.getInput();

      if ("end".equals(input)) throw new StoreException(INPUT_END);

      try {
        return Integer.parseInt(input);
      } catch (NumberFormatException e) {
        System.out.println(StoreErrorCode.INVALID_FORMAT.getMessage());
      }
    }
  }
}
