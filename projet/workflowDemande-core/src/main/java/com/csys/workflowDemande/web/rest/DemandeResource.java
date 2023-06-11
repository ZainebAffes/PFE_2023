package com.csys.workflowDemande.web.rest;

import com.csys.workflowDemande.dto.DemandeDTO;
import com.csys.workflowDemande.service.DemandeService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemandeResource {
  private static final String ENTITY_NAME = "demande";

  private final DemandeService demandeService;

  private final Logger log = LoggerFactory.getLogger(DemandeService.class);

  public DemandeResource(DemandeService demandeService) {
    this.demandeService=demandeService;
  }

  
 @PostMapping("/demandes")
    public ResponseEntity<String> createDemande(@Valid @RequestBody DemandeDTO demandeDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        log.debug("REST request to save Demande : {}", demandeDTO);
        if (bindingResult.hasErrors()) {
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        String result = demandeService.save(demandeDTO);
        return ResponseEntity.created(new URI("/api/demandes/")).body(result);
    }

    @PutMapping("/demandes/update")
    public ResponseEntity<String> updateDemande(@Valid @RequestBody DemandeDTO demandeDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        String result = demandeService.update(demandeDTO);
        return ResponseEntity.ok().body(result);
    }
  
    @PutMapping("/demandes/validation")
    public ResponseEntity<String> validation(@RequestParam String[] validation, @RequestParam String user,@RequestParam boolean refus) throws URISyntaxException, MethodArgumentNotValidException {
        String result = demandeService.validation(validation, user,refus);
        return ResponseEntity.created(new URI("/api/demandes/")).body(result);
    }
  
  @GetMapping("/demandes/{id}")
  public ResponseEntity<DemandeDTO> getDemande(@PathVariable Integer id) {
    log.debug("Request to get Demande: {}",id);
    DemandeDTO dto = demandeService.findById(id);
    return ResponseEntity.ok().body(dto);
  }
@GetMapping("/demandes/filter")
  public Collection<DemandeDTO> getAllDemandes(@RequestParam Integer codeParametrage) {
    log.debug("Request to get all  Demandes : {}");
    return demandeService.findAll(codeParametrage);
  }

  @DeleteMapping("/demandes/{id}")
  public ResponseEntity<Void> deleteDemande(@PathVariable Integer id) {
    log.debug("Request to delete Demande: {}",id);
    demandeService.delete(id);
    return ResponseEntity.ok().build();
  }
}

