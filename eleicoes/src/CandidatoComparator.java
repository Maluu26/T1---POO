class CandidatoComparator implements java.util.Comparator<Candidato> {
    @Override
    public int compare(Candidato a, Candidato b){
        int dif = b.getNumVotos() - a.getNumVotos();
        if(dif == 0){
            dif = b.getIdade() - a.getIdade();
        }
       return dif;
    }
}
