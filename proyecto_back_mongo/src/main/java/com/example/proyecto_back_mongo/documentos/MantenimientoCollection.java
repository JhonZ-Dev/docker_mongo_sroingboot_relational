package com.example.proyecto_back_mongo.documentos;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "coleccion_mantenimiento")
@Data
public class MantenimientoCollection {

    @Id
    private int id_mantenimiento;
    private String problema;
    private String solucion;
    private String fecha_llegada_problema;
    private String fecha_solucion_problema;
    private String dias_espera_solucion;
}
