package com.example.jakartaee.controller;

import com.example.jakartaee.dto.DogDTO;
import com.example.jakartaee.entity.Dog;
import com.example.jakartaee.mapper.DogMapper;
import com.example.jakartaee.repository.DogRepository;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.awt.*;
import java.net.URI;
import java.util.List;

@Path("/dogs")
@Produces(MediaType.APPLICATION_JSON)
public class DogController {
    @Inject
    DogRepository repository;
    @Inject
    DogMapper mapper;
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOne(@Valid DogDTO dogDTO){
        var input = dogDTO;
        var converter = mapper.map(input);
        repository.insertDog(converter);
        return Response.created(URI.create("dogs/" + dogDTO.getId())).build();
    }
    @GET
    @Path("/{id}")
    public Response getOne(@PathParam("id")Long id){
        var dogEntity = repository.findOne(id);
        if (dogEntity.isPresent()){
            var dtoConverter = mapper.map(dogEntity.get());
            return Response.ok().entity(dtoConverter).build();
        } else
            throw new NotFoundException("id: " + id);
    }
    @GET
    public List<DogDTO> getAll(@QueryParam("name") String name){
        if (name==null){
        return mapper.map(repository.findAll());
        } else
            return mapper.map(repository.findAllByName(name));
    }
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOne(@PathParam("id")Long id, @Valid DogDTO dogDTO){
        var dog = repository.findOne(id);
        if (dog.isPresent()){
            var dtoToEntity = mapper.map(dogDTO);
            var updateDatabase = repository.updateDog(id, dtoToEntity);
            var entityToDTO = mapper.map(updateDatabase);
            return Response.ok().entity(entityToDTO).build();
        }
        else
            return Response.noContent().build();
    }
    @DELETE
    @Path("/{id}")
    public Response deleteOne(@PathParam("id")Long id){
        var dog = repository.findOne(id);
        if (dog.isPresent()){
            repository.deleteDog(id);
            return Response.ok().build();
        }
        else
            return Response.noContent().build();
    }
}
