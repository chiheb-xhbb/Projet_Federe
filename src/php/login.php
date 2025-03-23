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
    $user = mysqli_fetch_assoc($res);

    // Stocker les informations de l'utilisateur dans la session
    $_SESSION['user_id'] = $user['cin1'];
    $_SESSION['user_name'] = $user['mdp1'];

    // Rediriger vers la page d'accueil après connexion
    header("Location: acceuille.html");
    exit();
} else {
    header("Location: inscrire.html?message=error");
}


// Fermer la connexion
mysqli_close($con);
?>
