package machine;

import java.util.Scanner;

public class CoffeeMachine {

    static Scanner scanner = new Scanner(System.in);

    private int milk;
    private int water;
    private int beansOfCoffee;
    private int disposableCups;
    private int money;
    private boolean enoughResources;
    static CoffeeState coffeeState;

    public CoffeeMachine(int milk, int water, int beansOfCoffee, int disposableCups, int money, boolean enoughResources) {
        this.milk = milk;
        this.water = water;
        this.beansOfCoffee = beansOfCoffee;
        this.disposableCups = disposableCups;
        this.money = money;
        this.enoughResources = enoughResources;
        coffeeState = CoffeeState.MENU;
    }

    public int getMilk() {
        return milk;
    }

    public void setMilk(int milk) {

        if (coffeeState == CoffeeState.FILL) {
            this.milk += milk;
        } else if (coffeeState == CoffeeState.BUY) {
            this.milk -= milk;
        }
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {

        if (coffeeState == CoffeeState.FILL) {
            this.water += water;
        } else if (coffeeState == CoffeeState.BUY) {
            this.water -= water;
        }
    }

    public int getBeansOfCoffee() {
        return beansOfCoffee;
    }

    public void setBeansOfCoffee(int beansOfCoffee) {

        if (coffeeState == CoffeeState.FILL) {
            this.beansOfCoffee += beansOfCoffee;
        } else if (coffeeState == CoffeeState.BUY) {
            this.beansOfCoffee -= beansOfCoffee;
        }
    }

    public int getDisposableCups() {
        return disposableCups;
    }

    public void setDisposableCups(int disposableCups) {

        if (coffeeState == CoffeeState.FILL) {
            this.disposableCups += disposableCups;
        } else if (coffeeState == CoffeeState.BUY) {
            this.disposableCups -= disposableCups;
        }
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {

        if (coffeeState == CoffeeState.TAKE) {
            this.money = 0;
        } else if (coffeeState == CoffeeState.BUY) {
            this.money += money;
        }
    }

    public boolean isEnoughResources() {
        return enoughResources;
    }

    public void setEnoughResources(boolean enoughResources) {
        this.enoughResources = enoughResources;
    }

    public static CoffeeState getCoffeeState() {
        return coffeeState;
    }

    public static void setCoffeeState(CoffeeState coffeeState) {
        CoffeeMachine.coffeeState = coffeeState;
    }


    static void chooseOption(CoffeeMachine coffeeMachine) {
        while (getCoffeeState() != CoffeeState.EXIT) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");

            String userInput = scanner.nextLine();

            switch (userInput) {
                case "buy":
                    setCoffeeState(CoffeeState.BUY);
                    buy(coffeeMachine);
                    break;
                case "fill":
                    setCoffeeState(CoffeeState.FILL);
                    fill(coffeeMachine);
                    break;
                case "take":
                    setCoffeeState(CoffeeState.TAKE);
                    take(coffeeMachine);
                    break;
                case "remaining":
                    setCoffeeState(CoffeeState.REMAINING);
                    showWhatMachineHas(coffeeMachine);
                    break;
                case "exit":
                    setCoffeeState(CoffeeState.EXIT);
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong action");
            }
        }
    }

    private static void showWhatMachineHas(CoffeeMachine coffeeMachine) {
        System.out.printf("The coffee machine has:\n" +
                "%d ml of water\n" +
                "%d ml of milk\n" +
                "%d g of coffee beans\n" +
                "%d disposable cups\n" +
                "%d of money\n", coffeeMachine.getWater(), coffeeMachine.getMilk(), coffeeMachine.getBeansOfCoffee(), coffeeMachine.getDisposableCups(), coffeeMachine.getMoney());
        setCoffeeState(CoffeeState.MENU);
        System.out.println();
    }

    private static void take(CoffeeMachine coffeeMachine) {
        System.out.printf("I gave you %d\n", coffeeMachine.getMoney());
        System.out.println();
        coffeeMachine.setMoney(0);
        setCoffeeState(CoffeeState.MENU);
    }

    private static void fill(CoffeeMachine coffeeMachine) {
        System.out.println("Write how many ml of water you want to add:");
        coffeeMachine.setWater(scanner.nextInt());
        System.out.println("Write how many ml of milk you want to add:");
        coffeeMachine.setMilk(scanner.nextInt());
        System.out.println("Write how many grams of coffee beans you want to add:");
        coffeeMachine.setBeansOfCoffee(scanner.nextInt());
        System.out.println("Write how many disposable cups you want to add:");
        coffeeMachine.setDisposableCups(scanner.nextInt());
        scanner.nextLine();
        System.out.println();
        setCoffeeState(CoffeeState.MENU);
    }

    private static void buy(CoffeeMachine coffeeMachine) {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, 4 - back to main menu:");
        String userInput = scanner.nextLine();

        switch (userInput) {
            case "1":
                checkResources("espresso", coffeeMachine);
                if (coffeeMachine.isEnoughResources()) {
                    coffeeMachine.setWater(250);
                    coffeeMachine.setBeansOfCoffee(16);
                    coffeeMachine.setMoney(4);
                    coffeeMachine.setDisposableCups(1);
                }
                break;
            case "2":
                checkResources("latte", coffeeMachine);
                if (coffeeMachine.isEnoughResources()) {

                    coffeeMachine.setWater(350);
                    coffeeMachine.setMilk(75);
                    coffeeMachine.setBeansOfCoffee(20);
                    coffeeMachine.setMoney(7);
                    coffeeMachine.setDisposableCups(1);
                }
                break;
            case "3":
                checkResources("cappuccino", coffeeMachine);
                if (coffeeMachine.isEnoughResources()) {

                    coffeeMachine.setWater(200);
                    coffeeMachine.setMilk(100);
                    coffeeMachine.setBeansOfCoffee(12);
                    coffeeMachine.setMoney(6);
                    coffeeMachine.setDisposableCups(1);
                }
                break;
            case "4":
                setCoffeeState(CoffeeState.MENU);
                break;
            default:
                System.out.println("Wrong choose");
        }

        System.out.println();


    }

    private static void checkResources(String userInput, CoffeeMachine coffeeMachine) {
        switch (userInput) {
            case "espresso":
                if (coffeeMachine.getWater() >= 250 && coffeeMachine.getBeansOfCoffee() >= 16 && coffeeMachine.getDisposableCups() >= 1) {
                    coffeeMachine.setEnoughResources(true);
                    System.out.println("I have enough resources, making you a coffee!");
                } else {
                    coffeeMachine.setEnoughResources(false);
                    String notEnoughResource = "";

                    if (coffeeMachine.getWater() < 250) {
                        notEnoughResource = "water";
                    } else if (coffeeMachine.getBeansOfCoffee() <= 16) {
                        notEnoughResource = "coffee beans";
                    } else if (coffeeMachine.getDisposableCups() < 1) {
                        notEnoughResource = "disposable cups";
                    }

                    System.out.printf("Sorry, not enough %s!", notEnoughResource);
                }
                break;
            case "latte":
                if (coffeeMachine.getWater() >= 350 && coffeeMachine.getBeansOfCoffee() >= 20 && coffeeMachine.milk >= 75 && coffeeMachine.getDisposableCups() >= 1) {
                    coffeeMachine.setEnoughResources(true);
                    System.out.println("I have enough resources, making you a coffee!");
                } else {
                    coffeeMachine.setEnoughResources(false);
                    String notEnoughResource = "";

                    if (coffeeMachine.getWater() < 350) {
                        notEnoughResource = "water";
                    } else if (coffeeMachine.getBeansOfCoffee() <= 20) {
                        notEnoughResource = "coffee beans";
                    } else if (coffeeMachine.milk < 75) {
                        notEnoughResource = "milk";
                    } else if (coffeeMachine.getDisposableCups() < 1) {
                        notEnoughResource = "disposable cups";
                    }

                    System.out.printf("Sorry, not enough %s!", notEnoughResource);
                }
                break;
            case "cappuccino":
                if (coffeeMachine.getWater() >= 200 && coffeeMachine.getBeansOfCoffee() >= 12 && coffeeMachine.getMilk() >= 100 && coffeeMachine.getDisposableCups() >= 1) {
                    coffeeMachine.setEnoughResources(true);
                    System.out.println("I have enough resources, making you a coffee!");
                } else {
                    coffeeMachine.setEnoughResources(false);
                    String notEnoughResource = "";

                    if (coffeeMachine.getWater() < 200) {
                        notEnoughResource = "water";
                    } else if (coffeeMachine.getBeansOfCoffee() <= 12) {
                        notEnoughResource = "coffee beans";
                    } else if (coffeeMachine.milk < 100) {
                        notEnoughResource = "milk";
                    } else if (coffeeMachine.getDisposableCups() < 1) {
                        notEnoughResource = "disposable cups";
                    }

                    System.out.printf("Sorry, not enough %s!", notEnoughResource);

                }
                break;

        }


    }
}
