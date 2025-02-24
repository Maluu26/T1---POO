import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.NumberFormat;
import java.util.Locale;
public class Candidato{
    
    private String nome;
    private int numUrna, numFederacao, foiEleito, gen, numVotos, idade;
    private LocalDate nasc;
    private Partido partido;
   
    public Candidato(String nome, int numUrna, int numFederacao, int foiEleito, int gen,
        String nasc, Partido partido) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(nasc, formatter);
        this.nome = nome;
        this.numUrna = numUrna;
        this.numFederacao = numFederacao;
        this.foiEleito = foiEleito;
        this.gen = gen;
        this.numVotos = 0;
        this.nasc = date;
        this.partido = partido;
        this.idade = 0;
    }

    public String getNome() {
        return nome;
    }

    public int getNumUrna() {
        return numUrna;
    }

    public Partido getPartido(){
        return partido;
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

    public void setIdade(int idade){
        this.idade = idade;
    }

    public int getIdade(){
        return idade;
    }

    public void incrementaVotosCandidato(int qtdVotos){
        this.numVotos += qtdVotos;
    }

    @Override
    public String toString() {
        String texto = "";
        NumberFormat brFormat = NumberFormat.getInstance(Locale.forLanguageTag("pt-BR"));
        if(this.numFederacao != -1) texto = "*";
        int votos = this.numVotos;
        texto+= this.nome + " (" + this.partido.getSigla() + ", " + brFormat.format(votos) + " votos)";
        return texto;
    } 

}
