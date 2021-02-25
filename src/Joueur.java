/**
 * 
 * <p>
 * La classe Joueur h�rite de la classe ArrayListConteneur qui permet de stock�
 * les ObjetZork transport� par ce joueur. Toutes les m�thodes impl�ment�s dans
 * la classe ArrayListConteneur sont acc�ssible par la classe Joueur et cela
 * gr�ce � la m�thode super.
 * </p>
 * 
 * <p>
 * Mod�lise un joueur pour le jeu Zork. Un Joueur est caract�ris� par un nom, un
 * poids maximal ainsi que par les ObjetZork qu'il "transporte" avec lui et dont
 * le poids total ne doit pas exc�der le poids maximal.
 * </p>
 *
 * <p>
 * La somme des poids de tous les ObjetZork transport�s doit toujours �tre
 * inf�rieure ou �gale � un poids maximal fix� d�finitivement � la cr�ation du
 * Joueur. Ce poids maximal doit toujours �tre sup�rieur ou �gal � z�ro.
 * </p>
 *
 * <p>
 * Pour le transport des ObjetZork, le Joueur h�rite de la classe
 * ArrayListConteneur qui se comporte comme un conteneur d'ObjetZork pour ce
 * joueur.
 * </p>
 * 
 * @invariant getNom() != null;
 * @invariant getPiece != null;
 * @invariant getPoidsMax() > 0;
 * @invariant getPoids() >= 0 && getPoids() <= getPoidsMax();
 *
 * @author Mohand Lounis BENSKEHRI 11710457.
 */
public class Joueur extends ArrayListConteneur {

	private String nom;
	private Piece piece;
	private int poids;
	private int poidsMax;

	/**
	 * Initalise un joueur ne transportant aucun objet et dont le nom, la pi�ce ou
	 * il se trouve et le poids maximal � transport� sont sp�cifi�s. le nom et la
	 * pi�ce doivent �tre non null. le poidsMax � transporter doit �tre strictement
	 * positif.
	 *
	 * @requires nom != null;
	 * @requires piece != null;
	 * @requires poidsMax > 0;
	 * @ensures getNom().equals(nom);
	 * @ensures getPiece().equals(piece);
	 * @ensures getPoidsMax() == poidsMax;
	 * @ensures getPoids() == 0;
	 *
	 * @param nom      Le nom du joueur.
	 * @param piece    La piece du joueur.
	 * @param poidsMax Le poids maximal transportable par ce joueur.
	 *
	 * @throws NullPointerException     si le nom sp�cifi� est null.
	 * @throws NullPointerException     si la piece sp�cifi�e est null.
	 * @throws IllegalArgumentException si le poids maximal � transport� est
	 *                                  inf�ieur ou �gal � 0.
	 */
	public Joueur(String nom, Piece piece, int poidsMax) {
		super();
		if (nom == null) {
			throw new NullPointerException("Le nom du Joueur doit �tre non null");
		}
		if (piece == null) {
			throw new NullPointerException("La pi�ce du Joueur doit �tre non null");
		}
		if (poidsMax <= 0) {
			throw new IllegalArgumentException(
					"Le poids maximal transportable par un Joueur doit �tre strictement positif");
		}

		this.nom = nom;
		this.piece = piece;
		this.poidsMax = poidsMax;
	}

	/**
	 * Renvoie le nom du joueur.
	 *
	 *
	 * @return Le nom du joueur.
	 *
	 * @pure
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Revoie la piece ou se trouve le joueur.
	 *
	 * @return la pi�ce o� se trouve le joueur.
	 *
	 * @pure
	 */
	public Piece getPiece() {
		return piece;
	}

	/**
	 * Change la pi�ce ou le joueur se trouve.
	 * 
	 * @requires newPiece != null;
	 * @ensures getPiece() == newPiece;
	 * 
	 * @param newPiece la nouvelle pi�ce ou se trouve le joueur.
	 * @throws NullPointerException la pi�ce ou se trouve un joueur doit �tre non
	 *                              null.
	 */
	public void setPiece(Piece newPiece) {
		if (newPiece == null) {
			throw new NullPointerException("La pi�ce du Joueur doit �tre non null");
		}
		this.piece = newPiece;
	}

