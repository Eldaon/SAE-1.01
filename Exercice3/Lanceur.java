import java.util.*;

// Classe qui lance tous les sous programmes et initialise des variables utiles dans tous les sous programmes
public class Lanceur
{
	public static void main(String[] a)
	{
		/*----------------------*/
		/*  Variable            */
		/*----------------------*/

		int indice 		 = 1;
		int indiceEquipe = 0;

		ArrayList<Etudiant> tabEtudiant;
		ArrayList<Equipe> 	tabEquipe;
		ArrayList<Jury> 	tabJury;

		/*----------------------*/
		/*  Contenu             */
		/*----------------------*/

		Generer.genererIndexHTML();	// Générer la partie HTML de la page d'accueil
		Generer.genererIndexCSS();	// Générer la partie HTML de la page d'accueil
		
		Generer.genererPage1HTML(); // Générer la partie HTML de la page de la liste des élèves
		Generer.genererPage2HTML(); // Générer la partie HTML de la page des groupes de travail
		Generer.genererPage3HTML();	// Générer la partie HTML de la page du planing des évaluations

		Generer.genererPage1CSS(); 	// Générer la partie CSS de la page de la liste des élèves
		Generer.genererPage2CSS();	// Générer la partie CSS de la page des groupes de travail
		Generer.genererPage3CSS();	// Générer la partie CSS de la page du planing des évaluations
	}
}