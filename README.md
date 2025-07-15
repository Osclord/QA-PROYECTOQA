# 🧪 Automatización de Pruebas - Plataforma Bioseguridad

Proyecto de automatización de pruebas funcionales para una plataforma web de control de bioseguridad. Se implementan pruebas de login, administración y evaluación usando buenas prácticas como el patrón Page Object Model (POM), con manejo de configuración externa y evidencia de resultados.

---

## ⚙️ Tecnologías Utilizadas

- **Lenguaje:** Java
- **Framework de Automatización:** Selenium WebDriver
- **Framework de Pruebas:** TestNG
- **Gestor de dependencias:** Maven
- **Manejo de WebDriver:** WebDriverManager
- **Navegador:** Chrome (puede ser configurable)
- **Evidencias:** Capturas de pantalla en caso de error y PDF descargables
- **Patrón de diseño:** Page Object Model (POM)

---

## 📁 Estructura del Proyecto

QA-PROYECTOQA/
├── src/
│ ├── main/java/
│ │ ├── pages/ # Clases POM (LoginPage, AdminPage, EvaluationPage)
│ │ └── utils/ # Utilidades (TestHelpers, ConfigReader)
│ └── resources/ # Configuración (config.properties)
├── test/java/
│ ├── base/ # Configuración base de pruebas (BaseTest)
│ └── tests/ # Casos de prueba (Login, Admin, Evaluación)
├── evidencias/ # Capturas de pantalla en ejecución
├── resultados/ # PDFs descargados
├── pom.xml # Archivo de configuración Maven
├── testng.xml # Suite de ejecución TestNG
└── README.md

## 🚀 Cómo ejecutar

1. Clona el repositorio:


git clone https://github.com/Osclord/QA-PROYECTOQA
cd qa-automation-serenity
Ejecuta las pruebas con Maven:

mvn clean test

Asegúrate de tener Java 21 y Chrome instalados.

Ejecución de las Pruebas

Ejecutar todas las pruebas
mvn test
Ejecutar con navegador visible
mvn test -Dbrowser=chrome
Ejecutar test específico
mvn test -Dtest=LoginRegisterTest
Ejecutar con TestNG XML
mvn test -DsuiteXmlFile=testng.xml


Casos de Prueba Implementados

1. Login y Registro (LoginRegisterTest):

•	testLoginWithNonExistentUser: Intenta hacer login con usuario inexistente
•	testUserRegistration: Registra un nuevo usuario con sector "Caficultor"

2. Administración (AdminCreationTest):

•	testCreateSectors: Crea 3 sectores automatizados
•	testCreateTemas: Crea 3 temas asociados a los sectores
•	testCreateSubtemas: Crea 3 subtemas asociados a los temas
•	testCreatePreguntas: Crea 3 preguntas asociadas a los subtemas

3. Evaluaciones (EvaluationTest):

•	testCreateEvaluations: Crea 3 evaluaciones con diferentes patrones de respuesta
•	testEvaluationResults: Verifica que las evaluaciones se guardaron correctamente

Evidencias y Resultados

•	Capturas de pantalla: Se generan automáticamente en evidencias/
•	PDFs descargados: Se almacenan en resultados/
•	Reportes: TestNG genera reportes HTML en target/surefire-reports/

Configuración Adicional

Cambiar navegador
Modificar en config.properties:
browser=firefox

Ajustar timeouts
Modificar en config.properties:
implicit.wait=15
explicit.wait=20

💡 Autor
Oscar Javier Bustos Solano
QA Engineer con experiencia en pruebas funcionales y automatizadas.
LinkedIn: https://www.linkedin.com/in/oscar-javier-bustos-solano-750a45211/