	/**
	 * Renvoie la somme des poids de l'ensemble des ObjetZork transport�s par ce
	 * Joueur.
	 *
	 *
	 * @return Le poids total des ObjetZork de ce Joueur.
	 *
	 * @pure
	 */
	public int getPoids() {
		return poids;
	}

	/**
	 * Retourne le poids maximal des objets que le joueur peut porter.
	 *
	 * @return le poids maximal transportable par ce Joueur
	 *
	 * @pure
	 */
	public int getPoidsMax() {
		return poidsMax;
	}

	/**
	 * Change le poids maximal � transport� par le joueur.
	 * 
	 * @requires poidsMax > 0;
	 * @ensures getPoidsMax() == poidsMax;
	 * 
	 * @param newPoids
	 * 
	 * @throws IllegalArgumentException si le poids maximal � transport� est
	 *                                  inf�ieur ou �gal � 0.
	 */
	public void setPoidsMax(int newPoidsMax) {
		if (newPoidsMax <= 0) {
			throw new IllegalArgumentException(
					"Le poids maximal transportable par un Joueur doit �tre strictement positif");
		}
		poidsMax = newPoidsMax;
	}

	/**
	 * Revoie une description de ce joueur en mentionant son nom et le poids maximal
	 * qu'il peut transporter et le poids restant qu'il peut transporter.
	 *
	 * @return Description affichable de ce joueur.
	 *
	 * @pure
	 */
	public String descriptionLongue() {
		String descriptionLongue;
		descriptionLongue = "               " + nom + "\nPoids maximal a transporter: " + poidsMax + " kg."
				+ "\nPoids restant a transporter: " + this.poidsRestantATrans() + " Kg.\n";
		return descriptionLongue;
	}

	/**
	 * Calcule le poids des objets transport� par le joueur. Si le joueur ne
	 * transporte pas d'objet renvoi 0.
	 * 
	 * @ensures poidsTransporte >= 0;
	 *
	 * @return le poids totale transport� par le joueur.
	 * 
	 * @pure
	 */
	public int poidsTrans() {
		int poidsTansporte = 0;
		if (super.getNbObjets() == 0)
			return 0;
		else {
			for (int i = 0; i < super.getNbObjets(); i++) {
				poidsTansporte += super.getLesObjets().get(i).getPoids();
			}
			return poidsTansporte;
		}
	}

	/**
	 * Calcule le poids restant que le joueur peut transporter.
	 *
	 * @ensures poidsRestantATrans() >= 0;
	 * 
	 * @return Le poids restant a transporter.
	 * 
	 * @pure
	 */
	public int poidsRestantATrans() {
		int poidsTransporte = this.poidsTrans();
		int poidsRestantATransporte = poidsMax - poidsTransporte;
		return poidsRestantATransporte;
	}

	/**
	 * Renvoie true si l'ObjetZork sp�cifi� est non null et son poids additionn� au
	 * poids des objets transport� par le joueur est inf�rieur au poids maximal
	 * transportable par ce dernier.
	 *
	 * @ensures \result <==> (oz != null) && ((getPoids() + oz.getPoids()) <=
	 *          getPoidsMax());
	 *
	 * @param oz L'objet dont on souhaite savoir si son poids additionn� a celui des
	 *           objets transport� par le joueur est inf�rieur ou non au poidsMax du
	 *           joueur.
	 * @return true si l'ObjetZork si son poids additionn�� celui des objets
	 *         tranport� par le joueur est inf�rieur au poidsMax du joueur, false si
	 *         non.
	 *
	 * @pure
	 */
	public boolean PoidsMaxSupPoidsPlus(ObjetZork oz) {
		return (oz != null) && (poids + oz.getPoids()) <= poidsMax;
	}

