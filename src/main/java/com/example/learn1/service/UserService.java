package com.example.learn1.service;

import com.example.learn1.dto.AnnouncementDTO;
import com.example.learn1.dto.UserDTO;
import com.example.learn1.model.Log;
import com.example.learn1.model.User;
import com.example.learn1.model.Announcement;
import com.example.learn1.model.Vehicle;
import com.example.learn1.repository.AnnouncementRepository;
import com.example.learn1.repository.LogRepository;
import com.example.learn1.repository.UserRepository;
import com.example.learn1.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private AnnouncementRepository announcementRepository;

    // 1) Register User
    public User registerUser(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setMiddleName(userDTO.getMiddleName());
        user.setLastName(userDTO.getLastName());
        user.setEmpNumber(userDTO.getEmpNumber());
        user.setEmail(userDTO.getEmail());
        user.setContactNumber(userDTO.getContactNumber());
        user.setUserType(userDTO.getUserType());
        return userRepository.save(user);
    }

    // 5) Get User Logs
    public List<Log> getUserVehicleLogs(String userId) {
        List<Vehicle> vehicles = vehicleRepository.findByUserID(userId);

        if (vehicles.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No vehicles found for user ID: " + userId);
        }

        List<Log> allLogs = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            List<Log> logs = logRepository.findByVehicleNumber(vehicle.getVehicleNumber());
            allLogs.addAll(logs);
        }

        if (allLogs.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No logs found for vehicles of user ID: " + userId);
        }

        return allLogs;
    }

    // 6) Get Announcements
    public List<AnnouncementDTO> getAnnouncements() {
        List<Announcement> announcements = announcementRepository.findAll();
        return announcements.stream()
                .map(announcement -> {
                    AnnouncementDTO dto = new AnnouncementDTO();
                    dto.setId(announcement.getId());
                    dto.setTitle(announcement.getTitle());
                    dto.setDescription(announcement.getDescription());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
