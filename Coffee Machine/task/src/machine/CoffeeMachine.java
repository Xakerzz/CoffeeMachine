package machine;

import java.util.Scanner;

public class CoffeeMachine {
    static Scanner scanner = new Scanner(System.in);
    private static int water = 400;
    private static int milk = 540;
    private static int coffeeBeans = 120;
    private static int amountCups = 9;
    private static int moneyInMachine = 550;
    private static boolean isWorking = true;
    static private String userInPut;

    public static int getWater() {
        return water;
    }

    public static void setWater(int water) {
        CoffeeMachine.water = water;
    }

    public static int getMilk() {
        return milk;
    }

    public static void setMilk(int milk) {
        CoffeeMachine.milk = milk;
    }

    public static int getCoffeeBeans() {
        return coffeeBeans;
    }

    public static void setCoffeeBeans(int coffeeBeans) {
        CoffeeMachine.coffeeBeans = coffeeBeans;
    }

    public static int getAmountCups() {
        return amountCups;
    }

    public static void setAmountCups(int amountCups) {
        CoffeeMachine.amountCups = amountCups;
    }

    public static int getMoneyInMachine() {
        return moneyInMachine;
    }

    public static void setMoneyInMachine(int moneyInMachine) {
        CoffeeMachine.moneyInMachine = moneyInMachine;
    }

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        while (isWorking) {
            getMenu();
            doOperation();
        }
    }


    public static void getMenu() {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        String newInPut = scanner.nextLine();
        userInPut = newInPut;

    }

    public static void doOperation() {
        switch (userInPut) {
            case "buy":
                System.out.println();
                buyCoffee();
                break;
            case "fill":
                System.out.println();
                fillTheMachine();
                break;
            case "take":
                System.out.println();
                takeMoney();
                break;
            case "remaining":
                System.out.println();
                getStateOfMachine();
                break;
            case "exit":
                isWorking = !isWorking;
                break;
            default:
                break;
        }
    }

    public static void buyCoffee() {

        do {
            System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
            userInPut = scanner.nextLine();
        } while (!"1".equals(userInPut) && !"2".equals(userInPut) && !"3".equals(userInPut) && !"back".equals(userInPut));

        switch (userInPut) {
            case "1":
                if (checkResources(userInPut)) {
                    setWater(getWater() - 250);
                    setCoffeeBeans(getCoffeeBeans() - 16);
                    setMoneyInMachine(getMoneyInMachine() + 4);
                    setAmountCups(getAmountCups() - 1);
                }
                break;
            case "2":
                if (checkResources(userInPut)) {
                    setWater(getWater() - 350);
                    setMilk(getMilk() - 75);
                    setCoffeeBeans(getCoffeeBeans() - 20);
                    setMoneyInMachine(getMoneyInMachine() + 7);
                    setAmountCups(getAmountCups() - 1);
                }
                break;
            case "3":
                if (checkResources(userInPut)) {
                    setWater(getWater() - 200);
                    setMilk(getMilk() - 100);
                    setCoffeeBeans(getCoffeeBeans() - 12);
                    setMoneyInMachine(getMoneyInMachine() + 6);
                    setAmountCups(getAmountCups() - 1);
                }
                break;
            case "back":
                break;
            default:
                System.out.println("Wrong input!");
        }
        System.out.println();
    }

    public static boolean checkResources(String userInPut) {
        String noResource = "";
        if ((userInPut.equals("1") && getWater() >= 250 && getCoffeeBeans() >= 16 && getAmountCups() >= 1) ||
                (userInPut.equals("2") && getWater() >= 350 && getMilk() >= 75 && getCoffeeBeans() >= 20 && getAmountCups() >= 1) ||
                (userInPut.equals("3") && getWater() >= 200 && getMilk() >= 100 && getCoffeeBeans() >= 12 && getAmountCups() >= 1)) {
            System.out.println("I have enough resources, making you a coffee!");
            return true;
        } else {
            if (getWater() < 200 || getWater() < 250 || getWater() < 350) {
                noResource = "water";
            } else if (getCoffeeBeans() < 12 || getCoffeeBeans() < 16 || getCoffeeBeans() < 20) {
                noResource = "coffee beans";
            } else if (getMilk() < 75 || getMilk() < 100) {
                noResource = "milk";
            }
            System.out.printf("Sorry, not enough %s!\n", noResource);
        }
        return false;
    }

    public static void fillTheMachine() {
        System.out.println("Write how many ml of water you want to add:");
        setWater(getWater() + scanner.nextInt());
        System.out.println("Write how many ml of milk you want to add:");
        setMilk(getMilk() + scanner.nextInt());
        System.out.println("Write how many grams of coffee beans you want to add:");
        setCoffeeBeans(getCoffeeBeans() + scanner.nextInt());
        System.out.println("Write how many disposable cups of coffee you want to add:");
        setAmountCups(getAmountCups() + scanner.nextInt());
        System.out.println();
    }

    public static void takeMoney() {
        System.out.printf("I gave you $%d\n\n", getMoneyInMachine());
        setMoneyInMachine(0);
    }

    public static void getStateOfMachine() {
        System.out.printf("The coffee machine has:\n" +
                "%d ml of water\n" +
                "%d ml of milk\n" +
                "%d g of coffee beans\n" +
                "%d disposable cups\n" +
                "$%d of money\n\n", getWater(), getMilk(), getCoffeeBeans(), getAmountCups(), getMoneyInMachine());
    }

    public static void knowHowManyCupsFromResource() {
        System.out.println("Write how many cups of coffee you will need:");
        int cupsOfCoffee = scanner.nextInt();
        System.out.printf("For %d cups of coffee you will need:\n", cupsOfCoffee);
        System.out.printf("%d ml of water\n" +
                "%d ml of milk\n" +
                "%d g of coffee beans", water * cupsOfCoffee, milk * cupsOfCoffee, coffeeBeans * cupsOfCoffee);
    }


    public void startToMakeCoffee() {
        System.out.println("Starting to make a coffee\n" +
                "Grinding coffee beans\n" +
                "Boiling water\n" +
                "Mixing boiled water with crushed coffee beans\n" +
                "Pouring coffee into the cup\n" +
                "Pouring some milk into the cup\n" +
                "Coffee is ready!");
    }

    public static void estimateServings() {
        int countCup = 0;
        System.out.println("Write how many ml of water the coffee machine has:");
        int containsWater = scanner.nextInt();
        System.out.println("Write how many ml of milk the coffee machine has:");
        int containsMilk = scanner.nextInt();
        System.out.println("Write how many grams of coffee beans th coffee machine has:");
        int containsBeans = scanner.nextInt();
        System.out.println("Write hom many cup of coffee you will need:");
        int needCups = scanner.nextInt();

        while (containsWater > 0 && containsMilk > 0 && containsBeans > 0) {
            if (containsWater < water || containsMilk < milk || containsBeans < coffeeBeans) {
                break;
            }
            containsWater -= water;
            containsMilk -= milk;
            containsBeans -= coffeeBeans;
            countCup++;
        }

        if (countCup == needCups) {
            System.out.println("Yes, I can make that amount of coffee");
        } else if (countCup > needCups) {
            System.out.printf("Yes, I can make that amount of coffee (and even %d more than that)", countCup - needCups);
        } else if (countCup < needCups) {
            System.out.printf("No, I can make only %d cup(s) of coffee", countCup);
        }
    }
}
