package me.project.menu;

import me.project.customers.Customer;
import me.project.customers.Customers;
import me.project.exception.EmptyInputException;
import me.project.exception.InputFormatException;
import me.project.exception.InputRangeException;
import me.project.exception.NumberRangeException;
import me.project.groups.Group;
import me.project.groups.Groups;

import java.util.regex.Pattern;

public class CustomerMenu extends Menu {

    public CustomerMenu() {
    }

    public static int displayCustomerMenu() { //고객 정보 메뉴창
        while (true) {
            try {
                System.out.println("==============================");
                System.out.println("1. 고객정보 추가");
                System.out.println("2. 고객정보 조회");
                System.out.println("3. 고객정보 수정");
                System.out.println("4. 고객정보 삭제");
                System.out.println("5. 이전");
                System.out.println("==============================");
                System.out.print("번호를 선택하시오 : ");
                int choice = Integer.parseInt(sc.next());
                if (choice >= 1 && choice <= 5) {
                    return choice;
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
        }
    }

    public static void choiceCustomerMenu() { //고객정보메뉴창에서 메뉴번호 선택하여 해당 업무 수행 메소드
        while(true) {
            int choice = displayCustomerMenu();
            if (choice == 1) {
                int size = inputSizeOfCustomers();
                addCustomerData(size);
            } else if (choice == 2) {
                viewCustomerData();
            } else if (choice == 3) {
                updateCustomerData();
            } else if (choice == 4) {
                deleteCustomerData();
            } else {
                if (choice == 5) {
                    return;
                } else {
                    System.out.println("올바른 번호를 입력하시오.");
                }

            }
        }
    }

    public static int inputSizeOfCustomers() { // 고객을 몇명 추가할지 입력하는 메소드
        while (true) {
            try {
                System.out.println("");
                System.out.println("** 0을 입력하시면 이전으로 돌아갑니다! ** ");
                System.out.println("몇 명의 고객을 추가하시겠습니까? ");
                int size = Integer.parseInt(sc.next());
                if (size < 0) {
                    throw new InputRangeException();
                }
                return size;
            } catch (NumberFormatException e) {
                System.out.println("유효하지 않은 입력입니다. 다시 입력하세요.\n");
                sc.nextLine();
            } catch (InputRangeException e) {
                System.out.print("유효하지 않은 범위의 숫자입니다. 다시 입력하세요.\n");
                sc.nextLine();
            }
        }
    }

    public static int displayAddCustomerMenu() { //고객정보 추가 메뉴창
        while (true) {
            try {
                System.out.println();
                System.out.println("==============================");
                System.out.println("1. 고객 이름 설정");
                System.out.println("2. 고객 ID 설정");
                System.out.println("3. 고객 이용시간 입력");
                System.out.println("4. 고객 총 이용금액 입력");
                System.out.println("5. 이전");
                System.out.println("==============================");
                System.out.print("번호를 선택해주세요 : ");
                int choice = Integer.parseInt(sc.next());
                if ( choice >= 1 && choice <= 5) {
                    return choice;
                } else {
                    throw new InputRangeException();
                }
            } catch (NumberFormatException e) {
                System.out.println("유효하지 않은 입력입니다. 다시 입력하세요.\n");
                sc.nextLine();
            } catch (InputRangeException e) {
                System.out.print("유효하지 않은 범위의 숫자입니다. 다시 입력하세요.\n");
                sc.nextLine();
            }
        }
    }

    public static void addCustomerData(int size) { //고객정보 추가 구현한 메소드
        restart :
        for(int i = 0; i < size; i++) {
            Customer customer = new Customer();
            System.out.println("\n====== 고객 " + (i+1) + "번 정보 ======");

            while (true) {
                while (true) {
                    int choice = displayAddCustomerMenu();
                    if (choice == 1) {
                        setCustomerUserName(customer);
                        break;
                    } else if (choice == 2) {
                        setCustomerUserID(customer);
                        break;
                    } else if (choice == 3) {
                        setCustomerSpentTime(customer);
                        break;
                    } else if (choice == 4) {
                        setCustomerTotalPay(customer);
                        break;
                    } else if (choice == 5) {
                        Customers.getInstance().add(customer);
                        System.out.println();
                        continue restart;
                    }
                    else {
                        System.out.println("올바르지 않은 번호입니다. 다시 입력하세요.");
                    }
                }

                Group group = Groups.getInstance().findGroup(customer);
                if (group == null) {
                    customer.setGroup((Group) null);
                } else if (group != null) {
                    customer.setGroup(group);
                }

            }
        }
    }


    public static void setCustomerUserName(Customer customer) {
        while (true) {
            try {
                System.out.println();
                System.out.print("고객 이름을 입력하시오 : ");
                String name = sc.next();
                if (name != null && !name.equals("")) {
                    if (Pattern.matches("^[a-zA-Z]*$",name)) { // 영어만 가능
                        customer.setCustomerName(name);
                        return;
                    }
                    throw new InputFormatException();
                }

                throw new EmptyInputException();
            } catch (InputFormatException e) {
                System.out.println("올바르지 않은 형식입니다. 다시 입력하세요.");
                sc.nextLine();
            } catch (EmptyInputException e) {
                System.out.println("아무것도 입력하지 않았습니다. 다시 입력하세요.");
                sc.nextLine();
            }
        }
    }

    public static void setCustomerUserID(Customer customer) {
        while (true) {
            try {
                System.out.println();
                System.out.print("고객 ID를 입력하시오 : ");
                String ID = sc.next();
                if (ID != null && !ID.equals("")) {
                    if (Pattern.matches("^[a-zA-Z0-9]*$",ID)) { //영어+숫자만 가능
                        customer.setCustomerID(ID);
                        return;
                    }
                    throw new InputFormatException();
                }

                throw new EmptyInputException();
            } catch (InputFormatException e) {
                System.out.println("올바르지 않은 형식입니다. 다시 입력하세요.\n");
                sc.nextLine();
            } catch (EmptyInputException e) {
                System.out.println("아무것도 입력하지 않았습니다. 다시 입력하세요.\n");
                sc.nextLine();
            }
        }
    }

    public static void setCustomerSpentTime(Customer customer) {
        while (true) {
            try {
                System.out.println();
                System.out.print("고객 이용시간을 입력하시오 : ");
                int time = Integer.parseInt(sc.next());
                if (time < 0) {
                    throw new NumberRangeException();
                }

                customer.setSpentTime(time);
                return;
            } catch (NumberFormatException e) {
                System.out.println("올바르지 않은 형식입니다. 다시 입력하세요.");
                sc.nextLine();
            } catch (NumberRangeException e) {
                System.out.print("유효하지 않은 범위의 숫자입니다. 다시 입력하세요.\n");
                sc.nextLine();
            }
        }
    }

    public static void setCustomerTotalPay(Customer customer) {
        while (true) {
            try {
                System.out.println();
                System.out.print("고객 총 사용금액을 입력하시오 : ");
                int total = Integer.parseInt(sc.next());
                if (total < 0) {
                    throw new NumberRangeException();
                }

                customer.setTotalPay(total);
                return;
            } catch (NumberFormatException e) {
                System.out.println("올바르지 않은 형식입니다. 다시 입력하세요.");
                sc.nextLine();
            } catch (NumberRangeException e) {
                System.out.print("유효하지 않은 범위의 숫자입니다. 다시 입력하세요.\n");
                sc.nextLine();
            }
        }
    }

    public static void viewCustomerData(){ //고객정보 조회 메소드
        System.out.println();
        System.out.println("======= 고객 정보 =======");

        for(int i = 0; i < Customers.getInstance().getCount(); i++) {
            Customer customer = Customers.getInstance().get(i);
            if (customer != null) {
                System.out.println("NO. " + (i+1) + " ==>\n" + customer);
                System.out.println();
            } else {
                System.out.println("null");
            }
        }
        System.out.println("=====================");
    }

    public static int findCustomer (int custcount) { //수정 또는 삭제하고 싶은 고객 번호를 입력받고 리턴하는 메소드
        while (true) {
            try {
                System.out.println("몇 번 고객을 선택하시겠습니까? (1" + " ~ " + custcount + ")" );
                int choice = Integer.parseInt(sc.next());
                return choice;
            } catch (NumberFormatException e) {
                System.out.print("유효하지 않은 입력입니다. 다시 입력하세요.\n");
                sc.nextLine();
            }
        }
    }

    public static void updateCustomerData() {
        int custcount = Customers.getInstance().getCount();
        viewCustomerData();
        int custNo = findCustomer(custcount);
        if (custNo >= 1 && custNo <= custcount) {
            Customer customer = Customers.getInstance().get(custNo - 1);
            while (true) {
                while (true) {
                    int choice = displayAddCustomerMenu();
                    if (choice == 1) {
                        setCustomerUserName(customer);
                        break;
                    } else if (choice == 2) {
                        setCustomerUserID(customer);
                        break;
                    } else if (choice == 3) {
                        setCustomerSpentTime(customer);
                        break;
                    } else if (choice == 4) {
                        setCustomerTotalPay(customer);
                        break;
                    } else if (choice == 5) {
                        return;
                    }
                    else {
                        System.out.println("올바르지 않은 번호입니다. 다시 입력하세요.");
                    }
                }
                Group group = Groups.getInstance().findGroup(customer);
                if (group == null) {
                    customer.setGroup((Group) null);
                } else if (group != null) {
                    customer.setGroup(group);
                }
            }
        }
        else
            System.out.println("올바르지 않은 번호입니다.");
    }

    public static void deleteCustomerData() {
        int custcount = Customers.getInstance().getCount();
        viewCustomerData();
        int custNo = findCustomer(custcount);
        if (custNo >= 1 && custNo <= custcount) {
            System.out.println("===================================");
            System.out.println("          삭제된 고객 정보");
            System.out.println("===================================");
            System.out.println(Customers.getInstance().pop(custNo - 1));
            System.out.println("-----------------------------------");
            System.out.println("고객 정보가 성공적으로 삭제되었습니다 !");
            System.out.println("===================================");
            viewCustomerData();
        }
        else
            System.out.println("올바르지 않은 번호입니다.");
    }
}
