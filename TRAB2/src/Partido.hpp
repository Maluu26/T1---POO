#if !defined(PARTIDO_HPP)
#define PARTIDO_HPP

#include <iostream>
#include <string>

using namespace std;

//#include "Candidato.hpp" //MARCELA:isso vai ruim pq um não pode conter o outro

class Partido
{
    string nome, sigla;
    int numPartido, qtdVotosLegenda;
    //MARCELA: ver como vamos fazer isso em c++
    // fazer uma estrutura lista que vai ter o candidato dentro da célula???????
    // LinkedList<Candidato> candidatos;
public:
    Partido(const string &nome, const string &sigla, const int &numPartido);
    void incrementaVotosLegenda(const int &votos);
    const string &getSigla() const;
    const string &getNome() const;
    const int &getNumPartido() const;
    const int &getQtdVotosLegenda() const;
    const int &getQtdCandidatos() const;
	const int &qtdVotosNominais() const;
    const int &getQtdVotosTotais() const;
    const int &getQtdVotosNominais() const;
    const int &getQtdEleitosNoPartido() const;
    //void adicionaCandidato(Candidato c);
    //LinkedList<Candidato> getCandidatos();
    //LinkedList<Candidato> getCandidatosOrdenados();

};

#endif