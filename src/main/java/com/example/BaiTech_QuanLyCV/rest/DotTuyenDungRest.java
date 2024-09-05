package com.example.BaiTech_QuanLyCV.rest;

import com.example.BaiTech_QuanLyCV.service.DotTuyenDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("dot-tuyen-dung")
public class DotTuyenDungRest {

    private DotTuyenDungService dotTuyenDungService;

    @Autowired
    public DotTuyenDungRest(DotTuyenDungService dotTuyenDungService) {
        this.dotTuyenDungService = dotTuyenDungService;
    }

    @GetMapping("hien-thi")
    public ResponseEntity<List> getAll(){
        return ResponseEntity.ok(dotTuyenDungService.getAll());
    }
}
