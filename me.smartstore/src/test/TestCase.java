package test;

import Service.CustomerService;
import domain.customer.Customer;
import domain.customer.Customers;
import domain.group.Group;
import domain.group.GroupType;
import domain.group.Groups;
import domain.group.Parameter;

import java.util.Random;

public class TestCase {

    private final Groups groups = Groups.getInstance();
    private final Customers customers = Customers.getInstance();
    private final CustomerService customerService = CustomerService.getInstance();

    public void buildTestCase() {
        groups.add(new Group(new Parameter(10, 100000), GroupType.GENERAL));
        groups.add(new Group(new Parameter(20, 200000), GroupType.VIP));
        groups.add(new Group(new Parameter(30, 300000), GroupType.VVIP));

        for (int i = 0; i < 26; i++) {
            customers.add(new Customer(
                    Character.toString(
                            (char) ('a' + i)),
                    (char) ('a' + i) + "123",
                    ((int) (Math.random() * 5) + 1) * 10,
                    ((int) (Math.random() * 5) + 1) * 100000));
        }

        int startChar = 48; // number '0'
        int endChar = 122; // letter 'z'
        int targetLength = 10;
        Random random = new Random();

        //NONE 타입 테스트를 위한 Customer generate
        for (int x = 0; x < 5; x++) {
            String randomName = random.ints(startChar,endChar + 1)
                    .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                    .limit(targetLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();

            String randomId = random.ints(startChar,endChar + 1)
                    .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                    .limit(targetLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();

            customers.add(new Customer(randomName, randomId, 0, 0));
        }

        customerService.refresh();

        System.out.println("allCustomers = \n" + customers);
        System.out.println("allGroups = \n" + groups);
    }
}
