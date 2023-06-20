public class Etudiant implements Comparable<Etudiant>
{

	/*----------------------*/
	/*  Attributs           */
	/*----------------------*/

	private String nom;
	private String prenom;
	private char classe;
	private String categorie;

	/*----------------------*/
	/*  Méthodes            */
	/*----------------------*/

	// Constructeur de la classe Etudiant
	public Etudiant( String nom, String prenom, char classe, String categorie) 
	{
		this.nom        = nom;
		this.prenom     = prenom;
		this.classe     = classe;
		this.categorie  = categorie;
	}

	// Permet de récupérer le nom de l'étudiant
	public String getNom()	{ return this.nom; }

	// Permet de récupérer le prénom de l'étudiant
	public String getPrenom()	{ return this.prenom; }

	// Permet de récupérer la classe de l'étudiant
	public char getClasse()	{ return this.classe; }

	// Permet de récupérer la catégorie de l'étudiant
	public String getCategorie()	{ return this.categorie; }

	// Méthode qui permet de comparer des étudiants entre eux selon leurs categorie (Utile pour le ArrayList.sort)
	public int compareTo ( Etudiant autreEtudiant )	{ return this.categorie.compareTo( autreEtudiant.categorie); }

	// Affiche les informations basique de l'étudiant (nom, prénom, groupe)
	public String toString()
	{
		String s = String.format("%-10s\t", this.nom);
		s += String.format("%-10s\t", this.prenom);
		s += String.format("%s\t", this.classe);
		return s;
	}
}