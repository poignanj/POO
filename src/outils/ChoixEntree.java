package outils;

import java.util.ArrayList;
import java.util.Scanner;

//Regroupe des fonctions permettant de demander un choix à l'utilisateur
public class ChoixEntree {
	//Demander à l'utilisateur de valider
	public static boolean Valider(String message)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println(message + "oui/non");
		try
		{
		String input = sc.next();
		if(input.equalsIgnoreCase("oui") || input.equalsIgnoreCase("o"))
			return true;
		else if(input.equalsIgnoreCase("non") || input.equalsIgnoreCase("n"))
			return false;
		else
			throw new Exception();
		}
		catch(Exception e)
		{
			return Valider("Réponse non valide, veuillez répondre à nouveau.");
		}
	}
	
	public static char ChoixPlageChar(char debut, char fin, String message, boolean ignoreCase)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println(message + " " + debut + "-" + fin + " : ");
		try
		{
			String input = sc.next();
			char c;
			
			if(input.length() == 1)
				c = input.charAt(0);
			else
				throw new Exception();

			if(ignoreCase)
			{
				if((debut >= 'a' && debut <= 'z') && (fin >= 'a' && fin <= 'z') && (c >= 'A' && c <= 'Z'))
					c += 'a' - 'A';
				else if((debut >= 'A' && debut <= 'Z') && (fin >= 'A' && fin <= 'Z') && (c >= 'a' && c <= 'z'))
					c += 'A' - 'a';
			}
				
			if((c >= debut) && (c <= fin))
				return c;
			else
				throw new Exception();
		}
		catch(Exception e)
		{
			return ChoixPlageChar(debut, fin, "Réponse non valide, veuillez répondre à nouveau.", ignoreCase);
		}
	}
	
	public static int ChoixPlageInt(int min, int max, String message)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println(message + " [" + min + ";" + max + "] : ");
		try
		{
			int input = sc.nextInt();
			
			if((input >= min) && (input <= max))
				return input;
			else
				throw new Exception();
		}
		catch(Exception e)
		{
			return ChoixPlageInt(min, max, "Réponse non valide, veuillez répondre à nouveau.");
		}
	}
	
	public static Coordonnee ChoixCoordonnee(char debut, char fin, int min, int max, String message)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println(message);
		try
		{
			Coordonnee retour = new Coordonnee();
			String input = sc.next();
			if(input.length() < 1)
				throw new Exception();
			
			char c = input.charAt(0);

			if((debut >= 'a' && debut <= 'z') && (fin >= 'a' && fin <= 'z') && (c >= 'A' && c <= 'Z'))
				c += 'a' - 'A';
			else if((debut >= 'A' && debut <= 'Z') && (fin >= 'A' && fin <= 'Z') && (c >= 'a' && c <= 'z'))
				c += 'A' - 'a';
			
			if((c >= debut) && (c <= fin))
				retour.y = c - debut;
			else
				throw new Exception();
			
			retour.x = Integer.parseInt(input.substring(1))-1;
			
			if(retour.x >= min-1 && retour.y <= max-1)
				return retour;
			else
				throw new Exception();
		}
		catch(Exception e)
		{
			return ChoixCoordonnee(debut, fin, min, max, "Réponse non valide, veuillez répondre à nouveau.");
		}
	}
	
	public static Coordonnee ChoixCoordonneeParmiListe(ArrayList<Coordonnee> coordonnees, char debut, char fin, int min, int max, String message)
	{
		Coordonnee retour = ChoixCoordonnee(debut, fin, min, max, message);
		while(!coordonnees.contains(retour))
		{
			retour = ChoixCoordonnee(debut, fin, min, max, "Cette case ne fait pas partie des choix possibles. Veuillez réessayer.");
		}
		
		return retour;
	}
}
