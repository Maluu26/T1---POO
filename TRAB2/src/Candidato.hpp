#if !defined(CANDIDATO_HPP)
#define CANDIDATO_HPP

#include <iostream>
#include <string>

#include "Partido.hpp"

using namespace std;

class Candidato
{
    //MARCELA: existe uma forma melhor de aramzenar a data? Talvez separadamente como inteiros
    string nome, data;
    int numUrna, numFederacao, foiEleito, gen, numVotos, idade;
    Partido &partido;
public:
    //MARCELA: partido precisa de const?
    Candidato(const string &nome, const int &numUrna, const int &numFederacao, const int &foiEleito, const int &gen,
        const string &nasc, Partido &partido);
    void setIdade(const int &idade);
    //MARCELA: precisa do & nesses getters?
    const string &getNome() const;
    const int &getIdade() const;
    const int &getNumVotos() const;
    const int &getGen() const;
    const int &getFoiEleito() const;
    const bool &verificaSeFoiEleito() const;
    const int &getNumFederacao() const;
    const int &getNumUrna() const;
    Partido &getPartido() const;
    //const string &getNasc() const;
    void incrementaVotosCandidato(const int &qtdVotos);
};

#endif // Candidato_HPP
