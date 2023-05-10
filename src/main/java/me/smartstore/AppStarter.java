package me.smartstore;

import me.smartstore.core.view.MainMenu;

public class AppStarter {
  private AppStarter() {}

  private static class StoreAppContext {
    private static final AppStarter app = new AppStarter();
  }

  public static AppStarter getInstance() {
    return StoreAppContext.app;
  }

  public void run() {
    title();
    MainMenu.launch();
    finish();
  }

  private void title() {
    System.out.println(
        """
        ===========================================
        Title : SmartStore Customer Segmentation
        Release Date : 23.04.24
        Copyright 2023 YongHo All rights reserved.
        ===========================================
        """);
  }
  private void finish() {
    System.out.println("Program Finished.");
  }
}
