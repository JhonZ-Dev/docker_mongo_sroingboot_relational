package com.example.proyecto_back_mongo.servicios;

import com.example.proyecto_back_mongo.documentos.MantenimientoCollection;
import com.example.proyecto_back_mongo.repositorios.MantenimientoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

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

    private MantenimientoCollection buscarPorId(Long id_mantenimiento){
        MantenimientoCollection mantenimientoCollection =
                this.repositorio.findById(Math.toIntExact(id_mantenimiento)).orElse(null);
        return mantenimientoCollection;
    }

    public MantenimientoCollection editar(MantenimientoCollection mantenimientoCollection,
                                          Integer id_mantenimiento){
        //verificar si existe el ID
        MantenimientoCollection manteExiste = buscarPorId(Long.valueOf(id_mantenimiento));
        if(manteExiste !=null){
            //actualizar lso campos
            manteExiste.setProblema(mantenimientoCollection.getProblema());
            manteExiste.setPosible_solucion(mantenimientoCollection.getPosible_solucion());
            manteExiste.setFecha_llegada_problema(mantenimientoCollection.getFecha_llegada_problema());
            manteExiste.setFecha_posiblesolucion_problema(mantenimientoCollection.getFecha_posiblesolucion_problema());
            return repositorio.save(manteExiste);
        }else{
            throw new RuntimeException("No se encontró el mantenimiento con ID: " + id_mantenimiento);

        }

    }

    //metodo para eliminar
    public void eliminar(Integer id_mantenimiento){
        repositorio.deleteById(id_mantenimiento);
    }
    //traerPorId
    public Optional<MantenimientoCollection>traerPorId(Integer id_mantenimiento){
        return repositorio.findById(id_mantenimiento);
    }
}
