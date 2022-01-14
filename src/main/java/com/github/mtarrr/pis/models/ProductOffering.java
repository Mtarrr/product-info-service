package com.github.mtarrr.pis.models;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

public class ProductOffering { /*Product Offering represents entity that are orderable from the provider of the catalog,
                                                                           this resource includes pricing information.*/

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

    private long version; //Version of the entity



}
