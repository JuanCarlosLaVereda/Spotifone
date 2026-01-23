package org.example;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "suscripciones")
public class Suscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoSuscripcion tipoSuscripcion;

    @Column
    private LocalDate fechaRenovacion;

    @Column
    private Double precio;

    @OneToOne(mappedBy = "suscripcion")
    private Usuario usuario;

    public Suscripcion() {}

    public Long getId() {
        return id;
    }

    public TipoSuscripcion getTipoSuscripcion() {
        return tipoSuscripcion;
    }

    public void setTipoSuscripcion(TipoSuscripcion tipoSuscripcion) {
        this.tipoSuscripcion = tipoSuscripcion;
    }

    public LocalDate getFechaRenovacion() {
        return fechaRenovacion;
    }

    public void setFechaRenovacion(LocalDate fechaRenovacion) {
        this.fechaRenovacion = fechaRenovacion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
