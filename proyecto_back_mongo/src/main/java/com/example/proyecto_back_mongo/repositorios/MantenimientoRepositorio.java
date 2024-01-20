package com.example.proyecto_back_mongo.repositorios;

import com.example.proyecto_back_mongo.documentos.MantenimientoCollection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;


public interface MantenimientoRepositorio extends MongoRepository<MantenimientoCollection, Integer> {

    @Query(value = "{}", sort = "{id_mantenimiento: -1}")
    Optional<MantenimientoCollection> findTopByOrderByIdMantenimientoDesc();
}
