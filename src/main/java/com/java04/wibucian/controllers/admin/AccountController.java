package com.java04.wibucian.controllers.admin;

import com.java04.wibucian.dtos.AccountDTO;
import com.java04.wibucian.services.AccountService;
import com.java04.wibucian.vos.AccountQueryVO;
import com.java04.wibucian.vos.AccountUpdateVO;
import com.java04.wibucian.vos.AccountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public String save(@Valid @RequestBody AccountVO vO) {
        return accountService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") String id) {
        accountService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") String id,
                       @Valid @RequestBody AccountUpdateVO vO) {
        accountService.update(id, vO);
    }

    @GetMapping("/{id}")
    public AccountDTO getById(@Valid @NotNull @PathVariable("id") String id) {
        return accountService.getById(id);
    }

    @GetMapping
    public Page<AccountDTO> query(@Valid AccountQueryVO vO) {
        return accountService.query(vO);
    }
}