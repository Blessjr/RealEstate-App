package com.realestate.repository;

import com.realestate.entity.Inquiry;
import com.realestate.entity.Property;
import com.realestate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
    List<Inquiry> findByProperty(Property property);
    List<Inquiry> findByBuyer(User buyer);
}
