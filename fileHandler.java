package com.mycompany.interview;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Counter{
    static int numberOfWords=0;           // words of the text file
    static int numberOfParagraphs=0;      // paragraphs of the text file
    static int numberOfSentences=0;       // sentences of the text file.
    static int phrases=0;                 // total number of double quotation marks in text file
    static int[] arr=new int[5];
    
    static int[] count(String string){
        
        int temp=0;
        if (string.length()!=0){
            
            if(string.charAt(string.length()-1)==' '){          // If last element space it is removed. 
                
                string=string.substring(0, string.length()-1);
            }
            numberOfSentences++;                                /* last sentence count is taken in this line. sometimes there is
                                                                   not any marks end of the paragraphs. in here it can be avoided. */ 
            numberOfWords++;                                    // word count is taken by using spaces. So, number of words should be "spaces+1" additional one is taken
        }  
        
        char[] chr=string.toCharArray();                        //string paragraph convert into characters.
        
        for(int i=0;i<chr.length-1;i++){
            if(chr[i]==' ' & chr[i+1]!=' '){                    // words are count by using spaces. if there are more than one spaces are in nearly, those only count one space.
                numberOfWords++;
            }else if((chr[i]=='?'|chr[i]=='.'|chr[i]=='!'|chr[i]==';')& chr[i+1]==' '){        // consider those are the end elements of the sentences.
                if(i-2>=0){                                                                    // If name with initials, there are '.' marks in those areas. That counts should be avoided.
                    if(chr[i-2]!=' '){ 
                    numberOfSentences++;
                    temp++;
                    }
                }
                               
            }else if(chr[i]=='"'){                              // count total number of double quotation marks. 
                phrases++;
            }
        }
        if(temp>1){                                             // Paragraph should be include more than one sentences.
            numberOfParagraphs++;                               // paragraph count is take in this line
        }
        arr[0]=numberOfWords;
        arr[1]=numberOfParagraphs;
        arr[2]=numberOfSentences;
        arr[3]=phrases/2;
        if(numberOfSentences==0){
            arr[4]=0;
        }else{
            arr[4]=numberOfWords/numberOfSentences;
        }
        return arr;
    }    
}
public class fileHandler {
    public static void main(String [] args) throws FileNotFoundException{
        if (args.length<1){                                                     //set text file as arguments.
            
            System.out.println("Please provide text file name with absolute path as an input: ");
            return;
        }
        File file= new File(args[0]);
        Scanner scan=null;
        try{
            scan = new Scanner(file);
            int[] arr=new int[4];
            while (scan.hasNextLine()){
                arr=Counter.count(scan.nextLine());            
            }
            System.out.println("Number of Worlds: "+arr[0]); 
            System.out.println("Number of Sentences: "+arr[2]);
            System.out.println("Number of Paragraphs: "+arr[1]);
            System.out.println("Average words in Sentences: "+(arr[4]));
            System.out.println("Number of Phrases: "+(arr[3]));
            
        }catch(FileNotFoundException fileNotFoundException){
            System.out.println("The provided file "+file+" not found");         // If couldn't find text file this error message will display.
            
        }catch(Exception e){
            System.out.println("Exception is thrown from programme. Error: "+ e.getMessage());      
                                                                                
                                                                                // If there is any programme error error message and these texts will printed.
            
        }finally{
            if(scan!=null){
                scan.close();               // close the scanner
            }
        }
        
    }
}
