package org.example;


import org.example.models.Comentario;
import org.example.models.Usuario;
import org.example.services.Service;

public class Main {
    public static void main(String[] args) {

        Service s = new Service(ObjectDBUtil.getEntityManagerFactory());


//        Usuario u = new Usuario();
//        u.setCorreo("vanesa@gmail.com");
//        u.setNombre("Vanesa");
//        s.saveUsuario(u);

//        Comentario c = new Comentario();
//        c.setContenido("Me ha gustado mucho la película");
//        c.setValoracion(10);
//        s.saveComentarioToUsuario(c, "vanesa@gmail.com");

        s.getUsuariosConValoracionMaxima().forEach(System.out::println);





    }
}