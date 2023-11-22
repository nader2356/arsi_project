package com.example.controller.adminController;

import com.example.dto.requestDto.FormationRequest;
import com.example.dto.requestDto.UpdateFormationRequest;
import com.example.dto.responseDto.FormationResponse;
import com.example.service.FormationService;
import com.example.util.Constants;
import io.swagger.annotations.Api;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(Constants.APP_ROOT_ADMIN+"/formation")
@Api(tags = "(Admin) formation Management ")
@CrossOrigin("*")
public class FormationAdminController {

    private final FormationService formationService;

    @PostMapping
    public ResponseEntity<String> createFormation(@RequestBody @Valid FormationRequest formationRequest){
        formationService.addFormation(formationRequest,true);
        return ResponseEntity.ok("save success !!");
    }
    @GetMapping
    public ResponseEntity<List<FormationResponse>> getAllFormation(){
        return ResponseEntity.ok(formationService.getAllFormation());
    }
    @GetMapping(value = "/suggest")
    public ResponseEntity<List<FormationResponse>> getAllSuggestFormation(){
        return ResponseEntity.ok(formationService.getAllSuggestFormation());
    }
    @GetMapping(value = "{id}")
    public ResponseEntity<FormationResponse> getFormationById(@PathVariable Long id){
        return ResponseEntity.ok(formationService.getFormationById(id));
    }
    @PutMapping(value = "{id}")
    public ResponseEntity<String> updateFormation(@PathVariable Long id,
                                                  @RequestBody @Valid UpdateFormationRequest updateFormation){

        formationService.updateFormation(id,updateFormation);
        return ResponseEntity.ok("update success");
    }
    @DeleteMapping(value = "{id}")
    public ResponseEntity<String> deleteFormation(@PathVariable Long id){

        formationService.deleteFormation(id);
        return ResponseEntity.ok("delete success");
    }
}