import java.util.*;

public class Lanceur
{
	public static void main(String[] a)
	{
		int numEquipe = 1;

		ArrayList<Etudiant> tabEtudiant;
		ArrayList<Equipe> tabEquipe;

		Construction construct = new Construction();

		tabEtudiant = construct.construireTabEtudiant();

		Collections.sort(tabEtudiant);

		tabEquipe = construct.construireTabEquipe( tabEtudiant );

		// Affichage Liste Elève non trier
		System.out.println ( "\nListe des équipes :\n ");
		for ( Equipe e : tabEquipe ) { System.out.println ( e.toString(numEquipe++) ); }

	}
}