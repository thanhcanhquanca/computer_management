package com.example.computer_management.services;


import com.example.computer_management.exception.NotFoundException;
import com.example.computer_management.model.Computer;
import com.example.computer_management.repository.IComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ComputerServices implements IComputerServices{
   @Autowired
   private IComputerRepository iComputerRepository;

    @Override
    public Iterable<Computer> findAll() {
        return iComputerRepository.findAll();
    }

    @Override
    public Optional<Computer> findById(Long id) throws NotFoundException {
        Optional<Computer> computer = iComputerRepository.findById(id);
        if (computer.isPresent()) {
            return computer;
        }else {
            throw new NotFoundException();
        }
    }

    @Override
    public void save(Computer computer) {
        iComputerRepository.save(computer);
    }

    @Override
    public void remove(Long id) {
        iComputerRepository.deleteById(id);

    }

    @Override
    public void update(Long id, Computer computer) {
        Optional<Computer> computerOptional = iComputerRepository.findById(id);
        if (computerOptional.isPresent()) {
            Computer computer1 = new Computer();
            computer1.setId(id);
            computer1.setName(computer.getName());
            computer1.setCode(computer.getCode());
            computer1.setProducer(computer.getProducer());
            computer1.setType_id(computer.getType_id());
            iComputerRepository.save(computer1);

        }else {
            throw new RuntimeException("Cuisine with id " + id + " not found");
        }
    }

    @Override
    public long count() {
        return iComputerRepository.count();
    }

    @Override
    public Page<Computer> findAll(Pageable pageable) {
        return iComputerRepository.findAll(pageable);
    }

    @Override
    public Page<Computer> findByNameContaining(String name, Pageable pageable) {
        return iComputerRepository.findByNameContaining(name, pageable);
    }

    @Override
    public Page<Computer> findAllSortedAsc(Pageable pageable) {
        return iComputerRepository.findAllSortedAsc(pageable);
    }

    @Override
    public Page<Computer> findAllSortedDesc(Pageable pageable) {
        return iComputerRepository.findAllSortedDesc(pageable);
    }

    @Override
    public Page<Computer> findAllRandom(Pageable pageable) {
        return iComputerRepository.findAllRandom(pageable);
    }
}
