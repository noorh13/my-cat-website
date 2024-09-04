import java.util.Random;
import java.util.Scanner;
public class PokemonBattle{
	public static void main (String[] args){
	Random rand= new Random();
	Scanner scanner= new Scanner(System.in);
	int rivalHealth=(int)(Math.random()*20)+40;
	double newrivalHealth=(rivalHealth/0.00100)+1;
	double dounewrivalHealth=newrivalHealth;
	dounewrivalHealth/=1000;
	System.out.print("Enter your Pokemon's nickname:");
	String urPokemon=scanner.nextLine().trim();
	System.out.print("Enter your rival Pokemon’s nickname:");
	String rivalPokemon=scanner.nextLine().trim();
	System.out.print("Your rival has chosen " + rivalPokemon + 
		" to fight, which has "); System.out.printf("%.2f",dounewrivalHealth); 
	System.out.println(" health.");
	int numofattacks=0;
	double drivalHealth=rivalHealth;
	do {
		AttackType attack = AttackType.values()[rand.nextInt(3)];
		if (attack.compareTo(AttackType.SCRATCH)==0){
			int numofscratch=(int)(Math.random()*3)+1;
			double damofscratch=(Math.random()*5.0)+1.0;
			double totaldamage= numofscratch*damofscratch;
			drivalHealth= drivalHealth-(totaldamage);
;			numofattacks+=1;
			if (drivalHealth>=0.00) {System.out.print(urPokemon + " used " + attack + " and did " );
			System.out.printf("%.2f",totaldamage); System.out.print( " damage. Your rival has "); 
			System.out.printf("%.2f",drivalHealth); System.out.println(" health remaining." );}else{
			System.out.print(urPokemon + " used " + attack + " and did " );
			System.out.printf("%.2f",totaldamage); System.out.print( " damage. Your rival has "); 
			System.out.print(0.00); System.out.println(" health remaining." );}
		}
		if (attack.compareTo(AttackType.SURF)==0){
			double damofsurf=(Math.random()*9.0)+2.0;
			drivalHealth= drivalHealth-damofsurf;
			numofattacks+=1;
			if (drivalHealth>=0.00) {System.out.print(urPokemon + " used " + attack + " and did " );
			System.out.printf("%.2f",damofsurf); System.out.print( " damage. Your rival has "); 
			System.out.printf("%.2f",drivalHealth); System.out.println(" health remaining." );}else{
			System.out.print(urPokemon + " used " + attack + " and did " );
			System.out.printf("%.2f",damofsurf); System.out.print( " damage. Your rival has "); 
			System.out.print(0.00); System.out.println(" health remaining." );	}}
		if (attack.compareTo(AttackType.TACKLE)==0){
			double damoftackle=(Math.random()*2.0)+7.0;
			drivalHealth= drivalHealth-damoftackle;
			numofattacks+=1;
			if (drivalHealth>=0.00) {System.out.print(urPokemon + " used " + attack + " and did " );
			System.out.printf("%.2f",damoftackle); System.out.print( " damage. Your rival has "); 
			System.out.printf("%.2f",drivalHealth); System.out.println(" health remaining." );}else{
			System.out.print(urPokemon + " used " + attack + " and did " );
			System.out.printf("%.2f",damoftackle); System.out.print( " damage. Your rival has "); 
			System.out.print(0.00); System.out.println(" health remaining." );	}}
		}while (drivalHealth>0.00);
	System.out.println(rivalPokemon + " fainted after " + numofattacks + " turns!");
	double money = 2400.0 - Math.random() * (2400.0 - 1200.0);
	System.out.print( "You have earned $"); System.out.printf("%.2f", money); System.out.print("!");


	}
}