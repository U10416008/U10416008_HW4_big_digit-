//U10416008丁杰

//package bigdigit;
import java.util.*;


public class BigDigit {

    public static void main(String[] args) {
        int nextLocation = 0;
		int lastLocation = 0;
        int j = 0;
        String digitChange;
        String numberChange;
        Scanner input = new Scanner(System.in);
        String digit1 = input.nextLine();
        String digit2 = input.nextLine();
        if(digit1.length() < digit2.length()){
            numberChange = digit1;
            digit1 = digit2;
            digit2 = numberChange;
        }
		if(digit1.length() == digit2.length() && Integer.parseInt(digit1.substring(0,1))<Integer.parseInt(digit2.substring(0,1))){
            numberChange = digit1;
            digit1 = digit2;
            digit2 = numberChange;
        }
        String[] number1 = digit1.split("");
        String[] number2 = digit2.split("");
        int length = digit1.length();
        ArrayList<String> sum = new ArrayList<>() ;
		ArrayList<String> minus = new ArrayList<>() ;
        //change digits of number1  
        for(int i = 0 ; i < digit1.length()/2;i++){
            digitChange = number1[i];
            number1[i] = number1[digit1.length()-1-i] ;
            number1[digit1.length()-1-i] = digitChange;
        }
		//print number1 after changing
        for(int i = 0 ; i < digit1.length();i++){
            System.out.print(number1[i]);
        }
        System.out.println();
		//change digits of number2  
        for(int i = 0 ; i < digit2.length()/2;i++){
            digitChange = number2[i];
            number2[i] = number2[digit2.length()-1-i] ;
            number2[digit2.length()-1-i] = digitChange;
        }
		//print number2 after changing
        for(int i = 0 ; i < digit2.length();i++){
            System.out.print(number2[i]);
        }
        System.out.println();
        //add every digits 
        for(int i = 0 ; i < digit2.length();i++){
            sum.add(Integer.toString(addDigit(Integer.parseInt(number1[i]),Integer.parseInt(number2[i]),nextLocation)));
            nextLocation = nextLocation(Integer.parseInt(number1[i]),Integer.parseInt(number2[i]),nextLocation);
        }
        if(digit2.length() != digit1.length()){
            for(int i = digit2.length(); i < digit1.length() ; i++){
                sum.add(Integer.toString(addDigit(Integer.parseInt(number1[i]),nextLocation)));
                nextLocation = nextLocation(Integer.parseInt(number1[i]),nextLocation);
            }
           
        }
		
		if(nextLocation == 1){
            sum.add("1");
        }
		
		//print the sum 
		
		System.out.print("The ADD result is ");
        for(int i = sum.size()-1 ; i>=0 ;i--){
            System.out.print(sum.get(i));
        }
		System.out.println();
        for(int i = 0 ; i < digit2.length();i++){
            minus.add(Integer.toString(minusDigit(Integer.parseInt(number1[i]),Integer.parseInt(number2[i]))));
            lastLocation = lastLocation(Integer.parseInt(number1[i]),Integer.parseInt(number2[i]));
            if(lastLocation == 1 ){
                if(Integer.parseInt(number1[i+1]) !=0){
                    number1[i+1] = Integer.toString(Integer.parseInt(number1[i+1]) -lastLocation);
                }else{
                    while(Integer.parseInt(number1[i+j+1]) == 0){
                        number1[i+j+1] = "9";
                        j++;
                    }
                    number1[i+j+1] = Integer.toString(Integer.parseInt(number1[i+j+1]) -lastLocation);
                }
                
            }
            j = 0;
        }
        if(digit2.length() != digit1.length()){
            for(int i = digit2.length(); i < digit1.length() ; i++){
                minus.add(Integer.toString(minusDigit(Integer.parseInt(number1[i]))));
                lastLocation = lastLocation(Integer.parseInt(number1[i]),lastLocation);
            }
        }
        int checkFirst0 = minus.size()-1;
        while(Integer.parseInt(minus.get(checkFirst0)) == 0){
            minus.remove(checkFirst0);
            checkFirst0--;
        }
        System.out.print("The MINUS result is ");
        for(int i = minus.size()-1 ; i>=0 ;i--){
            System.out.print(minus.get(i));
        }
        
        
    }
	//check number1 + number2 + nextLocation is >=10 or not 
    public static int nextLocation(int number1,int number2,int nextLocation){
        if(number1+ number2+nextLocation >=10){
            return 1;
        }else{
            return 0;
        }
    }
	//check number1 + nextLocation is >=10 or not 
    public static int nextLocation(int number1,int nextLocation){
        if(number1 + nextLocation >=10){
            return 1;
        }else{
            return 0;
        }
    }
	// return the first digit 
    public static int addDigit(int number1,int number2,int nextLocation){
        if(number1 + number2 + nextLocation >=10) {
            return (number1 + number2 + nextLocation)%10;
        }else{
            return number1 + number2 + nextLocation;
        }
            
    }
	// return the first digit 
    public static int addDigit(int number1,int nextLocation){
        if(number1 +  nextLocation >=10) {
            return (number1  + nextLocation)%10;
        }else{
            return number1  + nextLocation;
        }
    }
	    public static int lastLocation(int number1,int number2){
        if(number1 < number2){
            return 1;
        }else{
            return 0;
        }
    }
    public static int lastLocation(int number1){
        if(number1 < 0){
            return 1;
        }else{
            return 0;
        }
    }
    public static int minusDigit(int number1,int number2){
        if(number1  < number2) {
            return 10 + number1 - number2 ;
        }else{
            return number1 - number2;
        }
            
    }
    public static int minusDigit(int number1){
        if(number1  <0 ) {
            return 10 + number1 ;
        }else{
            return number1 ;
        }
    }
}
