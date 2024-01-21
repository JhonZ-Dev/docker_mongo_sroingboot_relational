package com.example.proyecto_back_mongo.documentos;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.annotation.Collation;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Usuarios")
@Data
public class UsuariosMantenimientoColletion {
    @Id
    @Indexed(unique = true)
    private int id_usuario;
    private String nombre_usuario;
    private String contrasenia_usuario;
    private String rol_usuario;

    //relacion de muchos a uno con mantenimiento
    @DBRef
    private MantenimientoCollection mantenimientoCollection;

}
