package com.example.multi_datasource.teacher;

import jakarta.persistence.*;

@Entity
@Table(name="Teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="ID")
    private long id;
    @Column(name = "Name")
    private  String name;
    @Column(name="AGE")
    private int age;

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
