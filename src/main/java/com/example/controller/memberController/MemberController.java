package com.example.controller.memberController;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import com.example.dto.requestDto.PasswordChangeRequest;
import com.example.dto.requestDto.UpdateMemberRequest;
import com.example.dto.searchRequest.SearchMember;
import com.example.dto.responseDto.UserResponse;

import org.springframework.web.multipart.MultipartFile;

import jakarta.websocket.server.PathParam;
import com.example.service.UserService;

import lombok.RequiredArgsConstructor;
import com.example.util.Constants;
import io.swagger.annotations.Api;

@RequiredArgsConstructor
@RestController
@RequestMapping(Constants.APP_ROOT_MEMBER)
@Api(tags = "(Member) User Management ")


@CrossOrigin("*")
public class MemberController {
	
	public final UserService userService;

     @GetMapping()
    public ResponseEntity<List<UserResponse>> getAllMember(){
        return ResponseEntity.ok(userService.getAllMember());
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<UserResponse> getMemberById(@PathVariable(name = "id")Long id){
        return ResponseEntity.ok(userService.getMemberById(id));
    }
   @GetMapping(value = "me")
    public ResponseEntity<UserResponse> getMe(){
        return ResponseEntity.ok(userService.getConnectedUser());
    }

    @PostMapping(value = "/filter")
    public ResponseEntity<List<UserResponse>> getAllMember(@RequestBody SearchMember request){
        return ResponseEntity.ok(userService.getMemberByFilter(request));
    }
     @PutMapping
    public ResponseEntity<String> updateMe(@RequestBody UpdateMemberRequest request){
        UserResponse user = userService.getConnectedUser();
        userService.updateMember(user.getId(),request);
        return ResponseEntity.ok("update success!!");
    }

    @PutMapping(value = "/password")
    public ResponseEntity<String> changeMyPassword(@RequestBody PasswordChangeRequest request){
        UserResponse user = userService.getConnectedUser();
        userService.changePassword(request,user.getId());
        return ResponseEntity.ok("Password changed successfully !!");
    }

    @PostMapping(value = "uploadImage/{userId}")
    public ResponseEntity<String> storeImage(@PathParam("file") MultipartFile file,@PathVariable Long userId){
        userService.uploadImage(file,userId);
        return ResponseEntity.ok("upload success");
    }
    
    @GetMapping("img/{filename:.+}")
    public ResponseEntity<Resource> serveImage(@PathVariable String filename) {


        Resource resource = userService.serveImage(filename);
        return   ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/jpeg") // Modify the content type as needed
                .body(resource);
    }
    @PostMapping(value = "uploadCV/{userId}")
    public ResponseEntity<String> storeCV(@PathParam("file") MultipartFile file,@PathVariable Long userId){
        userService.uploadCv(file,userId);
        return ResponseEntity.ok("upload success");
    }

    @GetMapping("CV/{filename:.+}")
    public ResponseEntity<Resource> serveCV(@PathVariable String filename) {

        Resource resource = userService.serveCv(filename);
        return   ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "application/pdf") // Modify the content type as needed
                .body(resource);
    }


}
