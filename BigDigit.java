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
        int compare = 0;
        
        if(digit1.length() == digit2.length()){
            if(digit1.length()>1){
                while(Integer.parseInt(digit1.substring(compare,compare+1)) == Integer.parseInt(digit2.substring(compare,compare+1)) && compare < digit1.length()-1){
                    
                    compare++;
                }
                if(Integer.parseInt(digit1.substring(compare,compare+1)) < Integer.parseInt(digit2.substring(compare,compare+1))){
                    numberChange = digit1;
                    digit1 = digit2;
                    digit2 = numberChange;
                }
            }else{
                if(Integer.parseInt(digit1) < Integer.parseInt(digit2)){
                    numberChange = digit1;
                    digit1 = digit2;
                    digit2 = numberChange;
                }
            }
        }
        
        int length = digit1.length() + digit2.length();
        int length2 = digit1.length() - digit2.length();
        int[] addLocation = new int[length];
        int[] quoLocation = new int[length2];
        String[] number1 = digit1.split("");
        String[] number2 = digit2.split("");
        ArrayList<String> sum = new ArrayList<>() ;
        ArrayList<String> minus = new ArrayList<>() ;
        ArrayList<String> multi = new ArrayList<>() ;
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
		// cross every number and add
        for(int i = 0 ; i < digit2.length(); i++){
            for(int k = 0 ; k < digit1.length();k++){
                    addLocation[k + i +1] =  addLocation[k + i +1] +  afterLocation(Integer.parseInt(number1[k]), Integer.parseInt(number2[i]),addLocation[k + i]);
                    addLocation[k + i] = multiDigit(Integer.parseInt(number1[k]), Integer.parseInt(number2[i]),addLocation[k + i]);                         
            }
        }
        for(int i = 0 ; i < digit1.length() + digit2.length() ;i++){
            multi.add(Integer.toString(addLocation[ i ]));
        }
        int checkFirst0 = multi.size()-1;
        while(Integer.parseInt(multi.get(checkFirst0)) == 0 && checkFirst0 > 0){
            multi.remove(checkFirst0);
            checkFirst0--;
        }
		//print the multi 
        System.out.print("The MULTI result is ");
        for(int i = multi.size()-1 ; i>=0 ;i--){
            System.out.print(multi.get(i));
        }
		System.out.println();
		//minus every digits
        
        int quo = 0;
        
        for(int i = 0 ; i < number2.length;i++){
            lastLocation = lastLocation(Integer.parseInt(number1[i]),Integer.parseInt(number2[i]));
            if(Integer.parseInt(number1[number1.length-1]) == 0){
                lastLocation = 0;
            }
            number1[i] = Integer.toString(minusDigit(Integer.parseInt(number1[i]),Integer.parseInt(number2[i])));
            
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
        if(number2.length != number1.length){
            for(int i = number2.length; i < number1.length ; i++){
                lastLocation = lastLocation(Integer.parseInt(number1[i]),lastLocation);
                number1[i] = Integer.toString(minusDigit(Integer.parseInt(number1[i])));
                
            }
        }
        for(int i = 0 ; i < number1.length ; i++){
            minus.add(number1[i]);
        }
        int check0 = minus.size()-1;
        while(Integer.parseInt(minus.get(check0)) == 0 && check0 > 0){
            minus.remove(check0);
            check0--;
        }
        System.out.print("The MINUS result is ");
        
        for(int i = minus.size()-1 ; i>=0 ;i--){
            System.out.print(minus.get(i));
        }
        System.out.println();
        if(minus.size() == digit2.length()){
            compare = 1;
            while(Integer.parseInt(minus.get(minus.size()-compare)) == Integer.parseInt(number2[number2.length -compare]) && compare <= minus.size()){
                compare++;
            }
        }
        quo++;

		while((minus.size() > digit2.length() || (minus.size() == digit2.length() && Integer.parseInt(minus.get(minus.size()-compare)) >= Integer.parseInt(number2[number2.length -compare]))) ){
            for(int i = 0 ; i < number2.length;i++){
            
                lastLocation = lastLocation(Integer.parseInt(number1[i]),Integer.parseInt(number2[i]));
                if(Integer.parseInt(number1[minus.size()-1]) <= 0){
                    lastLocation = 0;
                }
                number1[i] = Integer.toString(minusDigit(Integer.parseInt(number1[i]),Integer.parseInt(number2[i])));
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
            if(number2.length != minus.size()){
                for(int i = number2.length; i < minus.size() ; i++){
                    number1[i] = Integer.toString(minusDigit(Integer.parseInt(number1[i])));
                    lastLocation = lastLocation(Integer.parseInt(number1[i]),lastLocation);
                }
            }
            for(int i = 0 ; i < minus.size() ; i++){
                minus.set(i,number1[i]);
            }
            checkFirst0 = minus.size()-1;
            while(Integer.parseInt(minus.get(checkFirst0)) == 0 && checkFirst0 > 0){
                minus.remove(checkFirst0);
                checkFirst0--;
            }
        
       
        
        
            if(minus.size() == digit2.length()){
                compare = 1;
                while(Integer.parseInt(minus.get(minus.size()-compare)) == Integer.parseInt(number2[number2.length -compare]) && compare < minus.size()){
                    compare++;
                }
            }
        
        quo++;
        }
        System.out.print("The QUOTIENT result is " + quo + ", and REMAINDER is ");
        for(int i = minus.size()-1 ; i >= 0 ; i--){
            System.out.print(minus.get(i));
        }
        System.out.println();
        
        
            
        
    }
	//check number1 + number2 + nextLocation is >=10 or not
    public static int nextLocation(int number1,int number2,int nextLocation){
        if(number1+ number2+nextLocation >=10){
            return 1;
        }else{
            return 0;
        }
    }
	//check number1 + number2 + nextLocation is >=10 or not
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
	//check number1 is  < number2   or not
    public static int lastLocation(int number1,int number2){
        if(number1 < number2){
            return 1;
        }else{
            return 0;
        }
    }
	//check number1 is  < 0   or not
    public static int lastLocation(int number1){
        if(number1 < 0){
            return 1;
        }else{
            return 0;
        }
    }
	// return the digit
    public static int minusDigit(int number1,int number2){
        if(number1  < number2) {
            return 10 + number1 - number2 ;
        }else{
            return number1 - number2;
        }
            
    }
	// return the digit
    public static int minusDigit(int number1){
        if(number1  <0 ) {
            return 10 + number1 ;
        }else{
            return number1 ;
        }
    }
	//return the next digit
    public static int afterLocation(int number1 , int number2 ,int afterLocation){
        
        return (number1 * number2 + afterLocation )/10;
        
    }
	//return the first digit
    public static int multiDigit(int number1 , int number2 , int afterLocation){
        return (number1 * number2 + afterLocation)%10;
    }
}

