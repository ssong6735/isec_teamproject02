package com.prison_management.prison_mg.cli.prison.controller;

import com.prison_management.prison_mg.cli.prison.domain.Prison;
import com.prison_management.prison_mg.cli.prison.domain.SearchCondition;
import com.prison_management.prison_mg.cli.prison.repository.MemoryPrisonRepository;
import com.prison_management.prison_mg.cli.prison.repository.PrisonRepository;

import java.util.List;
import java.util.Scanner;

import static com.prison_management.prison_mg.cli.prison.domain.SearchCondition.*;
import static com.prison_management.prison_mg.cli.prison.domain.SearchCondition.SPECIAL;
import static com.prison_management.prison_mg.cli.ui.AppUI.*;
import static com.prison_management.prison_mg.cli.ui.AppUI.showInsertConditionScreen;

//형량 관리 시스템 분기 제어
public class TimeController<time> {
    //수감자 저장소와 의존 관계 설정
    private final PrisonRepository prisonRepository;

    Scanner sc = new Scanner(System.in);

    public TimeController() {
        prisonRepository = new MemoryPrisonRepository();
    }

    // 제어 시작 기능
    public void start() {
        while (true) {

            // 형량 관리 시스템 메뉴
            sentenceManagementScreen();
            int selection = inputInteger(">>> ");

            switch (selection) {
                case 1:
                    // 형량 추가
                    insertChargePlus();
                    break;
                case 2:
                    // 형량 감소
                    insertChargeMinus();

                    break;
                case 3:
                    // 첫화면으로 돌아가기
                    return;
                default:
                    System.out.println("\n# 메뉴를 다시 입력하세요!");
            }
        }
    }

    private void insertChargeMinus() {

    }

    //형량 관리 시스템 첫번째 기능 코리아
    private void insertChargePlus() {

        showInsertConditionScreen();
        int selection = inputInteger(">>> ");

        SearchCondition condition = ALL;
        switch (selection) {
            case 1:
                condition = NAME;
                System.out.println("\n## 이름 검색후 형량 추가.");
                break;
            case 2:
                condition = SPECIAL;
                System.out.println("\n## 감형을 적용합니다");
            default:
                System.out.println("\n## 잘 못 입력했습니다. ");
        }
        String keyword = "";
        if (condition != ALL) {
            keyword = inputString("# 이름 검색: ");
        }
        //로직
        List<Prison> prisonerList = prisonRepository.searchPrisonerList(keyword, condition);
//        System.out.println("prisonerList = " + prisonerList);
        int count = prisonerList.size();
        if (count > 0) {
            System.out.println("\n==========================검색 결과==========================");
            for (Prison prison : prisonerList) {
                System.out.println(prison);

            }
            System.out.println("===========================================================");
            System.out.print("# 수감번호를 입력: ");
            int num = sc.nextInt();
            Prison prison = prisonRepository.searchPrisonOne(num);

            System.out.print("# 추가할 형량을 입력: ");
            int plusTime = sc.nextInt();
            prison.setJailTime(plusTime);
            prison.setEndJailTime(prison.getEndJailTime().plusDays(prison.getJailTime()));

            System.out.println(prison);

        } else {
            System.out.println("\n# 검색 결과가 없습니다.");
        }
    }
}