	/**
	 * Renvoie true si l'ObjetZork sp�cifi� peut �tre ajout� � ce Joueur. Un
	 * ObjetZork peut-�tre ajout� s'il est diff�rent de null, s'il est transportable
	 * et si le poids des ObjetZork transport�s apr�s l'ajout resterait inf�rieur au
	 * poids maximal fix� pour ce Joueur.
	 *
	 * @ensures \result <==> (oz != null) && ((getPoids() + oz.getPoids()) <=
	 *          getPoidsMax());
	 *
	 * @param oz L'objet dont on souhaite savoir s'il peut �tre ajout� aux objets
	 *           transport�s par ce Joueur
	 * @return true si l'ObjetZork sp�cifi� peut-�tre ajout� � la charge de ce
	 *         Joueur ; false sinon.
	 *
	 * @pure
	 */
	public boolean ajoutEstPossible(ObjetZork oz) {
		return (oz != null) && oz.estTransportable() && ((poids + oz.getPoids()) <= poidsMax);
	}

	/**
	 * Ajoute si possible l'objet sp�cifi� aux objets possed� par ce joueur. Si
	 * l'objet est d�ja en poss�d�, un exemplaire suppl�mentaire de cet objet y est
	 * ajout�. Si l'ajout n'est pas possible (au sens de la m�thode
	 * ajoutEstPossible()), l'objet n'est pas ajout� et la valeur false est
	 * retourn�e.
	 *
	 * @ensures \result == \old(ajoutEstPossible(oz));
	 * @ensures \result ==> contient(oz);
	 * @ensures \result ==> (getPoids() == (\old(getPoids()) + oz.getPoids()));
	 * @ensures contient(oz) <==> (\old(ajoutEstPossible(oz)) ||
	 *          \old(contient(oz)));
	 * @ensures \result <==> (contientCombienDe(oz) == (\old(contientCombienDe(oz))
	 *          + 1));
	 * @ensures !\result <==> (contientCombienDe(oz) ==
	 *          \old(contientCombienDe(oz)));
	 *
	 * @param oz L'objet a ajouter au objets tranport�s par le joueur.
	 * @return true si l'objet a �t� effectivement ajout� � ce Joueur ; false sinon.
	 */
	@Override
	public boolean ajouter(ObjetZork oz) {
		if (!ajoutEstPossible(oz)) {
			return false;
		}
		super.getLesObjets().add(oz);
		poids += oz.getPoids();
		return true;
	}

	/**
	 * Retire un exemplaire de l'objet sp�cifi� du joueur si cet objet y est
	 * pr�sent. Renvoie true si cet objet �tait effectivement pr�sent chez le joueur
	 * et que l'objet a pu etre effectivement retir� ; renvoie false sinon.
	 * L'argument doit etre non <code>null</code>.
	 *
	 * @ensures \old(contient(oz)) <==> \result;
	 * @ensures \old(contient(oz)) <==> (contientCombienDe(oz) ==
	 *          \old(contientCombienDe(oz)) - 1);
	 * @ensures \old(contientCombienDe(oz) == 1) ==> !contient(oz);
	 * @ensures \old(contientCombienDe(oz) > 1) <==> contient(oz);
	 * @ensures \result ==> (getPoids() == (\old(getPoids()) - oz.getPoids()));
	 *
	 * @param oz Objet dont un exemplaire doit etre retirer des objets transport�s
	 *           par le joueur.
	 * @return true si cet objet �tait effectivement pr�sent ; false sinon.
	 */
	@Override
	public boolean retirer(ObjetZork oz) {
		if (oz == null) {
			return false;
		}
		if (contient(oz)) {
			super.getLesObjets().remove(oz);
			poids -= oz.getPoids();
		}
		return false;
	}

	/**
	 * Affiche la descriptionLongue de tous les objets transport�s par le joueur, si
	 * le contenu de ces objets est vide affiche un message indiquant cela.
	 * 
	 * @pure
	 */
	public void afficherLesObjets() {
		if (super.getNbObjets() == 0) {
			System.out.println(this.descriptionLongue());
			System.out.println("Le sac de " + nom + " est vide.");
		} else {
			System.out.println(this.descriptionLongue());
			System.out.println("Voici la liste des objets qui sont dans le sac de " + nom);
			for (int i = 0; i < super.getNbObjets(); i++) {
				System.out.println(super.getLesObjets().get(i).descriptionLongue());
			}
		}
	}
}
