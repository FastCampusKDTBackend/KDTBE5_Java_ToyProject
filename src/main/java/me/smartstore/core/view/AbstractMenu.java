package me.smartstore.core.view;

import static me.smartstore.exceptions.StoreErrorCode.INVALID_FORMAT;
import static me.smartstore.exceptions.StoreErrorCode.INVALID_INPUT;

import me.smartstore.exceptions.StoreException;
import me.smartstore.utils.ScannerUtility;

/**
 * 메뉴 출력 및 선택
 *
 * @author YongHo Shin
 * @version v1.0
 * @since 2023-05-10
 */
public abstract class AbstractMenu {
  /** 선택 가능 메뉴 항목 */
  private final String[] items;

  public AbstractMenu(String[] items) {
    this.items = items;
  }

  /** 메뉴 출력 */
  void show() {
    StringBuilder menuBuilder = new StringBuilder().append("==============================\n");
    for (int i = 0; i < items.length; i++) {
      menuBuilder.append(i + 1).append(". ").append(items[i]).append("\n");
    }
    menuBuilder.append("==============================");
    System.out.println(menuBuilder);
  }

  /**
   * 메뉴 입력
   *
   * @return 입력된 메뉴 번호
   */
  int selectMenuNumber() {
    System.out.print("Choose One: ");

    int selection = ScannerUtility.getIntegerInputSafely();

    if (selection == -1) {
      throw new StoreException(INVALID_FORMAT);
    }

    if (selection < 1 || selection > items.length) {
      throw new StoreException(INVALID_INPUT);
    }

    return selection;
  }
}
