import java.util.Scanner;

/**
 * Classe principale du jeu "Zork".
 * <p>
 *
 * Zork est un jeu d'aventure tr�s rudimentaire avec une interface en mode
 * texte: les joueurs peuvent juste se d�placer parmi les diff�rentes pieces. Ce
 * jeu n�cessite vraiment d'etre enrichi pour devenir int�ressant!
 * </p>
 * <p>
 *
 * Pour jouer a ce jeu, cr�er une instance de cette classe et appeler sa m�thode
 * "jouer".
 * </p>
 * <p>
 *
 * Cette classe cr��e et initialise des instances de toutes les autres classes:
 * elle cr�e toutes les pieces, cr�e l'analyseur syntaxique , cr�e les objets
 * dans les diff�rentes pi�ce, cr�e le joueur et d�marre le jeu. Elle se charge
 * aussi d'ex�cuter les commandes que lui renvoie l'analyseur syntaxique.
 * </p>
 * 
 * Un jeu est constitu� de:
 * <ul>
 * <li>Un analyseurSyntaxique qui permet la lecture des entr�e tap� par
 * l'utilisateur.</li>
 * <li>Une pieceCourante qui d�signe la pi�ce ou se trouve le joueur.</li>
 * <li>Une piecePrecedente qui d�sique la pi�ce pr�cedemment visit�e par le
 * joeuur si un joueur n'a pas effectuer son premier deplacement cette valeur
 * est �gale � null.</li>
 * <li>Un joueur qui d�signe le joueur qui ex�cutera toutes les commande du
 * jeu.</li>
 * </ul>
 *
 * @invariant analyseurSyntaxique != null;
 * @invariant getPieceCourante() != null;
 * @invariant getJoueur() != null;
 *
 * @author Michael Kolling
 * @author Marc Champesme (pour la traduction francaise)
 * @version 1.2
 * @since March 2000
 * @author Mohand Lounis BENSEKHRI 11710457 (modifications).
 */

public class Jeu {
	private AnalyseurSyntaxique analyseurSyntaxique;
	private Piece pieceCourante;
	private Piece piecePrecedente;
	private Joueur joueur;

	/**
	 * Cr�e le jeu et initialise la carte du jeu (i.e. les pi�ces).
	 */
	// @ pure
	public Jeu() {
		creerPieces();
		analyseurSyntaxique = new AnalyseurSyntaxique();
	}

	/**
	 * Renvoie la pi�ce courante. qui est aussi celle o� se trouve le joueur.
	 *
	 * @return la pi�ce courante l� ou se tourve le joueur.
	 */
	// @ pure
	public Piece getPieceCourante() {
		return pieceCourante;
	}

	/**
	 * Renvoie l'avant derni�re pi�ce visit�.
	 *
	 * @return La piece ou le joueur �t� juste avant la pi�ce courante
	 */
	// @ pure
	public Piece getPiecePrecedente() {
		return piecePrecedente;
	}

	/**
	 * Renvoie le joueur joueur.
	 *
	 * @return le joueur.
	 */
	// @ pure
	public Joueur getJoueur() {
		return joueur;
	}

