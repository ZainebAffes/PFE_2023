package com.csys.access.web.rest;

import com.csys.access.domain.ButtonPK;
import com.csys.access.dto.ButtonDTO;
import com.csys.access.service.ButtonService;
import com.csys.access.util.RestPreconditions;
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

/**
 * REST controller for managing Button.
 */
@RestController
@RequestMapping("/api")
public class ButtonResource {
  private static final String ENTITY_NAME = "button";

  private final ButtonService buttonService;

  private final Logger log = LoggerFactory.getLogger(ButtonService.class);

  public ButtonResource(ButtonService buttonService) {
    this.buttonService=buttonService;
  }

  @GetMapping("/buttons/{id}")
  public ResponseEntity<ButtonDTO> getButton(@PathVariable ButtonPK id) {
    log.debug("Request to get Button: {}",id);
    ButtonDTO dto = buttonService.findOne(id);
    RestPreconditions.checkFound(dto, "button.NotFound");
    return ResponseEntity.ok().body(dto);
  }

  @GetMapping("/buttons")
  public Collection<ButtonDTO> getAllButtons() {
    log.debug("Request to get all  Buttons : {}");
    return buttonService.findAll();
  }
}

