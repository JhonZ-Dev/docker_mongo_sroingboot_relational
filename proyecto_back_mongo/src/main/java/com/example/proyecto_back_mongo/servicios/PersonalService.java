package com.example.proyecto_back_mongo.servicios;

import com.example.proyecto_back_mongo.documentos.MantenimientoCollection;
import com.example.proyecto_back_mongo.documentos.PersonalCollection;
import com.example.proyecto_back_mongo.repositorios.PersonalCollectionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalService {
    //metodo para guardar

    @Autowired
    public PersonalCollectionRepositorio repositorio;
    public PersonalCollection guardar(PersonalCollection collection){
        collection.setId_personal(generarNuevoId());
        return repositorio.save(collection);
    }

    public List<PersonalCollection> listar(){
        return repositorio.findAll();
    }
    private int generarNuevoId() {
        List<PersonalCollection> mantenimientos = repositorio.findAll(Sort.by(Sort.Direction.DESC, "id_personal"));

        if (!mantenimientos.isEmpty()) {
            return mantenimientos.get(0).getId_personal() + 1;
        } else {
            return 1; // Si no hay documentos, comenzar desde 1
        }
    }
}