	/**
	 * Cr�e toutes les pieces et relie leurs sorties les unes aux autres. Cr�e tous
	 * les objets et les ajoutent � leurs pi�ces sp�cifies. Cr�e le joueur dans la
	 * pi�ce courante. Le jeu commence dans le chateau.
	 */
	// @ pure
	public void creerPieces() {
		// cr�ation des pieces
		Piece chateau = new Piece("Le Chateau");
		Piece foretPerdu = new Piece("La For�t Perdu");
		Piece grotte = new Piece("La Grotte");
		Piece maisonAbandonnee = new Piece("La Maison Abandonn�e");
		Piece nidDuDragon = new Piece("Le Nid Du Dragon");
		Piece donjon = new Piece("Le Donjon");

		// cr�ation des objets
		// Les ennemis
		ObjetZork dragon = new ObjetZork("Dragon", 1100, false);
		ObjetZork sorciere = new ObjetZork("Sorciere", 100, false);
		ObjetZork gollum = new ObjetZork("Gollum", 63, false);
		// Les outils
		ObjetZork epee = new ObjetZork("Epee", 65, true);
		ObjetZork argent = new ObjetZork("Argent", 1, true);
		ObjetZork cle = new ObjetZork("cle", 1, true);
		ObjetZork lance = new ObjetZork("Lance", 1200, true);
		ObjetZork cheval = new ObjetZork("Cheval", 7, true);
		ObjetZork sorcier = new ObjetZork("Sorcier", 110, true);
		ObjetZork fruitMagique = new ObjetZork("Fruit_Magique", 2, true);
		// A sauver
		ObjetZork princesse = new ObjetZork("La_princesse_Eva", 45, true);
		// Decorations
		ObjetZork chaines = new ObjetZork("Chaines");
		ObjetZork oeufs = new ObjetZork("Oeufs");
		ObjetZork flammes = new ObjetZork("Flammes");
		ObjetZork chaudron = new ObjetZork("Chaudron");
		ObjetZork patteDepoulet = new ObjetZork("Patte_de_Poulet");
		ObjetZork restes_de_nourriture = new ObjetZork("Restes_de_Nourriture");
		ObjetZork eau = new ObjetZork("Eau");
		ObjetZork lit = new ObjetZork("lit");
		ObjetZork fenetre = new ObjetZork("Fen�tre");
		ObjetZork miroir = new ObjetZork("Miroir");

		// initialise les sorties des pieces
		chateau.setSorties(maisonAbandonnee, null, null, grotte);
		foretPerdu.setSorties(null, grotte, null, null);
		grotte.setSorties(null, chateau, null, foretPerdu);
		maisonAbandonnee.setSorties(nidDuDragon, null, chateau, null);
		nidDuDragon.setSorties(donjon, null, maisonAbandonnee, null);
		donjon.setSorties(null, null, nidDuDragon, null);

		// On pose les objets dans leurs endroit destin�e.
		chateau.ajouter(epee);
		chateau.ajouter(argent);
		foretPerdu.ajouter(fruitMagique);
		chateau.ajouter(cheval);
		foretPerdu.ajouter(cle);
		nidDuDragon.ajouter(dragon);
		grotte.ajouter(gollum);
		maisonAbandonnee.ajouter(sorciere);
		donjon.ajouter(princesse);
		chateau.ajouter(lance);
		grotte.ajouter(sorcier);
		// On pose les d�corations
		nidDuDragon.ajouter(chaines);
		nidDuDragon.ajouter(oeufs);
		nidDuDragon.ajouter(flammes);
		maisonAbandonnee.ajouter(chaudron);
		maisonAbandonnee.ajouter(patteDepoulet);
		grotte.ajouter(eau);
		grotte.ajouter(restes_de_nourriture);
		donjon.ajouter(lit);
		donjon.ajouter(fenetre);
		donjon.ajouter(miroir);

		// le jeu commence dans le chateau
		pieceCourante = chateau;
		piecePrecedente = null;

		// cr�ation du joueur
		joueur = new Joueur("L�on", pieceCourante, 120);
	}

	/**
	 * Pour lancer le jeu. Boucle jusqu'a la fin du jeu.
	 */
	// @ pure
	public void jouer() {
		afficherMsgBienvennue();

		// Entr�e dans la boucle principale du jeu. Cette boucle lit
		// et ex�cute les commandes entr�es par l'utilisateur, jusqu'a
		// ce que la commande choisie soit la commande "quitter"
		boolean termine = false;
		while (!termine) {
			Commande commande = analyseurSyntaxique.getCommande();
			termine = traiterCommande(commande);
		}
		System.out.println("*                     Merci d'avoir jouer.  Au revoir.                    * ");
		System.out.println("***************************************************************************\n");
	}

	/**
	 * Affiche le message d'accueil pour l'utilisateur.
	 */
	// @ pure
	public void afficherMsgBienvennue() {
		System.out.println();
		System.out.println("********************************** MENU ***********************************");
		System.out.println("*                        L'AMOUR AU BOUT DU FIL                           *");
		System.out.println("*                    Bienvenue dans le monde de Zork !                    *");
		System.out.println("*                   Zork est un nouveau jeu d'aventure.                   *");
		System.out.println("*          Tapez 'histoire' si vous voulez lire l'hisoire du jeu.         *");
		System.out.println("*                Tapez 'plan' pour voir le plan du jeu.                   *");
		System.out.println("*               Tapez 'aide' si vous avez besoin d'aide.                  *");
		System.out.println("*                               BON JEU ;)                                *");
		System.out.println("***************************************************************************");

		System.out.println();
		System.out.println(pieceCourante.descriptionLongue());
	}

