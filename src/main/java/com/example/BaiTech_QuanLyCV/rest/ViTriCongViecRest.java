package com.example.BaiTech_QuanLyCV.rest;

import com.example.BaiTech_QuanLyCV.dto.HoatDongDTO;
import com.example.BaiTech_QuanLyCV.dto.ViTriCongViecDTO;
import com.example.BaiTech_QuanLyCV.service.ViTriCongViecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/{id}")
    public ResponseEntity<ViTriCongViecDTO> getOne(@PathVariable("id") Integer id){
        return ResponseEntity.ok(viTriCongViecService.getOne(id));
    }

    @PostMapping
    public ResponseEntity<ViTriCongViecDTO> save(@RequestBody ViTriCongViecDTO viTriCongViecDTO){
        return ResponseEntity.ok(viTriCongViecService.save(viTriCongViecDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ViTriCongViecDTO> update(@RequestBody ViTriCongViecDTO viTriCongViecDTO,@PathVariable("id") Integer id){
        return ResponseEntity.ok(viTriCongViecService.update(viTriCongViecDTO,id));
    }
}
