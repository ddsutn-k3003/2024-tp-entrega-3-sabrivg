package ar.edu.utn.dds.k3003.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="temperatura")
public class Temperatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Integer temperatura;
    @Column
    private Integer heladeraId;
    @Column
    private LocalDateTime fechaMedicion;

    public Temperatura(Integer temperatura, Integer heladeraId, LocalDateTime fechaMedicion) {
        this.temperatura = temperatura;
        this.heladeraId = heladeraId;
        this.fechaMedicion = fechaMedicion;
    }


    public Temperatura() {
    }
}
