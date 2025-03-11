#if !defined(COMPARATOR_HPP)
#define COMPARATOR_HPP

class Candidato;
class Partido;

class Comparator 
{
public:
    bool operator()(const Candidato *a, const Candidato *b) const;
    bool operator()(const Partido &a, const Partido &b) const;
};

#endif