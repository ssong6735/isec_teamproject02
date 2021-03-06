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
        Prison prison1 = new Prison("김제혁",35,"천안","폭행치사",1825);
        Prison prison2 = new Prison("명교수",43,"서울","사기",730);
        Prison prison3 = new Prison("김민철",52,"광주","살인",9125);
        Prison prison4 = new Prison("강철두",44,"인천","사기도박",1095);
        Prison prison5 = new Prison("이주형",23,"대전","상습절도",910);
        Prison prison6 = new Prison("유한양",26,"서울","마약복용",1825);
        Prison prison7 = new Prison("유정우",26,"대전","상해치사",4365);
        Prison prison8 = new Prison("법자",25,"청주","상습절도",545);
        Prison prison9 = new Prison("고박사",31,"부산","배임횡령",1825);
        Prison prison10 = new Prison("똘마니",24,"인천","상습폭행",7365);

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


    //수감자 추가
    @Override
    public void addprisoner(Prison prison) { // 추가
        prisonMemoryDB.put(prison.getPrisonerNumber(), prison);
    }
    //수감자 조건으로 검색
    @Override
    public List<Prison> searchPrisonerList(String keyword, SearchCondition condition) {
        // 호출부에 전달할 검색데이터 리스트
        List<Prison> results = null;
        // 검색조건 선택
        switch (condition) {
            case NAME://이름
                results = search(keyword, (k, p) -> k.equals(p.getName()));
                break;
            case AREA://지역
                results = search(keyword, (k, p) -> k.equals(p.getArea()));
                break;
            case ACHARGE://죄목
                results = search(keyword, (k, p) -> k.equals(p.getaCharge()));
                break;
            case ALL://전체
                results = search(keyword, (k, p) -> true);
                break;
            default:
                return null;
        }
        return results;
    }
    // 특정수감자 검색(수감번호)
    @Override
    public Prison searchPrisonOne(int prisonNumber) { // 1개 검색
        return prisonMemoryDB.get(prisonNumber);
    }
    // 수감자 출소
    @Override
    public void removePrisoner(int prisonNumber) { // 삭제
        prisonMemoryDB.remove(prisonNumber);
    }


    // 수감자 검색용 리스트
    private List<Prison> search(String keyword, PrisonPredicate pp) {
        List<Prison> prisonList = new ArrayList<>();
        for (int key : prisonMemoryDB.keySet()) {
            Prison prison = prisonMemoryDB.get(key);
            // 검색 키워드와 제목이 일치하는 prison 만 리스트에 추가
            if (pp.test(keyword, prison)) {
                prisonList.add(prison);
            }
        }
        return prisonList;
    }


    //형량 추가
    @Override
    public List<Prison> plusJailTime(int jailTime) {
        return Collections.singletonList(prisonMemoryDB.get(jailTime));
    }


    //보석금 추가
    @Override
    public void addBailMoney(Prison prison, int insertBailMoney) {
        prison.setBailMoney(prison.getBailMoney() + insertBailMoney);
    }


    /*// 보석금 수감자 검색용 리스트
    private List<Prison> searchAddBailMoney() {
        List<Prison> bailList = new ArrayList<>();
        for (int key : prisonMemoryDB.keySet()) {
            Prison prison = prisonMemoryDB.get(key);
            if (prison.getBailMoney() > 0) {
                bailList.add(prison);
            }
        }
        return bailList;
    }*/


    //수감자 검색 조건을 위한 인터페이스
    @FunctionalInterface // 람다식 검증
    interface PrisonPredicate {
        boolean test(String keyword, Prison prison);
    }

    
}


