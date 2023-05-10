package me.smartstore.core.view;

import me.smartstore.exceptions.StoreException;

/**
 * 고객 유형 세분화 기준 설정 메뉴
 *
 * @author YongHo Shin
 * @version v1.0
 * @since 2023-05-10
 */
public class ParameterMenu extends AbstractMenu {
  private static final ParameterMenu parameterMenu = new ParameterMenu();

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
          case 1 -> ParameterSubMenu.launch(1);

            // 설정된 기준 보기
          case 2 -> ParameterSubMenu.launch(2);

            // 기준 수정
          case 3 -> ParameterSubMenu.launch(3);

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
}
