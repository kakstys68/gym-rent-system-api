package com.rentsystemspring.controllers;

import com.google.gson.Gson;
import com.rentsystemspring.errors.RentProviderNotFound;
import com.rentsystemspring.model.RentProvider;
import com.rentsystemspring.repository.RentProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.Properties;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class RentProviderController {
    @Autowired
    private RentProviderRepository rentProviderRepository;
    public RentProviderController(RentProviderRepository rentProviderRepository) {
        this.rentProviderRepository = rentProviderRepository;
    }
    @GetMapping(value = "/allRentProviders")
    public @ResponseBody Iterable<RentProvider> getAllRentProviders(){
        return rentProviderRepository.findAll();
    }

    @GetMapping(value = "/rentProviderById/{id}")
    public EntityModel<RentProvider> getRentProviderById(@PathVariable(name = "id") int id){
        RentProvider rentProvider = rentProviderRepository.findById(id).orElseThrow(() -> new RentProviderNotFound(id));
        return EntityModel.of(rentProvider, linkTo(methodOn(RentProviderController.class).getRentProviderById(id)).withSelfRel(), linkTo(methodOn(RentProviderController.class).getAllRentProviders()).withRel("RentProvider"));
    }
    @PostMapping("/addRentProvider")
    RentProvider newRentProvider(@RequestBody RentProvider newRentProvider) {
        return rentProviderRepository.save(newRentProvider);
    }
    @PutMapping(value = "/updateRentProvider/{id}")
    public @ResponseBody String updateRentProvider(@RequestBody String rentProviderInfoToUpdate, @PathVariable int id) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(rentProviderInfoToUpdate, Properties.class);
        RentProvider rentProvider = rentProviderRepository.findById(id)
                .orElseThrow(() -> new RentProviderNotFound(id));
        rentProvider.setName(properties.getProperty("name"));
        rentProvider.setSurname(properties.getProperty("surname"));
        rentProvider.setUsername(properties.getProperty("username"));
        rentProvider.setPassword(properties.getProperty("password"));
        rentProvider.setEmail(properties.getProperty("email"));
        rentProvider.setRepresentative(properties.getProperty("representative"));
        rentProvider.setCompanyName(properties.getProperty("companyName"));
        rentProvider.setAdmin(Boolean.parseBoolean(properties.getProperty("admin")));
        rentProviderRepository.save(rentProvider);
        return "Rent Provider updated";
    }

    @PostMapping(value = "/validateRentProvider")
    public @ResponseBody EntityModel<RentProvider> validateRentProvider(@RequestBody String info){
        Gson parser = new Gson();
        Properties properties = parser.fromJson(info, Properties.class);
        RentProvider rentProvider = rentProviderRepository.findRentProviderByUsernameAndPassword(properties.getProperty("username"), properties.getProperty("password"));
        return EntityModel.of(rentProvider, linkTo(methodOn(RentProviderController.class).validateRentProvider(info)).withSelfRel(),
                linkTo(methodOn(RentProviderController.class).getAllRentProviders()).withRel("AllRentProviders"));
    }

    @DeleteMapping(value = "/deleteRentProvider/{id}")
    public @ResponseBody String deleteRentProvider(@PathVariable(name = "id") int id){
        rentProviderRepository.deleteById(id);
        return "Rent Provider Successfully deleted";
    }
}
