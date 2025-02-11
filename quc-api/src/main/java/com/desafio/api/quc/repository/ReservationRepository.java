package com.desafio.api.quc.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.desafio.api.quc.document.Reservation;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation, String> {

    @Query("{$and :[{'user.id':?0}, {$or :[{startDate : {$gte: ?1, $lte: ?2}}, {endDate : {$gte: ?1, $lte: ?2}} ] }] }")
    List<Reservation> findCustomByUserIdAndPeriod(String userId, LocalDateTime startDate, LocalDateTime endDate);

    @Query("{$and :[{'vehicle.id':?0}, {$or :[{startDate : {$gte: ?1, $lte: ?2}}, {endDate : {$gte: ?1, $lte: ?2}} ] }] }")
    List<Reservation> findCustomByVehicleIdAndPeriod(String vehicleId, LocalDateTime startDate, LocalDateTime endDate);

}