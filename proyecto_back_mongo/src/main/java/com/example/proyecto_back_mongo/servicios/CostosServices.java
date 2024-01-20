package com.example.proyecto_back_mongo.servicios;

import com.example.proyecto_back_mongo.documentos.CostoMantenimiento;
import com.example.proyecto_back_mongo.documentos.MantenimientoCollection;
import com.example.proyecto_back_mongo.repositorios.CostoRepositorio;
import com.example.proyecto_back_mongo.repositorios.MantenimientoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
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
            calcularDiasEsperaSolucion(costoMantenimiento);
            return repositorio.save(costoMantenimiento);
        } else {
            // Manejar el caso cuando no se encuentra el MantenimientoCollection
            throw new RuntimeException("Mantenimiento con id " + id_mantenimiento + " no encontrado.");
        }
    }

    private void calcularDiasEsperaSolucion(CostoMantenimiento collection) {
        // No es necesario parsear las fechas porque ahora son LocalDate

        // Calcular la diferencia en días
        long diasEspera = ChronoUnit.DAYS.between(collection.getFecha_llegada_problema(), collection.getFecha_posiblesolucion_problema());

        // Calcular el monto adicional por el 12% de IVA
        double ivaAdicional = diasEspera * collection.getIvaPorcentaje();

        // Sumar el 12% de IVA al resultado de días de espera
        double precioFinalPorDiasConIva = diasEspera + ivaAdicional;

        // Asignar el resultado como precio final por días con IVA
        collection.setPrecio_final_por_dias(String.valueOf(precioFinalPorDiasConIva));
    }


    //borrar
    public void eliminar(Integer id_costos){
        repositorio.deleteById(id_costos);
    }

    //metodo para traer por id
   public Optional<CostoMantenimiento> traerPorId(Integer id_costos){
        return repositorio.findById(id_costos);
    }

    //metodo para editar
    public CostoMantenimiento edit(CostoMantenimiento costoMantenimiento,
                                   Integer id_costos){
       CostoMantenimiento costoExiste = buscarPorId(id_costos);
        if(costoExiste !=null){
            //actaulizar los campos
            costoExiste.setDescripcion(costoMantenimiento.getDescripcion());
            costoExiste.setFecha_llegada_problema(costoMantenimiento.getFecha_llegada_problema());
            costoExiste.setFecha_posiblesolucion_problema(costoMantenimiento.getFecha_posiblesolucion_problema());
            //guardar
            return  repositorio.save(costoExiste);

        }else{
            throw new RuntimeException("No se encontró el costo con ID: " + id_costos);

        }
    }

    public CostoMantenimiento buscarPorId(Integer id_costos){
        CostoMantenimiento costos = this.repositorio.findById(id_costos).orElse(null);
        return costos;
    }

}
