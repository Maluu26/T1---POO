#if !defined(VOTACAO_HPP)
#define VOTACAO_HPP

#include <iostream>
#include <string>
#include <map>

#include "Partido.hpp"
#include "Candidato.hpp"

using namespace std;

class Votacao
{
    string dataEleicao;
    list<Candidato*> candidatosTotais;
    list<Candidato*> candidatosEleitos;
    map<int,Partido*> partidos;

public:
    Votacao();
    void setDataEleicao(string &data) const; //MARCELA:temos que usar const ou não??
    void atualizaVotacaoPartido(Partido *p, int chaveP) const;
    void atualizaVotacaoCandidato(Candidato *c) const;      
    void apuraVotos(int numUrna, int quantVotos, string &nomePart) const;
    const Partido *getPartidoKey(int key) const;
    int getQtdEleitos() const;
    int getTotalVotosNominais()const;
    int foiMaisVotado(Candidato *c) const;    
    int getTotalVotosLegenda() const;
    bool contemPartido(int key) const;
    list<Candidato*> getCandidatosTotais() const;
    list<Candidato*> getCandidatosEleitos() const;    
    map<int,Partido*> getPartidos();
    bool foiEleito(Candidato *c) const;
    void ordenaCandidatos() const;
};

#endif
