package com.example.computer_management.controller;

import com.example.computer_management.model.Type;
import com.example.computer_management.model.ViewsComputerDTO;
import com.example.computer_management.services.ITypeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("type")
public class TypeController {
    @Autowired
    private ITypeServices iTypeServices;

    @GetMapping("/list")
    public String showList(Model model) {
        Iterable<Type> optional = iTypeServices.findAll();
        model.addAttribute("types", optional);
        return "type/list";
    }

    @GetMapping("/create")
    public String showCreate(Model model) {
        model.addAttribute("type", new Type());
        return "type/create";
    }

    @PostMapping("/save")
    public String showSave(Model model, @ModelAttribute("type") Type type) {
        iTypeServices.save(type);
        return "redirect:/type/list";
    }

    @GetMapping("viewsList")
    public String showViewsList(Model model) {
        Iterable<ViewsComputerDTO> optional = iTypeServices.getAllViewsComputers();
        model.addAttribute("viewsList", optional);
        return "type/viewsList";
    }


}
