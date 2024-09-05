package com.example.BaiTech_QuanLyCV.service.imp;

import com.example.BaiTech_QuanLyCV.dto.RolesDTO;
import com.example.BaiTech_QuanLyCV.repository.RoleRepository;
import com.example.BaiTech_QuanLyCV.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService {

    private RoleRepository repository;

    private ModelMapper modelMapper;

    @Autowired
    public RoleServiceImp(RoleRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RolesDTO> getAll() {
        return null;
    }

    @Override
    public RolesDTO getOne(Integer id) {
        return null;
    }

    @Override
    public RolesDTO save(RolesDTO rolesDTO) {
        return null;
    }

    @Override
    public RolesDTO update(RolesDTO rolesDTO, Integer id) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
