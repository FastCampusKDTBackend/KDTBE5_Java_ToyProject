public class Main {
    public static void main(String[] args) {

        //SmartStoreApp.getInstance()의 객체가 리턴되어야 test()있는 것, 없는 것 동일하게 사용이 가능
        SmartStoreApp.getInstance().test().run();  //function chaining
//        SmartStoreApp.getInstance().run();
    }
}

