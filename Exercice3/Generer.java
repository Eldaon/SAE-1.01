import java.util.*;
import java.io.*;

public class Generer {

	public static void genererPage1HTML() {
		try
		{
            // Crée un objet BPrintWriter à partir de l'objet FileOutputStream en utilisant l'encodage UTF-8
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream("PageHTML/page1.html"), "UTF-8" ));
			
			// Crée le tableau d'étudiant
			ArrayList <Etudiant> tabEtudiant = Construction.construireTabEtudiant();

			// Trie les étudiants
			Collections.sort(tabEtudiant);
	
			// Permet d'attribuer le numéro d'équipe aux étudiants
			ArrayList <Equipe> tabEquipe = Construction.construireTabEquipe(tabEtudiant);	
	
			// Trie les étudiants par ordre alphabétique
			tabEtudiant = Outil.trierTabEtudiantParNom(tabEtudiant);

			// Génère la page HTML

			writer.print("<!DOCTYPE html>\n");
			writer.print("<html>\n");
			writer.print("\t<head>\n");
			writer.print("\t\t<meta charset=\"UTF-8\">\n");
			writer.print("\t\t<link rel=\"stylesheet\"href=\"css/page1.css\">\n");
			writer.print("\t\t<title>Page 1</title>\n");
			writer.print("\t</head>\n");
			writer.print("\t<body>\n");
			writer.print("\t\t<h1>Liste des Étudiants</h1>\n");
			writer.print("\t\t<div class=\"liste\">\n");
			writer.print("\t\t\t<table style=\"width:100%\">\n");
			writer.print("\t\t\t\t<thead>\n");
			writer.print("\t\t\t\t\t<tr>\n");
			writer.print("\t\t\t\t\t\t<th>Nom </th>\n");
			writer.print("\t\t\t\t\t\t<th>Prénom</th>\n");
			writer.print("\t\t\t\t\t\t<th>Groupe </th>\n");
			writer.print("\t\t\t\t\t\t<th style=\" text-align: center\">N° d'équipe</th>\n");
			writer.print("\t\t\t\t\t</tr>\n");
			writer.print("\t\t\t\t</thead>\n");
			writer.print("\t\t\t<tbody>\n");
			for(int cptEtudiant = 0; cptEtudiant < tabEtudiant.size(); cptEtudiant++)
			{
				writer.print("\t\t\t\t<tr>\n");
				writer.print("\t\t\t\t\t<td>" + tabEtudiant.get(cptEtudiant).getNom() + "</td>\n");
				writer.print("\t\t\t\t\t<td>" + tabEtudiant.get(cptEtudiant).getPrenom() + "</td>\n");
				writer.print("\t\t\t\t\t<td>" + tabEtudiant.get(cptEtudiant).getClasse() + "</td>\n");
				writer.print("\t\t\t\t\t<td class=\"nEquipe\">" + tabEtudiant.get(cptEtudiant).getEquipe() + "</td>\n");
				writer.print("\t\t\t\t</tr>\n");
			}
			writer.print("\t\t\t</tbody>\n");
			writer.print("\t\t\t</table>\n");
			writer.print("\t\t</div>\n");
			writer.print("\t</body>\n");
			writer.print("</html>\n");

