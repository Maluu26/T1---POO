//ARQUIVO DE TESTE
#include <string>
#include <iostream>

#include "Candidato.hpp"
#include "Partido.hpp"
#include "Comparator.hpp"

using namespace std;

void imprimePartido(const Partido *p){
    cout << p->getNome() << endl;
    cout << p->getSigla() << endl;
    cout << p->getNumPartido() << endl;
    cout << p->getQtdVotosLegenda() << endl;
}

void imprimeCandidato(const Candidato *c){
    cout << c->getNome() << " " << c->getIdade() << " " << c->getNumVotos()<< endl;
    /*cout << c.getNumUrna() << endl;
    cout << c.getNumFederacao() << endl;
    cout << c.getFoiEleito() << endl;
    cout << c.getGen() << endl;
    cout << c.getIdade() << endl;
    cout << c.getDiaNasc() << "/" << c.getMesNasc() << "/" << c.getAnoNasc() << endl;
    imprimePartido(c.getPartido());*/
}

int main(){
    list<Partido*> partidos;
    
    Partido *p1 = new Partido("PAIXAO", "PX", 20);
    Partido *p2 = new Partido("CARPENTER", "CP", 30);
    
    partidos.push_back(p1);
    partidos.push_back(p2);

    Candidato *c1 = new Candidato("marcela", 1, 2, 3, 4, "06/02/2005", p1);
    Candidato *c2 = new Candidato("julia", 1, 2, 3, 4, "23/05/2002", p1);
    Candidato *c3 = new Candidato("mirian", 1, 2, 3, 4, "21/01/2012", p2);
    Candidato *c4 = new Candidato("samuel", 1, 2, 3, 4, "10/10/2000", p2);

    p1->adicionaCandidato(c2);
    p1->adicionaCandidato(c1);
    
    p2->adicionaCandidato(c3);
    p2->adicionaCandidato(c4);

    c1->incrementaVotosCandidato(10);;

    for(Partido *p: partidos){
        for(Candidato *c: p->getCandidatos()){
            c->calculaIdade("10/03/2025");
            imprimeCandidato(c);
        }
        cout << "\n" << endl;
    }

    c3->incrementaVotosCandidato(200);

    Comparator comp;

    partidos.sort([&comp](const Partido *a, const Partido *b) {
        if (!a) return false;
        if (!b) return true;
        return comp.comparaPartidos(*a, *b);
    });
    
    cout << "ordenados:" << endl;
    for(Partido *p: partidos){
        for(Candidato *c: p->getCandidatosOrdenados()){
            imprimeCandidato(c);
        }
        cout << "\n" << endl;
    }

    delete c1;
    delete c2;
    delete c3;
    delete c4;
    delete p1;
    delete p2;
}