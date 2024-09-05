package com.example.BaiTech_QuanLyCV.service;

import com.example.BaiTech_QuanLyCV.dto.ViTriCongViecDTO;

import java.util.List;

public interface ViTriCongViecService {

    List<ViTriCongViecDTO> getAll();

    ViTriCongViecDTO getOne(Integer id);

    ViTriCongViecDTO save(ViTriCongViecDTO viTriCongViecDTO);

    ViTriCongViecDTO update(ViTriCongViecDTO viTriCongViecDTO, Integer id);

    void delete(Integer id);
}
