package menu;

interface Menu {
    StringBuilder sb = new StringBuilder();
    //read buffer or scanner

    default String chooseMenu(){
        return "";
    }

    default void displayMenu(){

    }

    void runMenuSelectionLoop();

    void handleChoice(String menuNumber);
}
