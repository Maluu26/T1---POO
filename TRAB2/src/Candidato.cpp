#include "Candidato.hpp"
#include "Partido.hpp"

Candidato::Candidato(const string &nome, const int &numUrna, const int &numFederacao, const int &foiEleito, const int &gen,
    const string &nasc, Partido *partido) : partido(partido) {
        this->nome = nome;
        this->numUrna = numUrna;
        this->numFederacao = numFederacao;
        this->foiEleito = foiEleito;
        if(gen == FEMININO) this->gen = FEMININO;
        else this->gen = MASCULINO;
        this->numVotos = 0;
        this->idade = 0;
        sscanf(nasc.c_str(), "%d/%d/%d", &diaNasc, &mesNasc, &anoNasc);
}

void Candidato::calculaIdade(const string &dataEleicao) {    
    int dia, mes, ano;
    
    sscanf(dataEleicao.c_str(), "%d/%d/%d", &dia, &mes, &ano);
    int anos = ano - this->getAnoNasc();
    
    if (mes < mesNasc || (mes == mesNasc && dia < diaNasc)) {
        anos--;
    }
    
    this->idade = anos;
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

const genero &Candidato::getGen() const{
    return gen;
}

const int &Candidato::getFoiEleito() const{
    return foiEleito;
}

bool Candidato::verificaSeFoiEleito() const{
    return (this->foiEleito == 2 || this->foiEleito == 3);
}

const int &Candidato::getNumFederacao() const{
    return numFederacao;
}

const int &Candidato::getNumUrna() const{
    return numUrna;
}

Partido *Candidato::getPartido() const{
    return this->partido;
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

const int Candidato::comparaNasc(const Candidato *c) const{
    int ano1 = this->getAnoNasc(), mes1 = this->getMesNasc(), dia1 = this->getDiaNasc();
    int ano2 = c->getAnoNasc(), mes2 = c->getMesNasc(), dia2 = c->getDiaNasc();

    if (ano1 < ano2 || (ano1 == ano2 && mes1 < mes2) || (ano1 == ano2 && mes1 == mes2 && dia1 < dia2)) {
        return -1; 
    } else if (ano1 > ano2 || (ano1 == ano2 && mes1 > mes2) || (ano1 == ano2 && mes1 == mes2 && dia1 > dia2)) {
        return 1; 
    }
    return 0; 
}
