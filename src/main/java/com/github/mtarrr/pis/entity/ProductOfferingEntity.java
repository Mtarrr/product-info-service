package com.github.mtarrr.pis.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "product_offering")
public class ProductOfferingEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "last_update")
    private OffsetDateTime lastUpdate;

    @Column(name = "version")
    private Long version;

    @Column(name = "body")
    @Type(type = "com.github.mtarrr.pis.entity.ProductOfferingBody")
    private ProductOfferingBody body;


}
