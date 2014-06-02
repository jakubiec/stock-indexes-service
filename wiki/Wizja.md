## Opis problemu

Problem sprowadza się do stworzenia serwisu udostępniającego informacje o indeksach giełdowych. Centralny broker powinien zbierać i rozsyłać informacje o indeksach do brokerów konkretnych indeksów. System powinien być zbudowany zgodnie z modelem PaaS. Ponadto z otrzymane informacje powinny być dostępne po przez aplikacje zbudowaną zgodnie z modelem SaaS.

## Koncepcja rozwiązania

System będzie się składał z następujących części:
* aplikacji webowej, w której można wyróżnić część :
  * odpowiedzialnej za zbieranie i rozsyłanie informacji o indeksach - Provider
  * umożliwiającą przeglądanie danych oraz określającej dostęp do nich - Client
  * biblioteki zawierającej wspólne komponenty - Core
* bazy danych przechowującej dane o indeksach
* serwera aplikacji posiadającego zasoby takie jak:
   * pula połączeń do bazy danych
   * JMS Queue dla centralnego brookera
   * JMS Topic dla brokerów konkretnego indeksu

Wszystkie wyżej wymienione elementy będą dostępne na instancji chmury PaaS.

![Architecture](http://student.agh.edu.pl/~jakubiec/iosr/architecture.jpg)

## Wymagania funkcjonalne

* przeglądanie informacji o wybranym indeksie
* definiowanie dostępu użytkownikom do konkretnych indeksów
* periodyczne aktualizowanie informacji o indeksach

## Wymaganie niefunkcjonalne

* jako architektury PaaS należy użyć platformy Openshift
* główny broker ma rozsyłać informacje do innych kolejek
* aplikacja kliencka zgodna z architekturą SaaS, ma być multitenantna 
* należy skorzystać z własnej instancji Openshift
* do komunikacji JMS należy wykorzystać HornetQ

## Analiza ryzyka

Analiza ryzyka dla skali 1-10 (prawdopodobieństwo/skutki):

* nieznajomość technologii - 5/7
* problemy z komunikacji poszczególnych elementów - 4/8
* nakład prac związany z konfiguracja środowiska - 5/5