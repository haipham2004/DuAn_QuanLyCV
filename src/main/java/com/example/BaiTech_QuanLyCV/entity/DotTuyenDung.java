package com.example.BaiTech_QuanLyCV.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name="DotTuyenDung")
public class DotTuyenDung {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="dot_tuyen_dung_id")
    private Integer dotTuyenDungId;

    @Column(name = "ma_dot_tuyen_dung", nullable = false, length = 10, unique = true)
    private String maDotTuyenDung;

    @Column(name = "ten_dot_tuyen_dung", nullable = false, length = 100)
    private String tenDotTuyenDung;

    @Column(name = "noi_dung",nullable = false)
    private String noiDung;

    @Column(nullable = false)
    private LocalDate deadline;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private NhanVien nhanVien;

}
