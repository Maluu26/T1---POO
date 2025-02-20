import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;

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
    
    public void apuraVotos(int numUrna, int quantVotos){
        if(numUrna>9 && numUrna<100){
            Partido p = this.partidos.get(numUrna);
            p.incrementaVotosLegenda(quantVotos);
        }
        ///achar candidato e somar os seus votos e tbm os do seu partido;
        if(numUrna>9999 && numUrna<100000){
            for(Candidato c: this.candidatosTotais){
                if(c.getNumUrna() == numUrna){
                    c.adicionaVotos((quantVotos));
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

    //fazendo de forma provisória, depois a gnt altera pra imprimir bonitinho
    public void imprimeQtdPorGenero(){
        int fem=0, masc = 0;
        float femPorc=0, mascPorc=0;

        if(this.getQtdEleitos() == 0) this.encontraEleitos();
        for(Candidato c: this.candidatosEleitos){
            if(c.getGen() == 4) fem++;
            else masc++;
        }
        femPorc = (fem/this.getQtdEleitos()) * 100;
        mascPorc = (masc/this.getQtdEleitos()) * 100;

        //System.out.println();
    }

    public void imprimeQtdPorIdade(){
        int faixa20=0, faixa30=0, faixa40=0, faixa50=0, faixa60=0;

        if(this.getQtdEleitos() == 0) this.encontraEleitos();
        this.calculaIdades();
        //ordena por idade
        //incrementa as variáveis de acordo com a faixa etária
        //calcula e iimprime junto com as porcentagens

        //System.out.println();
    }

    public void imprimeQtdVotosEleicao(){
        int validos=0, nominais=0, legenda=0;
        for(Parido p: this.partidos.values()){
            nominais += p.getQtdVotosNominais();
            legenda += p.getQtdVotosLegenda();
        }
        validos = nominais + legenda;
        //System.out.println();
    }

    
    /*LinkedList <Candidato> eleitos = new LinkedList<>();
    for(Candidato c: candidatos){
      if(c.foiEleito()) eleitos.add(c);}
    for(Candidato c: eleitos){   
      System.out.println(c);
  }*/
}
