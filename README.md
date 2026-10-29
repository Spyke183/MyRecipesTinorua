# MyRecipes

Application Android qui affiche des recettes de cuisine à partir de l'API [TheMealDB](https://www.themealdb.com/api.php) : catégories, recettes d'une catégorie, détail d'une recette avec son nutri-score, recherche et favoris.

Projet de groupe du cours de développement mobile Android (M2 Expert en développement mobile & IoT, Ynov, 2026-2027), encadré par Laurent Pastorelli. Projet réalisé seul.

## Fonctionnalités

| Besoin | Description | État |
|---|---|---|
| B1 | Application nommée « MyRecipes » | fait (v0.1) |
| B2 | Écran « Categories » : liste des catégories avec leur vignette | fait (v0.1) |
| B3 | Appui sur une catégorie : ouverture de ses recettes | à faire |
| B4, B5 | Écran « \<CategoryName\> Recipes » : recettes d'une catégorie | à faire |
| B6, B7 | Détail d'une recette avec son nutri-score | à faire |
| B8 à B10 | Écran « Favorites », enregistré dans une base locale | à faire |
| B11 | Écran « Search » : recherche d'une recette par son nom | à faire |
| B12 | Menu hamburger : Categories, Search, Favorites | à faire |

Le suivi détaillé est dans les [issues](https://github.com/Spyke183/MyRecipesTinorua/issues) et les [jalons](https://github.com/Spyke183/MyRecipesTinorua/milestones) du dépôt.

## Choix techniques

- Kotlin, minSdk 26, écrans en layouts XML
- Nutri-score affiché dans une vue personnalisée
- Favoris enregistrés avec Room
- Retrofit et Gson pour l'API, RecyclerView et data binding pour les listes, Picasso pour les images, comme dans l'appli TMDB du cours

## Compiler et lancer

1. Ouvrir le projet dans Android Studio et laisser Gradle synchroniser le projet.
2. Lancer la configuration `app` sur un émulateur ou un téléphone (Android 8.0 minimum).

L'API TheMealDB est utilisée avec sa clé de test publique : il n'y a aucune clé à configurer.

## Organisation du dépôt

- `main` : version stable, mise à jour à chaque version (v0.1, v0.2…)
- `develop` : branche de travail
- `feature/...` : une branche par fonctionnalité, fusionnée dans `develop` par une pull request

## Maquette

La maquette de l'application est dans [docs/MyRecipes-Maquette-Tinorua.pdf](docs/MyRecipes-Maquette-Tinorua.pdf).
