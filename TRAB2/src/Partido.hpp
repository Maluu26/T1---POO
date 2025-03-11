#if !defined(PARTIDO_HPP)
#define PARTIDO_HPP

#include <iostream>
#include <string>
#include <list>

using namespace std;

class Candidato;

class Partido
{
    string nome, sigla;
    int numPartido, qtdVotosLegenda;
    list<Candidato*> candidatos;
    
public:
    Partido(const string &nome, const string &sigla, const int &numPartido);
    void incrementaVotosLegenda(const int &votos);
    const string &getSigla() const;
    const string &getNome() const;
    const int &getNumPartido() const;
    const int &getQtdVotosLegenda() const;
    int getQtdCandidatos() const;
    int getQtdVotosTotais() const;
    int getQtdVotosNominais() const;
    int getQtdEleitosNoPartido() const;
    void adicionaCandidato(Candidato *c);
    list<Candidato*> getCandidatos();
    list<Candidato*> getCandidatosOrdenados();

};

#endif