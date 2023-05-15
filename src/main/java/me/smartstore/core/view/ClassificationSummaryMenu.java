package me.smartstore.core.view;

import static me.smartstore.enums.SmartStoreMessage.INPUT_SORT_ORDER;
import static me.smartstore.enums.SmartStoreMessage.PRESS_END_MSG;
import static me.smartstore.enums.SortBy.*;
import static me.smartstore.exceptions.StoreErrorCode.INPUT_END;
import static me.smartstore.utils.StoreUtility.convertInputStrToSortOrder;

import me.smartstore.core.domain.CustomerDTO;
import me.smartstore.core.domain.CustomerGroupDTO;
import me.smartstore.core.service.CustomerGroupService;
import me.smartstore.core.service.CustomerService;
import me.smartstore.enums.SortBy;
import me.smartstore.enums.SortOrder;
import me.smartstore.exceptions.StoreException;
import me.smartstore.utils.ScannerUtility;

/**
 * 고객 분류 정보 출력 메뉴
 *
 * @author YongHo Shin
 * @version v1.0
 * @since 2023-05-10
 */
public class ClassificationSummaryMenu extends AbstractMenu {
  private static ClassificationSummaryMenu classificationSummaryMenu =
      new ClassificationSummaryMenu();

  private static final CustomerGroupService customerGroupService =
      CustomerGroupService.getInstance();
  private static final CustomerService customerService = CustomerService.getInstance();

  private ClassificationSummaryMenu() {
    super(
        new String[] {
          "Summary",
          "Summary (Sorted By Name)",
          "Summary (Sorted By Spent Time)",
          "Summary (Sorted By Total Pay Amount)",
          "Back"
        });
  }

  public static ClassificationSummaryMenu getInstance() {
    if (classificationSummaryMenu == null) {
      classificationSummaryMenu = new ClassificationSummaryMenu();
    }
    return classificationSummaryMenu;
  }

  public void launch() {
    loop:
    while (true) {
      classificationSummaryMenu.show();
      try {
        switch (classificationSummaryMenu.selectMenuNumber()) {
            // 일반 요약 정보 출력 - 가장 최근에 조회한 정렬 기준에 따라 자동으로 변환
          case 1 -> displayClassifiedCustomerData();

            // 고객 그룹별 이름순 정렬
          case 2 -> {
            while (true) {
              try {
                displayClassifiedCustomerDataSortBy(NAME, inputSortOrder());
              } catch (StoreException e) {
                if (e.getErrorCode() == INPUT_END) break;
                System.out.println(e.getMessage());
              }
            }
          }

            // 고객 그룹별 이용시간순 정렬
          case 3 -> {
            while (true) {
              try {
                displayClassifiedCustomerDataSortBy(SPENT_TIME, inputSortOrder());
              } catch (StoreException e) {
                if (e.getErrorCode() == INPUT_END) break;
                System.out.println(e.getMessage());
              }
            }
          }

            // 고객 그룹별 사용금액순 정렬
          case 4 -> {
            while (true) {
              try {
                displayClassifiedCustomerDataSortBy(PAY_AMOUNT, inputSortOrder());
              } catch (StoreException e) {
                if (e.getErrorCode() == INPUT_END) break;
                System.out.println(e.getMessage());
              }
            }
          }

            // 이전 메뉴
          case 5 -> {
            break loop;
          }
        }
      } catch (StoreException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  /**
   * @return 정렬 방향 (오름차순, 내림차순)
   * @throws StoreException 종료 선택시
   */
  private SortOrder inputSortOrder() throws StoreException {
    while (true) {
      System.out.println(INPUT_SORT_ORDER + "\n" + PRESS_END_MSG);
      try {
        String input = ScannerUtility.getInput();
        if ("end".equalsIgnoreCase(input)) throw new StoreException(INPUT_END);
        return convertInputStrToSortOrder(input);
      } catch (StoreException e) {
        System.out.println(e.getMessage());
        if (e.getErrorCode() == INPUT_END) throw new StoreException(INPUT_END);
      }
    }
  }

  private void displayClassifiedCustomerData() {
    CustomerGroupDTO[] customerGroupDTOs = customerGroupService.findAll();
    CustomerDTO[][] classifiedCustomerDTOs = customerService.getClassifiedCustomerData();

    displayClassifiedCustomerData(customerGroupDTOs, classifiedCustomerDTOs);
  }

  private void displayClassifiedCustomerDataSortBy(SortBy sortBy, SortOrder sortOrder) {
    CustomerGroupDTO[] customerGroupDTOs = customerGroupService.findAll();
    CustomerDTO[][] classifiedCustomerDTOs =
        customerService.getClassifiedCustomerData(sortBy, sortOrder);

    displayClassifiedCustomerData(customerGroupDTOs, classifiedCustomerDTOs);
  }

  private void displayClassifiedCustomerData(
      CustomerGroupDTO[] customerGroupDTOs, CustomerDTO[][] classifiedCustomerDTOs) {
    for (int gIdx = 0; gIdx < customerGroupDTOs.length; gIdx++) {
      System.out.println("\n" + customerGroupDTOs[gIdx].groupTitle());

      if (classifiedCustomerDTOs[gIdx] == null || classifiedCustomerDTOs[gIdx].length == 0)
        System.out.println("Null.");

      for (int cIdx = 0; cIdx < classifiedCustomerDTOs[gIdx].length; cIdx++) {
        System.out.println("No. " + (cIdx + 1) + " => " + classifiedCustomerDTOs[gIdx][cIdx]);
      }
    }
  }
}
