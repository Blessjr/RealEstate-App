package com.realestate.controller;

import com.realestate.entity.Property;
import com.realestate.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/property")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    // 🏡 Show Add Property Form
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("property", new Property());
        return "properties/addproperty";
    }

    // 🏡 Handle Property Submit
    @PostMapping("/add")
    public String addProperty(@ModelAttribute Property property,
                              @RequestParam("image") MultipartFile imageFile) {
        propertyService.saveProperty(property, imageFile);
        return "redirect:/dashboard"; // Redirect to dashboard after adding
    }

    // 🏡 Show Edit Property Form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Property property = propertyService.getPropertyById(id);
        model.addAttribute("property", property);
        return "properties/editproperty";
    }

    // 🏡 Handle Edit Submit
    @PostMapping("/edit/{id}")
    public String editProperty(@PathVariable Long id,
                                @ModelAttribute Property updatedProperty,
                                @RequestParam(value = "image", required = false) MultipartFile imageFile) {
        propertyService.updateProperty(id, updatedProperty, imageFile);
        return "redirect:/dashboard";
    }

    // 🏡 Show Property Details
    @GetMapping("/view/{id}")
    public String viewProperty(@PathVariable Long id, Model model) {
        Property property = propertyService.getPropertyById(id);
        model.addAttribute("property", property);
        return "properties/propertydetail";
    }

    // 🏡 Show Inquiry Form
    @GetMapping("/inquire/{id}")
    public String showInquiryForm(@PathVariable Long id, Model model) {
        Property property = propertyService.getPropertyById(id);
        model.addAttribute("property", property);
        return "properties/inquiryproperty";
    }

    // 🏡 Handle Inquiry Submit
    @PostMapping("/inquire/{id}")
    public String submitInquiry(@PathVariable Long id,
                                 @RequestParam("message") String message) {
        propertyService.createInquiry(id, message);
        return "redirect:/dashboard";
    }
}
