package com.example.BaiTech_QuanLyCV.service.imp;

import com.example.BaiTech_QuanLyCV.dto.HoSoDTO;
import com.example.BaiTech_QuanLyCV.dto.HoatDongDTO;
import com.example.BaiTech_QuanLyCV.entity.HoatDong;
import com.example.BaiTech_QuanLyCV.entity.NhanVien;
import com.example.BaiTech_QuanLyCV.exception.ResourceNotfound;
import com.example.BaiTech_QuanLyCV.repository.HoatDongRepository;
import com.example.BaiTech_QuanLyCV.repository.NhanVienRepository;
import com.example.BaiTech_QuanLyCV.service.HoatDongService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HoatDongServiceImp implements HoatDongService {

    private HoatDongRepository hoatDongRepository;

    private NhanVienRepository nhanVienRepository;

    private ModelMapper modelMapper;

    @Autowired
    public HoatDongServiceImp(HoatDongRepository hoatDongRepository, NhanVienRepository nhanVienRepository, ModelMapper modelMapper) {
        this.hoatDongRepository = hoatDongRepository;
        this.nhanVienRepository = nhanVienRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<HoatDongDTO> getAll() {
        return hoatDongRepository.findAll().stream()
                .map((hoatDong) -> modelMapper.map(hoatDong, HoatDongDTO.class)).collect(Collectors.toList());
    }

    @Override
    public HoatDongDTO getOne(Integer id) {

        return modelMapper.map(hoatDongRepository.findById(id).orElseThrow(() -> new ResourceNotfound("Khong ton tai hoa dong có id: "+id)),HoatDongDTO.class);
    }

    @Override
    public HoatDongDTO save(HoatDongDTO hoatDongDTO) {
        NhanVien nhanVien=nhanVienRepository.findById(hoatDongDTO.getIdNhanVien()).orElseThrow(() -> new ResourceNotfound("Khong ton tai nhan vien có id: "+hoatDongDTO.getIdNhanVien()));
        HoatDong hoatDong=modelMapper.map(hoatDongDTO,HoatDong.class);
        hoatDong.setNhanVien(nhanVien);
        HoatDong hoatDongSave=hoatDongRepository.save(hoatDong);
        return modelMapper.map(hoatDongSave,HoatDongDTO.class);
    }

    @Override
    public HoatDongDTO update(HoatDongDTO hoatDongDTO, Integer id) {
        NhanVien nhanVien=nhanVienRepository.findById(hoatDongDTO.getIdNhanVien()).orElseThrow(() -> new ResourceNotfound("Khong ton tai nhan vien có id: "+hoatDongDTO.getIdNhanVien()));
        HoatDong hoatDong=hoatDongRepository.findById(id).orElseThrow(() -> new ResourceNotfound("Khong ton tai hoa dong có id: "+id));
        hoatDong.setActivityType(hoatDongDTO.getActivityType());
        hoatDong.setActivityNote(hoatDongDTO.getActivityNote());
        hoatDong.setShare(hoatDongDTO.getShare());
        hoatDong.setCreateDate(hoatDongDTO.getCreateDate());
        hoatDong.setNhanVien(nhanVien);
        HoatDong hoatDongUpdate=hoatDongRepository.save(hoatDong);
        return modelMapper.map(hoatDongUpdate,HoatDongDTO.class);
    }

    @Override
    public void delete(Integer id) {

    }
}
