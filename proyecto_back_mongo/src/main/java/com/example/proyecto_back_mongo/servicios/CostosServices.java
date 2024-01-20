package com.example.proyecto_back_mongo.servicios;

import com.example.proyecto_back_mongo.documentos.CostoMantenimiento;
import com.example.proyecto_back_mongo.documentos.MantenimientoCollection;
import com.example.proyecto_back_mongo.repositorios.CostoRepositorio;
import com.example.proyecto_back_mongo.repositorios.MantenimientoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CostosServices {
    @Autowired
    public CostoRepositorio repositorio;

    @Autowired
    public MantenimientoRepositorio mantenimientoCollectionRepository;


    //metodo para guardar un costo dependiendo del id_mantenimeinto
    public CostoMantenimiento guardarConIdMantenimiento(
            CostoMantenimiento costoMantenimiento
    ){
        costoMantenimiento.setId_costos(generarNuevoId());
       return repositorio.save(costoMantenimiento);
    }

    public List<CostoMantenimiento> listar(){
        return repositorio.findAll();
    }

    private int generarNuevoId() {
        List<CostoMantenimiento> mantenimientos = repositorio.findAll(Sort.by(Sort.Direction.DESC, "id_costos"));

        if (!mantenimientos.isEmpty()) {
            return mantenimientos.get(0).getId_costos() + 1;
        } else {
            return 1; // Si no hay documentos, comenzar desde 1
        }
    }

    public CostoMantenimiento guardarConMantenimiento(CostoMantenimiento costoMantenimiento, Long id_mantenimiento) {
        // Generar nuevo id para CostoMantenimiento
        costoMantenimiento.setId_costos(generarNuevoId());

        // Buscar el MantenimientoCollection por su id
        Optional<MantenimientoCollection> optionalMantenimiento = mantenimientoCollectionRepository.findById(Math.toIntExact(id_mantenimiento));

        // Verificar si el MantenimientoCollection existe
        if (optionalMantenimiento.isPresent()) {
            MantenimientoCollection mantenimientoCollection = optionalMantenimiento.get();

            // Asignar el MantenimientoCollection a la lista de mantenimientos
            costoMantenimiento.setId_mantenimiento(Collections.singletonList(mantenimientoCollection));

            // Guardar el CostoMantenimiento
            return repositorio.save(costoMantenimiento);
        } else {
            // Manejar el caso cuando no se encuentra el MantenimientoCollection
            throw new RuntimeException("Mantenimiento con id " + id_mantenimiento + " no encontrado.");
        }
    }

}
