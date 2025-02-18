package org.megacity.cab_service.mapper;

import org.megacity.cab_service.dto.admin_dto.AdminEditDTO;
import org.megacity.cab_service.dto.admin_dto.AdminInsertDTO;
import org.megacity.cab_service.dto.admin_dto.AdminResponseDTO;
import org.megacity.cab_service.model.Admin;

public class AdminMapper {

    public Admin toEntity(AdminInsertDTO dto){
        Admin admin = new Admin();
        admin.setFirstName(dto.getFirstName());
        admin.setLastName(dto.getLastName());
        admin.setEmail(dto.getEmail());
        admin.setPassword(dto.getPassword());
        return admin;
    }
    public AdminInsertDTO toAdminInsertDto(Admin entity){
        AdminInsertDTO dto = new AdminInsertDTO(
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPassword()
        );
        return dto;
    }

    public Admin toEntity(AdminResponseDTO dto){
        Admin entity = new Admin();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        return entity;
    }
    public AdminResponseDTO toAdminResponseDto(Admin entity){
        AdminResponseDTO dto = new AdminResponseDTO(
                entity.getFirstName(), entity.getLastName(), entity.getEmail()
        );
        return dto;
    }

    public Admin toEntity(AdminEditDTO dto){
        Admin entity = new Admin();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        return entity;
    }
    public AdminEditDTO toAdminEditDto(Admin entity){
        AdminEditDTO dto = new AdminEditDTO(
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPassword()
        );
        return dto;
    }
}
