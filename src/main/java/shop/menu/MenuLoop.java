package shop.menu;

import java.util.Scanner;

public class MenuLoop {
    private Scanner input;

    public MenuLoop(Scanner input) {
        this.input = input;
    }

    public boolean shouldContinue() {

        while (true) {

            System.out.print("Deseja continuar?(S/N): ");
            char sellContinue = input.next().toLowerCase().charAt(0);

            if (sellContinue == 'n') {
                return false;

            } else if (sellContinue == 's') {
                return true;

            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
