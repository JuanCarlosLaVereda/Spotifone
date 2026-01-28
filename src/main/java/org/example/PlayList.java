package org.example;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "playlists")
public class PlayList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    @Lob
    @Column
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "playLists")
    private Usuario usuarioPropietario;

    @ManyToMany(mappedBy = "playLists")
    private List<Cancion> canciones = new ArrayList<>();

    public PlayList(String nombre, String descripcion, Usuario usuarioPropietario) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.usuarioPropietario = usuarioPropietario;
    }

    public PlayList() {}

    public void addCancion(Cancion cancion) {
        canciones.add(cancion);
        cancion.addPlayList(this);
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
