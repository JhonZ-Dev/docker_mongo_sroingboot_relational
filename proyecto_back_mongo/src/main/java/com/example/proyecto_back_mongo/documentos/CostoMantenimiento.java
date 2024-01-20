package com.example.proyecto_back_mongo.documentos;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "Costos")
@Data
public class CostoMantenimiento {

    @Id
    @Indexed(unique = true)
    private int id_costos;
    private String descripcion;
    private String materiales_implementados;
    private Double costo_inicial = 0.0;
    private LocalDate fecha_llegada_problema;
    private LocalDate fecha_posiblesolucion_problema;
    private String precio_final_por_dias;
    @Indexed
    private int id_mantenimiento; // Referencia al id_mantenimiento en la otra colección


}
