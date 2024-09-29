package com.backend.productservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Token extends BaseModel{

    private String value;
    @ManyToOne
    private User user;
    private Date expiryDate;
}
