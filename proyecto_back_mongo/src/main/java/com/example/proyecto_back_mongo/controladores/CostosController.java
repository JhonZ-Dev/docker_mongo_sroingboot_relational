package com.example.proyecto_back_mongo.controladores;

import com.example.proyecto_back_mongo.documentos.CostoMantenimiento;
import com.example.proyecto_back_mongo.servicios.CostosServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/costos")
public class CostosController {

    @Autowired
    public CostosServices services;
    @PostMapping("/guardar")
    public CostoMantenimiento guardar(@RequestBody CostoMantenimiento costoMantenimiento)
    {
        return services.guardarConIdMantenimiento(costoMantenimiento);
    }
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


    }
