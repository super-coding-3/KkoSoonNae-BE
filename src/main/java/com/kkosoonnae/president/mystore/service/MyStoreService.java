package com.kkosoonnae.president.mystore.service;

import com.kkosoonnae.common.exception.CustomException;
import com.kkosoonnae.common.exception.ErrorCode;
import com.kkosoonnae.config.s3.S3Uploader;
import com.kkosoonnae.jpa.entity.Store;
import com.kkosoonnae.jpa.entity.StoreImg;
import com.kkosoonnae.jpa.repository.StoreImgRepository;
import com.kkosoonnae.jpa.repository.StoreRepository;
import com.kkosoonnae.president.mystore.dto.AdminStoreImgRequestDto;
import com.kkosoonnae.president.mystore.dto.AdminStoreRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


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
    public AdminStoreRequestDto createStore(AdminStoreRequestDto adminStoreRequestDto) {
        try {
            Store store = adminStoreRequestDto.toEntity();
            if (storeRepository.existsByStoreName(store.getStoreName())) {
                log.error("해당 매장이름이 존재합니다.매장이름:{}", store.getStoreName());
                throw new CustomException(ErrorCode.STORE_SAME_NAME);
            }
            Store saveStore = storeRepository.save(store);

            return adminStoreRequestDto.storeFromDto(saveStore);



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
    public AdminStoreRequestDto updateStore(Integer storeNo, AdminStoreRequestDto adminStoreRequestDto) {
        try {
            Store store = storeRepository.findByStoreNo(storeNo);
            if (store == null) {
                throw new CustomException(ErrorCode.STORE_NOT_FOUND);
            }

            adminStoreRequestDto.updateEntity(store);

            Store updateStore = storeRepository.save(store);

            return adminStoreRequestDto.storeFromDto(updateStore);

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

    public AdminStoreImgRequestDto updateStoreImg(Integer storeNo, MultipartFile newFile) throws IOException {
        Store store = storeRepository.findByStoreNo(storeNo);
        if (store == null) {
            throw new CustomException(ErrorCode.STORE_NOT_FOUND);
        }
        String s3updateImg = s3Uploader.updateFile(newFile, "", "");

        StoreImg storeImg = new StoreImg();
        storeImg.setImg(s3updateImg);

        AdminStoreImgRequestDto adminStoreImgRequestDto = new AdminStoreImgRequestDto();
        adminStoreImgRequestDto.updateImgEntity(storeImg);

        return adminStoreImgRequestDto;

    }
}

