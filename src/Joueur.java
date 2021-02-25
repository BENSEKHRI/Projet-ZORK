/**
 * 
 * <p>
 * La classe Joueur hérite de la classe ArrayListConteneur qui permet de stocké
 * les ObjetZork transporté par ce joueur. Toutes les méthodes implémentés dans
 * la classe ArrayListConteneur sont accéssible par la classe Joueur et cela
 * grâce à la méthode super.
 * </p>
 * 
 * <p>
 * Modélise un joueur pour le jeu Zork. Un Joueur est caractérisé par un nom, un
 * poids maximal ainsi que par les ObjetZork qu'il "transporte" avec lui et dont
 * le poids total ne doit pas excéder le poids maximal.
 * </p>
 *
 * <p>
 * La somme des poids de tous les ObjetZork transportés doit toujours être
 * inférieure ou égale à un poids maximal fixé définitivement à la création du
 * Joueur. Ce poids maximal doit toujours être supérieur ou égal à zéro.
 * </p>
 *
 * <p>
 * Pour le transport des ObjetZork, le Joueur hérite de la classe
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
	 * Initalise un joueur ne transportant aucun objet et dont le nom, la pièce ou
	 * il se trouve et le poids maximal à transporté sont spécifiés. le nom et la
	 * pièce doivent être non null. le poidsMax à transporter doit être strictement
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
	 * @throws NullPointerException     si le nom spécifié est null.
	 * @throws NullPointerException     si la piece spécifiée est null.
	 * @throws IllegalArgumentException si le poids maximal à transporté est
	 *                                  inféieur ou égal à 0.
	 */
	public Joueur(String nom, Piece piece, int poidsMax) {
		super();
		if (nom == null) {
			throw new NullPointerException("Le nom du Joueur doit être non null");
		}
		if (piece == null) {
			throw new NullPointerException("La pièce du Joueur doit être non null");
		}
		if (poidsMax <= 0) {
			throw new IllegalArgumentException(
					"Le poids maximal transportable par un Joueur doit être strictement positif");
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
	 * @return la pièce où se trouve le joueur.
	 *
	 * @pure
	 */
	public Piece getPiece() {
		return piece;
	}

	/**
	 * Change la pièce ou le joueur se trouve.
	 * 
	 * @requires newPiece != null;
	 * @ensures getPiece() == newPiece;
	 * 
	 * @param newPiece la nouvelle pièce ou se trouve le joueur.
	 * @throws NullPointerException la pièce ou se trouve un joueur doit être non
	 *                              null.
	 */
	public void setPiece(Piece newPiece) {
		if (newPiece == null) {
			throw new NullPointerException("La pièce du Joueur doit être non null");
		}
		this.piece = newPiece;
	}

	/**
	 * Renvoie la somme des poids de l'ensemble des ObjetZork transportés par ce
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
	 * Change le poids maximal à transporté par le joueur.
	 * 
	 * @requires poidsMax > 0;
	 * @ensures getPoidsMax() == poidsMax;
	 * 
	 * @param newPoids
	 * 
	 * @throws IllegalArgumentException si le poids maximal à transporté est
	 *                                  inféieur ou égal à 0.
	 */
	public void setPoidsMax(int newPoidsMax) {
		if (newPoidsMax <= 0) {
			throw new IllegalArgumentException(
					"Le poids maximal transportable par un Joueur doit être strictement positif");
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
	 * Calcule le poids des objets transporté par le joueur. Si le joueur ne
	 * transporte pas d'objet renvoi 0.
	 * 
	 * @ensures poidsTransporte >= 0;
	 *
	 * @return le poids totale transporté par le joueur.
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
	 * Renvoie true si l'ObjetZork spécifié est non null et son poids additionné au
	 * poids des objets transporté par le joueur est inférieur au poids maximal
	 * transportable par ce dernier.
	 *
	 * @ensures \result <==> (oz != null) && ((getPoids() + oz.getPoids()) <=
	 *          getPoidsMax());
	 *
	 * @param oz L'objet dont on souhaite savoir si son poids additionné a celui des
	 *           objets transporté par le joueur est inférieur ou non au poidsMax du
	 *           joueur.
	 * @return true si l'ObjetZork si son poids additionnéà celui des objets
	 *         tranporté par le joueur est inférieur au poidsMax du joueur, false si
	 *         non.
	 *
	 * @pure
	 */
	public boolean PoidsMaxSupPoidsPlus(ObjetZork oz) {
		return (oz != null) && (poids + oz.getPoids()) <= poidsMax;
	}

	/**
	 * Renvoie true si l'ObjetZork spécifié peut être ajouté à ce Joueur. Un
	 * ObjetZork peut-être ajouté s'il est différent de null, s'il est transportable
	 * et si le poids des ObjetZork transportés après l'ajout resterait inférieur au
	 * poids maximal fixé pour ce Joueur.
	 *
	 * @ensures \result <==> (oz != null) && ((getPoids() + oz.getPoids()) <=
	 *          getPoidsMax());
	 *
	 * @param oz L'objet dont on souhaite savoir s'il peut être ajouté aux objets
	 *           transportés par ce Joueur
	 * @return true si l'ObjetZork spécifié peut-être ajouté à la charge de ce
	 *         Joueur ; false sinon.
	 *
	 * @pure
	 */
	public boolean ajoutEstPossible(ObjetZork oz) {
		return (oz != null) && oz.estTransportable() && ((poids + oz.getPoids()) <= poidsMax);
	}

	/**
	 * Ajoute si possible l'objet spécifié aux objets possedé par ce joueur. Si
	 * l'objet est déja en possédé, un exemplaire supplémentaire de cet objet y est
	 * ajouté. Si l'ajout n'est pas possible (au sens de la méthode
	 * ajoutEstPossible()), l'objet n'est pas ajouté et la valeur false est
	 * retournée.
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
	 * @param oz L'objet a ajouter au objets tranportés par le joueur.
	 * @return true si l'objet a été effectivement ajouté à ce Joueur ; false sinon.
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
	 * Retire un exemplaire de l'objet spécifié du joueur si cet objet y est
	 * présent. Renvoie true si cet objet était effectivement présent chez le joueur
	 * et que l'objet a pu etre effectivement retiré ; renvoie false sinon.
	 * L'argument doit etre non <code>null</code>.
	 *
	 * @ensures \old(contient(oz)) <==> \result;
	 * @ensures \old(contient(oz)) <==> (contientCombienDe(oz) ==
	 *          \old(contientCombienDe(oz)) - 1);
	 * @ensures \old(contientCombienDe(oz) == 1) ==> !contient(oz);
	 * @ensures \old(contientCombienDe(oz) > 1) <==> contient(oz);
	 * @ensures \result ==> (getPoids() == (\old(getPoids()) - oz.getPoids()));
	 *
	 * @param oz Objet dont un exemplaire doit etre retirer des objets transportés
	 *           par le joueur.
	 * @return true si cet objet était effectivement présent ; false sinon.
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
	 * Affiche la descriptionLongue de tous les objets transportés par le joueur, si
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
