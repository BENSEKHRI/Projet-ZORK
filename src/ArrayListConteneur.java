import java.util.ArrayList;

/**
 * Un conteneur d'ObjetZork dans un jeu d'aventure.
 * 
 * Cette classe fait partie du logiciel Zork, un jeu d'aventure simple en mode
 * texte.
 * 
 * Une super classe qui permet de stock� les Objets Zork.
 * 
 * <p>
 * La classe ArrayListConteneur est une classe abstract car on aura pas besoin
 * d'un type ArrayListConteneur. Mais seulement du fait de l'h�ritage des
 * attributs et des m�thodes par les classe filles.
 * </p>
 * 
 * <p>
 * Un ArrayListConteneur est un conteneur d'ObjetZork poss�dant les propri�t�s
 * suivantes:
 * <ul>
 * <li>Un nombre non born� d'ObjetZork peut �tre ajout�.</li>
 * <li>Le ArrayListConteneur peut contenir plusieurs exemplaires d'un m�me
 * ObjetZork</li>
 * <li>La valeur null n'est pas autoris�e parmi les ObjetZork pr�sents</li>
 * <li>Les ObjetZork contenus dans le ArrayListConteneur ne sont pas
 * ordonn�s.</li>
 * </ul>
 * 
 * Cette super classe permet � toutes les classe qui l'h�rite d'acc�der aux
 * m�thodes impl�ment�es et aux attributs pr�sentes. Les Classes qui h�riteront
 * de cette classe est la classe Piece et la classe Joueur et cela pour que la
 * classe piece h�rite de la liste d'objets qui sont stock� en elle et le joueur
 * h�rite de la liste d'objets qui sont stock� dans le sac de ce dernier.
 * 
 * @invariant !contient(null);
 * @invariant getNbObjets() >= 0;
 * 
 * @author Mohand Lounis BENSEKHRI 11710457.
 */
abstract public class ArrayListConteneur {
	private ArrayList<ObjetZork> lesObjets;

	/**
	 * Initialise un ArrayListConteneur qui est un ArrayList d'objets Zork.
	 *
	 * @ensures this.getNbObjets == 0;
	 *
	 * @pure
	 */
	public ArrayListConteneur() {
		lesObjets = new ArrayList<ObjetZork>();
	}

	/**
	 * Renvoie le nombre d'ObjetZork pr�sents dans cet ArrayListConteneur.
	 *
	 * @ensures \result >= 0;
	 *
	 * @return Le nombre d'ObjetZork pr�sents dans cet ArrayListConteneur.
	 *
	 * @pure
	 */
	public int getNbObjets() {
		return lesObjets.size();
	}

	/**
	 * Renvoie une nouvelle instance de la liste contenant tous les objets pr�sents
	 * dans cette piece. La liste renvoy� contient, pour chaque objet, autant
	 * d'exemplaires de cet objet que le ArrayListConteneur en contient. La taille
	 * de la liste renvoy� est identique au nombre d'objets pr�sents dans cette
	 * ArrayListConteneur. L'ordre dans lequel les objets sont plac� dans la liste
	 * n'est pas significatif, lorsque des op�rations modifiant les objets pr�sents
	 * dans cet ArrayListConteneur surviennent, il n'est pas garanti que plusieurs
	 * appels successifs � cette m�thode renvoyent les objets dans le m�me ordre, y
	 * compris pour ce qui est des objets non concern�s par ces modifications.
	 * Cependant, si entre deux appels � cette m�thode, aucune modification n'est
	 * intervenue sur les objets pr�sents dans cet ArrayListConteneur, il est
	 * garanti que les objets sont renvoy�s dans le m�me ordre.
	 *
	 * @ensures \result != null;
	 * @ensures \result.size == lesObjets.size();
	 * @ensures (\forall int i; i >= 0 && i < lesObjets.size(); \result.get(i) !=
	 *          null && contient(\result[i]) && (contientCombienDe(\result[i]) ==
	 *          (\num_of int j; j >= 0 && j < lesObjets.size();
	 *          \result[i].equals(\result[j]))));
	 *
	 * @return Une liste contenant tous les objets pr�sents dans cette piece
	 *
	 * @pure
	 */
	public ArrayList<ObjetZork> getLesObjets() {

		/*
		 * ArrayList<ObjetZork> result = new ArrayList<ObjetZork>();
		 * 
		 * for (ObjetZork oz : lesObjets) { result.add(oz.clone()); }
		 * 
		 * return result;
		 */

		return lesObjets;
	}

