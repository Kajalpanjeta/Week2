package com.example.MVN2.Week2.Controller;

import com.example.MVN2.Week2.DTO.DepartmentDTO;
import com.example.MVN2.Week2.Entities.DepartmentEntity;
import com.example.MVN2.Week2.Services.DepartmentServices;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    public DepartmentController(DepartmentServices departmentServices) {
        this.departmentServices = departmentServices;
    }

    private final DepartmentServices departmentServices;


    @GetMapping("/{DepartmentId}")
    public DepartmentDTO getDepartmentById(@PathVariable Long DepartmentId){
        return departmentServices.getDepartmentById(DepartmentId);
    }
    @GetMapping
    public List<DepartmentDTO> getAllDepartmentId(){
        return departmentServices.getAllDepartmentId();
    }
    @PostMapping
    public DepartmentDTO createNewDepartment(@RequestBody DepartmentDTO inputDepartment){
        return departmentServices.createNewDepartment(inputDepartment);
    }
//    to change whole data
    @PutMapping(path = "/{departmentId}")
    public DepartmentDTO UpdateDepartment(@RequestBody DepartmentDTO departmentDTO,@PathVariable Long departmentId){
        return departmentServices.updateDepartment(departmentDTO,departmentId);
    }

    @DeleteMapping(path="/{departmentId}")
    public boolean DeleteDepartment(@PathVariable Long departmentId){
        return departmentServices.DeleteDepartment(departmentId);
    }

    @PatchMapping(path = "/departmentId")
    public DepartmentDTO UpdatePartialDepartment(@RequestBody Map<String,Object> updates, @PathVariable Long departmentId){
        return departmentServices.updatePartialDepartment(updates,departmentId);
    }

}
