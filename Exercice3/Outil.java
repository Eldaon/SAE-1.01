import java.io.FileInputStream;
import iut.algo.Decomposeur;
import java.util.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Outil
{

	// Récupère le nombre d'élève moyen par équipe
	public static int getCapaEquipe() {

		/*----------------------*/
		/*  Variables           */
		/*----------------------*/
        Decomposeur dec;
		Scanner     scFic;

        int 		capaEquipe = 0;
		String 		ligne;

		/*----------------------*/
		/*  Contenu             */
		/*----------------------*/
		try
		{
			// Lecture du fichier promotion.data
			scFic = new Scanner ( new FileInputStream ( "data/ressources.data" ), "UTF8" );
			scFic.hasNextLine();	// On évite la première ligne qui représente le nb de personne par équipe (Pas utile ici)

			ligne = scFic.nextLine();
			dec   = new Decomposeur(ligne);

            capaEquipe = dec.getInt(0);
        
        } catch ( Exception e ){ e.printStackTrace(); }

		return capaEquipe;
    }

	// Permet de convertir une date sous format "JJ/MM/AA" en format "Jour X Mois Annee"
	public static String afficherDate(String dateEntree)
	{

        String[] dateParts = dateEntree.split("/");
        int 	 jour 	   = Integer.parseInt(dateParts[0]);
        int 	 mois 	   = Integer.parseInt(dateParts[1]);
        int 	 annee 	   = Integer.parseInt(dateParts[2]);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy", Locale.FRANCE);
        LocalDate localDate 		= LocalDate.of(annee, mois, jour);
        String dateFormate			= localDate.format(formatter);

		dateParts = dateFormate.split(" ");
		String jourString = dateParts[0].substring(0, 1).toUpperCase() + dateParts[0].substring(1);
		String moisString = dateParts[2].substring(0, 1).toUpperCase() + dateParts[2].substring(1);

		String date = jourString + " " + dateParts[1] + " " + moisString + " " + annee;

		return date;

    }

	public static String afficherPassage(int indice, Jury jury, String heure)
	{
		String sRet = heure + " à \t";
		heure = ajoutPassage(heure);
		sRet += heure;
		sRet += " Equipe\t" + (indice+1) + " salle " + jury.getSalle();
		
		return sRet;
	}

	// Permet d'ajouter le temps de passage
	public static String ajoutPassage(String heure)
	{
		String[] tabHeure = heure.split(":");
		int 	 heures   = Integer.parseInt(tabHeure[0]);
		int 	 minutes  = Integer.parseInt(tabHeure[1]) + getTpsPassage();
		String 	 sRet 	  = "";
		
		return formatHeure(heures, minutes);
	}

	// Permet d'ajouter le temps de pause
	public static String ajoutPause(String heure)
	{
		String[] tabHeure = heure.split(":");
		int 	 heures   = Integer.parseInt(tabHeure[0]);
		int 	 minutes  = Integer.parseInt(tabHeure[1]) + getTpsPause();

		return formatHeure(heures, minutes);
	}

	// Permet de corriger les heures et de mettre sous le format "hh:mm"
	public static String formatHeure(int heures, int minutes  )
	{
		String sRet = "";
		if(minutes >= 60) 	{ heures++; minutes = minutes - 60; }
		
		if(heures < 10)		{ sRet += "0"+heures;} else {sRet += heures;}
		sRet += ":";

		if(minutes < 10){ sRet += "0"+minutes;} else {sRet += minutes;}
		return sRet;
	}

	// Permet de convertir un string "hh:mm" en minutes
	public static int getMinute(String heure)
	{
		String[] tabHeure 	  = heure.split(":");
		int 	 hours 	  	  = Integer.parseInt(tabHeure[0]);
		int 	 minutes  	  = Integer.parseInt(tabHeure[1]);
		int 	 totalMinutes = hours * 60 + minutes;
		
		return totalMinutes;
	}

	// Permet de lire le temps de passage dans le fichier
	public static int getTpsPassage()
	{
		Decomposeur dec;
		Scanner     scFic;

		String  ligne;
		int 	passage = 0;

		try
		{
			scFic 	= new Scanner ( new FileInputStream ( "data/jury.data" ), "UTF8" );
			scFic.hasNextLine();
			ligne 	= scFic.nextLine();
			dec 	= new Decomposeur(ligne);
			passage = dec.getInt(0);

			scFic.close();

		}catch ( Exception e )    { e.printStackTrace(); }

		return passage;
	}

	// Permet de lire le temps de pause dans le fichier
	public static int getTpsPause()
	{
		Decomposeur dec;
		Scanner     scFic;

		String  ligne;
		int     pause = 0;

		try
		{
			scFic = new Scanner ( new FileInputStream ( "data/jury.data" ), "UTF8" );
			scFic.hasNextLine();
			ligne = scFic.nextLine();
			dec   = new Decomposeur(ligne);
			pause = dec.getInt(1);


		}catch ( Exception e )    { e.printStackTrace(); }
		return pause;
	}

	// Permet de compter le nombre d'étudiants par catégorie 
	public static int[] getTabCategorie(ArrayList<Etudiant> tabEtudiant)
	{
		int nbLettre;

		ArrayList<String> lettreCategorie = new ArrayList<String>();

		for(int i = 0; i < tabEtudiant.size();i++)
		{
			lettreCategorie.add(tabEtudiant.get(i).getCategorie());
		}

		HashSet<String> tempoCategorie = new HashSet<String>(lettreCategorie);

		nbLettre = tempoCategorie.size();

		int[] tabCategorie = new int[nbLettre];

		for( int i = 0; i < tabEtudiant.size(); i++ )
		{
			char categorie = tabEtudiant.get(i).getCategorie().charAt(0);
			int nbCategorie = (int) categorie - 'A';
			tabCategorie[nbCategorie]++;
		}

		return tabCategorie;
	}

	// Permet de trier les étudiants dans l'ordre alphabétique
	public static ArrayList<Etudiant> trierTabEtudiantParNom(ArrayList<Etudiant> tabEtudiant)
	{
		/*----------------------*/
		/*  Variable            */
		/*----------------------*/
		int 	 i, j;
		int 	 nbEtudiant = tabEtudiant.size();
		Etudiant temp;

		/*----------------------*/
		/*  Contenu             */
		/*----------------------*/
		for(i = 0; i < nbEtudiant; i++)
		{
			for(j = 0; j < nbEtudiant-1; j++)
			{
				if(tabEtudiant.get(j).getNom().compareTo(tabEtudiant.get(j+1).getNom()) > 0)
				{
					temp = tabEtudiant.get(j);
					tabEtudiant.set(j, tabEtudiant.get(j+1));
					tabEtudiant.set(j+1, temp);
				}
			}
		}
		return tabEtudiant;
	}
}