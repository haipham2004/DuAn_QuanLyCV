package com.example.BaiTech_QuanLyCV.service;

import com.example.BaiTech_QuanLyCV.dto.PhongBanDTO;

import java.util.List;

public interface PhongBanService {

    List<PhongBanDTO> getAll();

    PhongBanDTO getOne(Integer id);

    PhongBanDTO save(PhongBanDTO phongBanDTO);

    PhongBanDTO update(PhongBanDTO phongBanDTO, Integer id);

    void delete(Integer id);
}