	/**
	 * Ex�cute la commande sp�cifi�e. Si cette commande termine le jeu, la valeur
	 * true est renvoy�e, sinon false est renvoy�e
	 *
	 * @param commande La commande a ex�cuter
	 * @return true si cette commande termine le jeu ; false sinon.
	 */
	// @ pure
	public boolean traiterCommande(Commande commande) {
		int test = 0;
		if (commande.estInconnue()) {
			System.out.println("Je ne comprends pas ce que vous voulez...");
			return false;
		}

		String motCommande = commande.getMotCommande();
		if (motCommande.equals("aide")) {
			afficherAide();
		} else if (motCommande.equals("aller")) {
			deplacerVersAutrePiece(commande);
		} else if (motCommande.equals("retour")) {
			if (commande.aSecondMot()) {
				System.out.println("retour quoi ?");
			} else
				retour(commande);
		} else if (motCommande.equals("quitter")) {
			if (commande.aSecondMot()) {
				System.out.println("Quitter quoi ?");
			} else {
				System.out.println("\n***************************************************************************");
				test = 1;
			}
		} else if (motCommande.equals("piece")) {
			if (commande.aSecondMot()) {
				System.out.println("Veillez � ne mettre que le mot de commande.");
			} else {
				this.getPieceCourante().afficherPiece();
			}
		} else if (motCommande.equals("sac")) {
			if (commande.aSecondMot()) {
				System.out.println("Veillez � ne mettre que le mot de commande.");
			} else {
				this.getJoueur().afficherLesObjets();
			}
		} else if (motCommande.equals("plan")) {
			this.afficherPlanJeu();
		} else if (motCommande.equals("prendre")) {
			if (prendre(commande))
				test = 1;
		} else if (motCommande.equals("poser")) {
			if (poser(commande))
				test = 1;
		} else if (motCommande.equals("ouSuisJe")) {
			System.out.println(this.getJoueur().getPiece().descriptionLongue());
		} else if (motCommande.equals("tuer")) {
			if (tuer(commande))
				test = 1;
		} else if (motCommande.equals("histoire")) {
			afficherHistoire();
		}
		if (test == 1)
			return true;
		return false;
	}

	// implementations des commandes utilisateur:

	/**
	 * Affichage de l'aide. Affiche notament la liste des commandes utilisables.
	 */
	// @ pure
	public void afficherAide() {
		System.out.println("********************************** AIDE ***********************************");
		System.out.println("*      Le but du jeu est de sauver la princesse Eva emprisonn�e           *");
		System.out.println("*      dans le donjon et de la ramener au chateau, pour cela il           *");
		System.out.println("*      faut tuer le Dragon qui est dans le nid du dragon. Vous            *");
		System.out.println("*      pouvez le tuer que avec une lance, pour r�cup�rer la               *");
		System.out.println("*      lance il faut manger le fruit magique qui augmente votre force.    *");
		System.out.println("*                                                                         *");
		System.out.println("*    -> Pour Bien comprendre ce que vous faites dans le jeu:              *");
		System.out.println("* 1)- Affichez la pi�ce courante apr�s chaque d�placement.                *");
		System.out.println("* 2)- Affichez apres chaque prise ou depot d'un Objet le sac de L�on.     *");
		System.out.println("* 3)- Affichez le plan du jeu.                                            *");
		System.out.println("*                                                                         *");
		System.out.println("*     Les monstres � tuer sont: Le Dragon, le Gollum et la Sorci�re.      *");
		System.out.println("*                                                                         *");
		System.out.println("*                               INDICATION:                               *");
		System.out.println("*        On peut utiliser qu'une fois une arme pour tuer un monstre !     *");
		System.out.println("*              Un Sorcier (si corrompu) peut tuer des ennemis  !          *");
		System.out.println("*                     Le Cheval ne sert PAS a rien  !                     *");
		System.out.println("***************************************************************************\n");

		analyseurSyntaxique.afficherToutesLesCommandes();
	}

	/**
	 * Affiche l'hisoire que j'ai invent� pour le jeu.
	 */
	// @ pure
	public void afficherHistoire() {
		System.out.println("******************************** HISTOIRE *********************************");
		System.out.println("*                                                                         *");
		System.out.println("* Dans un pays lointain, L�on, un chevalier fort et courageux             *");
		System.out.println("* a toujours �t� fou amoureux d�une princesse de son pays du nom d�Eva.   *");
		System.out.println("* Malheureusement, il y a quelques temps, L�on a apprit                   *");
		System.out.println("* une terrible nouvelle : Eva avait �t� enlever au sommet d'un donjon.    *");
		System.out.println("* L�on, tr�s confiant n�h�sita pas une seule seconde pour aller sauver    *");
		System.out.println("* sa bien-aim�e. Mais, L�on n��tait pas encore pr�t. Effectivement,       *");
		System.out.println("* plusieurs obstacles l�attendait et il repartit avec une lourde d�faite. *");
		System.out.println("* Heureusement, L�on ne s�est pas laiss� d�courager il s�est renseign�    *");
		System.out.println("* sur toutes les formes de puissances qu�il pourrait acqu�rir comme       *");
		System.out.println("* par exemple un fruit magique qui lui permettrait de multipli�           *");
		System.out.println("* sa force par dix. Il s�est entra�n� nuit et jour et a battu tout les    *");
		System.out.println("* monstres du pays, plus rien ne pouvait arr�ter L�on. Le chevalier est   *");
		System.out.println("* fin pr�t pour le grand jour, celui ou il pourrait porter dans ses bras  *");
		System.out.println("* celle qu�il a toujours aim� : la belle Eva. L�on se trouve maintenant   *");
		System.out.println("* au pied de l�entr�e du chateau, plus d�termin� que jamais.              *");
		System.out.println("***************************************************************************");
	}

