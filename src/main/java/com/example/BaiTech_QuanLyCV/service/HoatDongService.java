package com.example.BaiTech_QuanLyCV.service;

import com.example.BaiTech_QuanLyCV.dto.HoatDongDTO;
import com.example.BaiTech_QuanLyCV.entity.HoatDong;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HoatDongService {

    List<HoatDongDTO> getAll();

    HoatDongDTO getOne(Integer id);

    HoatDongDTO save(HoatDongDTO hoatDongDTO);

    HoatDongDTO update(HoatDongDTO hoatDongDTO, Integer id);

    void delete(Integer id);

    Page<HoatDongDTO> searchHoatDong(@Param("activityType") String activityType,
                                  @Param("tenNhanVien") String tenNhanVien,
                                  Pageable pageable);
}
