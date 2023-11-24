package com.example.controller.adminController;

import com.example.dto.requestDto.PartnerRequest;
import com.example.dto.responseDto.PartnerResponse;
import com.example.service.PartnerService;
import com.example.util.Constants;
import io.swagger.annotations.Api;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(Constants.APP_ROOT_ADMIN+"/partner")
@Api(tags = "(Admin) Partner Management ")
@CrossOrigin("*")
public class PartnerAdminController {

    private final PartnerService partnerService;

    @PostMapping
    public ResponseEntity<String> addPartner(@RequestBody @Valid PartnerRequest request){
        partnerService.addPartner(request);
        return ResponseEntity.ok("save success !");
    }
    @GetMapping
    public ResponseEntity<List<PartnerResponse>> getAllPartner(){
        return ResponseEntity.ok(partnerService.getAllPartner());
    }
    @GetMapping(value = "{id}")
    public ResponseEntity<PartnerResponse> getPartnerById (@PathVariable Long id){
        return ResponseEntity.ok(partnerService.getPartnerByID(id));
    }
    @PutMapping(value = "{id}")
    public ResponseEntity<String> updatePartner(
            @PathVariable Long id,
            @RequestBody @Valid PartnerRequest request){

        partnerService.updatePartner(id,request);
        return ResponseEntity.ok("update success !");
    }
    @DeleteMapping(value = "{id}")
    public ResponseEntity<String> deletePartner(@PathVariable Long id){
        partnerService.deletePartner(id);
        return ResponseEntity.ok("delete success !");
    }
    


}