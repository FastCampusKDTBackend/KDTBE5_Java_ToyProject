package me.smartstore.core.view;

import me.smartstore.core.domain.CustomerGroup;
import me.smartstore.core.domain.Parameter;
import me.smartstore.core.service.CustomerGroupService;
import me.smartstore.enums.CustomerType;
import me.smartstore.exceptions.StoreException;
import me.smartstore.utils.ScannerUtility;

import static me.smartstore.enums.SmartStoreMessage.INPUT_CUSTOMER_GROUP_MSG;
import static me.smartstore.enums.SmartStoreMessage.PRESS_END_MSG;
import static me.smartstore.exceptions.StoreErrorCode.*;
import static me.smartstore.utils.StoreUtility.convertInputStrToCustomerType;

/**
 * 고객 유형 세분화 기준 설정 메뉴
 *
 * @author YongHo Shin
 * @version v1.0
 * @since 2023-05-10
 */
public class ParameterMenu extends AbstractMenu {
  private static final ParameterMenu parameterMenu = new ParameterMenu();
  private static final ParameterSubMenu parameterSubMenu = ParameterSubMenu.getInstance();
  private static final CustomerGroupService customerGroupService =
      CustomerGroupService.getInstance();

  private ParameterMenu() {
    super(new String[] {"Set Parameter", "View Parameter", "Update Parameter", "Back"});
  }

  public static void launch() {
    loop:
    while (true) {
      parameterMenu.show();
      try {
        switch (parameterMenu.selectMenuNumber()) {
            // 기준 초기 설정
          case 1 -> {
            while (true) {
              CustomerType customerType = inputCustomerType();
              CustomerGroup customerGroup = customerGroupService.find(customerType);
              if (customerGroup.getParameter() != null) {
                System.out.println(GROUP_ALREADY_SET.getMessage());
                System.out.println(customerGroup);
                continue;
              }
              Parameter parameter = parameterSubMenu.inputParameter();
              customerGroup = customerGroupService.setParameter(customerType, parameter);
              System.out.println(customerGroup);
            }
          }

            // 설정된 기준 보기
          case 2 -> {
            while (true) {
              CustomerType customerType = inputCustomerType();
              System.out.println(customerGroupService.find(customerType));
            }
          }

            // 기준 수정
          case 3 -> {
            while (true) {
              CustomerType customerType = inputCustomerType();
              CustomerGroup customerGroup = customerGroupService.find(customerType);
              if (customerGroup.getParameter() == null) {
                throw new StoreException(NO_PARAMETER);
              }
              Parameter parameter = parameterSubMenu.inputParameter();
              customerGroup = customerGroupService.setParameter(customerType, parameter);
              System.out.println(customerGroup);
            }
          }

            // 이전 메뉴
          case 4 -> {
            break loop;
          }
        }
      } catch (StoreException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  /**
   * @return 입력받은 고객 유형
   * @throws StoreException 종료 선택시, 공백 입력시 발생
   */
  private static CustomerType inputCustomerType() throws StoreException {
    while (true) {
      System.out.println(INPUT_CUSTOMER_GROUP_MSG + "\n" + PRESS_END_MSG);
      String input = ScannerUtility.getInput().toUpperCase();
      if ("end".equalsIgnoreCase(input)) throw new StoreException(INPUT_END);

      try {
        if ("".equals(input)) throw new StoreException(NULL_INPUT);
        return convertInputStrToCustomerType(input);
      } catch (StoreException e) {
        System.out.println(e.getMessage());
      }
    }
  }
}