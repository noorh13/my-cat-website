public class ShoppingMall{
	//My name is Noor Hasan and a fun fact is that I speak three languages. 
	public static void main (String[] args){
	int cash=100;
	double taxRate=1.13;
	double subtotal=58.62;
	String name="Noor";
	double change= cash - subtotal * taxRate;
	change/=0.0100;
	int newchange= (int) change;
	double changeTrunc=newchange;
	changeTrunc/=100;
	System.out.println(name + " has $" + changeTrunc + " dollars remaining!\n" 
		+ name + " started with $" + cash + " dollars!");
	}
}