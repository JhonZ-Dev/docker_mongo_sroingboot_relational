package com.example.proyecto_back_mongo.servicios;

import com.example.proyecto_back_mongo.documentos.MantenimientoCollection;
import com.example.proyecto_back_mongo.repositorios.MantenimientoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MantenimientoServices {
    @Autowired
    public MantenimientoRepositorio repositorio;
    //metodo para guardar

    public MantenimientoCollection guardar(MantenimientoCollection collection){
        //asignar
        collection.setId_mantenimiento(generarNuevoId());
        return repositorio.save(collection);
    }

    //metodo para listar
    public List<MantenimientoCollection> listar(){
        return repositorio.findAll();
    }

    private int generarNuevoId() {
        // Obtener el máximo id_mantenimiento actual y sumar 1
        Integer maxId = repositorio.findTopByOrderByIdMantenimientoDesc().map(MantenimientoCollection::getId_mantenimiento).orElse(0);
        return maxId + 1;
    }

}
