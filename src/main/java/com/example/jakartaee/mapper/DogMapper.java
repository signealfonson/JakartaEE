package com.example.jakartaee.mapper;

import com.example.jakartaee.dto.DogDTO;
import com.example.jakartaee.entity.Dog;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class DogMapper {
    public List<DogDTO> map(List<Dog> dog){
        return dog.stream().map(DogDTO::new).toList();
    }
    public DogDTO map(Dog dog){
        return new DogDTO(dog);
    }
    public Dog map(DogDTO dogDTO){
        var dog = new Dog();
        dog.setId(dogDTO.getId());
        dog.setName(dogDTO.getName());
        dog.setBreed(dogDTO.getBreed());
        return dog;
    }

}
