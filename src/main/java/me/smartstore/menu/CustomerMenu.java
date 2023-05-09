package me.smartstore.menu;

public class CustomerMenu extends Menu implements DataCRUD {
    @Override
    public void addData() {
        
    }

    @Override
    public void viewData() {

    }

    @Override
    public void updateData() {

    }

    @Override
    public void DeleteData() {

    }

    @Override
    public void back() {

    }

    public void printCustomerInitMenu(){
        System.out.println("==============================");
        System.out.println("1. 고객 정보 추가");
        System.out.println("2. 고객 정보 조회");
        System.out.println("3. 고객 정보 수정");
        System.out.println("4. 고객 정보 삭제");
        System.out.println("5. 뒤로 가기");
        System.out.println("==============================");
        System.out.print("메뉴번호를 입력해주세요: ");
    }
}