	/**
	 * Affiche le plan des pi�ces du Jeu et leurs sorties.
	 */
	// @ pure
	public void afficherPlanJeu() {
		System.out.println("           ______________________PLAN DU JEU______________________");
		System.out.println("          |                                                       |");
		System.out.println("          |                                     ***********       |");
		System.out.println("          |                                     *         *       |");
		System.out.println("          |              N                      * DONJON  *       |");
		System.out.println("          |              *                      *         *       |");
		System.out.println("          |              *                      ***********       |");
		System.out.println("          |        W * * * * * E                    ***           |");
		System.out.println("          |              *                           *            |");
		System.out.println("          |              *                          ***           |");
		System.out.println("          |              S                      ***********       |");
		System.out.println("          |                                     *   NID   *       |");
		System.out.println("          |                                     *   DU    *       |");
		System.out.println("          |                                     * DRAGON  *       |");
		System.out.println("          |                                     ***********       |");
		System.out.println("          |                                         ***           |");
		System.out.println("          |                                          *            |");
		System.out.println("          |                                         ***           |");
		System.out.println("          |               **                    ***********       |");
		System.out.println("          |        *** ou **  PORTE             * MAISON  *       |");
		System.out.println("          |        ***    **                    *  ABAND- *       |");
		System.out.println("          |                                     *  ONNEE  *       |");
		System.out.println("          |                                     ***********       |");
		System.out.println("          |                                         ***           |");
		System.out.println("          |                                          *            |");
		System.out.println("          |                                         ***           |");
		System.out.println("          |      ***********    ************    ***********       |");
		System.out.println("          |      *  FORET  **  **          **  **         *       |");
		System.out.println("          |      *         ******  GROTTE  ****** CHATEAU *       |");
		System.out.println("          |      *  PERDU  **  **          **  **         *       |");
		System.out.println("          |      ***********    ************    ***********       |");
		System.out.println("          |_______________________________________________________|");
	}

	/**
	 * Tente d'aller dans la direction sp�cifi�e par la commande. Si la piece
	 * courante poss�de une sortie dans cette direction, la piece correspondant a
	 * cette sortie devient la piece courante, dans les autres cas affiche un
	 * message d'erreur. C'est le joueur qui se d�place.
	 *
	 * <p>
	 * -> Quelques pr�f�rences du jeu:
	 * </p>
	 * <ul>
	 * <li>Si le joueur est dans une salle ou y a un monstre il ne peut pas aller
	 * plus loin sauf si ce monstre disparet de la piece et cela est possible gr�ce
	 * � la commande tuer.</li>
	 * <li>Si le joueur est dans la pi�ce leNidDuDragon et qu'il n'a pas l'objet cle
	 * dans les objets qu'il transporte il ne pourra pas aller plus loin.</li>
	 * </ul>
	 * 
	 * <p>
	 * Apr�s Chaque deplacement on proc�de � une sauvegarde de la pi�ce pr�c�dement
	 * visit� et cela dans l'attribut piecePrecedente et on change la pi�ce courante
	 * du joueur.
	 * </p>
	 *
	 * @ensures commande.getMotCommande().equals("aller");
	 * @ensures this.getPieceCourante() =
	 *          this.getPieceCourante().pieceSuivante(Direction);
	 *
	 * @param commande Commande dont le second mot sp�cifie la direction a suivre
	 */
	public void deplacerVersAutrePiece(Commande commande) {
		if (!commande.aSecondMot()) {
			// si la commande ne contient pas de second mot, nous ne
			// savons pas ou aller..
			if (commande.getMotCommande().equals("aller"))
				System.out.println("Aller o� ?");
			return;
		}

		String direction = commande.getSecondMot();
		Direction d = null;
		try {
			d = Direction.valueOf(direction);
		} catch (IllegalArgumentException e) {
			System.out.println("Pour indiquer une direction entrez une des cha�nes de caract�res suivantes:");
			for (Direction dok : Direction.values()) {
				System.out.println("---> \"" + dok + "\"");
			}
			return;
		}

		if (pieceCourante.descriptionCourte().equals("Le Nid Du Dragon")) {
			// Initialisation des objets de la condition qui sont: le dragon, et la cle
			ObjetZork dragon = new ObjetZork("Dragon", 1100, false);
			ObjetZork cle = new ObjetZork("cle", 1, true);
			// Tentative d'aller dans la direction indiqu�e.
			if (commande.getSecondMot().equals("NORD")) {
				if (pieceCourante.contient(dragon)) {
					System.out.println("Vous devez d'abord tuer le Dragon pour aller au NORD");
					return;
				}
				if (!joueur.contient(cle)) {
					System.out.println("Vous devez avoir une cle pour ouvrir la porte du donjon.");
					return;
				}
				if (!pieceCourante.contient(dragon) && joueur.contient(cle)) {
					// Sauvegarde de la pi�ce courante.
					piecePrecedente = pieceCourante;
					Piece pieceSuivante = pieceCourante.pieceSuivante(d);
					joueur.setPiece(pieceSuivante);
				}
			}
		}

		if (pieceCourante.descriptionCourte().equals("La Maison Abandonn�e")) {
			// Initialisation de l'objet de la condition qui est la sorciere
			ObjetZork sorciere = new ObjetZork("Sorciere", 100, false);
			// Tentative d'aller dans la direction indiqu�e.
			if (commande.getSecondMot().equals("NORD")) {
				if (pieceCourante.contient(sorciere)) {
					System.out.println("Vous devez d'abord tuer la Sorciere pour aller au NORD");
					return;
				} else {
					// Sauvegrade de la pi�ce courante.
					piecePrecedente = pieceCourante;
					Piece pieceSuivante = pieceCourante.pieceSuivante(d);
					joueur.setPiece(pieceSuivante);
				}
			}
		}

		if (pieceCourante.descriptionCourte().equals("La Grotte")) {
			// Initialisation de l'objet de la condition qui est le Gollum
			ObjetZork gollum = new ObjetZork("Gollum", 63, false);

			// Tentative d'aller dans la direction indiqu�e.
			if (commande.getSecondMot().equals("OUEST")) {
				if (pieceCourante.contient(gollum)) {
					System.out.println("Vous devez d'abord tuer le Gollum pour aller vers l'OUEST");
					return;
				} else {
					// Sauvegrade de la pi�ce courante.
					piecePrecedente = pieceCourante;
					Piece pieceSuivante = pieceCourante.pieceSuivante(d);
					joueur.setPiece(pieceSuivante);
				}
			}
		}

		// Sauvegarde de la pi�ce courante.
		piecePrecedente = pieceCourante;
		// Tentative d'aller dans la direction indiqu�e.
		Piece pieceSuivante = pieceCourante.pieceSuivante(d);

		if (pieceSuivante == null) {
			System.out.println("Il n'y a pas de chemin dans cette direction!");
			return;
		} else {
			pieceCourante = pieceSuivante;
			joueur.setPiece(pieceCourante);
			System.out.println(pieceCourante.descriptionLongue());
		}
	}

