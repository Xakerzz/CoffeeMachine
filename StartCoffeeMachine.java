package machine;

public class StartCoffeeMachine {

    static CoffeeMachine coffeeMachine;

    public static void main(String[] args) {
        start();


    }

    public static void start() {
        coffeeMachine = new CoffeeMachine(540, 400, 120, 9, 550, true);
        CoffeeMachine.chooseOption(coffeeMachine);
    }
}
