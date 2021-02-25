import java.util.HashMap;

/**
 * Une piece dans un jeu d'aventure.
 * 
 * <p>
 * Cette classe fait partie du logiciel Zork, un jeu d'aventure simple en mode
 * texte.
 * </p>
 *
 * <p>
 * Une "Piece" represente un des lieux dans lesquels se d�roule l'action du jeu.
 * Elle est reli�e a au plus quatre autres "Piece" par des sorties. Les sorties
 * sont �tiquett�es "nord", "est", "sud", "ouest". Pour chaque direction, la
 * "Piece" poss�de une r�f�rence sur la piece voisine ou null s'il n'y a pas de
 * sortie dans cette direction.
 * </p>
 *
 * <p>
 * Chaque piece � une description sp�cifi�e qui est un String qui ne doit pas
 * �tre �gale � null.
 * </p>
 * 
 * <p>
 * La classe Piece h�rite de la classe ArrayListConteneur qui est la liste des
 * ObjetZork qui sont dans ce cas pour la piece les objets qu'elle contient. Ces
 * Objets ne sont pas ordonn�s.
 * </p>
 * 
 * <p>
 * Toutes les m�thodes impl�ment�s dans la classe ArrayListConteneur sont
 * utilisable par la classe Piece et cela gr�ce � la m�thode super
 * </p>
 *
 * @invariant descriptionCourte() != null;
 * @invariant descriptionLongue() != null;
 * @invariant descriptionSorties() != null;
 *
 * @author Mohand Lounis BENSEKHRI 11710457.
 */

public class Piece extends ArrayListConteneur {
	private String description;

	// m�morise les sorties de cette piece.
	private HashMap<Direction, Piece> sorties;

	/**
	 * Initialise une piece d�crite par la chaine de caract�res sp�cifi�e.
	 * Initialement, cette piece ne poss�de aucune sortie. La description fournie
	 * est une courte phrase comme "Le Chateau" ou "Le Nid Du Dragon".
	 *
	 * @requires description != null;
	 * @ensures descriptionCourte() == description;
	 * @ensures pieceSuivante("nord") == null;
	 * @ensures pieceSuivante("est") == null;
	 * @ensures pieceSuivante("sud") == null;
	 * @ensures pieceSuivante("ouest") == null;
	 *
	 * @param description Description de la piece.
	 * @throws NullPointerException si le param�tre est null.
	 */
	public Piece(String description) {
		super();
		if (description == null) {
			throw new NullPointerException("La description d'une Piece ne peut-�tre null");
		}
		this.description = description;
		sorties = new HashMap<Direction, Piece>();
	}

	/**
	 * D�finie les sorties de cette piece. A chaque direction correspond ou bien une
	 * piece ou bien la valeur null signifiant qu'il n'y a pas de sortie dans cette
	 * direction.
	 *
	 * @ensures (nord != null) ==> (pieceSuivante("nord") == nord);
	 * @ensures (est != null) ==> (pieceSuivante("est") == est);
	 * @ensures (sud != null) ==> (pieceSuivante("sud") == sud);
	 * @ensures (ouest != null) ==> (pieceSuivante("ouest") == ouest);
	 * @ensures (nord == null) ==> (pieceSuivante("nord") ==
	 *          \old(pieceSuivante("nord")));
	 * @ensures (est == null) ==> (pieceSuivante("est") ==
	 *          \old(pieceSuivante("est")));
	 * @ensures (sud == null) ==> (pieceSuivante("sud") ==
	 *          \old(pieceSuivante("sud")));
	 * @ensures (ouest == null) ==> (pieceSuivante("ouest") ==
	 *          \old(pieceSuivante("ouest")));
	 *
	 *
	 * @param nord  La sortie nord
	 * @param est   La sortie est
	 * @param sud   La sortie sud
	 * @param ouest La sortie ouest
	 */
	public void setSorties(Piece nord, Piece est, Piece sud, Piece ouest) {
		if (nord != null) {
			sorties.put(Direction.NORD, nord);
		}
		if (est != null) {
			sorties.put(Direction.EST, est);
		}
		if (sud != null) {
			sorties.put(Direction.SUD, sud);
		}
		if (ouest != null) {
			sorties.put(Direction.OUEST, ouest);
		}
	}

	/**
	 * Renvoie la piece atteinte lorsque l'on se d�place a partir de cette piece
	 * dans la direction sp�cifi�e. Si cette piece ne poss�de aucune sortie dans
	 * cette direction, renvoie null.
	 *
	 * @ensures (!direction.equals("nord") && !direction.equals("est") &&
	 *          !direction.equals("sud") && !direction.equals("ouest")) ==> (\result
	 *          == null);
	 *
	 * @param direction La direction dans laquelle on souhaite se d�placer.
	 * @return Piece atteinte lorsque l'on se d�place dans la direction sp�cifi�e ou
	 *         null.
	 *
	 * @pure
	 */
	public Piece pieceSuivante(Direction direction) {
		return sorties.get(direction);
	}

	/**
	 * Renvoie la description de cette piece (i.e. la description sp�cifi�e lors de
	 * la cr�ation de cette instance).
	 *
	 * @return Description de cette piece.
	 *
	 * @pure
	 */
	public String descriptionCourte() {
		return description;
	}

	/**
	 * Renvoie une description de cette piece mentionant ses sorties et directement
	 * format�e pour affichage, de la forme:
	 * 
	 * <pre>
	 *  Vous etes dans la biblioth�que.
	 *  Sorties: nord ouest
	 * </pre>
	 * 
	 * Cette description utilise la chaine de caract�res renvoy�e par la m�thode
	 * descriptionSorties pour d�crire les sorties de cette piece.
	 *
	 * @return Description affichable de cette piece.
	 *
	 * @pure
	 */
	public String descriptionLongue() {
		return "Vous etes dans " + description + ".\n" + descriptionSorties();
	}

	/**
	 * Renvoie une description des sorties de cette piece, de la forme:
	 * 
	 * <pre>
	 *  Sorties: nord ouest
	 * </pre>
	 * 
	 * Cette description est utilis�e dans la description longue d'une piece.
	 *
	 * @return Une description des sorties de cette pi�ce.
	 *
	 * @pure
	 */
	public String descriptionSorties() {
		String resulString = "Sorties:";
		for (Direction sortie : sorties.keySet()) {
			resulString += " " + sortie;
		}
		return resulString;
	}

	/**
	 * Affiche la descriptionLongue de tous les objets qui se trouve dans la pi�ce,
	 * si la pi�ce est vide affiche un message indiquant cela.
	 *
	 * @pure
	 */
	public void afficherPiece() {
		if (super.getNbObjets() == 0)
			System.out.println("La pi�ce est vide.");
		else {
			System.out.println("Voici les objets qui sont dans: " + description);
			for (int i = 0; i < super.getNbObjets(); i++) {
				System.out.println(super.getLesObjets().get(i).descriptionLongue());
			}
		}
	}
}
