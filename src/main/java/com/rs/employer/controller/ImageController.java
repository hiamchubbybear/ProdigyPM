package com.rs.employer.controller;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rs.employer.apirespone.ApiRespone;
import com.rs.employer.model.Image;
import com.rs.employer.model.ImageRequest;
import com.rs.employer.serviceimplements.ImageService;

@RestController
@RequestMapping(value = "/api/image")
public class ImageController {
    @Autowired
    private ImageService imageService;
    Instant now = Instant.now();

    @GetMapping(value = "/getbyid/{id}")
    public ApiRespone<Image> getImageByID(
            @PathVariable(value = "id") Long id) {
        ApiRespone apiRespone = new ApiRespone<>(imageService.getImageByID(id));
        return apiRespone;
    }

    @GetMapping(value = "/getbyproductid/{id}")
    public ApiRespone<Image> getImageByProductID(
            @PathVariable(value = "id") Long id) {
        ApiRespone apiRespone = new ApiRespone<>(imageService.getImageByProductID(id));
        return apiRespone;
    }

    @GetMapping(value = "/getbyfiletype")
    public ApiRespone<Image> getImageByFileType(
            @RequestParam(value = "value") String filetype) {
        ApiRespone apiRespone = new ApiRespone<>(imageService.getImageByFileType(filetype));
        return apiRespone;
    }

    @GetMapping(value = "/getbytypename")
    public ApiRespone<Image> getImageByFileName(
            @RequestParam(value = "value") String filename) {
        ApiRespone apiRespone = new ApiRespone<>(imageService.getImageByFileName(filename));
        return apiRespone;
    }

    @PostMapping(value = "/add")
    public ApiRespone<Image> addImage(@RequestBody Image image) {
        ApiRespone apiRespone = new ApiRespone<>(imageService.addImage(image));
        return apiRespone;
    }

    @PutMapping(value = "/update")
    public ApiRespone<Image> updateImage(@PathVariable(required = true) Long id, @RequestBody ImageRequest image) {
        ApiRespone apiRespone = new ApiRespone<>(imageService.updateImage(id, image));
        return apiRespone;
    }

    @DeleteMapping(value = "/update/{id}")
    public ApiRespone<Image> deleteImage(@PathVariable(value = "id") Long id) {
        ApiRespone apiRespone = new ApiRespone<>(imageService.deleteImage(id));
        return apiRespone;
    }

    @GetMapping(value = "/all")
    public ApiRespone<Image> getAll() {
        ApiRespone apiRespone = new ApiRespone<>(imageService.getAllImages());
        return apiRespone;
    }

    @GetMapping(value = "/all/{value}")
    public ApiRespone<Image> getAllAndSort(@PathVariable(value = "value") String value) {
        ApiRespone apiRespone = new ApiRespone<>(imageService.getAllImagesAndSort(value));
        return apiRespone;
    }
}
