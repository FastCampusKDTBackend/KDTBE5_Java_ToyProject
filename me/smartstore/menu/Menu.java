package me.smartstore.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import me.smartstore.exception.InputEndException;
import me.smartstore.exception.InputRangeException;

public class Menu {
  private static Menu menu;
  private BufferedReader reader;
  
  public static Menu getInstance() {
    if (menu == null)
      menu = new Menu(); 
    return menu;
  }
  
  public Menu() {
    reader = new BufferedReader(new InputStreamReader(System.in));
  }
  
  public String nextLine() {
    try {
      String str = reader.readLine().toUpperCase();
      String[] strings = str.split("\\s");
      return (strings.length > 1) ? "" : str;
    } catch (IOException e) {
      e.printStackTrace();
      return "";
    }
  }
  
  public String nextLine(String messageForEnd) throws InputEndException {
    System.out.println("\n**END를 누르면 닫습니다.**");
    try {
      String str = reader.readLine().toUpperCase();
      if (str.equals(messageForEnd))
        throw new InputEndException();
      String[] strings = str.split("\\s");
      return (strings.length > 1) ? "" : str;
    } catch (IOException e) {
      e.printStackTrace();
      return "";
    }
  }
  
  public int displayMenus(String[] menus) {
    while (true) {
      try {
        System.out.println("\n==============================");
        for (int i = 0; i < menus.length; i++) {
          System.out.printf(" %d. %s\n", i + 1, menus[i]);
        } 
        System.out.println("==============================");
        System.out.print("하나를 선택해주세요. : ");
        int choice = Integer.parseInt(nextLine());
        if (choice >= 1 && choice <= menus.length)
          return choice; 
        throw new InputRangeException();
      } catch (NumberFormatException e) {
        System.out.println("입력 포맷이 잘못되었습니다. 다시 시도해주세요.");
      } catch (InputRangeException e) {
        System.out.println("잘못 입력하셨습니다. 다시 시도해주세요.");
      }
    }
  }
}
