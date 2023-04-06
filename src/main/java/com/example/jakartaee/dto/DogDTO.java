package com.example.jakartaee.dto;

import com.example.jakartaee.entity.Dog;
import jakarta.validation.constraints.NotNull;

public class DogDTO {
    @NotNull
    private Long id;
    @NotNull
    String name;
    @NotNull
    String breed;

    public DogDTO(Dog dog){
        this.id = dog.getId();
        this.name = dog.getName();
        this.breed = dog.getBreed();
    }

    public DogDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
}
