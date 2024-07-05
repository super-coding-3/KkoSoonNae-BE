package com.kkosoonnae.president.mystore.service;

import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.kkosoonnae.common.exception.CustomException;
import com.kkosoonnae.common.exception.ErrorCode;
import com.kkosoonnae.config.s3.S3Uploader;
import com.kkosoonnae.jpa.entity.Store;
import com.kkosoonnae.jpa.entity.StoreImg;
import com.kkosoonnae.jpa.repository.StoreImgRepository;
import com.kkosoonnae.jpa.repository.StoreRepository;
import com.kkosoonnae.president.mystore.dto.AdminStoreImgRequestDto;
import com.kkosoonnae.president.mystore.dto.AdminStoreRequestDto;
import com.kkosoonnae.president.mystore.dto.AdminStoreResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * packageName    : com.kkosoonnae.president.mystore.service
 * fileName       : MyStoreService
 * author         : hagjoon
 * date           : 2024-06-13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-13        hagjoon       최초 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MyStoreService {
    private final StoreRepository storeRepository;

    private final StoreImgRepository storeImgRepository;

    private final S3Uploader s3Uploader;

    //어드민 매장 생성
    public AdminStoreResponseDto createStore(AdminStoreRequestDto adminStoreRequestDto) {
        try {
            Store store = adminStoreRequestDto.toEntity();
            if (storeRepository.existsByStoreName(store.getStoreName())) {
                log.error("해당 매장이름이 존재합니다.매장이름:{}", store.getStoreName());
                throw new CustomException(ErrorCode.STORE_SAME_NAME);
            }
            Store saveStore = storeRepository.save(store);

            AdminStoreResponseDto adminStoreResponseDto = new AdminStoreResponseDto();

            return adminStoreResponseDto.storeFromDto(saveStore);


        } catch (DataAccessException dae) {
            throw new CustomException(ErrorCode.DATABASE_ERROR);

        }
    }

    //어드민 매장이미지 등록
    @Transactional
    public AdminStoreImgRequestDto uploadImg(Integer storeNo, MultipartFile multipartFile) throws IOException {
        String s3UploadedImgUrl;

        try {
            Store store = storeRepository.findByStoreNo(storeNo);
            if (store == null) {
                throw new CustomException(ErrorCode.STORE_NOT_FOUND);
            }

            s3UploadedImgUrl = s3Uploader.upload(multipartFile, "imgUrl");

            StoreImg storeImg = StoreImg.builder()
                    .store(store)
                    .img(s3UploadedImgUrl)
                    .build();

            storeImgRepository.save(storeImg);

        } catch (IOException ie) {
            throw new CustomException(ErrorCode.FILE_UPLOAD_ERROR);

        } catch (DataAccessException dae) {
            throw new CustomException(ErrorCode.DATABASE_ERROR);

        }
        return AdminStoreImgRequestDto.builder()
                .storeNo(storeNo)
                .imgUrl(s3UploadedImgUrl)
                .build();
    }

    //어드민 매장정보 수정
    public AdminStoreResponseDto updateStore(Integer storeNo, AdminStoreRequestDto adminStoreRequestDto) {
        try {
            Store store = storeRepository.findByStoreNo(storeNo);
            if (store == null) {
                throw new CustomException(ErrorCode.STORE_NOT_FOUND);
            }

            store.updateStore(
                    adminStoreRequestDto.getStoreName(),
                    adminStoreRequestDto.getContent(),
                    adminStoreRequestDto.getPhone(),
                    adminStoreRequestDto.getLat(),
                    adminStoreRequestDto.getLon(),
                    adminStoreRequestDto.getRoadAddress(),
                    adminStoreRequestDto.getOpeningTime(),
                    adminStoreRequestDto.getClosingTime()
            );

            Store update = storeRepository.save(store);

            AdminStoreResponseDto adminStoreResponseDto = new AdminStoreResponseDto();

            return adminStoreResponseDto.storeFromDto(update);

        } catch (DataAccessException dae) {
            throw new CustomException(ErrorCode.DATABASE_ERROR);

        }
    }

    //매장삭제
    @Transactional
    public void deleteStore(Integer storeNo) {
        Store store = storeRepository.findByStoreNo(storeNo);
        if (store == null) {
            throw new CustomException(ErrorCode.STORE_NOT_FOUND);
        }

        try {
            storeImgRepository.deleteStoreImgByStore(store);

            storeRepository.delete(store);
        } catch (DataAccessException dae) {
            throw new CustomException(ErrorCode.DATABASE_ERROR);

        } catch (Exception e) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);

        }
    }

    //매장 이미지 바꾸기
    public void updateStoreImg(Integer storeNo, MultipartFile file) throws IOException {
        Store store = storeRepository.findByStoreNo(storeNo);
        if (store == null) {
            throw new CustomException(ErrorCode.STORE_NOT_FOUND);
        }
        if (file != null && !file.isEmpty()) {
            try {
                List<StoreImg> storeImgList = store.getStoreImg();
                if (storeImgList != null && !storeImgList.isEmpty()) {
                    for (StoreImg img : storeImgList) {
                        String oldFileName = img.extractFileNameFromUrl();
                        s3Uploader.updateFile(file, oldFileName, "store");
                    }
                } else {
                    //매장이미지가 없을경우 새이미지 업로드
                    String newImageUrl = s3Uploader.upload(file, "store");
                    StoreImg storeImg = new StoreImg();
                    storeImg.setImg(newImageUrl);

                    if (storeImgList == null) {
                        storeImgList = new ArrayList<>();
                    }
                    storeImgList.add(storeImg);
                    store.setStoreImages(storeImgList);

                    storeRepository.save(store);
                }
            } catch (IOException e) {
                throw new AmazonS3Exception("file = " + file.getOriginalFilename());
            }


        }
    }
}

