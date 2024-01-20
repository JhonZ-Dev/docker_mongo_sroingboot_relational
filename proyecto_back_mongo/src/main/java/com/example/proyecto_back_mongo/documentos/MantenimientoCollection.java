package com.example.proyecto_back_mongo.documentos;

import lombok.Data;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "Mantenimientos")
@Data
public class MantenimientoCollection {

    @Id
    @Indexed(unique = true)
    private int id_mantenimiento;
    private String problema;
    private String posible_solucion;
    private LocalDate fecha_llegada_problema;
    private LocalDate fecha_posiblesolucion_problema;
    private String dias_espera_solucion;

    @DBRef
    private CostoMantenimiento costoMantenimiento;

}
