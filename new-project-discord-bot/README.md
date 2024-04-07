<h1>Bot do discorda który poprzez REST API będzie umożliwiał komunikację pomiędzy graczami na serwerze minecraft a serwerem discord </h1>
<br>
Aby uruchomić bota należy utworzyć venv następnie pobrac zależności z pomocą <br>
pip install -r requirements.txt <br>
oraz stworzyć plik .env i dodać do niego TOKEN bota

Aby odpalić serwer oraz bota należy uzyć komendy uvicorn main:app --reload --port 8000 --host <ip_serwera>


Bot używa bibliotek:
py-cord
fastapi
dotenv<br>

