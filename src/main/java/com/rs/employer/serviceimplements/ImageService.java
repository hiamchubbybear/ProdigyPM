package com.rs.employer.serviceimplements;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import com.rs.employer.dao.ProductRepository;
import com.rs.employer.dto.Request.ImageDTO;
import com.rs.employer.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rs.employer.dao.ImageRepository;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.model.Image;
import com.rs.employer.service.IImageService;

import io.jsonwebtoken.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ImageService implements IImageService {
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ImageMapper mapper;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Image addImage(Image image) {
        return imageRepository.save(image);
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    public Image updateImage(MultipartFile file, Long id) {
        Image image = getImageByID(id);
        try {
            image.setFileType(file.getContentType());
            image.setTypeName(file.getOriginalFilename());
            SerialBlob blob = new SerialBlob(file.getBytes());
            image.setImage(new SerialBlob(file.getBytes()));
            imageRepository.save(image);
        } catch (java.io.IOException | SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    public List<Image> getAllImagesAndSort(String sort) {
        return imageRepository.findAll(Sort.by(sort));
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    public Boolean deleteImage(Long id) {
        imageRepository.findById(id).ifPresentOrElse(imageRepository::delete,
                () -> new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION));
        return true;
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    public Image getImageByID(Long id) {
        return imageRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.IMAGES_NOTFOUND));
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    public List<Image> getImageByProductID(Long productId) {
        return imageRepository.findAllByProductsId(productId);
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    public List<Image> getImageByFileType(String fileType) {
        List<Image> images = imageRepository.findByfileType(fileType);
        if (!images.isEmpty())
            throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
        else
            return images;
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    public List<Image> getImageByFileName(String fileName) {
        List<Image> images = imageRepository.findByTypeName(fileName);
        if (!images.isEmpty())
            throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
        return images;
    }

    @Override
    public List<ImageDTO> saveIgImage(List<MultipartFile> files, Long productId) throws SQLException {
        Product product = productRepository.findProductById(productId);
        List<ImageDTO> savedImageDto = new ArrayList<>();
        for (MultipartFile fileItem : files) {
            try {
                Image image = new Image();
                image.setFileType(fileItem.getContentType());
                image.setTypeName(fileItem.getOriginalFilename());
                image.setImage(new SerialBlob(fileItem.getBytes()));
                String buildDownloadUrl = "/api/images/image/download";
                String downloadUrl = buildDownloadUrl + image.getId();
                image.setDownloadUrl(downloadUrl);
                Image savedImage = imageRepository.save(image);
                savedImage.setDownloadUrl(downloadUrl);
                ImageDTO dto  = new ImageDTO();
                dto.setDownloadUrl(savedImage.getDownloadUrl());
                dto.setImageName(savedImage.getTypeName());
                dto.setImageId(savedImage.getId());
                savedImageDto.add(dto);

            } catch ( SQLException | java.io.IOException  e) {
    throw new RuntimeException(e.getMessage());
            }
        }
        return savedImageDto;
    }

}
