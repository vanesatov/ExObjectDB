package org.example.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Usuario implements Serializable {
    private static final long serialVersionUID = 2L;
@Id
    @GeneratedValue
    private Long id;
    private String correo;
    private String nombre;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Comentario> comentarios= new ArrayList<>();

    public void addComentario(Comentario c) {
        c.setUsuario(this);
        this.comentarios.add(c);
    }
}
