package com.pi;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class IO {

	public static void sacuvajUPdf(Korisnik korisnik) {
		PDDocument document = new PDDocument();
		PDPage page = new PDPage();
		document.addPage(page);

		try {
			PDPageContentStream contentStream = new PDPageContentStream(document, page);
			contentStream.setNonStrokingColor(Color.GRAY);
			contentStream.addRect(15, 550, 580, 180);
			contentStream.fill();
			
			contentStream.setNonStrokingColor(Color.black);
			PDImageXObject pdImage = PDImageXObject.createFromFile("input/logo.jpg", document);
			contentStream.setLeading(15);
			contentStream.drawImage(pdImage, 25, 660);
			
			contentStream.beginText();
			contentStream.newLineAtOffset(80, 700);
			contentStream.setFont(PDType1Font.COURIER_BOLD_OBLIQUE, 22);
			contentStream.showText("DAVIDOVA BANKA");
			contentStream.newLine();
			contentStream.newLine();
			contentStream.setFont(PDType1Font.TIMES_ITALIC, 14);
			contentStream.showText("RC NOVI SAD 87 Ekspozitura 06");
			contentStream.newLine();
			contentStream.endText();
			
			contentStream.beginText();
			contentStream.newLineAtOffset(200, 630);
			contentStream.showText("PODACI O KORISNIKU");
			contentStream.endText();
			
			contentStream.beginText();
			contentStream.newLine();
			contentStream.newLineAtOffset(25, 610);
			contentStream.setFont(PDType1Font.TIMES_ROMAN, 14);
			contentStream.showText(
					"Ime i prezime " + korisnik.getName().toUpperCase() + " " + korisnik.getSurname().toUpperCase());
			contentStream.newLine();
			contentStream.showText("JMBG: " + korisnik.getJmbg());
			contentStream.newLine();
			contentStream.showText("Broj deviznog racuna: " + korisnik.getAccountNum());
			contentStream.endText();
			
			contentStream.beginText();
			contentStream.newLineAtOffset(450, 595);
			contentStream.showText("POTPIS");
			contentStream.endText();
			
			contentStream.moveTo(25, 620);
			contentStream.lineTo(580, 620);
			contentStream.stroke();
			
			
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
