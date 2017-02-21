// Restaurant Homework
// Instructor-provided code.
// You SHOULD heavily modify this file to make it interface with your own classes.

import java.io.*;
// import java.util.*;

/**
 * This class represents the text user interface (UI) for the restaurant
 * program, allowing the user to view and manage the restaurant and its objects.
 * @author Your name
 * @version Put version
 */
public class RestaurantTextUI {
    // file name from which to read the restaurant data
    private static final String DEFAULT_RESTAURANT_FILENAME = "tables.txt";
    private Restaurant restaurant = new Restaurant("Joe's Restaurant");

    /**
     * Constructs a new text user interface for managing a restaurant.
     */
    public RestaurantTextUI() {
        System.out.println("Restaurant Simulator");

        // TODO: initialization code can go here

    }

    /**
     * Reads the information about the restaurant from the default restaurant
     * file.
     * @return true if the data was read successfully; false if there were any errors
     */
    public boolean readRestaurantData() {
        try {
            File restaurantFile = ValidInputReader.getValidFile(
                    "File name for restaurant data [" + DEFAULT_RESTAURANT_FILENAME + "]?",
                    DEFAULT_RESTAURANT_FILENAME);

            // TODO: read restaurant info from tables file;
            Table t = new Table(1, 4, false, null);
            restaurant.initiateTables(t.producer());

            // return true if it was successful and false if not
            return true;
        } catch (Exception e) {
            // when there is an error reading the file,
            System.out.println("Unable to read restaurant data: file not found.");
            return false;
        }
    }

    /**
     * Displays the main menu of choices and prompts the user to enter a choice.
     * Once a valid choice is made, initiates other code to handle that choice.
     */
    public void mainMenu() {
        // main menu
        displayOptions();
        while (true) {
            String choice = ValidInputReader.getValidString(
                    "Main menu, enter your choice:",
                    "^[sSaAdDrRpPtTcCwWqQ!?]$").toUpperCase();
            if (choice.equals("S")) {
                serversOnDuty();
            } else if (choice.equals("A")) {
                addServer();
            } else if (choice.equals("D")) {
                dismissServer();
            } else if (choice.equals("R")) {
                cashRegister();
            } else if (choice.equals("P")) {
                partyToBeSeated();
            } else if (choice.equals("T")) {
                tableStatus();
            } else if (choice.equals("C")) {
                checkPlease();
            } else if (choice.equals("W")) {
                waitingList();
            } else if (choice.equals("Q")) {
                break;
            } else if (choice.equals("?")) {
                displayOptions();
            } else if (choice.equals("!")) {
                rickRoll();
            }
            System.out.println();
        }
    }

    // Displays the list of key commands the user can use.
    private void displayOptions() {
        System.out.println();
        System.out.println("Main System Menu");
        System.out.println("----------------");
        System.out.println("S)ervers on duty");
        System.out.println("A)dd server");
        System.out.println("D)ismiss server");
        System.out.println("R)egister");
        System.out.println("P)arty has arrived");
        System.out.println("T)ables status");
        System.out.println("C)heck, please");
        System.out.println("W)aiting list");
        System.out.println("?) display this menu of choices again");
        System.out.println("Q)uit");
    }

    // Called when S key is pressed from main menu.
    // Displays all servers who are currently working.
    private void serversOnDuty() {
        System.out.println("Servers currently on duty:");

        // TODO: display current servers, e.g.:
        restaurant.displayAllServers();

        // Server #1 ($49.76 in total tips)
    }

    // Called when A key is pressed from main menu.
    // Adds one more server to the system.
    private void addServer() {
        System.out.println("Adding a new server to our workforce:");

        // TODO: add server and display current count of servers, e.g.:
        // Current server count: 3
        int serverNum;
        serverNum = restaurant.getServers();
        System.out.println("Current server count: " + restaurant.getServers());
        restaurant.addServer(new Servers(serverNum++));
    }

    // Called when D key is pressed from main menu.
    // Sends one server home for the night (if possible).
    private void dismissServer() {

        int serverNum;
        serverNum = restaurant.getServers();

        if(serverNum == 0) {
            // when there are no servers,
            System.out.println("No servers to dismiss.");
        } else if (serverNum == 1) {
            // when only one server remains with tables remaining,
            System.out.println("Sorry, the server cannot cash out now;");
            System.out.println("there are still tables remaining and this is the only server left.");
        } else if (serverNum >= 1) {
            // when the server is able to be dismissed,
            System.out.println("Dismissing a server:");
            restaurant.removeServer(new Servers(serverNum));
            serverNum--;
            System.out.println("Servers nos available: " + serverNum);
        } else {
            System.out.println("error, cannot dismiss server");
        }

        // TODO: cash out server and display current count of servers
        // Server #2 cashes out with $47.95 in total tips.
        // Servers now available: 3
    }

    // Called when R key is pressed from main menu.
    // Displays how much money is in the restaurant's cash register.
    private void cashRegister() {
        System.out.println("Cash register:");

        // TODO: display total money earned, e.g.:
        // Total money earned = $877.50
        System.out.println("Toatl money earned = " + restaurant.getCashReg());
    }

