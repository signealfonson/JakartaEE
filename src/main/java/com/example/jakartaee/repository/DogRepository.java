package com.example.jakartaee.repository;

import com.example.jakartaee.entity.Dog;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
@Transactional
@ApplicationScoped
public class DogRepository {

    @PersistenceContext
    EntityManager entityManager;

    public List<Dog> findAll(){
        var query = entityManager.createQuery("select d from Dog d");
        return (List<Dog>) query.getResultList();
    }
    public List<Dog> findAllByName(String name){
        var query = entityManager.createQuery("select d from Dog d where d.name like :name");
        query.setParameter("name", name);
        return  (List<Dog>) query.getResultList();
    }
    public Optional<Dog> findOne(Long id){
        return Optional.ofNullable(entityManager.find(Dog.class, id));
    }

    public void insertDog(Dog dog){
        entityManager.persist(dog);
    }

    public void deleteDog(Long id){
        var dog = findOne(id);
        dog.ifPresent((d)-> entityManager.remove(d));
    }

    public Dog updateDog(Long id, Dog dog){
        var entity = entityManager.find(Dog.class, id);
        entity.setName(dog.getName());
        entity.setBreed(dog.getBreed());
        entityManager.persist(entity);
        return entity;
    }
}
