package com.example.BaiTech_QuanLyCV.rest;

import com.example.BaiTech_QuanLyCV.service.ViTriCongViecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("vi-tri-cong-viec")
public class ViTriCongViecRest {

    private ViTriCongViecService viTriCongViecService;

    @Autowired
    public ViTriCongViecRest(ViTriCongViecService viTriCongViecService) {
        this.viTriCongViecService = viTriCongViecService;
    }

    @GetMapping("hien-thi")
    public ResponseEntity<List> getAll(){
        return ResponseEntity.ok(viTriCongViecService.getAll());
    }
}
