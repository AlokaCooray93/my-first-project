package com.example.multi_datasource.student;


import jakarta.persistence.*;

@Entity
@Table(name ="students")
public class Student {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name ="ID")
    private long id;
    @Column(name = "Name")
    private  String name;
    @Column("AGE")
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
