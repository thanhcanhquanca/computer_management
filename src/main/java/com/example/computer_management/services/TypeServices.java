package com.example.computer_management.services;

import com.example.computer_management.model.Type;
import com.example.computer_management.model.ViewsComputerDTO;
import com.example.computer_management.repository.ITypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TypeServices implements ITypeServices {
  @Autowired
  private ITypeRepository iTypeRepository;

    @Override
    public Iterable<Type> findAll() {
        return iTypeRepository.findAll();
    }

    @Override
    public Optional<Type> findById(Long id) {
        return iTypeRepository.findById(id);
    }

    @Override
    public void save(Type type) {
        iTypeRepository.save(type);
    }

    @Override
    public void remove(Long id) {
        iTypeRepository.deleteById(id);
    }

    @Override
    public void update(Long id, Type type) {
        Optional<Type> computerOptional = iTypeRepository.findById(id);
        if (computerOptional.isPresent()) {
            Type type1 = new Type();
            type1.setId(id);
            type1.setName(type.getName());
            iTypeRepository.save(type1);

        }else {
            throw new IllegalArgumentException("Type not found");
        }
    }

    @Override
    public long count() {
        return iTypeRepository.count();
    }

    @Override
    public Page<Type> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Page<Type> findByNameContaining(String name, Pageable pageable) {
        return null;
    }

    @Override
    public Page<Type> findAllSortedAsc(Pageable pageable) {
        return null;
    }

    @Override
    public Page<Type> findAllSortedDesc(Pageable pageable) {
        return null;
    }

    @Override
    public Page<Type> findAllRandom(Pageable pageable) {
        return null;
    }

    @Override
    public Iterable<ViewsComputerDTO> getAllViewsComputers() {
        return iTypeRepository.getAllViewsComputers();
    }

    @Override
    public void deleteAllViewsComputers(Long id) {
        iTypeRepository.deleteAllViewsComputers(id);
    }
}
