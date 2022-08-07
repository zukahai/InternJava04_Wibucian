package com.java04.wibucian.repositories;

import com.java04.wibucian.models.OrdercfNoMapPing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrdercfNoMapPingRepository extends JpaRepository<OrdercfNoMapPing, String>, JpaSpecificationExecutor<OrdercfNoMapPing> {
    //findByGroupTableId(String groupTableId);
    @Query(value = "select * from Ordercf where idGroupTable = ?1", nativeQuery = true)
    public List<OrdercfNoMapPing> findByGroupTableId(String groupTableId);
}