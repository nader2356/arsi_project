package com.example.controller.adminController;



import com.example.dto.requestDto.ContactRequest;
import com.example.dto.responseDto.ContactResponse;
import com.example.service.ContactService;
import com.example.util.Constants;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(Constants.APP_ROOT_ADMIN + "/contact")
@Api(tags = "(Admin) Contact Management ")
@CrossOrigin("*")
public class ContactAdminController {
    private final ContactService contactService;

    @PostMapping
    public ResponseEntity<Object> addContact (@RequestBody @Valid ContactRequest request){
        contactService.addContact(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Collections.singletonMap("message", "save success !!"));
    }
    @PutMapping(value = "{id}")
    public ResponseEntity<Object> updateContact (@PathVariable Long id,@RequestBody ContactRequest request){

        contactService.updateContact(id,request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                Collections.singletonMap("message", "update success !!"));

    }
    @GetMapping("{userId}")
    public ResponseEntity<List<ContactResponse>> getAllContactByUser (@PathVariable Long userId){
        return ResponseEntity.ok(contactService.getAllContactByUser(userId));

    }
    @DeleteMapping(value = "{id}")
    public ResponseEntity<Object> deleteContact (@PathVariable Long id){

        contactService.deleteContact(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Collections.singletonMap("message", "delete success !!"));
    }
    
}