	/**
	 * Fait revenir le joueur dans la pi�ce pr�c�dente visit�e. Si l'utilisateur
	 * tente de revenir d� le d�but du jeu un message d'erreur lui indique qu'il n'a
	 * pas chager de pi�ce avant cela.
	 *
	 * @param commande La commande retour qui permet de revenir � la pi�ce
	 *                 pr�c�dente visit�.
	 */
	// @ pure
	public void retour(Commande commande) {
		if (piecePrecedente == null)
			System.out.println("Vous n'avez pas changer de pi�ce depuis le d�but du jeur");
		else {
			joueur.setPiece(piecePrecedente);
			pieceCourante = piecePrecedente;
			System.out.println(pieceCourante.descriptionLongue());
		}
	}

	/**
	 * <p>
	 * Prendre un objet dans une pi�ce Et cela gr�ce une commande entr�e par
	 * l'utilisateur dans le jeu, qui doit obligatoirement contenir deux champs, qui
	 * sont le mot de commande qui est prendre, le secondMot qui est la
	 * descriptionCourte de l'objet � prendre et cela gr�ce � la m�thode
	 * trouverObjDesc qui est dans la super classe ArrayListConteneur qui est h�rit�
	 * par la classe Joueur et la classe Piece.
	 * </p>
	 * 
	 * <ul>
	 * <li>Si le joueur tape que le mot de commande on affiche un message d'erreur
	 * pour dire que l'on sait pas quel est la description de l'objet a
	 * prendre.</li>
	 * <li>Si la description entr� ne correspend pas � une description d'un des
	 * ObjetZork contenu dans la pi�ce un message d'erreur indique ce la description
	 * entr� ne correspend � aucun ObjetZork de la pi�ce.</li>
	 * </ul>
	 * 
	 * <p>
	 * Une fois ces champs bien entr�s et valid�s.
	 * </p>
	 * 
	 * <ul>
	 * Pour prendre l'objet demand� il faut que:
	 * <li>L'objet soit transportable et pr�sent dans la pi�ce courantante.</li>
	 * <li>Si le poids de l'objet a transport� additionner au poids total des objets
	 * transport� est sup�rieur au poids maximal que le joueur peut transport�
	 * envoyer un message d'erreur indiquant que l'objet a prendre est beaucoup trop
	 * lourd pour le joueur.</li>
	 * </ul>
	 * 
	 * <p>
	 * Quand un joueur prend un objet ce dernier est retirer de la pi�ce.
	 * </p>
	 * 
	 * <ul>
	 * Quelques pr�f�rences du jeu:
	 * <li>Il existe un objet sp�cial qui permet de multipli� le poids maximal qu'un
	 * joueur peut transporter par 10 et c'est la Fruit_Magique: poidsMax*=10.</li>
	 * <li>Quand on prend le fruit magique on le supprime du des Objet transport�s
	 * par le joueur car c'est un objet qui sert juste pour augmenter le poids max
	 * du joueur.</li>
	 * <li>Pour prendre un Sorcier il faut avoir de l'argent si on n'en a pas on ne
	 * peut pas le prendre.</li>
	 * <li>Quand on prend le sorcier en ayant l'argent au d�but dans les Objets
	 * transport�s apres la prise l'argent disparait des Objets transport� par le
	 * joueur.</li>
	 * </ul>
	 * 
	 * <ul>
	 * Cas Fin du jeu:
	 * <li>PERDU Si on transporte une princesse sans avoir un cheval dans les Objets
	 * transport�s le jeu prend fin avec une d�faite.</li>
	 * </ul>
	 * 
	 * @ensures commande.getMotCommande().equals("prendre");
	 * 
	 * @param commande la commande permettant d'identifier l'action de prendre un
	 *                 objet et sa description.
	 * @return true si le jeu prend fin, false sinon.
	 * @pure
	 */
	public boolean prendre(Commande commande) {
		if (!commande.aSecondMot()) {
			// si la commande ne contient pas de second mot, nous ne
			// savons pas quoi prendre..
			if (commande.getMotCommande().equals("prendre"))
				System.out.println("Prendre quoi ?");
			return false;
		}

		// Description de l'objet � prendre.
		String descOz = commande.getSecondMot();

		// Recherche de l'objet � prendre dans la pi�ce courante gr�ce � la description
		// entr�e.
		ObjetZork oz = joueur.getPiece().trouverObjDesc(descOz);
		if (oz == null) {
			System.out.println(joueur.getNom() + " ne peut pas prendre " + descOz + " car il n'est pas pr�sent dans "
					+ pieceCourante.descriptionCourte());
			return false;
		} else {
			if (oz.estTransportable()) {
				if (joueur.PoidsMaxSupPoidsPlus(oz)) {

					// Cas prise sp�cial de quelques objets.

					// Cas perdant prendre la princesse sans cheval.
					if (oz.descriptionCourte().equals("La_princesse_Eva")) {
						ObjetZork cheval = new ObjetZork("Cheval", 7, true);
						if (!joueur.contient(cheval)) {
							System.out.println(
									"\n********************************PERDU !************************************");
							System.out.println(
									"*         Vous ne pouvez pas transporter une princesse sans cheval !      *");
							return true;
						}
					}

					// Pour prendre un Sorcier, il faut avoir dans le sac l'objet Argent.
					ObjetZork sorcier = new ObjetZork("Sorcier", 110, true);
					if (sorcier.equals(oz)) {
						ObjetZork argent = new ObjetZork("Argent", 1, true);
						if (!joueur.contient(argent)) {
							System.out.println("Vous n'avez pas de quoi payer le sorcier pour le prendre !");
							return false;
						} else {
							System.out.println("Vous venez de payer le sorcier, il se battra � vos cot�s d�sormais !");
							joueur.retirer(argent);
							joueur.ajouter(sorcier);
							pieceCourante.retirer(sorcier);
							return false;
						}
					}

					// L'objet Fruit_magique multiplie par 10 le poids max transportable du joueur
					// quand on le prend et on le retire du sac.
					ObjetZork fruit_magique = new ObjetZork("Fruit_Magique", 2, true);
					if (fruit_magique.equals(oz)) {
						int newPoidsMax = joueur.getPoidsMax() * 10;
						joueur.setPoidsMax(newPoidsMax);
						joueur.ajouter(oz);
						System.out.println(joueur.getNom()
								+ " mange le fruit magique !\nSon poids maximal transportable est d�sormais de: "
								+ joueur.getPoidsMax() + " kg.");
						pieceCourante.retirer(oz);
						joueur.retirer(oz);
						return false;
					}

					// Cas prise des objets standards
					joueur.ajouter(oz);
					pieceCourante.retirer(oz);
					return false;

				} else {
					System.out.println(oz.descriptionCourte() + " est trop lourd pour qu'il soit transport� par "
							+ joueur.getNom());
					return false;
				}
			} else {
				System.out.println(oz.descriptionCourte() + " n'est pas transportable");
				return false;
			}
		}
	}

