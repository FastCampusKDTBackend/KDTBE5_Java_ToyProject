package me.project.menu;

import me.project.customers.Customers;
import me.project.exception.EmptyInputException;
import me.project.exception.InputRangeException;
import me.project.exception.NumberRangeException;
import me.project.groups.Grade;
import me.project.groups.Group;
import me.project.groups.Groups;
import me.project.groups.Parameter;


import java.util.InputMismatchException;

public class ParameterMenu extends Menu{
    public ParameterMenu() {
    }

    public static String chooseGrade() {
        while(true) {
            try {
                System.out.println();
                System.out.println("** 'end'를 입력하시면 이전으로 돌아갑니다! **");
                System.out.print("어떤 그룹을 선택하시겠습니까 (GENERAL, VIP, VVIP) ? : ");
                String choice = sc.next().toUpperCase();
                if (choice.equals("")) {
                    throw new EmptyInputException();
                }
                if(choice.equals("END")) {
                    return choice;
                }
                if (choice.equals("GENERAL") || choice.equals("VIP") || choice.equals("VVIP")) {
                    return choice;
                }

                throw new InputRangeException();
            }  catch (EmptyInputException e) {
                System.out.print("아무것도 입력하지 않았습니다. 다시 입력하세요.\n");
                sc.nextLine();
            } catch (InputRangeException e) {
                System.out.println("잘못입력하셨습니다. 다시 입력하세요.\n");
                sc.nextLine();
            }
        }
    }
    public static int displayParamterMenu() {
        while(true) {
            try {
                System.out.println("==============================");
                System.out.println("1. 파라미터 설정");
                System.out.println("2. 파라미터 조회");
                System.out.println("3. 파라미터 수정");
                System.out.println("4. 이전");
                System.out.println("==============================");
                System.out.print("번호를 선택하시오 : ");
                int choice = Integer.parseInt(sc.next());
                if (choice >= 1 && choice <= 4) {
                    return choice;
                }
                else {
                    throw new NumberRangeException();
                }
            } catch (NumberFormatException e) {
                System.out.print("유효하지 않은 입력입니다. 다시 입력하세요.\n");
                sc.nextLine();
            } catch (NumberRangeException e) {
                System.out.print("유효하지 않은 범위의 숫자입니다. 다시 입력하세요.\n");
                sc.nextLine();
            }
        }
    }

    public static void choiceParameterMenu() {
        while (true) {
            int choice = displayParamterMenu();
            if (choice == 1) {
                setParameter();
            } else if (choice == 2) {
                viewParameter();
            } else if (choice == 3) {
                updateParameter();
            } else {
                if (choice == 4) {
                    return;
                } else {
                    System.out.println("올바른 번호를 입력해주세요.");
                }
            }
        }
    }

    public static void setParameter() {
        String grade = chooseGrade();
        if (grade.equals("END")) { //END
            return;
        }
        if (Groups.getInstance().find(Grade.valueOf(grade)) != null && Groups.getInstance().find(Grade.valueOf(grade)).getParameter() != null) {
            System.out.println("해당 분류기준은 이미 존재합니다.");
            System.out.println(Groups.getInstance().find(Grade.valueOf(grade)));
            return;
        }
        Group group = new Group(Grade.valueOf(grade));
        Parameter parameter = new Parameter();
        while (true) {
            try {
                System.out.println("==============================");
                System.out.println("1. 최소 이용시간");
                System.out.println("2. 최소 이용금액");
                System.out.println("3. 이전");
                System.out.println("==============================");
                System.out.print("번호를 선택하시오 : ");
                int choice = Integer.parseInt(sc.next());
                if (choice == 1) {
                    setParameterMinimumTime(parameter);
                } else if (choice == 2) {
                    setParameterTotalPay(parameter);
                } else if (choice == 3) {
                    return;
                } else {
                    throw new NumberRangeException();
                }
            } catch (NumberFormatException e) {
                System.out.print("유효하지 않은 입력입니다. 다시 입력하세요.\n");
                sc.nextLine();
            } catch (NumberRangeException e) {
                System.out.print("유효하지 않은 범위의 숫자입니다. 다시 입력하세요.\n");
                sc.nextLine();
            }
            group.setParameter(parameter);
            Groups.getInstance().add(group);
            Customers.getInstance().refresh(Groups.getInstance());
        }
    }

    public static void setParameterMinimumTime(Parameter parameter) {
        while (true) {
            try {
                System.out.print("최소 이용시간을 입력하시오 : ");
                int minimumtime = Integer.parseInt(sc.next());
                if (minimumtime < 0) {
                    throw new InputRangeException();
                }
                parameter.setMinimumSpentTime(minimumtime);
                return;
            } catch (NumberFormatException e) {
                System.out.print("유효하지 않은 입력입니다. 다시 입력하세요.\n");
                sc.nextLine();
            }  catch (InputRangeException e) {
                System.out.println("유효하지 않은 값입니다. 다시 입력하세요.\n");
                sc.nextLine();
            }
        }
    }

    public static void setParameterTotalPay(Parameter parameter) {
        while (true) {
            try {
                System.out.print("최소 이용금액을 입력하시오 : ");
                int totalpay = Integer.parseInt(sc.next());
                if (totalpay < 0) {
                    throw new InputRangeException();
                }
                parameter.setMinimumTotalPay(totalpay);
                return;
            } catch (InputMismatchException e) {
                System.out.print("유효하지 않은 입력입니다. 다시 입력하세요.\n");
                sc.nextLine();
            } catch (InputRangeException e) {
                System.out.println("유효하지 않은 값입니다. 다시 입력하세요.\n");
                sc.nextLine();
            }
        }
    }

    public static void viewParameter() {
        String grade = chooseGrade();
        if (grade.equals("END")) { //END
            return;
        }
        Group group = Groups.getInstance().find(Grade.valueOf(grade));
        System.out.println(group);
    }

    public static void updateParameter() {
        String grade = chooseGrade();
        if (grade.equals("END")) { //END
            return;
        }
        if (Groups.getInstance().find(Grade.valueOf(grade)).getParameter() == null) {
            System.out.println("파라미터가 설정되지 않았습니다. 파라미터 설정을 먼저 해주십시오.");
        }
        else {
            Group updategroup = Groups.getInstance().find(Grade.valueOf(grade));
            Parameter parameter = new Parameter();
            while (true) {
                try {
                    System.out.println("==============================");
                    System.out.println("1. 최소 이용시간");
                    System.out.println("2. 최소 이용금액");
                    System.out.println("3. 이전");
                    System.out.println("==============================");
                    System.out.print("번호를 선택하시오 : ");
                    int choice = Integer.parseInt(sc.next());
                    if (choice == 1) {
                        setParameterMinimumTime(parameter);
                    } else if (choice == 2) {
                        setParameterTotalPay(parameter);
                    } else if (choice == 3) {
                        return;
                    } else {
                        throw new NumberRangeException();
                    }
                } catch (NumberFormatException e) {
                    System.out.print("유효하지 않은 입력입니다. 다시 입력하세요.\n");
                    sc.nextLine();
                } catch (NumberRangeException e) {
                    System.out.print("유효하지 않은 범위의 숫자입니다. 다시 입력하세요.\n");
                    sc.nextLine();
                }
                updategroup.setParameter(parameter);
                Customers.getInstance().refresh(Groups.getInstance());
            }
        }
    }
}
