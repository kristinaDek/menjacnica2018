package sistemske_operacije;

import menjacnica.Valuta;

public class SOIzvrsiTransakciju {
	public static double uradi(Valuta valuta, boolean prodaja, double iznos) {
		if (prodaja)
			return iznos*valuta.getProdajni();
		else
			return iznos*valuta.getKupovni();
	}
}
