import java.io.FileInputStream;
import iut.algo.Decomposeur;
import java.util.*; 


public class Outil
{

	public static int getCapaEquipe()
	{
        Decomposeur dec;
		Scanner     scFic;

        int capaEquipe = 0;

		String ligne;
		try
		{
			// Lecture du fichier promotion.data
			scFic = new Scanner ( new FileInputStream ( "data/ressources.data" ), "UTF8" );

			// On évite la première ligne qui représente le nb de personne par équipe (Pas utile ici)
			scFic.hasNextLine();

			ligne = scFic.nextLine();
			dec   = new Decomposeur(ligne);

            capaEquipe = dec.getInt(0);
        
        } catch ( Exception e ){ e.printStackTrace(); }

		return capaEquipe;
    }

}