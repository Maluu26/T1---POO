//ARQUIVO DE TESTE
#include <string>
#include <iostream>

#include "Candidato.hpp"
#include "Partido.hpp"

using namespace std;

/*void imprimePartido(const Partido *p){
    cout << p->getNome() << endl;
    cout << p->getSigla() << endl;
    cout << p->getNumPartido() << endl;
    cout << p->getQtdVotosLegenda() << endl;
}*/

void imprimePartido(const Partido &p){
    cout << p.getNome() << endl;
    cout << p.getSigla() << endl;
    cout << p.getNumPartido() << endl;
    cout << p.getQtdVotosLegenda() << endl;
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
    
    Partido p1("PAIXAO", "PX", 20);
    Candidato c1("marcela", 1, 2, 3, 4, "06/02/2005", p1);
    Candidato c2("julia", 1, 2, 3, 4, "23/05/2002", p1);
    Candidato c3("mirian", 1, 2, 3, 4, "21/01/2012", p1);
    Candidato c4("samuel", 1, 2, 3, 4, "10/10/2000", p1);

    p1.adicionaCandidato(&c1);
    p1.adicionaCandidato(&c2);
    p1.adicionaCandidato(&c3);
    p1.adicionaCandidato(&c4);

    c1.incrementaVotosCandidato(10);
    //imprimeCandidato(&c1);

    for(Candidato *c: p1.getCandidatosOrdenados()){
        c->calculaIdade("10/03/2025");
        imprimeCandidato(c);
    }

    c3.incrementaVotosCandidato(200);
    cout << "\n" << endl;

    for(Candidato *c: p1.getCandidatosOrdenados()){
        imprimeCandidato(c);
    }
    

}