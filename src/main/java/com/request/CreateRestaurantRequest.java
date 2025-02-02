package com.request;

import java.util.List;

import com.milind.model.Address;
import com.milind.model.ContactInformation;

import lombok.Data;

@Data
public class CreateRestaurantRequest {
    private Long id;
    private String name;
    private String description;
    private String cuisineType;
    private Address address;
    private ContactInformation contactInformation;
    private String opningHours;
    private List<String> images;
}
