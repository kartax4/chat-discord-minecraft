# Bot Discorda do Komunikacji z Serwerem Minecraft

Bot Discorda, który umożliwia komunikację pomiędzy graczami na serwerze Minecraft a serwerem Discord poprzez REST API.

## Wymagania

Aby uruchomić bota, należy spełnić następujące wymagania:

- Python 3.7 lub nowszy
- Zainstalowany menadżer pakietów pip
- Virtual Environment (venv)

## Instalacja

1. Utwórz wirtualne środowisko (venv) w katalogu projektu:
    ```bash
    python3 -m venv venv
    ```

2. Aktywuj wirtualne środowisko:
    - Na systemach Unix/Linux:
        ```bash
        source venv/bin/activate
        ```
    - Na systemie Windows (w wierszu poleceń cmd):
        ```bash
        venv\Scripts\activate
        ```

3. Zainstaluj zależności projektu z pliku `requirements.txt`:
    ```bash
    pip install -r requirements.txt
    ```

4. Utwórz plik `.env` w katalogu głównym projektu i dodaj do niego TOKEN bota:
    ```plaintext
    TOKEN=<twój_token_bota>
    ```

## Uruchomienie

Aby uruchomić serwer oraz bota, użyj poniższej komendy, zastępując `<ip_serwera>` odpowiednim adresem:
```bash
uvicorn main:app --reload --port 8000 --host <ip_serwera>
