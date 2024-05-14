package com.kkosoonnae.store.service;

import com.kkosoonnae.jpa.entity.StoreImg;
import com.kkosoonnae.jpa.entity.Style;
import com.kkosoonnae.jpa.projection.StoreDetailViewProjection;
import com.kkosoonnae.jpa.repository.StoreImgRepository;
import com.kkosoonnae.jpa.repository.StoreRepository;
import com.kkosoonnae.jpa.repository.StyleRepository;
import com.kkosoonnae.store.dto.StoreDetailViewResponseDto;
import com.kkosoonnae.store.dto.StoreDetailWithImageResponseDto;
import com.kkosoonnae.store.dto.StoreDto;
import com.kkosoonnae.store.dto.StyleDto;
import com.kkosoonnae.store.exception.CustomException;
import com.kkosoonnae.store.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * packageName    : com.kkosoonnae.store.service
 * fileName       : StoreService
 * author         : hagjoon
 * date           : 2024-05-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-10        hagjoon       최초 생성
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class StoreServiceImpl implements StoreService {
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private StoreImgRepository storeImgRepository;
    @Autowired
    private StyleRepository styleRepository;

    @Override
    public StoreDetailWithImageResponseDto findStoreDetailWithImage(Integer storeNo) {
        Optional<StoreDetailViewProjection> storeDetailViewProjection = storeRepository.findStoreByStoreNo(storeNo);
        if (storeDetailViewProjection.isEmpty()) {
            throw new CustomException(ErrorCode.STORE_NOT_FOUND);
        }
        Optional<StoreImg> storeImg = storeImgRepository.findByStoreNo(storeNo);

        if (storeImg.isEmpty()) {
            throw new CustomException(ErrorCode.STORE_IMAGE_NOT_FOUND);
        }
        return new StoreDetailWithImageResponseDto(storeDetailViewProjection.get(),storeImg.get());
    }

    @Override
    public List<StyleDto> findStyles (Integer storeNo) {
        if (storeNo ==null) {
            throw new CustomException(ErrorCode.STORE_NOT_FOUND);
        }
        List<Style> styles = styleRepository.findByStoreNo(storeNo);
        if (styles.isEmpty()) {
            throw new CustomException(ErrorCode.STYLE_NOT_FOUND);
        }
        return StyleDto.styleToEntity(styles);
        }
    }






//    @Override
//    public StoreDetailViewResponseDto findById (Integer storeNo) {
//        Optional<StoreDetailViewProjection> storeDetailViewProjection = storeRepository.findStoreByStoreNo(storeNo);
//        if (storeDetailViewProjection.isEmpty()) {
//            throw new CustomException(ErrorCode.STORE_NOT_FOUND);
//        }
//       // return new StoreDetailViewResponseDto(List.of(storeDetailViewProjection.get()));
//
//        }

//    @Override
//    public Optional<StoreImg> findByImage(String img) {
//        return storeImgRepository.findByImg(img);
//    }
//}





//    @Override
//    public StoreDto findById(Integer storeNo, String img) {
//        Optional<StoreDetailViewProjection> storeDetailViewProjection = storeRepository.findStoreByStoreNo(storeNo);
//        if (storeDetailViewProjection.isEmpty()) {
//            throw  new CustomException(ErrorCode.STORE_NOT_FOUND);
//    }
//        Optional<StoreImg> image = storeImgRepository.findByImg(img);
//
//        return convertToDto(storeDetailViewProjection,img);
//}
//
//private StoreDto convertToDto(Optional<StoreDetailViewProjection> storeDetailViewProjection , String img) {
//      return StoreDto.StoreEntity()
//}


//    @Override
//    public StoreDto findById(Integer storeNo) {
//        Store store = storeRepository.findStoreWithImage(storeNo);
//        return StoreDto.StoreEntity(store);
//    }


//    @Override
//    public StoreDto getByStore(Integer storeNumber) {
//
//        Store store = storeRepository.findById(storeNumber)
//                .orElseThrow(()-> new CustomException(ErrorCode.Store_NOT_Found));
//        //매장이미지 같이 조회
//        StoreImg storeImg = storeImgRepository.findByStore(storeNumber);
//
//        StoreDto storeDto = new StoreDto();
//        storeDto.setStoreName(store.getStoreName());
//
//
//        String img = storeImg.getImg();


        //return



