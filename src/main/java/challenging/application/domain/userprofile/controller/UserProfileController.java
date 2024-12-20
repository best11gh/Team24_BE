package challenging.application.domain.userprofile.controller;

import challenging.application.global.dto.response.userprofile.HostProfileGetResponse;
import challenging.application.global.dto.response.userprofile.UserProfileGetResponse;
import challenging.application.global.dto.response.userprofile.UserProfilePutResponse;
import challenging.application.global.security.annotation.LoginMember;
import challenging.application.domain.auth.entity.Member;
import challenging.application.domain.userprofile.service.UserProfileService;
import challenging.application.global.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/userprofile")
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getUserProfile(@LoginMember Member user) {

        UserProfileGetResponse userProfileResponse = userProfileService.getUserProfile(user.getId());

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ApiResponse.successResponse(userProfileResponse));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> updateUserProfile(
        @LoginMember Member user,
        @RequestPart(value = "userNickName", required = false) String userNickName,
        @RequestPart(value = "image", required = false) MultipartFile image) {

        UserProfilePutResponse userProfileResponse = userProfileService.putUserProfile(user.getId(), userNickName,image);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ApiResponse.successResponse(userProfileResponse));
    }

    @GetMapping("/host/{uuid}")
    public ResponseEntity<ApiResponse<?>> getUserProfileByUUID(
        @PathVariable("uuid") String uuid
    ) {
        HostProfileGetResponse hostProfile = userProfileService.getHostProfile(uuid);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ApiResponse.successResponse(hostProfile));

    }
}
