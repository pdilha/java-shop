package shop.application;

import javafx.application.Platform;
import shop.menu.MainMenu;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Locale.setDefault(new Locale("en", "US"));

        Platform.startup(() -> {});

        Scanner input = new Scanner(System.in);

        MainMenu mainMenu = new MainMenu(input);

        mainMenu.showMainMenu();

        input.close();

    }
}
