package com.example.restblog.web;

import com.example.restblog.data.Category;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/categories", headers = "Accept=application/json")
public class CategoriesController {

    @GetMapping // If this is empty, it will default to the @requestmapping value.
    private void getPostsByCategory(@RequestParam String categoryName){
        Category category = new Category();

    }
}
