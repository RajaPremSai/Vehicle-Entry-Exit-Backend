package com.example.learn1.repository;

import com.example.learn1.model.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends MongoRepository<Vehicle, String> {
    List<Vehicle> findByUserID(String userID);
    Optional<Vehicle> findByVehicleNumber(String vehicleNumber);
}