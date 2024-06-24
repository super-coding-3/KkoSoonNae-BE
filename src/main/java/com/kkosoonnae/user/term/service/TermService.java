package com.kkosoonnae.user.term.service;

import com.kkosoonnae.jpa.repository.TermQueryRepository;
import com.kkosoonnae.jpa.repository.TermRepository;
import com.kkosoonnae.user.term.dto.TermRsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * packageName    : com.kkosoonnae.user.term.service
 * fileName       : TermService
 * author         : hagjoon
 * date           : 2024-06-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-24        hagjoon       최초 생성
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TermService {

    private final TermRepository termRepository;

    private final TermQueryRepository query;



    public List<TermRsDto> getTerm(){
        List<TermRsDto> list = query.list();

        if(list.isEmpty() || list == null){
            return Collections.emptyList();
        }
        return list;
    }

}
