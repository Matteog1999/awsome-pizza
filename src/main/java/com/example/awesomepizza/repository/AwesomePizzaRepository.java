package com.example.awesomepizza.repository;

import com.example.awesomepizza.model.entity.AwesomePizzaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AwesomePizzaRepository extends JpaRepository<AwesomePizzaEntity, Long> {

    @Query("SELECT ap.orderStatus FROM AwesomePizzaEntity ap WHERE ap.orderId = :orderId")
    String findByOrderId(@Param("orderId") String orderId);

    List<AwesomePizzaEntity> findByOrderByOrderDateAsc();

    @Query("SELECT ap FROM AwesomePizzaEntity ap WHERE  ap.orderStatus = 'PROCESSING'")
    AwesomePizzaEntity findProcessing();

    @Modifying
    @Query("UPDATE AwesomePizzaEntity ap set ap.orderStatus = :status WHERE ap.orderId = :orderId")
    Integer updateStatus(@Param("orderId") String orderId, @Param("status") String status);
}
