package ar.edu.utn.dds.k3003.repositories;


import ar.edu.utn.dds.k3003.model.Heladera;
import ar.edu.utn.dds.k3003.model.Temperatura;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TemperaturaRepository {
    //private Collection<Temperatura> temperaturas;
    static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tpdb");;
    EntityManager entityManager= entityManagerFactory.createEntityManager(); ;

    public TemperaturaRepository() {

        //this.temperaturas = new ArrayList<Temperatura>();
    }

    public Temperatura save(Temperatura temperatura) {

        entityManager.getTransaction().begin();
        entityManager.persist(temperatura); //se prepara insert
        entityManager.getTransaction().commit();//se ejecutan las sentencias

        return temperatura;
    }

    public List<Temperatura> findById(Long idHeladera) {
        List<Temperatura> listaTemps = entityManager.createQuery("from Temperatura", Temperatura.class).getResultList();
        return listaTemps;
    }
}
