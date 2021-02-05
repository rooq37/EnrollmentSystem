# Integracja (punkt styku)

## Ogólne
Aplikacja udostępnia niestrzeżony endpoint `/integration`, który służy do kontaktowania 'zewnętrznego serwisu'.
Zimplementowany został jeden taki serwis i można uruchomić odpytywanie go za pomocą endpointa `/integration/courses`.
Następnie backend odpytuje serwis o najnowsze dane; jak są jakieś nowe, to zapisuje je do bazy. Zwraca nowozapisane
tylko kursy. Zapis (bądź nie) jest logowany na konsolę.

## Budowanie
Serwis jest osobną aplikacją na Kubernetes. Trzeba zbudować obraz tej aplikacji, a następnie wydeployować przy
pomocy konfiguracji `integration.yml`.

## Cron
Cron jest zawarty w `integration.yml` i na ten moment ma ustawiony wyzwalacz tak, jak backup.
