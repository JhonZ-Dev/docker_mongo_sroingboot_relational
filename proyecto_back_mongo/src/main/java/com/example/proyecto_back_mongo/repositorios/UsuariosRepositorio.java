package com.example.proyecto_back_mongo.repositorios;

import com.example.proyecto_back_mongo.documentos.MantenimientoCollection;
import com.example.proyecto_back_mongo.documentos.UsuariosMantenimientoColletion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UsuariosRepositorio extends MongoRepository<UsuariosMantenimientoColletion, Long> {

    @Query(value = "{}", sort = "{id_usuario: -1}")
    Optional<UsuariosMantenimientoColletion> findTopByOrderByIdUsuarioDesc();
}
