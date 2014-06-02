###Narzędzia
Do testów bezpieczeństwa został wykorzystany framework w3af. Aplikacja została umieszczona na darmowej domenie OpenShift (http://client-stockindexes.rhcloud.com/). Użyto domyślnej konfiguracji dla kilku pre definiowanych testów. Poniżej linki do plików zawierających informacje o wykonanych testach. 

###Raporty

**[OWSAP-TOP10 Test](http://student.agh.edu.pl/~jakubiec/iosr/reports/security/report_owsap.html)**

* niezidentyfikowany błąd 500, 400, 405 po stronie aplikacji
* dostępne informacje o typie oraz serwerze aplikacji

**[High-Risk Test](http://student.agh.edu.pl/~jakubiec/iosr/reports/security/report_high_risk.html)**

* j.w
* dozwolone wykonanie metod  DELETE, GET, HEAD, OPTIONS, PATCH, POST, PUT, TRACE dla zasobów


**[Bruteforece Test](http://student.agh.edu.pl/~jakubiec/iosr/reports/security/report_bruteforce.html)**

* raport de facto wskazał, że znalazł login i hasło, jednak znalezione dane są niepoprawne.


**[Fast-Scan Test](http://student.agh.edu.pl/~jakubiec/iosr/reports/security/report_fast_scan.html)**

* formularze oraz pola tekstowe posiadają zdolność autouzepłniania


**[Web-Infrasctructure Test](http://student.agh.edu.pl/~jakubiec/iosr/reports/security/report_web.html)**

* brak ewidetnych, nowych problemów

###Wnioski

Testy wykazały, że po stronie aplikacji znajduje się klika luk, które w prawdzie nie pozwalają na dostęp i manipulacje danymi aplikacji, ale powodują niespodziewane kody błędów. Ponadto testy mogę być obarczone błędami jak w przypadku BRUTEFORCE







