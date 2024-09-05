package com.example.BaiTech_QuanLyCV.service;

import com.example.BaiTech_QuanLyCV.dto.NhanVienDTO;

import java.util.List;

public interface NhanVienService {

    List<NhanVienDTO> getAll();

    NhanVienDTO getOne(Integer id);

    NhanVienDTO save(NhanVienDTO nhanVienDTO);

    NhanVienDTO update(NhanVienDTO nhanVienDTO, Integer id);

    void delete(Integer id);
}
