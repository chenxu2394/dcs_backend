package com.backend.ecommerce.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "category", schema = "ecommerce")
@Setter
@Getter
public class Category {
    @Id
    private int id;
    private String name;
    private String description;
}
