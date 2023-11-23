package com.example.controller.adminController;

import com.example.dto.requestDto.PartnerRequest;
import com.example.dto.responseDto.PartnerResponse;
import com.example.service.PartnerService;
import com.example.util.Constants;
import io.swagger.annotations.Api;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
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
    
    @PostMapping(value = "uploadImage/{partnerId}")
    public ResponseEntity<String> storeImage(@PathParam("file") MultipartFile file, @PathVariable Long partnerId){
        partnerService.uploadImage(file,partnerId);
        return ResponseEntity.ok("upload success");
    }

    @GetMapping("img/{filename:.+}")
    public ResponseEntity<Resource> serveImage(@PathVariable String filename) {


        Resource resource = partnerService.serveImage(filename);
        return   ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                .body(resource);
    }


}