#include "Votacao.hpp"

Votacao::Votacao() : dataEleicao(), candidatosTotais(), candidatosEleitos(), partidos() {}

//MARCELA: se mudar o tipo da data, pode transfomar de string para o tipo aqui mesmo, ao invés de lidar com isso fora daqui
void Votacao::setDataEleicao(string &data){
    this->dataEleicao = data;
}

void Votacao::atualizaVotacaoPartido(Partido *p, int chaveP) {
    this->partidos.insert(make_pair(chaveP, p));
}

void Votacao::atualizaVotacaoCandidato(Candidato *c){
    this->candidatosTotais.push_back(c);
    if(c->verificaSeFoiEleito()) this->candidatosEleitos.push_back(c);
    c->calculaIdade(dataEleicao);
}      

void Votacao::apuraVotos(int numUrna, int quantVotos, string &nomePart){
    if(numUrna>9 && numUrna<100){
        auto it = this->partidos.find(numUrna);
        if (it != this->partidos.end()) {
            it->second->incrementaVotosLegenda(quantVotos);
        }
    }
    
    if(numUrna>9999 && numUrna<100000){
        for(Candidato *c: this->candidatosTotais){
            if(c->getNumUrna()==numUrna){
                c->incrementaVotosCandidato(quantVotos);
                break;
            }
        }
    }
}

const Partido *Votacao::getPartidoKey(int key) const{
    auto it = this->partidos.find(key);
    if (it == this->partidos.end()) return nullptr;
    else return it->second; 
}

int Votacao::getQtdEleitos() const{
    return this->candidatosEleitos.size();
}

int Votacao::getTotalVotosNominais()const{
    int qtdNominais = 0;
    for(Candidato *c: this->candidatosTotais){
        qtdNominais += c->getNumVotos();
    }
    return qtdNominais;
}

int Votacao::foiMaisVotado(Candidato *c) const{
    int i = 1;
    for(Candidato *cand: this->candidatosTotais){
        if(cand == c && i<=getQtdEleitos()) return -1;
        if(cand == c && i>getQtdEleitos()) break;
        i++;
    }
    return i;
} 

int Votacao::getTotalVotosLegenda() const{
    int votosLegenda = 0;
    for (const auto &par : this->partidos) {
        votosLegenda += par.second->getQtdVotosLegenda();
    }
    return votosLegenda;
}

bool Votacao::contemPartido(int key) const{
    return this->partidos.find(key) != this->partidos.end();
}

list<Candidato*> Votacao::getCandidatosTotais() const{
    list<Candidato*> copia = candidatosTotais;
    return copia;
}

list<Candidato*> Votacao::getCandidatosEleitos() const{
    list<Candidato*> copia = candidatosEleitos;
    return copia;
}   

map<int,Partido*> Votacao::getPartidos(){
    map<int, Partido*> copia = this->partidos;
    return copia;
}

bool Votacao::foiEleito(Candidato *c) const{
    for(Candidato *cEleito: this->candidatosEleitos){
        if(c==cEleito) return true;
    }
    return false;
}

void Votacao::ordenaCandidatos(){
    Comparator comp;

    this->candidatosTotais.sort([&comp](Candidato* a, Candidato* b) {
        return comp.comparaCandidatos(*a, *b);
    });
    
    this->candidatosEleitos.sort([&comp](Candidato* a, Candidato* b) {
        return comp.comparaCandidatos(*a, *b);
    });
}