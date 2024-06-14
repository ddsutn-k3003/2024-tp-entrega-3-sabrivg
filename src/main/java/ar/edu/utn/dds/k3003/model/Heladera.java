package ar.edu.utn.dds.k3003.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Data
@Entity
@Table(name="heladera")
public class Heladera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition ="varchar")
    private String nombre;

    @Transient
    List<String> viandas;
    @Column
    int capacidadMax; //en unidad de viandas
    @Column
    LocalDate fechaInicioFuncionamiento;
    @Transient
    boolean activa;
   // Collection<Integer> temperaturas;
   @Transient
    float longitud;
    @Transient
    float latitud;
    @Transient
    String direccion;
    @Transient
    String ciudad;
    @Transient
    float temperaturaMin;
    @Transient
    float temperaturaMax;
    @Transient
    long cantidadAperturas;

    public Heladera(Long id,String nombre) {
        this.id = id;
        this.nombre=nombre;
        this.fechaInicioFuncionamiento=LocalDate.now();
        this.activa=true;
        //this.capacidadMax=20;//por ahora
        this.cantidadAperturas=0;
        this.viandas=new ArrayList<String>();
    }

    public Heladera() {
    }

    public void depositarVianda(String qrVianda) {
        this.viandas.add(qrVianda);
        this.cantidadAperturas++;
    }

    public void retirarVianda(String qrVianda) throws Exception {
        this.cantidadAperturas++;
        if(!viandas.contains(qrVianda)) {
            throw new Exception("No existe la vianda en la heladera ");
        }
        this.viandas.remove(qrVianda);

    }
}
