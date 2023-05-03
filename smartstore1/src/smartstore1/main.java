package smartstore1;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int a;
		int b;
		int use;
		String st;
		ArrayList<Member> memberList = new ArrayList<Member>();
		
		Group group = new Group();
		
				
		
		
		while(true) {
		System.out.println("1: 고객정보 입력 2: 고객 분류기준 입력, 3: 고객정보출력,4:회원 정보 삭제 5: 회원 정보 수정");
		System.out.println("고객 분류 기준을 입력하지 않으면 등급은 상이할 수 있습니다.");
		 a = sc.nextInt();
		 switch(a) {
		 case 1:
			Member member = new Member();
			System.out.println("이름 :");
			st = sc.next();
			member.setName(st);			
			System.out.println("아이디 :");
			st = sc.next();
			member.setId(st);			
			System.out.println("이용시간 :");
			use = sc.nextInt();			
			member.setUseHour(use);			
			System.out.println("이용금액 :");
			use = sc.nextInt();
			member.setUseMoney(use);
			group.setGrade(member);
			memberList.add(member);

			
			System.out.println("<입력된 정보 출력> : ");
			System.out.println(member.toString());		
			
			break;
		 case 2:
			 System.out.println("고객 분류기준 입력창 입니다.");
			 System.out.println("GENERAL고객 이용시간 기준을 입력해주세요 ex) ~10");
			 a = sc.nextInt();
			 System.out.println("GENERAL고객 이용금액 기준을 입력해주세요 ex) ~10000");
			 b = sc.nextInt();
			 group.setGeneral(a, b);
			 System.out.println("VIP고객 이용시간 기준을 입력해주세요 ex) ~20");
			 a = sc.nextInt();
			 System.out.println("VIP고객 이용금액 기준을 입력해주세요 ex) ~20000");
			 b = sc.nextInt();
			 group.setVip(a, b);
			 System.out.println("입력이 완료되었습니다. 새로운 등급을 설정합니다.");	
			 for(int i = 0 ; i< memberList.size();i++) {
				 Member temMember = new Member();
				 temMember = memberList.get(i);
				 group.setGrade(temMember);				 
			 }
			 System.out.println("새로운 등급 설정이 완료되었습니다.");
			 break;
//////////////////////출력//////////////////////////////
		 case 3:
			 System.out.println("출력기준을 선택해주세요. 1.기본 2.이름순 3. 시간순 4.금액순");	
			 int printNum = sc.nextInt();
			 
			 if(memberList.size() == 0) {
				 System.out.println("입력된 고객이 없습니다. 처음 화면으로 돌아갑니다.");
				 break;
			 }			 
			 
			 switch(printNum){
				 case 1:		
					 for (Member member1 : memberList) {
				            System.out.println(member1);
				        }
					break;
					
					
				 case 2:
					 MemberSorter.sortByName(memberList);
					 for (Member member1 : memberList) {
				            System.out.println(member1);
				        }
				      break;
				      

				 case 3:
					 MemberSorter.sortByUseHour(memberList);
					 for (Member member2 : memberList) {
				            System.out.println(member2);
				        }
					 break;
					 
					 
				 case 4:
					 MemberSorter.sortByUseMoney(memberList);
				      for (Member member3 : memberList) {
				            System.out.println(member3);
				        }
				      break;
				 default:
					 System.out.println("잘못 입력하셨습니다.");
					 break;
				 }

			
////////////////////////////출력//////////////////////////////////			 
			 
			case 4:
				System.out.println("삭제할 회원의 이름을 입력해주세요:");
				String name = sc.next();
				int index = -1;
				for (int i = 0; i < memberList.size(); i++) {
				    if (memberList.get(i).getName().equals(name)) {
				        index = i;
				        break;
				    }
				}
				if (index != -1) {
				    memberList.remove(index);
				    System.out.println(name + " 회원이 삭제되었습니다.");
				} else {
				    System.out.println("해당하는 이름의 회원이 존재하지 않습니다.");
				}
				 break;
				 
			case 5:
				System.out.println("수정할 회원의 이름을 입력해주세요:");
				String name1 = sc.next();
				int index1 = -1;
				for (int i = 0; i < memberList.size(); i++) {
				    if (memberList.get(i).getName().equals(name1)) {
				        index1 = i;
				        break;
				    }
				}
				if (index1 != -1) {				  
				    System.out.println("수정할 " + name1 +  " 회원의 아이디을 입력하세요.");
				    String id = sc.next();
				    memberList.get(index1).setId(id);
				    System.out.println("수정할 " + name1 +  " 회원의 이용시간을 입력하세요.");
				    int hour = sc.nextInt();
				    memberList.get(index1).setUseHour(hour);
				    System.out.println("수정할 " + name1 +  " 회원의 이용금액을 입력하세요.");
				    int money = sc.nextInt();
				    memberList.get(index1).setUseMoney(money);
				    
				} else {
				    System.out.println("해당하는 이름의 회원이 존재하지 않습니다.");
				}
				 break;

			 default:
				 System.out.println("잘못 입력하셨습니다.");
				 break;
				}
	
			 
		 }
		
		
	}
}
