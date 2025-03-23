<?php
// Connexion à la base de données
$con = mysqli_connect("localhost", "root", "", "bd_location");

// Vérifier la connexion
if (!$con) {
    die("Échec de la connexion : " . mysqli_connect_error());
}

// Récupération des données du formulaire
$cin = $_POST['cin'];
$nom = $_POST['nom'];
$email = $_POST['email'];
$mdp = $_POST['mdp'];
$num = $_POST['tel'];

// Vérifier si l'email existe déjà
$req = "SELECT * FROM utilisateurs WHERE id = '$cin'";
$res = mysqli_query($con, $req);

if (mysqli_num_rows($res) > 0) {
    echo "utilisateur est deja inscrit.";
} else {
    // Insérer l'utilisateur dans la base de données
    $req = "INSERT INTO utilisateurs VALUES ('$cin', '$nom', '$email', '$mdp', '$num', '')";
    mysqli_query($con,$req);
    if(mysqli_affected_rows($con)!=-1)
echo("operation effectue avec succes");
else
echo("operation echoue");
}


// Fermer la connexion
mysqli_close($con);
?>
