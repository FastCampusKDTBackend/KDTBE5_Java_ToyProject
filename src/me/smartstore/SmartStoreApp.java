package me.smartstore;

import me.smartstore.customer.CustomerRepository;
import me.smartstore.group.Group;
import me.smartstore.group.GroupParameter;
import me.smartstore.menu.Menu;
import me.smartstore.menu.topic.StartMenu;

public class SmartStoreApp {

    private static final SmartStoreApp INSTANCE = new SmartStoreApp();
    private SmartStoreApp() {}
    public static SmartStoreApp getInstance() { return INSTANCE; }

    public SmartStoreApp test() {
        Integer[][] groupParameters = new Integer[][] {
                        {10, 100_000},
                        {20, 200_000},
                        {30, 300_000}
        };
        CustomerRepository repository = CustomerRepository.getInstance();

        Group[] groups = Group.getUsedGroups();
        for (int i = 0; i < groups.length; i++) {
            Group group = groups[i];
            Integer[] param = groupParameters[i];
            group.setGroupParameter(param);
            for (int j = 0; j < 10; j++) {
                repository.resetTempCustomer();

                String id = String.format("%s_%02d", group.name(), j);
                repository.setTempId(id);

                String name = String.format(Character.toString('a'+j).repeat(4));
                repository.setTempName(name);

                Integer spentHours = (int) (Math.random() * 10) + param[0];
                repository.setTempSpentHours(spentHours);

                Integer totalPaidAmount = (int) (Math.random() * 100_000) + param[1];
                repository.setTempTotalPaidAmount(totalPaidAmount);

                repository.updateGroupOfTempCustomer();
                repository.addTempIntoRepository();
            }
        }
        return this;
    }

    public void run() {
        Menu menu = StartMenu.getInstance();
        do {
            Menu nextMenu = menu.printAndInputAndGetNextMenu();
            if (nextMenu == null)
                return;
            nextMenu.setPrevMenu(menu);
            menu = nextMenu;
        } while (true);
    }
}
