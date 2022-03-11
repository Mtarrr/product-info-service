package com.github.mtarrr.pis.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.mtarrr.pis.model.*;
import lombok.Data;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class ProductOfferingBody implements HasNullFields {

    @JsonIgnore
    @Transient
    private List<String> nullFields = new ArrayList<>();

    private String name; //Name of the entity

    private String description; //Description of the productOffering

    private Map<String, Object> extendedParameters; //Arbitrary parameters that can be provided along with Product Offering

    private String href; //Reference of the ProductOffering

    private List<ProductOfferingPriceRef> productOfferingPrice; //List of product offering prices

    private List<CategoryRef> category; //Related product offerings categories

    private List<ChannelRef> channel; //List of applicable distribution channels

    private List<CustomerCategoryRef> customerCategory; //List of applicable customer categories
}
