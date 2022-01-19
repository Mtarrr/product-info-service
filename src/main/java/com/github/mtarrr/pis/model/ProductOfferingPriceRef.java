package com.github.mtarrr.pis.model;


import lombok.Data;

@Data
public class ProductOfferingPriceRef { //The entity represents reference to the ProductOfferingPrice.

    private String id; //Unique identifier

    private String description; //Description

    private String price; //Price

    private String priceType; //A category describing price

}
