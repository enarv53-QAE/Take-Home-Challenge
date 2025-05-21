Take-Home Challenge - QA Automation

Este proyecto fue desarrollado como parte de una prueba técnica para la vacante de Tester Automatizador.

 Descripción

Automatiza un flujo de búsqueda en Mercado Libre México, realizando los siguientes pasos:

Selecciona el país: México

Busca el término "playstation5"

Aplica los filtros:

Condición: Nuevos

Ubicación: CDMX

Ordena los resultados por precio: mayor a menor

Extrae los primeros 5 productos mostrados

 Tecnologías utilizadas

Selenium WebDriver

Java

Maven

WebDriverManager

IntelliJ IDEA

 Ejecución

Clona el repositorio:

git clone https://github.com/enarv53-QAE/Take-Home-Challenge.git
cd Take-Home-Challenge

Asegúrate de tener Java y Maven instalados:

java -version
mvn -version

Ejecuta el proyecto:

mvn clean compile exec:java -Dexec.mainClass="Main"

Cambia Main por el nombre completo de tu clase si está en un paquete.

 Estructura del proyecto

Take-Home-Challenge/
├── src/
│   └── main/
│       └── java/
│           └── Main.java
├── pom.xml
├── .gitignore
└── README.md

 Autor

Eduardo Martínez Narváez

QA Automation Engineer

 narvaez5307@hotmail.com