package com.example.BaiTech_QuanLyCV.service;

import com.example.BaiTech_QuanLyCV.dto.DotTuyenDungDTO;

import java.util.List;

public interface DotTuyenDungService {

    List<DotTuyenDungDTO> getAll();

    DotTuyenDungDTO getOne(Integer id);

    DotTuyenDungDTO save(DotTuyenDungDTO dotTuyenDungDTO);

    DotTuyenDungDTO update(DotTuyenDungDTO dotTuyenDungDTO, Integer id);

    void delete(Integer id);
}
