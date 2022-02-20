package com.github.mtarrr.pis.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.mtarrr.pis.model.HasNullFields;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "product_offering")
public class ProductOfferingEntity implements HasNullFields {

    @JsonIgnore
    @Transient
    private List<String> nullFields = new ArrayList<>();

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
