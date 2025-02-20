import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.math.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Votacao {
    private LocalDate dataEleicao;
    private Map <Integer,Partido> partidos;
    private LinkedList<Candidato> candidatosTotais;
    private LinkedList<Candidato> candidatosEleitos;

    public Votacao(){
        this.dataEleicao = null;
        this.partidos = new HashMap<>();
        this.candidatosTotais = new LinkedList<>();
        this.candidatosEleitos = new LinkedList<>();
    }

    public void setDataEleicao(LocalDate dataEleicao) {
        this.dataEleicao = dataEleicao;
    }

    public void atualizaVotacao(Candidato c, Partido p, int chaveP){
        this.candidatosTotais.add(c);
        this.partidos.putIfAbsent(chaveP, p);
    }
    public void apuraVotos(int numUrna, int quantVotos, String nomePart){
       
        if(numUrna>9 && numUrna<100){
            Partido p = this.partidos.get(numUrna);
            if(p!=null){
                p.incrementaVotosLegenda(quantVotos);
            }
            if(p==null){
                p = new Partido(nomePart,null,numUrna);
                p.incrementaVotosLegenda(quantVotos);
                this.partidos.put(numUrna, p);
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

    

    /*public void calculaIdades(){
        int anoEl = dataEleicao.getYear();
        int mesEl = dataEleicao.getMonthValue();
        int diaEl = dataEleicao.getDayOfMonth();

        for(Candidato c: candidatosEleitos){
            int anoC = c.getNasc().getYear();
            int mesC = c.getNasc().getMonthValue();
            int diaC = c.getNasc().getDayOfMonth();
            
            if(mesC == mesEl){
                if(diaC < diaEl){
                    c.setIdade(anoEl - anoC - 1);
                }
                else {
                    c.setIdade(anoEl - anoC);
                }
            }
            else {
                if(mesC > mesEl){
                    c.setIdade(anoEl - anoC - 1);
                }
                else {
                    c.setIdade(anoEl - anoC);
                }
            }
        }

    }*/
    
    /*MARCELA: tem que ver se essa função vai funcionar direitinho. Se não, a gente usa a de cima mesmo; */
    private void calculaIdades(){
        for (Candidato c : candidatosEleitos) {
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
        //MALU FAZER A FORMATAÇÃO DOS NÚMEROS PELO PAÍS E AJEITAR PORCENTAGEM
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
        //ordena por idade
        //incrementa as variáveis de acordo com a faixa etária
        //calcula e iimprime junto com as porcentagens

        //System.out.println();
    }
    
}
