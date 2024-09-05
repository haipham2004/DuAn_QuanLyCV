package com.example.BaiTech_QuanLyCV.service;

import com.example.BaiTech_QuanLyCV.dto.HoSoDTO;

import java.util.List;

public interface HoSoService {

    List<HoSoDTO> getAll();

    HoSoDTO getOne(Integer id);

    HoSoDTO save(HoSoDTO hoSoDTO);

    HoSoDTO update(HoSoDTO hoSoDTO, Integer id);

    void delete(Integer id);
}
