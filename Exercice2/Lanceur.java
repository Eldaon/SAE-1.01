import java.util.*;

public class Lanceur
{
	public static void main(String[] a)
	{
		int indice = 1;
		int indiceEquipe = 0;

		ArrayList<Etudiant> tabEtudiant;
		ArrayList<Equipe> tabEquipe;
		ArrayList<Jury> tabJury;

		Construction construct = new Construction();

		tabEtudiant = construct.construireTabEtudiant();

		Collections.sort(tabEtudiant);

		tabEquipe = construct.construireTabEquipe( tabEtudiant );

		tabJury = construct.construireTabJury( tabEtudiant );

		// Affiche les jury
		
		String stringDate = Outil.afficherDate(tabJury.get(0).getDate());

		System.out.println(stringDate);

		for ( Jury jury : tabJury)
		{
			String heure = jury.getDebut();

			if(!stringDate.equals(Outil.afficherDate(jury.getDate())))
			{
				stringDate = Outil.afficherDate(jury.getDate());
				System.out.println(stringDate);
			}

			System.out.println( jury.toString(indice) );

			for(int i = 0; i < jury.getNbPassage(); i++)
			{
				if(indiceEquipe < tabEquipe.size())
				{
					System.out.println(Outil.afficherPassage(indiceEquipe, jury, heure));
					indiceEquipe++;
					heure = Outil.ajoutPassage(heure);
					heure = Outil.ajoutPause(heure);
				}
			}
			System.out.println("\n");
			indice++;
		}
	}
}