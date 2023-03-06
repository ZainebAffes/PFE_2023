package com.csys.workflowDemande.web.rest;

import com.csys.workflowDemande.dto.TypeDemandeDTO;
import com.csys.workflowDemande.service.TypeDemandeService;
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
public class TypeDemandeResource {
  private static final String ENTITY_NAME = "typedemande";

  private final TypeDemandeService typedemandeService;

  private final Logger log = LoggerFactory.getLogger(TypeDemandeService.class);

  public TypeDemandeResource(TypeDemandeService typedemandeService) {
    this.typedemandeService=typedemandeService;
  }

  @PostMapping("/typedemandes")
  public ResponseEntity<TypeDemandeDTO> createTypeDemande(@Valid @RequestBody TypeDemandeDTO typedemandeDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
    log.debug("REST request to save TypeDemande : {}", typedemandeDTO);
    if (bindingResult.hasErrors()) {
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    if ( typedemandeDTO.getCodeTypeDemande() != null ||  !typedemandeDTO.getCodeTypeDemande().isEmpty() ) {
      bindingResult.addError( new FieldError("TypeDemandeDTO","codeTypeDemande","POST method does not accepte "+ENTITY_NAME+" with code"));
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    TypeDemandeDTO result = typedemandeService.save(typedemandeDTO);
    return ResponseEntity.created( new URI("/api/typedemandes/"+ result.getCodeTypeDemande())).body(result);
  }

  @PutMapping("/typedemandes")
  public ResponseEntity<TypeDemandeDTO> updateTypeDemande(@Valid @RequestBody TypeDemandeDTO typedemandeDTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
    log.debug("Request to update TypeDemande: {}",typedemandeDTO);
    if (bindingResult.hasErrors()) {
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    if ( typedemandeDTO.getCodeTypeDemande() == null ||  typedemandeDTO.getCodeTypeDemande().isEmpty() ) {
      bindingResult.addError( new FieldError("TypeDemandeDTO","codeTypeDemande","PUT method does not accepte "+ENTITY_NAME+" with code"));
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    TypeDemandeDTO result =typedemandeService.update(typedemandeDTO);
    return ResponseEntity.ok().body(result);
  }

  @GetMapping("/typedemandes/{id}")
  public ResponseEntity<TypeDemandeDTO> getTypeDemande(@PathVariable String id) {
    log.debug("Request to get TypeDemande: {}",id);
    TypeDemandeDTO dto = typedemandeService.findOne(id);
    return ResponseEntity.ok().body(dto);
  }

  public Collection<TypeDemandeDTO> getAllTypeDemandes() {
    log.debug("Request to get all  TypeDemandes : {}");
    return typedemandeService.findAll();
  }

  @DeleteMapping("/typedemandes/{id}")
  public ResponseEntity<Void> deleteTypeDemande(@PathVariable String id) {
    log.debug("Request to delete TypeDemande: {}",id);
    typedemandeService.delete(id);
    return ResponseEntity.ok().build();
  }
}

