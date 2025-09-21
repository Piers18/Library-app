package com.codigo.spring_boot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "pilotos")
public class PilotoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_piloto")
    private int id;

    private String nombre;
    private String apellido;
    private boolean estado;
    private String dni;

    @ManyToMany
    @JoinTable(
            name = "vuelo_piloto",
            joinColumns = @JoinColumn(name = "id_piloto_fk"),
            inverseJoinColumns = @JoinColumn(name = "id_vuelo_fk")
    )
    private List<VueloEntity> vueloEntities;
}
