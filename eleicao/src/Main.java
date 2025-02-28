import java.io.IOException;
public class Main {
    public static void main(String[] args) {
  
        if(args.length<4){
            System.out.println("ERRO");
            return;
        }
        
        Votacao eleicao = null;
        
        try{
        eleicao = Leitor.leCandidatos(args[0], args[1], args[3]);
        Leitor.leVotacao(args[2], args[0], eleicao);
        } 
        catch (IOException e){
            System.err.println("Erro ao processar arquivos: " + e.getMessage());
            e.printStackTrace();
            return;
        }
        
        Relatorio relatorio = new Relatorio(eleicao);
        relatorio.geraTodosOsRelatorios();
    }         
        
}
