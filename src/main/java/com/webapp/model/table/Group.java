package com.webapp.model.table;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by aass on 2016/4/8.
 */
@Entity
@Table(name = "group")
@GenericGenerator(name = "systemUUID",strategy = "uuid")
public class Group {

    @Id
    @Column(name = "Id")
    String Id;

    @Column(name = "name")
    private String name;


}
