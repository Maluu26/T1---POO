import java.time.LocalDate;
public class Candidato{
    private String nome;
    private int numUrna, numCandidato, codCargo, numFederacao, foiEleito, gen, numVotos;
    private LocalDate nasc;
    private Partido partido;
    
    
    public Candidato(String nome, int numUrna, int numCandidato, int codCargo, int numFederacao, int foiEleito, int gen,
            int numVotos, LocalDate nasc, Partido partido) {
        this.nome = nome;
        this.numUrna = numUrna;
        this.numCandidato = numCandidato;
        this.codCargo = codCargo;
        this.numFederacao = numFederacao;
        this.foiEleito = foiEleito;
        this.gen = gen;
        this.numVotos = numVotos;
        this.nasc = nasc;
        this.partido = partido;
    }
    

}