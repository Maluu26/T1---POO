import java.text.NumberFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.print.DocFlavor.STRING;

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

        System.out.println("\nTotal de votos válidos:    " + brFormat.format(total));
        System.out.println("Total de votos nominais:   " + brFormat.format(votosNominais) + " (" +(nf.format(p1))+"%)");
        System.out.println("Total de votos de legenda: " + brFormat.format(votosLegenda)+" (" + nf.format(p2)+"%)\n\n");
    }

    public void eleitos(){
        System.out.println("\nVereadores eleitos:");
        List<Candidato> eleitos = eleicao.getCandidatosEleitosOrdenados();
        
        int i=1;
        for(Candidato c : eleitos){
            System.out.println(i + " - " + c);
            i++;
        }
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

        System.out.println("\nEleitos, por gênero:");
        System.out.println("Feminino:  " + brFormat.format(fem) + " (" + nf.format(femPorc)+"%)" );
        System.out.println("Masculino: " + brFormat.format(masc) + " (" + nf.format(mascPorc)+"%)" );
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

        System.out.println("\nEleitos, por faixa etária (na data da eleição):");
        System.out.println("      Idade < 30: " + faixa20 + " (" + nf.format(p20) +"%)");
        System.out.println("30 <= Idade < 40: " + faixa30 + " (" + nf.format(p30) +"%)");
        System.out.println("40 <= Idade < 50: " + faixa40 + " (" + nf.format(p40) +"%)");
        System.out.println("50 <= Idade < 60: " + faixa50 + " (" + nf.format(p50) +"%)");
        System.out.println("60 <= Idade     : " + faixa60 + " (" + nf.format(p60) +"%)");
        
    }

    public void maisVotados(int vagas){
        List<Candidato> candidatos = eleicao.getCandidatosTotaisOrdenados();
        
        int i=1;
        System.out.println("\nCandidatos mais votados (em ordem decrescente de votação e respeitando número de vagas):");
        for(Candidato c : candidatos){
            System.out.println(i + " - " + c);
            if(i == vagas) break;
            i++;
        }
    }

    public void partidosMaisVotados(){
        HashMap <Integer, Partido> h = eleicao.getPartidos();
        LinkedList <Partido> partidos = new LinkedList<>(h.values());
    
        Collections.sort(partidos,new PartidoComparator());
        
        int i = 1;
        String nome ="";
        System.out.println("\nVotação dos partidos e número de candidatos eleitos:");
        
        for(Partido p: partidos){
            int eleitos = p.getQtdEleitosNoPartido();
            if(eleitos > 1){  nome = " candidatos eleitos\n";}
            else{ nome = " candidato eleito\n";}
           
            System.out.print(i + " - " + p.getSigla() + " - "+ p.getNumPartido()+ ", ");
            double votos = p.getQtdVotosTotais();
            if(votos < 2) System.out.print(brFormat.format(votos) + " voto (");
            else System.out.print(brFormat.format(votos) + " votos (");

            votos = p.getQtdVotosNominais();
            if(votos < 2) System.out.print(brFormat.format(votos) + " nominal e ");
            else System.out.print(brFormat.format(votos) + " nominais e ");
            
            System.out.print(brFormat.format(p.getQtdVotosLegenda())+ " de legenda), " + brFormat.format(p.getQtdEleitosNoPartido()) + nome);
            
            i++;
        }
    }

    public void candidatosQueSeriamEleitos(){
        LinkedList <Candidato> todos = this.eleicao.getCandidatosTotaisOrdenados();
        
        int i = 1;
        System.out.println("\nTeriam sido eleitos se a votação fosse majoritária, e não foram eleitos:\n" + //
                        "(com sua posição no ranking de mais votados)");
        for(Candidato c: todos){
            if(!eleicao.foiEleito(c)){
                System.out.println(i + " - "+ c);
            } 
            if(i==eleicao.getQtdEleitos()) break;
            i++;
        }
    }

    public void candidatosQueNaoSeriamEleitos(){
        LinkedList <Candidato> eleitos = this.eleicao.getCandidatosEleitosOrdenados();
        int i = 1;
        System.out.println("\nEleitos, que se beneficiaram do sistema proporcional:\n" + //
                    "(com sua posição no ranking de mais votados)");
        for(Candidato c: eleitos){
            //MARCELA: era pra num ser 19 no TATU DO BEM, mas ta saindo 18
            int num = eleicao.foiMaisVotado(c);
            if(num != -1) System.out.println(num + " - "+ c);
            //MARCELA: o que significa esse 13???
            if(i==13) break;
            i++;
        }
    }

    public void candidatosMaisEMenosVotados(){
        HashMap <Integer, Partido> h = eleicao.getPartidos();
        LinkedList <Partido> partidos = new LinkedList<>();

        for(Partido p: h.values()){
            if(p.getQtdCandidatos() != 0 && p.getQtdVotosNominais() != 0) partidos.add(p);
        }

        Collections.sort(partidos, new CandidatoPartidoComparator());

        int i = 1;
        String texto = "";
        double votos = 0;

        System.out.println("\nPrimeiro e último colocados de cada partido:");
        for(Partido p: partidos){
            
            LinkedList <Candidato> candidatos = p.getCandidatosOrdenados();
            
            texto += i + " - " + p.getSigla() + " - " + p.getNumPartido() + ", " + candidatos.getFirst().getNome() + 
                    " (" + candidatos.getFirst().getNumUrna() + ", ";
            votos = candidatos.getFirst().getNumVotos();
            if(votos < 2) texto += brFormat.format(votos) + " voto) / ";
            else texto += brFormat.format(votos) + " votos) / ";
            
            texto += candidatos.getLast().getNome() + " (" + candidatos.getLast().getNumUrna() + ", ";
            votos = candidatos.getLast().getNumVotos();
            if(votos < 2) texto += brFormat.format(votos) + " voto)";
            else texto += brFormat.format(votos) + " votos)";
            
            System.out.println(texto);
            if(i == partidos.size()) break;
            i++;
            texto = "";
        }
        
        
    }
}




