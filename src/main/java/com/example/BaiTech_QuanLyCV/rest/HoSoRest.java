package com.example.BaiTech_QuanLyCV.rest;

import com.example.BaiTech_QuanLyCV.service.HoSoService;
import com.example.BaiTech_QuanLyCV.service.HoatDongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("ho-so")
public class HoSoRest {

    private HoSoService hoSoService;

    @Autowired
    public HoSoRest(HoSoService hoSoService) {
        this.hoSoService = hoSoService;
    }

    @GetMapping("hien-thi")
    public ResponseEntity<List> getAll(){
        return ResponseEntity.ok(hoSoService.getAll());
    }
}
