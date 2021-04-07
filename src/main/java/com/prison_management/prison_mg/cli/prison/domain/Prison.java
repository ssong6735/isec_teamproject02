package com.prison_management.prison_mg.cli.prison.domain;

import java.time.LocalDate;

//역할, 책임: 이 클래스는 하나의 수감자 정보를 저장할 수 있어야 한다.
public class Prison {

    //기본 필드
    private int prisonerNumber;          //수감자 고유번호
    private String name;                 //이름
    private int age;                     //나이
    private String area;                 //지역
    private String aCharge;              //죄목
    private int jailTime;                //형량 (입력: 1년 단위, 출력: 1일단위로 변경)
    private LocalDate startJailTime;     //수감 시작일
    private LocalDate endJailTime;       //출소 예정일
    private boolean jailed;              //수감상태 (기본: 제소자, 출소 예정일 7일 이하면: 출소예정자)
    private int bailMoney;               //보석금

    private static int uniqueNumber;     //일련번호 (자동으로 붙는 수감자번호)

    //생성자: (이 객체가 생성될때 사용자한테 받아야 할 정보가 무엇인가?)
    public Prison(String name, int age, String area, String aCharge, int jailTime) {
        this.prisonerNumber = ++uniqueNumber;
        this.name = name;
        this.age = age;
        this.area = area;
        this.aCharge = aCharge;
        this.jailTime = jailTime; // 년 -> 일
        this.startJailTime = LocalDate.now(); // 수감시작일 = 오늘 날짜
        this.endJailTime = startJailTime.plusDays(jailTime);
    }

    //getter, setter
    public int getPrisonerNumber() {
        return prisonerNumber;
    }
    public void setPrisonerNumber(int prisonerNumber) {
        this.prisonerNumber = prisonerNumber;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }

    public String getaCharge() {
        return aCharge;
    }
    public void setaCharge(String aCharge) {
        this.aCharge = aCharge;
    }

    public int getJailTime() {
        return jailTime;
    }
    public void setJailTime(int jailTime) {
        this.jailTime = jailTime;
    }

    public LocalDate getStartJailTime() {
        return startJailTime;
    }
    public void setStartJailTime(LocalDate startJailTime) {
        this.startJailTime = startJailTime;
    }

    public LocalDate getEndJailTime() {
        return endJailTime;
    }
    public void setEndJailTime(LocalDate endJailTime) {
        this.endJailTime = endJailTime;
    }

    public boolean isJailed() {
        return jailed;
    }
    public void setJailed(boolean jailed) {
        this.jailed = jailed;
    }

    public int getBailMoney() {
        return bailMoney;
    }
    public void setBailMoney(int bailMoney) {
        this.bailMoney = bailMoney;
    }

    public static int getUniqueNumber() {
        return uniqueNumber;
    }
    public static void setUniqueNumber(int uniqueNumber) {
        Prison.uniqueNumber = uniqueNumber;
    }


    //인스턴스 메서드
    public String toString() {

        String jailed = this.jailed ? "제소자" : "출소예정자";

        return String.format("" +
//                        "============================== 수감자 정보 안내 ==============================\n" +
                "수감번호: %d, 이름: %s, 나이: %d, 지역: %s, 죄목: %s, 형량: %d일\n" +
                "수감 시작일: %s, 출소 예정일: %s, 수감상태: %s, 보석금: %d만원\n" +
                "==============================================================================",
                prisonerNumber, name, age, area, aCharge, jailTime, startJailTime, endJailTime, jailed,
                bailMoney/10000);
    }


}
