package com.java04.wibucian.repositories;

import com.java04.wibucian.models.GroupTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GroupTableRepository extends JpaRepository<GroupTable, String>, JpaSpecificationExecutor<GroupTable> {

}