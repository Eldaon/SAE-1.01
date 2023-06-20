public class Salle 
{
    /*----------------------*/
	/*  Attributs           */
	/*----------------------*/

    private int numero;
    private int capacite;

    /*----------------------*/
	/*  Méthodes            */
	/*----------------------*/

    // Constructeur de la classe Salle
    public Salle(int numero, int place)
    {
        this.numero = numero;
        this.capacite = place;
    }

    // Permet de récupérer le numéro de la salle
    public int getNumero()  { return numero;  }

    // Permet de récupérer le nombre de places libres dans la salle
    public int getCapacite() { return this.capacite; }

    // Permet de retirer une place 
    public void moinsPlace() {  this.capacite--;  }
}