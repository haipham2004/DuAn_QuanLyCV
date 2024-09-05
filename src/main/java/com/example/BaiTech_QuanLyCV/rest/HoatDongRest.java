package com.example.BaiTech_QuanLyCV.rest;

import com.example.BaiTech_QuanLyCV.service.HoatDongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("hoat-dong")
public class HoatDongRest {

    private HoatDongService hoatDongService;

    @Autowired
    public HoatDongRest(HoatDongService hoatDongService) {
        this.hoatDongService = hoatDongService;
    }

    @GetMapping("hien-thi")
    public ResponseEntity<List> getAll(){
        return ResponseEntity.ok(hoatDongService.getAll());
    }
}
