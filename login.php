<?php
// Connexion à la base de données
$con = mysqli_connect("localhost", "root", "", "bd_location");

// Vérifier la connexion
if (!$con) {
    die("Échec de la connexion : " . mysqli_connect_error());
}

// Récupération des données du formulaire
$cin = $_POST['cin1'];
$mdp = $_POST['mdp1'];

// Vérifier si l'utilisateur existe
$req = "SELECT * FROM utilisateurs WHERE id = '$cin' AND mot_de_passe = '$mdp'";
$res = mysqli_query($con, $req);

if (mysqli_num_rows($res) > 0) {
    echo "utilisateur mawjoud";
} else {
    echo "<script>
            alert('Utilisateur non trouvé ! Vérifiez vos informations.');
            window.location.href = 'inscrire.html'; // Change cette URL si nécessaire
          </script>";
}

// Fermer la connexion
mysqli_close($con);
?>
