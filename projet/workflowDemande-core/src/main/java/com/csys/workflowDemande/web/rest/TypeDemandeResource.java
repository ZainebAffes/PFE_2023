package com.csys.workflowDemande.web.rest;

import com.csys.workflowDemande.domain.TypeDemande;
import com.csys.workflowDemande.dto.TypeDemandeDTO;
import com.csys.workflowDemande.service.TypeDemandeService;
import com.csys.workflowDemande.web.rest.util.ExcelGenerator;
import com.csys.workflowDemande.web.rest.util.PDFGenerator;
import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.lang.String;
import java.lang.Void;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javax.servlet.http.HttpServletResponse;
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

    @GetMapping("/typedemandes/filter")
    public List<TypeDemandeDTO> getAllTypeDemandes(@RequestParam(required = false) String designation) {
    log.debug("Request to get all  TypeDemandes : {}");
    return typedemandeService.findAll(designation);
  }

    @DeleteMapping("/typedemandes/{id}")
    public ResponseEntity<Void> deleteTypeDemande(@PathVariable String id) {
    log.debug("Request to delete TypeDemande: {}",id);
    typedemandeService.delete(id);
    return ResponseEntity.ok().build();
  }
  @GetMapping("/pdf/typeDemandes")
	public void generatePdf(HttpServletResponse response,@RequestParam(required = false) String designation) throws DocumentException, IOException {
		
		response.setContentType("application/pdf");
		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
		String currentDateTime = dateFormat.format(new Date());
		String headerkey = "Content-Disposition";
		String headervalue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
		response.setHeader(headerkey, headervalue);
		
		List<TypeDemandeDTO> typeDemandeList = typedemandeService.findAll(designation);
		
		PDFGenerator generator = new PDFGenerator();
		generator.setTypeDemandeList(typeDemandeList);
		generator.generate(response);
                
}
//       @GetMapping("/export/typeDemandes")
//	public void exportIntoExcel(HttpServletResponse response) throws IOException {
//		response.setContentType("application/octet-stream");
//		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
//		String currentDateTime = dateFormatter.format(new Date());
//
//		String headerKey = "Content-Disposition";
//		String headerValue = "attachment; filename=records_" + currentDateTime + ".xlsx";
//		response.setHeader(headerKey, headerValue);
//
//                List<TypeDemandeDTO> typeDemandeList = typedemandeService.getAll();
//		ExcelGenerator generator = new ExcelGenerator(typeDemandeList);
//
//		generator.generate(response);
//}

}
