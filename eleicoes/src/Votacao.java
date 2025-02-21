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
        System.out.println(this.dataEleicao);
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
        
    private void calculaIdades(){
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

public void contagemFinal(){
        int votosNominais = 0, votosLegenda = 0;
        
        for(Partido p: this.partidos.values()){
            votosLegenda += p.getQtdVotosLegenda();
        }
        for(Candidato c: this.candidatosTotais){
            votosNominais += c.getNumVotos();
        }
        int total = votosLegenda + votosNominais;
        double p1 = ((double)(votosNominais)/(double)(total))*100;
        double p2 = 100-p1;
       
        NumberFormat brFormat = NumberFormat.getInstance(Locale.forLanguageTag("pt-BR"));
        NumberFormat nf = NumberFormat.getInstance(Locale.forLanguageTag("pt-BR"));
        brFormat.setGroupingUsed(true);
        nf.setGroupingUsed(true);
        nf.setMinimumFractionDigits(2);   
        nf.setMaximumFractionDigits(2);  

        System.out.println("Total de votos válidos:    " + brFormat.format(total));
        System.out.println("Total de votos nominais:   " + brFormat.format(votosNominais) + " (" +(nf.format(p1))+"%)");
        System.out.println("Total de votos de legenda: " + brFormat.format(votosLegenda)+" (" + nf.format(p2)+"%)");
    }

    public void eleitos(){
        if(this.getQtdEleitos() == 0) this.encontraEleitos();
        
        //fazer comparator;
        int i=1;
        for(Candidato c: this.candidatosEleitos){   
            System.out.println(i + " - " + c);
            i++;
        }

    }
    public boolean contemPartido(int key){
        return (this.partidos.containsKey(key));
    }
    public void eleitosGenero(){
        int fem=0, masc = 0;

        if(this.getQtdEleitos() == 0) this.encontraEleitos();
        for(Candidato c: this.candidatosEleitos){
            if(c.getGen() == 4) fem++;
            else masc++;
        }
        
        NumberFormat brFormat = NumberFormat.getInstance(Locale.forLanguageTag("pt-BR"));
        NumberFormat nf = NumberFormat.getInstance(Locale.forLanguageTag("pt-BR"));
        brFormat.setGroupingUsed(true);
        nf.setGroupingUsed(true);
        nf.setMinimumFractionDigits(2);   
        nf.setMaximumFractionDigits(2); 
        

        double femPorc = ((double)fem/(double)this.getQtdEleitos()) * 100;
        double mascPorc = ((double)masc/(double)this.getQtdEleitos()) * 100;
        System.out.println("Feminino:    " + brFormat.format(fem) + " (" + nf.format(femPorc)+"%)" );
        System.out.println("Masculino:    " + brFormat.format(masc) + " (" + nf.format(mascPorc)+"%)" );
    }

    public void eleitosIdade(){
        int faixa20=0, faixa30=0, faixa40=0, faixa50=0, faixa60=0;
        if(this.getQtdEleitos() == 0) this.eleitos();
        this.calculaIdades();
        for(Candidato c: this.candidatosEleitos){
            int idade = c.getIdade();
            if(idade<30) faixa20++;
            else if(idade>=30 && idade<40) faixa30++;
            else if(idade>=40 && idade<50) faixa40++;
            else if(idade>=50 && idade<60) faixa50++;
            else faixa60++;
        }
        double p20 = ((double)(faixa20)/(double)(candidatosEleitos.size()))*100;
        double p30 = ((double)(faixa30)/(double)(candidatosEleitos.size()))*100;
        double p40 = ((double)(faixa40)/(double)(candidatosEleitos.size()))*100;
        double p50 = ((double)(faixa50)/(double)(candidatosEleitos.size()))*100;
        double p60 = ((double)(faixa60)/(double)(candidatosEleitos.size()))*100;

        System.out.println("Idade < 30:  (" +p20 +"%)");
        System.out.println("Idade < 40:  (" +p30 +"%)");
        System.out.println("Idade < 50:  (" +p40 +"%)");
        System.out.println("Idade < 60:  (" +p50 +"%)");
        System.out.println("Idade: 0 (" +p60 +"%)");
        
    }
    
}
