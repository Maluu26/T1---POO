
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
        int i = 0, numEleitos = 0;
        
        /*MARCELA: não precisa mais dessas 2 linhas aqui né? já que vamos mandar tudo para dentro de Votacao */ ///
        HashMap <Integer,Partido> partidos = new HashMap<>();   ///
        Votacao eleicao = new Votacao();
        while(linha!=null) {
            
            Scanner scanner = new Scanner(linha).useDelimiter(";");    
            i = 0;
            String palavra = "", nomeUrnaCand = "", siglaPart="",
            dataNasc = "", nomePart = "";   
            int num = 0,  numCand=0,numPart=0, numFed=0, gen=0, eleito=0;

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
                    Candidato c = new Candidato(nomeUrnaCand,numCand, numFed, eleito, gen, null, p);
                    eleicao.atualizaVotacao(c, p, numPart);
                    
                    /*  MARCELA: e se a gente fizer assim:
                        1- Criar o partido p (ai ele so vai entrar na Votacao v se ainda não existir, 
                            mas essa verificação não precisa ser feita aqui, pois já é feita automaticamente na hora de inserir com putIfAbsent());
                        2- criar o candidato c(adicionando o partido ao qual c pertence);
                        3- adicionar c à lista de candidatos do partido p;
                        4- colocar c e p dentro de Votacao v;
                    */
                }        
                i+=1;
            }     
            scanner.close();
            linha = br.readLine();
            }
          br.close();
          fin.close();
          System.out.println("Eleitos   " + numEleitos);
           
        //fazer comparator;
        }         
        
    }