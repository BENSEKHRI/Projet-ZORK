/**
 * Un objet dans un jeu d'aventure.
 * <p>
 *
 * Cette classe fait partie du logiciel Zork, un jeu d'aventure simple en mode
 * texte. Dans le logiciel Zork un objet peut se trouver dans une pi�ce ou etre
 * transport� par un joueur s'il est transportable.
 * </p>
 *
 * <p>
 * Un ObjetZork est compl�tement caract�ris� par une description, un poids et le
 * fait qu'il soit ou non transportable. Tous les ObjetZork (transportables ou
 * non) poss�dent un poids sup�rieur ou �gal � z�ro, mais les ObjetZork
 * transportables doievnt avoir un poids strictement positif. Les ObjetZork sont
 * non modifiables (immutable): leurs prori�t�s ne peuvent pas �tre modifi�es
 * apr�s cr�ation de l'instance.
 * </p>
 *
 * @invariant descriptionCourte() != null;
 * @invariant descriptionLongue() != null;
 * @invariant getPoids() >= 0;
 * @invariant estTransportable() ==> (getPoids() > 0);
 *
 * @author Mohand Lounis BENSEKHRI 11710457.
 */
public class ObjetZork implements Cloneable {
	private String description;
	private int poids;
	private boolean transportable;

	/**
	 * Initialise un objet non transportable identifi� par la chaine de caract�res
	 * sp�cifi�e et de poids nul. La chaine de caract�res sp�cifi�e doit �tre
	 * diff�rente de null.
	 *
	 * @requires description != null;
	 * @ensures descriptionCourte() == description;
	 * @ensures getPoids() == 0;
	 * @ensures !estTransportable();
	 *
	 * @param description La chaine de caract�res qui identifie cet objet.
	 * @throws NullPointerException si la cha�ne de caract�res sp�cifi�e est null.
	 */
	public ObjetZork(String description) {
		if (description == null) {
			throw new NullPointerException("La description doit �tre non null");
		}
		this.description = description;
	}

	/**
	 * Initialise un ObjetZork identifi� par la chaine de caract�res sp�cifi�e et
	 * dont le poids est l'entier sp�cifi�. L'objet est transportable si le
	 * param�tre estTransportable a la valeur true. La chaine de caract�res
	 * sp�cifi�e doit �tre diff�rente de null et le poids doit etre sup�rieur ou
	 * �gal a 0. Si un objet est transportable son poids est obligatoirement
	 * strictement sup�rieur � 0.
	 *
	 * @requires description != null;
	 * @requires poids >= 0;
	 * @requires estTransportable ==> (poids != 0);
	 * @ensures descriptionCourte() == description;
	 * @ensures getPoids() == poids;
	 * @ensures estTransportable() == estTransportable;
	 *
	 * @param description      La chaine de caract�res qui identifie cet objet.
	 * @param poids            Le poids de l'objet.
	 * @param estTransportable Indique si l'ObjetZork est transportable.
	 * @throws NullPointerException     si la cha�ne de caract�res sp�cifi�e est
	 *                                  null.
	 * @throws IllegalArgumentException si le poids sp�cifi� est stictement n�gatif.
	 */
	public ObjetZork(String description, int poids, boolean estTransportable) {
		this(description);
		if (poids < 0) {
			throw new IllegalArgumentException("Le poids d'un ObjetZork doit �tre positif");
		}
		if (estTransportable && (poids == 0)) {
			throw new IllegalArgumentException("Le poids d'un ObjetZork transportable doit �tre strictement positif");
		}
		transportable = estTransportable;
		this.poids = poids;
	}

	/**
	 * Renvoie la description de cet objet (i.e. la description sp�cifi�e lors de la
	 * cr�ation de cette instance).
	 *
	 * @return Description de cet objet.
	 *
	 * @pure
	 */
	public String descriptionCourte() {
		return description;
	}

