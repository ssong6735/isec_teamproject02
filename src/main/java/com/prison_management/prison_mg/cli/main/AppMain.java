package com.prison_management.prison_mg.cli.main;

import com.prison_management.prison_mg.cli.ui.AppUI;
import static com.prison_management.prison_mg.cli.ui.AppUI.startScreen;

public class AppMain {

    public static void main(String[] args) {

        // 감옥관리 시스템 시작메뉴
        while (true) {
            startScreen();
            int selection = AppUI.inputInteger(">>> ");
            FrontController.chooseSystem(selection);
        }

    }

}
