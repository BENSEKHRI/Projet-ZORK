/**
 * <p>
 *
 * Cette classe fait partie du logiciel Zork, un jeu d'aventure simple en mode
 * texte.
 * </p>
 * <p>
 *
 * Cette classe répertorie les informations liées a une commande entrée par
 * l'utilisateur. Une commande est constituée soit: d'une chaine de caractères
 * mot-clé de commande seulement (par exemple, si la commande entrée par
 * l'utilisateur est piece).
 * </p>
 * <p>
 *
 * de deux chaines de caractères: un mot-clé de commande et un second mot
 * apportant un complément (un paramètre) au mot-clé indiquant la commande a
 * exécuter (par exemple, si la commande entrée par l'utilisateur est "prendre
 * Epee", alors les deux chaines de caractères sont "prendre" et "Epee").
 * </p>
 * <p>
 *
 * de trois chaines de caractère: un mot-clé de commande et deux autres mots
 * apportants un complément (un paramètre) au mot-clé indiquant la commande a
 * exécuter (par exemple, si la commande entrée par l'utilisateur est "tuer
 * Dragon Lance", alors les trois chaines sont "prendre" et "Epee" et "Lance").
 * </p>
 * <p>
 *
 * Les mots utilisés lors de l'initialisation d'une instance de cette classe
 * sont supposés venir d'une commande utilisateur dont la validité a déjà été
 * testée:
 * <ul>
 * <li>si le mot commande entré par l'utilisateur ne correspond pas a une
 * commande valide, alors la valeur du mot commande donné a l'initialisation
 * doit etre null</li>
 * <li>si la commande entrée par l'utilisateur ne contient pas d'autre mot que
 * le mot commande, alors la valeur du second mot et du troisième mot donné a
 * l'initialisation doit etre null</li>
 * </ul>
 * La validité du second mot n'est pas testée, sa valeur peut etre quelconque.
 * La validité du troisième mot n'est pas testée, sa valeur peut etre
 * quelconque.
 * </p>
 *
 * @author Michael Kolling
 * @author Marc Champesme (pour la traduction francaise)
 * @version 1.0
 * @since July 1999
 * @author Mohand Lounis BENSEKHRI 11710457 (modifications).
 */

public class Commande {
	// @ pure
	private String motCommande;
	private String secondMot;
	private String troisiemeMot;

	/**
	 * Initialise une Commande a partir des deux mots spécifiés.
	 * <p>
	 *
	 * Le premier argument représente un mot commande, sa valeur peut etre null si
	 * le mot commande ne correspond pas a une commande valide. Le second mot peut
	 * également etre null dans le cas ou l'utilisateur n'aurait pas fourni de
	 * second mot dans sa commande.
	 * </p>
	 *
	 * @param motCommande Le mot commande de la commande utilisateur ou null
	 * @param secondMot   Le second mot de la commande utilisateur ou null
	 */
	// @pure
	public Commande(String motCommande, String secondMot) {
		this.motCommande = motCommande;
		this.secondMot = secondMot;
	}

	/**
	 * Initialise une Commande a partir des trois mots spécifiés.
	 * <p>
	 *
	 * Le premier argument représente un mot commande, sa valeur peut etre null si
	 * le mot commande ne correspond pas a une commande valide. Le second mot peut
	 * également etre null dans le cas ou l'utilisateur n'aurait pas fourni de
	 * second mot dans sa commande. Le troisième mot doit obligatoirement être un
	 * entier pas une chaine de caractère quelconque peut être également null dans
	 * le cas ou l'utilisateur n'aurait pas fourni de troisième mot dans sa
	 * commande./p>
	 *
	 * @param motCommande  Le mot commande de la commande utilisateur ou null
	 * @param secondMot    Le second mot de la commande utilisateur ou null
	 * @param TroisiemeMot Le troisième mot qui doit être un entier de la commande
	 *                     utilisateur ou null
	 */
	// @pure
	public Commande(String motCommande, String secondMot, String TroisiemeMot) {
		this.motCommande = motCommande;
		this.secondMot = secondMot;
		this.troisiemeMot = TroisiemeMot;
	}

	/**
	 * Renvoie le mot commande (le premier mot) de cette Commande. Si cette commande
	 * n'est pas une commande valide, la valeur renvoyée est null.
	 *
	 * @return Le mot commande de cette Commande ou null.
	 */
	// @ pure
	public String getMotCommande() {
		return motCommande;
	}

	/**
	 * Renvoie le second mot de cette Commande ou null si cette commande ne possède
	 * pas de second mot.
	 *
	 * @return le second mot de cette Commande ou null.
	 */
	// @ pure
	public String getSecondMot() {
		return secondMot;
	}

	/**
	 * Renvoie le troisieme mot de cette Commande ou null si cette commande ne
	 * possède pas de troisieme mot.
	 *
	 * @return le troisieme mot de cette Commande ou null.
	 */
	// @ pure
	public String getTroisiemeMot() {
		return troisiemeMot;
	}

	/**
	 * Renvoie le troisième mot de cette Commande convertie en int
	 *
	 * @requires troisiemeMot != null;
	 * 
	 * @return la valeur en int du troisième mot de cette Commande
	 * @throws NullPointerException Si cette commande n'a pas de troisieme mot, on
	 *                              ne peut pas le convertir en int.
	 */
	public int getTroisiemeMotToInt() {
		if (troisiemeMot == null) {
			throw new NullPointerException("Cette commande n'a pas de troisieme mot pour le convertir en int.");
		}
		return Integer.valueOf(this.troisiemeMot).intValue();
	}

	/**
	 * Teste si cette commande est une commande reconnue par le jeu.
	 *
	 * @return true si cette commande est valide ; false sinon.
	 */
	// @ pure
	public boolean estInconnue() {
		return (motCommande == null);
	}

	/**
	 * Teste si cette commande possède un second mot.
	 *
	 * @return true si cette commande possède un second mot ; false sinon.
	 */
	// @ pure
	public boolean aSecondMot() {
		return (secondMot != null);
	}

	/**
	 * Teste si cette commande possède un troisième mot.
	 *
	 * @return true si cette commande possède un troisième mot ; false sinon.
	 */
	// @ pure
	public boolean aTroisiemeMot() {
		return (troisiemeMot != null);
	}
}