	/**
	 * Renvoie une description de cet objet contenant l'ensemble de ses
	 * caract�ristiques. Cette description est destin�e a fournir une description
	 * compl�te de cet objet format�e pour un affichage pr�sent� a l'utilisateur.
	 *
	 * @return Renvoie une description de cet objet contenant l'ensemble de ses
	 *         caract�ristiques.
	 *
	 * @pure
	 */
	public String descriptionLongue() {
		String descriptionLongue = description;
		if (!transportable && poids == 0)
			descriptionLongue = "--> *Objet: " + description + "\t\t*Transportabilit�: " + transportable + ".";
		if (transportable) {
			descriptionLongue = "--> *Objet: " + description + "\t\t*Poids: " + poids + " Kg" + "\t\t*Tansportabilit�: "
					+ transportable + ".";
		}
		if (!transportable && poids != 0) {
			descriptionLongue = "--> *Objet: " + description + "\t\t*Poids: " + poids + " Kg" + "\t\t*Transportablit�: "
					+ transportable + ".";
		}
		return descriptionLongue;
	}

	/**
	 * Renvoie le poids de cet objet. Si cet objet n'est pas transportable, la
	 * valeur retourn�e n'est pas significative.
	 *
	 * @return Le poids de cet objet.
	 *
	 * @pure
	 */
	public int getPoids() {
		return poids;
	}

	/**
	 * Renvoie true si cet objet est transportable ; false sinon.
	 *
	 * @return true si cet objet est transportable ; false sinon.
	 *
	 * @pure
	 */
	public boolean estTransportable() {
		return transportable;
	}

	/**
	 * Renvoie true si et seulement si l'objet sp�cifi� est un ObjetZork, que les
	 * deux objets sont tous les deux non transportables, ou bien tous les deux
	 * transportables et s'ils sont de m�me poids. Et ils ont la m�me description.
	 *
	 * @param o L'objet a comparer avec cet objet.
	 * @return true si et seulement si l'objet sp�cifi� est un ObjetZork poss�dant
	 *         les memes propri�t�s ; false sinon.
	 */
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof ObjetZork)) {
			return false;
		}

		ObjetZork oz = (ObjetZork) o;
		if (estTransportable() != oz.estTransportable()) {
			return false;
		}
		if (getPoids() != oz.getPoids()) {
			return false;
		}
		return description.equals(oz.description);
	}

	/**
	 * Renvoie true si et seulement si l'objet sp�cifi� est un ObjetZork, que les
	 * deux objets ont la m�me description.
	 *
	 * @param o L'objet a comparer avec cet objet.
	 * @return true si et seulement si l'objet sp�cifi� est un ObjetZork poss�dant
	 *         la m�me description ; false sinon.
	 */
	public boolean equalsDescription(Object o) {
		if (!(o instanceof ObjetZork)) {
			return false;
		}
		ObjetZork oz = (ObjetZork) o;
		return description.equals(oz.description);
	}

	/**
	 * Renvoie un nouveau ObjetZork equals � cet ObjetZork.
	 *
	 * @ensures \result != null;
	 * @ensures \result != this;
	 * @ensures this.equals(\result);
	 *
	 * @return un clone de cet ObjetZork.
	 *
	 * @pure
	 */
	@Override
	public ObjetZork clone() {
		ObjetZork oz = null;
		try {
			oz = (ObjetZork) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError();
		}
		return oz;
	}

	/**
	 * Renvoie un code de hashage pour cet ObjetZork.
	 *
	 * @return un code de hashage pour cet ObjetZork.
	 */
	public int hashCode() {
		if (estTransportable()) {
			return poids + 31 * description.hashCode();
		}
		return description.hashCode() + 31 * poids;
	}

	/**
	 * Renvoie une description succincte sous forme de cha�ne de caract�res de cet
	 * ObjetZork.
	 *
	 * @return Une description succincte de cet ObjetZork.
	 */
	public String toString() {
		return description + "(" + poids + ", " + ", " + transportable + ")";
	}
}
