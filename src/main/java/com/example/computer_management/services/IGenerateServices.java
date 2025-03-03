package com.example.computer_management.services;

import com.example.computer_management.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IGenerateServices<T> {
    Iterable<T> findAll();  // lay all du lieu nhu list
    Optional<T> findById(Long id) throws NotFoundException;    // tim kiem theo id crudRepostiory
    void save(T t);     // luu du lieu ok oi
    void remove(Long id);    // xoa theo id
    void update(Long id, T t);   // cap nhap theo id
    long count();   // dem so luong
    Page<T> findAll(Pageable pageable);   // Lấy tất cả bản ghi với phân trang
    Page<T> findByNameContaining(String name, Pageable pageable);   // Tìm kiếm theo tên với phân trang, dùng cho chức năng tìm kiếm.
    Page<T> findAllSortedAsc(Pageable pageable);   // Lấy danh sách sắp xếp tăng dần
    Page<T> findAllSortedDesc(Pageable pageable);  // Lấy danh sách sắp xếp giảm dần
    Page<T> findAllRandom(Pageable pageable);    // Lấy danh sách sắp xếp random

}
