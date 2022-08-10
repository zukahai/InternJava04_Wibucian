package com.java04.wibucian.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "GroupTable")
public class GroupTableNoMapPing implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Id
    @Column(name = "idGroupTable", nullable = false)
    private String idGroupTable;

    @Column(name = "groupName")
    private String groupName;

    @Column(name = "foundedTime")
    private Date foundedTime;

}
