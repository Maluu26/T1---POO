import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public class Partido {
    private String nome, sigla;
    private int numPartido, qtdVotosLegenda;
    private LinkedList<Candidato> candidatos;

    public Partido(String nome, String sigla, int numPartido){
        this.nome = nome;
        this.sigla = sigla;
        this.numPartido = numPartido;
        this.qtdVotosLegenda = 0;
        this.candidatos = new LinkedList<>();
    }

    public void adicionaCandidato(Candidato c){
        this.candidatos.add(c);
    }

    public void incrementaVotosLegenda(int votos){
        this.qtdVotosLegenda += votos;
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
        return this.candidatos.size();
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

    public int getQtdEleitosNoPartido(){
        int total = 0;
        for(Candidato c: this.candidatos){
            if(c.foiEleito()){
                total++;
            }
        }
        return total;
    }

    public LinkedList<Candidato> getCandidatos(){
        return new LinkedList<>(this.candidatos);
    }

    public LinkedList<Candidato> getCandidatosOrdenados(){
        LinkedList<Candidato> ordenados = new LinkedList<>(this.candidatos);
        Collections.sort(ordenados, new CandidatoComparator());
        return ordenados;
    }
   

}
