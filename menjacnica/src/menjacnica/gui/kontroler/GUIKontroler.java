package menjacnica.gui.kontroler;


import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


import menjacnica.Menjacnica;
import menjacnica.MenjacnicaInterface;
import menjacnica.Valuta;
import menjacnica.gui.DodajKursGUI;
import menjacnica.gui.IzvrsiZamenuGUI;
import menjacnica.gui.MenjacnicaGUI;
import menjacnica.gui.ObrisiKursGUI;
import menjacnica.gui.models.MenjacnicaTableModel;

public class GUIKontroler {
	
	public static MenjacnicaInterface menjacnica = new Menjacnica();
	
	public static MenjacnicaGUI mn;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mn = new MenjacnicaGUI();
					mn.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void ugasiAplikaciju() {
		
		int opcija = JOptionPane.showConfirmDialog(mn.getContentPane(),"Da li ZAISTA zelite da izadjete iz apliacije", "Izlazak",JOptionPane.YES_NO_OPTION);

		if (opcija == JOptionPane.YES_OPTION)
			System.exit(0);
		
	}
	
	public static void prikaziAboutProzor(){
		JOptionPane.showMessageDialog(mn.getContentPane(),"Autor: Bojan Tomic, Verzija 1.0", "O programu Menjacnica",JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void sacuvajUFajl() {
		
		try {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showSaveDialog(mn.getContentPane());

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();

				menjacnica.sacuvajUFajl(file.getAbsolutePath());
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(mn.getContentPane(), e1.getMessage(),"Greska", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	public static void ucitajIzFajla() {
		
		try {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(mn.getContentPane());

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				menjacnica.ucitajIzFajla(file.getAbsolutePath());
				prikaziSveValute();
			}	
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(mn.getContentPane(), e1.getMessage(),"Greska", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public static void prikaziSveValute() {
		
		MenjacnicaTableModel model = (MenjacnicaTableModel)(mn.getTable().getModel());
		model.staviSveValuteUModel(menjacnica.vratiKursnuListu());

	}
	
	public static void prikaziDodajKursGUI() {
	
		DodajKursGUI prozor = new DodajKursGUI();
		prozor.setLocationRelativeTo(mn.getContentPane());
		prozor.setVisible(true);
	
	}

	public static void prikaziObrisiKursGUI() {
		
		if (mn.getTable().getSelectedRow() != -1) {
			MenjacnicaTableModel model = (MenjacnicaTableModel)(mn.getTable().getModel());
			ObrisiKursGUI prozor = new ObrisiKursGUI(model.vratiValutu(mn.getTable().getSelectedRow()));
			prozor.setLocationRelativeTo(mn.getContentPane());
			prozor.setVisible(true);
		}
	
	}
	
	public static void unesiKurs(String naziv, String skraceniNaziv, int sifra, double prodajni, double kupovni, double srednji) throws Exception{
			
		Valuta valuta = new Valuta();
				
		valuta.setNaziv(naziv);
		valuta.setSkraceniNaziv(skraceniNaziv);
		valuta.setSifra(sifra);
		valuta.setProdajni(prodajni);
		valuta.setKupovni(kupovni);
		valuta.setSrednji(srednji);

		menjacnica.dodajValutu(valuta);

	}
	
	public static void prikaziIzvrsiZamenuGUI() {
		
		if (mn.getTable().getSelectedRow() != -1) {
			MenjacnicaTableModel model = (MenjacnicaTableModel)(mn.getTable().getModel());
			IzvrsiZamenuGUI prozor = new IzvrsiZamenuGUI(model.vratiValutu(mn.getTable().getSelectedRow()));
			prozor.setLocationRelativeTo(mn.getContentPane());
			prozor.setVisible(true);
		}
		
	}
	
	public static String izvrsiZamenu(Valuta valuta, boolean prodaja, double iznos) {
		
			double izn = menjacnica.izvrsiTransakciju(valuta, prodaja, iznos);
			return " " + izn;
	
	}
	public static void obrisiKurs(Valuta valuta) {
		
		menjacnica.obrisiValutu(valuta);
		prikaziSveValute();
	}
}
