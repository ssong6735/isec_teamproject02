package com.prison_management.prison_mg.cli.prison.controller;

import com.prison_management.prison_mg.cli.prison.domain.Prison;
import com.prison_management.prison_mg.cli.prison.domain.SearchCondition;
import com.prison_management.prison_mg.cli.prison.repository.MemoryPrisonRepository;
import com.prison_management.prison_mg.cli.prison.repository.PrisonRepository;
import java.util.List;
import static com.prison_management.prison_mg.cli.prison.domain.SearchCondition.*;
import static com.prison_management.prison_mg.cli.ui.AppUI.*;

// 감옥관리 시스템의 분기를 제어
public class PrisonController {

    // 수감자 저장소와 의존관계 설정
    private final PrisonRepository prisonRepository;

    public PrisonController() {
        prisonRepository = new MemoryPrisonRepository();
    }

    // 제어 시작 기능
    public void start() {
        while (true) {

            // 수감자 관리 시스템 메뉴
            prisonerManagementScreen();
            int selection = inputInteger(">>> ");

            switch (selection) {
                case 1:
                    // 수감자 추가
                    insertPrisonerData();
                    break;
                case 2:
                    // 수감자 검색
                    showSearchPrisonerData();
                    break;
                case 3:
                    // 수감자 출소
                    deletePrisonerData();
                    break;
                case 4:
                    // 수감자 전체검색
                    break;
                case 5:
                    // 첫화면으로 돌아가기
                    return;
                default:
                    System.out.println("\n# 메뉴를 다시 입력하세요!");
            }

        }
    }

    // 수감자 정보 검색 기능
    private void showSearchPrisonerData() {

        showSearchConditionScreen();
        int selection = inputInteger(">>> ");

        SearchCondition condition = ALL;
        switch (selection) {
            case 1:
                condition = NAME;
                System.out.println("\n## 이름으로 검색합니다.");
                break;
            case 2:
                condition = AREA;
                System.out.println("\n## 지역으로 검색합니다.");
                break;
            case 3:
                condition = ACHARGE;
                System.out.println("\n## 죄목으로 검색합니다.");
                break;
            default:
                System.out.println("\n## 잘못 입력했습니다.");
        }
        String keyword = "";
        if (condition != ALL) {
            keyword = inputString("# 검색어: ");
        }

        // 핵심 로직
        List<Prison> prisonerList = prisonRepository.searchPrisonerList(keyword, condition);

        int count = prisonerList.size();
        if (count > 0) {
            System.out.printf("\n============================= 검색 결과 (총 %d건) =============================\n", count);
            for (Prison prisoner : prisonerList) {
                System.out.println(prisoner);
            }
        } else {
            System.out.println("\n### 검색 결과가 없습니다.");
        }
    }

    // 수감자 정보 추가 기능
    private void insertPrisonerData() {
        System.out.println("\n========================= 신규 수감자를 등록 합니다. =========================");
        String name = inputString("# 이름: ");
        int age = inputInteger("# 나이: ");
        String area = inputString("# 지역: ");
        String aCharge = inputString("# 죄목: ");
        int jailTime = inputInteger("# 형량: ");

        // 저장할 수감자 객체
        Prison newPrison = new Prison(name, age, area, aCharge, jailTime);

        // 저장 명령
        prisonRepository.addprisoner(newPrison);
        System.out.printf("\n### [%s]의 수감자 정보가 정상 추가되었습니다.\n", newPrison.getName());
    }

    // 수감자 정보 삭제 기능 (출소)
    private void deletePrisonerData() {
        System.out.println("\n## 출소처리 할 수감자의 수감번호를 입력해주세요.");
        int delTargetNum = inputInteger(">>> ");

        // delTargetNum 을 prisonerNumber 과 비교해서 해당 수감자 찾기
        Prison prison = prisonRepository.searchPrisonOne(delTargetNum);
        if (prison != null) {
            // 수감자 정보 출력
            System.out.println(prison);
            // 삭제여부 한번더 확인
            System.out.println("\n[ 1. 출소처리 | 2. 돌아가기 ]");
            int selection = inputInteger(">>> ");
            if (selection == 1) {
                // 삭제
                prisonRepository.removePrisoner(prison.getPrisonerNumber());
                System.out.printf("\n수감번호 %d번 [%s]을(를) 출소처리 하였습니다.\n\n", prison.getPrisonerNumber(),prison.getName());
            } else {
                return;
            }
        } else {
            System.out.println("## 조회 결과가 없습니다.");
        }
    }
}
