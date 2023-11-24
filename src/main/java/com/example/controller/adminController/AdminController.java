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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@RequiredArgsConstructor
@RestController
@RequestMapping(Constants.APP_ROOT_ADMIN)
@Api(tags = "(Admin) User Management ")

@CrossOrigin("*")
public class AdminController {

    public final UserService userService;
    
    private final FileStorageService fileStorageService;
    



    @PostMapping(value = "/filter")
    public ResponseEntity<Page<UserResponse>> getAllUserByFilter(@RequestBody SearchAdmin request,
            Pageable pageable) {
       Page<UserResponse> usersPage = userService.getAllUserByFilter(request, pageable);

       return ResponseEntity.ok(usersPage);
    }
    @PutMapping(value = "/enable/{id}")
    public ResponseEntity<String> enableMember(@PathVariable(name = "id") Long id){
        userService.enableMember(id);
        return ResponseEntity.ok("This Account enabled with success !!!!!");
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
    
 
    @PutMapping(value = "/password")
    public ResponseEntity<String> changeMyPassword(@RequestBody PasswordChangeRequest request){
        UserResponse user = userService.getConnectedUser();
        userService.changePassword(request,user.getId());
        return ResponseEntity.ok("Password changed successfully !!");
    }
  
    
    
    @PutMapping(value = "/password/{id}")
    public ResponseEntity<String> changeUserPassword(@RequestBody PasswordChangeRequest request,
                                                 @PathVariable Long id){

        userService.changePassword(request,id);
        return ResponseEntity.ok("Password changed successfully !!");
    }
   
}