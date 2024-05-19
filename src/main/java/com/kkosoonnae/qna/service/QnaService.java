package com.kkosoonnae.qna.service;

import com.kkosoonnae.config.auth.PrincipalDetails;
import com.kkosoonnae.jpa.entity.CustomerBas;
import com.kkosoonnae.jpa.entity.Qna;
import com.kkosoonnae.jpa.repository.QnaQueryRepository;
import com.kkosoonnae.jpa.repository.QnaRepository;
import com.kkosoonnae.qna.dto.QnaListDto;
import com.kkosoonnae.qna.dto.QnaRqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * packageName    : com.kkosoonnae.member.service
 * fileName       : MemberService
 * author         : hagjoon
 * date           : 2024-05-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-10        hagjoon       최초 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class QnaService {

    private final QnaRepository qnaRepository;

    private final QnaQueryRepository query;


    public void createQna(PrincipalDetails principalDetails,QnaRqDto rq){
        CustomerBas bas = principalDetails.getCustomerBas();

        Qna qna = Qna.builder()
                .cstmrNo(bas)
                .title(rq.getTitle())
                .content(rq.getContent())
                .qnaState(rq.getQnaState())
                .createDt(LocalDateTime.now())
                .build();
        qnaRepository.save(qna);
    }

    public List<QnaListDto> allList() {
        PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer cstmrNo = principalDetails.getCustomerBas().getCstmrNo();

        List<QnaListDto> qnaList = query.listQna(cstmrNo);

        if(qnaList == null || qnaList.isEmpty()){
            return Collections.emptyList();
        }

        return qnaList;
    }

    public QnaListDto detailQna(Integer qnaNo){
        QnaListDto list = query.findQnaInfoById(qnaNo);
        return list;
    }

    public void deleteQna(Integer qnaNo){
        Qna qna = qnaRepository.findById(qnaNo)
                .orElseThrow(()-> new IllegalStateException("문의사항 정보를 찾을 수 없습니다."));

        if(qna != null){
            qnaRepository.deleteById(qnaNo);
        }
    }

}
