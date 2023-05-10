package me.smartstore.core.view;

import me.smartstore.exceptions.StoreException;

/**
 * 메인 메뉴 화면
 *
 * @author YongHo Shin
 * @version v1.0
 * @since 2023-05-10
 */
public class MainMenu extends AbstractMenu {
  private static final MainMenu mainMenu = new MainMenu();

  private MainMenu() {
    super(new String[] {"Parameter", "Customer Data", "Classification Summary", "Quit"});
  }

  public static void launch() {
    loop:
    while (true) {
      mainMenu.show();
      try {
        switch (mainMenu.selectMenuNumber()) {
          // 고객 유형 세분화 기준 설정
          case 1 -> ParameterMenu.launch();

          // 고객 정보 관리
          case 2 -> CustomerMenu.launch();

          // 고객 정보 요약
          case 3 -> ClassificationSummaryMenu.launch();

          // 종료
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
