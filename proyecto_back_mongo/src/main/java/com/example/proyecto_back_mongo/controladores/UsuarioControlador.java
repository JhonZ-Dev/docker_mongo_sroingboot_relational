package com.example.proyecto_back_mongo.controladores;

import com.example.proyecto_back_mongo.documentos.UsuariosMantenimientoColletion;
import com.example.proyecto_back_mongo.servicios.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/datos/restTemplate")
public class UsuarioControlador {

    @Autowired
    public UsuarioServices services;
    @PostMapping("/insertar")
    public UsuariosMantenimientoColletion guardar(@RequestBody UsuariosMantenimientoColletion usuariosMantenimientoColletion){
        return services.guardarResTemplate(usuariosMantenimientoColletion);
    }

    @GetMapping("/listar")
    public List<UsuariosMantenimientoColletion> listarUsuarios(){
        return services.listar();
    }

    @PutMapping("/editar/{id_usuario}")
    public UsuariosMantenimientoColletion editarUsuarios(@RequestBody UsuariosMantenimientoColletion usuariosMantenimientoColletion,
                                                         @PathVariable Integer id_usuario){
        return services.editar(usuariosMantenimientoColletion,id_usuario);
    }

    @DeleteMapping("/eliminar/{id_usuarios}")
    public void eliminarUsuarios(@PathVariable Integer id_usuarios){
        services.eliminar(id_usuarios);
    }

    //trearPorid
    @GetMapping("/traer-por-id/{id_usuario}")
    public Optional<UsuariosMantenimientoColletion> traerPorSuId(@PathVariable Integer id_usuario){
        return services.traerPorId(id_usuario);
    }

}
