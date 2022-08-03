package com.java04.wibucian.services;

import com.java04.wibucian.dtos.AccountDTO;
import com.java04.wibucian.models.Account;
import com.java04.wibucian.repositories.AccountRepository;
import com.java04.wibucian.vos.AccountQueryVO;
import com.java04.wibucian.vos.AccountUpdateVO;
import com.java04.wibucian.vos.AccountVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public String save(AccountVO vO) {
        Account bean = new Account();
        BeanUtils.copyProperties(vO, bean);
        bean = accountRepository.save(bean);
        return bean.getIdAccount();
    }

    public void delete(String id) {
        accountRepository.deleteById(id);
    }

    public void update(String id, AccountUpdateVO vO) {
        Account bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        accountRepository.save(bean);
    }

    public AccountDTO getById(String id) {
        Account original = requireOne(id);
        return toDTO(original);
    }

    public Page<AccountDTO> query(AccountQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private AccountDTO toDTO(Account original) {
        AccountDTO bean = new AccountDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Account requireOne(String id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
