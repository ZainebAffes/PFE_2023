package com.csys.access.service;

import com.csys.access.domain.Button;
import com.csys.access.domain.ButtonPK;
import com.csys.access.dto.ButtonDTO;
import com.csys.access.factory.ButtonFactory;
import com.csys.access.repository.ButtonRepository;
import com.google.common.base.Preconditions;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing Button.
 */
@Service
@Transactional
public class ButtonService {
  private final Logger log = LoggerFactory.getLogger(ButtonService.class);

  private final ButtonRepository buttonRepository;

  public ButtonService(ButtonRepository buttonRepository) {
    this.buttonRepository=buttonRepository;
  }

  @Transactional(
      readOnly = true
  )
  public ButtonDTO findOne(ButtonPK id) {
    log.debug("Request to get Button: {}",id);
    Button button= buttonRepository.findOne(id);
    ButtonDTO dto = ButtonFactory.buttonToButtonDTO(button);
    return dto;
  }

  @Transactional(
      readOnly = true
  )
  public Button findButton(ButtonPK id) {
    log.debug("Request to get Button: {}",id);
    Button button= buttonRepository.findOne(id);
    return button;
  }

  @Transactional(
      readOnly = true
  )
  public Collection<ButtonDTO> findAll() {
    log.debug("Request to get All Buttons");
    Collection<Button> result= buttonRepository.findAll();
    return ButtonFactory.buttonToButtonDTOs(result);
  }

}

