import java.util.LinkedList;

public class Partido {
    private String nome, sigla;
    private int numPartido, qtdVotosLegenda, qtdVotosNominais, qtdCandidatos;
    private LinkedList<Candidato> candidatos;

    public Partido(String nome, String sigla, int numPartido){
        this.nome = nome;
        this.sigla = sigla;
        this.numPartido = numPartido;
        this.qtdVotosLegenda = 0;
        this.qtdVotosNominais = 0;
        this.qtdCandidatos = 0;
        this.candidatos = new LinkedList<>();
    }

    public void adicionaCandidato(Candidato c){
        this.candidatos.add(c);
        this.qtdCandidatos++;
    }

    public void adicionaVotosNominais(int qtdVotos) {
        this.qtdVotosNominais += qtdVotos;
    }

    public void incrementaVotosLegenda(int votos){
        this.qtdVotosLegenda=+ votos;
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

    public int getQtdVotosTotais() {
        return this.qtdVotosNominais + this.qtdVotosLegenda;
    }

    public int getQtdVotosNominais() {
        return this.qtdVotosNominais;
    }

    public getQtdEleitosNoPartido(){
        int total = 0;
        for(Candidato c: this.candidatos){
            if(c.foiEleito() == true){
                total++;
            }
        }
        return total;
    }

    public LinkedList<Candidato> getCandidatos(){
        LinkedList <Candidato> copiaCandidatos = new LinkedList<>();
        copiaCandidatos.addAll(this.candidatos);
        return copiaCandidatos;
    }

    @Override
    public String toString() {
        String texto = "";
        texto += this.nome + " - " + this.numPartido + ", " + this.getQtdVotosTotais() + " votos (" +
        this.getQtdVotosNominais() + "nominais e " + this.getQtdVotosLegenda() + "de legenda), ";
        
        int qtdEleitos = this.getQtdEleitosNoPartido();
        if(qtdEleitos < 2){
            texto += "candidato eleito";
        }
        else {
            texto += "candidatos eleitos";
        }

        return texto;
    }

}
