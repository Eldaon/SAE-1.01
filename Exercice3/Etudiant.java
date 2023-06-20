public class Etudiant implements Comparable<Etudiant>
{
	/*----------------------*/
	/*  Attributs           */
	/*----------------------*/

	private String nom;
	private String prenom;
	private char   classe;
	private String categorie;
	private int    nEquipe;

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
		this.nEquipe	= -1;
	}

	// Permet de changer le numéro de l'équipe
	public void setEquipe(int numEquipe) { this.nEquipe = numEquipe; }

	// Permet de récupérer le nom de l'étudiant
	public String getNom()	{ return this.nom; }

	// Permet de récupérer le prénom de l'étudiant
	public String getPrenom()	{ return this.prenom; }

	// Permet de récupérer la classe de l'étudiant
	public char getClasse()	{ return this.classe; }

	// Permet de récupérer la catégorie de l'étudiant
	public String getCategorie()	{ return this.categorie; }

	public int getEquipe() { return this.nEquipe;}

	// Permet de comparer des étudiants entre eux selon leurs categorie (Utile pour le ArrayList.sort)
	public int compareTo ( Etudiant autreEtudiant )	{ return this.categorie.compareTo( autreEtudiant.categorie); }

	// Permet d'afficher les informations basiques d'un 'étudiant (nom, prénom, groupe)
	public String toString()
	{
		String sRet = String.format("%-10s\t", this.nom);
		sRet += String.format("%-10s\t", this.prenom);
		sRet += String.format("%s\t", this.classe);
		sRet += this.nEquipe;
		return sRet;
	}
}