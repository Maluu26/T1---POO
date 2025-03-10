#if !defined(CANDIDATO_HPP)
#define CANDIDATO_HPP

#include <iostream>
#include <string>

//#include "Partido.hpp"

using namespace std;

class Candidato
{
    //MARCELA: na hora da leitura e da criação criar uma função pra separar a data como inteiros,
    //ou é possível já ler a data em formato de inteiros?
    string nome;
    int numUrna, numFederacao, foiEleito, gen, numVotos, idade;
    int numPartido, diaNasc, mesNasc, anoNasc;
    //Partido &partido;
public:
    //MARCELA: vai transformar a data em inteiros, caso ela seja lida como string
    Candidato(const string &nome, const int &numUrna, const int &numFederacao, const int &foiEleito, const int &gen,
        const string &nasc, const int &numPartido);
    void setIdade(const int &idade);
    
    const string &getNome() const;
    const int &getIdade() const;
    const int &getNumVotos() const;
    const int &getGen() const;
    const int &getFoiEleito() const;
    const bool &verificaSeFoiEleito() const;
    const int &getNumFederacao() const;
    const int &getNumUrna() const;
    const int &getNumPartidoCandidato() const;
    const int &getDiaNasc() const;
    const int &getMesNasc() const;
    const int &getAnoNasc() const;
    void incrementaVotosCandidato(const int &qtdVotos);
};

#endif // Candidato_HPP
