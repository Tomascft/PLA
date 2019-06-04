package game.shellda;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import edu.ricm3.game.GameModel;

public class Model extends GameModel {

	Tree m_tree;
	Noeud m_corbeille;
	LinkedList<Virus> m_virus;
	
	
	Noeud m_courant;
	
	BufferedImage m_virusSprite;
	
	BufferedImage m_boutonplaySprite;
	BoutonPlay m_boutonplay;
	BufferedImage m_boutonexitSprite;
	BoutonExit m_boutonexit;
	boolean gameStart = false;

	public Model() {
		loadSprites();
		m_virus = new LinkedList<Virus>();
		m_corbeille = new Noeud(null);
		m_tree = new Tree(m_corbeille, m_virus);
		m_courant = m_tree.m_root;
		
		m_boutonplay = new BoutonPlay(this, 0, m_boutonplaySprite, 1, 1, Options.WIDTH / 2 - (int)(m_boutonplaySprite.getWidth()*Options.BoutonPlayScale)/2, Options.HEIGHT / 2 - (int)(m_boutonplaySprite.getHeight()*Options.BoutonPlayScale)/2, Options.BoutonPlayScale);
		m_boutonexit = new BoutonExit(this, 0, m_boutonexitSprite, 1, 1, Options.WIDTH-40, 0, Options.BoutonExitScale);
	}
	
	private void loadSprites() {
		File imageFile = new File("ressources/Virus.png");
		try {
			m_virusSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		imageFile = new File("ressources/bouton.png");
		try {
			m_boutonplaySprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		imageFile = new File("ressources/exit.png");
		try {
			m_boutonexitSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
	}

	@Override
	public void step(long now) {
		// TODO Auto-generated method stub

	}

	@Override
	public void shutdown() {
		System.exit(0);
	}

}
