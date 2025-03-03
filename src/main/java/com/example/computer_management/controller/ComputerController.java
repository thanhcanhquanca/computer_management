package com.example.computer_management.controller;

import com.example.computer_management.exception.NotFoundException;
import com.example.computer_management.model.Computer;
import com.example.computer_management.model.Type;
import com.example.computer_management.services.IComputerServices;
import com.example.computer_management.services.ITypeServices;
import com.example.computer_management.uitily.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("computers")
public class ComputerController {

        @Autowired
        private IComputerServices iComputerServices;

        @Autowired
        private ITypeServices iTypeServices;

    @ModelAttribute("types")
    public Iterable<Type> getTypes() {
        return iTypeServices.findAll();
    }

    @GetMapping("/list")
    public String showList(Model model,
                           @RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "5") int size,
                           @RequestParam(defaultValue = "asc") String sort) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Computer> componentPage;
        switch (sort.toLowerCase()) {
            case "desc":
                componentPage = iComputerServices.findAllSortedDesc(pageable);
                break;
            case "random":
                componentPage = iComputerServices.findAllRandom(pageable);
                break;
            case "asc":
            default:
                componentPage = iComputerServices.findAllSortedAsc(pageable);
                break;
        }

        model.addAttribute("computers", componentPage.getContent());
        model.addAttribute("currentPage", componentPage.getNumber());
        model.addAttribute("totalPages", componentPage.getTotalPages());
        model.addAttribute("size", size);
        model.addAttribute("pageNumbers", PaginationUtils.getPageNumbers(componentPage.getTotalPages(), componentPage.getNumber()));
        model.addAttribute("sort", sort);
        model.addAttribute("totalItems", iComputerServices.count());
     return "computer/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        System.out.println("Controller");
            model.addAttribute("computer", new Computer());
            return "computer/create";
    }

    @PostMapping("/save")
    public String createComputer(@Valid @ModelAttribute("computer") Computer computer,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "computer/create";
        }
        iComputerServices.save(computer);
        return "redirect:/computers/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteComputer(@PathVariable Long id) {
        iComputerServices.remove(id);
        return "redirect:/computers/list";
    }

    @GetMapping("/view/{id}")
    public String viewComputer(@PathVariable Long id, Model model) throws NotFoundException {
        Optional<Computer> computer = iComputerServices.findById(id);
            model.addAttribute("computer", computer.get());
            return "computer/views";
    }

    @GetMapping("/search")
    public String searchCuisines(Model model,
                                 @RequestParam("search") String search,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Computer> cuisinePage = iComputerServices.findByNameContaining(search, pageable);

        model.addAttribute("computers", cuisinePage.getContent());
        model.addAttribute("currentPage", cuisinePage.getNumber());
        model.addAttribute("totalPages", cuisinePage.getTotalPages());
        model.addAttribute("size", size);
        model.addAttribute("pageNumbers", PaginationUtils.getPageNumbers(cuisinePage.getTotalPages(), cuisinePage.getNumber()));
        model.addAttribute("search", search);

        return "computer/search";
    }

    @PostMapping("/updater")
    public String updateComputer(@RequestParam("id") Long id ,
                                 @ModelAttribute Computer computer) {
        iComputerServices.update(id, computer);
        return "redirect:/computers/list";
    }


    @GetMapping("/edit")
    public String showEditForm(Model model, Long id , RedirectAttributes redirect) throws NotFoundException {
        Optional<Computer> computer = iComputerServices.findById(id);

            model.addAttribute("computer", computer.get());
            model.addAttribute("types", iTypeServices.findAll());
            return "computer/edit";

    }

    @ExceptionHandler(NotFoundException.class)
    public String handleNotFound(Model model, NotFoundException e) {
        return "errors/error";
    }

}

