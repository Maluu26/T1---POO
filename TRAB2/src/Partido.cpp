#include "Partido.hpp"
#include "Candidato.hpp"
#include "Comparator.hpp"

Partido::Partido(const string &nome, const string &sigla, const int &numPartido)
    : nome(nome), sigla(sigla), numPartido(numPartido), qtdVotosLegenda(0), candidatos() {}

void Partido::incrementaVotosLegenda(const int &votos){
    this->qtdVotosLegenda += votos;
}

const string &Partido::getSigla() const{
    return sigla;
}

const string &Partido::getNome() const{
    return nome;
}

const int &Partido::getNumPartido() const{
    return numPartido;
}

const int &Partido::getQtdVotosLegenda() const{
    return qtdVotosLegenda;
}

int Partido::getQtdVotosTotais() const{
    return this->getQtdVotosNominais() + this->qtdVotosLegenda;
}

int Partido::getQtdCandidatos() const{
    return candidatos.size();
}

int Partido::getQtdVotosNominais() const{
    int total = 0;
    for (Candidato *c : candidatos) {
        total += c->getNumVotos(); 
    }
    return total;
}

int Partido::getQtdEleitosNoPartido() const{
    int total = 0;
    for (Candidato *c : candidatos) {
        if(c->verificaSeFoiEleito()){
            total++;
        }
    }
    return total;
}

void Partido::adicionaCandidato(Candidato *c) {
    this->candidatos.push_back(c);
}

const list<Candidato*> Partido::getCandidatos() const{
    return this->candidatos;
}

const list<Candidato*> Partido::getCandidatosOrdenados() const {
    list<Candidato*> candidatosOrdenados = this->candidatos;

    Comparator comp;
    candidatosOrdenados.sort([&comp](Candidato* a, Candidato* b) {
        return comp.comparaCandidatos(*a, *b);
    });

    return candidatosOrdenados;
}

