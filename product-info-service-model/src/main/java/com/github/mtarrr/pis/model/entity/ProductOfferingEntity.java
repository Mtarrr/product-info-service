package com.github.mtarrr.pis.model.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
    @Type(type = "java.lang.String")
    private ProductOfferingBody body;


}
