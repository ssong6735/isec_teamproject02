package com.prison_management.prison_mg.cli.prison.repository;


import com.prison_management.prison_mg.cli.prison.domain.Prison;
import com.prison_management.prison_mg.cli.prison.domain.SearchCondition;

import java.util.List;

//역할: 데이터 저장소 역할을 수행하는 저장소 기능의 명세
public interface PrisonRepository {

    //수감자 정보 추가
    void addprisoner(Prison prison);

    //조건별 수감자 검색
    /**
     * @param keyword 검색어
     * @param condition 검색 조건
     * @return 검색에 따른 수감자정보 리스트
     */
    List<Prison> searchPrisonerList(String keyword, SearchCondition condition);

    //특정 수감자 1명 검색
    Prison searchPrisonOne(int prisonNumber);

    //특정 수감자 삭제(출소)
    void removePrison(int serialNumber);

    //형량 추가
    List<Prison> plusJailTime(int jailTime);
    }
    //    //형량 추가 기능
//    List<Prison> plusJailTime(int jailTime);
//
//    //형량 감소 기능
//    void minusJailTime(int jailTime);




