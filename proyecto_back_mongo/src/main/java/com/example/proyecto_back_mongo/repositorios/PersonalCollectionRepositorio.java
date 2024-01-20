package com.example.proyecto_back_mongo.repositorios;

import com.example.proyecto_back_mongo.documentos.MantenimientoCollection;
import com.example.proyecto_back_mongo.documentos.PersonalCollection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface PersonalCollectionRepositorio extends MongoRepository<PersonalCollection, Integer>
{
    @Query(value = "{}", sort = "{id_personal: -1}")
    Optional<PersonalCollection> findTopByOrderByIdPersonalDesc();
}
