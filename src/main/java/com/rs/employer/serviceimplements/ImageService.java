package com.rs.employer.serviceimplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.rs.employer.dao.ImageRepository;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.model.Image;
import com.rs.employer.model.ImageRequest;
import com.rs.employer.service.IImageService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ImageService implements IImageService {
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ImageMapper mapper;

    @Override
    public Image addImage(Image image) {
        return imageRepository.save(image);
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    public Image updateImage(Long id, ImageRequest image) {
        Optional<Image> image1 = imageRepository.findById(id);
        Image image2 = mapper.toImage(image);
        return imageRepository.save(image2);
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

}
