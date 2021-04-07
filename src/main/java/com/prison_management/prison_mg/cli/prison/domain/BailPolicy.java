package com.prison_management.prison_mg.cli.prison.domain;

/*
* 보석금 정책
* 0. 보석금을 적용하면 형량이 줄어듬
* 1. 보석금은 한번만 적용 (30일)
* 2. 강력범죄 = 1000만원
* 3. 중범죄 = 500만원
* 4. 경범죄 = 300만원
* 5. 형량 최소 30일 이하 남았을때는 적용 안됨
*
*
*
*
* */

public class BailPolicy {

    //보석금 관련 상수

    private static final int STRONG_CRIMES = 10000000;
    private static final int FELONY_CRIMES = 5000000;
    private static final int MISDEMEANOR = 3000000;



}
