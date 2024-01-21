package com.example.proyecto_back_mongo.servicios;

import com.example.proyecto_back_mongo.documentos.MantenimientoCollection;
import com.example.proyecto_back_mongo.documentos.UsuariosMantenimientoColletion;
import com.example.proyecto_back_mongo.repositorios.UsuariosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServices {
    @Autowired
    public UsuariosRepositorio repositorio;

    public UsuariosMantenimientoColletion guardarResTemplate(UsuariosMantenimientoColletion usuariosMantenimientoColletion){
        //asignar
        usuariosMantenimientoColletion.setId_usuario(generarNuevoId());
        return repositorio.save(usuariosMantenimientoColletion);
    }


    private int generarNuevoId() {
        List<UsuariosMantenimientoColletion> mantenimientos = repositorio.findAll(Sort.by(Sort.Direction.DESC, "id_usuario"));

        if (!mantenimientos.isEmpty()) {
            return mantenimientos.get(0).getId_usuario() + 1;
        } else {
            return 1; // Si no hay documentos, comenzar desde 1
        }
    }
}
