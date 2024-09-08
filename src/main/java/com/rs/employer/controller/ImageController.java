package com.rs.employer.controller;

import java.io.ByteArrayOutputStream;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

import com.rs.employer.dto.Request.ImageDTO;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.model.Resources;
import com.rs.employer.service.IImageService;
import jakarta.annotation.Resource;
import org.hibernate.ResourceClosedException;
import org.hibernate.annotations.NotFound;
import org.hibernate.metamodel.mapping.EntityRowIdMapping;
import org.hibernate.metamodel.mapping.internal.ImmutableAttributeMappingList;
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

import com.rs.employer.apirespone.ApiRespone;
import com.rs.employer.model.Image;
import com.rs.employer.model.ImageRequest;
import com.rs.employer.serviceimplements.ImageService;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.html.HTMLParagraphElement;

import javax.xml.stream.events.EntityReference;

@RestController
@RequestMapping(value = "/api/images")
public class ImageController {
    @Autowired
    private IImageService imageService;
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
    @GetMapping(value = "/api/images/image/download/{imageId}")
    public ResponseEntity<Resource> downloadImage(@PathVariable Long imageId) throws SQLException {
        Image image = imageService.getImageByID(imageId);
        ByteArrayResource resource = new ByteArrayResource(image.getImage().getBytes(1,(int)image.getImage().length()));
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(image.getFileType())).header(HttpHeaders.CONTENT_DISPOSITION,  "attachment; filename=\"" +image.getTypeName() + "\"")
                .body((Resource) resource);
    }

    @PostMapping(value = "/add")
    public ApiRespone<Image> addImage(@RequestBody Image image) {
        ApiRespone apiRespone = new ApiRespone<>(imageService.addImage(image));
        return apiRespone;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<ApiRespone> saveImage(@RequestParam List<MultipartFile> file, @RequestParam Long productId) {
        try {
            List<ImageDTO> imageDTOS = imageService.saveIgImage(file, productId);
            return ResponseEntity.ok(new ApiRespone<>(imageDTOS));
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiRespone(ErrorCode.UNCATEGORIZE_EXCEPTION));
        }
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<ApiRespone<Image>> updateImage
            (@PathVariable(required = true) Long id, @RequestBody MultipartFile images) {
        try {
            Image image = imageService.getImageByID(id);
            if (image != null) {
                imageService.updateImage(images, id);
                return ResponseEntity.ok(new ApiRespone<>(imageService.updateImage(images, id)));
            }
        } catch (ResourceClosedException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRespone(ErrorCode.UNCATEGORIZE_EXCEPTION));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiRespone(ErrorCode.SERVER_INTERNAL_ERROR));
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
