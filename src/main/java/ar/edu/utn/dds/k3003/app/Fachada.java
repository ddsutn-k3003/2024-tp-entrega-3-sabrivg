package ar.edu.utn.dds.k3003.app;

import ar.edu.utn.dds.k3003.facades.FachadaHeladeras;
import ar.edu.utn.dds.k3003.facades.FachadaViandas;
import ar.edu.utn.dds.k3003.facades.dtos.*;
import ar.edu.utn.dds.k3003.model.Heladera;
import ar.edu.utn.dds.k3003.model.Temperatura;
import ar.edu.utn.dds.k3003.repositories.HeladeraMapper;
import ar.edu.utn.dds.k3003.repositories.HeladerasRepository;
import ar.edu.utn.dds.k3003.repositories.TemperaturaMapper;
import ar.edu.utn.dds.k3003.repositories.TemperaturaRepository;

import java.time.LocalDateTime;
import java.util.*;

public class Fachada implements FachadaHeladeras {

 private final HeladerasRepository heladerasRepository;
 private final HeladeraMapper heladeraMapper;
 private final TemperaturaRepository temperaturaRepository;
 private final TemperaturaMapper temperaturaMapper;
 private FachadaViandas fachadaViandas;

 public Fachada() {
  this.heladerasRepository = new HeladerasRepository();
  this.heladeraMapper = new HeladeraMapper();
  this.temperaturaRepository=new TemperaturaRepository();
  this.temperaturaMapper=new TemperaturaMapper();
 }
    public Fachada(HeladerasRepository heladerasRepository, HeladeraMapper heladeraMapper, TemperaturaRepository temperaturaRepository, TemperaturaMapper temperaturaMapper) {
        this.heladerasRepository = heladerasRepository;
        this.heladeraMapper = heladeraMapper;
        this.temperaturaRepository = temperaturaRepository;
        this.temperaturaMapper = temperaturaMapper;
    }

    @Override public HeladeraDTO agregar(HeladeraDTO heladeraDTO){
    Heladera heladera= new Heladera(heladeraDTO.getId().longValue(),heladeraDTO.getNombre());
    heladera = this.heladerasRepository.save(heladera);
    return heladeraMapper.map(heladera);
    }

    @Override public void depositar(Integer heladeraId, String qrVianda) throws NoSuchElementException{
      Heladera heladera= heladerasRepository.findById(heladeraId.longValue());
      this.fachadaViandas.buscarXQR(qrVianda);
      heladera.depositarVianda(qrVianda);
       fachadaViandas.modificarEstado(qrVianda, EstadoViandaEnum.DEPOSITADA);
    }

    @Override public Integer cantidadViandas(Integer heladeraId) throws NoSuchElementException{
      Heladera heladera=heladerasRepository.findById(heladeraId.longValue());
      return heladera.getViandas().size();
 }

    @Override public void retirar(RetiroDTO retiro) throws NoSuchElementException{
        Heladera heladera=heladerasRepository.findById(retiro.getHeladeraId().longValue());
        ViandaDTO viandaDTO=this.fachadaViandas.buscarXQR(retiro.getQrVianda());
        try {
            heladera.retirarVianda(retiro.getQrVianda());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        fachadaViandas.modificarEstado(viandaDTO.getCodigoQR(),EstadoViandaEnum.RETIRADA);

    }

    @Override public void temperatura(TemperaturaDTO temperaturaDTO){
     Temperatura temperatura=new Temperatura(temperaturaDTO.getTemperatura(),temperaturaDTO.getHeladeraId(), LocalDateTime.now());
     Heladera heladera=this.heladerasRepository.findById(temperatura.getHeladeraId().longValue());
     temperatura=this.temperaturaRepository.save(temperatura);
    }

    @Override public List<TemperaturaDTO> obtenerTemperaturas(Integer heladeraId){
     Heladera heladera=heladerasRepository.findById(heladeraId.longValue());
     List<Temperatura> temperaturas= temperaturaRepository.findById(heladeraId.longValue());
     List<TemperaturaDTO> temperaturaDTOS=new ArrayList<TemperaturaDTO>();
     for (Temperatura t: temperaturas) {
        TemperaturaDTO temp=new TemperaturaDTO(t.getTemperatura(),t.getHeladeraId(),t.getFechaMedicion());
        temperaturaDTOS.add(temp);
        }
     Collections.reverse(temperaturaDTOS);
     return temperaturaDTOS;
    }

    @Override public void setViandasProxy(FachadaViandas viandas){
     this.fachadaViandas=viandas;
    }

    public HeladeraDTO obtenerHeladera(Integer id){
      return heladeraMapper.map(heladerasRepository.findById(id.longValue()));
    }
}