	/**
	 * <p>
	 * Poser un objet des Objets transport�s par le joueur et le mettre dans la
	 * pi�ce courante. Et cela gr�ce une commande entr�e par l'utilisateur dans le
	 * jeu, qui doit obligatoirement contenir deux champs, qui sont le mot de
	 * commande qui est poser, le secondMot qui est la descriptionCourte de l'objet
	 * � poser et cela gr�ce � la m�thode trouverObjDesc qui est dans la super
	 * classe ArrayListConteneur qui est h�rit� par la classe Joueur et la classe
	 * Piece.
	 * </p>
	 * 
	 * <p>
	 * Si le joueur tape que le mot de commande on affiche un message d'erreur pour
	 * dire que l'on sait pas quel est la description de l'objet a poser.
	 * </p>
	 * 
	 * <ul>
	 * Une fois ces champs bien entr�s et valid�s. Pour poser un objet du sac il
	 * faut que:
	 * <li>Si l'objet n'est pas dans le Objets transport� par le joueur envoyer un
	 * message d'erreur indiquant que l'objet n'est pas pris par le joueur.</li>
	 * <li>Quand un joueur lache un objet ce dernier est mis dans la pi�ce o� se
	 * trouve le joueur.</li>
	 * </ul>
	 *
	 * <ul>
	 * Cas Fin du jeu:
	 * <li>GAGNER Si on ram�ne la princesse jusqu'au chateau et on la pose au
	 * chateau le jeu prend fin et c'est gagner.</li>
	 * <li>PERDU Si on lache le Sorcier le jeu prend fin et c'est perdu.</li>
	 * <li>PERDU Si on lache le cheval est la princesse est dans le sac le jeu prend
	 * fin et c'est perdu.</li>
	 * </ul>
	 * 
	 * @ensures commande.getMotCommande().equals("poser");
	 *
	 * @param commande la commande permettant d'identifier l'action de poser un
	 *                 objet et la description de ce dernier.
	 * @return true si le jeu prend fin, false sinon.
	 * @pure
	 */
	public boolean poser(Commande commande) {
		if (!commande.aSecondMot()) {
			// si la commande ne contient pas de second mot, nous ne
			// savons pas quoi poser..
			if (commande.getMotCommande().equals("poser"))
				System.out.println("poser quoi ?");
			return false;
		}

		// Description de l'objet � prendre.
		String descOz = commande.getSecondMot();

		// Recherche de l'objet � prendre dans la pi�ce courante gr�ce � la description
		// entr�e.
		ObjetZork oz = joueur.trouverObjDesc(descOz);

		if (!(joueur.contient(oz))) {
			System.out.println(
					joueur.getNom() + " ne peut pas poser " + descOz + " car il n'est pas pr�sent dans son sac");
			return false;
		} else {
			// D�pot des objets standars.
			pieceCourante.ajouter(oz);
			joueur.retirer(oz);
		}

		// Cas gagnant depot de la pricesse dans le chateau.
		if (joueur.getPiece().descriptionCourte().equals("Le Chateau")) {
			ObjetZork princesse = new ObjetZork("La_princesse_Eva", 45, true);
			if (joueur.getPiece().contient(princesse)) {
				System.out.println("\n**********************************GAGNER***********************************");
				System.out.println("*               Bien jou�, vous avez sauver la princesse Eva.             *");
				System.out.println("*                              F�licitation !                             *");
				System.out.println("*                                                                         *");
				return true;
			}
		}

		// Cas perdant depot du Sorcier, car on ne peut plus le payer, il n'y plus
		// d'argent dans le chateau.
		ObjetZork sorcier = new ObjetZork("Sorcier", 110, true);
		if (oz.equals(sorcier)) {
			System.out.println("\n********************************PERDU !************************************");
			System.out.println("*        Vous ne pourrez pas prendre le Sorcier une autres fois !         *");
			System.out.println("*                  Vous ne pourrez pas tuer le Dragon !                   *");
			System.out.println("*                                                                         *");
			return true;
		}

		// Cas perdant depot du cheval en ayant la princesse dans le sac.
		ObjetZork cheval = new ObjetZork("Cheval", 7, true);
		if (oz.equalsDescription(cheval)) {
			ObjetZork princesse = new ObjetZork("La_princesse_Eva", 45, true);
			if (joueur.contient(princesse)) {
				System.out.println("\n********************************PERDU !************************************");
				System.out.println("*         Vous ne pouvez pas transporter une princesse sans cheval !      *");
				System.out.println("*                                                                         *");
				return true;
			}
		}
		return false;
	}

