package com.example.controller.memberController;


import com.example.dto.responseDto.MediaResponse;
import com.example.service.MediaService;
import com.example.util.Constants;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(Constants.APP_ROOT_MEMBER+"/media")
@Api(tags = "(Admin) Media Management ")
@CrossOrigin("*")
public class MediaMemberController {
    private final MediaService mediaService;

    @GetMapping
    public ResponseEntity<List<MediaResponse>> getAllMedia() {
        return ResponseEntity.ok(mediaService.getAllMedia());
    }

}