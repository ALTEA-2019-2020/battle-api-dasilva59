# battle-api-dasilva59
battle-api-dasilva59 created by GitHub Classroom


Microservice fonctionnel et déployé sur heroku à l'adresse   https://battleapi-dasilva.herokuapp.com/.

Un swagger est disponible à cet url https://battleapi-dasilva.herokuapp.com/swagger-ui.html#.


Les routes disponibles sont les suivantes:

  - GET: /battles retourne toutes les battles.
  
  - GET: /battles/{uuid} retourne la battle d'identifiant uuid.
  
  - POST: /battles avec parameters opponent et trainer obligatoire. Permet d'initialisé une battle entre deux adversaires. Retourne l'identifiant de la battle.
  
  - POST: /battles/{uuid}/{trainerName}/attack permet à un trainername d'attaquer son adversaire. La battle retournée est à jour en fonction de l'attaque portée.
