import java.util.ArrayList;

public class Librairie {
    private int numero;
    private String nom;
    private String adresse;
    private ArrayList<Livre> listeLivre;

    public Librairie() {
       listeLivre = new ArrayList<Livre>();
    }

    public Librairie(int numero, String nom, String adresse) {
        this.numero = numero;
        this.nom = nom;
        this.adresse = adresse;
    }

    public ArrayList<Livre> getListeLivre() {
        return listeLivre;
    }

    public void setListeLivre(ArrayList<Livre> listeLivre) {
        this.listeLivre = listeLivre;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    // methode pour ajouter les liuvre dans la librairie
    public void add(Livre livre) {
        listeLivre.add(livre);
    }

    @Override
    public String toString() {
        return  getListeLivre().toString();
    }
}