	/**
	 * Renvoie le nombre d'exemplaires de l'objet sp�cifi� pr�sents dans cet
	 * ArrayListConteneur. La pr�sence d'un objet est test� en utilisant la m�thode
	 * equals de la classe ObjetZork. Renvoie 0 si l'argument est null.
	 *
	 * @ensures \result >= 0;
	 * @ensures contient(oz) <==> \result > 0;
	 * @ensures !contient(oz) <==> \result == 0;
	 * @ensures (oz == null) ==> (\result == 0);
	 *
	 * @param oz Objet dont on cherche a savoir en combien d'exemplaires il est
	 *           pr�sent dans cet ArrayListConteneur.
	 * @return le nombre d'exemplaires de l'objet sp�cifi� pr�sents dans cet
	 *         ArrayListConteneur.
	 * @pure
	 */
	public int contientCombienDe(ObjetZork oz) {
		int n = 0;
		for (ObjetZork o : lesObjets) {
			if (o.equals(oz)) {
				n++;
			}
		}
		return n;
	}

	/**
	 * Renvoie true si cet ArrayListConteneur contient au moins un exemplaire de
	 * l'objet sp�cifi�. Renvoie false si l'argument est <code>null</code>.
	 *
	 * @ensures \result <==> (contientCombienDe(oz) > 0);
	 *
	 * @param oz Objet dont on cherche a savoir s'il est pr�sent dans cet
	 *           ArrayListConteneur.
	 * @return true si cet ArrayListConteneur contient au moins un exemplaire de
	 *         l'objet sp�cifi� ; false sinon.
	 * @pure
	 */
	public boolean contient(ObjetZork oz) {
		if (oz == null) {
			return false;
		}
		return lesObjets.contains(oz);
	}

	/**
	 * Ajoute l'objet sp�cifi� aux objets pr�sents dans cet ArrayListConteneur. Si
	 * l'objet est d�ja pr�sent dans cet ArrayListConteneur un exemplaire
	 * suppl�mentaire de cet objet y est ajout�. L'argument doit etre non
	 * <code>null</code>.
	 *
	 * @requires oz != null;
	 * @ensures contient(oz);
	 * @ensures contientCombienDe(oz) == \old(contientCombienDe(oz)) + 1;
	 * @ensures this.getNbObjets += 1;
	 *
	 * @param oz L'objet a ajouter dans cet ArrayListConteneur.
	 * @return true.
	 * @throws NullPointerException si le param�tre est null
	 */
	public boolean ajouter(ObjetZork oz) {
		if (oz == null) {
			throw new NullPointerException("L'ObjetZork � ajouter doit �tre non null");
		}
		lesObjets.add(oz);
		return true;
	}

	/**
	 * Retire un exemplaire de l'objet sp�cifi� de cet ArrayListConteneur si cet
	 * objet y est pr�sent. Renvoie true si cet objet �tait effectivement pr�sent
	 * dans le ArrayListConteneur et que l'objet a pu etre effectivement retir� ;
	 * renvoie false sinon. L'argument doit etre non <code>null</code>.
	 *
	 * @ensures \old(contient(oz)) <==> \result;
	 * @ensures \old(contient(oz)) <==> (contientCombienDe(oz) ==
	 *          \old(contientCombienDe(oz)) - 1);
	 * @ensures \old(contientCombienDe(oz) == 1) ==> !contient(oz);
	 * @ensures \old(contientCombienDe(oz) > 1) <==> contient(oz);
	 *
	 * @param oz Objet dont un exemplaire doit etre retirer de cet
	 *           ArrayListConteneur.
	 * @return true si cet objet �tait effectivement pr�sent ; false sinon.
	 */
	public boolean retirer(ObjetZork oz) {
		if (oz == null) {
			return false;
		}
		return lesObjets.remove(oz);
	}

	/**
	 * Renvoi le clone de l'Objet trouv� dans le ArrayListConteneur qui � la
	 * description sp�cifi�. La m�thode utilis� pour cloner l'objet est la m�thode
	 * clone de la classe ObjetZork. si aucun objet � �t� trouv� renvoy� null.
	 * 
	 * 
	 * @param description
	 * @return le clone de l l'Objet trouv� dans le ArrayListConteneur qui � la
	 *         description sp�cifi�. ou null si pas trouv�.
	 * 
	 * @pure
	 */
	public ObjetZork trouverObjDesc(String description) {
		for (int i = 0; i < lesObjets.size(); i++) {
			if (lesObjets.get(i).descriptionCourte().equals(description)) {
				return lesObjets.get(i).clone();
			}
		}
		return null;
	}
}
