package com.example.controller.adminController;

import com.example.dto.requestDto.OpportunityRequest;
import com.example.dto.responseDto.OpportunityResponse;
import com.example.service.OpportunityService;
import com.example.util.Constants;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.websocket.server.PathParam;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(Constants.APP_ROOT_ADMIN + "/opportunity")
@Api(tags = "(Admin) Opportunity Management ")
@CrossOrigin("*")
public class OpportunityAdminController {

    private final OpportunityService opportunityService;

    @PostMapping
    public ResponseEntity<String> createOpportunity (@RequestBody OpportunityRequest request){
        opportunityService.createOpportunity(request);
        return ResponseEntity.ok("save success !!!");
    }
    @GetMapping
    public ResponseEntity<List<OpportunityResponse>> getAllOpportunity (){
        return ResponseEntity.ok(opportunityService.getAllOpportunity());
    }
    @GetMapping(value = "{id}")
    public ResponseEntity<OpportunityResponse> getOpportunityById (@PathVariable Long id){

        return ResponseEntity.ok(opportunityService.getOpportunityById(id));
    }
    @PutMapping(value = "{id}")
    public ResponseEntity<String> UpdateOpportunity (
            @RequestBody OpportunityRequest request,
            @PathVariable Long id){

        opportunityService.updateOpportunity(request,id);
        return ResponseEntity.ok("update success !!!!");
    }
    @DeleteMapping(value = "{id}")
    public ResponseEntity<String> deleteOpportunity (@PathVariable Long id){

        opportunityService.deleteOpportunity(id);
        return ResponseEntity.ok("delete success !!!!");
    }
    @PostMapping(value = "uploadImage/{opportunityId}")
    public ResponseEntity<String> storeImage(@PathParam("file") MultipartFile file, @PathVariable Long opportunityId){
        opportunityService.uploadImage(file,opportunityId);
        return ResponseEntity.ok("upload success");
    }

    @GetMapping("img/{filename:.+}")
    public ResponseEntity<Resource> serveImage(@PathVariable String filename) {


        Resource resource = opportunityService.serveImage(filename);
        return   ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                .body(resource);
    }


}