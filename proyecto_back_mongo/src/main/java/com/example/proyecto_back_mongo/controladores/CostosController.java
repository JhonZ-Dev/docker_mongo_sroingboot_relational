package com.example.proyecto_back_mongo.controladores;

import com.example.proyecto_back_mongo.documentos.CostoMantenimiento;
import com.example.proyecto_back_mongo.servicios.CostosServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/costos")
public class CostosController {

    @Autowired
    public CostosServices services;

    @GetMapping("/listar")
    public List<CostoMantenimiento> listar(){
        return services.listar();
    }

    @PostMapping("/insertar/{id_mantenimiento}")
    public CostoMantenimiento guardarConMantenimiento(
             @RequestBody CostoMantenimiento costoMantenimiento,
             @PathVariable Long id_mantenimiento)

    {
        return services.guardarConMantenimiento(costoMantenimiento,id_mantenimiento);
    }

    //editar
    @PutMapping("/editar/{id_costos}")
    public CostoMantenimiento editar(@RequestBody CostoMantenimiento costoMantenimiento,
                                     @PathVariable Integer id_costos){
        return services.edit(costoMantenimiento,id_costos);
    }

    //eliminar
    @DeleteMapping("/eliminar/{id_costos}")
    public void eliminar(@PathVariable Integer id_costos){
        services.eliminar(id_costos);
    }

    //traerporid
    @GetMapping("/traer-por-id/{id_costos}")
    Optional<CostoMantenimiento> traerPorId(@PathVariable Integer id_costos){
        return services.traerPorId(id_costos);
    }






}
