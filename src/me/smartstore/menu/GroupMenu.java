package me.smartstore.menu;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import me.smartstore.customer.Customers;
import me.smartstore.exception.InputExitException;
import me.smartstore.exception.InputFormatException;
import me.smartstore.group.Group;
import me.smartstore.group.GroupType;
import me.smartstore.group.Groups;
import me.smartstore.group.Parameter;
import me.smartstore.util.Message;

public class GroupMenu implements Menu {
	private static final Groups groups = Groups.getInstance();
	private static final Customers customers = Customers.getInstance();

	private static GroupMenu groupMenu;

	private GroupMenu() {
	}

	public static GroupMenu getInstance() {
		if (groupMenu == null) {
			groupMenu = new GroupMenu();
		}
		return groupMenu;
	}

	/***** Menu 선택 loop *****/
	@Override
	public void root() {
		while (true) {
			try {
				int choice = chooseMenu(Stream.of(RootMenu.values()).map(RootMenu::toString).toArray(String[]::new));

				Boolean isBack = RootMenu.findByCode(choice).run();
				if (isBack) {
					break;
				}
			} catch (NoSuchElementException e) {
				System.out.println(Message.ERR_INVALID_INPUT_RANGE);
			}
		}
	}

	protected GroupType chooseGroup() {
		while (true) {
			try {
				System.out.println(
					"\nWhich group (" + GroupType.GENERAL + ", " + GroupType.VIP + ", " + GroupType.VVIP + ")?");
				String choice = nextUpperLine(Message.EXIT_FLAG);
				return GroupType.findByInput(choice);
			} catch (InputExitException e) {
				System.out.println(Message.INFO_INPUT_EXIT_FLAG);
				return null;
			} catch (NoSuchElementException e) {
				System.out.println(Message.ERR_INVALID_INPUT_RANGE);
			}
		}
	}

	protected void chooseParameter(Group group) {
		while (true) {
			try {
				int choice = chooseMenu(
					Stream.of(ParameterMenu.values()).map(ParameterMenu::toString).toArray(String[]::new));

				Boolean isBack = ParameterMenu.findByCode(choice).run(group);
				if (isBack) {
					break;
				}
			} catch (NoSuchElementException e) {
				System.out.println(Message.ERR_INVALID_INPUT_RANGE);
			}
		}
	}

	/*****RootMenu 수행 메서드(set, view, update) *****/
	protected void setParameter() {
		while (true) {
			try {
				GroupType groupType = chooseGroup();
				if (groupType == null) {
					break;
				}

				Group group = groups.findByGroupType(groupType);
				if (group.getParameter() != null) { // 이미 초기화 됨. 그룹 다시 선택
					System.out.println("\n" + group.getGroupType() + " group already exists.");
					System.out.println(group);
					continue;
				}

				chooseParameter(group);
			} catch (NoSuchElementException e) {
				System.out.println(Message.ERR_INVALID_INPUT_RANGE);
			}
		}
	}

	protected void viewParameter() {
		while (true) {
			GroupType groupType = chooseGroup();
			if (groupType == null) {
				break;
			}

			Group group = groups.findByGroupType(groupType);
			System.out.println(group);
		}
	}

	protected void updateParameter() {
		while (true) {
			try {
				GroupType groupType = chooseGroup();
				if (groupType == null) {
					break;
				}

				Group group = groups.findByGroupType(groupType);
				if (group.getParameter() == null) { // 초기화 안함. 루트 메뉴로 돌아감
					System.out.println(Message.ERR_INVALID_PARAMETER_EMPTY);
					break;
				}

				chooseParameter(group);
			} catch (NoSuchElementException e) {
				System.out.println(Message.ERR_INVALID_INPUT_RANGE);
			}
		}
	}

	/***** ParameterMenu 수행 메서드(각 파라미터 set. add, update 시 사용) *****/
	protected void setSpentTime(Group group) {
		while (true) {
			try {
				System.out.println("\nInput Minimum Spent Time");
				int input = groupMenu.nextInt(Message.EXIT_FLAG);

				if (group.getParameter() == null) {    // 초기 등록 시
					group.setParameter(new Parameter());
				}

				Parameter parameter = new Parameter(input, group.getParameter().getMinimumTotalPay());
				group.setParameter(parameter);

				customers.refreshGroupType(groups);
				break;
			} catch (InputExitException e) {
				System.out.println(Message.INFO_INPUT_EXIT_FLAG);
				break;
			} catch (InputFormatException e) {
				System.out.println(Message.ERR_INVALID_INPUT_FORMAT);
			}
		}
	}

	protected void setTotalPay(Group group) {
		while (true) {
			try {
				System.out.println("\nInput Minimum Total Pay");
				int input = groupMenu.nextInt(Message.EXIT_FLAG);

				if (group.getParameter() == null) { // 초기 등록 시
					group.setParameter(new Parameter());
				}

				Parameter parameter = new Parameter(group.getParameter().getMinimumSpentTime(), input);
				group.setParameter(parameter);

				customers.refreshGroupType(groups);
				break;
			} catch (InputExitException e) {
				System.out.println(Message.INFO_INPUT_EXIT_FLAG);
				break;
			} catch (InputFormatException e) {
				System.out.println(Message.ERR_INVALID_INPUT_FORMAT);
			}
		}
	}

	/***** Menu enum *****/
	enum RootMenu {
		SET(1, "Set Parameter") {
			@Override
			public Boolean run() {
				groupMenu.setParameter();
				return false;
			}
		},
		VIEW(2, "View Parameter") {
			@Override
			public Boolean run() {
				groupMenu.viewParameter();
				return false;
			}
		},
		UPDATE(3, "Update Parameter") {
			@Override
			public Boolean run() {
				groupMenu.updateParameter();
				return false;
			}
		},
		BACK(4, "Back") {
			@Override
			public Boolean run() {
				return Menu.back();
			}
		};

		private int code;
		private String label;

		RootMenu(int code, String label) {
			this.code = code;
			this.label = label;
		}

		public int getCode() {
			return code;
		}

		public static RootMenu findByCode(int code) {
			return Arrays.stream(RootMenu.values())
				.filter(t -> t.getCode() == code)
				.findAny()
				.orElseThrow(NoSuchElementException::new);
		}

		@Override
		public String toString() {
			return " " + code + ". " + label;
		}

		public abstract Boolean run();
	}

	enum ParameterMenu {
		SPENT_TIME(1, "Minimum Spent Time") {
			@Override
			public Boolean run(Group group) {
				groupMenu.setSpentTime(group);
				return false;
			}
		},
		TOTAL_PAY(2, "Minimum Total Pay") {
			@Override
			public Boolean run(Group group) {
				groupMenu.setTotalPay(group);
				return false;
			}
		},
		BACK(3, "Back") {
			@Override
			public Boolean run(Group group) {
				System.out.println(group);
				return Menu.back();
			}
		};

		private int code;
		private String label;

		ParameterMenu(int code, String label) {
			this.code = code;
			this.label = label;
		}

		public int getCode() {
			return code;
		}

		public static ParameterMenu findByCode(int code) {
			return Arrays.stream(ParameterMenu.values())
				.filter(t -> t.getCode() == code)
				.findAny()
				.orElseThrow(NoSuchElementException::new);
		}

		@Override
		public String toString() {
			return " " + code + ". " + label;
		}

		public abstract Boolean run(Group group);
	}
}






