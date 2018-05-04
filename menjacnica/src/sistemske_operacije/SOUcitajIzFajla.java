package sistemske_operacije;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.LinkedList;


import menjacnica.Valuta;

public class SOUcitajIzFajla {
	
	@SuppressWarnings("unchecked")
	public static LinkedList<Valuta> izvrsi(String putanja) {
		try{
			LinkedList<Valuta> kursnaLista;
			ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(putanja)));
			
			kursnaLista = (LinkedList<Valuta>)(in.readObject());
			in.close();
			return kursnaLista;
			
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
