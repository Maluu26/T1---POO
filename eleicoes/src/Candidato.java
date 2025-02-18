import java.time.LocalDate;

public class Candidato{
    


    private String nome;
    private int numUrna, numFederacao, foiEleito, gen, numVotos;
    private LocalDate nasc;
    private Partido partido;
    
    
    public Candidato(String nome, int numUrna, int numFederacao, int foiEleito, int gen,
         LocalDate nasc, Partido partido) {
        this.nome = nome;
        this.numUrna = numUrna;
        this.numFederacao = numFederacao;
        this.foiEleito = foiEleito;
        this.gen = gen;
        this.numVotos = 0;
        this.nasc = nasc;
        this.partido = partido;
    }


    public String getNome() {
        return nome;
    }


    public int getNumUrna() {
        return numUrna;
    }


    public int getNumFederacao() {
        return numFederacao;
    }


    public int getFoiEleito() {
        return foiEleito;
    }
    public boolean foiEleito() {
        if(this.foiEleito == 2 || this.foiEleito == 3)return true;
        return false;
    }

    public int getGen() {
        return gen;
    }


    public int getNumVotos() {
        return numVotos;
    }


    public LocalDate getNasc() {
        return nasc;
    }


   

    public void adicionaVoto(){
        this.numVotos++;
    }

    @Override
    public String toString() {
        String texto = "";
        if(this.numFederacao != -1) texto = "*";
        texto+= this.nome + "(" + this.numVotos+ " votos)";
        return texto;
    }
    

}
