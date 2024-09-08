package com.example.BaiTech_QuanLyCV.repository;

import com.example.BaiTech_QuanLyCV.entity.HoatDong;
import com.example.BaiTech_QuanLyCV.entity.PhongBan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface HoatDongRepository  extends JpaRepository<HoatDong,Integer> {

    @Query("SELECT hd FROM HoatDong hd WHERE " +
            "(:activityType IS NULL OR hd.activityType LIKE %:activityType%) AND " +
            "(:tenNhanVien IS NULL OR hd.nhanVien.tenNhanVien LIKE %:tenNhanVien%) AND hd.deletedAt=false ")
    Page<HoatDong> searchHoatDong(@Param("activityType") String activityType,
                                  @Param("tenNhanVien") String tenNhanVien,
                                  Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE HoatDong hd SET hd.deletedAt = true WHERE hd.activityId = :id")
    void softDeleteHoatDong(@Param("id") Integer id);
}
