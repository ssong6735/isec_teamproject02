package com.prison_management.prison_mg.cli.prison.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

//역할, 책임: 이 클래스는 하나의 수감자 정보를 저장할 수 있어야 한다.
public class Prison {

    //기본 필드
    private int prisonerNumber;          //수감자 고유번호
    private String name;                 //이름
    private int age;                     //나이
    private String area;                 //지역
    private String aCharge;              //죄목
    private int jailTime;                //형량
    private LocalDateTime startJailTime; //수감 시작일
    private LocalDateTime endJailTime;   //출소 예정일
    private boolean jailed;              //수감상태 (기본: 제소자, 출소 예정일 7일 이하면: 출소예정자)

    private LocalDate baseDay;
    private LocalDate targetDay;

    //일련번호 (자동으로 붙는 수감자번호)
    private static int uniqueNumber;

    //생성자: (이 객체가 생성될때 사용자한테 받아야 할 정보가 무엇인가?)
    public Prison(String name, int age, String area, String aCharge, int jailTime) {
        this.prisonerNumber = ++uniqueNumber;
        this.name = name;
        this.age = age;
        this.area = area;
        this.aCharge = aCharge;
        this.jailTime = jailTime;
        this.startJailTime = LocalDateTime.now();
        this.endJailTime = startJailTime.plusHours(jailTime);

        this.baseDay = LocalDate.now();
        this.targetDay = baseDay.plusDays(jailTime);
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

    public LocalDateTime getStartJailTime() {
        return startJailTime;
    }
    public void setStartJailTime(LocalDateTime startJailTime) {
        this.startJailTime = startJailTime;
    }

    public LocalDateTime getEndJailTime() {
        return endJailTime;
    }
    public void setEndJailTime(LocalDateTime endJailTime) {
        this.endJailTime = endJailTime;
    }

    public boolean isJailed() {
        return jailed;
    }
    public void setJailed(boolean jailed) {
        this.jailed = jailed;
    }

    public static int getUniqueNumber() {
        return uniqueNumber;
    }
    public static void setUniqueNumber(int uniqueNumber) {
        Prison.uniqueNumber = uniqueNumber;
    }


    public LocalDate getBaseDay() {
        return baseDay;
    }
    public void setBaseDay(LocalDate baseDay) {
        this.baseDay = baseDay;
    }

    public LocalDate getTargetDay() {
        return targetDay;
    }
    public void setTargetDay(LocalDate targetDay) {
        this.targetDay = targetDay;
    }



    //인스턴스 메서드
    public String toString() {

        /*if ((targetDay - LocalDate.now() < 7)) {
            String jailed = "출소예정자";
        } else {
            String jailed = "제소자";
        }*/

        String jailed = this.jailed ? "대여중" : "대여가능";

        return String.format("**** 수감자 정보 ****\n" +
                "이름: %s, 나이: %d, 지역: %s, 죄목: %s, 형량: %d," +
                "수감 시작일: %s, 출소 예정일: %s, 수감상태: %s\n" +
                "****************", name, age, area, aCharge, jailTime, startJailTime,endJailTime, jailed);
    }




}
