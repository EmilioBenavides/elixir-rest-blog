package com.example.restblog.web;

import com.example.restblog.data.BrandRepository;
import com.example.restblog.data.Guitar;
import com.example.restblog.data.GuitarsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
public class GuitarsController {

    private final GuitarsRepository guitarsRepository; // you have to use dependancy injection
    private final BrandRepository brandRepository;

    public GuitarsController(GuitarsRepository guitarsRepository, BrandRepository brandRepository) {
        this.guitarsRepository = guitarsRepository;
        this.brandRepository = brandRepository;
    }

    @GetMapping
    public Collection<Guitar> getAll() {
        return guitarsRepository.findAll();
    }

    @PostMapping
    public void createGuitar(@RequestBody Guitar guitar) {

        guitar.setBrand(brandRepository.findBrandByName(guitar.getBrand().getName()));

        guitarsRepository.save(guitar);
    }

    @GetMapping({"guitarID"})
    public Optional<Guitar> getOneGuitar(@PathVariable Long guitarID){
       return guitarsRepository.findById(guitarID);
    }

    @PutMapping("{guitarId}")
    public void updateGuitar(@PathVariable Long guitarId, @RequestBody Guitar newGuitar) {
        Guitar guitarToUpdate = guitarsRepository.getById(guitarId);
        guitarToUpdate.setStyle(newGuitar.getStyle());
        guitarToUpdate.setModel(newGuitar.getModel());
        guitarToUpdate.setNumberOfStrings(newGuitar.getNumberOfStrings());
        guitarToUpdate.setType(newGuitar.getType());
        guitarsRepository.save(guitarToUpdate);
    }

    @DeleteMapping("{guitarId}")
    public void deleteGuitar(@PathVariable Long guitarId){
        guitarsRepository.deleteById(guitarId);
    }

}
