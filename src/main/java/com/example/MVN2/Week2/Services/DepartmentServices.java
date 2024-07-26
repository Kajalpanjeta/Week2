package com.example.MVN2.Week2.Services;
import com.example.MVN2.Week2.DTO.DepartmentDTO;
import com.example.MVN2.Week2.Entities.DepartmentEntity;
import com.example.MVN2.Week2.Repositories.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentServices {
    private final DepartmentRepository departmentRepository;

    public DepartmentServices(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    private final ModelMapper modelMapper;

    public boolean DeleteDepartment(Long departmentId) {
        boolean exists=departmentRepository.existsById(departmentId);
        if(!exists)return false;
        departmentRepository.deleteById(departmentId);
        return true;
    }
    public DepartmentDTO getDepartmentById(Long departmentId) {
        DepartmentEntity employeeEntity= departmentRepository.findById(departmentId).orElse(null);
        return modelMapper.map(departmentId,DepartmentDTO.class);
    }

    public List<DepartmentDTO> getAllDepartmentId() {
        List<DepartmentEntity> departmentEntities= departmentRepository.findAll();
        return departmentEntities
                .stream().map(departmentEntity->modelMapper.map(departmentEntity,DepartmentDTO.class))
                .collect(Collectors.toList());
    }

    public DepartmentDTO createNewDepartment(DepartmentDTO inputDepartment) {
        DepartmentEntity toSaveEntity=modelMapper.map(inputDepartment,DepartmentEntity.class);
        DepartmentEntity departmentEntity=departmentRepository.save(toSaveEntity);
        return modelMapper.map(departmentEntity,DepartmentDTO.class);

    }

    public DepartmentDTO updateDepartment(DepartmentDTO departmentDTO, Long departmentId) {
        DepartmentEntity departmentEntity=modelMapper.map(departmentDTO,DepartmentEntity.class);
        departmentEntity.setId(departmentId);
        DepartmentEntity savedDepartmentEntity=departmentRepository.save(departmentEntity);
        return modelMapper.map(savedDepartmentEntity,DepartmentDTO.class);
    }

    public DepartmentDTO updatePartialDepartment(Map<String, Object> updates, Long departmentId) {
        boolean exists=departmentRepository.existsById(departmentId);
        if(!exists)return null;
        DepartmentEntity departmentEntity=departmentRepository.findById(departmentId).get();
        updates.forEach((field,value)->{
            Field fieldToUpdated=ReflectionUtils.findField(DepartmentEntity.class,field);
            fieldToUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToUpdated,departmentEntity,value);
        });
        return modelMapper.map(departmentRepository.save(departmentEntity),DepartmentDTO.class);
    }
}
