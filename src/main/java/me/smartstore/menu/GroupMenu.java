package me.smartstore.menu;

public class GroupMenu extends Menu implements DataCRUD{
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
    public void deleteData() {

    }

    @Override
    public void back() {

    }

    public void printGroupInitMenu() {
        System.out.println("==============================");
        System.out.println("1. 고객 등급 기준 추가");
        System.out.println("2. 고객 등급 기준 조회");
        System.out.println("3. 고객 등급 기준 수정");
        System.out.println("4. 뒤로 가기");
        System.out.println("==============================");
        System.out.print("메뉴번호를 입력해주세요: ");
    }
}
