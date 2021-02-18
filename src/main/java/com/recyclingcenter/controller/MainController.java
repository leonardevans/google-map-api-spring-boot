package com.recyclingcenter.controller;

import com.recyclingcenter.model.Location;
import com.recyclingcenter.model.RecyclingCenter;
import com.recyclingcenter.repository.LocationRepository;
import com.recyclingcenter.repository.RecyclingCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    RecyclingCenterRepository recyclingCenterRepository;

    @Autowired
    LocationRepository locationRepository;

    @GetMapping("/")
    public String showIndex(){
        return "index";
    }

    @GetMapping("/locations")
    public String showLocations(){
        return "locations";
    }

    @GetMapping("/recycling")
    public String showRecycling(){
        return "recycling";
    }

    @GetMapping("/dates")
    public String showDates(){
        return "dates";
    }

    @GetMapping("/recyclable")
    public String showRecyclable(){
        return "recyclable";
    }

    @GetMapping("/centers")
    public String showRecyclingCenters(Model model)
    {
        model.addAttribute("recyclingCenters", recyclingCenterRepository.findAll());
        return "recycling-centers";
    }

    @GetMapping("/centers/new")
    public String showAddRecyclingCenter(Model model){
        RecyclingCenter recyclingCenter = new RecyclingCenter();
        Location location = new Location();
        recyclingCenter.setLocation(location);
        model.addAttribute("center", recyclingCenter);
        return "add-recycling-center";
    }

    @PostMapping("/centers/new")
    public String addRecyclingCenter(@Valid @ModelAttribute("center") RecyclingCenter center, BindingResult bindingResult){
        if ( center.getLocation().getAddress().isEmpty() || center.getLocation().getAddress() == null){
            bindingResult.addError(new FieldError("center", "location", "Location's latitude, longitude and address are required"));
        }
        if (bindingResult.hasErrors()){
            return "add-recycling-center";
        }

        Location savedLocation = locationRepository.save(center.getLocation());
        center.setLocation(savedLocation);

        RecyclingCenter savedRecyclingCenter1 = recyclingCenterRepository.save(center);
        return "redirect:/centers";
    }

    @GetMapping("/centers/delete/{id}")
    public String deleteRecyclingCenter(@PathVariable("id") Long id)
    {
        Optional<RecyclingCenter> recyclingCenter = recyclingCenterRepository.findById(id);
        recyclingCenter.ifPresent(center -> recyclingCenterRepository.delete(center));
        return "redirect:/centers";
    }
}
