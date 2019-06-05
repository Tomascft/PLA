package game.shellda;

import java.awt.Color;
import java.awt.Graphics;

public class Noeud {

	Element m_carte[][];
	Noeud m_parent;
	Noeud m_enfants[];
	int m_nb_enfant;

	Model m_model;

	public Noeud(Model m) {
		m_model = m;
		initialiser_carte(m);
	}

	public Noeud(Model m, Noeud parent) {
		m_model = m;
		m_parent = parent;
		initialiser_carte(m);
	}

	public Noeud parent() {
		return m_parent;
	}

	public int nb_enfant() {
		return m_nb_enfant;
	}

	private void initialiser_carte(Model m) {
		m_carte = new Element[Options.HAUTEUR_CARTE][Options.LARGEUR_CARTE];
		for (int i = 0; i < Options.HAUTEUR_CARTE; i++) {
			for (int j = 0; j < Options.LARGEUR_CARTE; j++) {
				m_carte[i][j] = null;
			}
		}
		m_carte[0][0] = new Corbeille(m.m_corbeille, this, m, 0, m.m_corbeilleSprite, 1, 1, 0, 0, 1);
		m_carte[1][0] = new Dossier(m_parent, this, "Retour", m, 0, m.m_corbeilleSprite, 1, 1, 0, 1, 1);
		m_carte[5][0] = new Dossier(m_parent, this, "Retour", m, 0, m.m_corbeilleSprite, 1, 1, 5, 0, 1);
	}

	public void ajouter_enfant(Noeud parent, int nb, Tree root) {
		m_nb_enfant = nb;
		m_enfants = new Noeud[nb];
		for (int i = 0; i < nb; i++) {
			Noeud n = new Noeud(m_model, parent);
			m_enfants[i] = n;
			n.m_carte[5][6] = new Virus(this, m_model, 1, m_model.m_virusSprite, 2, 4, 5,
					6, 1, m_model.m_virus);
			root.vir.add((Virus) n.m_carte[5][6]);

			m_carte[Options.HAUTEUR_CARTE - 1][i] = new Dossier(n, this, "" + i, m_model, 0, m_model.m_corbeilleSprite,
					1, 1, Options.HAUTEUR_CARTE - 1, i, 1);
		}

	}

	public void print() {
		for (int i = 0; i < Options.HAUTEUR_CARTE; i++) {
			for (int j = 0; j < Options.LARGEUR_CARTE; j++) {
				System.out.print("|");

				if (m_carte[i][j] == null) {
					System.out.print(" ");
				} else {
					if (m_carte[i][j] instanceof Corbeille) {
						System.out.print("C");
					} else if (m_carte[i][j] instanceof Dossier) {
						System.out.print("D");
					}
					if (m_carte[i][j] instanceof Clink) {
						System.out.print("J");
					}
					if (m_carte[i][j] instanceof Virus) {
						Virus v = (Virus) m_carte[i][j];
						System.out.print(v.display);
					}

				}

			}
			System.out.println("|");
		}

	}

	public void paint(Graphics g) {
		int case_width = Options.WIDTH / Options.LARGEUR_CARTE;
		int case_height = Options.HEIGHT / Options.HAUTEUR_CARTE;
		for (int i = 0; i < Options.HAUTEUR_CARTE; i++) {
			for (int j = 0; j < Options.LARGEUR_CARTE; j++) {
				if (m_carte[i][j] == null) {
					g.setColor(Color.cyan);
				} else {
					g.setColor(m_carte[i][j].c);
				}
				g.fillRect(j * case_width, i * case_height, case_width - 4, case_height - 4);
			}
		}

	}
	
	public Element[][] carte() {
		return m_carte;
	}
}
