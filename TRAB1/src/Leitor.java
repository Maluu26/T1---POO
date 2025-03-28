import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Leitor {
    
    /**
     * A função espera receber os valores não numéricos do arquivo csv entre aspas duplas, caso haja necessidade de 
     * conversão para inteiro, a própria função irá tratar disso.
     */
    public static Votacao leCandidatos(String codVotacao,String caminho,String dataEleicao) throws IOException{
    
        int numCod =Integer.parseInt(codVotacao);
        FileInputStream fin = new FileInputStream(caminho);
        InputStreamReader r = new InputStreamReader(fin, "ISO-8859-1");
        BufferedReader br = new BufferedReader(r);
        String linha = br.readLine();
        linha = br.readLine();
        String palavra = "", nomeUrnaCand = "", siglaPart="", dataNasc = "", nomePart = "";   
        int i = 0,num = 0, cargo = 0, numCand=0, numPart=0, numFed=0, gen=0, eleito=0, numUrna = 0, codigo = 0;

        Votacao eleicao = new Votacao();
        eleicao.setDataEleicao(dataEleicao);
        while(linha!=null) {
                
            Scanner scanner = new Scanner(linha).useDelimiter(";");    
            i = 0;
            while(i<50 && scanner.hasNext()){
                if(scanner.hasNextInt()){
                        
                    num = scanner.nextInt();
                    if(i == 13)cargo = num; 
                    if(i==16) numCand = num;
                    if(i==25)numPart = num;
                    if(i==28) numFed = num;
                    if(i==38)gen = num;
                    else if(i==48) eleito = num;

                }else{
                    palavra = scanner.next();
                    palavra = palavra.substring(1,palavra.length()-1);

                    if(i ==11) codigo = Integer.parseInt(palavra);
                    if(i== 18) nomeUrnaCand = palavra;
                    if(i==26) siglaPart = palavra;
                    if(i==27){ 
                        nomePart = palavra;
                        Partido p = null;
                        if(!eleicao.contemPartido(numUrna)){

                            p = new Partido(nomePart,siglaPart, numPart);
                            eleicao.atualizaVotacaoPartido(p, numPart);
                        }
                        if(codigo!=numCod || cargo != 13) break;
                    }
                    if(i==36) dataNasc =palavra;
                        
                }
                    
                if(i==48 && num == -1) break;
                else if(i==48){
                    Partido p = eleicao.getPartidoKey(numPart);
                    Candidato c = new Candidato(nomeUrnaCand,numCand, numFed, eleito, gen, dataNasc, p);
                    eleicao.atualizaVotacaoCandidato(c);
                    p.adicionaCandidato(c);
                        
                }        
                i+=1;
            }     
            scanner.close();
            linha = br.readLine();
        }
        br.close();
        fin.close(); 
        return eleicao;
    }
      
    /**
     * A função espera receber os valores não numéricos do arquivo csv entre aspas duplas, caso haja necessidade de 
     * conversão para inteiro, a própria função irá tratar disso.
     */
    public static void leVotacao(String caminho, String codigoCidade, Votacao eleicao)throws IOException {

        FileInputStream vot = new FileInputStream(caminho);
        InputStreamReader rVot = new InputStreamReader(vot, "ISO-8859-1");
        BufferedReader brVot = new BufferedReader(rVot);
        String linhaVot = brVot.readLine();
        linhaVot = brVot.readLine();
        int numCod = Integer.parseInt(codigoCidade);
        String palavra = "", nomePart = "";   
        int i = 0,num = 0, numUrna = 0;
        while(linhaVot!=null) {
            
            Scanner scanner = new Scanner(linhaVot).useDelimiter(";");    
            i = 0;

            while(i<22 && scanner.hasNext()){
                if(scanner.hasNextInt()){                
                    
                    num = scanner.nextInt();
                    if(num != 13 && i == 17) break; 
                    if(i==19 && (num>=95 && num<=98)) break;
                    if(i==19) numUrna = num;     
                    if(i==21) eleicao.apuraVotos(numUrna, num,nomePart);
                
                }else{

                    palavra = scanner.next();
                    palavra = palavra.substring(1,palavra.length()-1);
                    if(i ==11){
                        num = Integer.parseInt(palavra);
                        if(numCod != num)break;
                    } 
                    if(i == 20) nomePart = palavra; 
                    
                }
                i+=1;
            }     
            scanner.close();
            linhaVot = brVot.readLine();
        }
        brVot.close();
        vot.close();
        eleicao.ordenaCandidatos();

    }

}