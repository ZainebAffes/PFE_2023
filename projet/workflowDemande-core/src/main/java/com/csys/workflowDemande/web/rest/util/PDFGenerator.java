package com.csys.workflowDemande.web.rest.util;


import com.csys.workflowDemande.domain.TypeDemande;
import com.csys.workflowDemande.dto.TypeDemandeDTO;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PDFGenerator {

	// List to hold all TypeDemandes
	private List<TypeDemandeDTO> typeDemandeList;

	public void generate(HttpServletResponse response) throws DocumentException, IOException {

		// Creating the Object of Document
		Document document = new Document(PageSize.A4);

		// Getting instance of PdfWriter
	 	PdfWriter.getInstance(document, response.getOutputStream());

		// Opening the created document to modify it
		document.open();

		// Creating font
		// Setting font style and size
		Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTiltle.setSize(20);

		// Creating paragraph
		Paragraph paragraph = new Paragraph("List Of TypeDemandes", fontTiltle);

		// Aligning the paragraph in document
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);

		// Adding the created paragraph in document
		document.add(paragraph);

		// Creating a table of 3 columns
		PdfPTable table = new PdfPTable(3);

		// Setting width of table, its columns and spacing
		table.setWidthPercentage(100f);
		table.setWidths(new int[] { 3, 3, 3 });
		table.setSpacingBefore(5);

		// Create Table Cells for table header
		PdfPCell cell = new PdfPCell();

		// Setting the background color and padding
		cell.setBackgroundColor(CMYKColor.MAGENTA);
		cell.setPadding(5);

		// Creating font
		// Setting font style and size
		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setColor(CMYKColor.WHITE);

		// Adding headings in the created table cell/ header
		// Adding Cell to table
		cell.setPhrase(new Phrase("ID", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("TypeDemande Name", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Section", font));
		table.addCell(cell);

		// Iterating over the list of typeDemandes
		for (TypeDemandeDTO typeDemande : typeDemandeList) {
			// Adding typeDemande id
			table.addCell(String.valueOf(typeDemande.getCodeTypeDemande()));
			// Adding typeDemande name
			table.addCell(typeDemande.getDescription());
			// Adding typeDemande section
			table.addCell(typeDemande.getNom());
		}
		// Adding the created table to document
		document.add(table);

		// Closing the document
		document.close();

	}

    public List<TypeDemandeDTO> getTypeDemandeList() {
        return typeDemandeList;
    }

    public void setTypeDemandeList(List<TypeDemandeDTO> typeDemandeList) {
        this.typeDemandeList = typeDemandeList;
    }

   
        
}