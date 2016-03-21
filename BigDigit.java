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
		String[] dividend = digit1.split("");
        String[] divisor = digit2.split("");
        ArrayList<String> sum = new ArrayList<>() ;
        ArrayList<String> minus = new ArrayList<>() ;
        ArrayList<String> multi = new ArrayList<>() ;
		ArrayList<String> divid = new ArrayList<>() ;
        ArrayList<String> quotient = new ArrayList<>() ;
        //change digits of number1
        for(int i = 0 ; i < digit1.length()/2;i++){
            digitChange = number1[i];
            number1[i] = number1[digit1.length()-1-i] ;
            number1[digit1.length()-1-i] = digitChange;
        }
		//print number1 after changing
        /*for(int i = 0 ; i < digit1.length();i++){
            System.out.print(number1[i]);
        }
        System.out.println();*/
		//change digits of number2
		
        for(int i = 0 ; i < digit2.length()/2;i++){
            digitChange = number2[i];
            number2[i] = number2[digit2.length()-1-i] ;
            number2[digit2.length()-1-i] = digitChange;
        }
		//print number2 after changing
        /*for(int i = 0 ; i < digit2.length();i++){
            System.out.print(number2[i]);
        }
        System.out.println();*/
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
        for(int i = 0 ; i < digit2.length() ; i++){
            divid.add(dividend[i]);
        }
        int beforeLocation = 0;        
        quo = 0 ;
        compare = 0;
        lastLocation = 0;
        j = 0;       
		checkFirst0 = 0;
        boolean compareOrNot = false ;
        if(divid.size() == digit2.length()){
            compare = 0;
            while(Integer.parseInt(divid.get(compare)) == Integer.parseInt(divisor[compare]) && compare < divid.size()-1){
                compare++;
				compareOrNot = true ;
            }
        }
        while( beforeLocation < dividend.length - digit2.length() + 1  ){
            
            while(divid.size()  > digit2.length() || (divid.size() == digit2.length() && Integer.parseInt(divid.get(compare)) >= Integer.parseInt(divisor[compare]))) {
            
                for(int k = 1;k <= divisor.length ; k++ ){
                
                    lastLocation = lastLocation(Integer.parseInt(dividend[divisor.length+beforeLocation-k]),Integer.parseInt(divisor[divisor.length-k]));
                    //System.out.println(dividend[divisor.length+beforeLocation-k]);
                    dividend[divisor.length+beforeLocation-k] = Integer.toString(minusDigit(Integer.parseInt(dividend[divisor.length+beforeLocation-k]),Integer.parseInt(divisor[divisor.length-k])));
                    //System.out.println(dividend[divisor.length+beforeLocation-k]);
                    if(lastLocation == 1 && Integer.parseInt(divid.get(0)) > 0  ){
                        if(Integer.parseInt(dividend[divisor.length+beforeLocation-k-1]) !=0){
                            if(Integer.parseInt(dividend[divisor.length+beforeLocation-k-1]) == 1 ){
                                divid.set(0 , "0");
                            }
                            dividend[divisor.length+beforeLocation-k-1] = Integer.toString(Integer.parseInt(dividend[divisor.length+beforeLocation-k-1]) -lastLocation);
                        
                        }else{
                            while(Integer.parseInt(dividend[divisor.length+beforeLocation-k-j-1]) == 0 && divisor.length+beforeLocation-k-j-1 > 0){
                                dividend[divisor.length+beforeLocation-k-j-1] = "9";
                                j++;
                            }
							if(Integer.parseInt(dividend[divisor.length+beforeLocation-k-j-1]) == 1 ){
                                divid.set(0 , "0");
                            }
                            dividend[divisor.length+beforeLocation-k-j-1] = Integer.toString(Integer.parseInt(dividend[divisor.length+beforeLocation-k-j-1]) -lastLocation);
                            
                        }
                        j = 0 ;
                    }
                
                }  
                if(Integer.parseInt(divid.get(0)) == 0 && divid.size() > divisor.length ){
                    divid.remove(checkFirst0);
                
                }
				if(divid.size() > divisor.length ){
					for(int set = 1; set < divisor.length + 1; set++ ){
						divid.set(set,dividend[set + beforeLocation - 1]);
                    }
				}else{
					for(int set = 0; set < divisor.length; set++ ){
						divid.set(set,dividend[set + beforeLocation]);
                    }
                }
                checkFirst0 = 0;
                while(Integer.parseInt(divid.get(checkFirst0)) == 0 && divid.size() > 1 ){
                    divid.remove(checkFirst0);
                
                }           
            
            
                if(divid.size() == digit2.length()){
                    compare = 0;
                    while(Integer.parseInt(divid.get(compare)) == Integer.parseInt(divisor[compare]) && compare < divid.size()-1){
                        compare++;
                        compareOrNot = true ; 
                    }
                }
                quo++;
            
            }
            if(compareOrNot == true){
                compare = 0 ;
                compareOrNot = false ;
            }
        
        
        
            quotient.add(Integer.toString(quo));
            if(digit2.length()+beforeLocation < dividend.length){
                divid.add(dividend[digit2.length()+beforeLocation]);              
            }
            beforeLocation++;
            while(Integer.parseInt(divid.get(checkFirst0)) == 0 && divid.size() > 1 ){
                    divid.remove(checkFirst0);                
            }           
            
            quo = 0 ;
        
        }
		while(Integer.parseInt(quotient.get(checkFirst0)) == 0 && divid.size() > 1 ){
                    quotient.remove(checkFirst0);                
        }
        System.out.print("The QUOTIENT result is ");
        for(int i = 0 ; i < quotient.size() ; i++){
            System.out.print(quotient.get(i));
        }
        System.out.print(", and REMAINDER is ");
        for(int i = 0 ; i < divid.size() ; i++){
            System.out.print(divid.get(i));
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

