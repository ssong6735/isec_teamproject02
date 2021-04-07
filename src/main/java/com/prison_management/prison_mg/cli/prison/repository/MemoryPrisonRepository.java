package com.prison_management.prison_mg.cli.prison.repository;


import com.prison_management.prison_mg.cli.prison.domain.Prison;
import com.prison_management.prison_mg.cli.prison.domain.SearchCondition;

import java.util.*;

public class MemoryPrisonRepository implements PrisonRepository {

    //수감자 정보들을 저장할 자료구조
    private final static Map<Integer, Prison> prisonMemoryDB = new HashMap<>();

    static {
        insertTestData();
    }

    private static void insertTestData() {
//        Prison prison1 = new prisoner("이름","나이","지역","죄목","형량");
        Prison prison1 = new Prison("김제혁", 35, "천안", "폭행치사", 5);
        Prison prison2 = new Prison("명교수", 43, "서울", "사기", 2);
        Prison prison3 = new Prison("김민철", 52, "광주", "살인", 25);
        Prison prison4 = new Prison("강철두", 44, "인천", "사기도박", 3);
        Prison prison5 = new Prison("이주형", 23, "대전", "상습절도", 1);
        Prison prison6 = new Prison("유한양", 26, "서울", "마약복용", 1);
        Prison prison7 = new Prison("유정우", 26, "대전", "상해치사", 3);
        Prison prison8 = new Prison("법자", 25, "청주", "상습절도", 2);
        Prison prison9 = new Prison("고박사", 31, "부산", "배임횡령", 5);
        Prison prison10 = new Prison("똘마니", 24, "인천", "상습폭행", 4);
        Prison prison11 = new Prison("멸치도사", 50, "대전", "", 4);
        // prison1.setJailTime(prison1.getJailTime()+3);

        prisonMemoryDB.put(prison1.getPrisonerNumber(), prison1);
        prisonMemoryDB.put(prison2.getPrisonerNumber(), prison2);
        prisonMemoryDB.put(prison3.getPrisonerNumber(), prison3);
        prisonMemoryDB.put(prison4.getPrisonerNumber(), prison4);
        prisonMemoryDB.put(prison5.getPrisonerNumber(), prison5);
        prisonMemoryDB.put(prison6.getPrisonerNumber(), prison6);
        prisonMemoryDB.put(prison7.getPrisonerNumber(), prison7);
        prisonMemoryDB.put(prison8.getPrisonerNumber(), prison8);
        prisonMemoryDB.put(prison9.getPrisonerNumber(), prison9);
        prisonMemoryDB.put(prison10.getPrisonerNumber(), prison10);
    }


    @Override
    public void addprisoner(Prison prison) { // 추가
        prisonMemoryDB.put(prison.getPrisonerNumber(), prison);
    }


    @Override
    public List<Prison> searchPrisonerList(String keyword, SearchCondition condition) { // 조건 검색

        // 호출부에 전달할 검색데이터 리스트
        List<Prison> results = null;
        // 검색조건 선택
        switch (condition) {
            case NAME:
                results = searchByName(keyword);
                break;
            case AREA:
                results = searchByArea(keyword);
                break;
            case ACHARGE:
                results = searchByAcharge(keyword);
                break;
            case ALL:
                results = searchAll();
                break;

            /*case POSSIBLE:
                results = searchAll()
                break;*/
            default:
                return null;
        }
        return results;
    }

    //이름검색
    private List<Prison> searchByName(String keyword) {
        List<Prison> prisonList = new ArrayList<>();
        for (int key : prisonMemoryDB.keySet()) {
            Prison prison = prisonMemoryDB.get(key);

            //검색 키워드와 이름이 같은 키워드만 리스트에 추가
            if (keyword.equals(prison.getName())) {
                prisonList.add(prison);
            }
        }
        return prisonList;
    }

    //AREA 검색
    private List<Prison> searchByArea(String keyword) {
        List<Prison> prisonList = new ArrayList<>();
        for (int key : prisonMemoryDB.keySet()) {
            Prison prison = prisonMemoryDB.get(key);

            if (keyword.equals(prison.getArea())) {
                prisonList.add(prison);
            }
        }
        return prisonList;
    }


    private List<Prison> searchByAcharge(String keyword) {

        List<Prison> prisonList = new ArrayList<>();
        for (int key : prisonMemoryDB.keySet()) {
            Prison prisone = prisonMemoryDB.get(key);

            //검색 키워드와 제목이 일치하는 movie만 리스트에 추가
            if (keyword.equals(prisone.getAcharge())) {
                prisonList.add(prisone);
            }
        }
        return prisonList;
    }

    private List<Prison> searchAll() {

        List<Prison> prisoneList = new ArrayList<>();
        for (int key : prisonMemoryDB.keySet()) {
            Prison prison = prisonMemoryDB.get(key);
            prisoneList.add(prison);
        }
        return prisoneList;
    }


    @Override
    public Prison searchPrisonOne(int prisonerNumber) { // 1개 검색
        return prisonMemoryDB.get(prisonerNumber);
    }

    @Override
    public void removePrison(int prisonerNumber) { // 삭제
        prisonMemoryDB.remove(prisonerNumber);
    }

    @Override
    public List<Prison> plusJailTime(int jailTime) {
        return Collections.singletonList(prisonMemoryDB.get(jailTime));
    }

    //Predicate 는 입력값 하나 있다.
    //predicate는 결과값이 주어진 조건에 만족하는지 아닌지를 확인할때 씀.
    //
}


