
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws IOException {
        /*if(args.length<5){
            System.out.println("ERRO");
        }*/
        
        String code = "01473";
        FileInputStream fin = new FileInputStream("candidatos.csv");
        InputStreamReader r = new InputStreamReader(fin, "ISO-8859-1");
        BufferedReader br = new BufferedReader(r);
        String linha = br.readLine();
        linha = br.readLine();
        
        int i = 0, numEleitos = 0, numVotos = 0;
        String palavra = "", nomeUrnaCand = "", siglaPart="", dataNasc = "", nomePart = "";   
        int num = 0, numCand=0, numPart=0, numFed=0, gen=0, eleito=0, numUrna = 0;

        HashMap <Integer,Partido> partidos = new HashMap<>();  
        Votacao eleicao = new Votacao();
        while(linha!=null) {
            
            Scanner scanner = new Scanner(linha).useDelimiter(";");    
            i = 0;
            while(i<50 && scanner.hasNext()){
                if(scanner.hasNextInt()){
                    
                    num = scanner.nextInt();
                    if(num != 13 && i == 13)break; 
                    if(i==16) numCand = num;
                    if(i==25)numPart = num;
                    if(i==28) numFed = num;
                    if(i==38)gen = num;
                    else if(i==48){
                        eleito = num;
                        if(num == 3 || num == 2) numEleitos++;
                    }

                }else{
                    palavra = scanner.next();
                    palavra = palavra.substring(1,palavra.length()-1);

                    if(!palavra.equals(code) && i ==11) break; 
                    if(i== 18) nomeUrnaCand = palavra;
                    if(i==26) siglaPart = palavra;
                    if(i==27) nomePart = palavra; 
                    if(i==36) dataNasc =palavra;
                    
                }
                
                if(i==48 && num == -1) break;
                else if(i==48){
                    Partido p;
                    if(partidos.containsKey(numPart)) p = partidos.get(numPart);
                    else{
                        p = new Partido(nomePart,siglaPart, numPart);
                        partidos.put(numPart, p);
                    }
                    //Candidato c = new Candidato(nomeUrnaCand,numCand, numFed, eleito, gen, null, p);
                    Candidato c = new Candidato(nomeUrnaCand,numCand, numFed, eleito, gen, dataNasc, p);
                    eleicao.atualizaVotacao(c, p, numPart);
                    
                }        
                i+=1;
            }     
            scanner.close();
            linha = br.readLine();
            }
          br.close();
          fin.close();


        FileInputStream vot = new FileInputStream("votacao.csv");
        InputStreamReader rVot = new InputStreamReader(vot, "ISO-8859-1");
        BufferedReader brVot = new BufferedReader(rVot);
        String linhaVot = brVot.readLine();
        linhaVot = brVot.readLine();
        i = 0;
        int flag=0;
        
        while(linhaVot!=null) {
            
            Scanner scanner = new Scanner(linhaVot).useDelimiter(";");    
            i = 0;

            while(i<22 && scanner.hasNext()){
                if(scanner.hasNextInt()){                
                    
                    num = scanner.nextInt();
                    if(num != 13 && i == 17) break; 
                    if(i==19 && (num>=95 && num<=98)) break;
                    if(i==19) numUrna = num;     
                    if(i==21) eleicao.apuraVotos(numUrna, num);
                
                }else{

                    palavra = scanner.next();
                    palavra = palavra.substring(1,palavra.length()-1);
                    if(!palavra.equals(code) && i ==11) break; 
                    if(i==8 && flag==0) eleicao.setDataEleicao(palavra); flag=1;
                    
                }
                i+=1;
            }     
            scanner.close();
            linhaVot = brVot.readLine();
            }
          brVot.close();
          vot.close();
    
        //tem um metodo pra pegar a qtd de eleitos em Votacao, ent acho q essa variavel nem vai precisar
        System.out.println("Eleitos   " + numEleitos);
           
        //fazer comparator;
        }         
        
    }