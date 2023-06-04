package com.csys.workflowDemande.web.rest;

import com.csys.workflowDemande.domain.Champs;
import com.csys.workflowDemande.dto.ChampsDTO;
import com.csys.workflowDemande.service.ChampsService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChampsResource {
  private static final String ENTITY_NAME = "listedeschamps";

  private final ChampsService listedeschampsService;

  private final Logger log = LoggerFactory.getLogger(ChampsService.class);

  public ChampsResource(ChampsService listedeschampsService) {
    this.listedeschampsService=listedeschampsService;
  }

  @PostMapping("/listedeschampss")
  public ResponseEntity<ChampsDTO> createChamps(@Valid @RequestBody ChampsDTO listedeschampsDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
    log.debug("REST request to save Champs : {}", listedeschampsDTO);
    if (bindingResult.hasErrors()) {
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    if ( listedeschampsDTO.getCode() != null) {
      bindingResult.addError( new FieldError("ChampsDTO","code","POST method does not accepte "+ENTITY_NAME+" with code"));
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    ChampsDTO result = listedeschampsService.save(listedeschampsDTO);
    return ResponseEntity.created( new URI("/api/listedeschampss/"+ result.getCode())).body(result);
  }

  @PutMapping("/listedeschampss")
  public ResponseEntity<ChampsDTO> updateChamps(@Valid @RequestBody ChampsDTO listedeschampsDTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
    log.debug("Request to update Champs: {}",listedeschampsDTO);
    if (bindingResult.hasErrors()) {
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    if ( listedeschampsDTO.getCode() == null) {
      bindingResult.addError( new FieldError("ChampsDTO","code","PUT method does not accepte "+ENTITY_NAME+" with code"));
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    ChampsDTO result =listedeschampsService.update(listedeschampsDTO);
    return ResponseEntity.ok().body(result);
  }

  @GetMapping("/listedeschampss/{id}")
  public ResponseEntity<ChampsDTO> getChamps(@PathVariable Integer id) {
    log.debug("Request to get Champs: {}",id);
    ChampsDTO dto = listedeschampsService.findOne(id);
    return ResponseEntity.ok().body(dto);
  }

  public Collection<ChampsDTO> getAllChampss() {
    log.debug("Request to get all  Champss : {}");
    return listedeschampsService.findAll();
  }

  @DeleteMapping("/listedeschampss/{id}")
  public ResponseEntity<Void> deleteChamps(@PathVariable Integer id) {
    log.debug("Request to delete Champs: {}",id);
    listedeschampsService.delete(id);
    return ResponseEntity.ok().build();
  }
  
  @GetMapping("/listedeschampss/filter")
  public Collection<Champs> getAllDemandes(@RequestParam Integer codeParametrage) {
    log.debug("Request to get all  Demandes : {}");
    return listedeschampsService.findAllChampsByCodeDemande(codeParametrage);
  }
}

