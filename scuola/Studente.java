package scuola;

public class Studente {

    double voto;
    String nome;
    String cognome;
    String guidizio;
    String esito;

    public Studente(String nome, String cognome, double voto, String guidizio, String esito) {
        this.nome = nome;
        this.cognome = cognome;
        this.voto = voto;
        this.guidizio = guidizio;
        this.esito = esito;
    }
}
