package com.example.restblog.web;


import com.example.restblog.data.BrandRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/brands", headers = "Accept=application/json")
public class BrandsController {

    private final BrandRepository brandRepository;

    public BrandsController(BrandRepository brandRepository){
        this.brandRepository = brandRepository;
    }

    @GetMapping
    public Collection<Brand> getAll(){
        return brandRepository.findAll();
    }
}
