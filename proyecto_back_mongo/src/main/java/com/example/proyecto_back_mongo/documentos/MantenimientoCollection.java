package com.example.proyecto_back_mongo.documentos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "coleccion_mantenimiento")
public class MantenimientoCollection {

    @Id
    private int id_mantenimineto;
    private String problema;
    private String solucion;
    private String fecha_llegada_problema;
    private String fecha_solucion_problema;
    private String dias_espera_solucion;
}
