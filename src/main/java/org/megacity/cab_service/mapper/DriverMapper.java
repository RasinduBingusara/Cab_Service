package org.megacity.cab_service.mapper;

import org.megacity.cab_service.dto.driver_dto.DriverDetailDTO;
import org.megacity.cab_service.dto.driver_dto.DriverEditDTO;
import org.megacity.cab_service.dto.driver_dto.DriverInsertDTO;
import org.megacity.cab_service.dto.driver_dto.DriverResponseDTO;
import org.megacity.cab_service.model.Driver;

public class DriverMapper {

    public Driver toEntity(DriverInsertDTO dto){
        Driver driver = new Driver();
        driver.setFirstName(dto.getFirstName());
        driver.setLastName(dto.getLastName());
        driver.setEmail(dto.getEmail());
        driver.setPassword(dto.getPassword());
        driver.setContactNumber(dto.getContactNumber());
        driver.setStatus(dto.getStatus());
        driver.setDriverLicense(dto.getDriverLicense());
        driver.setNic(dto.getNic());
        driver.setAddress(dto.getAddress());
        driver.setEmploymentType(dto.getEmploymentType());
        return driver;
    }

    public DriverInsertDTO toDriverInsertDto(Driver entity){
        DriverInsertDTO dto = new DriverInsertDTO(
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getContactNumber(),
                entity.getStatus(),
                entity.getDriverLicense(),
                entity.getNic(),
                entity.getAddress(),
                entity.getEmploymentType()
        );
        return dto;
    }

    public Driver toEntity(DriverEditDTO dto){
        Driver driver = new Driver();
        driver.setFirstName(dto.getFirstName());
        driver.setLastName(dto.getLastName());
        driver.setEmail(dto.getEmail());
        driver.setPassword(dto.getPassword());
        driver.setContactNumber(dto.getContactNumber());
        driver.setStatus(dto.getStatus());
        driver.setDriverLicense(dto.getDriverLicense());
        driver.setNic(dto.getNic());
        driver.setAddress(dto.getAddress());
        return driver;
    }

    public DriverEditDTO toDriverEditDto(Driver entity){
        DriverEditDTO dto = new DriverEditDTO(
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getContactNumber(),
                entity.getStatus(),
                entity.getDriverLicense(),
                entity.getNic(),
                entity.getAddress()
        );
        return dto;
    }

    public Driver toEntity(DriverDetailDTO dto){
        Driver driver = new Driver();
        driver.setFirstName(dto.getFirstName());
        driver.setLastName(dto.getLastName());
        driver.setEmail(dto.getEmail());
        driver.setContactNumber(dto.getContactNumber());
        driver.setUserType(dto.getUserType());
        driver.setStatus(dto.getStatus());
        driver.setDriverLicense(dto.getDriverLicense());
        driver.setNic(dto.getNic());
        driver.setAddress(dto.getAddress());
        driver.setEmploymentType(dto.getEmploymentType());
        driver.setUpdatedAt(dto.getUpdatedAt());
        driver.setCreatedAt(dto.getCreatedAt());
        return driver;
    }

    public DriverDetailDTO toDriverDetailDto(Driver entity){
        DriverDetailDTO dto = new DriverDetailDTO(
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getContactNumber(),
                entity.getUserType(),
                entity.getStatus(),
                entity.getDriverLicense(),
                entity.getNic(),
                entity.getAddress(),
                entity.getEmploymentType(),
                entity.getUpdatedAt(),
                entity.getCreatedAt()
        );
        return dto;
    }

    public Driver toEntity(DriverResponseDTO dto){
        Driver driver = new Driver();
        driver.setFirstName(dto.getFirstName());
        driver.setLastName(dto.getLastName());
        driver.setEmail(dto.getEmail());
        driver.setContactNumber(dto.getContactNumber());
        driver.setStatus(dto.getStatus());
        driver.setDriverLicense(dto.getDriverLicense());
        driver.setNic(dto.getNic());
        driver.setAddress(dto.getAddress());
        return driver;
    }

    public DriverResponseDTO toDriverResponseDto(Driver entity){
        DriverResponseDTO dto = new DriverResponseDTO(
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getContactNumber(),
                entity.getStatus(),
                entity.getDriverLicense(),
                entity.getNic(),
                entity.getAddress()
        );
        return dto;
    }
}
