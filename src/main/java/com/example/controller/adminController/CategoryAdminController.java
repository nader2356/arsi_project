package com.example.controller.adminController;

import com.example.dto.requestDto.CategoryRequest;
import com.example.dto.responseDto.CategoryResponse;
import com.example.service.CategoryService;
import com.example.util.Constants;
import io.swagger.annotations.Api;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(Constants.APP_ROOT_ADMIN + "/category")
@Api(tags = "(Admin) Category Management ")
@CrossOrigin("*")
public class CategoryAdminController {
    private final CategoryService categoryService;
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
    @GetMapping(value = "{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @PostMapping
    public ResponseEntity<Object> addCategory(@RequestBody @Valid CategoryRequest request) {
        categoryService.addCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Collections.singletonMap("message", "save success !"));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable(name = "id") Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Collections.singletonMap("message", "delete success !"));
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Object> updateCategory(
            @PathVariable(name = "id") Long id,
            @RequestBody @Valid CategoryRequest request) {

        categoryService.updateCategory(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Collections.singletonMap("message", "update success !!!"));
    }
}