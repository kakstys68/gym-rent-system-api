package com.rentsystemspring.controllers;

import com.google.gson.Gson;
import com.rentsystemspring.errors.GymNotFound;
import com.rentsystemspring.model.Gym;
import com.rentsystemspring.repository.GymRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.Properties;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class GymController {
    @Autowired
    private GymRepository gymRepository;

    public GymController(GymRepository gymRepository) {
        this.gymRepository = gymRepository;
    }
    @GetMapping(value = "/allGyms")
    public @ResponseBody Iterable<Gym> getAllGyms(){
        return gymRepository.findAll();
    }

    @GetMapping(value = "/gymById/{id}")
    public EntityModel<Gym> getGymById(@PathVariable(name = "id") int id){
        Gym gym = gymRepository.findById(id).orElseThrow(() -> new GymNotFound(id));
        return EntityModel.of(gym, linkTo(methodOn(GymController.class).getGymById(id)).withSelfRel(),
                linkTo(methodOn(GymController.class).getAllGyms()).withRel("AllGyms"));
    }
    @PostMapping("/addGym")
    Gym newGym(@RequestBody Gym newGym) {
        return gymRepository.save(newGym);
    }
    @PutMapping(value = "/updateGym/{id}")
    public @ResponseBody String updateGym(@RequestBody String gymInfoToUpdate, @PathVariable int id) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(gymInfoToUpdate, Properties.class);
        //CHANGE
        Gym gym = gymRepository.findById(id)
                .orElseThrow(() -> new GymNotFound(id));
        gym.setName(properties.getProperty("name"));
        gym.setAddress(properties.getProperty("address"));
        gym.setHourlyPrice(Double.parseDouble(properties.getProperty("hourlyPrice")));
        gym.setType(properties.getProperty("type"));
        gymRepository.save(gym);
        return "Gym updated";
    }


    @DeleteMapping(value = "/deleteGym/{id}")
    public @ResponseBody String deleteGym(@PathVariable(name = "id") int id){
        gymRepository.deleteById(id);
        return "Gym Successfully deleted";
    }
}
