package org.example;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "canciones")
public class Cancion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String titulo;

    @Column
    private Integer duracion;

    @ManyToOne
    @JoinColumn(name = "artista")
    private Artista artistaCancion;

    @ManyToMany
    @JoinTable(
            name = "playlist_contenido",
            joinColumns = @JoinColumn(name = "id_cancion"),
            inverseJoinColumns = @JoinColumn(name = "id_playlist")
    )
    private List<PlayList> playLists = new ArrayList<>();

    public Cancion(String titulo, Integer duracion) {
        this.titulo = titulo;
        this.duracion = duracion;
    }

    public Cancion() {}

    public void addPlayList(PlayList playList) {
        playLists.add(playList);
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Artista getArtistaCancion() {
        return artistaCancion;
    }

    public void setArtistaCancion(Artista artistaCancion) {
        this.artistaCancion = artistaCancion;
    }
}
