package com.example.computer_management.repository;

import com.example.computer_management.model.Type;
import com.example.computer_management.model.ViewsComputerDTO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ITypeRepository extends CrudRepository<Type,Long> {

    @Query(nativeQuery = true ,
    value = "SELECT type.id, type.name, COUNT(computer.name) AS total_computers\n" +
            "FROM computer\n" +
            "         LEFT JOIN type ON computer.type_id_id = type.id\n" +
            "GROUP BY type.id, type.name;")
    Iterable<ViewsComputerDTO> getAllViewsComputers();

    @Query(nativeQuery = true ,
    value = "CALL deleteAllViewsComputers(:id)")
    @Transactional
    @Modifying
    void deleteAllViewsComputers(@Param("id") Long id);


}
