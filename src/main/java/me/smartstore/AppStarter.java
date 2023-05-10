package me.smartstore;

import me.smartstore.core.view.MainMenu;

/**
 * 스마트스토어 메인 시작점
 *
 * @author YongHo Shin
 * @version v1.0
 * @since 2023-05-10
 */
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
