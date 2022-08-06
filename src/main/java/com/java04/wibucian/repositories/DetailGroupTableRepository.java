package com.java04.wibucian.repositories;

import com.java04.wibucian.models.DetailGroupTable;
import com.java04.wibucian.models.GroupTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface DetailGroupTableRepository extends JpaRepository<DetailGroupTable, String>, JpaSpecificationExecutor<DetailGroupTable> {
    public List<DetailGroupTable> getByGroupTable(GroupTable groupTable);
}