#include "Candidato.hpp"

Candidato::Candidato(const string &nome, const int &numUrna, const int &numFederacao, const int &foiEleito, const int &gen,
    const string &nasc, Partido &partido) : partido(partido){
        this->nome = nome;
        this->numUrna = numUrna;
        this->numFederacao = numFederacao;
        this->foiEleito = foiEleito;
        this->gen = gen;
        this->numVotos = 0;
        //this->nasc = nasc;
        //this->partido = partido;
        this->idade = 0;
}

void Candidato::setIdade(const int &idade){
    this->idade = idade;
}

const string &Candidato::getNome() const{
    return nome;
}

const int &Candidato::getIdade() const{
    return idade;
}

const int &Candidato::getNumVotos() const{
    return numVotos;
}

const int &Candidato::getGen() const{
    return gen;
}

const int &Candidato::getFoiEleito() const{
    return foiEleito;
}

const bool &Candidato::verificaSeFoiEleito() const{
    return (this->foiEleito == 2 || this->foiEleito == 3);
}

const int &Candidato::getNumFederacao() const{
    return numFederacao;
}

const int &Candidato::getNumUrna() const{
    return numUrna;
}

Partido &Candidato::getPartido() const{
    return this->partido;
}

void Candidato::incrementaVotosCandidato(const int &qtdVotos){
    //MARCELA: é a mesma coisa né?
    //this->numVotos += qtdVotos;
    numVotos += qtdVotos;
}