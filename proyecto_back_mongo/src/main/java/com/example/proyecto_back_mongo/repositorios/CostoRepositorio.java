package com.example.proyecto_back_mongo.repositorios;

import com.example.proyecto_back_mongo.documentos.CostoMantenimiento;
import com.example.proyecto_back_mongo.documentos.MantenimientoCollection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface CostoRepositorio extends MongoRepository<CostoMantenimiento, Integer> {
    @Query(value = "{}", sort = "{id_costos: -1}")
    Optional<CostoMantenimiento> findTopByOrderByIdCostosDesc();
}
