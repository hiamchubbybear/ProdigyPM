package com.rs.employer.service;

import java.sql.SQLException;
import java.util.List;

import com.rs.employer.dto.Request.ImageDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rs.employer.model.Image;

public interface IImageService {
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    public Image addImage(Image image);
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    public List<ImageDTO> saveImage(List<MultipartFile> file, Long productId) throws SQLException ;
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    public Image updateImage(MultipartFile file, Long imageId);
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    public List<Image> getAllImages();
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    public Boolean deleteImage(Long id);
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    public List<Image> getAllImagesAndSort(String sort);
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    public Image getImageByID(Long id);
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    public List<Image> getImageByProductID(Long productId);
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    public List<Image> getImageByFileType(String fileType);
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    public List<Image> getImageByFileName(String fileName);
}
