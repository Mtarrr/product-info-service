package com.github.mtarrr.pis.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class ProductOffering implements HasNullFields { /*Product Offering represents entity that are orderable from the provider of the catalog,this resource includes pricing information.*/

    @JsonIgnore
    private List<String> nullFields = new ArrayList<>();

    private String id; //Unique identifier of the entity

    private String name; //Name of the entity

    private String description; //Description of the productOffering

    private Map<String, Object> extendedParameters; //Arbitrary parameters that can be provided along with Product Offering

    private String href; //Reference of the ProductOffering

    private OffsetDateTime lastUpdate; //Date and time of the last update

    private List<ProductOfferingPriceRef> productOfferingPrice; //List of product offering prices

    private List<CategoryRef> category; //Related product offerings categories

    private List<ChannelRef> channel; //List of applicable distribution channels

    private List<CustomerCategoryRef> customerCategory; //List of applicable customer categories

    private Long version; //Version of the entity


    public void setName(String name) {
        this.name = name;
        if (name == null) {
            nullFields.add("name");
        }
    }

    public void setDescription(String description) {
        this.description = description;
        if (description == null) {
            nullFields.add("description");
        }
    }

    public void setExtendedParameters(Map<String, Object> extendedParameters) {
        this.extendedParameters = extendedParameters;
        if (extendedParameters == null) {
            nullFields.add("extendedParameters");
        }
    }

    public void setHref(String href) {
        this.href = href;
        if (href == null) {
            nullFields.add("href");
        }
    }

    public void setProductOfferingPrice(List<ProductOfferingPriceRef> productOfferingPrice) {
        this.productOfferingPrice = productOfferingPrice;
        if (productOfferingPrice == null) {
            nullFields.add("productOfferingPrice");
        }
    }

    public void setCategory(List<CategoryRef> category) {
        this.category = category;
        if (category == null) {
            nullFields.add("category");
        }
    }

    public void setChannel(List<ChannelRef> channel) {
        this.channel = channel;
        if (channel == null) {
            nullFields.add("channel");
        }
    }

    public void setCustomerCategory(List<CustomerCategoryRef> customerCategory) {
        this.customerCategory = customerCategory;
        if (customerCategory == null) {
            nullFields.add("customerCategory");
        }
    }
}
