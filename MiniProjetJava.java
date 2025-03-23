import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Classe de base Utilisateur
class Utilisateur {
    private String nom;
    private String email;
    private String motDePasse;

    public Utilisateur(String nom, String email, String motDePasse) {
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}

// Classe dérivée Formateur
class Formateur extends Utilisateur {
    private List<Formation> formations;

    public Formateur(String nom, String email, String motDePasse) {
        super(nom, email, motDePasse);
        this.formations = new ArrayList<>();
    }

    public void ajouterFormation(Formation formation) {
        formations.add(formation);
    }

    public List<Formation> getFormations() {
        return formations;
    }
}

// Classe dérivée Étudiant
class Etudiant extends Utilisateur {
    private List<Formation> inscriptions;

    public Etudiant(String nom, String email, String motDePasse) {
        super(nom, email, motDePasse);
        this.inscriptions = new ArrayList<>();
    }

    public void sinscrireFormation(Formation formation) throws FormationDejaInscriteException {
        if (inscriptions.contains(formation)) {
            throw new FormationDejaInscriteException("Vous êtes déjà inscrit à cette formation : " + formation.getTitre());
        }
        inscriptions.add(formation);
    }

    public List<Formation> getInscriptions() {
        return inscriptions;
    }
}

// Classe Formation
class Formation {
    private String titre;
    private String description;
    private Formateur formateur;
    private double prix;

    public Formation(String titre, String description, Formateur formateur, double prix) {
        this.titre = titre;
        this.description = description;
        this.formateur = formateur;
        this.prix = prix;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Formateur getFormateur() {
        return formateur;
    }

    public void setFormateur(Formateur formateur) {
        this.formateur = formateur;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Formation formation = (Formation) o;
        return Objects.equals(titre, formation.titre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titre);
    }
}

// Exception personnalisée : Formation déjà inscrite
class FormationDejaInscriteException extends Exception {
    public FormationDejaInscriteException(String message) {
        super(message);
    }
}

// Exception personnalisée : Utilisateur non trouvé
class UtilisateurNonTrouveException extends Exception {
    public UtilisateurNonTrouveException(String message) {
        super(message);
    }
}

// Exemple d'utilisation
public class MiniProjetJava {
    public static void main(String[] args) {
        try {
            // Création des utilisateurs
            Formateur formateur = new Formateur("Alice", "alice@mail.com", "password123");
            Etudiant etudiant = new Etudiant("Bob", "bob@mail.com", "pass456");

            // Création d'une formation
            Formation javaFormation = new Formation("Java Avancé", "Apprenez les concepts avancés de Java", formateur, 299.99);
            formateur.ajouterFormation(javaFormation);

            // Inscription à une formation
            etudiant.sinscrireFormation(javaFormation);

            // Tentative d'inscription à la même formation (lance une exception)
            etudiant.sinscrireFormation(javaFormation);
        } catch (FormationDejaInscriteException e) {
            System.err.println(e.getMessage());
        }
    }
}
