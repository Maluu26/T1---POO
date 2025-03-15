#if !defined(VOTACAO_HPP)
#define VOTACAO_HPP

#include <iostream>
#include <string>
#include <map>

#include "Partido.hpp"
#include "Candidato.hpp"
#include "Comparator.hpp"

using namespace std;

class Votacao
{
    string dataEleicao;
    list<Candidato*> candidatosTotais;
    list<Candidato*> candidatosEleitos;
    map<int,Partido*> partidos;

public:
    Votacao();
    void setDataEleicao(string &data); 
    void atualizaVotacaoPartido(Partido *p, int chaveP);
    void atualizaVotacaoCandidato(Candidato *c);      
    void apuraVotos(int numUrna, int quantVotos, string &nomePart);
    const Partido *getPartidoKey(int key) const;
    void encontraEleitos();
    int getQtdEleitos() const;
    int getTotalVotosNominais()const;
    int foiMaisVotado(Candidato *c) const;    
    int getTotalVotosLegenda() const;
    bool contemPartido(int key) const;
    list<Candidato*> getCandidatosTotais() const;
    list<Candidato*> getCandidatosEleitos() const;    
    map<int,Partido*> getPartidos();
    bool foiEleito(Candidato *c) const;
    void ordenaCandidatos();
};

#endif
