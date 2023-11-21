package com.example.controller.adminController;

import com.example.dto.requestDto.UserCompetenceRequest;
import com.example.dto.responseDto.CompetenceResponse;
import com.example.dto.responseDto.UserCompetenceResponse;
import com.example.dto.responseDto.UserDto;
import com.example.service.UserCompetenceService;
import com.example.service.UserService;
import com.example.util.Constants;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(Constants.APP_ROOT_ADMIN+"/userCompetence")
@Api(tags = "(Admin) User's Competence management ")
public class UserCompetenceAdminController {

    private final UserCompetenceService userCompetenceService;
    private final UserService userService;


    @PostMapping
    public ResponseEntity<String> addUserCompetence(@RequestBody @Valid UserCompetenceRequest request){
        userCompetenceService.addUserCompetence(request);
        return ResponseEntity.ok("save success !!");
    }
    @GetMapping(value = "users/{id}")
    public ResponseEntity<List<UserDto>> getAllUserByCompetence(@PathVariable Long id){
        return ResponseEntity.ok(userCompetenceService.getAllUserByCompetence(id));
    }
    @GetMapping(value = "competences")
    public ResponseEntity<List<UserCompetenceResponse>> getAllCompetencesByMe(){

        UserDto userDto = userService.getConnectedUser();
        return ResponseEntity.ok(userCompetenceService.getAllCompetenceByUser(userDto.getId()));
    }
    @GetMapping(value = "competences/{id}")
    public ResponseEntity<List<UserCompetenceResponse>> getAllCompetencesByUser(@PathVariable Long id){

        return ResponseEntity.ok(userCompetenceService.getAllCompetenceByUser(id));
    }
    @PutMapping("{id}")
    public ResponseEntity<String> updateUserCompetence(
            @PathVariable Long id,
            @RequestBody @Valid UserCompetenceRequest request
    ){
        userCompetenceService.updateUserCompetence(id,request);
        return ResponseEntity.ok("update success !!");
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> updateUserCompetence(@PathVariable Long id){
        userCompetenceService.deleteUserCompetence(id);
        return ResponseEntity.ok("delete success !!");
    }


}