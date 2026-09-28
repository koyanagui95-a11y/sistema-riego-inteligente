
package com.sistema.sistema_riego_inteligente.Entidad;


import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "lecturas")
public class Lectura implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer humedad;

    @Column(nullable = false)
    private Boolean bombaActiva;

    private LocalDateTime fechaRegistro;

    @PrePersist
    public void prePersist() {
        this.fechaRegistro = LocalDateTime.now();
    }

    public Lectura() {}

    public Lectura(Long id, Integer humedad, Boolean bombaActiva, LocalDateTime fechaRegistro) {
        this.id = id;
        this.humedad = humedad;
        this.bombaActiva = bombaActiva;
        this.fechaRegistro = fechaRegistro;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getHumedad() { return humedad; }
    public void setHumedad(Integer humedad) { this.humedad = humedad; }

    public Boolean getBombaActiva() { return bombaActiva; }
    public void setBombaActiva(Boolean bombaActiva) { this.bombaActiva = bombaActiva; }

    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }
}
