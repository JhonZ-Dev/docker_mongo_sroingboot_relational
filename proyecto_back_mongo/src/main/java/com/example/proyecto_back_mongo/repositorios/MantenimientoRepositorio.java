package com.example.proyecto_back_mongo.repositorios;

import com.example.proyecto_back_mongo.documentos.MantenimientoCollection;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MantenimientoRepositorio extends MongoRepository<MantenimientoCollection, Integer> {
}
