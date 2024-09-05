package com.example.BaiTech_QuanLyCV.rest;

import com.example.BaiTech_QuanLyCV.service.PhongBanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("phong-ban")
public class PhongBanRest {

    private PhongBanService phongBanService;

    @Autowired
    public PhongBanRest(PhongBanService phongBanService) {
        this.phongBanService = phongBanService;
    }

    @GetMapping("hien-thi")
    public ResponseEntity<List> getAll(){
        return ResponseEntity.ok(phongBanService.getAll());
    }
}
