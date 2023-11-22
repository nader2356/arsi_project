package com.example.controller.memberController;



import com.example.dto.responseDto.PartnerResponse;
import com.example.service.PartnerService;
import com.example.util.Constants;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(Constants.APP_ROOT_MEMBER+"/partner")
@Api(tags = "(Member) Partner Management ")
@CrossOrigin("*")
public class PartnerMemberController {

    private final PartnerService partnerService;


    @GetMapping
    public ResponseEntity<List<PartnerResponse>> getAllPartner(){
        return ResponseEntity.ok(partnerService.getAllPartner());
    }
    @GetMapping(value = "{id}")
    public ResponseEntity<PartnerResponse> getPartnerById (@PathVariable Long id){
        return ResponseEntity.ok(partnerService.getPartnerByID(id));
    }


}
