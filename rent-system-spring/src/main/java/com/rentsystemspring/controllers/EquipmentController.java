package com.rentsystemspring.controllers;

import com.google.gson.Gson;
import com.rentsystemspring.errors.EquipmentNotFound;
import com.rentsystemspring.model.Equipment;
import com.rentsystemspring.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.Properties;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class EquipmentController {
    @Autowired
    private EquipmentRepository equipmentRepository;

    public EquipmentController(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }
    @GetMapping(value = "/allEquipment")
    public @ResponseBody Iterable<Equipment> getAllEquipment(){
        return equipmentRepository.findAll();
    }

    @GetMapping(value = "/equipmentById/{id}")
    public EntityModel<Equipment> getEquipmentById(@PathVariable(name = "id") int id){
        Equipment equipment = equipmentRepository.findById(id).orElseThrow(() -> new EquipmentNotFound(id));
        return EntityModel.of(equipment, linkTo(methodOn(EquipmentController.class).getEquipmentById(id)).withSelfRel(),
                linkTo(methodOn(EquipmentController.class).getAllEquipment()).withRel("AllEquipment"));
    }
    @PostMapping("/addEquipment")
    Equipment newEquipment(@RequestBody Equipment newEquipment) {
        return equipmentRepository.save(newEquipment);
    }
    @PutMapping(value = "/updateEquipment/{id}")
    public @ResponseBody String updateEquipment(@RequestBody String equipmentInfoToUpdate, @PathVariable int id) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(equipmentInfoToUpdate, Properties.class);
        Equipment equipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new EquipmentNotFound(id));
        equipment.setName(properties.getProperty("name"));
        equipment.setSportType(properties.getProperty("sportType"));
        equipment.setQuantity(Integer.parseInt(properties.getProperty("quantity")));
        equipmentRepository.save(equipment);
        return "Equipment updated";
    }


    @DeleteMapping(value = "/deleteEquipment/{id}")
    public @ResponseBody String deleteEquipment(@PathVariable(name = "id") int id){
        equipmentRepository.deleteById(id);
        return "Equipment Successfully deleted";
    }
}
