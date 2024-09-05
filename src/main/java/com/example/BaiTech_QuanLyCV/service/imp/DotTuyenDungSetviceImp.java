package com.example.BaiTech_QuanLyCV.service.imp;

import com.example.BaiTech_QuanLyCV.dto.DotTuyenDungDTO;
import com.example.BaiTech_QuanLyCV.entity.DotTuyenDung;
import com.example.BaiTech_QuanLyCV.entity.NhanVien;
import com.example.BaiTech_QuanLyCV.exception.ResourceNotfound;
import com.example.BaiTech_QuanLyCV.repository.DotTuyenDungRepository;
import com.example.BaiTech_QuanLyCV.repository.NhanVienRepository;
import com.example.BaiTech_QuanLyCV.service.DotTuyenDungService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DotTuyenDungSetviceImp implements DotTuyenDungService {

    private DotTuyenDungRepository dotTuyenDungRepository;

    private NhanVienRepository nhanVienRepository;

    private ModelMapper modelMapper;

    @Autowired
    public DotTuyenDungSetviceImp(DotTuyenDungRepository dotTuyenDungRepository, NhanVienRepository nhanVienRepository, ModelMapper modelMapper) {
        this.dotTuyenDungRepository = dotTuyenDungRepository;
        this.nhanVienRepository = nhanVienRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<DotTuyenDungDTO> getAll() {
        return dotTuyenDungRepository.findAll().stream()
                .map((dotTuyenDung) -> modelMapper.map(dotTuyenDung, DotTuyenDungDTO.class)).collect(Collectors.toList());
    }

    @Override
    public DotTuyenDungDTO getOne(Integer id) {
        return modelMapper.map(dotTuyenDungRepository.findById(id).orElseThrow(() -> new ResourceNotfound("Khong ton tai dơt tuyen dung có id: "+id)),DotTuyenDungDTO.class);

    }

    @Override
    public DotTuyenDungDTO save(DotTuyenDungDTO dotTuyenDungDTO) {
        NhanVien nhanVien=nhanVienRepository.findById(dotTuyenDungDTO.getDotTuyenDungId()).orElseThrow(() -> new ResourceNotfound("Không tồn tại nhan vien có id: "+dotTuyenDungDTO.getDotTuyenDungId()));
        DotTuyenDung dotTuyenDung=modelMapper.map(dotTuyenDungDTO,DotTuyenDung.class);
        dotTuyenDung.setNhanVien(nhanVien);
        return modelMapper.map(dotTuyenDung,DotTuyenDungDTO.class);
    }

    @Override
    public DotTuyenDungDTO update(DotTuyenDungDTO dotTuyenDungDTO, Integer id) {
        DotTuyenDung dotTuyenDung=dotTuyenDungRepository.findById(id).orElseThrow(() -> new ResourceNotfound("Khong ton tai dơt tuyen dung có id: "+id));
        NhanVien nhanVien=nhanVienRepository.findById(dotTuyenDungDTO.getDotTuyenDungId()).orElseThrow(() -> new ResourceNotfound("Không tồn tại nhan vien có id: "+dotTuyenDungDTO.getDotTuyenDungId()));
        dotTuyenDung.setMaDotTuyenDung(dotTuyenDungDTO.getMaDotTuyenDung());
        dotTuyenDung.setTenDotTuyenDung(dotTuyenDungDTO.getTenDotTuyenDung());
        dotTuyenDung.setNoiDung(dotTuyenDungDTO.getNoiDung());
        dotTuyenDung.setDeadline(dotTuyenDungDTO.getDeadline());
        dotTuyenDung.setNhanVien(nhanVien);
        DotTuyenDung dotTuyenDungUpdate=dotTuyenDungRepository.save(dotTuyenDung);
        return modelMapper.map(dotTuyenDungUpdate,DotTuyenDungDTO.class);
    }

    @Override
    public void delete(Integer id) {

    }
}
