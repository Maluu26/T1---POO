#if !defined(CANDIDATO_HPP)
#define CANDIDATO_HPP

#include <iostream>
#include <string>

using namespace std;

enum genero {
    MASCULINO = 2,
    FEMININO = 4
};

class Partido;

class Candidato
{
    string nome;
    int numUrna, numFederacao, foiEleito, numVotos, idade;
    //MARCELA: ver de mudar a forma da data pra ele não reclamar de ser manual
    int numPartido, diaNasc, mesNasc, anoNasc;
    genero gen;
    Partido *partido;
public:
    Candidato(const string &nome, const int &numUrna, const int &numFederacao, const int &foiEleito, const int &gen,
        const string &nasc, Partido *partido);
    void calculaIdade(const string &dataEleicao);
    const string &getNome() const;
    const int &getIdade() const;
    const int &getNumVotos() const;
    const genero &getGen() const;
    const int &getFoiEleito() const;
    bool verificaSeFoiEleito() const;
    const int &getNumFederacao() const;
    const int &getNumUrna() const;
    Partido *getPartido() const;
    const int &getDiaNasc() const;
    const int &getMesNasc() const;
    const int &getAnoNasc() const;
    void incrementaVotosCandidato(const int &qtdVotos);
    const int comparaNasc(const Candidato *c) const;
};

#endif // Candidato_HPP
