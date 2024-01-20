package com.example.proyecto_back_mongo.servicios;

import com.example.proyecto_back_mongo.documentos.CostoMantenimiento;
import com.example.proyecto_back_mongo.documentos.MantenimientoCollection;
import com.example.proyecto_back_mongo.repositorios.CostoRepositorio;
import com.example.proyecto_back_mongo.repositorios.MantenimientoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CostosServices {
    @Autowired
    public CostoRepositorio repositorio;

    @Autowired
    public MantenimientoRepositorio mantenimientoRepositorio;

    //metodo para guardar un costo dependiendo del id_mantenimeinto
    public CostoMantenimiento guardarConIdMantenimiento(
            CostoMantenimiento costoMantenimiento
    ){
       return repositorio.save(costoMantenimiento);
    }

    public List<CostoMantenimiento> listar(){
        return repositorio.findAll();
    }
}
