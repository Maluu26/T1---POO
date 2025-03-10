class CandidatoComparator implements java.util.Comparator<Candidato> {
    @Override
    public int compare(Candidato a, Candidato b){
        int dif = b.getNumVotos() - a.getNumVotos();
        if(dif == 0){
            if(b.getNasc().isBefore(a.getNasc())) dif = 1;
            else if(b.getNasc().isAfter(a.getNasc())) dif = -1;
            else dif = 0;
        }
       return dif;
    }
}
