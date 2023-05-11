package me.smartstore.core.view;

import static me.smartstore.enums.SmartStoreMessage.INPUT_NUMBER_OF_CUSTOMERS;
import static me.smartstore.enums.SmartStoreMessage.PRESS_END_MSG;
import static me.smartstore.exceptions.StoreErrorCode.*;
import static me.smartstore.utils.ScannerUtility.getInput;
import static me.smartstore.utils.ScannerUtility.getIntegerInputSafely;

import me.smartstore.core.domain.CustomerDTO;
import me.smartstore.core.service.CustomerService;
import me.smartstore.exceptions.StoreException;

/**
 * 고객 정보 관리 메뉴
 *
 * @author YongHo Shin
 * @version v1.0
 * @since 2023-05-10
 */
public class CustomerMenu extends AbstractMenu {
  private static CustomerMenu customerMenu = new CustomerMenu();
  private static final CustomerService customerService = CustomerService.getInstance();
  private static final CustomerSubMenu customerSubMenu = CustomerSubMenu.getInstance();

  /** 고객 정보 캐싱 */
  private static CustomerDTO[] dtoCache = new CustomerDTO[] {};

  private CustomerMenu() {
    super(
        new String[] {
          "Add Customer Data",
          "View Customer Data",
          "Update Customer Data",
          "Delete Customer Data",
          "Back"
        });
  }

  public static CustomerMenu getInstance() {
    if (customerMenu == null) {
      customerMenu = new CustomerMenu();
    }
    return customerMenu;
  }

  public void launch() {
    loop:
    while (true) {
      customerMenu.show();
      try {
        switch (customerMenu.selectMenuNumber()) {

            // 고객 등록
          case 1 -> {
            int numberOfCustomers = inputNumberOfCustomers();
            for (int idx = 0; idx < numberOfCustomers; idx++) {
              System.out.println("\n====== Customer " + (idx + 1) + ". Info. ======");
              CustomerDTO newCustomer = customerSubMenu.inputCustomerInfo();
              customerService.save(newCustomer);
            }
          }

            // 고객 명단 확인
          case 2 -> showCustomerInfo();

            // 고객 정보 수정
          case 3 -> {
            showCustomerInfo();
            int customerNumber = inputCustomerNumber();
            Long id = dtoCache[customerNumber - 1].id();
            CustomerDTO updateDTO = customerSubMenu.inputCustomerInfo();
            customerService.updateCustomerById(id, updateDTO);
          }

            // 고객 정보 삭제
          case 4 -> {
            showCustomerInfo();
            int customerNumber = inputCustomerNumber();
            Long id = dtoCache[customerNumber - 1].id();
            customerService.deleteCustomerById(id);
            showCustomerInfo();
          }

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
   * 현재 등록된 고객 명단 출력
   *
   * @throws StoreException 등록된 고객 정보가 없음
   */
  private void showCustomerInfo() throws StoreException {
    // No. 1 => Customer{userId='TEST123', name='TEST', spentTime=null, totalPay=null, group=null}
    dtoCache = customerService.findAll();
    if (dtoCache.length == 0) {
      throw new StoreException(NO_CUSTOMER);
    }

    System.out.println("\n======= Customer Info. =======");
    for (int idx = 0; idx < dtoCache.length; idx++) {
      System.out.println("No. " + (idx + 1) + " => " + dtoCache[idx]);
    }
  }

  /**
   * @return 새로 등록할 고객수
   * @throws StoreException 종료 선택시
   */
  private int inputNumberOfCustomers() throws StoreException {
    while (true) {
      System.out.println(INPUT_NUMBER_OF_CUSTOMERS + "\n" + PRESS_END_MSG);
      String input = getInput();
      if ("end".equalsIgnoreCase(input)) throw new StoreException(INPUT_END);
      try {
        int numberOfCustomers = Integer.parseInt(input);
        if (numberOfCustomers == -1) {
          throw new StoreException(INVALID_FORMAT);
        }
        return numberOfCustomers;
      } catch (NumberFormatException e) {
        System.out.println(INVALID_FORMAT.getMessage());
      } catch (StoreException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  /**
   * @return 출력된 명단 중 수정하고자 하는 고객의 번호
   */
  private int inputCustomerNumber() {
    while (true) {
      System.out.println("Which customer ( 1 ~ " + customerService.getNumberOfCustomers() + " )? ");
      try {
        int customerNumber = getIntegerInputSafely();
        if (customerNumber < 1 || customerNumber > customerService.getNumberOfCustomers()) {
          throw new StoreException(INVALID_FORMAT);
        }
        return customerNumber;
      } catch (StoreException e) {
        System.out.println(e.getMessage());
      }
    }
  }
}
