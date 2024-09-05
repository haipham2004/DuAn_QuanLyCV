package com.example.BaiTech_QuanLyCV.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class HoatDongDTO {

    private Integer activityId;

    private String activityType;

    private String activityNote;

    private Boolean share = false;

    private LocalDateTime createDate;

    private Integer idNhanVien;

    private String tenNhanVien;
}
