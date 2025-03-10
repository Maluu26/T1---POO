#include "Candidato.hpp"

Candidato::Candidato(const string &nome, const int &numUrna, const int &numFederacao, const int &foiEleito, const int &gen,
    const string &nasc, const int &numPartido)  {
        this->nome = nome;
        this->numUrna = numUrna;
        this->numFederacao = numFederacao;
        this->foiEleito = foiEleito;
        this->gen = gen;
        this->numVotos = 0;
        this->numPartido = numPartido;
        this->idade = 0;
        //aqui separa a data em inteiros caso seja lida como string
        /*this->diaNasc = +++;
        this->mesNasc = +++;
        this->anoNasc = +++;*/
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

const int &Candidato::getNumPartidoCandidato() const{
    return numPartido;
}

const int &Candidato::getDiaNasc() const{
    return diaNasc;
}

const int &Candidato::getMesNasc() const{
    return mesNasc;
}

const int &Candidato::getAnoNasc() const{
    return anoNasc;
}

void Candidato::incrementaVotosCandidato(const int &qtdVotos){
    this->numVotos += qtdVotos;
}