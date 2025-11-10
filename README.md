# Java Thread Exercises

Este repositorio contiene una coleccion de ejercicios practicos enfocados en **concurrencia y multithreading en Java**.  
El objetivo es aprender y aplicar conceptos fundamentales como la creacion de hilos, sincronizacion y control de condiciones de carrera.

## Contenido

- [`SaiyanRace.java`](src/main/java/thread/exercises/SaiyanRace.java): Simulacion de una carrera entre dos hilosrepresentando a dos persinajes de dragon ball.
- [`BattlePokemon.java`](src/main/java/thread/exercises/SaiyanRace.java): Simulación de una batalla por turnos entre dos hilos utilizando sincronización y comunicación entre threads.
- [`HorcruxHunters.java`](src/main/java/thread/exercises/HorcruxHunters.java): Simulación de una búsqueda competitiva entre tres hilos, donde el primero en encontrar un objetivo detiene a los demás.
- [`DroisFactory.java`](src/main/java/thread/exercises/DroisFactory.java): Simulación de una fábrica de drois con múltiples hilos productores y consumidores, utilizando una cola compartida y sincronizacion.
- [`QuidditchMatch.java`](src/main/java/thread/exercises/QuidditchMatch.java): Simulación de un partido de Quidditch con varios hilos representando los dos equipos y el buscador, utilizando sincronización para manejar eventos del juego.
-[`JediExplorers.java](src/main/java/thread/exercises/JediExplorers.java): Simulación de una búsqueda entre dos hilos, donde el primero en hallar una pista interrumpe la exploración de los demás.

## Tecnologias

- Java 17+
- JUnit 5
- Maven

## Objetivo

Comprender los fundamentos de la **programación con hilos (threads)** en Java, incluyendo la creación, ejecución y control del flujo concurrente básico.

## Lo que aprendi

- Creacion y manejo de hilos en java
- Manejo de la interfaz Runnable
- Uso de la palabra clave `volatile` para variables compartidas
- Sincronización entre hilos con `synchronized`
- Coordinación de hilos mediante `wait()` y `notifyAll()`

## Ejecución

```bash
mvn clean install
mvn clean test
```
