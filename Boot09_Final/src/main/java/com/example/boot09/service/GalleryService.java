package com.example.boot09.service;

import org.springframework.ui.Model;
import com.example.boot09.dto.GalleryDto;

public interface GalleryService {
    public void addToGallery(GalleryDto dto);
    public void selectOne(Model model, int num);
    public void selectAll(Model model, int pageNum);
}
