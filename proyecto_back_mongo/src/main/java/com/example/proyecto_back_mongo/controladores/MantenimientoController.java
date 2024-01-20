package com.example.proyecto_back_mongo.controladores;

import com.example.proyecto_back_mongo.documentos.MantenimientoCollection;
import com.example.proyecto_back_mongo.servicios.MantenimientoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/proyecto/mongo")
public class MantenimientoController {

    @Autowired
    public MantenimientoServices services;
    @PostMapping("/guardar")
    public MantenimientoCollection guardar(@RequestBody MantenimientoCollection collection){
        return services.guardar(collection);
    }

    @GetMapping("/listar")
    public List<MantenimientoCollection> listar(){
        return services.listar();
    }

    @PutMapping("/editar/{id_mantenimiento}")
    public MantenimientoCollection editar(@RequestBody MantenimientoCollection mantenimientoCollection,
                                          @PathVariable Integer id_mantenimiento){
        return services.editar(mantenimientoCollection, id_mantenimiento);
    }

    //eliminar
    @DeleteMapping("/eliminar/{id_mantenimiento}")
    public void eliminar(Integer id_mantenimiento){
        services.eliminar(id_mantenimiento);
    }

    //traerPorId
    @GetMapping("/traer-por_id/{id_mantenimiento}")
    public Optional<MantenimientoCollection> traerPorSuId(@PathVariable Integer id_mantenimiento){
        return services.traerPorId(id_mantenimiento);
    }
}
