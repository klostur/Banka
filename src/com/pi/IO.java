package com.pi;


import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class IO {

	public static void sacuvajUPdf(Korisnik korisnik) {
		PDDocument document = new PDDocument();
		PDPage page = new PDPage();
		document.addPage(page);
		float margineX = 40f;
		float margineY = 40f;
		float offsetY = page.getMediaBox().getHeight() - margineY;

		try {
			PDPageContentStream contentStream = new PDPageContentStream(document, page);
			PDType1Font font = PDType1Font.COURIER.COURIER_BOLD_OBLIQUE;
			int fontSize = 28;
			String naslov = korisnik.getName() + " " + korisnik.getSurname();
			float fontHeight = font.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * fontSize;
			float fontWidth = font.getStringWidth(naslov) * fontSize / 1000;
			contentStream.setLeading(15);

			contentStream.beginText();
			contentStream.setFont(font, fontSize);
			contentStream.newLineAtOffset((page.getMediaBox().getWidth() - fontWidth) / 2,
					page.getMediaBox().getHeight() - margineX - fontHeight);// treba ti samo offset
			contentStream.showText(naslov);
			contentStream.endText();
			offsetY -= fontHeight;
			
			font = PDType1Font.TIMES_ROMAN;
			fontSize = 20;
			contentStream.setFont(font, fontSize);
			fontHeight = font.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * fontSize;

			contentStream.moveTo(margineX, offsetY - fontHeight );
			contentStream.lineTo(page.getMediaBox().getWidth() - margineX, offsetY - fontHeight);
			contentStream.setStrokingColor(Color.blue); // ha! stroking color
			contentStream.stroke();
			offsetY -= fontHeight; 
			
//			contentStream.moveTo(margineX, offsetY - fontHeight );
			
			String text = korisnik.getAccountNum(); // samo za primer posto je svaki racun iste duzine
			fontHeight = font.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * fontSize;
			fontWidth = font.getStringWidth(text) * fontSize / 1000;
			
			for (int i = 0; i < korisnik.getAccountNum().length(); i++) {
				text = korisnik.getAccountNum();
				contentStream.beginText();
				contentStream.newLineAtOffset(margineX, offsetY - fontHeight - (fontHeight/4) ); //ne znam zasto font height/4 radi
				contentStream.showText(text);
				contentStream.endText();
				offsetY -= fontHeight;
				
				contentStream.moveTo(margineX, offsetY - fontHeight);
				contentStream.lineTo(page.getMediaBox().getWidth() - margineX, offsetY - fontHeight);
				contentStream.stroke();
				offsetY -= fontHeight; 
			}

			contentStream.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			document.save("output/Korisnik.pdf");
			document.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("PDF fajl uspesno kreiran.");
	}

	public static String ucitajSaTastature() {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String s = "";
		try {
			s = input.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}
}
