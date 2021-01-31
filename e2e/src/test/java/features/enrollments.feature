Feature: Enrollments

  Scenario: E2E
    Given open Home Page
    When select "Kierunek" "Informatyka stosowana" on the Enrollment Home Page
    And select "Blok zapisów" "Zapisy Lato 2020/2021" on the Enrollment Home Page
    Then check if "Informacje" section contains "Ograniczenia: Nie" on the Enrollment Home Page
    And check if "Informacje" section contains "Rozpoczęcie bloku: 30.01.2021 08:00" on the Enrollment Home Page
    And check if "Informacje" section contains "Zakończenie bloku: 30.01.2021 22:00" on the Enrollment Home Page
    And check if "Informacje" section contains "Korekty: Nie" on the Enrollment Home Page
    And check if "Informacje" section contains "Rozpoczęcie studenta: 30.01.2021 08:00" on the Enrollment Home Page
    And check if "Informacje" section contains "Zakończenie studenta: 30.01.2021 22:00" on the Enrollment Home Page
    And check if "Bieżące kursy" table contains "Praca dyplomowa II" on the Enrollment Home Page
    And check if "Bieżące kursy" table contains "Przetwarzanie dużych zbiorów danych" on the Enrollment Home Page
    And check if "Bieżące kursy" table contains "Etyka nowych technologii" on the Enrollment Home Page
    And check if "Bieżące kursy" table contains "Przetwarzanie dużych zbiorów danych" on the Enrollment Home Page
    And check if "Bieżące kursy" table contains "Systemy wyszukiwania informacji" on the Enrollment Home Page
    And check if "Bieżące kursy" table contains "Seminarium dyplomowe" on the Enrollment Home Page
    And check if "Bieżące kursy" table contains "Systemy wyszukiwania informacji" on the Enrollment Home Page
    And check if "Zaległe kursy" table contains "Progr. współbieżne i funkcyjne" on the Enrollment Home Page

    When click button "Przejdź do zapisów" on the Enrollment Home Page
    Then check if "Kierunek" label contains "Informatyka stosowana (W8_INF)" on the Group Enrollment Page
    And check if "Blok zapisów" label contains "Zapisy Lato 2020/2021" on the Group Enrollment Page
    When select "Kursy" "Praca dyplomowa II - Praca dyplomowa" on the Group Enrollment Page
    Then check if "Grupy" table contains "Z01-01a" on the Group Enrollment Page
    And check if "Grupy" table contains "Z01-02a" on the Group Enrollment Page
    When click button "Zapisz" in the row "Z01-01a" in the "Grupy" table on the Group Enrollment Page
    Then check if "Błąd" dialog contains "Zapisy Lato 2020/2021" on the Message Dialog
    And check if "Błąd" dialog contains "jeszcze się nie rozpoczął lub już się skończył!" on the Message Dialog
    When click button "Ok" on the Message Dialog

    When click button "Zapisy" on the Topbar
    And select "Kierunek" "Informatyka stosowana" on the Enrollment Home Page
    And select "Blok zapisów" "Korekty zapisów Lato 2020/2021" on the Enrollment Home Page
    Then check if "Informacje" section contains "Ograniczenia: Nie" on the Enrollment Home Page
    And check if "Informacje" section contains "Rozpoczęcie bloku: 31.01.2021 08:00" on the Enrollment Home Page
    And check if "Informacje" section contains "Zakończenie bloku: 07.02.2021 22:00" on the Enrollment Home Page
    And check if "Informacje" section contains "Korekty: Nie" on the Enrollment Home Page
    And check if "Informacje" section contains "Rozpoczęcie studenta: 31.01.2021 08:00" on the Enrollment Home Page
    And check if "Informacje" section contains "Zakończenie studenta: 07.02.2021 22:00" on the Enrollment Home Page
    And check if "Bieżące kursy" table contains "Praca dyplomowa II" on the Enrollment Home Page
    And check if "Bieżące kursy" table contains "Przetwarzanie dużych zbiorów danych" on the Enrollment Home Page
    And check if "Bieżące kursy" table contains "Etyka nowych technologii" on the Enrollment Home Page
    And check if "Bieżące kursy" table contains "Przetwarzanie dużych zbiorów danych" on the Enrollment Home Page
    And check if "Bieżące kursy" table contains "Systemy wyszukiwania informacji" on the Enrollment Home Page
    And check if "Bieżące kursy" table contains "Seminarium dyplomowe" on the Enrollment Home Page
    And check if "Bieżące kursy" table contains "Systemy wyszukiwania informacji" on the Enrollment Home Page
    And check if "Zaległe kursy" table contains "Progr. współbieżne i funkcyjne" on the Enrollment Home Page

    When click button "Przejdź do zapisów" on the Enrollment Home Page
    Then check if "Kierunek" label contains "Informatyka stosowana (W8_INF)" on the Group Enrollment Page
    And check if "Blok zapisów" label contains "Korekty zapisów Lato 2020/2021" on the Group Enrollment Page
    When select "Kursy" "Praca dyplomowa II - Praca dyplomowa" on the Group Enrollment Page
    Then check if "Grupy" table contains "Z01-01a" on the Group Enrollment Page
    And check if "Grupy" table contains "Z01-02a" on the Group Enrollment Page
    When click button "Zapisz" in the row "Z01-01a" in the "Grupy" table on the Group Enrollment Page
    Then check if "Błąd" dialog contains "Grupa Z01-01a należąca do kursu Praca dyplomowa II (INZ002380D) jest już pełna!" on the Message Dialog
    When click button "Ok" on the Message Dialog

    And click button "Zapisz" in the row "Z01-02a" in the "Grupy" table on the Group Enrollment Page
    Then check if "Sukces" dialog contains "Pomyślnie zapisano do grupy Z01-02a!" on the Message Dialog
    When click button "Ok" on the Message Dialog

    And select "Kursy" "Przetwarzanie dużych zbiorów danych - Projekt" on the Group Enrollment Page
    Then check if "Grupy" table contains "Z01-01c" on the Group Enrollment Page
    And check if "Grupy" table contains "Z01-02c" on the Group Enrollment Page
    And check if "Grupy" table contains "Z01-03c" on the Group Enrollment Page
    When click button "Zapisz" in the row "Z01-01c" in the "Grupy" table on the Group Enrollment Page
    Then check if "Sukces" dialog contains "Pomyślnie zapisano do grupy Z01-01c!" on the Message Dialog
    When click button "Ok" on the Message Dialog

    And click button "Zapisz" in the row "Z01-01c" in the "Grupy" table on the Group Enrollment Page
    Then check if "Błąd" dialog contains "Jesteś już zapisany do grupy Z01-01c!" on the Message Dialog
    When click button "Ok" on the Message Dialog

    And click button "Zapisz" in the row "Z01-02c" in the "Grupy" table on the Group Enrollment Page
    Then check if "Błąd" dialog contains "Jesteś już zapisany na kurs Przetwarzanie dużych zbiorów danych - Projekt (INZ002419P)!" on the Message Dialog
    When click button "Ok" on the Message Dialog

    Then check if "Bieżące kursy" table contains "Z01-02a" on the Group Enrollment Page
    And check if "Bieżące kursy" table contains "Z01-01c" on the Group Enrollment Page
    When click button "Wypisz" in the row "Z01-02a" in the "Bieżące kursy" table on the Group Enrollment Page
    Then check if "Sukces" dialog contains "Pomyślnie wypisano z grupy Z01-02a!" on the Message Dialog
    When click button "Ok" on the Message Dialog
    And click button "Wypisz" in the row "Z01-01c" in the "Bieżące kursy" table on the Group Enrollment Page
    Then check if "Sukces" dialog contains "Pomyślnie wypisano z grupy Z01-01c!" on the Message Dialog
    When click button "Ok" on the Message Dialog