    // Called when T key is pressed from main menu.
    // Displays the current status of all tables.
    private void tableStatus() {
        System.out.println("Tables status:");

        System.out.println(restaurant.getTable(1));
        System.out.println(restaurant.getTable(2));
        System.out.println(restaurant.getTable(3));
        System.out.println(restaurant.getTable(4));
        System.out.println(restaurant.getTable(5));
        System.out.println(restaurant.getTable(6));
        System.out.println(restaurant.getTable(7));
        System.out.println(restaurant.getTable(8));
        System.out.println(restaurant.getTable(9));
        System.out.println(restaurant.getTable(10));

        // TODO: show restaurant's table statuses, e.g.:
        // Table 5 (2-top): Jones party of 2 - Server #2
        // Table 6 (4-top): empty
    }

    // Called when C key is pressed from main menu.
    // Helps process a party's check to leave the restaurant.

    private void checkPlease() {
        System.out.println("Send the check to a party that has finished eating:");
        String partyName = ValidInputReader.getValidString("Party's name?", "^[a-zA-Z '-]+$");

        // when such a party is sitting at a table in the restaurant,
        double subtotal = ValidInputReader.getValidDouble("Bill subtotal?", 0.0, 9999.99);
        double tip = ValidInputReader.getValidDouble("Tip?", 0.0, 9999.99);

        restaurant.addCashReg(subtotal);
        System.out.println("Gave total of $" + subtotal + "to cash register.");
        System.out.println("Cash Register has " + restaurant.getCashReg() + " inside of it.");

        // TODO: give tip to server, e.g.:
        // Gave tip of $9.50 to Server #2.

        // update restaurant's cash register, e.g.
        // Gave total of $39.75 to cash register.

        System.out.println("Seating from waiting list:");
        // when a party on the waiting list can now be seated, e.g.:
        // Table 6 (6-top): Erickson party of 5 - Server #2

        // when such a party is NOT sitting at a table in the restaurant,
        System.out.println("There is no party by that name.");
    }

    // Called when W key is pressed from main menu.
    // Displays the current waiting list, if any.
    private void waitingList() {
        if (restaurant.isWaitingListEmpty()) {
            System.out.println("Waiting list:");
            restaurant.printWaitingList();
            // TODO: show restaurant's waiting list, e.g.:
            // Johnson party of 7
            // Erickson party of 6

            // when there is nobody on the waiting list,
        } else {
            System.out.println("empty");
        }
    }

    // Called when P key is pressed from main menu.
    // Helps seat a newly arriving party at a table in the restaurant.
    private void partyToBeSeated() {
        if (restaurant.isServerListEmpty()) {
            // when there are no servers,
            System.out.println("Sorry, there are no servers here yet to seat this party");
            System.out.println("and take their orders.  Add servers and try again.");
        } else {
            // when there is at least one server,
            String partyName = ValidInputReader.getValidString("Party's name?", "^[a-zA-Z '-]+$");
            int partySize = ValidInputReader.getValidInt("How many people in the party?", 1, 99999);
            Party party = new Party(partyName, partySize);

            if (!(party.uniqueName(restaurant.waitingList))) {
                System.out.println("We already have a party with that name in the restaurant.");
                System.out.println("Please try again with a unique party name.");
                // when a duplicate party name is found,
            } else {
                // TODO: try to seat this party

                if (partySize > 8) {
                    // when the restaurant doesn't have any tables big enough to ever seat this party,
                    System.out.println("Sorry, the restaurant is unable to seat a party of this size.");
                } else {

                    if(restaurant.getLargestTable() < partySize) {
                        // when all tables large enough to accommodate this party are taken,
                        System.out.println("Sorry, there is no open table that can seat this party now.");
                        boolean wait = ValidInputReader.getYesNo("Place this party onto the waiting list? (y/n)");
                        if (wait) {
                            restaurant.addWaiting(party);
                        }
                    } else {
                        restaurant.addPartyToTable(party);

                        // TODO: put this party on the waiting list
                    }
                }
            }
        }
    }


    // You know what this method does.
    private void rickRoll() {
        // TODO: tell you how I'm feeling; make you understand
        System.out.println("We're no strangers to love");
        System.out.println("You know the rules and so do I");
        System.out.println("A full commitment's what I'm thinking of");
        System.out.println("You wouldn't get this from any other guy");
        System.out.println("I just wanna tell you how I'm feeling");
        System.out.println("Gotta make you understand");
        System.out.println();
        System.out.println("Never gonna give you up");
        System.out.println("Never gonna let you down");
        System.out.println("Never gonna run around and desert you");
        System.out.println("Never gonna make you cry");
        System.out.println("Never gonna say goodbye");
        System.out.println("Never gonna tell a lie and hurt you");
    }

    // This helper is just put into the text UI code to mark places where you
    // will need to add or modify this file.  Crashes with a runtime exception.
    private void crash(String message) {
        // Math.random() < 10 will always be true;  so why is it there?
        // I can't just throw because Eclipse will then warn about dead code
        // for any code that occurs after a call to crash().
        // So I wrap the exception throw in an "opaque predicate" to fool it.
        if (Math.random() < 10) {
            throw new RuntimeException("Not yet implemented: " + message);
        }
    }
}
