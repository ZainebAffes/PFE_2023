package com.csys.workflowDemande.web.rest;

import com.csys.workflowDemande.dto.OptionEtiquetteDTO;
import com.csys.workflowDemande.service.OptionEtiquetteService;
import java.lang.Integer;
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
public class OptionEtiquetteResource {
  private static final String ENTITY_NAME = "optionetiquette";

  private final OptionEtiquetteService optionetiquetteService;

  private final Logger log = LoggerFactory.getLogger(OptionEtiquetteService.class);

  public OptionEtiquetteResource(OptionEtiquetteService optionetiquetteService) {
    this.optionetiquetteService=optionetiquetteService;
  }

  @PostMapping("/optionetiquettes")
  public ResponseEntity<OptionEtiquetteDTO> createOptionEtiquette(@Valid @RequestBody OptionEtiquetteDTO optionetiquetteDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
    log.debug("REST request to save OptionEtiquette : {}", optionetiquetteDTO);
    if (bindingResult.hasErrors()) {
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    if ( optionetiquetteDTO.getCodeOption() != null) {
      bindingResult.addError( new FieldError("OptionEtiquetteDTO","codeOption","POST method does not accepte "+ENTITY_NAME+" with code"));
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    OptionEtiquetteDTO result = optionetiquetteService.save(optionetiquetteDTO);
    return ResponseEntity.created( new URI("/api/optionetiquettes/"+ result.getCodeOption())).body(result);
  }

  @PutMapping("/optionetiquettes")
  public ResponseEntity<OptionEtiquetteDTO> updateOptionEtiquette(@Valid @RequestBody OptionEtiquetteDTO optionetiquetteDTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
    log.debug("Request to update OptionEtiquette: {}",optionetiquetteDTO);
    if (bindingResult.hasErrors()) {
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    if ( optionetiquetteDTO.getCodeOption() == null) {
      bindingResult.addError( new FieldError("OptionEtiquetteDTO","codeOption","PUT method does not accepte "+ENTITY_NAME+" with code"));
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    OptionEtiquetteDTO result =optionetiquetteService.update(optionetiquetteDTO);
    return ResponseEntity.ok().body(result);
  }

  @GetMapping("/optionetiquettes/{id}")
  public ResponseEntity<OptionEtiquetteDTO> getOptionEtiquette(@PathVariable Integer id) {
    log.debug("Request to get OptionEtiquette: {}",id);
    OptionEtiquetteDTO dto = optionetiquetteService.findOne(id);
    return ResponseEntity.ok().body(dto);
  }

  public Collection<OptionEtiquetteDTO> getAllOptionEtiquettes() {
    log.debug("Request to get all  OptionEtiquettes : {}");
    return optionetiquetteService.findAll();
  }

  @DeleteMapping("/optionetiquettes/{id}")
  public ResponseEntity<Void> deleteOptionEtiquette(@PathVariable Integer id) {
    log.debug("Request to delete OptionEtiquette: {}",id);
    optionetiquetteService.delete(id);
    return ResponseEntity.ok().build();
  }
}

