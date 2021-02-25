import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Cette classe fait partie du logiciel Zork, un jeu d'aventure simple en mode
 * texte.
 * <p>
 *
 * Cet analyseur syntaxique lit les entr�es (au terminal) utilisateur et tente
 * de les interpr�ter comme des commande du jeu "Zork". Chaque appel a la
 * m�thode getCommande() lit une ligne au terminal et tente de l'interpr�ter
 * comme constituant une commande compos�e de deux mots. La commande est alors
 * renvoy�e sous forme d'une instance de la classe Commande.
 * </p>
 * <p>
 *
 * Cet analyseur contient un r�pertoire de toutes les commandes reconnues par le
 * jeu. Il compare les entr�es de l'utilisateur au commandes r�pertori�es et si
 * la commande utilisateur n'est pas reconnue il renvoie un objet Commande
 * marqu� comme �tant une commande inconnue.
 * </p>
 *
 * @author Michael Kolling
 * @author Marc Champesme (pour la traduction francaise)
 * @version 1.0
 * @since July 1999
 * @author Mohand Lounis BENSEKHRI 11710457 (modifications).
 */

public class AnalyseurSyntaxique {

	// r�pertorie toutes les commandes reconnues
	// @ pure
	private MotCleCommande commandes;

	/**
	 * Initialise un nouvel analyseur syntaxique
	 */
	// @ pure
	public AnalyseurSyntaxique() {
		commandes = new MotCleCommande();
	}

	/**
	 * Lit une ligne au terminal et tente de l'interpr�ter comme constituant une
	 * commande compos�e de deux mots. La commande est alors renvoy�e sous forme
	 * d'une instance de la classe Commande.
	 *
	 * @return La commande utilisateur sous la forme d'un objet Commande
	 */
	// @ pure
	public Commande getCommande() {
		// pour m�moriser la ligne entr�e par l'utilisateur
		String ligneEntree = "";
		String mot1;
		String mot2;
		String mot3;

		// affiche l'invite de commande
		System.out.print("\n> ");

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			ligneEntree = reader.readLine();
		} catch (java.io.IOException exc) {
			System.out.println("Une erreur est survenue pendant la lecture de votre commande: " + exc.getMessage());
		}

		StringTokenizer tokenizer = new StringTokenizer(ligneEntree);

		if (tokenizer.hasMoreTokens()) {
			// r�cup�ration du permier mot (le mot commande)
			mot1 = tokenizer.nextToken();
		} else {
			mot1 = null;
		}
		if (tokenizer.hasMoreTokens()) {
			// r�cup�ration du second mot
			mot2 = tokenizer.nextToken();
		} else {
			mot2 = null;
		}
		if (tokenizer.hasMoreTokens()) {
			// r�cup�ration du troisi�me mot
			mot3 = tokenizer.nextToken();
		} else {
			mot3 = null;
		}

		// note: le reste de la ligne est ignor�.

		// Teste si le permier mot est une commande valide, si ce n'est pas
		// le cas l'objet renvoy� l'indique
		if (commandes.estCommande(mot1)) {
			return new Commande(mot1, mot2, mot3);
		} else {
			return new Commande(null, mot2, mot3);
		}
	}

	/**
	 * Affiche la liste de toutes les commandes reconnues pour le jeu.
	 */
	// @ pure
	public void afficherToutesLesCommandes() {
		commandes.afficherToutesLesCommandes();
	}
}
