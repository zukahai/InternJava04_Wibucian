package com.java04.wibucian.repositories;

import com.java04.wibucian.models.GroupTableNoMapPing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GroupTableNoMapPingRepository extends JpaRepository<GroupTableNoMapPing, String>, JpaSpecificationExecutor<GroupTableNoMapPing> {

}