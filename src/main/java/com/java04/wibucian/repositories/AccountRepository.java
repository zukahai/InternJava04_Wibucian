package com.java04.wibucian.repositories;

import com.java04.wibucian.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AccountRepository extends JpaRepository<Account, String>, JpaSpecificationExecutor<Account> {

}