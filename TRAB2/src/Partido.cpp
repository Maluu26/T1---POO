#include "Partido.hpp"

Partido::Partido(const string &nome, const string &sigla, const int &numPartido){
    this->nome = nome;
    this->sigla = sigla;
    this->numPartido = numPartido;
    this->qtdVotosLegenda = 0;
    //trata a lista de candidatos

}

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

const int &Partido::getQtdVotosTotais() const{
    return this->getQtdVotosNominais() + this->qtdVotosLegenda;
}

const int &Partido::getQtdCandidatos() const{
    //
}

const int &Partido::getQtdVotosNominais() const{
    //
}

const int &Partido::getQtdEleitosNoPartido() const{
    //
}

//void adicionaCandidato(Candidato c);
//LinkedList<Candidato> getCandidatos();
//LinkedList<Candidato> getCandidatosOrdenados();