package com.example.computer_management.repository;

import com.example.computer_management.model.Computer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IComputerRepository extends CrudRepository<Computer,Long> {
    Page<Computer> findByNameContaining(String name, Pageable pageable);

    Page<Computer> findAll(Pageable pageable);

    @Query("SELECT c FROM Computer c ORDER BY c.name ASC")
    Page<Computer> findAllSortedAsc(Pageable pageable);

    @Query("SELECT c FROM Computer c ORDER BY c.name DESC")
    Page<Computer> findAllSortedDesc(Pageable pageable);

    @Query("SELECT c FROM Computer c ORDER BY RAND()")
    Page<Computer> findAllRandom(Pageable pageable);
}
