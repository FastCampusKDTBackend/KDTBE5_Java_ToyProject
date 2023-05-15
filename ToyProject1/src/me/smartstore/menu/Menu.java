package me.smartstore.menu;

import java.util.Scanner;
import me.smartstore.exception.InputEndException;
import me.smartstore.exception.InputRangeException;

public class Menu {
	private static Menu menu;
	
	public static Menu getInstance() {
		if (menu == null)
			menu = new Menu(); 
		return menu;
	}
	
	protected Scanner scanner = new Scanner(System.in);
	  
	public String nextLine() {
		String str = this.scanner.nextLine().toUpperCase();
		String[] strings = str.split("\\s");
		return (strings.length > 1) ? "" : str;
	}
	
	public String nextLine(String messageForEnd) throws InputEndException {
		System.out.println("\n** Press 'end', if you want to exit! **");
		String str = this.scanner.nextLine().toUpperCase();
		if (str.equals(messageForEnd))
			throw new InputEndException(); 
		String[] strings = str.split("\\s");
		return (strings.length > 1) ? "" : str;
	}
	  
	public int dispMenus(String[] menus) {
		while (true) {
			try {
				System.out.println("\n==============================");
				for (int i = 0; i < menus.length; i++) {
					System.out.printf(" %d. %s\n", new Object[] { Integer.valueOf(i + 1), menus[i] });
				} 
				System.out.println("==============================");
				System.out.print("Choose One: ");
				int choice = Integer.parseInt(nextLine());
				if (choice >= 1 && choice <= menus.length)
					return choice; 
				throw new InputRangeException();
			} catch (NumberFormatException e) {
				System.out.println("입력 형식이 잘못되었습니다.");
			} catch (InputRangeException e) {
				System.out.println("입력 범위가 잘못되었습니다.");
			} 
		} 
	}

}
