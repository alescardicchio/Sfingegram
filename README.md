# SFINGEGRAM 

Progetto del corso di [Architetture dei Sistemi Software](http://cabibbo.inf.uniroma3.it/asw/) per l'anno accademico 2020-2021. 

#### TEAM COMPOSTO DA:

- Alessandro Scardicchio
- Alexandru Rotariu
- Maria Carmela Pascale
- Michela Pascale
- Susanna Valentina Papetti



## Esecuzione 

Per eseguire questo progetto: 

* per avviare l'applicazione *Sfingegram* mandando in esecuzione **una singola istanza per ciascun servizio**, eseguire lo script 
 `run-sfingegram-single-service.sh`

* per avviare l'applicazione *Sfingegram* mandando in esecuzione **più istanze per ciascun servizio**, eseguire lo script 
 `run-sfingegram-multiple-services.sh`

* per interrompere l'applicazione *Sfingegram*, eseguire lo script `stop-sfingegram.sh`

* per inizializzare le basi di dati con dei dati di esempio, eseguire lo script `init-databases.sh` 


## Tecnologie utilizzate

- Per i servizi enigmi, connessioni ed enigmi-seguiti, è stata utilizzata come base di dati **MySQL**
- Per realizzare i canali per lo scambio di messaggi asincroni tra i servizi, è stato utilizzato come message broker **Kafka**
- Per il rilascio dell'applicazione e la composizione dei container, è stato utilizzato **Docker-Compose**


## Descrizione delle attività svolte 

### Specifica dei container Docker e dei database
- Aggiunta del *Dockerfile* per la creazione dell'immagine del container per ogni servizio
- Aggiunta del file *docker-compose.yml* per effettuare il deploy, combinare e configurare più container Docker allo stesso momento
- Nei servizi enigmi e connessioni, è stata introdotta una base di dati MySQL al posto di HSQLDB (eseguita in un contenitore Docker separato)

### Configurazione per Kafka e Zookeper
- Configurazione di due canali Kafka per l'inoltro dei messaggi asincroni. Il servizio enigmi pubblica eventi sul canale *enigmi* ed il servizio connessioni pubblica eventi sul canale *connessioni*. Su quest'ultimo canale avviene sia la pubblicazione delle connessioni utente-autore che quelle utente-tipo.
- Il consumatore è il servizio enigmi-seguiti, che riceve comandi dai due produttori *enigmi* e *connessioni*

### Modifica della logica del servizio enigmi-seguiti
- Introduzione di un database *My-SQL* (in un container Docker separato) nell'*application.yml* dedicato al servizio enigmi-seguiti, in modo tale che possa rispondere alle richieste GET accedendo solo alla propria tabella *enigmi-seguiti*. Sono state quindi eliminate le classi che si occupavano della comunicazione REST del servizio enigmi-seguiti con enigmi e connessioni.



## Descrizione di questo progetto 

Questo progetto contiene il il codice di *Sfingegram*, un semplice social network per la condivisione di enigmi (ovvero, giochi enigmistici). 
Gli utenti del sistema possono pubblicare degli enigmi. 
Possono poi seguire gli enigmi di specifici autori o di specifici tipi.  
Quando un utente accede alla pagina degli enigmi che segue, gli vengono mostrati gli enigmi degli autori e dei tipi che segue. 

L'applicazione *Sfingegram* è composta dai seguenti microservizi: 

* Il servizio *enigmi* gestisce gli enigmi. 
  Ogni enigma ha un autore, un tipo, un titolo, un testo (che può essere composta da più righe) e una soluzione (che può essere composta da più parole). 
  Operazioni: 
  * `POST /enigmi` aggiunge un nuovo enigma (dati autore, tipo, titolo, testo e soluzione)
  * `GET /enigmi/{id}` trova un enigma, dato l'id 
  * `GET /enigmi/{id}/soluzione` trova la soluzione di un enigma, dato l'id 
  * `GET /enigmi` trova tutti gli enigmi (senza la soluzione)
  * `GET /cercaenigmi/autore/{autore}` trova tutti gli enigmi di un certo autore (senza soluzione)
  * `GET //cercaenigmi/autori/{elenco-di-autori}` trova tutti gli enigmi di un insieme di autori (senza soluzione) 
  * `GET /cercaenigmi/tipo/{tipo}` trova tutti gli enigmi di un certo tipo (senza soluzione)
  * `GET /cercaenigmi/tipi/{elenco-di-tipi}` trova tutti gli enigmi di un insieme di tipi (senza soluzione)
  
* Il servizio *connessioni* gestisce le connessioni degli utenti, ovvero gli autori e i tipi di enigmi seguiti dagli utenti. 
  Le connessioni sono delle coppie utente-autore oppure utente-tipo, in cui gli autori sono in genere altri utenti del sistema. 
  Operazioni: 
  * `POST /connessioniconautori` aggiunge una nuova connessione utente-autore (dati utente e autore)
  * `GET /connessioniconautori` trova tutte le connessioni utente-autore
  * `GET /connessioniconautori/{utente}` trova tutte le connessioni utente-autore di un certo utente
  * `POST /connessionicontipi` aggiunge una nuova connessione utente-tipo (dati utente e tipo)
  * `GET /connessionicontipi` trova tutte le connessioni utente-tipo
  * `GET /connessionicontipi/{utente}` trova tutte le connessioni utente-tipo di un certo utente

* Il servizio *enigmi-seguiti* consente a un utente di trovare gli enigmi di tutti gli autori e di tutti i tipi che segue. 
  Operazioni: 
  * `GET /enigmiseguiti/{utente}` trova tutti gli enigmi seguiti da un certo utente, ovvero gli enigmi scritti da autori seguiti da quell'utente o di tipi di enigmi seguiti da quell'utente (gli enigmi sono senza soluzione)
  
* Il servizio *api-gateway* (esposto sulla porta *8080*) è l'API gateway dell'applicazione che: 
  * espone il servizio *enigmi* sul path `/enigmi` - ad esempio, `GET /enigmi/enigmi`
  * espone il servizio *connessioni* sul path `/connessioni` - ad esempio, `GET /connessioni/connessioniconautori/{utente}`
  * espone il servizio *enigmi-seguiti* sul path `/enigmi-seguiti` - ad esempio, `GET /enigmi-seguiti/enigmiseguiti/{utente}`
