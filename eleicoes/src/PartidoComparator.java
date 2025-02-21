class PartidoComparator implements java.util.Comparator<Partido> {
    @Override
    public int compare(Partido a, Partido b){
        int dif = b.getQtdVotosTotais() - a.getQtdVotosTotais();
        if(dif == 0){
            dif = a.getNumPartido() - b.getNumPartido();
        }
       return dif;
    }
}
