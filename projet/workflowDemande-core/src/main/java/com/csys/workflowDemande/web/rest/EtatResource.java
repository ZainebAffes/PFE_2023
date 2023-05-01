package com.csys.workflowDemande.web.rest;

import com.csys.workflowDemande.dto.EtatDTO;
import com.csys.workflowDemande.service.EtatService;
import java.lang.String;
import java.lang.Void;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
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
public class EtatResource {
  private static final String ENTITY_NAME = "etat";

  private final EtatService etatService;

  private final Logger log = LoggerFactory.getLogger(EtatService.class);

  public EtatResource(EtatService etatService) {
    this.etatService=etatService;
  }

  @PostMapping("/etats")
  public ResponseEntity<EtatDTO> createEtat(@Valid @RequestBody EtatDTO etatDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
    log.debug("REST request to save Etat : {}", etatDTO);
    if (bindingResult.hasErrors()) {
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    if ( etatDTO.getCode()!= null ||  !etatDTO.getCode().isEmpty() ) {
      bindingResult.addError( new FieldError("EtatDTO","id","POST method does not accepte "+ENTITY_NAME+" with code"));
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    EtatDTO result = etatService.save(etatDTO);
    return ResponseEntity.created( new URI("/api/etats/"+ result.getCode())).body(result);
  }

//  @PutMapping("/etats")
//  public ResponseEntity<EtatDTO> updateEtat(@Valid @RequestBody EtatDTO etatDTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
//    log.debug("Request to update Etat: {}",etatDTO);
//    if (bindingResult.hasErrors()) {
//      throw new MethodArgumentNotValidException(null, bindingResult);
//    }
//    if ( etatDTO.getCode()== null ||  etatDTO.getCode().isEmpty() ) {
//      bindingResult.addError( new FieldError("EtatDTO","id","PUT method does not accepte "+ENTITY_NAME+" with code"));
//      throw new MethodArgumentNotValidException(null, bindingResult);
//    }
//    EtatDTO result =etatService.update(etatDTO);
//    return ResponseEntity.ok().body(result);
//  }

//  @GetMapping("/etats/{id}")
//  public ResponseEntity<EtatDTO> getEtat(@PathVariable String id) {
//    log.debug("Request to get Etat: {}",id);
//    EtatDTO dto = etatService.findById(id);
//    return ResponseEntity.ok().body(dto);
//  }
     @GetMapping("/etats")
  public Collection<EtatDTO> getAllEtats() {
    log.debug("Request to get all  Etats : {}");
    return etatService.findAll();
  }

  @DeleteMapping("/etats/{id}")
  public ResponseEntity<Void> deleteEtat(@PathVariable String id) {
    log.debug("Request to delete Etat: {}",id);
    etatService.delete(id);
    return ResponseEntity.ok().build();
  }
}

