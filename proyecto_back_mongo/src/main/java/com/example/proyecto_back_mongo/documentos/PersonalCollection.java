package com.example.proyecto_back_mongo.documentos;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "Personal")
@Data
public class PersonalCollection {
    @Id
    @Indexed(unique = true)
    private int id_personal;
    private String edad;
    private LocalDate fecha_nacimiento;
    private String nombre, apellido, telefono, email;


}
