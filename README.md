# Java Thread Exercises

Este repositorio contiene una coleccion de ejercicios practicos enfocados en **concurrencia y multithreading en Java**.  
El objetivo es aprender y aplicar conceptos fundamentales como la creacion de hilos, sincronizacion y control de condiciones de carrera.

## Contenido

- [`SaiyanRace.java`](src/main/java/thread/exercises/SaiyanRace.java): Simulacion de una carrera entre hilos utilizando la interfaz `Runnable`.

## Tecnologias

- Java 17+
- JUnit 5
- Maven

## Objetivo

Comprender los fundamentos de la **programación con hilos (threads)** en Java, incluyendo la creación, ejecución y control del flujo concurrente básico.

## Lo que aprendi

- Creacion y manejo de hilos en java
- Manejo de la interfaz Runnable
- Uso de la palabra clave `volatile` para evitar condiciones de carrera  
- Sincronización básica y control de flujo entre hilos  

## Ejecución

```bash
mvn clean install
mvn clean test
```