	/**
	 * <p>
	 * Tuer un objet dans une pi�ce gr�ce � un autre qui dans les objets transport�s
	 * par le joueur. Et cela gr�ce une commande entr�e par l'utilisateur dans le
	 * jeu, qui doit obligatoirement contenir trois champs, qui sont le mot de
	 * commande qui est tuer, le secondMot qui est la descriptionCourte de l'objet a
	 * tuer, le troisiemeMot descriptionCourte de l'objet avec lequel on tura le
	 * premier objet choisi les Objets sont trouv� grace � une description qui est
	 * utilis� par la m�thode trouverObjDesc qui permet de r�cup�rer soit l'objet
	 * qui a comme description ce qui est sp�cifi� et cela depuis les Objets qui
	 * sont dans une pi�ce ou transport� par le joueur.
	 * </p>
	 * 
	 * <ul>
	 * <li>Si le joueur entre que le mot de commande on affiche un message d'erreur
	 * pour dire que l'on sait pas quel est la description de l'objet a tuer.</li>
	 * <li>Si le joueur entre que les deux premiers champs on affiche un message
	 * d'erreur pour dire que l'on sait pas quel est la description de l'objet qui
	 * sert d'arme.</li>
	 * </ul>
	 * 
	 * <p>
	 * Une fois ces champs bien entr�s et valid�s.
	 * </p>
	 *
	 * <ul>
	 * Pour tuer un objet il faut que:
	 * <li>Il ait un poids inf�rieur � celui de l'objet utiliser.</li>
	 * <li>On peut utiliser qu'un objet a la fois pour tuer quelque chose.</li>
	 * <li>Il faut que l'objet a tuer soit dans la m�me pi�ce que le joueur.</li>
	 * <li>Il faut que l'objet qui sert d'arme soit pr�sent dans les Objets
	 * transport�s par le joueur.</li>
	 * <li>Il faut que l'objet a tuer soit non transportable Quand on utilise un
	 * objet pour tuer un autre, l'objet utiliser disparait.</li>
	 *
	 * <p>
	 * Pr�f�rences du jeu: Pour tuer le Gollum il faut r�pondre � une question.
	 * <p>
	 *
	 * <p>
	 * Cas Fin du jeu: PERDU Si on essaye de tuer un monstre qui a un poids
	 * sup�rieur � l'arme utilis�.
	 * </p>
	 * 
	 * @ensures commande.getMotCommande().equals("tuer");
	 * 
	 * @return true si le jeu prend fin, false sinon.
	 * @pure
	 */
	public boolean tuer(Commande commande) {
		if (!commande.aSecondMot()) {
			// si la commande ne contient pas de second mot, nous ne
			// savons pas quoi tuer..
			if (commande.getMotCommande().equals("tuer"))
				System.out.println("Tuer qui ?");
			return false;
		}

		if (!commande.aTroisiemeMot()) {
			// si la commande ne contient pas de quatri�me mot, nous ne
			// savons pas avec quoi tuer..
			if (commande.getMotCommande().equals("tuer"))
				if (commande.aSecondMot())
					System.out.println("Avec quoi ?");
			return false;
		}

		// Initialisation de l'objet a tuer.
		String descMonstre = commande.getSecondMot();
		ObjetZork monstre = joueur.getPiece().trouverObjDesc(descMonstre);

		if (!joueur.getPiece().contient(monstre)) {
			System.out.println(descMonstre + " n'est pas pr�sent dans " + pieceCourante.descriptionCourte());
			return false;
		}
		// Initialisation de l'objet a utiliser pour tuer.
		String descArme = commande.getTroisiemeMot();
		ObjetZork arme = joueur.trouverObjDesc(descArme);

		if (!joueur.contient(arme)) {
			System.out.println(descArme + " n'est pas dans le sac de " + joueur.getNom());
			return false;
		}
		if (monstre.descriptionCourte().equals("Dragon") || monstre.descriptionCourte().equals("Gollum")
				|| monstre.descriptionCourte().equals("Sorciere")) {
			if (arme.getPoids() < monstre.getPoids()) {
				System.out.println(arme.descriptionCourte() + " ne peux pas tuer " + monstre.descriptionCourte()
						+ " car " + monstre.descriptionCourte() + " est plus fort que " + arme.descriptionCourte());
				System.out.println("\n********************************PERDU !************************************");
				System.out.println("*        Le monstre vous a tuer car il est plus fort que vous !           *");
				System.out.println("*                                                                         *");
				return true;
			}

		} else {
			System.out.println(joueur.getNom() + " ne peut pas tuer " + descMonstre + " car ce n'est pas un ennemi");
			return false;
		}

		// Objet spcial � tuer, le Gollum; il faut r�pondre � une �nigme.
		ObjetZork gollum = new ObjetZork("Gollum", 63, false);
		if (monstre.equalsDescription(gollum)) {
			String reponse = "Une montagne";
			System.out.println("Vous devez trouver l'�nigme du Gollum pour le tuer.");
			System.out.println("L'ENIGME:");
			System.out.println("         Qu'est-ce qui a des racines que personne ne voit, ");
			System.out.println("         Qui est plus grande que les arbres, ");
			System.out.println("         Qui monte, qui monte, ");
			System.out.println("         Et pourtant ne pousse jamais ?\n");
			System.out.println("NB: la r�ponse � deux mot; le premier mot est: Une");
			Scanner sc = new Scanner(System.in);
			String saisie = sc.nextLine();
			if (reponse.equals(saisie)) {
				System.out.println("Bien jouer vous avez trouver la r�ponse, et tuer le Gollum.");
				joueur.retirer(arme);
				joueur.getPiece().retirer(monstre);
				return false;
			} else {
				System.out.println("Mauvaise r�ponse, r�essayez ! Le Gollum est toujours vivant !");
				return false;
			}
		}

		joueur.retirer(arme);
		joueur.getPiece().retirer(monstre);
		return false;
	}
}
