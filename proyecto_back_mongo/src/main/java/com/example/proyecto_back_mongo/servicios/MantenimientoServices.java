package com.example.proyecto_back_mongo.servicios;

import com.example.proyecto_back_mongo.documentos.MantenimientoCollection;
import com.example.proyecto_back_mongo.repositorios.MantenimientoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class MantenimientoServices {
    @Autowired
    public MantenimientoRepositorio repositorio;
    //metodo para guardar

    public MantenimientoCollection guardar(MantenimientoCollection collection){
        //asignar
        collection.setId_mantenimiento(generarNuevoId());
        //calcular
        calcularDiasEsperaSolucion(collection);
        return repositorio.save(collection);
    }

    //metodo para listar
    public List<MantenimientoCollection> listar(){
        return repositorio.findAll();
    }

    private int generarNuevoId() {
        List<MantenimientoCollection> mantenimientos = repositorio.findAll(Sort.by(Sort.Direction.DESC, "id_mantenimiento"));

        if (!mantenimientos.isEmpty()) {
            return mantenimientos.get(0).getId_mantenimiento() + 1;
        } else {
            return 1; // Si no hay documentos, comenzar desde 1
        }
    }

    private void calcularDiasEsperaSolucion(MantenimientoCollection collection) {
        // No es necesario parsear las fechas porque ahora son LocalDate

        // Calcular la diferencia en días
        long diasEspera = ChronoUnit.DAYS.between(collection.getFecha_llegada_problema(), collection.getFecha_posiblesolucion_problema());

        // Asignar la diferencia como días de espera de solución
        collection.setDias_espera_solucion(String.valueOf(diasEspera));
    }


}
