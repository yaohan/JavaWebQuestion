package com.webapp.model.table;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by yao_han on 2015/12/23.
 */
@Entity
@Table(name = "school")
@GenericGenerator(name = "systemUUID",strategy = "uuid")
public class School {

    @Id
    @Column(name = "schoolId",nullable = false)
    @GeneratedValue(generator = "systemUUID")
    private String schoolId;

    @Column(name = "province",nullable = false)
    private String province;

    @Column(name = "city",nullable = false)
    private String city;

    @Column(name = "area",nullable = false)
    private String area;

    @Column(name = "school",nullable = false)
    private String school;

    public School() {};

    public School(String province, String city, String area, String school) {
        this.province = province;
        this.city = city;
        this.area = area;
        this.school = school;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getArea() {
        return area;
    }

    public String getSchool() {
        return school;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}
