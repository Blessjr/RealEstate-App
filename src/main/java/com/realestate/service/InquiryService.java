package com.realestate.service;

import com.realestate.entity.Inquiry;
import com.realestate.entity.Property;
import com.realestate.entity.User;
import com.realestate.repository.InquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InquiryService {

    @Autowired
    private InquiryRepository inquiryRepository;

    public Inquiry saveInquiry(Inquiry inquiry) {
        return inquiryRepository.save(inquiry);
    }

    public List<Inquiry> getInquiriesByProperty(Property property) {
        return inquiryRepository.findByProperty(property);
    }

    public List<Inquiry> getInquiriesByBuyer(User buyer) {
        return inquiryRepository.findByBuyer(buyer);
    }

    public Object getAllInquiries() {
        //Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllInquiries'");
    }

    public interface inquiryService {
        Inquiry sendInquiry(Inquiry inquiry);
        List<Inquiry> getAllInquiries();
        List<Inquiry> getInquiriesByPropertyId(Long propertyId);
        List<Inquiry> getInquiriesByCustomerId(Long customerId);
    }
}
