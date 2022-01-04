import java.util.Scanner;

public class UserClient {
    // Menu used to get user inputs
    private static final Menu MENU = new Menu(new Scanner(System.in),
            "\n***** Property Register Application v0.1 *****\n", "Add property", "List all properties",
            "Search property", "Calculate average area");

    // Values corresponding to user inputs
    private static final int ADD_PROPERTY = 1, LIST_ALL_PROPERTIES = 2, FIND_PROPERTY = 3, CALCULATE_AVERAGE_AREA = 4,
            EXIT = 5;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        TestData testData = new TestData();

        boolean finished = false;
        // The while-loop will run as long as the user has not selected
        // to quit the application
        while (!finished) {
            int menuChoice = MENU.promptUser();
            switch (menuChoice) {
                case ADD_PROPERTY:
                    String muniName = Menu.askString(scanner,
                            "What is the name of the municipality in which the property is located in?");
                    int muniNum = Menu.askInt(scanner, "What is the municipality number?");
                    int lotNum = Menu.askInt(scanner, "In what lot number is the property is located?");
                    int secNum = Menu.askInt(scanner, "In what section number is the property located?");
                    float area = Menu.askFloat(scanner, "What is the area of the property?");
                    String owner = Menu.askString(scanner, "Who is the current owner of the property?");
                    Property newProperty = new Property(muniName, muniNum, lotNum, secNum, area, owner);
                    testData.data.addProperty(newProperty);
                    break;
                case LIST_ALL_PROPERTIES:
                    System.out.println(testData.data.listAllProperties());
                    break;
                case FIND_PROPERTY:
                    String identifyer = Menu.askString(scanner, "What is the unique identifyer of the property?");
                    try {
                        Property property = testData.data.getProperty(identifyer);
                        System.out.println(property.toString());
                    } catch (IllegalArgumentException e) {
                        System.out.println("The property could not be found...");
                    }
                    break;
                case CALCULATE_AVERAGE_AREA:
                    System.out.printf("The average area is %.2f square meters.\n", testData.data.calcAvgPropertyArea());
                    break;
                case EXIT:
                    System.out.println("Thank you for using the Properties app!\n");
                    finished = true;
                    break;
                default:
                    System.out.println("Unrecognized menu selected..");
                    break;
            }
        }
    }
}
