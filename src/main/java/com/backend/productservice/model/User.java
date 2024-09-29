package com.backend.productservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User extends BaseModel{
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long userId;
    private String email;
    private String hashedPassword;
    private String fullname;
}
