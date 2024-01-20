package com.example.proyecto_back_mongo.controladores;

import com.example.proyecto_back_mongo.documentos.MantenimientoCollection;
import com.example.proyecto_back_mongo.servicios.MantenimientoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proyecto/mongo")
public class MantenimientoController {

    @Autowired
    public MantenimientoServices services;
    @PostMapping("/guardar")
    public MantenimientoCollection guardar(@RequestBody MantenimientoCollection collection){
        return services.guardar(collection);
    }
}