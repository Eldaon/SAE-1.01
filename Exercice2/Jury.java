import java.util.ArrayList;

public class Jury 
{
	/*----------------------*/
	/*  Attributs           */
	/*----------------------*/

    private int salle;
    private String date;
    private String debut;
    private String fin;
    private int nbPassage;
    private ArrayList<String> enseignant = new ArrayList<String>();

	/*----------------------*/
	/*  Méthodes            */
	/*----------------------*/

    // Constructeur de la classe Jury
    public Jury(int salle, String date, String debut, String fin, ArrayList<String> enseignant, int nbPassage)
    {
        this.salle = salle;
        this.date = date;
        this.debut = debut;
        this.fin = fin;
        this.enseignant = enseignant;
        this.nbPassage = nbPassage;
    }

    // Récupère la salle du Jury
    public int getSalle() {
        return this.salle;
    }

    // Récupère la date du Jury
    public String getDate() {
        return this.date;
    }

    // Récupère l'heure de commencement du Jury
    public String getDebut() {
        return this.debut;
    }

    // Récupère l'heure de fin du Jury
    public String getFin() {
        return this.fin;
    }

    // Récupère le nombre de passage que peut faire le jury
    public int getNbPassage()
    {
        return this.nbPassage;
    }

    // Récupère les enseignants du jury
    public String getEns(){
        String sRet = "";
        for(int i = 0; i < enseignant.size(); i++){
            sRet += enseignant.get(i) + "\n";
        }
        return sRet;
    }

    // Affiche le jury
    public String toString(int indice){
        String sRet = "";

        sRet += "Jury " + indice + "\n";
        sRet += getEns();

        return sRet;
    }
}