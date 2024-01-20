package com.example.proyecto_back_mongo.controladores;

import com.example.proyecto_back_mongo.documentos.PersonalCollection;
import com.example.proyecto_back_mongo.servicios.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personal")
public class PersonalController {

    @Autowired
    public PersonalService service;

    //guardar
    @PostMapping("/insertar")
    public PersonalCollection guardar(@RequestBody PersonalCollection collection){
        return service.guardar(collection);
    }

    @GetMapping("/listar")
    public List<PersonalCollection> listar(){
        return service.listar();
    }
}
