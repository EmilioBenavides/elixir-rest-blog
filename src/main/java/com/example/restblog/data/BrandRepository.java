package com.example.restblog.data;

import com.example.restblog.web.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    Brand findBrandByName(String name);
}
