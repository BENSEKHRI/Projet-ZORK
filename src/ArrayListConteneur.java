import java.util.ArrayList;

/**
 * Un conteneur d'ObjetZork dans un jeu d'aventure.
 * 
 * Cette classe fait partie du logiciel Zork, un jeu d'aventure simple en mode
 * texte.
 * 
 * Une super classe qui permet de stocké les Objets Zork.
 * 
 * <p>
 * La classe ArrayListConteneur est une classe abstract car on aura pas besoin
 * d'un type ArrayListConteneur. Mais seulement du fait de l'héritage des
 * attributs et des méthodes par les classe filles.
 * </p>
 * 
 * <p>
 * Un ArrayListConteneur est un conteneur d'ObjetZork possédant les propriétés
 * suivantes:
 * <ul>
 * <li>Un nombre non borné d'ObjetZork peut être ajouté.</li>
 * <li>Le ArrayListConteneur peut contenir plusieurs exemplaires d'un même
 * ObjetZork</li>
 * <li>La valeur null n'est pas autorisée parmi les ObjetZork présents</li>
 * <li>Les ObjetZork contenus dans le ArrayListConteneur ne sont pas
 * ordonnés.</li>
 * </ul>
 * 
 * Cette super classe permet à toutes les classe qui l'hérite d'accéder aux
 * méthodes implémentées et aux attributs présentes. Les Classes qui hériteront
 * de cette classe est la classe Piece et la classe Joueur et cela pour que la
 * classe piece hérite de la liste d'objets qui sont stocké en elle et le joueur
 * hérite de la liste d'objets qui sont stocké dans le sac de ce dernier.
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
	 * Renvoie le nombre d'ObjetZork présents dans cet ArrayListConteneur.
	 *
	 * @ensures \result >= 0;
	 *
	 * @return Le nombre d'ObjetZork présents dans cet ArrayListConteneur.
	 *
	 * @pure
	 */
	public int getNbObjets() {
		return lesObjets.size();
	}

	/**
	 * Renvoie une nouvelle instance de la liste contenant tous les objets présents
	 * dans cette piece. La liste renvoyé contient, pour chaque objet, autant
	 * d'exemplaires de cet objet que le ArrayListConteneur en contient. La taille
	 * de la liste renvoyé est identique au nombre d'objets présents dans cette
	 * ArrayListConteneur. L'ordre dans lequel les objets sont placé dans la liste
	 * n'est pas significatif, lorsque des opérations modifiant les objets présents
	 * dans cet ArrayListConteneur surviennent, il n'est pas garanti que plusieurs
	 * appels successifs à cette méthode renvoyent les objets dans le même ordre, y
	 * compris pour ce qui est des objets non concernés par ces modifications.
	 * Cependant, si entre deux appels à cette méthode, aucune modification n'est
	 * intervenue sur les objets présents dans cet ArrayListConteneur, il est
	 * garanti que les objets sont renvoyés dans le même ordre.
	 *
	 * @ensures \result != null;
	 * @ensures \result.size == lesObjets.size();
	 * @ensures (\forall int i; i >= 0 && i < lesObjets.size(); \result.get(i) !=
	 *          null && contient(\result[i]) && (contientCombienDe(\result[i]) ==
	 *          (\num_of int j; j >= 0 && j < lesObjets.size();
	 *          \result[i].equals(\result[j]))));
	 *
	 * @return Une liste contenant tous les objets présents dans cette piece
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
	 * Renvoie le nombre d'exemplaires de l'objet spécifié présents dans cet
	 * ArrayListConteneur. La présence d'un objet est testé en utilisant la méthode
	 * equals de la classe ObjetZork. Renvoie 0 si l'argument est null.
	 *
	 * @ensures \result >= 0;
	 * @ensures contient(oz) <==> \result > 0;
	 * @ensures !contient(oz) <==> \result == 0;
	 * @ensures (oz == null) ==> (\result == 0);
	 *
	 * @param oz Objet dont on cherche a savoir en combien d'exemplaires il est
	 *           présent dans cet ArrayListConteneur.
	 * @return le nombre d'exemplaires de l'objet spécifié présents dans cet
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
	 * l'objet spécifié. Renvoie false si l'argument est <code>null</code>.
	 *
	 * @ensures \result <==> (contientCombienDe(oz) > 0);
	 *
	 * @param oz Objet dont on cherche a savoir s'il est présent dans cet
	 *           ArrayListConteneur.
	 * @return true si cet ArrayListConteneur contient au moins un exemplaire de
	 *         l'objet spécifié ; false sinon.
	 * @pure
	 */
	public boolean contient(ObjetZork oz) {
		if (oz == null) {
			return false;
		}
		return lesObjets.contains(oz);
	}

	/**
	 * Ajoute l'objet spécifié aux objets présents dans cet ArrayListConteneur. Si
	 * l'objet est déja présent dans cet ArrayListConteneur un exemplaire
	 * supplémentaire de cet objet y est ajouté. L'argument doit etre non
	 * <code>null</code>.
	 *
	 * @requires oz != null;
	 * @ensures contient(oz);
	 * @ensures contientCombienDe(oz) == \old(contientCombienDe(oz)) + 1;
	 * @ensures this.getNbObjets += 1;
	 *
	 * @param oz L'objet a ajouter dans cet ArrayListConteneur.
	 * @return true.
	 * @throws NullPointerException si le paramètre est null
	 */
	public boolean ajouter(ObjetZork oz) {
		if (oz == null) {
			throw new NullPointerException("L'ObjetZork à ajouter doit être non null");
		}
		lesObjets.add(oz);
		return true;
	}

	/**
	 * Retire un exemplaire de l'objet spécifié de cet ArrayListConteneur si cet
	 * objet y est présent. Renvoie true si cet objet était effectivement présent
	 * dans le ArrayListConteneur et que l'objet a pu etre effectivement retiré ;
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
	 * @return true si cet objet était effectivement présent ; false sinon.
	 */
	public boolean retirer(ObjetZork oz) {
		if (oz == null) {
			return false;
		}
		return lesObjets.remove(oz);
	}

	/**
	 * Renvoi le clone de l'Objet trouvé dans le ArrayListConteneur qui à la
	 * description spécifié. La méthode utilisé pour cloner l'objet est la méthode
	 * clone de la classe ObjetZork. si aucun objet à été trouvé renvoyé null.
	 * 
	 * 
	 * @param description
	 * @return le clone de l l'Objet trouvé dans le ArrayListConteneur qui à la
	 *         description spécifié. ou null si pas trouvé.
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
