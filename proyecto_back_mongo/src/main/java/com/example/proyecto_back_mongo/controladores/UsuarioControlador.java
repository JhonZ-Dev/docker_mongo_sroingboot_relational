package com.example.proyecto_back_mongo.controladores;

import com.example.proyecto_back_mongo.documentos.UsuariosMantenimientoColletion;
import com.example.proyecto_back_mongo.servicios.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/datos/restTemplate")
public class UsuarioControlador {

    @Autowired
    public UsuarioServices services;
    @PostMapping("/insertar")
    public UsuariosMantenimientoColletion guardar(@RequestBody UsuariosMantenimientoColletion usuariosMantenimientoColletion){
        return services.guardarResTemplate(usuariosMantenimientoColletion);
    }

}
