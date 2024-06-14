package ar.edu.utn.dds.k3003.repositories;
import ar.edu.utn.dds.k3003.model.Heladera;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class HeladerasRepository {

        private static AtomicLong seqId = new AtomicLong();
        private final Collection<Heladera> heladeras;

        public HeladerasRepository() {
            this.heladeras = new ArrayList<>();
        }

        public Heladera save(Heladera heladera) {
            if (Objects.isNull(heladera.getId())) {
                heladera.setId(seqId.getAndIncrement());
            }
            this.heladeras.add(heladera);
            return heladera;
        }

        public Heladera findById(Long id) {
            Optional<Heladera> first = this.heladeras.stream().filter(x -> x.getId().equals(id)).findFirst();
            return first.orElseThrow(() -> new NoSuchElementException(
                    String.format("No hay una heladera de id: %s", id)
            ));
        }
}
