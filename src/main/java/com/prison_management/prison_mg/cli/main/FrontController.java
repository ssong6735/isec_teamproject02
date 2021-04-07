package com.prison_management.prison_mg.cli.main;

import com.prison_management.prison_mg.cli.prison.controller.BailController;
import com.prison_management.prison_mg.cli.prison.controller.PrisonController;
import com.prison_management.prison_mg.cli.prison.controller.TimeController;

// 역할: 시스템 분기를 결정해주는 클래스
public class FrontController {

    // 분기 결정 기능
    public static void chooseSystem(int selection) {

        // 감옥 관리 시스템 메뉴
        switch (selection) {
            case 1: // 수감자 관리
                PrisonController prisonController = new PrisonController();
                prisonController.start();
                break;
            case 2: // 형량 관리
                TimeController timeController = new TimeController();
                timeController.start();
                break;
            case 3: // 보석금 관리
                BailController bailController = new BailController();
                bailController.start();
                break;
            case 4: // 프로그램 종료
                System.out.println("프로그램을 종료합니다.");
                System.exit(0);
            default:
                System.out.println("\n### 메뉴를 잘못 선택했습니다.");
        }

    }

}
