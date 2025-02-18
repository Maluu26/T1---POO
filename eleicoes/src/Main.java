
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws Exception {
        /*if(args.length<5){
            System.out.println("ERRO");
        }*/
        
        String code = "01473";
        FileInputStream fin = new FileInputStream("candidatos.csv");
        InputStreamReader r = new InputStreamReader(fin, "ISO-8859-1");
        BufferedReader br = new BufferedReader(r);
        String linha = br.readLine();
        linha = br.readLine();
        int i = 0, numEleitos = 0;
        LinkedList <Candidato> candidatos = new LinkedList<>();
        HashMap <Integer,Partido> partidos = new HashMap<>();
       
        int invalidos = 0;
        while(linha!=null) {
            
            Scanner scanner = new Scanner(linha).useDelimiter(";");    
            i = 0;
            String palavra = "", nomeUrnaCand = "", siglaPart="", dataNasc = ""; 
            int num = 0;
            int numCand=0,numPart=0, numFed=0, gen=0, eleito=0;
            // 16 numCand,18 indx nomeCandidatoUrna numPart 25 siglaPart 26 nrFed 27 dataNasc 36 gen 38 eleito? 49



            while(i<50 && scanner.hasNext()){
                if(scanner.hasNextInt()){
                    num = scanner.nextInt();
                    if(num != 13 && i == 13){
                        invalidos++;
                        break;
                    } 
                    if(i==16){
                        numCand = num;
                    }
                    else if(i==25){
                        numPart = num;
                    }
                    else if(i==28){
                        numFed = num;
                    }
                    else if(i==38){
                        gen = num;
                    }
                    else if(i==48){
                        eleito = num;
                        if(num == 3 || num == 2) numEleitos++;
                    }
                }else{
                    palavra = scanner.next();
                    palavra = palavra.substring(1,palavra.length()-1);
                    System.out.println(i + ":" + palavra);
                    if(!palavra.equals(code) && i ==11){
                        break;
                    }
                    else if(i== 18){
                        nomeUrnaCand = palavra;
                    }else if(i==26){
                        siglaPart = palavra;
                    }else if(i==36){
                        dataNasc =palavra;
                    }
                }
                if(i==48 && num == -1){ 
                    invalidos++;
                    break;}
                else if(i==48){
                    /*Partido p;
                    if(partidos.containsKey(numPart)){
                        p = partidos.get(numPart);
                    }else{
                        p = new Partido(siglaPart, numPart);
                        partidos.put(numPart, p);
                    }*/ 
                    Candidato c = new Candidato(nomeUrnaCand,numCand, numFed, eleito, gen, null, null);
                    candidatos.addLast(c);
                }

                
                i= i+1;
            }
            
            scanner.close();
            linha = br.readLine();
            }
          br.close();
          fin.close();
          System.out.println("Eleitos   " + numEleitos);
        
          LinkedList <Candidato> eleitos = new LinkedList<>();
          for(Candidato c: candidatos){
            if(c.foiEleito()) eleitos.add(c);}
          for(Candidato c: eleitos){   
            System.out.println(c);
        }
           
        //fazer comparator;




        }  
        
        
        
    }



