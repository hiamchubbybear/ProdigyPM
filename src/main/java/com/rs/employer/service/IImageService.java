package com.rs.employer.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rs.employer.model.Image;
import com.rs.employer.model.ImageRequest;

@Service
public interface IImageService {
    public Image addImage(Image image);

    public Image updateImage(Long id, ImageRequest image);

    public List<Image> getAllImages();

    public Boolean deleteImage(Long id);

    public List<Image> getAllImagesAndSort(String sort);

    public Image getImageByID(Long id);

    public List<Image> getImageByProductID(Long productId);

    public List<Image> getImageByFileType(String fileType);

    public List<Image> getImageByFileName(String fileName);
}
