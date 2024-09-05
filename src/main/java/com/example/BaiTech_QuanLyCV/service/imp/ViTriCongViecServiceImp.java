package com.example.BaiTech_QuanLyCV.service.imp;

import com.example.BaiTech_QuanLyCV.dto.NhanVienDTO;
import com.example.BaiTech_QuanLyCV.dto.ViTriCongViecDTO;
import com.example.BaiTech_QuanLyCV.entity.ViTriCongViec;
import com.example.BaiTech_QuanLyCV.exception.ResourceNotfound;
import com.example.BaiTech_QuanLyCV.repository.ViTriCongViecRepository;
import com.example.BaiTech_QuanLyCV.service.ViTriCongViecService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ViTriCongViecServiceImp implements ViTriCongViecService {

    private ViTriCongViecRepository viTriCongViecRepository;

    private ModelMapper modelMapper;

    @Autowired
    public ViTriCongViecServiceImp(ViTriCongViecRepository viTriCongViecRepository, ModelMapper modelMapper) {
        this.viTriCongViecRepository = viTriCongViecRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ViTriCongViecDTO> getAll() {
        return viTriCongViecRepository.findAll().stream()
                .map((viTriCongViec) -> modelMapper.map(viTriCongViec, ViTriCongViecDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ViTriCongViecDTO getOne(Integer id) {
        return modelMapper.map(viTriCongViecRepository.findById(id).orElseThrow(()-> new ResourceNotfound("Khong ton tai vi tri cong viec co id: "+id)), ViTriCongViecDTO.class);
    }

    @Override
    public ViTriCongViecDTO save(ViTriCongViecDTO viTriCongViecDTO) {
        ViTriCongViec viTriCongViec=modelMapper.map(viTriCongViecDTO,ViTriCongViec.class);
        ViTriCongViec viTriCongViecSave=viTriCongViecRepository.save(viTriCongViec);
        return modelMapper.map(viTriCongViecSave,ViTriCongViecDTO.class);
    }

    @Override
    public ViTriCongViecDTO update(ViTriCongViecDTO viTriCongViecDTO, Integer id) {
        ViTriCongViec viTriCongViec=viTriCongViecRepository.findById(id).orElseThrow(()-> new ResourceNotfound("Khong ton tai vi tri cong viec co id: "+id));
        viTriCongViec.setMaViTri(viTriCongViecDTO.getMaViTri());
        viTriCongViec.setTenViTri(viTriCongViecDTO.getTenViTri());
        viTriCongViec.setLevel(viTriCongViecDTO.getLevel());
        ViTriCongViec viTriCongViecUpdate=viTriCongViecRepository.save(viTriCongViec);
        return modelMapper.map(viTriCongViecUpdate,ViTriCongViecDTO.class);
    }

    @Override
    public void delete(Integer id) {

    }
}
