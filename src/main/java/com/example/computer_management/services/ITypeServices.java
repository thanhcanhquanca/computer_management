package com.example.computer_management.services;

import com.example.computer_management.model.Type;
import com.example.computer_management.model.ViewsComputerDTO;

public interface ITypeServices extends IGenerateServices<Type> {
    Iterable<ViewsComputerDTO> getAllViewsComputers();
    void deleteAllViewsComputers(Long id);
}
