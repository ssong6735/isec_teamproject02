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
            case 4:
                condition = ALL;
                System.out.println("\n## 전체정보를 검색합니다.");
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
            System.out.printf("\n============== 검색 결과(총 %d건) ==============\n", count);
            for (Prison prisoner : prisonerList) {
                System.out.println(prisoner);
            }
        } else {
            System.out.println("\n### 검색 결과가 없습니다.");
        }

    }

    // 수감자 정보 추가 기능
    private void insertPrisonerData() {

        System.out.println("\n============== 신규 수감자를 등록합니다. ==============");
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

        /*// TEST "컨트롤러를 통해 영화 정보를 입력하면 잘 저장되어야 한다."
        System.out.println("===============================");
        movieRepository.searchMovieList("", SearchCondition.ALL)
                .forEach(m -> System.out.println(m));*/

    }
}
