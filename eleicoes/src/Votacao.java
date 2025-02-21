import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.text.NumberFormat;

public class Votacao {
    private LocalDate dataEleicao;
    private Map <Integer,Partido> partidos;
    private LinkedList<Candidato> candidatosTotais;
    private LinkedList<Candidato> candidatosEleitos;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public Votacao(){
        this.dataEleicao = null;
        this.partidos = new HashMap<>();
        this.candidatosTotais = new LinkedList<>();
        this.candidatosEleitos = new LinkedList<>();
    }

    public void setDataEleicao(String data) {
        this.dataEleicao = LocalDate.parse(data,formatter);
    }
    
    public void atualizaVotacaoPartido(Partido p, int chaveP){
        this.partidos.putIfAbsent(chaveP, p);
    }
    
    public void atualizaVotacaoCandidato(Candidato c){
        this.candidatosTotais.add(c);
    }
    
    public void apuraVotos(int numUrna, int quantVotos, String nomePart){
       
        if(numUrna>9 && numUrna<100){
            Partido p = this.partidos.get(numUrna);
            if(p!=null){
                p.incrementaVotosLegenda(quantVotos);
            }
            
        }
        if(numUrna>9999 && numUrna<100000){
            for(Candidato c: this.candidatosTotais){
                if(c.getNumUrna()==numUrna){
                    c.incrementaVotosCandidato(quantVotos);
                    break;
                }
            }
        }
    }
    
    public Partido getPartidoKey(int key){
        return this.partidos.get(key);
    }
        
    public void calculaIdades(){
        for (Candidato c : candidatosTotais) {
            int idade = (int) ChronoUnit.YEARS.between(c.getNasc(), dataEleicao);
            c.setIdade(idade);
        }
    }

    public void encontraEleitos(){
        for(Candidato c: candidatosTotais){
            if(c.foiEleito()) candidatosEleitos.add(c);
        }  
    }

    public int getQtdEleitos(){
        return this.candidatosEleitos.size();
    }

    public int getTotalVotosNominais(){
        int votosNominais = 0;;
        for(Candidato c: this.candidatosTotais){
            votosNominais += c.getNumVotos();
        }
        return votosNominais;
    }

    public int getTotalVotosLegenda(){
        int votosLegenda = 0;;
        for(Partido p: this.partidos.values()){
            votosLegenda += p.getQtdVotosLegenda();
        }
        return votosLegenda;
    }

    public boolean contemPartido(int key){
        return (this.partidos.containsKey(key));
    }

    public LinkedList<Candidato> getCandidatosTotais(){
        return new LinkedList<>(this.candidatosTotais);
    }

    public LinkedList<Candidato> getCandidatosEleitos(){
        if (this.getQtdEleitos() == 0) {
            this.encontraEleitos();
        }
        return new LinkedList<>(this.candidatosEleitos);
    }
    
}
