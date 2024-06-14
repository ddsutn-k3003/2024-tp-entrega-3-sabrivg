package ar.edu.utn.dds.k3003.repositories;


import ar.edu.utn.dds.k3003.model.Temperatura;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TemperaturaRepository {
    private Collection<Temperatura> temperaturas;
    private static AtomicLong seqId = new AtomicLong();

    public TemperaturaRepository() {
        this.temperaturas = new ArrayList<Temperatura>();
    }

    public Temperatura save(Temperatura temperatura) {
        if (Objects.isNull(temperatura.getId())) {
            temperatura.setId(seqId.getAndIncrement());
        }
        this.temperaturas.add(temperatura);
        return temperatura;
    }

    public List<Temperatura> findById(Long idHeladera) {
        List<Temperatura> listaTemps = this.temperaturas.stream().filter(x -> x.getHeladeraId().equals(idHeladera)).toList();
        return listaTemps;
    }
}
