#!/bin/bash

# trova tutti gli enigmi seguiti da un certo utente 

# se l'utente contiene spazi deve essere racchiuso tra virgolette 
UTENTE=$(echo $1 | sed -e "s/ /%20/g") 

echo "# gli enigmi seguiti da $1" 
echo $(curl -s localhost:8080/enigmi-seguiti/enigmiseguiti/$UTENTE)
echo 
