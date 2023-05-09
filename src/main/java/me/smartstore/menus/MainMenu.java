package me.smartstore.menus;

import me.smartstore.exceptions.StoreException;

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
          case 1 -> ParameterMenu.launch();
          case 2 -> CustomerDataMenu.launch();
          case 3 -> ClassificationSummaryMenu.launch();
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
