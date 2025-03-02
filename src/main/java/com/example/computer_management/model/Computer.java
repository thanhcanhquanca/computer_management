package com.example.computer_management.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "computer")
public class Computer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Tên không được để trống")
    @Size(min = 2, max = 100, message = "Tên phải từ 2 đến 100 ký tự")
    private String name;

    @NotBlank(message = "Mã không được để trống")
    private String code;

    @NotBlank(message = "Nhà sản xuất không được để trống")
    private String producer;

    private String img;

    @ManyToOne
    @JoinColumn
    private Type type_id;

    public Computer() {
    }

    public Computer(Long id, String code, String name, String producer, String img, Type type_id) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.producer = producer;
        this.img = img;
        this.type_id = type_id;
    }

    public Computer(String code, String name, String producer, String img, Type type_id) {
        this.code = code;
        this.name = name;
        this.producer = producer;
        this.img = img;
        this.type_id = type_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Type getType_id() {
        return type_id;
    }

    public void setType_id(Type type_id) {
        this.type_id = type_id;
    }
}
