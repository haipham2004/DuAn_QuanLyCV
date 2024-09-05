package com.example.BaiTech_QuanLyCV.service;

import com.example.BaiTech_QuanLyCV.dto.HoatDongDTO;

import java.util.List;

public interface HoatDongService {

    List<HoatDongDTO> getAll();

    HoatDongDTO getOne(Integer id);

    HoatDongDTO save(HoatDongDTO hoatDongDTO);

    HoatDongDTO update(HoatDongDTO hoatDongDTO, Integer id);

    void delete(Integer id);
}
