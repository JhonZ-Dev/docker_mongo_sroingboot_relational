package com.example.proyecto_back_mongo.servicios;


import com.example.proyecto_back_mongo.documentos.UsuariosMantenimientoColletion;
import com.example.proyecto_back_mongo.repositorios.UsuariosRepositorio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServices {
    @Autowired
    public UsuariosRepositorio repositorio;

    @Autowired
    public RestTemplate restTemplate;

    private static final Logger log = LoggerFactory.getLogger(UsuariosMantenimientoColletion.class);

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

    //listar
    public List<UsuariosMantenimientoColletion> listar(){
        return repositorio.findAll();
    }

    //buscarPorId

    private UsuariosMantenimientoColletion buscarPorId(Long id_usuario){
        UsuariosMantenimientoColletion usuariosMantenimientoColletion =
                this.repositorio.findById(id_usuario).orElse(null);
        return usuariosMantenimientoColletion;
    }

    //editar
    public UsuariosMantenimientoColletion editar(UsuariosMantenimientoColletion usuariosMantenimientoColletion,
                                                 Integer id_usuario) {
        UsuariosMantenimientoColletion existe = buscarPorId(Long.valueOf(id_usuario));

        if ("mantenimiento".equalsIgnoreCase(usuariosMantenimientoColletion.getRol_usuario())) {
            // Si el rol es mantenimiento, actualizar en MongoDB
            return actualizarEnMongoDB(existe, usuariosMantenimientoColletion);
        } else {
            // Si no es mantenimiento, enviar a PostgreSQL
            return enviarAPostgreSQL(usuariosMantenimientoColletion);
        }
    }

    private UsuariosMantenimientoColletion actualizarEnMongoDB(UsuariosMantenimientoColletion existe,
                                                               UsuariosMantenimientoColletion actualizacion) {
        if (existe != null) {
            existe.setNombre_usuario(actualizacion.getNombre_usuario());
            existe.setContrasenia_usuario(actualizacion.getContrasenia_usuario());
            existe.setRol_usuario(actualizacion.getRol_usuario());
            return repositorio.save(existe);
        } else {
            throw new RuntimeException("No se encontró el mantenimiento con ID: " + actualizacion.getId_usuario());
        }
    }

    private UsuariosMantenimientoColletion enviarAPostgreSQL(UsuariosMantenimientoColletion usuariosMantenimientoColletion) {
        String urlPostgres = "http://172.18.0.3:8081/datos/restTemplate/insertar";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UsuariosMantenimientoColletion> requestEntity = new HttpEntity<>(usuariosMantenimientoColletion, headers);

        try {
            ResponseEntity<UsuariosMantenimientoColletion> response =
                    restTemplate.postForEntity(urlPostgres, requestEntity, UsuariosMantenimientoColletion.class);
            log.info("Respuesta de PostgresDB: {}", response.getBody());
            return response.getBody();
        } catch (HttpClientErrorException e) {
            log.error("Error al enviar la solicitud a Postgres", e);
            throw new RuntimeException("Error al enviar la solicitud a Postgres", e);
        }
    }

    //traerPorId
    public Optional<UsuariosMantenimientoColletion> traerPorId(Integer id_usuario){
        return repositorio.findById(Long.valueOf(id_usuario));
    }

    //eliminar
    public void eliminar(Integer id_usuario){
        repositorio.deleteById(Long.valueOf(id_usuario));
    }


}
