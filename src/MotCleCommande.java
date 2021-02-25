/**
 * <p>
 *
 * Cette classe fait partie du logiciel Zork, un jeu d'aventure simple en mode
 * texte.
 * </p>
 * <p>
 *
 * Classe répertoriant l'ensemble des mots-clé utilisables comme commande dans
 * le jeu. Elle est utilisée pour vérifier la validité des commandes de
 * l'utilisateur.
 *
 * @author Michael Kolling
 * @author Marc Champesme (pour la traduction francaise)
 * @version 1.0
 * @since July 1999
 * @author Mohand Lounis BENSEKHRI 11710457 (modifications).
 */

public class MotCleCommande {
	/**
	 * Un tableau constant contenant tous les mots-clé valides comme commandes.
	 */
	// @ pure
	private final static String commandesValides[] = { "aller", "retour", "prendre", "poser", "tuer", "sac", "piece",
			"ouSuisJe", "aide", "plan", "histoire", "quitter" };

	/**
	 * Initialise la liste des mots-clé utilisables comme commande.
	 */
	// @ pure
	public MotCleCommande() {
	}

	/**
	 * Teste si la chaine de caractères spécifiée est un mot-clé de commande valide.
	 * Check whether a given String is a valid command word. Return true if it is,
	 * false if it isn't.
	 *
	 * @param aString Chaine de caractères a tester
	 * @return true si le paramètre est une commande valide, false sinon
	 */
	// @ pure
	public boolean estCommande(String aString) {
		for (int i = 0; i < commandesValides.length; i++) {
			if (commandesValides[i].equals(aString)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Affiche toutes les commandes (i.e. les mots-clé) valides.
	 */
	// @ pure
	public void afficherToutesLesCommandes() {
		System.out.println("             ************************************************");
		System.out.println("             *      LES COMMANDES RECONNUES POUR JOUER      *");
		System.out.println("             ************************************************");
		System.out.println("             * Commandes Déplacements  **      Syntaxe      *");
		System.out.println("             ************************************************");
		System.out.println("             *          aller          ** Co. Direction     *");
		System.out.println("             *         retour          ** Co.               *");
		System.out.println("             ************************************************");
		System.out.println("             *    Commandes Actions    **      Syntaxe      *");
		System.out.println("             ************************************************");
		System.out.println("             *        prendre          ** Co. Oz            *");
		System.out.println("             *         poser           ** Co. Oz            *");
		System.out.println("             *          tuer           ** Co. Monstre Arme  *");
		System.out.println("             *         piece           ** Co.               *");
		System.out.println("             *          sac            ** Co.               *");
		System.out.println("             ************************************************");
		System.out.println("             * Commandes Aide & System **      Syntaxe      *");
		System.out.println("             ************************************************");
		System.out.println("             *        ouSuisJe         ** Co.               *");
		System.out.println("             *          aide           ** Co.               *");
		System.out.println("             *          plan           ** Co.               *");
		System.out.println("             *        histoire         ** Co.               *");
		System.out.println("             *         quitter         ** Co.               *");
		System.out.println("             ************************************************");
	}
}
