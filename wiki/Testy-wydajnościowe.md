##Narzędzia
Do testów wydajnościowych został wykorzystany program jmeter. Testowano aplikację uruchomioną lokalnie w ramach OpenShift Origin.

##Raporty

###Load test
Celem testów było sprawdzenie zachowania się aplikacji działającej pod przewidywanym obciążeniem. Wykorzystano prosty scenariusz: użytkownik loguje się, włącza kartę z indeksami, wykonuje kilka operacji (zmienia zakres czasowy prezentowanych danych, wyświetla różne indeksy), a następnie wylogowuje się. Operacje przeprowadzono w dwóch wariantach:

*  Dla 100 współbieżnych, wirtualnych użytkowników.

[Czas odpowiedzi - średni, mediana i punkt 90%](http://student.agh.edu.pl/~azarnow/iosr/performanceTests/loadTest/aggregateGraphAvgMedianAndNinety.png)

[Podsumowanie](http://student.agh.edu.pl/~azarnow/iosr/performanceTests/loadTest/summary.png)

* Dla 50 współbieżnych, wirtualnych użytkowników.

[Czas odpowiedzi - średni, mediana i punkt 90%](http://student.agh.edu.pl/~azarnow/iosr/performanceTests/loadTest/aggrGraph_50.png)

[Podsumowanie](http://student.agh.edu.pl/~azarnow/iosr/performanceTests/loadTest/summary_50.png)

Wyniki pokazują, że wąskim gardłem naszej aplikacji jest logowanie się do aplikacji, a właściwie sprawdzanie podanych przez użytkownika danych oraz generowanie strony głównej aplikacji.

Aplikacja nie spełnia wymogu użytkowania przez 100 współbieżnych użytkowników - ok 1,4% logowań do aplikacji skończyło się wyświetleniem błędu "The target server failed to respond".

### Load test z load balancingiem

Przeprowadzono analogiczny test dla 50 współbieżnych użytkowników:

[Czas odpowiedzi - średni, mediana i punkt 90%](http://student.agh.edu.pl/~azarnow/iosr/load_balanced/load/aggregate_50_2.png)

[HAProxy Report] (http://student.agh.edu.pl/~azarnow/iosr/load_balanced/load/haproxy_std_test_50_2.png)

[Podsumowanie](http://student.agh.edu.pl/~azarnow/iosr/load_balanced/load/summary_std_50_2.png)

###Stress test
W ramach stress testu postanowiliśmy przetestować tworzenie użytkowników. 2000 wirtualnych użytkowników zostało stworzonych a ich zadaniem było tworzenie użytkowników w systemie - otwarcie okna sign-up i wysłanie formularza. 

[Czas odpowiedzi - średni, mediana i punkt 90%](http://student.agh.edu.pl/~azarnow/iosr/performanceTests/stressTest/aggrGraphCreateUsers.png)

[Podsumowanie](http://student.agh.edu.pl/~azarnow/iosr/performanceTests/stressTest/summaryCreateUsers.png)

Już przy 500 użytkownikach zaczęliśmy otrzymywać błędy przy dostępie do aplikacji, a wraz ze wzrostem czasu zaczęliśmy mieć problemy także z otrzymywaniem odpowiedzi na wysłany formularz. Gdy 1000 użytkowników jednocześnie próbowało się zarejestrować do aplikacji, prawie wszystkie zapytania kończyły się błędem. Po zejściu poniżej 500 użytkowników znów wszystkie zapytania otrzymywały poprawną odpowiedź.

Po zakończonym teście aplikacja wciąż jest normalnie responsywna.

### Endurance test

W ramach testów wytrzymałościowych zostało uruchomionych 50 wirtualnych, współbieżnych użytkowników, którzy w nieskończonej pętli przez godzinę wykonywali prosty scenariusz: zalogownie do aplikacji, otwarcie strony z indeksami, wylogowanie się.

[Czas odpowiedzi - średni, mediana i punkt 90%](http://student.agh.edu.pl/~azarnow/iosr/performanceTests/enduranceTest/aggregate.png)

[Podsumowanie](http://student.agh.edu.pl/~azarnow/iosr/performanceTests/enduranceTest/summary.png)

Około 1% wszystkich logowań zakończyło się niepowodzeniem. W każdym innym zadaniu osiągnięto 100% sukces. Aplikacja odpowiada szybko, potrafi obsłużyć do 120 zapytań na sekundę. Średnio odpowiedź na zapytanie jest otrzymywana w 400ms (dla logowania jest to 1200ms).

###OpenShift Free Plan
W ramach konta darmowego OpenShift jest możliwość stworzenia aplikacji webowej, jednak testy jej wydajności nie mają sensu. Już przy 20 współbieżnych użytkownikach aplikacja przestaje działać, a co kilka sekund działania, uruchamia się ponownie.

##Wnioski
Testy wykazały jakie są grancie, dla których aplikacja działa poprawnie, wskazała miejsca wymagające poprawy, oraz które sprawiają największe problemy. Udowodniono, że dla odpowiedniej liczby użytkowników korzystanie z niej nie powinno sprawiać problemów

Load balancing zmniejszył średni czas odpowiedzi, szczególnie dla logowania do aplikacji.