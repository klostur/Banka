package com.pi;

import java.util.ArrayList;
import java.util.List;

public class App {
	public static List<Korisnik> listaKorisnika = new ArrayList<>();

	public static void main(String[] args) {
		ucitajKorisnike();
		String input = "";
		while (!input.equals("6")) {
			meni();
			input = IO.ucitajSaTastature();
			switch (input) {
			case "1":
				prikaziSveKorisnike();
				break;
			case "2":
				dodajNovogKorisnika();
				break;
			case "3":
				// TODO dodati mogucnost izmene korisnika
				break;
			case "4":
				izbrisiKorisnika();
				break;
			case "5":
				IO.sacuvajUPdf(listaKorisnika.get(izaberiIzListe()));
				break;
			default:
				break;
			}
		}
	}

	private static void ucitajKorisnike() {
		Korisnik k1 = new Korisnik("Mitar", "Miric", "1245879512327");
		listaKorisnika.add(k1);
		Korisnik k2 = new Korisnik("Vepar", "Spiric", "1245879512327");
		listaKorisnika.add(k2);
	}

	private static int izaberiIzListe() {
		prikaziSveKorisnike();
		int input = Integer.valueOf(IO.ucitajSaTastature()) - 1;
		return input;
	}

	private static boolean izbrisiKorisnika() {
		System.out.println("Izaberite broj korisnika kojeg zelite izbrisati, ne?");
		listaKorisnika.remove(izaberiIzListe());
		return true;
	}

	private static void prikaziSveKorisnike() {
		for (Korisnik korisnik : listaKorisnika) {
			System.out.println((listaKorisnika.indexOf(korisnik) + 1) + ") " + korisnik.toString());
		}
	}

	private static boolean dodajNovogKorisnika() {
		System.out.println("Unesite ime korisnika");
		String name = IO.ucitajSaTastature();
		System.out.println("Unesite prezime korisnika");
		String surname = IO.ucitajSaTastature();
		System.out.println("Unesite JMBG korisnika");
		String jmbg = IO.ucitajSaTastature();
		Korisnik korisnik = new Korisnik(name, surname, jmbg);
		listaKorisnika.add(korisnik);
		return true;
	}

	private static void meni() {
		System.out.println("###########################");
		System.out.println("Dobrodosli u Davidovu Banku");
		System.out.println("1) Prikazi sve korisnike banke");
		System.out.println("2) Dodaj novog korisnika banke");
		System.out.println("3) Izmeni postojeceg korisnika");
		System.out.println("4) Izbrisi postojeceg korisnika");
		System.out.println("5) Sacuvaj u PDF formatu");
		System.out.println("6) Izlaz");
		System.out.println("###########################");
	}
}
