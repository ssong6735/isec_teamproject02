package com.prison_management.prison_mg.cli.main;

import com.prison_management.prison_mg.cli.ui.AppUI;
import static com.prison_management.prison_mg.cli.ui.AppUI.startScreen;

public class AppMain {

    public static void main(String[] args) {

        while (true) {

            startScreen();
            int selection = AppUI.inputInteger(">>> ");

            FrontController.chooseSystem(selection);

        }

    }

}
