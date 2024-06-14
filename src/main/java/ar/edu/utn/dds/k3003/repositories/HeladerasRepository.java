package ar.edu.utn.dds.k3003.repositories;
import ar.edu.utn.dds.k3003.model.Heladera;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class HeladerasRepository {

    static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tpdb");;
    EntityManager entityManager= entityManagerFactory.createEntityManager(); ;


    public HeladerasRepository() {

        //this.heladeras = new ArrayList<>();
        }

        public Heladera save(Heladera heladera) {
            entityManager.getTransaction().begin();
            entityManager.persist(heladera); //se prepara insert
            entityManager.getTransaction().commit();//se ejecutan las sentencias

            return heladera;
        }

        public Heladera findById(Long id) {
            List<Heladera> heladeras = entityManager.createQuery("from Heladera",Heladera.class).getResultList();
            Optional<Heladera> first = heladeras.stream().filter(x -> x.getId().equals(id)).findFirst();
            return first.orElseThrow(() -> new NoSuchElementException(
                    String.format("No hay una heladera de id: %s", id)
            ));
        }
}
