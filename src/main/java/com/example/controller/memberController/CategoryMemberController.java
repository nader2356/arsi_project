package com.example.controller.memberController;

import com.example.dto.responseDto.CategoryResponse;
import com.example.service.CategoryService;
import com.example.util.Constants;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(Constants.APP_ROOT_MEMBER+"/category")
@Api(tags = "(Member) Category Management ")


@CrossOrigin("*")
public class CategoryMemberController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

}