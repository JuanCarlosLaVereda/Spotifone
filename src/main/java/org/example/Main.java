package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Spotifone");

        EntityManager em = emf.createEntityManager();

        try {
            //Mision A
            em.getTransaction().begin();

            Usuario usuario1 = new Usuario("Manolito", "asd@gmail.com");

            Suscripcion suscripcion = new Suscripcion(TipoSuscripcion.PREMIUM, LocalDate.now().plusMonths(1), 15.0, usuario1);
            usuario1.setSuscripcion(suscripcion);
            em.persist(suscripcion);
            //¿se guarda el usuario?
            //NO

            //¿Por qué?
            //Porque el usuario no existe

            //¿necesitas cascada?
            //SI

            //¿Cuál y por qué?
            //


            //MISION B
            Artista rosalia = new Artista("Rosalia", "Española", GeneroMusical.TRAP);

            Cancion cancion1 = new Cancion("Despechá", 33);
            Cancion cancion2 = new Cancion("Bizcochito", 33);
            rosalia.addCancion(cancion1);
            rosalia.addCancion(cancion2);

            em.persist(rosalia);




            em.getTransaction().commit();



        } catch (Exception ex) {
            System.err.println("Error genérico");
            ex.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
