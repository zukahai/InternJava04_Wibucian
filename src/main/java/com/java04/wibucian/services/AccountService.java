package com.java04.wibucian.services;

import com.java04.wibucian.dtos.AccountDTO;
import com.java04.wibucian.models.Account;
import com.java04.wibucian.models.UpdatePassword;
import com.java04.wibucian.repositories.AccountRepository;
import com.java04.wibucian.security.CustomPasswordEncoder;
import com.java04.wibucian.vos.AccountQueryVO;
import com.java04.wibucian.vos.AccountUpdateVO;
import com.java04.wibucian.vos.AccountVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AccountService {

    private final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    @Qualifier("customPasswordEncoder")
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public String save(AccountVO vO) {
        Account bean = new Account();
        BeanUtils.copyProperties(vO, bean);
        bean = accountRepository.save(bean);
        return bean.getId();
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

    public Boolean updatePassword(UpdatePassword updatePassword) {
        String userName = updatePassword.getUserName();
        String oldPassword = updatePassword.getOldPassword();
        String password = updatePassword.getPassword();
        Optional<Account> optionalAccount = accountRepository.findAccountById(userName);
        if (optionalAccount.isPresent()) {
            Account curAccount = optionalAccount.get();
            String encodedPassword = curAccount.getPassword();
            if (bCryptPasswordEncoder.matches(oldPassword, encodedPassword)) {
                curAccount.setPassword(bCryptPasswordEncoder.encode(password));
                try {
                    accountRepository.save(curAccount);
                    return true;
                } catch (Exception e) {
                    LOGGER.error("Cannot update password : {}", e.getMessage());
                    return false;
                }
            }
        }
        return false;

    }
}