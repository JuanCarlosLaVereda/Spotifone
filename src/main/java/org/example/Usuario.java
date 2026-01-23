package org.example;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nick;

    @Column(unique = true)
    private String email;

    @OneToOne
    @JoinColumn(name = "id_suscripcion")
    private Suscripcion suscripcion;

    @OneToMany(mappedBy = "usuarioPropietario", cascade = CascadeType.ALL)
    private List<PlayList> playLists = new ArrayList<>();


    public Usuario() {}

    public Long getId() {
        return id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
