package shop.menu;

import java.io.IOException;
import java.util.Scanner;

public class MainMenu {
    private Scanner input;
    private TradeMenu tradeMenu;
    private StockMenu stockMenu;

    public MainMenu(Scanner input) {
        this.input = input;
        this.tradeMenu = new TradeMenu(input);
        this.stockMenu = new StockMenu(input);
    }

    public void showMainMenu() throws IOException {
        while (true) {

            System.out.println("\n      -=-=-=-=Shop=-=-=-=-");
            System.out.println("\n1- Compra e venda \n2- Consultar estoque \n3- Sair do programa\n");

            System.out.print("Opção desejada: ");

            int userInput = input.nextInt();
            input.nextLine();
            System.out.println();

            if (userInput == 1) {
                tradeMenu.showTradeMenu();

            } else if (userInput == 2) {
                stockMenu.showStockMenu();

            } else if (userInput == 3) {
                break;

            } else {
                System.out.println("Opção inválida. Tente novamente.");

            }
        }
    }
}