			// Ferme le fichier
			writer.close();

		} catch (Exception e) { e.printStackTrace(); }
  	}

	public static void genererPage1CSS() {
		try
		{
            // Crée un objet BPrintWriter à partir de l'objet FileOutputStream en utilisant l'encodage UTF-8
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream("PageHTML/css/page1.css"), "UTF-8" ));

			// Génère la page HTML

			writer.print(".liste{\n");
			writer.print("\toverflow-x:scroll;\n");
			writer.print("\toverflow-x: hidden;\n");
			writer.print("\theight:800px;\n");
			writer.print("\twidth:40%;\n");
			writer.print("\tmargin: auto;\n");
			writer.print("}\n\n");
			
			writer.print("th{\n");
			writer.print("\tbackground-color:#333F4F;\n");
			writer.print("\ttext-align: left;\n");
			writer.print("\tcolor:white;\n");
			writer.print("}\n\n");

			writer.print("tbody tr:nth-child(even){\n");
			writer.print("\tbackground-color:#dbdbdb;\n");
			writer.print("}\n\n");

			writer.print("h1{\n");
			writer.print("\tfont-size:5vh;\n");
			writer.print("\ttext-align:center;\n");
			writer.print("\tborder: 1px solid black;\n");
			writer.print("}\n");

			writer.print(".nEquipe{\n");
			writer.print("\ttext-align:right;\n");
			writer.print("}\n");
			
			// Ferme le fichier
			writer.close();
		} 
		catch (Exception e) { e.printStackTrace(); }
	}

	public static void genererPage2HTML() {
		
		ArrayList<Etudiant> tabEtudiant = Construction.construireTabEtudiant();
		Collections.sort(tabEtudiant);
		ArrayList<Equipe>   tabEquipe 	= Construction.construireTabEquipe(tabEtudiant);

		int[] 	 tabCategorie = Outil.getTabCategorie(tabEtudiant);
		
		String[] tabCouleur   = new String[] { "darkgoldenrod", "steelblue", "firebrick", "palegreen"};
		int 	 nbEleveCate  = 0;
		int 	 cptEquipe 	  = 0;
		int 	 cptCouleur = 0;

		int opacite = 100;

		try
		{
            // Crée un objet BPrintWriter à partir de l'objet FileOutputStream en utilisant l'encodage UTF-8
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream("PageHTML/page2.html"), "UTF-8" ));

			// Génère la page HTML

			writer.print("<!DOCTYPE html>\n");
			writer.print("<html>\n");
			writer.print("\t<head>\n");
			writer.print("\t\t<meta charset=\"UTF-8\">\n");
			writer.print("\t\t<title>Page 2</title>\n");
			writer.print("\t\t<link rel=\"stylesheet\" href=\"css/page2.css\">\n");
			writer.print("\t</head>\n");
			writer.print("\t<body>\n");
			writer.print("\t\t<h1>Liste des Équipes</h1>\n");
			writer.print("\t\t\t<div id=\"grille\">\n");

			for(int cptCategorie = 0; cptCategorie < tabCategorie.length ; cptCategorie++)
			{
				cptCouleur = cptCategorie%4;
				
				writer.print("\t\t\t\t<div class=\"col\">\n");
				while(cptEquipe < tabEquipe.size())
				{
					writer.print("\t\t\t\t\t<div class=\"grp\">\n");
					writer.print("\t\t\t\t\t\t<div class=\"gauche\" style=\"background-color:"+tabCouleur[cptCouleur]+";opacity:"+opacite+"%\">\n");
					writer.print("\t\t\t\t\t\t\t<p>" + tabEquipe.get(cptEquipe).getNumero() + "</p>\n");
					writer.print("\t\t\t\t\t\t</div>\n");
					writer.print("\t\t\t\t\t\t<table class=\"millieu\">\n");
					for(int cptMembre = 0; cptMembre < tabEquipe.get(cptEquipe).getTabMembreEquipe().size(); cptMembre++)
					{
						writer.print("\t\t\t\t\t\t\t<tr class=\"eleve\">\n");
						writer.print("\t\t\t\t\t\t\t\t<td>" + tabEquipe.get(cptEquipe).getTabMembreEquipe().get(cptMembre).getNom() + "</td>\n");
						writer.print("\t\t\t\t\t\t\t\t<td>" + tabEquipe.get(cptEquipe).getTabMembreEquipe().get(cptMembre).getPrenom() + "</td>\n");
						writer.print("\t\t\t\t\t\t\t\t<td>" + tabEquipe.get(cptEquipe).getTabMembreEquipe().get(cptMembre).getClasse() + "</td>\n");
						writer.print("\t\t\t\t\t\t\t</tr>\n");
						nbEleveCate++;
					}
					writer.print("\t\t\t\t\t\t</table>\n");
					writer.print("\t\t\t\t\t\t<div class=\"droite\" style=\"background-color:"+tabCouleur[cptCouleur]+";opacity:"+opacite+"%\">\n");
					writer.print("\t\t\t\t\t\t\t<p>" + tabEquipe.get(cptEquipe).getSalle() + "</p>\n");
					writer.print("\t\t\t\t\t\t</div>\n");


					writer.print("\t\t\t\t\t</div>\n");
					cptEquipe++;
					opacite = opacite - 5;
					if(nbEleveCate == tabCategorie[cptCategorie]) { break; }
					
				}
				opacite = 100;
				nbEleveCate = 0;
				writer.print("\t\t\t\t</div>\n");
			}
			writer.print("\t\t\t</div>\n");
			writer.print("\t</body>\n");
			writer.print("</html>");

			// Ferme le fichier
			writer.close();
			
		} catch (Exception e) { e.printStackTrace(); }
  	}

	public static void genererPage2CSS()
	{
		try
		{
			// Crée un objet BPrintWriter à partir de l'objet FileOutputStream en utilisant l'encodage UTF-8
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream("PageHTML/css/page2.css"), "UTF-8" ));

			writer.print("h1{\n");
			writer.print("\tfont-size:5vh;\n");
			writer.print("\ttext-align:center;\n");
			writer.print("\tborder: 1px solid black;\n");
			writer.print("}\n\n");

			writer.print(".grp{\n");
			writer.print("\tdisplay:inline-flex;\n");
			writer.print("\tborder:1px solid black;\n");
			writer.print("\tmargin-bottom:50px;\n");
			writer.print("}\n\n");

			writer.print(".gauche,.droite{\n");
			writer.print("\twidth:60px;\n");
			writer.print("\talign-items:center;\n");
			writer.print("}\n\n");

			writer.print(".gauche p,.droite p{\n");
			writer.print("\ttext-align:center;\n");
			writer.print("\tmargin-top:65%;\n");
			writer.print("}\n\n");

			writer.print("td {\n");
			writer.print("\twidth:105px;\n");
			writer.print("\theight:30px;\n");
			writer.print("}\n\n");

			writer.print("#grille {\n");
			writer.print("\tdisplay: flex;\n");
			writer.print("\tjustify-content: center;\n");
			writer.print("}\n\n");

			writer.print(".col {\n");
			writer.print(" width: 100%;\n");
			writer.print(" text-align:center;\n");
			writer.print("}\n\n");

			// Ferme le fichier
			writer.close();

		} catch (Exception e) { e.printStackTrace(); }
	}

	public static void genererPage3HTML()
	{
		ArrayList<Etudiant> tabEtudiant = Construction.construireTabEtudiant();
		ArrayList<Jury> tabJury = Construction.construireTabJury(tabEtudiant);
		ArrayList<Equipe> tabEquipe = Construction.construireTabEquipe(tabEtudiant);
		String heure;
		String stringDate = Outil.afficherDate(tabJury.get(0).getDate());

		int cptEquipe = 0;
		try
		{
			// Crée un objet BPrintWriter à partir de l'objet FileOutputStream en utilisant l'encodage UTF-8
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream("PageHTML/page3.html"), "UTF-8" ));

			// Génère la page HTML

			writer.print("<!DOCTYPE html>\n");
			writer.print("<html>\n");
			writer.print("\t<head>\n");
			writer.print("\t\t<meta charset=\"UTF-8\">\n");
			writer.print("\t\t<link rel=\"stylesheet\" href=\"css/page3.css\">\n");
			writer.print("\t\t<title>Page 3</title>\n");
			writer.print("\t</head>\n");
			writer.print("\t<body>\n");
			writer.print("\t<h2> " + stringDate + " </h2>\n");
			
			writer.print("\t<div class=\"agenda\">\n");

			for(int cptJury = 0; cptJury < tabJury.size(); cptJury++)
			{
				if(!stringDate.equals(Outil.afficherDate(tabJury.get(cptJury).getDate())))
				{
					stringDate = Outil.afficherDate(tabJury.get(cptJury).getDate());
					writer.print("\t</div>\n");
					writer.print("\t<h2> " + stringDate + " </h2>\n");
					writer.print("\t<div class=\"agenda\">\n");
				}

				heure = tabJury.get(cptJury).getDebut();

				writer.print("\t\t\t<div class=\"jury\">\n");
				writer.print("\t\t\t\t<h3> Jury "+ (cptJury+1) + " </h3>\n");
				for(int cptProf = 0; cptProf < tabJury.get(cptJury).getTabEnseignant().size() ; cptProf++ )
				{
					writer.print("\t\t\t\t<p>  "+ tabJury.get(cptJury).getTabEnseignant().get(cptProf) + " </p>\n");
				}

				for(int cptPassage = 0; cptPassage < tabJury.get(cptJury).getNbPassage(); cptPassage++)
				{
					if(cptEquipe < tabEquipe.size())
					{
						writer.print("\t\t\t\t<div class=\"passage\">\n");
						writer.print("\t\t\t\t\t<div>\n");
						writer.print("\t\t\t\t\t\t<p> " + heure + " </p>\n");
						heure = Outil.ajoutPassage(heure);
						writer.print("\t\t\t\t\t\t<p> " + heure + " </p>\n");
						writer.print("\t\t\t\t\t</div>\n");
						
						writer.print("\t\t\t\t\t<div class=\"grp-salles\">\n");
						writer.print("\t\t\t\t\t\t<p> " + (cptEquipe+1) + " </p>\n");
						writer.print("\t\t\t\t\t\t<p> " + tabJury.get(cptJury).getSalle() + " </p>\n");
						writer.print("\t\t\t\t\t</div>\n");
						writer.print("\t\t\t\t</div>\n");
						cptEquipe++;
						heure = Outil.ajoutPause(heure);
					}
				}
				writer.print("\t\t\t</div>\n");
			}
			writer.print("\t\t</div>\n");
			writer.print("\t</body>\n");
			writer.print("</html>");
		
			// Ferme le fichier
			writer.close();

		} catch (Exception e) { e.printStackTrace(); }
	}

	public static void genererPage3CSS()
	{
		try
		{
			// Crée un objet BPrintWriter à partir de l'objet FileOutputStream en utilisant l'encodage UTF-8
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream("PageHTML/css/page3.css"), "UTF-8" ));

			// Génère la page CSS

			writer.print(".jury{\n");
			writer.print("\theight:550px;\n");
			writer.print("\twidth:250px;\n");
			writer.print("\tborder: 1px solid black;\n");
			writer.print("\tmargin-right:50px;\n");
			writer.print("}\n\n");
			
			writer.print("h2{\n");
			writer.print("\tborder-top:1px solid black;\n");
			writer.print("\tborder-bottom:1px solid black;\n");
			writer.print("\twidth:300px;\n");
			writer.print("}\n\n");

			writer.print("h3{\n");
			writer.print("\tbackground-color:#F2F2F2;\n");
			writer.print("\tmargin:0;\n");
			writer.print("}\n\n");

			writer.print(".agenda{\n");
			writer.print("\tdisplay: inline-flex;\n");
			writer.print("}\n\n");
			
			writer.print(".passage{\n");
			writer.print("\tdisplay: flex;\n");
			writer.print("\tjustify-content: center;\n");
			writer.print("\talign-items: center;\n");
			writer.print("}\n\n");
			
			writer.print(".grp-salles{\n");
			writer.print("\tborder:1px solid black;\n");
			writer.print("\twidth:100px;\n");
			writer.print("\theight:40px;\n");
			writer.print("}\n\n");

			writer.print(".grp-salles p{\n");
			writer.print("\ttext-align:center;\n");
			writer.print("\tmargin-top:0;\n");
			writer.print("\tmargin-bottom:0;\n");
			writer.print("}");

			// Ferme le fichier
			writer.close();

		} catch (Exception e) { e.printStackTrace(); }
	}

	public static void genererIndexHTML()
	{
		try
		{
			// Crée un objet BPrintWriter à partir de l'objet FileOutputStream en utilisant l'encodage UTF-8
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream("PageHTML/index.html"), "UTF-8" ));

			// Génère la page HTML
			writer.print("<!DOCTYPE html>\n");
			writer.print("<html>\n");
			writer.print("\t<head>\n");
			writer.print("\t\t<meta charset=\"utf-8\">\n");
			writer.print("\t\t<link rel=\"stylesheet\" href=\"css/index.css\">\n");
			writer.print("\t\t<title>Accueil</title>\n");
			writer.print("\t</head>\n");
			writer.print("\t<body>\n");
			writer.print("\t\t<h1> Générateur de Planning </h1> \n");

			writer.print("\t\t<button> <a href=\"page1.html\"> Liste des Étudiants </a> </button>\n");
			writer.print("\t\t<button> <a href=\"page2.html\"> Liste des Équipes </a> </button>\n");
			writer.print("\t\t<button> <a href=\"page3.html\"> Planning des Oraux </a> </button>\n");
			writer.print("\t</body>\n");
			writer.print("</html>\n");

			// Ferme le fichier
			writer.close();

		} catch (Exception e) { e.printStackTrace(); }
	}
  
	public static void genererIndexCSS()
	{
		try
		{
			// Crée un objet BPrintWriter à partir de l'objet FileOutputStream en utilisant l'encodage UTF-8
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream("PageHTML/css/index.css"), "UTF-8" ));

			// Génère la page CSS

			writer.print("@import url('https://fonts.googleapis.com/css2?family=Roboto&display=swap');\n\n");
			writer.print("body{\n");
			writer.print("\tbackground-color:#7A5CFA;\n");
			writer.print("\ttext-align:center;\n");
			writer.print("}\n\n");

			writer.print("h1{\n");
			writer.print("\tcolor:white;\n");
			writer.print("\tfont-family: 'Roboto', sans-serif;\n");
			writer.print("}");

			writer.print("button{\n");
			writer.print("\tbackground-color: white;\n");
			writer.print("\tborder:none;\n");
			writer.print("\twidth:275px;\n");
			writer.print("\theight: 70px;\n");
			writer.print("\tborder-radius:10px;\n");
			writer.print("}\n\n");

			writer.print("a{\n");
			writer.print("\tcolor:#7A5CFA;\n");
			writer.print("\ttext-decoration:none;");
			writer.print("\tfont-size: 2.5vh;\n");
			writer.print("\tfont-family: 'Roboto', sans-serif;\n");
			writer.print("}");

			// Ferme le fichier
			writer.close();
  
		} catch (Exception e) { e.printStackTrace(); }
	}
}