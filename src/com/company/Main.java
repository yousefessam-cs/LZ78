
package com.company;

import java.util.*;

public class Main {

    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<com.company.MAP> arraymap = new ArrayList<>();
    public static ArrayList<String> dictionary =new ArrayList<>();


    public static void main(String[] args) {
        final String input ;
        input = scanner.nextLine();
        dictionary.add("");
        boolean success = compress(input) ;
        if(success)
            System.out.println(decompress(arraymap));
        for (com.company.MAP m : arraymap) {
            System.out.println("<" + m.getIndex() + "," + m.getSymbol()+ ">\n");
        }

        System.out.println("Printing  Dictionary: \n");

        for (int i = 0; i < dictionary.size(); i++) {
            System.out.println(" | " + i + " | " + " " + dictionary.get(i));
        }

    }

    private static String decompress(ArrayList<com.company.MAP> arraymap) {
        String originalMessage ="";
        for (com.company.MAP m : arraymap) {
            originalMessage+= dictionary.get(m.getIndex());
            if(!m.getSymbol().equals("null") )
                originalMessage+= m.getSymbol() ;
        }
        return originalMessage ;
    }

    private static boolean compress(String input){
        int numberOfTags=0;
        boolean found ;
        for(int i = 0 ; i < input.length() ; i++ ){
            com.company.MAP m = new com.company.MAP() ;
            String symb = input.charAt(i) + "" ;
            int index = dictionary.indexOf(symb); // this is for get index from dictionary
            if(index == -1 )
                found  = false ;
            else
                found  = true ;
            // if this symbol isnot exist in the dictionary
            if(!found){
                dictionary.add(symb+"");
                m.setSymbol(symb);
                m.setIndex(0);
                arraymap.add(m);
            }
            else{
                boolean isSaved = false ;  // if it saved before in the dictionary
                while (i+1 < input.length()){
                    int updatedindex = dictionary.indexOf(symb) ;
                    symb+= input.charAt(++i) ;
                    if(dictionary.indexOf(symb)!= -1 ){
                        updatedindex = dictionary.indexOf(symb); // da el index bta3 a5r symbol mwgod fel dictionary y3ny el next symbol hayb2a b null
                    }
                    else{
                        char nextsymb = symb.charAt(symb.length()-1);  // for entering the unique 2 char
                        isSaved = true ;
                        m.setIndex(updatedindex);
                        m.setSymbol(nextsymb+"");
                        arraymap.add(m) ;
                        dictionary.add(symb) ;
                        break ;
                    }
                }
                if(!isSaved) {    //a5r 7rf
                    m.setIndex(dictionary.indexOf(symb));
                    m.setSymbol("null");
                    arraymap.add(m) ;
                }
            }
            numberOfTags++ ;
        }
        return true ;
    }
}