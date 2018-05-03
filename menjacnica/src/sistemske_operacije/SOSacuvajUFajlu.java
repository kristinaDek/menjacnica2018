package sistemske_operacije;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import menjacnica.Valuta;

public class SOSacuvajUFajlu {
	public static void uradi(LinkedList<Valuta> kursnaLista, String putanja) {
		try{
			ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(putanja)));
			
			out.writeObject(kursnaLista);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
