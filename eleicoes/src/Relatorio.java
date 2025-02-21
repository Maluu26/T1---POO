import java.text.NumberFormat;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class Relatorio {
    private Votacao eleicao;
    NumberFormat brFormat = NumberFormat.getInstance(Locale.forLanguageTag("pt-BR"));
    NumberFormat nf = NumberFormat.getInstance(Locale.forLanguageTag("pt-BR"));
 
    public Relatorio(Votacao eleicao) {
        this.eleicao = eleicao;
        brFormat.setGroupingUsed(true);
        nf.setGroupingUsed(true);
        nf.setMinimumFractionDigits(2);   
        nf.setMaximumFractionDigits(2);
    }

    public void contagemFinal(){
        int votosNominais = eleicao.getTotalVotosNominais();
        int votosLegenda = eleicao.getTotalVotosLegenda();
        int total = votosNominais + votosLegenda;
        double p1 = ((double)(votosNominais)/(double)(total))*100;
        double p2 = 100-p1;

        System.out.println("Total de votos válidos:    " + brFormat.format(total));
        System.out.println("Total de votos nominais:   " + brFormat.format(votosNominais) + " (" +(nf.format(p1))+"%)");
        System.out.println("Total de votos de legenda: " + brFormat.format(votosLegenda)+" (" + nf.format(p2)+"%)\n");
    }

    public void eleitos(){
        System.out.println("Vereadores eleitos:");
        List<Candidato> eleitos = eleicao.getCandidatosEleitos();
        Collections.sort(eleitos, new CandidatoComparator());
        
        int i=1;
        for(Candidato c : eleitos){
            System.out.println(i + " - " + c);
            i++;
        }
        System.out.println("\n");
    }

    public void eleitosGenero(){
        int fem=0, masc = 0;
        double totalEleitos = eleicao.getQtdEleitos();

        for(Candidato c: eleicao.getCandidatosEleitos()){
            if(c.getGen() == 4) fem++;
            else masc++;
        }
        
        double femPorc = ((double)fem/ totalEleitos) * 100;
        double mascPorc = ((double)masc/ totalEleitos) * 100;

        System.out.println("Eleitos, por gênero:");
        System.out.println("Feminino:  " + brFormat.format(fem) + " (" + nf.format(femPorc)+"%)" );
        System.out.println("Masculino: " + brFormat.format(masc) + " (" + nf.format(mascPorc)+"%)\n" );
    }

    public void eleitosIdade(){
        int faixa20=0, faixa30=0, faixa40=0, faixa50=0, faixa60=0;
        eleicao.calculaIdades();
        double totalEleitos = eleicao.getQtdEleitos();
        
        for(Candidato c: eleicao.getCandidatosEleitos()){
            int idade = c.getIdade();
            if(idade<30) faixa20++;
            else if(idade>=30 && idade<40) faixa30++;
            else if(idade>=40 && idade<50) faixa40++;
            else if(idade>=50 && idade<60) faixa50++;
            else faixa60++;
        }
        double p20 = ((double)(faixa20)/(totalEleitos))*100;
        double p30 = ((double)(faixa30)/(totalEleitos))*100;
        double p40 = ((double)(faixa40)/(totalEleitos))*100;
        double p50 = ((double)(faixa50)/(totalEleitos))*100;
        double p60 = ((double)(faixa60)/(totalEleitos))*100;

        System.out.println("Eleitos, por faixa etária (na data da eleição):");
        System.out.println("      Idade < 30: " + faixa20 + " (" + nf.format(p20) +"%)");
        System.out.println("30 <= Idade < 40: " + faixa30 + " (" + nf.format(p30) +"%)");
        System.out.println("40 <= Idade < 50: " + faixa40 + " (" + nf.format(p40) +"%)");
        System.out.println("50 <= Idade < 60: " + faixa50 + " (" + nf.format(p50) +"%)");
        System.out.println("60 <= Idade     : " + faixa60 + " (" + nf.format(p60) +"%)\n\n");
        
    }

    public void maisVotados(int vagas){
        List<Candidato> candidatos = eleicao.getCandidatosTotais();
        Collections.sort(candidatos, new CandidatoComparator());
        
        int i=1;
        System.out.println("Candidatos mais votados (em ordem decrescente de votação e respeitando número de vagas):");
        for(Candidato c : candidatos){
            System.out.println(i + " - " + c);
            i++;
            if(i > vagas) break;
        }
        System.out.println("\n");
    }

    public void partidos(){
        //transformar a hash em lista, ordenar e imprimir
    }

    
}


