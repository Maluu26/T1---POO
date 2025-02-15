import java.util.Set;
import java.util.HashSet;

public class Partido {
    private String nome, sigla;
    private int numPartido, qtdVotosLegenda, qtdCandidatos;
    private Set<Candidato> candidatos;

    public Partido(String nome, String sigla, int numPartido){
        this.nome = nome;
        this.sigla = sigla;
        this.numPartido = numPartido;
        this.qtdVotosLegenda = 0;
        this.qtdCandidatos = 0;
        this.candidatos = new HashSet<Candidato>();
    }

    public void adicionaCandidato(Candidato c){
        this.candidatos.add(c);
        this.qtdCandidatos++;
    }

    public void incrementaVotosLegenda(){
        this.qtdVotosLegenda++;
    }

    public String getSigla() {
        return sigla;
    }

    public String getNome() {
        return nome;
    }

    public int getNumPartido(){
        return this.numPartido;
    }

    public int getQtdVotosLegenda() {
        return qtdVotosLegenda;
    }
    
    public int getQtdCandidatos() {
        return qtdCandidatos;
    }

    private int qtdVotosNominais(){
        int total = 0;
        for(Candidato c: this.candidatos){
            total += c.getNumVotos();
        }
        return total;
    }

    public int getQtdVotosTotais() {
        int total = this.qtdVotosNominais();
        return total + this.qtdVotosLegenda;
    }

    public int getQtdVotosNominais() {
        return this.qtdVotosNominais();
    }

    public getQtdEleitos(){
        int total = 0;
        for(Candidato c: this.candidatos){
            if(c.getFoiEleito() == true){
                total++;
            }
        }
        return total;
    }

    public Set<Candidato> getCandidatos(){
        Set<Candidato> copiaCandidatos = new HashSet<Candidato>();
        copiaCandidatos.addAll(this.candidatos);
        return copiaCandidatos;
    }

}
