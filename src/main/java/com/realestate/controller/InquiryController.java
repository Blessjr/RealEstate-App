package com.realestate.controller;

import com.realestate.entity.Inquiry;
import com.realestate.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/inquiries")
public class InquiryController {

    @Autowired
    private InquiryService inquiryService;

    @GetMapping
    public String listInquiries(Model model) {
        model.addAttribute("inquiries", inquiryService.getAllInquiries());
        return "inquiry_list"; // you can create this template later
    }

    @PostMapping("/submit")
    public String submitInquiry(@ModelAttribute Inquiry inquiry, Model model) {
        inquiryService.saveInquiry(inquiry);
        model.addAttribute("message", "Inquiry submitted successfully!");
        return "redirect:/properties"; // redirect back to properties page after submitting
    }
}
