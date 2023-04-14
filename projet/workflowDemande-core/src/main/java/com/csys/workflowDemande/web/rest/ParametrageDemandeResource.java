package com.csys.workflowDemande.web.rest;

import com.csys.workflowDemande.dto.ParametrageDemandeDTO;
import com.csys.workflowDemande.service.ParametrageDemandeService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ParametrageDemandeResource {

    private static final String ENTITY_NAME = "parametragedemande";

    private final ParametrageDemandeService parametragedemandeService;

    private final Logger log = LoggerFactory.getLogger(ParametrageDemandeService.class);

    public ParametrageDemandeResource(ParametrageDemandeService parametragedemandeService) {
        this.parametragedemandeService = parametragedemandeService;
    }

    @PostMapping("/parametragedemandes")
    public ResponseEntity<ParametrageDemandeDTO> createParametrageDemande(@Valid @RequestBody ParametrageDemandeDTO parametragedemandeDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        log.debug("REST request to save ParametrageDemande : {}", parametragedemandeDTO);
        if (bindingResult.hasErrors()) {
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        if (parametragedemandeDTO.getCode() != null) {
            bindingResult.addError(new FieldError("ParametrageDemandeDTO", "code", "POST method does not accepte " + ENTITY_NAME + " with code"));
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        ParametrageDemandeDTO result = parametragedemandeService.save(parametragedemandeDTO);
        return ResponseEntity.created(new URI("/api/parametragedemandes/" + result.getCode())).body(result);
    }

    @PutMapping("/parametragedemandes")
    public ResponseEntity<ParametrageDemandeDTO> updateParametrageDemande(@Valid @RequestBody ParametrageDemandeDTO parametragedemandeDTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        log.debug("Request to update ParametrageDemande: {}", parametragedemandeDTO);
        if (bindingResult.hasErrors()) {
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        if (parametragedemandeDTO.getCode() == null) {
            bindingResult.addError(new FieldError("ParametrageDemandeDTO", "code", "PUT method does not accepte " + ENTITY_NAME + " with code"));
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        ParametrageDemandeDTO result = parametragedemandeService.update(parametragedemandeDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/parametragedemandes/{id}")
    public ResponseEntity<ParametrageDemandeDTO> getParametrageDemande(@PathVariable Integer id) {
        log.debug("Request to get ParametrageDemande: {}", id);
        ParametrageDemandeDTO dto = parametragedemandeService.findOne(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/parametragedemandes")
    public List<ParametrageDemandeDTO> getAllParametrageDemandes(@RequestParam(required = false) String designation) {
        log.debug("Request to get all  ParametrageDemandes : {}");
        return parametragedemandeService.findAll(designation);
    }

    @DeleteMapping("/parametragedemandes/{id}")
    public ResponseEntity<Void> deleteParametrageDemande(@PathVariable Integer id) {
        log.debug("Request to delete ParametrageDemande: {}", id);
        parametragedemandeService.delete(id);
        return ResponseEntity.ok().build();
    }
}
