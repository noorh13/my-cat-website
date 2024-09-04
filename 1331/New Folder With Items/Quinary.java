public class Quinary {
	public static void main (String[] args) {
		int initialNum= 33;
		String answer= "";
		int zeroCount= 0;
		int oneCount= 0;
		int twoCount= 0;
		int threeCount= 0;
		int fourCount= 0;
		int currentNum= initialNum;
		while (currentNum!=0){
			int digit= currentNum % 5; 
			if (digit == 0){
				zeroCount+=1;
			}
			if (digit == 1){
				oneCount+=1;
			}
			if (digit == 2){
				twoCount+=1;
			}
			if (digit ==3){
				threeCount+=1;
			}
			if (digit==4){
				fourCount+=1;
			}
			answer = digit + answer;
			currentNum = currentNum/5;
		}
		System.out.printf("Decimal representation: " + initialNum + "\n");
		System.out.printf("Quinary representation: " + answer + "\n");
		System.out.printf(zeroCount + " zero(s), " + oneCount + " one(s), " + twoCount 
			+ " two(s), " + threeCount + " three(s), " + fourCount + " four(s)" + "\n");
		int digitSum= 0*zeroCount + 1*oneCount + 2*twoCount + 3*threeCount + 4*fourCount;
		switch (digitSum %5 ){
		case 0:
			System.out.println("The Quinary digits sum to a multiple of 5!");
			break;
		case 1:
			System.out.println("The Quinary digits almost summed to a multiple 5!");
			break;
		case 4:
			System.out.println("So close!");
			break;
		default:
			System.out.println("Nope!");
		}
		String iszerobigger = (zeroCount>oneCount && zeroCount>twoCount && zeroCount>threeCount 
			&& zeroCount>fourCount) 
		? "Zero is the most used digit."
		: "Zero is not the most used digit.";
		System.out.println(iszerobigger);
	}
}