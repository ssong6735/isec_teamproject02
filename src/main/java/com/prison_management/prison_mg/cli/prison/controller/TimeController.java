package com.prison_management.prison_mg.cli.prison.controller;

import com.prison_management.prison_mg.cli.prison.domain.Prison;
import com.prison_management.prison_mg.cli.prison.domain.SearchCondition;
import com.prison_management.prison_mg.cli.prison.repository.MemoryPrisonRepository;
import com.prison_management.prison_mg.cli.prison.repository.PrisonRepository;

import java.util.List;
import java.util.Scanner;

import static com.prison_management.prison_mg.cli.prison.domain.SearchCondition.*;
import static com.prison_management.prison_mg.cli.ui.AppUI.*;

//형량 관리 시스템 분기 제어
public class TimeController {

    //수감자 저장소와 의존 관계 설정
    private final PrisonRepository prisonRepository;
    public TimeController() {
        prisonRepository = new MemoryPrisonRepository();
    }

    //제어 시작 기능
    public void start() {
        while (true) {
            //형량 관리 시스템 메뉴
            sentenceManagementScreen();
            int selection = inputInteger(">>> ");
            switch (selection) {
                case 1://형량 추가
                    insertChargePlus();
                    break;
                case 2://형량 감형
                    insertChargeMinus();
                    break;
                case 3://첫화면으로 돌아가기
                    return;
                default:
                    System.out.println("\n# 메뉴를 다시 입력하세요!");
            }
        }
    }

    //형량 추가 기능
    private void insertChargePlus() {
        showInsertConditionScreen();
        int selection = inputInteger(">>> ");
        SearchCondition condition = ALL;
        switch (selection) {
            case 1:// 이름
                condition = NAME;
                System.out.println("\n## 이름으로 검색합니다.");
                break;
            case 2:// 지역
                condition = AREA;
                System.out.println("\n## 지역으로 검색합니다.");
                break;
            default:
                System.out.println("\n# 메뉴를 다시 입력하세요!");
        }
        String keyword = "";
        if (condition != ALL) {
            keyword = inputString("# 검색어: ");
        }
        //로직
        List<Prison> prisonerList = prisonRepository.searchPrisonerList(keyword, condition);
        int count = prisonerList.size();
        if (count > 0) {
            System.out.printf("\n=============================== 검색 결과 (총 1건) ===============================\n", count);
            for (Prison prison : prisonerList) {
                System.out.println(prison);
            }
            System.out.println("\n## 수감번호를 입력해주세요.");
            int num = inputInteger(">>> ");
            Prison prison = prisonRepository.searchPrisonOne(num);

            System.out.println("\n## 추가할 형량을 입력해주세요.");
            int plusTime = inputInteger(">>> ");
            prison.setJailTime(prison.getJailTime() + plusTime);
            prison.setEndJailTime(prison.getEndJailTime().plusDays(plusTime));
            System.out.println(prison);
        } else {
            System.out.println("\n# 검색 결과가 없습니다.");
        }
    }

    //형량 감형 기능
    private void insertChargeMinus() {
        showInsertConditionScreen();
        int selection = inputInteger(">>> ");
        SearchCondition condition = ALL;
        switch (selection) {
            case 1:// 이름
                condition = NAME;
                System.out.println("\n## 이름으로 검색합니다.");
                break;
            case 2:// 지역
                condition = AREA;
                System.out.println("\n## 지역으로 검색합니다.");
                break;
            default:
                System.out.println("\n# 메뉴를 다시 입력하세요!");
        }
        String keyword = "";
        if (condition != ALL) {
            keyword = inputString("# 검색어: ");
        }
        //로직
        List<Prison> prisonerList = prisonRepository.searchPrisonerList(keyword, condition);
        int count = prisonerList.size();
        if (count > 0) {
            System.out.printf("\n=============================== 검색 결과 (총 1건) ===============================\n", count);
            for (Prison prison : prisonerList) {
                System.out.println(prison);
            }
            System.out.println("\n## 수감번호를 입력해주세요.");
            int num = inputInteger(">>> ");
            Prison prison = prisonRepository.searchPrisonOne(num);

            System.out.println("\n## 감형할 형량을 입력해주세요.");
            int minusTime = inputInteger(">>> ");
            prison.setJailTime(prison.getJailTime() - minusTime);
            prison.setEndJailTime(prison.getEndJailTime().minusDays(minusTime));
            System.out.println(prison);
        } else {
            System.out.println("\n# 검색 결과가 없습니다.");
        }
    }
}

