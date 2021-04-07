package com.prison_management.prison_mg.cli.prison.controller;

import com.prison_management.prison_mg.cli.prison.domain.Prison;
import com.prison_management.prison_mg.cli.prison.repository.MemoryPrisonRepository;
import com.prison_management.prison_mg.cli.prison.repository.PrisonRepository;
import static com.prison_management.prison_mg.cli.ui.AppUI.*;

//보석금 관리 시스템의 분기를 제어
public class BailController {

    private final PrisonRepository prisonRepository;

    public BailController() {
        prisonRepository = new MemoryPrisonRepository();
    }

    //보석금 시작 기능
    public void start() {
        while (true) {

            //보석금 관리 시스템 메뉴
            bailManagementScreen();
            int selection = inputInteger(">>>");

            switch (selection){
                case 1 :
                    //보석금 추가
                    insertjewelryData();
                    break;
                case 2 :
                    //보석금 정보 검색
                    // 보석금이 있는 수감자만 검색되게
                    showJewelryData();
                    break;
                case 3 :
                    //첫화면으로 돌아가기
                    return;
                default :
                    System.out.println("\n# 메뉴를 다시 입력하세요!");
            }
        }
    }



    // 보석금 추가 기능
    private void insertjewelryData() {
    // 수감번호 검색 -> 보석금 추가할 수감자 선택 -> 보석금 추가 -> 추가 후 정보 출력
        System.out.println("\n## 보석금을 추가할 수감자의 수감번호를 입력해주세요.");
        int delTargetNum = inputInteger(">>> ");


    }


    //보석금 정보 검색 기능
    private void showJewelryData(){
        showSearchJewelryScreen();
        int sclection = inputInteger(">>>");
        switch (sclection) {
            case 1 :
                System.out.println("\n ## 강력범죄는 1000만원에 30일이 줄어듭니다.");
                break;
            case 2 :
                System.out.println("\n ## 중범죄는 500만원에 30일이 줄어듭니다.");
                break;
            case 3 :
                System.out.println("\n ## 경범죄는 300만원에 30일이 줄어듭니다.");
                break;
            default:
        }
    }




}