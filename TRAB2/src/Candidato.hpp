#if !defined(CANDIDATO_HPP)
#define CANDIDATO_HPP

#include <iostream>
#include <string>

using namespace std;

class Partido;

class Candidato
{
    string nome;
    int numUrna, numFederacao, foiEleito, gen, numVotos, idade;
    int numPartido, diaNasc, mesNasc, anoNasc;
    Partido *partido;
public:
    Candidato(const string &nome, const int &numUrna, const int &numFederacao, const int &foiEleito, const int &gen,
        const string &nasc, Partido *partido);
    //MARCELA: pensei de ter um loop logo após ler todos candidatos para chamar essa função 
    //for cada Candidato c:
    //    c.calculaIdade(string dataEleicao)
    void calculaIdade(const string &dataEleicao);
    const string &getNome() const;
    const int &getIdade() const;
    const int &getNumVotos() const;
    const int &getGen() const;
    const int &getFoiEleito() const;
    bool verificaSeFoiEleito() const;
    const int &getNumFederacao() const;
    const int &getNumUrna() const;
    Partido *getPartido() const;
    //Partido &getPartido() const;
    const int &getDiaNasc() const;
    const int &getMesNasc() const;
    const int &getAnoNasc() const;
    void incrementaVotosCandidato(const int &qtdVotos);
    const int comparaNasc(const Candidato *c) const;
};

#endif // Candidato_HPP
