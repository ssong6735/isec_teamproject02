package com.prison_management.prison_mg.cli.prison.controller;

import com.prison_management.prison_mg.cli.prison.repository.MemoryPrisonRepository;
import com.prison_management.prison_mg.cli.prison.repository.PrisonRepository;

import static com.prison_management.prison_mg.cli.ui.AppUI.inputInteger;
import static com.prison_management.prison_mg.cli.ui.AppUI.prisonerManagementScreen;


// 형량관리 시스템의 분기를 제어
public class JailTimeController {

    // 수감자 저장소와 의존관계 설정
    private final PrisonRepository prisonRepository;

    public JailTimeController() {
        prisonRepository = new MemoryPrisonRepository();
    }

    // 제어 시작 기능
    public void start() {
        while (true) {

            // 형량 관리 시스템 메뉴
            prisonerManagementScreen();
            int selection = inputInteger(">>> ");

            switch (selection) {
                case 1:
                    // 형량 추가
                    System.out.println("\n# 형량을 추가합니다.");
                    break;
                case 2:
                    // 형량 감소
                    System.out.println("\n# 형량을 감소합니다.");
                    break;
                case 3:
                    // 첫화면으로 돌아가기
                    return;
                default:
                    System.out.println("\n# 메뉴를 다시 입력하세요!");
            }

        }
    }


    /*// 형량 추가할 수감자 정보 검색 기능
    private void modifyJailTimeTargetData() {

        sentenceManagementScreen();
    }

    // 형량 추가 기능
    private void insertJailTimeData() {

        System.out.println("\n============================= 형량을 추가합니다. =============================");
        String plusJailTime = inputString("# 추가 형량: ");

        // 저장할 수감자 객체
        Prison newPrison = new Prison(plusJailTime);

        // 저장 명령
        prisonRepository.addprisoner(newPrison);
        System.out.printf("\n### [%s]의 수감자 정보가 정상 추가되었습니다.\n", newPrison.getName());

    }*/
}