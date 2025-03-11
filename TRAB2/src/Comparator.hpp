#if !defined(COMPARATOR_HPP)
#define COMPARATOR_HPP

class Candidato;
class Partido;

class Comparator 
{
public:
    bool comparaCandidatos(const Candidato &a, const Candidato &b) const;
    bool comparaPartidos(const Partido &a, const Partido &b) const;
    bool comparaCandidatosPartidos(const Partido &a, const Partido &b) const;
};

#endif