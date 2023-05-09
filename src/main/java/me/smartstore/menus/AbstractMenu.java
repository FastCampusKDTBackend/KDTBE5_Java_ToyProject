package me.smartstore.menus;

import me.smartstore.exceptions.ErrorCode;
import me.smartstore.exceptions.StoreException;
import me.smartstore.utils.ScannerHolder;

/**
 * 메뉴 출력 및 선택
 *
 * @author YongHo Shin
 * @version v0.1
 * @since 2023-04-28
 */
public abstract class AbstractMenu {
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

    int selection = ScannerHolder.getIntegerInputSafely();

    if (selection == -1) {
      throw new StoreException(ErrorCode.INVALID_FORMAT);
    }

    if (selection < 1 || selection > items.length) {
      throw new StoreException(ErrorCode.INVALID_INPUT);
    }

    return selection;
  }
}
