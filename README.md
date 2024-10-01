# Employee register

Detta projekt är ett enkelt REST API som är utformat för att hantera ett personalregister, implementerat i Java med Spring Boot. API
låter dig lägga till, ta bort och hämta anställda. Det säkerställer att flera anställda kan ha samma för- och efternamn, men e-postadressen måste vara unik för varje anställd.

## Innehållsförteckning:
- [Introduktion](#introduktion)
- [Teknologier](#teknologier)
- [Funktioner](#funktioner)
- [Installation](#installation)
- [Köra applikationen](#köra-applikationen)
- [API-endpoints](#api-endpoints)
- [Testning](#testning)
- [Designval](#designval)
- [Framtida förbättringar](#framtida-förbättringar)

## Introduktion

Etimo har fått i uppdrag att utveckla ett REST-API för en kunds personalregister. API
gör det möjligt för användare att hantera personalinformation genom att lägga till, ta bort och hämta personalposter. Systemet stödjer flera anställda med samma för- och efternamn, men kräver unika e-postadresser för varje anställd.

Målet med detta projekt är att leverera en högkvalitativ lösning som följer bästa praxis inom API-design, kodkvalitet och testning. API
är utformat för att vara underhållbart och enkelt att bygga vidare på, med tydlig dokumentation för både användare och utvecklare.  

## Teknologier
- **Java 17**
- **Spring Boot** (Web, JPA, DevTools)
- **JUnit 5** (för enhets- och integrationstester)
- **Maven** (för beroendehantering)
- **MockMvc** (för testning av HTTP-förfrågningar i integrationstester)

## Funktioner

API:et erbjuder följande funktioner:
1. **Lägg till en anställd**: Lägger till en anställd i personalregistret. Flera anställda med samma för- och efternamn tillåts, men e-postadresser måste vara unika.
2. **Ta bort en anställd**: Tar bort en anställd från registret med hjälp av deras e-postadress.
3. **Hämta alla anställda**: Hämtar en lista över alla anställda som för närvarande finns i registret.


## Installation

### Förutsättningar
- Java 17+
- Maven
- Git

### Steg
1. Klona repositoryn:
   ```bash
   git clone <repository-url>
2. Navigera till projektkatalogen:   
 ```bash
  cd employee-register-api
 ```
ersätt namnet på mappen till det du har skapat

3. Installera beroenden:
 ```bash
  mvn clean install
 ```
### Köra applikationen
Du kan köra applikationen lokalt med Maven eller din föredragna IDE (IntelliJ IDEA, Eclipse, etc.).

 **Köra via Maven** 
 ```bash
  mvn spring-boot:run
 ```
När applikationen körs kommer den vara tillgänglig på http://localhost:8080.

**Köra via IDE**
- **Öppna projektet i din IDE.**
- **Navigera till main-metoden i klassen RegisterApiApplication.** 
- **Kör applikationen.** 

### API-endpoints
API exponerar följande endpoints:
1. **Lägg till en anställd**:
- **Metod:** POST
- **URL:** /api/employees
- **Begäran Body:**
  ```bash
  {
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com"
  }
  ```
- **Svar:**
   - **201 created:** Om den anställde lades till
   - **400 Bad request:**  Om e-postaddressen redan existerar.
2. **Ta bort anställd**:
- **Metod:** DELETE
- **URL:** /api/employees/{email}
- **Svar:**
   - **200 ok:** Om den anställde togs bort
   - **400 Bad request:**  Om den anställde inte existerar.
3. **Hämta alla anställda**:
- **Metod:** GET
- **URL:** /api/employees
- **Svar:**
   - **200 ok:** och en lista över alla anställda i JSON-format.
### Testning
Projektet inkluderar både enhets- och integrationstester för nyckelfunktionaliteter med **JUnit 5** och **MockMvc**.

**Köra tester**

För att köra alla tester, använd följande Maven-kommando:
 ```bash
 mvn test
 ```
**Enhetstester**

Enhetstester används för att testa servicelagret (EmployeeService) i isolation.

**Integrationstester**

Integrationstester säkerställer att API-endpoints fungerar korrekt och interagerar med servicelagret som förväntat.

### Designval
1. **Servicelager**: Servicelagret (EmployeeService) är separerat från kontrollern för att hantera affärslogiken, vilket främjar en ren och underhållbar arkitektur.
2. **Minnebaserad lagring**: Anställda lagras i ett minnesbaserat ConcurrentHashMap. Detta ger trådsäker åtkomst till personaldata och uppfyller kravet att data inte behöver sparas mellan körningarna.
3. **Unika e-postadresser**: E-postadresser är skiftlägesokänsliga och kontrolleras för unika värden genom att använda den normaliserade (gemener och trimmade) versionen av e-poststrängen.
   
### Framtida förbättringar
1. **Databassupport**: Lägga till stöd för en databas (t.ex. PostgreSQL) för att spara personaldata mellan applikationsstarter.
2. **Validering**: Ytterligare validering (t.ex. korrekt e-postformatvalidering) och felhantering kan introduceras för att förbättra robustheten.
3. **Paginering**:  Lägga till paginering till GET /api/employees-endpoint för bättre hantering av stora dataset.


