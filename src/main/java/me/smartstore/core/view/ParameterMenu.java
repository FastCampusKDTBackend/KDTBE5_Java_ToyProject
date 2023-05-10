package me.smartstore.core.view;

import me.smartstore.exceptions.StoreException;

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
          case 1 -> ParameterSubMenu.launch(1);
          case 2 -> ParameterSubMenu.launch(2);
          case 3 -> ParameterSubMenu.launch(3);
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
