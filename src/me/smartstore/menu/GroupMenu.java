package me.smartstore.menu;

import me.smartstore.customer.Customers;
import me.smartstore.exception.ElementNotFoundException;
import me.smartstore.exception.InputEndException;
import me.smartstore.exception.InputRangeException;
import me.smartstore.group.Group;
import me.smartstore.group.GroupType;
import me.smartstore.group.Groups;
import me.smartstore.util.Message;

public class GroupMenu implements Menu{
	private final Groups allGroups = Groups.getInstance();
	private final Customers allCustomers = Customers.getInstance();

	private static GroupMenu groupMenu;

	private GroupMenu() {}

	public static GroupMenu getInstance() {
		if (groupMenu == null) {
			groupMenu = new GroupMenu();
		}

		return groupMenu;
	}

	@Override
	public void show() {
		while (true) {
			int choice = chooseMenu(new String[]{
				"Set Parameter",
				"View Parameter",
				"Update Parameter",
				"Back"
			});

			if (choice == 1) {
				setParameter(0);
			} else if (choice == 2) {
				viewParameter();
			} else if (choice == 3) {
				setParameter(1);
			} else if (choice == 4) {
				break;
			}
		}
	}

	private void setParameter(int updateFlag) {
		while(true) {
			try {
				System.out.println("Which group (" + GroupType.showGroupType() +  ")?");
				String inputGroup = nextLine(Message.END_MSG);

				if (!GroupType.isElement(inputGroup)) throw new ElementNotFoundException();

				Group selectGroup = allGroups.findByGroupType(GroupType.getGroupType(inputGroup));

				if (selectGroup != null && updateFlag == 0) {
					System.out.println(selectGroup.getGroupType() + " group already exists.");
					System.out.println("\n" + selectGroup);
					continue;
				}

				if (selectGroup != null) System.out.println(selectGroup);

				if (selectGroup == null) {
					selectGroup = new Group(GroupType.getGroupType(inputGroup));
					allGroups.add(selectGroup);
				}

				showParameterMenu(selectGroup);

				allCustomers.refresh();

			} catch (InputEndException e) {
				System.out.println(e.getMessage());
				break;
			} catch (ElementNotFoundException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private void showParameterMenu(Group group) {
		while(true) {
			int choice = chooseMenu(new String[]{
				"Minimum Spent Time",
				"Minimum Total Pay",
				"Back"
			});

			if (choice == 1) {
				setTimeParameter(group);
			} else if (choice == 2) {
				setPayParameter(group);
			} else if (choice == 3) {
				System.out.println(group);
				break;
			}
		}
	}

	private void setTimeParameter(Group group) {
		while(true) {
			try {
				System.out.println("Input Minimum Spent Time: ");
				int time = Integer.parseInt(nextLine(Message.END_MSG));

				if (time < 0) throw new InputRangeException();

				group.setMinHours(time);

				break;
			} catch (NumberFormatException | InputRangeException e){
				System.out.println(e.getMessage());
			} catch (InputEndException e) {
				System.out.println(e.getMessage());
				break;
			}
		}
	}

	private void setPayParameter(Group group) {
		while(true) {
			try {
				System.out.println("Input Minimum Total Pay: ");

				int pay = Integer.parseInt(nextLine(Message.END_MSG));

				if (pay < 0) throw new InputRangeException();
				group.setMinPay(pay);

				break;

			} catch (NumberFormatException | InputRangeException e) {
				System.out.println(e.getMessage());
			} catch (InputEndException e) {
				System.out.println(e.getMessage());
				break;
			}
		}
	}

	private void viewParameter() {
		while(true) {
			try {
				System.out.println("Which group (GENERAL(G), VIP(V), VVIP(V))?");
				String groupType = nextLine(Message.END_MSG);

				Group findGroup = allGroups.findByGroupType(GroupType.getGroupType(groupType));

				if (findGroup == null) throw new ElementNotFoundException();

				System.out.println(findGroup);
			} catch (InputEndException e){
				System.out.println(e.getMessage());
				break;
			} catch (ElementNotFoundException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
