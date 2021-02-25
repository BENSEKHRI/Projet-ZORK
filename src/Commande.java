/**
 * <p>
 *
 * Cette classe fait partie du logiciel Zork, un jeu d'aventure simple en mode
 * texte.
 * </p>
 * <p>
 *
 * Cette classe r�pertorie les informations li�es a une commande entr�e par
 * l'utilisateur. Une commande est constitu�e soit: d'une chaine de caract�res
 * mot-cl� de commande seulement (par exemple, si la commande entr�e par
 * l'utilisateur est piece).
 * </p>
 * <p>
 *
 * de deux chaines de caract�res: un mot-cl� de commande et un second mot
 * apportant un compl�ment (un param�tre) au mot-cl� indiquant la commande a
 * ex�cuter (par exemple, si la commande entr�e par l'utilisateur est "prendre
 * Epee", alors les deux chaines de caract�res sont "prendre" et "Epee").
 * </p>
 * <p>
 *
 * de trois chaines de caract�re: un mot-cl� de commande et deux autres mots
 * apportants un compl�ment (un param�tre) au mot-cl� indiquant la commande a
 * ex�cuter (par exemple, si la commande entr�e par l'utilisateur est "tuer
 * Dragon Lance", alors les trois chaines sont "prendre" et "Epee" et "Lance").
 * </p>
 * <p>
 *
 * Les mots utilis�s lors de l'initialisation d'une instance de cette classe
 * sont suppos�s venir d'une commande utilisateur dont la validit� a d�j� �t�
 * test�e:
 * <ul>
 * <li>si le mot commande entr� par l'utilisateur ne correspond pas a une
 * commande valide, alors la valeur du mot commande donn� a l'initialisation
 * doit etre null</li>
 * <li>si la commande entr�e par l'utilisateur ne contient pas d'autre mot que
 * le mot commande, alors la valeur du second mot et du troisi�me mot donn� a
 * l'initialisation doit etre null</li>
 * </ul>
 * La validit� du second mot n'est pas test�e, sa valeur peut etre quelconque.
 * La validit� du troisi�me mot n'est pas test�e, sa valeur peut etre
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
	 * Initialise une Commande a partir des deux mots sp�cifi�s.
	 * <p>
	 *
	 * Le premier argument repr�sente un mot commande, sa valeur peut etre null si
	 * le mot commande ne correspond pas a une commande valide. Le second mot peut
	 * �galement etre null dans le cas ou l'utilisateur n'aurait pas fourni de
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
	 * Initialise une Commande a partir des trois mots sp�cifi�s.
	 * <p>
	 *
	 * Le premier argument repr�sente un mot commande, sa valeur peut etre null si
	 * le mot commande ne correspond pas a une commande valide. Le second mot peut
	 * �galement etre null dans le cas ou l'utilisateur n'aurait pas fourni de
	 * second mot dans sa commande. Le troisi�me mot doit obligatoirement �tre un
	 * entier pas une chaine de caract�re quelconque peut �tre �galement null dans
	 * le cas ou l'utilisateur n'aurait pas fourni de troisi�me mot dans sa
	 * commande./p>
	 *
	 * @param motCommande  Le mot commande de la commande utilisateur ou null
	 * @param secondMot    Le second mot de la commande utilisateur ou null
	 * @param TroisiemeMot Le troisi�me mot qui doit �tre un entier de la commande
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
	 * n'est pas une commande valide, la valeur renvoy�e est null.
	 *
	 * @return Le mot commande de cette Commande ou null.
	 */
	// @ pure
	public String getMotCommande() {
		return motCommande;
	}

	/**
	 * Renvoie le second mot de cette Commande ou null si cette commande ne poss�de
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
	 * poss�de pas de troisieme mot.
	 *
	 * @return le troisieme mot de cette Commande ou null.
	 */
	// @ pure
	public String getTroisiemeMot() {
		return troisiemeMot;
	}

	/**
	 * Renvoie le troisi�me mot de cette Commande convertie en int
	 *
	 * @requires troisiemeMot != null;
	 * 
	 * @return la valeur en int du troisi�me mot de cette Commande
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
	 * Teste si cette commande poss�de un second mot.
	 *
	 * @return true si cette commande poss�de un second mot ; false sinon.
	 */
	// @ pure
	public boolean aSecondMot() {
		return (secondMot != null);
	}

	/**
	 * Teste si cette commande poss�de un troisi�me mot.
	 *
	 * @return true si cette commande poss�de un troisi�me mot ; false sinon.
	 */
	// @ pure
	public boolean aTroisiemeMot() {
		return (troisiemeMot != null);
	}
}
