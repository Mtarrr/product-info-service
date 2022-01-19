package com.github.mtarrr.pis.entity;

import com.github.mtarrr.pis.entity.ProductOfferingBody;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class ProductOfferingEntity {

    private String id;

    private OffsetDateTime lastUpdate;

    private Long version;

    private ProductOfferingBody body;
}
