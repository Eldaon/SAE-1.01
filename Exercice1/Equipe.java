import java.util.*;  

public class Equipe 
{

	/*----------------------*/
	/*  Attributs           */
	/*----------------------*/

	private int numero;
	ArrayList<Etudiant> tabMembreEquipe = new ArrayList<Etudiant>();
	private int salle;
	private char categorie;

	/*----------------------*/
	/*  Méthodes            */
	/*----------------------*/

	// Constructeur de la classe Equipe
	public Equipe(int numero, ArrayList<Etudiant> tabMembreEquipe, int salle, char categorie)
	{
		this.numero = numero;
		this.tabMembreEquipe = tabMembreEquipe;
		this.salle   = salle;
		this.categorie = categorie;
	}

	public int getNumero()	{ return this.numero;}

	// Permet de récupérer la liste des membres de l'équipe
	public ArrayList<Etudiant> getTabMembreEquipe() { return this.tabMembreEquipe; }

	// Permet de récupérer la salle de l'équipe
	public int getSalle()       { return this.salle; }

	// Permet de récupérer la catégorie du groupe
	public char getCategorie()  { return this.categorie; }

	// Méthode d'affichage des équipes
	public String toString(int indice)
	{
		String s = "Equipe "+ indice + ": \tSalle : "+ this.salle + "\n";
		for (Etudiant membre : tabMembreEquipe) {
			s += membre + "\n";
		}
		return s;
	}
}