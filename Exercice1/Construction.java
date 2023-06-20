import javax.print.attribute.standard.NumberUpSupported;
import java.io.FileInputStream;
import iut.algo.Decomposeur;
import java.util.*; 


public class Construction
{
	public ArrayList<Etudiant> construireTabEtudiant()
	{

		/*----------------------*/
		/*  Variable            */
		/*----------------------*/

		Scanner      scFic;
		Decomposeur  dec;

		ArrayList<Etudiant> tabEtudiant = new ArrayList<Etudiant>();
		int        nbEtudiant;

		String     nom;
		String     prenom;
		char       groupe;
		String       categorie;

		/*----------------------*/
		/*  Contenu             */
		/*----------------------*/

		// Parcours du tableau pour ajouter les Etudiants
		try
		{
			// Lecture du fichier promotion.data
			scFic = new Scanner ( new FileInputStream ( "data/promotion.data" ), "UTF8" );

			nbEtudiant = 0;
			
			// On évite la première ligne (Prénom, nom, Classe, Catégorie)
			scFic.nextLine(); 

			// Tant qu'il reste des lignes
			while ( scFic.hasNextLine() )
			{
				// On décompose la phrase à chaque tabulation
				dec = new Decomposeur ( scFic.nextLine () );

				// Attribution des différentes variables
				nom       = dec.getString 	(0);
				prenom    = dec.getString 	(1);
				groupe    = dec.getChar		(2);
				categorie = dec.getString 	(3);

				// Création d'un étudiant
				tabEtudiant.add(new Etudiant ( nom, prenom, groupe, categorie ));
			}

			// Fermeture du scanner
			scFic.close();
		}catch (Exception e){ e.printStackTrace(); }

		return tabEtudiant;
	}

	public ArrayList<Salle> construireTabSalle()
	{
		Decomposeur dec;
		Scanner     scFic;

		String ligne;
		int    placeEquipe, place, salle;

		ArrayList<Salle> tabSalle = new ArrayList<Salle> ();

		try
		{
			// Lecture du fichier promotion.data
			scFic = new Scanner ( new FileInputStream ( "data/ressources.data" ), "UTF8" );

			// On évite la première ligne qui représente le nb de personne par équipe (Pas utile ici)
			scFic.hasNextLine();
			ligne = scFic.nextLine();

			// Récupération des informations concernant la salle, et création d'une nouvelle "Salle"
			while ( scFic.hasNextLine())
			{
				ligne = scFic.nextLine();
				dec   = new Decomposeur(ligne);

				salle = dec.getInt(0);
				place = dec.getInt(1);
				tabSalle.add( new Salle(salle, place) );
			}

		}
		catch ( Exception e ){ e.printStackTrace(); }
		return tabSalle;
	}

	public ArrayList<Equipe> construireTabEquipe( ArrayList<Etudiant> tabEtudiant )
	{
		/*----------------------*/
		/*  Variable            */
		/*----------------------*/
		int[]  nbCategorie = new int[3];
		int    surplus, nbEquipe;

		int    indiceMembre = 0;
		int    indiceSalle  = 0;
		int cptEquipe = 1;

		ArrayList<Salle>    tabSalle    = construireTabSalle()      	;
		ArrayList<Equipe>   tabEquipe   = new ArrayList<Equipe>()		;
		ArrayList<Etudiant> tabMembreEquipe = new ArrayList<Etudiant>()	;
	
		/*----------------------*/
		/*  Méthode             */
		/*----------------------*/

		// Calcul le nombre d'étudiant par catégorie
		for( int i = 0; i < tabEtudiant.size(); i++ )
		{
			switch(tabEtudiant.get(i).getCategorie())
			{
				case "A": nbCategorie[0]++; break;
				case "B": nbCategorie[1]++; break;
				case "C": nbCategorie[2]++; break;
			}
		}

		// Récupération de la taille des groupes
		int tailleGrp   = 4;

		// Récupération du numéro de salle
		int numeroSalle = tabSalle.get(indiceSalle).getNumero();

		// Initialisation de la catégorie
		char categorie = 'Z';

		// Passage sur les 3 catégories
		for(int indiceCategorie = 0; indiceCategorie < 3; indiceCategorie++)
		{
			// Attribution de la catégorie actuel à la variable "categorie"
			switch(indiceCategorie)
			{
				case 0 : categorie = 'A'; break;
				case 1 : categorie = 'B'; break;
				case 2 : categorie = 'C'; break;
			}

			// Récupération des élèves en trop dans la catégorie actuel
			surplus  = nbCategorie[indiceCategorie] % tailleGrp;

			// Récupération du nombre de groupe dans la catégorie actuel
			nbEquipe = nbCategorie[indiceCategorie] / tailleGrp; 
			
			// Ici, on ajoute les membres déjà passé à la variable "nbCategorie"
			if(indiceCategorie != 0) { nbCategorie[indiceCategorie] = nbCategorie[indiceCategorie] + nbCategorie[indiceCategorie-1]; }

			// Tant que le membre actuel ne dépasse la la limite de membre de la categorie
			while(indiceMembre < nbCategorie[indiceCategorie])
			{
				// Changement de la salle du groupe si elle est pleine
				if(tabSalle.get(indiceSalle).getCapacite() == 0)	
				{
					indiceSalle++;
					numeroSalle = tabSalle.get(indiceSalle).getNumero();
				}
				
				// Entrer les 4 premiers étudiants de l'équipe
				for(int i = 0; i < 4; i++) { 
					tabMembreEquipe.add(tabEtudiant.get(indiceMembre+i)); 
				}

				// Entrer d'un 5ème étudiant si necéssaire
				if(nbEquipe <= surplus) { tabMembreEquipe.add(tabEtudiant.get(indiceMembre+4)); }

				// Ajout de (4-5) étudiants dans le compteur
				indiceMembre = indiceMembre + tabMembreEquipe.size();

				// Ajoute une nouvelle équipe
				tabEquipe.add ( new Equipe(cptEquipe, tabMembreEquipe, numeroSalle, categorie) );

				// On retire une place dans la salle actuel
				tabSalle.get(indiceSalle).moinsPlace();       
				
				// On réinitialise la liste des membres de l'équipe
				tabMembreEquipe = new ArrayList<Etudiant>();

				nbEquipe--;
				cptEquipe++;
			}
		}
		return tabEquipe;
	}
}



