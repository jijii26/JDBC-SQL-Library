public class Edition {
    private int numero;
    private int ordre;
    private int exemplaire;
    private Livre livre = new Livre();

    public Edition() {
        numero = 0;
        ordre = 0;
        exemplaire = 0;
    }

    public Edition(int numero, int ordre, int exemplaire) {
        this.numero = numero;
        this.ordre = ordre;
        this.exemplaire = exemplaire;
    }

    public Edition(String titre, int exemplaire) {
        livre.setTitre(titre);
        this.exemplaire = exemplaire;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getOrdre() {
        return ordre;
    }

    public void setOrdre(int ordre) {
        this.ordre = ordre;
    }

    public int getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(int exemplaire) {
        this.exemplaire = exemplaire;
    }

    @Override
    public String toString() {
        return "Edition{" +
                "numero=" + numero +
                ", ordre=" + ordre +
                ", exemplaire=" + exemplaire +
                '}';
    }

    public String afficherTitre(){
        return "Titre " + "\t" + livre.getTitre() + "nombre exemplaire " + "\t" + exemplaire;
    }
}
