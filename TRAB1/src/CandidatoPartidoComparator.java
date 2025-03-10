import java.util.List;

class CandidatoPartidoComparator implements java.util.Comparator<Partido> {
    @Override
    public int compare(Partido a, Partido b){
        List<Candidato> candidatosA = a.getCandidatosOrdenados();
        List<Candidato> candidatosB = b.getCandidatosOrdenados();
        
        int dif = candidatosB.get(0).getNumVotos() - candidatosA.get(0).getNumVotos();
        if(dif == 0){
            dif = candidatosA.get(0).getPartido().getNumPartido() - candidatosB.get(0).getPartido().getNumPartido();
        }
        return dif;
    }
}