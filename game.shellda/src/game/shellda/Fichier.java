package game.shellda;

import java.awt.FontMetrics;
import java.awt.Graphics;

import interpreter.IKind;
import java.util.Random;

public class Fichier extends Element {

	String m_name;

	int m_infection;

	int m_x_ancien, m_y_ancien;
	Noeud m_courant_ancien;
	String m_name_ancien;

	public Fichier(Noeud courant, Model model, int x, int y, String name) {
		super(courant, model, x, y);
		m_kind = new IKind("P");
		m_name = name;
		m_infection = 100;
	}

	public void paint(Graphics g) {
		g.drawImage(m_model.m_fichierSprite, m_x * 48 + 8, m_y * 48, 32, 32, null);

		g.setFont(m_model.m_font);
		FontMetrics f = g.getFontMetrics();
		g.drawString(m_name, m_x * 48 + (48 - f.stringWidth(m_name)) / 2, m_y * 48 + 32 + (16 / 2));
	}

	long w = 0;

	public void step(long now) throws Exception {
		if (now - w > 1000) {
			if (m_auto != null)
				m_auto.step(this);
			w = now;
		}
	}

	public void goCorbeille() {
		Random r = new Random();
		int r_x = r.nextInt(13);
		int r_y = r.nextInt(9);
		r_x += 3;
		while (m_model.m_corbeille.m_carte[r_x][r_y] != null) {
			r_x = r.nextInt(13);
			r_y = r.nextInt(9);
			r_x += 3;
		}
		m_model.m_corbeille.m_carte[r_x][r_y] = new FichCorb(m_model.m_corbeille, m_model, r_x, r_y, "%&%%", m_x, m_y,
				m_courant,m_name);
	}

	public void retour() {
		m_courant_ancien.m_carte[m_x_ancien][m_y_ancien] = new FichNorm(m_courant_ancien, m_model, m_x_ancien,
				m_y_ancien, m_name_ancien);
	}

	public static class FichNorm extends Fichier {

		public FichNorm(Noeud courant, Model model, int x, int y, String name) {
			super(courant, model, x, y, name);
		}
	}

	public static class FichCorb extends Fichier {

		public FichCorb(Noeud courant, Model model, int x, int y, String name, int old_x, int old_y, Noeud old_noeud,
				String old_name) {
			super(courant, model, x, y, name);
			m_x_ancien = old_x;
			m_y_ancien = old_y;
			m_courant_ancien = old_noeud;
			m_name_ancien = old_name;
			m_auto = m_model.m_automateFichier.copy();
		}

	}

}
