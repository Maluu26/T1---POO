import java.io.IOException;
public class Main {
    public static void main(String[] args) throws IOException {
        if(args.length<4){
            System.out.println("ERRO");
        }
        Votacao eleicao = Leitor.leCandidatos(args[0], args[1], args[3]);
        Leitor.leVotacao(args[2], args[0], eleicao);
        Relatorio relatorio = new Relatorio(eleicao);
        relatorio.geraTodosOsRelatorios();
        //MALU: FAZER TRATAMENTOS DE EXCEÇÕES DE LEITURAS DE ARQUIVO, E COISAS D AVOTACA
    }         
        
}
