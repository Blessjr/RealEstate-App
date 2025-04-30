package com.realestate.service;

import com.realestate.entity.Property;
import com.realestate.entity.Inquiry;
import com.realestate.repository.PropertyRepository;
import com.realestate.repository.InquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private InquiryRepository inquiryRepository;

    private static final String UPLOAD_DIR = "src/main/resources/static/images/";

    public void saveProperty(Property property, MultipartFile imageFile) {
        try {
            if (imageFile != null && !imageFile.isEmpty()) {
                String fileName = imageFile.getOriginalFilename();
                Path filePath = Paths.get(UPLOAD_DIR + fileName);
                Files.write(filePath, imageFile.getBytes());
                property.setImage(fileName);
            }
            propertyRepository.save(property);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateProperty(Long id, Property updatedProperty, MultipartFile imageFile) {
        Optional<Property> optionalProperty = propertyRepository.findById(id);
        
        if (optionalProperty.isPresent()) {
            Property existingProperty = optionalProperty.get();

            existingProperty.setTitle(updatedProperty.getTitle());
            existingProperty.setDescription(updatedProperty.getDescription());
            existingProperty.setPrice(updatedProperty.getPrice());
            existingProperty.setLocation(updatedProperty.getLocation());

            try {
                if (imageFile != null && !imageFile.isEmpty()) {
                    String fileName = imageFile.getOriginalFilename();
                    Path filePath = Paths.get(UPLOAD_DIR + fileName);
                    Files.write(filePath, imageFile.getBytes());
                    existingProperty.setImage(fileName);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            propertyRepository.save(existingProperty);
        } else {
            // Handle the case when the property is not found, for example by throwing an exception
            throw new IllegalArgumentException("Property not found with id: " + id);
        }
    }

    public Property getPropertyById(Long id) {
        return propertyRepository.findById(id).orElse(null);
    }

    public void createInquiry(Long propertyId, String message) {
        Property property = propertyRepository.findById(propertyId).orElse(null);
        if (property != null) {
            Inquiry inquiry = new Inquiry();
            inquiry.setProperty(property);
            inquiry.setMessage(message);
            inquiryRepository.save(inquiry);
        }
    }
}
