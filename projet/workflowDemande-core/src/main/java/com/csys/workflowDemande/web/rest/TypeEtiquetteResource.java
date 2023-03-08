package com.csys.workflowDemande.web.rest;

import com.csys.workflowDemande.dto.TypeEtiquetteDTO;
import com.csys.workflowDemande.service.TypeEtiquetteService;
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
public class TypeEtiquetteResource {
  private static final String ENTITY_NAME = "typeetiquette";

  private final TypeEtiquetteService typeetiquetteService;

  private final Logger log = LoggerFactory.getLogger(TypeEtiquetteService.class);

  public TypeEtiquetteResource(TypeEtiquetteService typeetiquetteService) {
    this.typeetiquetteService=typeetiquetteService;
  }

  @PostMapping("/typeetiquettes")
  public ResponseEntity<TypeEtiquetteDTO> createTypeEtiquette(@Valid @RequestBody TypeEtiquetteDTO typeetiquetteDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
    log.debug("REST request to save TypeEtiquette : {}", typeetiquetteDTO);
    if (bindingResult.hasErrors()) {
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    if ( typeetiquetteDTO.getCode() != null) {
      bindingResult.addError( new FieldError("TypeEtiquetteDTO","code","POST method does not accepte "+ENTITY_NAME+" with code"));
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    TypeEtiquetteDTO result = typeetiquetteService.save(typeetiquetteDTO);
    return ResponseEntity.created( new URI("/api/typeetiquettes/"+ result.getCode())).body(result);
  }

  @PutMapping("/typeetiquettes")
  public ResponseEntity<TypeEtiquetteDTO> updateTypeEtiquette(@Valid @RequestBody TypeEtiquetteDTO typeetiquetteDTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
    log.debug("Request to update TypeEtiquette: {}",typeetiquetteDTO);
    if (bindingResult.hasErrors()) {
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    if ( typeetiquetteDTO.getCode() == null) {
      bindingResult.addError( new FieldError("TypeEtiquetteDTO","code","PUT method does not accepte "+ENTITY_NAME+" with code"));
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    TypeEtiquetteDTO result =typeetiquetteService.update(typeetiquetteDTO);
    return ResponseEntity.ok().body(result);
  }

  @GetMapping("/typeetiquettes/{id}")
  public ResponseEntity<TypeEtiquetteDTO> getTypeEtiquette(@PathVariable Integer id) {
    log.debug("Request to get TypeEtiquette: {}",id);
    TypeEtiquetteDTO dto = typeetiquetteService.findOne(id);
    return ResponseEntity.ok().body(dto);
  }

  public List<TypeEtiquetteDTO> getAllTypeEtiquettes() {
    log.debug("Request to get all  TypeEtiquettes : {}");
    return typeetiquetteService.findAll();
  }

  @DeleteMapping("/typeetiquettes/{id}")
  public ResponseEntity<Void> deleteTypeEtiquette(@PathVariable Integer id) {
    log.debug("Request to delete TypeEtiquette: {}",id);
    typeetiquetteService.delete(id);
    return ResponseEntity.ok().build();
  }
}

