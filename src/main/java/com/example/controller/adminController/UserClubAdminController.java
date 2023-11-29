package com.example.controller.adminController;

import com.example.dto.requestDto.UserClubRequest;
import com.example.dto.responseDto.UserClubResponse;
import com.example.dto.responseDto.UserResponse;
import com.example.service.UserClubService;
import com.example.service.UserService;
import com.example.util.Constants;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(Constants.APP_ROOT_ADMIN + "/userClub")
@Api(tags = "(Admin) User's Club management ")
@CrossOrigin("*")
public class UserClubAdminController {

    private final UserClubService userClubService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<String> addUserClub(@RequestBody @Valid UserClubRequest request) {
        userClubService.addUserClub(request);
        return ResponseEntity.ok("save success !!");
    }

    @GetMapping(value = "club")
    public ResponseEntity<List<UserClubResponse>> getAllClub() {
        UserResponse userDto = userService.getConnectedUser();
        return ResponseEntity.ok(userClubService.getAllClub(userDto.getId()));
    }

    @GetMapping(value = "club/{id}")
    public ResponseEntity<List<UserClubResponse>> getAllClubByUser(@PathVariable UUID id) {
        return ResponseEntity.ok(userClubService.getAllClub(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUserClub(@PathVariable Long id, @RequestBody @Valid UserClubRequest request) {
        boolean updated = userClubService.updateUserClub(id, request);
        if (updated) {
            return ResponseEntity.ok("Club updated successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserClub(@PathVariable Long id) {
        boolean deleted = userClubService.deleteUserClub(id);
        if (deleted) {
            return ResponseEntity.ok("Club deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}