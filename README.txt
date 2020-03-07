OBSERVATII:		


	SURSE:
		- in folderul SURSE se afla si resursele pentru interfata grafica, fisiere .java aflandu-se in SURSE/SURSE
		- pentru rularea testelor date (clasei Test) se folosesc fisierele checker.sh (pentru linux), respectiv checker.bat (pentru windows), output-urile pentru fiecare test fiind disponibile in fisierele output*.txt
		- pentru rularea clasei Main (cu interfata grafica) se foloseste "make run" pentru linux si fisierul Run.bat pentru windows


	PROIECT:
		- proiectul este realizat in IntelliJ
		- pentru a functiona corect trebuie rulata comanda "java Main" (dupa compilare) direct in folderul unde se afla fisierul "Main.class"
		- fisierele campaign.txt si users.txt reprezinta campaniile si utilizatorii care vor fi incarcati la porninea aplicatiei cu interfata grafica
		- fisierul emails.txt contine adresele de email pentru generarea simultana a mai multor vouchere 


In mare, flow-ul programului este urmatorul:
	- VMS este colectia de date caracterizata de multimea de campanii existente, precum si de utilizatorii care pot primi diverse vouchere
	- Se pot adauga noi campanii sau utilizatori, sau se pot inchide/modifica campaniile existente
	- In cadrul fiecarei campanii se pot genera noi vouchere (in limita numarului total) sau se pot folosi voucherele deja generate
	- Fiecare campanie are o lista cu vouchere generate corespunzatoare fiecarui utilizator
	- Exista doua tipuri de vouchere: GiftVoucher si LoyalityVoucher
	- Fiecare utilizator are o lista cu voucherele sale din fiecare campanie
	- Utilizatorii abonati la o campanie primesc notificari de fiecare data cand se inchide/modifica o campanie


DETALII DE IMPLEMENTARE:


Flow-ul programului este cel mentionat mai sus (si in enunt) cu cateva precizari:
	- notificarile se distribuie in cazul in care o campanie a fost inchisa/modificata cu succes, in cadrul metodelor cancelCampaign (din VMS), respectiv editCampaign (din Campaign)
	- in momentul in care se genereaza un voucher (metoda generateVoucher din Campaign), acesta este introdus in: lista de vouchere ale campaniei si lista de vouchere ale utilizatorului corespunzator. Daca utilizator nu este in lista de Observeri a campaniei, acesta este adaugat.
	- nu se sterge niciodata un utilizator din lista de Observeri a unei campanii, deoarece acesta mereu o sa aiba un voucher in campania respectiva care o sa fie USED sau UNUSED, indiferent de statusul campaniei.


Interfata Grafica:
Pentru fiecare pagina descriu doar operatiile permise d.p.d.v grafic. Campurile si metodele fiecarei pagini sunt comentate in cod.

	AdminPage.java:
		- mosteneste clasa Page
		- permite: 
			- vizualizarea campaniilor utilizatorului autentificat sub forma de tabel ce se poate sorta
			- adaugarea unei campanii noi
			- editarea unei campanii
			- terminarea unei campanii
			- afisarea paginii unei campanii
			- aplicarea strategiei specifice unei campanii
			- intoarcerea la pagina de logare

	CampaignPage.java:
		- mosteneste clasa Page
		- permite: 
			- vizualizarea voucherelor utilizatorului autentificat sub forma de tabel ce se poate sorta
			- generarea unui nou Voucher
			- folosirea unui Voucher
			- generarea simultana a mai multor Vouchere
			- intoarcerea la pagina adminului
	
	GuestPage.java:
		- mosteneste clasa Page
		- permite:
			- vizualizarea campaniilor, voucherelor si notificarilor utilizatorului autentificat sub forma de tabel ce se poate sorta
			- intoarcerea la pagina de logare

	MainPage.java:
		- mosteneste clasa Page
		- incarca campaniile si utilizatorii in VMS
		- permite:
			- logarea unui utilizator ca ADMIN sau GUEST
	
	Page.java:
		- mosteneste clasa JFrame
		- initializeaza specificatiile pentru paginile interfetei grafice


Voi prezenta ce am adaugat in plus pentru fiecare clasa din enunt:

	ArrayMap.java:
		- un ArrayList de tip ArrayMapEntry
		- metoda toString pentru afisare

	Campaign.java:
		- metoda editCampaign:
			- editeaza campania actuala
		- metoda executeStrategy:
			- executa strategia specificata
		- metoda toString pentru afisare

	CampaignPageVoucherMap.java:
		- metoda toString pentru afisare

	GiftVoucher.java:
		- metoda toString pentru afisare	

	LoyaltyVoucher.java:
		- metoda toString pentru afisare

	Main.java:
		- contine functia main care porneste interfata grafica creand un obiect de tip MainPage

	Notification.java:	
		- metoda toString pentru afisare

	Observer:
		- interfata pentru implementarea sablonului de proiectare "Observer Pattern"

	Strategy.java:
		- interfata pentru implementarea sablonului de proiectare "Strategy Pattern"
		- contine si clasele A, B si C, reprezentand fiecare strategie posibila specificata in enunt

	Subject:
		- interfata pentru implementarea sablonului de proiectare "Observer Pattern"

	Test.java:
		- clasa pentru testarea aplicatiei folosind testele date

	User.java:
		- clasa ce implementeaza interfata Observer:
			- metoda update:
				- adauga la lista de notificari notificarea primita
		- metoda toString pentru afisare
		- metoda printNotifications:
			- returneaza un String ce contine toate notificarile primite de utilizator

	UserVoucherMap.java:
		- metoda toString pentru afisare

	VMS.java:
		- implementeaza sablonul de proiectare "Singleton Pattern", folosind:
			- campul private static VMS instance
			- contructor privat
			- metoda getInstance
		- metoda getUser:
			- intoarce utilizatorul specificat prin ID

	Voucher.java:
		- metoda toString pentru afisare


Bonus:

	Strategii:
		- am implementat cele 3 strategii de distribuire a voucherelor in interfata "Strategy.java"

	Timer:
		- am implementat un timer task care odata la 10 secunde verifica fiecare campanie daca trebuie sa inceapa sau sa se termine

	Notificari:
		- pentru utilizatorii de tip GUEST am implementat un tabel in care se pot vizualiza notificarile primite (vizibil in pagina GuestPage)

	Distribuirea multipla de vouchere:
		- in pagina CampaignPage exista posibilitatea de a se genera simultan mai multe vouchere pentru adresele de email primite din fisierul ales (am folosit JFileChooser)


