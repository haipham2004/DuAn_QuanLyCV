package com.example.BaiTech_QuanLyCV.dto;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class PhongBanDTO {

    private Integer phongBanId;

    private String maPhongBan;

    private String tenPhongBan;
}
