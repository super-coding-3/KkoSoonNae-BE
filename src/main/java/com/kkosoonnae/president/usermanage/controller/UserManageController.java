package com.kkosoonnae.president.usermanage.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName    : com.kkosoonnae.president.usermanage.controller
 * fileName       : UserManageController
 * author         : hagjoon
 * date           : 2024-06-13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-13        hagjoon       최초 생성
 */
@RestController
@RequestMapping("/api/president/User-Manage")
@Tag(name = "UserManageController",description = "회원 관리 API 컨트롤러")
@Slf4j
@RequiredArgsConstructor
public class UserManageController {
}
