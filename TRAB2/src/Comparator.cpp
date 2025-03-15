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
   list<Candidato*> candidatosA = a.getCandidatosOrdenados();
   list<Candidato*> candidatosB = b.getCandidatosOrdenados();

   if (candidatosA.empty()) return false;
   if (candidatosB.empty()) return true;

   Candidato* candidatoMaisVotadoA = candidatosA.front();
   Candidato* candidatoMaisVotadoB = candidatosB.front();

   int dif = candidatoMaisVotadoB->getNumVotos() - candidatoMaisVotadoA->getNumVotos();
   if (dif == 0) {
       dif = a.getNumPartido() - b.getNumPartido();
   }

   return (dif < 0);
}