package com.rs.employer.serviceimplements;

import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.sql.rowset.serial.SerialBlob;

import com.rs.employer.mapper.ImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rs.employer.dao.ImageRepository;
import com.rs.employer.dao.ProductRepository;
import com.rs.employer.dto.Request.ImageDTO;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.model.Image;
import com.rs.employer.model.Product;
import com.rs.employer.service.IImageService;

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
    public Instant now = Instant.now();

    @Override
    public Image addImage(Image image) {
        return imageRepository.save(image);
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    public Image updateImage(MultipartFile file, UUID id) {
        Image image = getImageByID(id);
        try {
            image.setFileType(file.getContentType());
            image.setFileName(file.getOriginalFilename());
            SerialBlob blob = new SerialBlob(file.getBytes());
            image.setImage(new SerialBlob(file.getBytes()));
            return imageRepository.save(image);
        } catch (java.io.IOException | SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL') or hasAuthority('SCOPE_PRODUCT_MANAGE')")
    public List<Image> getAllImagesById(Long productId) {
        return imageRepository.findAllByProductsId(productId);
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    public List<Image> getAllImagesAndSort(String sort) {
        return imageRepository.findAll(Sort.by(sort));
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    public Boolean deleteImage(UUID id) {
        imageRepository.findById(id).ifPresentOrElse(imageRepository::delete,
                () -> new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION));
        return true;
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    public Image getImageByID(UUID id) {
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
        List<Image> images = imageRepository.findByfileName(fileName);
        if (!images.isEmpty())
            throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
        return images;
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    public List<ImageDTO> saveImage(List<MultipartFile> file, Long productId) throws SQLException {
        Product product = productRepository.findProductById(productId);
        if (product == null) throw new AppException(ErrorCode.PRODUCT_NOTFOUND);

        List<ImageDTO> savedImageDto = new ArrayList<>();
        System.out.println("Log to service ->");
        for (MultipartFile fileItem : file) {
            try {
                Image image = new Image();
                image.setId(UUID.randomUUID());
                image.setCreateAt(now);
                image.setUpdateAt(now);
                image.setFileType(fileItem.getContentType());
                image.setFileName(fileItem.getOriginalFilename());
                image.setImage(new SerialBlob(fileItem.getBytes()));
                image.setProducts(product);
                String buildDownloadUrl = "/api/images/image/download/";
                System.out.println(image.getId());
                String downloadUrl = buildDownloadUrl + image.getId();
                System.out.println("String download is " + downloadUrl);
                image.setDownloadUrl(downloadUrl);
                Image savedImage = imageRepository.save(image);
                savedImage.setDownloadUrl(downloadUrl);
                ImageDTO dto = new ImageDTO();
                dto.setDownloadUrl(savedImage.getDownloadUrl());
                dto.setImageName(savedImage.getFileName());
                dto.setImageId(savedImage.getId());
                dto.setCreateAt(savedImage.getCreateAt());
                dto.setUpdateAt(savedImage.getUpdateAt());
                savedImageDto.add(dto);
            } catch (SQLException | java.io.IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return savedImageDto;
    }
}
