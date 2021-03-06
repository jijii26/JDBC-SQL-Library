public class Livre {

    private int ISBN;
    private String titre;
    private double prix;
    private int no_livre;

    public Livre() {
        ISBN = 0;
        titre = "";
        prix = 0;
        no_livre = 0;
    }

    public Livre(int ISBN, String titre, double prix,int no_livre) {
        this.ISBN = ISBN;
        this.titre = titre;
        this.prix = prix;
        this.no_livre = no_livre;
    }


    public int getNo_livre() {
        return no_livre;
    }

    public void setNo_livre(int no_livre) {
        this.no_livre = no_livre;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Livre : " + getISBN() + " Titre  " + getTitre() + " Prix  " + getPrix();
    }
}
