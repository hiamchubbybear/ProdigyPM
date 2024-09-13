package com.rs.employer.controller;

import java.sql.SQLException;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.rs.employer.serviceimplements.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import com.rs.employer.apirespone.ApiRespone;
import com.rs.employer.dto.Request.ImageDTO;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.model.Image;
import com.rs.employer.service.IImageService;


@RestController
@RequestMapping(value = "/api/images")
public class ImageController {
    @Autowired
    private ImageService imageService;
    Instant now = Instant.now();

    @GetMapping(value = "/getbyid/{id}")
    public ApiRespone<Image> getImageByID(
            @PathVariable(value = "id") UUID id) {
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

    @GetMapping(value = "/api/image/download/{imageId}")
    public ResponseEntity<Resource> downloadImage(@RequestParam(name = "imageId") UUID imageId) throws SQLException {
        Image image = imageService.getImageByID(imageId);
        ByteArrayResource resource = new ByteArrayResource(image.getImage().getBytes(1, (int) image.getImage().length()));
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(image.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +image.getFileName() + "\"")
                .body( resource);
    }

    @PostMapping(value = "/add")
    public ApiRespone<Image> addImage(@RequestBody Image image) {
        ApiRespone apiRespone = new ApiRespone<>(imageService.addImage(image));
        return apiRespone;
    }

        @PostMapping(value = "/upload/{id}")
    public ApiRespone<List<ImageDTO>> saveImage(@RequestParam List<MultipartFile> file, @PathVariable(name = "id") Long productId)
            throws SQLException {
        ApiRespone apiRespone = new ApiRespone<>(imageService.saveImage( file ,productId));
        return apiRespone;
        }


    @PutMapping(value = "/update")
    public ApiRespone<Image> updateImage(@PathVariable UUID imageid,
            @RequestBody MultipartFile images) {

            Image image = imageService.getImageByID(imageid);
            if (image != null) {
                ApiRespone<Image> imageApiRespone = new ApiRespone<>(imageService.updateImage(images, imageid));
                return imageApiRespone;
            }
            else throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
    }

    @DeleteMapping(value = "/update/{id}")
    public ApiRespone<Boolean> deleteImage(@PathVariable(value = "id") UUID id) {
        ApiRespone apiRespone = new ApiRespone<>(imageService.deleteImage(id));
        return apiRespone;
    }

    @GetMapping(value = "/all")
    public ApiRespone<Image> getAll(@PathVariable Long productId) {
        ApiRespone apiRespone = new ApiRespone<>(imageService.getAllImagesById(productId));
        return apiRespone;
    }

    @GetMapping(value = "/all/{value}")
    public ApiRespone<Image> getAllAndSort(@PathVariable(value = "value") String value) {
        ApiRespone apiRespone = new ApiRespone<>(imageService.getAllImagesAndSort(value));
        return apiRespone;
    }
}

