package shop.menu;

import shop.view.JavaFxInterface;

import shop.service.TradeService;

import java.io.IOException;
import java.util.Scanner;

public class TradeMenu {
    private Scanner input;
    private TradeService tradeService;
    private MenuLoop menuLoop;

    public TradeMenu(Scanner input) {
        this.input = input;
        this.tradeService = new TradeService();
        this.menuLoop = new MenuLoop(input);
    }

    public void showTradeMenu() throws IOException {
        while (true) {
            System.out.println("\n---COMPRA E VENDA---");
            System.out.println("\n1- Nova compra \n2- Nova venda  \n3- Voltar para o início");

            System.out.print("\nOpção desejada: ");
            int tradeMenuOption = input.nextInt();
            input.nextLine();

            if (tradeMenuOption == 1) {
                JavaFxInterface.showStockWindow();
                do {
                    System.out.print("\nInsira o ID do produto: ");
                    int idInput = input.nextInt();
                    System.out.print("Insira a quantidade do produto: ");
                    int quantInput = input.nextInt();
                    tradeService.sellProductService(idInput, quantInput);

                } while (menuLoop.shouldContinue());
            } else if (tradeMenuOption == 2) {
                JavaFxInterface.showStockWindow();
                do {
                    System.out.print("\nInsira o ID do produto: ");
                    int idInput = input.nextInt();
                    System.out.print("Insira a quantidade do produto: ");
                    int quantInput = input.nextInt();
                    tradeService.buyProductService(idInput, quantInput);

                } while (menuLoop.shouldContinue());
                break;

            } else if (tradeMenuOption == 3) {
                break;

            } else {
                System.out.println("Opção inválida. Tente novamente.");

            }
        }
    }
}
