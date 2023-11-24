package com.example.controller.adminController;

import com.example.dto.requestDto.PasswordChangeRequest;
import com.example.dto.requestDto.UpdateUserRequest;
import com.example.dto.responseDto.UserResponse;
import com.example.dto.searchRequest.SearchAdmin;
import com.example.service.UserService;
import com.example.util.Constants;
import com.example.util.FileStorageService;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import jakarta.websocket.server.PathParam;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping(Constants.APP_ROOT_ADMIN)
@Api(tags = "(Admin) User Management ")

@CrossOrigin("*")
public class AdminController {

    public final UserService userService;
    
    private final FileStorageService fileStorageService;
    



    @PostMapping(value = "/filter")
    public ResponseEntity<List<UserResponse>> getAllUserByFilter(@RequestBody SearchAdmin request){
        return ResponseEntity.ok(userService.getAllUserByFilter(request));
    }
    @PutMapping(value = "/enable/{id}")
    public ResponseEntity<String> enableMember(@PathVariable(name = "id") Long id){
        userService.enableMember(id);
        return ResponseEntity.ok("This Account enabled with success !!!!!");
    }
    
    @PutMapping(value = "/disable/{id}")
    public ResponseEntity<String> disableMember(@PathVariable(name = "id") Long id) {
        userService.disableAccount(id);
        return ResponseEntity.ok("This Account is disabled !!!!!");
    }
    
    @DeleteMapping(value = "{id}")
    public ResponseEntity<String> deleteMember(@PathVariable(name = "id") Long id){
        userService.deleteMember(id);
        return ResponseEntity.ok("this Account is deleted");
    }
    @GetMapping(value = "me")
    public ResponseEntity<UserResponse> getUserConnected(){

        return ResponseEntity.ok(userService.getConnectedUser());
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<String> updateUser(@PathVariable(name = "id") Long id ,@RequestBody UpdateUserRequest request){
        userService.updateUser(id,request);
        return ResponseEntity.ok("update success!!");
    }
    @PutMapping
    public ResponseEntity<String> updateMe(@RequestBody UpdateUserRequest request){
        UserResponse user = userService.getConnectedUser();
        userService.updateUser(user.getId(),request);
        return ResponseEntity.ok("update success!!");
    }
    
    @GetMapping("img/{filename:.+}")
    public ResponseEntity<Resource> serveImage(@PathVariable String filename) {

    	Resource resource = userService.serveImage(filename);
     return   ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/jpeg") // Modify the content type as needed
                .body(resource);
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
    
    
    @PutMapping(value = "/password/{id}")
    public ResponseEntity<String> changeUserPassword(@RequestBody PasswordChangeRequest request,
                                                 @PathVariable Long id){

        userService.changePassword(request,id);
        return ResponseEntity.ok("Password changed successfully !!");
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