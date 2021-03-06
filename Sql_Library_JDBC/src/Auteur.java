public class Auteur {
    private int numero;
    private Double droitAuteur;
    private int nivauAuteur;

    public Auteur() {
        super();
        numero = 0;
        droitAuteur = 0.0;
        nivauAuteur = 0;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Double getDroitAuteur() {
        return droitAuteur;
    }

    public void setDroitAuteur(Double droitAuteur) {
        this.droitAuteur = droitAuteur;
    }

    public int getNivauAuteur() {
        return nivauAuteur;
    }

    public void setNivauAuteur(int nivauAuteur) {
        this.nivauAuteur = nivauAuteur;
    }


    public Auteur(int numero, Double droitAuteur, int nivauAuteur) {
        this.numero = numero;
        this.droitAuteur = droitAuteur;
        this.nivauAuteur = nivauAuteur;
    }


}
