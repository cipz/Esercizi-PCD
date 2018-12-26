
## Diffie Hellman key exchange
#### Spiegazione
* Alice e Bob pubblicano *p, g*
* Alice sceglie a, ed invia a Bob *A = g^a mod p*
* Bob sceglie b, ed invia ad Alice *B = g^b mod p*
* Ora condividono *s = B^a mod p = A^b mod p*

Quindi, durante uno scambio fra Alice e Bob sono pubblici:

    p, g, A, B

Mentre sono di difficile calcolo, e segreti:

    a, b, s

#### Scopo
Lo scopo dell'esercizio è impersonare un'agente malevolo, Charlie, che intende recuperare i segreti di Alice e Bob con un attacco di forza bruta.
Ai fini di completare l'esercizio prima della morte termica dell'universo, i numeri (e quindi la sicurezza) dello scambio sono molto, molto bassi.

#### Realizzazione
La classe da realizzare deve rendere verde un test che, dati i seguenti parametri:

    p = 128504093	g = 10009	A = 69148740	B = 67540095

verifica che tutte le coppie prodotte dalla funzione da completare soddisfino la condizione di calcolo di *s*.

Charlie è inoltre a conoscenza che, imprudentemente, Alice e Bob hanno scelto:

	0 <= a <= 65536
	0 <= b <= 65536

Le coppie da verificare quindi sono solo 2^32.

#### Obbiettivo dell'esercizio
L' obbiettivo dell'esercizio è organizzare il lavoro di attraversamento dello spazio di ricerca in modo da utilizzare più thread contemporaneamente, e occupare tutti i core disponibili.

####Scelte procedurali
Per svolgere l'esercizio ho scelto di utilizzare una classe interna ***DiffieHellmanThread***  che si occupa del calcolo dei possibili valori salvandoli prima in due liste separate per poi confrontare i valori trovati.
Questa classe implementa la classe ***Runnable*** e viene fornita di un costruttore che inizializza le variabili interne alla classe con quelli passati per creare e poi far partire un nuovo thread.
Nel metodo *crack* di ***DiffieHellman*** prendo il numero di core logici del processore e chiamo tante volte DiffieHellmanThread per generare tanti thread quanti gli *n* core logici presenti.
A ciascun thread vengono assegnati da calcolare *LIMIT / nLogicCores* valori.
Quando nel thread viene trovato un valore corretto, questo viene salvato in una lista dei risultati parziali.
Viene utilizzato un ciclo che aspetta che finiscano tutti i thread utilizzando il metodo *join*, prima di continuare l'esecuzione del programma.
Da ciascun thread generato vengono recuperati i risultati parziali per aggiungerli in coda alla lista *res* contenente tutti i risultati dello spazio tra 1 e *LIMIT* tramite un metodo *getPartialRes*.
I tempi di esecuzione del test variano tra i *33~* e *36~* secondi.

------
Per la realizzazione dell'esercizio ho preso spunto dalla spiegazione sulla creazione dei valori e sullo scambio di chiavi che avviene nell'algoritmo DiffieHellman del dipartimento di matematica che si può trovare a [questo link](https://www.math.brown.edu/~jhs/MathCrypto/SampleSections.pdf).