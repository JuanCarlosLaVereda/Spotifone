package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
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

            em.getTransaction().commit();


            em.getTransaction().begin();
            //MISION B
            Artista rosalia = new Artista("Rosalia", "Española", GeneroMusical.TRAP);

            Cancion cancion1 = new Cancion("Despechá", 33);
            Cancion cancion2 = new Cancion("Bizcochito", 33);
            rosalia.addCancion(cancion1);
            rosalia.addCancion(cancion2);

            em.persist(rosalia);
            em.getTransaction().commit();

            //MISION C
            em.getTransaction().begin();

            TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.nick = :nick", Usuario.class).setParameter("nick", "Manolito");
            Usuario manolo = query.getSingleResult();


            PlayList playList = new PlayList("Playlist to wapa", "Esta playlist esta wapisima asi que le pongo una descripcion mu chula", manolo);

            TypedQuery<Cancion> query2 = em.createQuery("SELECT c FROM Cancion c WHERE c.artistaCancion = :artista", Cancion.class).setParameter("artista", rosalia);
            List<Cancion> canciones = query2.getResultList();

            if (!canciones.isEmpty()) {
                for (Cancion cancion : canciones) {
                    playList.addCancion(cancion);
                }
            }

            em.persist(playList);
            em.getTransaction().commit();

            //MISION D
            em.getTransaction().begin();

            TypedQuery<Artista> query3 = em.createQuery("SELECT a FROM Artista a",  Artista.class);
            List<Artista> artistas = query3.getResultList();

            for (Artista artista : artistas) {
                List<Cancion> canciones2 = artista.getCanciones();
                int duracion = 0;
                for (Cancion cancion : canciones2) {
                    duracion += cancion.getDuracion();
                }
                System.out.println("Artista: [" + artista.getNombre() + "] - Total Minutos: [" +duracion + "]");
            }

            em.getTransaction().commit();

            //MISION E
            em.getTransaction().begin();

            em.remove(manolo);

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
