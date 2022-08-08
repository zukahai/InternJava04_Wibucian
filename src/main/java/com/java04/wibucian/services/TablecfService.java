package com.java04.wibucian.services;

import com.java04.wibucian.dtos.TablecfDTO;
import com.java04.wibucian.models.Tablecf;
import com.java04.wibucian.models.TypeTable;
import com.java04.wibucian.repositories.TablecfRepository;
import com.java04.wibucian.repositories.TypeTableRepository;
import com.java04.wibucian.vos.TablecfQueryVO;
import com.java04.wibucian.vos.TablecfUpdateVO;
import com.java04.wibucian.vos.TablecfVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TablecfService {

    @Autowired
    private TablecfRepository tablecfRepository;
    private TypeTableRepository typeTableRepository;

    public TablecfService(TypeTableRepository typeTableRepository) {
        this.typeTableRepository = typeTableRepository;
    }

    public String save(TablecfVO vO) {
        Tablecf bean = new Tablecf();
        BeanUtils.copyProperties(vO, bean);
        bean = tablecfRepository.save(bean);
        return bean.getId();
    }

    public String save(TablecfVO vO, String idTypeTable) {
        Tablecf tablecf = new Tablecf();
        BeanUtils.copyProperties(vO, tablecf);
        TypeTable typeTable = typeTableRepository.findById(idTypeTable).orElse(null);
        tablecf.setTypeTable(typeTable);
        tablecf = tablecfRepository.save(tablecf);
        return tablecf.getId();
    }

    public void delete(String id) {
        tablecfRepository.deleteById(id);
    }

    public void update(String id, TablecfUpdateVO vO) {
        Tablecf bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        tablecfRepository.save(bean);
    }

    public void update(String id, TablecfUpdateVO vO, String idTypeTable) {
        Tablecf bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);

        TypeTable typeTable = typeTableRepository.findById(idTypeTable).orElse(null);
        bean.setTypeTable(typeTable);

        tablecfRepository.save(bean);
    }

    public TablecfDTO getById(String id) {
        Tablecf original = requireOne(id);
        return toDTO(original);
    }
    public Tablecf findById(String id) {
        return requireOne(id);
    }

    public Page<TablecfDTO> query(TablecfQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private TablecfDTO toDTO(Tablecf original) {
        TablecfDTO bean = new TablecfDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Tablecf requireOne(String id) {
        return tablecfRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    public List<Tablecf> findAll(){
        return tablecfRepository.findAll();
    }

    public List<Tablecf> findAllHaiZuka(Pageable pageable) {
        int start = pageable.getPageNumber() * pageable.getPageSize();
        List<Tablecf> all = tablecfRepository.findAll();
        List<Tablecf> answer = new ArrayList();

        for (int i = start; i < start + pageable.getPageSize() && i < all.size(); i++) {
            answer.add(all.get(i));
        }
        return answer;
    }

    public int getTotalPage(int limit) {
        return (int) Math.ceil(tablecfRepository.findAll().size() / (float)limit);
    }
}
