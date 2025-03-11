#include "Comparator.hpp"
#include "Partido.hpp"
#include "Candidato.hpp"

bool Comparator::comparaCandidatos(const Candidato &a, const Candidato &b) const{
    int dif = b.getNumVotos() - a.getNumVotos();
    if(dif == 0){
        dif = a.comparaNasc(&b);
    }
    return(dif < 0);
}
bool Comparator::comparaPartidos(const Partido &a, const Partido &b) const{
    int dif = b.getQtdVotosTotais() - a.getQtdVotosTotais();
    if(dif == 0){
        dif = a.getNumPartido() - b.getNumPartido();
    }
    return(dif < 0);
}
bool Comparator::comparaCandidatosPartidos(const Partido &a, const Partido &b) const{
    return true;//so pra compilar sem warning
}