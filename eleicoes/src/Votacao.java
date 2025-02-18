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
        if(numUrna>9999 && numUrna<100000){
            ///achar candidato e somar os seus votos e tbm os do seu partido;
        }
    }

    

    /*public void calculaIdades(LinkedList<Candidato> candidatos){
        int anoEl = dataEleicao.getYear();
        int mesEl = dataEleicao.getMonthValue();
        int diaEl = dataEleicao.getDayOfMonth();

        for(Candidato c: candidatos){
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
    /*MARCELA: outra coisa, acho que podemos deixar private, pq só queremos acessar essa função especificamente aqui dentro e depois que todas as infos 
     * da Votação v que vamos criar na main estiverem completas. quando formos precisar disso, vai ser em outra função do Votacao.java msm,
     *  ent acho q não tem problema
    */
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
    /*LinkedList <Candidato> eleitos = new LinkedList<>();
    for(Candidato c: candidatos){
      if(c.foiEleito()) eleitos.add(c);}
    for(Candidato c: eleitos){   
      System.out.println(c);
  }*/
}
