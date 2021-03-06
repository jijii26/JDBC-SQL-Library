import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.transform.Result;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Ecran extends JFrame {


    JButton btnFonctionUn, btnFonctionDeux, btnFonctionTrois, btnFonctionQuatre;
    JLabel lblPhraseFonction, lblNoAuteur, lblNoLivre, lblNoEdition, lblNoLibrairie;
    JFrame frame;
    JPanel pano, panoBtn, panoTexte, panoContenu;
    JTextField numeroAuteur, numeroLivre, numeroEdition, numeroLibrairie;
    JTable table;
    DefaultTableModel model;

    EcouteurRequeteUn ec1;
    EcouteurRequeteDeux ec2;
    EcouteurRequeteTrois ec3;
    EcouteurRequeteQuatre ec4;


    public Ecran() throws Exception {
        Connexion.getConnexion();

        setTitle("Les fonctionnalités");
        setBounds(0, 0, 960, 600);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        btnFonctionUn = new JButton("Afficher liste des livres");
        btnFonctionDeux = new JButton("Afficher pour numéro de livre");
        btnFonctionTrois = new JButton("afficher pour un No Édition");
        btnFonctionQuatre = new JButton("Afficher livre commandés");

        numeroAuteur = new JTextField(4);
        numeroLivre = new JTextField(4);
        numeroEdition = new JTextField(4);
        numeroLibrairie = new JTextField(4);

        lblPhraseFonction = new JLabel("Veuillez choisir une fonction");
        lblNoAuteur = new JLabel("No Auteur");
        lblNoEdition = new JLabel("No Edition");
        lblNoLivre = new JLabel("No Livre");
        lblNoLibrairie = new JLabel("No Librairie");

        frame = new JFrame();

        pano = new JPanel();
        panoBtn = new JPanel();
        panoTexte = new JPanel();
        panoContenu = new JPanel();


        //ajout de mes inputs
        panoTexte.add(lblNoAuteur);
        panoTexte.add(numeroAuteur);
        panoTexte.add(lblNoLivre);
        panoTexte.add(numeroLivre);
        panoTexte.add(lblNoEdition);
        panoTexte.add(numeroEdition);
        panoTexte.add(lblNoLibrairie);
        panoTexte.add(numeroLibrairie);

        btnFonctionUn.setBackground(Color.decode("#BD5555"));
        panoBtn.add(btnFonctionUn);
        btnFonctionDeux.setBackground(Color.decode("#5892F7"));
        panoBtn.add(btnFonctionDeux);
        btnFonctionTrois.setBackground(Color.decode("#72E6A4"));
        panoBtn.add(btnFonctionTrois);
        btnFonctionQuatre.setBackground(Color.decode("#F3E880"));
        panoBtn.add(btnFonctionQuatre);


        //placé mes pano dans le frame
        ((FlowLayout) pano.getLayout()).setAlignment(FlowLayout.LEFT);
        ((FlowLayout) pano.getLayout()).setHgap(20);
        ((FlowLayout) pano.getLayout()).setVgap(20);

        ((FlowLayout) panoTexte.getLayout()).setAlignment(FlowLayout.CENTER);
        ((FlowLayout) panoTexte.getLayout()).setHgap(20);
        ((FlowLayout) panoTexte.getLayout()).setVgap(20);

        //tableau de résultat
        model = new DefaultTableModel();
        model.addColumn("Resultat");
        table = new JTable(model);
        table.setPreferredSize(new Dimension(400, 550));
        panoContenu.add(new JScrollPane(table));
        panoContenu.add(table.getTableHeader(), BorderLayout.NORTH);

        //fermeture du programme
        addWindowListener(new WindowAdapter() {
                              public void windowClosing(WindowEvent arg0) {
                                  System.exit(0);
                              }
                          }
        );

        getContentPane().add(panoTexte, BorderLayout.NORTH);
        getContentPane().add(panoContenu, BorderLayout.CENTER);
        getContentPane().add(panoBtn, BorderLayout.SOUTH);

        ec1 = new EcouteurRequeteUn();
        btnFonctionUn.addActionListener(ec1);
        ec2 = new EcouteurRequeteDeux();
        btnFonctionDeux.addActionListener(ec2);
        ec3 = new EcouteurRequeteTrois();
        btnFonctionTrois.addActionListener(ec3);
        ec4 = new EcouteurRequeteQuatre();
        btnFonctionQuatre.addActionListener(ec4);
    }

    public class EcouteurRequeteUn implements ActionListener {
        public void actionPerformed(ActionEvent args0) {
            Ecran.Clear(model);
            Livre livre = null;
            Auteur auteur = new Auteur();
            try {
                ValiderDonnee(numeroAuteur);
                auteur.setNumero(Integer.parseInt(numeroAuteur.getText()));

                String instruction = "select t1.no_isbn,t1.titre,t1.prix,t2.no_auteur,t3.nom_auteur,t3.cote " +
                        "from livre t1 inner join AuteurLivre t2 on t1.id_livre = t2.id_livre " +
                        "inner join auteur t3 on t3.no_auteur=t2.no_auteur where t2.no_auteur = ?";

                PreparedStatement afficherLivre = Connexion.getConnexion().prepareStatement(instruction);
                afficherLivre.setInt(1, auteur.getNumero());
                ResultSet rs = afficherLivre.executeQuery();

                ExistePas(rs);

                do {
                    livre = new Livre(rs.getInt("no_isbn"), rs.getString("titre"), rs.getInt("prix"), rs.getInt(4));
                    model.addRow(new Object[]{livre.toString()});
                }
                while (rs.next());

                model.fireTableDataChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public class EcouteurRequeteDeux implements ActionListener {
        public void actionPerformed(ActionEvent args0) {
            Ecran.Clear(model);
            Livre livre;

            try {
                Edition edition = null;
                livre = new Livre();
                ValiderDonnee(numeroLivre);
                livre.setNo_livre(Integer.parseInt(numeroLivre.getText()));

                String instruction = "select e.no_edition, e.ordre, e.exemplaire from livre l inner join edition e on l.no_edition = e.no_edition where l.no_livre = ?";
                PreparedStatement afficherNoLivre = Connexion.getConnexion().prepareStatement(instruction);
                afficherNoLivre.setInt(1, livre.getNo_livre());
                ResultSet rs = afficherNoLivre.executeQuery();

                ExistePas(rs);

                do {
                    edition = new Edition(rs.getInt(1), rs.getInt(2), rs.getInt(3));
                } while (rs.next());

                model.addRow(new Object[]{edition.toString()});
                model.fireTableDataChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public class EcouteurRequeteTrois implements ActionListener {
        public void actionPerformed(ActionEvent args0) {
            Ecran.Clear(model);
            Edition edition = new Edition();
            try {
                ValiderDonnee(numeroEdition);
                edition.setNumero(Integer.parseInt(numeroEdition.getText()));

                String instruction = "select l.titre,e.exemplaire from livre l inner join edition e on l.no_edition = e.no_edition where e.no_edition = ?";
                PreparedStatement afficherNoEdition = Connexion.getConnexion().prepareStatement(instruction);
                afficherNoEdition.setInt(1, edition.getNumero());
                ResultSet rs = afficherNoEdition.executeQuery();

                ExistePas(rs);

                do {
                    edition = new Edition(rs.getString("titre"), rs.getInt(2));
                    model.addRow(new Object[]{edition.afficherTitre()});
                }
                while (rs.next());

                model.fireTableDataChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public class EcouteurRequeteQuatre implements ActionListener {
        public void actionPerformed(ActionEvent args0) {
            Ecran.Clear(model);
            Librairie librairie = new Librairie();
            Livre livre;
            try {
                String instruction = "select l.no_isbn, l.no_edition, l.titre, l.prix ,l.no_livre " +
                        "from livre l inner join librairie li on li.no_librairie = l.no_librairie " +
                        "where li.no_librairie = ?";

                PreparedStatement livreCommandee = Connexion.getConnexion().prepareStatement(instruction);
                ValiderDonnee(numeroLibrairie);
                livreCommandee.setInt(1, Integer.parseInt(numeroLibrairie.getText()));
                ResultSet rs = livreCommandee.executeQuery();

                ExistePas(rs);

                do {
                    livre = new Livre(rs.getInt(1), rs.getString("titre"), rs.getDouble(4), rs.getInt(5));
                    librairie.add(livre);
                }
                while (rs.next());
                for (Livre livres : librairie.getListeLivre()) {
                    model.addRow(new Object[]{librairie.toString()});
                }
                model.fireTableDataChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //methode qui sert a effacer le model pour pouvoir en mettre une nouvelle
    public static void Clear(DefaultTableModel model) {
        int index = 0;
        if (model.getRowCount() > 0) {
            for (int i = model.getRowCount() - 1; i > -1; i--) {
                model.removeRow(i);
            }
        }
    }

    //methode qui regarde si les inputs sont valide
    public void ValiderDonnee(JTextField text) {
        try {
            Integer.parseInt(text.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "entrer un nombre", "NO", JOptionPane.PLAIN_MESSAGE);
        }
    }

    //methode qui regarde sil existe
    public void ExistePas(ResultSet rs) throws SQLException {
        if (!rs.next()) {
            JOptionPane.showMessageDialog(frame, "données n'existe pas ", "Non existant", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
