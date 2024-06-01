package com.incident.management.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.incident.management.entity.Incident;
import com.incident.management.entity.IncidentType;
import com.incident.management.entity.Priority;
import com.incident.management.entity.Status;
import com.incident.management.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Integer userId;
    private String username;
    private String email;
    private String phoneNumber;
    private String address;
    private String pinCode;
    private String city;
    private String country;
    private Priority priority;
    private Status status;
    private IncidentType incidentType;
    private List<IncidentDTO> incidents;
    // You might exclude the incidents list from the DTO if not needed
    
    public static UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setAddress(user.getAddress());
        userDTO.setPinCode(user.getPinCode());
        userDTO.setCity(user.getCity());
        userDTO.setCountry(user.getCountry());
        userDTO.setPriority(user.getPriority());
        userDTO.setStatus(user.getStatus());
        userDTO.setIncidentType(user.getIncidentType());
  
        return userDTO;
    }
    public static User convertToEntity(UserDTO userDTO,IncidentDTO incidentDTO) {
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setAddress(userDTO.getAddress());
        user.setPinCode(userDTO.getPinCode());
        user.setCity(userDTO.getCity());
        user.setCountry(userDTO.getCountry());
        user.setPriority(userDTO.getPriority());
        user.setStatus(userDTO.getStatus());
        user.setIncidentType(userDTO.getIncidentType());
       
        return user;
    }
}
