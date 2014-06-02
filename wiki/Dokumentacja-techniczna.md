## Przypadki użycia

![Use case](http://student.agh.edu.pl/%7Ejakubiec/iosr/use-cases.jpg)

**User - użytkownik:**
* View index info - użytkownik przegląda dane o indeksie, do którego ma dostęp
* Change index - użytkownik może zmienić przeglądany indeks na inny dla niego dostępny

**Admin - administrator:**
* Create user - administrator tworzy nowego użytkownika i nadaje mu dostęp do indeksów
* Grant privileges - administrator nadaje użytkownikowi dostęp do indeksów

## Model danych

![Data model](http://student.agh.edu.pl/~jakubiec/iosr/db-model.jpg)

## Projekt systemu

![System model](http://student.agh.edu.pl/~jakubiec/iosr/system-model.jpg)

## Przebieg pobrania wartości indeksów

![Communication](http://student.agh.edu.pl/~jakubiec/iosr/seq.jpg)

## Wybór technologii

* Spring Framework
* JBoss Application Server 7.1
* PostgreSQL Database
* HornetQ
* OpenShift
* Freemarker

## Bezpieczeństwo

Bezpieczeństwo po stronie aplikacji zostało zapewnione przez:

**Spring Data oraz Hibernate** - operacje na bazie danych są wykonywane za pośrednictwem dobrze zdefiniowanego API odpornego na SQL Injection

**StrongPasswordEncryptor** - klasa wykorzystywana do szyfrowania haseł użytkownika algorytmem SHA-256. Zaszyfrowane hasła są zapisywane w bazie. Sprawdzenie poprawności hasła odbywa się również przez tę klasę, która sprawdza czy zaszyfrowana postać podanego przez użytkownika hasła zgadza się z przechowywanym.

**Spring Security** - framework jest wykorzystywany do autoryzacji oraz przechowywaniu informacji zalogowanego użytkownika. Każdy użytkownik posiada swoją rolę, a zdefiniowane interceptory przed wejściem na żądaną stronę sprawdzają czy ma do niej dostęp.

Ponadto OpenShift wykorzystuje następujące technologie związane z bezpieczeństwem:

* SELinux
* Separacja procesów, sieci i dostępu do danych
* Firewall
* Monitorowanie systemu (CPU, memory, limits)
* Detekcja intruzów (intrusion detection)
* Monitorowanie portów
* Weryfikacja i aktualizacje pakietów RPM
* Zdalne logowanie
* Szyfrowana komunikacja ssh, ssl - w wersji darmowej jest jedynie możliwe skorzystanie z certyfikatu *.rhcloud.com




