# Descrizione dell'applicazione
## Esecuzione
Per avviare l'applicativo sarà necessario eseguire il relativo jar.
All'avvio si presenterà la schermata di accesso, rappresentante il logo della barberia e contenente due distinti pulsanti di accesso: uno per l'amministratore di sistema (il titolare della 
barberia), ed uno per i clienti.
Per accedere come amministratore sarà necessario inserire la password *"admin"* nell'apposito campo; per accedere nell'area clienti si richiede invece di inserire un codice numerico (e.g. 1).
## Sezione admin
La finestra dell'admin è divisa in tab, ognuno dei quali contiene tabelle di interesse all'amministratore stesso. La prima è quella del cliente, nella quale è possibile visualizzare tutti i 
clienti precedentemente inseriti nel sistema. In questa sezione è possibile utilizzare i pulsanti presenti nell'area sinistra per creare un nuovo cliente, cosa che porterà ad aprire una nuova 
finestra nella quale inserire i dati utili e poter salvare il nuovo cliente. Il tasto *"aggiorna clienti"* deve essere utilizzato ogni volta che si inserisce un nuovo cliente. Per ripristinare 
invece la visualizzazione di tutti i clienti dopo una ricerca specifica è necessario invece utilizzare il tasto *"ripristina"* . È possibile poi cercare i clienti inserendo un nome, o parte di 
esso, oppure selezionare i clienti il cui scontrino medio è superiore ad un dato numero passato in input nel rispettivo campo. Per mezzo dell'ultimo pulsante, si visualizzano in ordine i primi 
tre clienti per numero di servizi premium acquistati. Facendo invece clic destro su un dato cliente è possibile rimuovere il cliente, visualizzare i suoi dettagli, modificarlo o associargli 
una nuova fidelity o un nuovo appuntamento.
Nella sezione appuntamenti, similmente all'area clienti, sono visualizzati tutti gli appuntamenti presenti all'interno del database. Qui attraverso i pulsanti è possibile aggiungere un nuovo 
appuntamento, compilando adegautamente la pagina che si avvierà al click del relativo pulsante, filtrare per data gli appuntamenti, azzerare i filtri, ed aggiornare a seguito ad esempio di un'aggiunta. Attraverso click destro su un appuntamento si potranno modificare data 
e/o ora dello stesso, o visualizzare i servizi associati a quel dato appuntamento. 
I tab *"barbieri"*, *"scontrini"* e *"servizi"* sono più semplici rispetto ai precedenti, in quanto contengono soltanto la visualizzazione di tutti i rispettivi elementi.
## Sezione cliente
Accedendo all'interno dell'area clienti con il proprio codice identificativo, sarà possibile visualizzare tutti i propri appuntamenti passati ed in programma, con la presenza di un tasto 
*"aggiorna"* utile per visualizzare correttamente tutti gli appuntamenti in seguito all'aggiunta di uno di essi.
È presente inoltre una scheda *"dati"* in cui è possibile visualizzare tutti i propri dati registrati e modificare alcuni valori, salvando poi i cambiamenti effettuati.
