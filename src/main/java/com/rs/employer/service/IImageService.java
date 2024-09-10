package com.rs.employer.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.rs.employer.dto.Request.ImageDTO;
import com.rs.employer.model.Image;

public interface IImageService {
    public Image addImage(Image image);
    public List<ImageDTO> saveImage(List<MultipartFile> file, Long productId) throws SQLException ;
    public Image updateImage(MultipartFile file, Long imageId);
    public List<Image> getAllImages();
    public Boolean deleteImage(Long id);
    public List<Image> getAllImagesAndSort(String sort);
    public Image getImageByID(Long id);
    public List<Image> getImageByProductID(Long productId);
    public List<Image> getImageByFileType(String fileType);
    public List<Image> getImageByFileName(String fileName);
}
