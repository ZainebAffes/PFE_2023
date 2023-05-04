package com.csys.workflowDemande.web.rest;

import com.csys.workflowDemande.domain.Etiquetteparametragedemande;
import com.csys.workflowDemande.dto.EtiquetteparametragedemandeDTO;
import com.csys.workflowDemande.service.EtiquetteparametragedemandeService;
import java.lang.Integer;
import java.lang.String;
import java.lang.Void;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EtiquetteparametragedemandeResource {
  private static final String ENTITY_NAME = "etiquetteparametragedemande";

  private final EtiquetteparametragedemandeService etiquetteparametragedemandeService;

  private final Logger log = LoggerFactory.getLogger(EtiquetteparametragedemandeService.class);

  public EtiquetteparametragedemandeResource(EtiquetteparametragedemandeService etiquetteparametragedemandeService) {
    this.etiquetteparametragedemandeService=etiquetteparametragedemandeService;
  }

  @GetMapping("/etiquetteparametragedemandes/{id}")
  public ResponseEntity<EtiquetteparametragedemandeDTO> getEtiquetteparametragedemande(@PathVariable Integer id) {
    log.debug("Request to get Etiquetteparametragedemande: {}",id);
    EtiquetteparametragedemandeDTO dto = etiquetteparametragedemandeService.findOne(id);
    return ResponseEntity.ok().body(dto);
  }
@GetMapping("/etiquetteparametragedemandes")
  public List<EtiquetteparametragedemandeDTO> getAllEtiquetteparametragedemandes() {
    log.debug("Request to get all  Etiquetteparametragedemandes : {}");
    return etiquetteparametragedemandeService.findAll();
  }

  @DeleteMapping("/etiquetteparametragedemandes/{id}")
  public ResponseEntity<Void> deleteEtiquetteparametragedemande(@PathVariable Integer id) {
    log.debug("Request to delete Etiquetteparametragedemande: {}",id);
    etiquetteparametragedemandeService.delete(id);
    return ResponseEntity.ok().build();
  }
}

