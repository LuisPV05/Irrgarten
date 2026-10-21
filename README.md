
# Irrgarten

Proyecto de **Programación y Diseño Orientado a Objetos (PDOO)**: implementación en Java de un juego de laberinto en el que los jugadores avanzan por el tablero, encuentran monstruos y utilizan armas y escudos durante los combates.

## Descripción

Irrgarten organiza la lógica del juego mediante clases que representan el laberinto, los jugadores, los monstruos, los equipos y los elementos de combate. El proyecto incluye interfaces de usuario tanto en modo texto como en modo gráfico.

## Funcionalidades principales

- Representación y gestión del laberinto.
- Movimiento de los jugadores mediante direcciones.
- Encuentros y combates contra monstruos.
- Gestión de armas y escudos.
- Gestión de equipos de monstruos y jugadores.
- Uso de dados para introducir aleatoriedad en las acciones del juego.
- Interfaz de usuario en modo texto y en modo gráfico.

> **Nota:** Las reglas concretas, las condiciones de victoria y los detalles de cada mecánica dependen de la implementación del código del proyecto.

## Tecnologías utilizadas

- **Java:** lenguaje utilizado para implementar la lógica y las clases del juego.
- **Apache Ant:** herramienta de compilación y ejecución mediante el archivo `build.xml`.
- **NetBeans:** entorno de desarrollo compatible con la estructura del proyecto.

## Estructura del proyecto

```text
Irrgarten/
├── src/
│   ├── controller/       # Control de la interacción entre el juego y la interfaz
│   ├── irrgarten/        # Lógica del juego y modelo de dominio
│   ├── main/             # Punto de entrada de la aplicación
│   └── UI/               # Interfaces gráficas y de texto
├── nbproject/            # Configuración del proyecto en NetBeans
├── build.xml             # Tareas de compilación de Ant
├── manifest.mf           # Manifiesto de la aplicación
└── README.md
```

## Paquete `irrgarten`

Contiene las clases principales del modelo, entre ellas:

- `Game`: coordinación y estado general de la partida.
- `Labyrinth`: representación del laberinto.
- `Player` y `Monster`: personajes que participan en el juego.
- `Weapon` y `Shield`: elementos utilizados en los combates.
- `Dice`: generación de resultados aleatorios.
- `GameState`: información del estado de la partida.
- `Directions` y `Orientation`: direcciones y orientación del movimiento.
- `Team`, `MonsterTeam` y `MonsterTrainer`: clases relacionadas con la gestión de equipos.

## Paquetes `controller`, `UI` y `main`

- **`controller`:** contiene el controlador que coordina las acciones de la partida.
- **`UI`:** contiene la interfaz común y las implementaciones de texto y gráfica.
- **`main`:** contiene la clase de entrada de la aplicación.

## Requisitos

- Tener instalado un **JDK de Java** compatible con la configuración del proyecto.
- Tener **Apache Ant** o utilizar NetBeans para compilar y ejecutar el proyecto.

## Cómo ejecutar el proyecto

### Opción 1: NetBeans

1. Descomprime el proyecto.
2. Abre NetBeans.
3. Selecciona **File → Open Project** y elige la carpeta `Irrgarten`.
4. Espera a que el IDE cargue la configuración.
5. Ejecuta el proyecto desde el IDE.

### Opción 2: Apache Ant

Desde una terminal, entra en la carpeta raíz del proyecto, donde se encuentra el archivo `build.xml`.

Para compilar el proyecto, ejecuta:

```bash
ant
```

Para consultar las tareas disponibles:

```bash
ant -p
```

La tarea concreta para ejecutar la aplicación puede depender de la configuración de `build.xml` y de la clase principal definida en el proyecto.

## Objetivo académico

El proyecto permite poner en práctica conceptos de programación orientada a objetos, como:

- Diseño de clases y responsabilidades.
- Encapsulación y organización del modelo.
- Relaciones entre objetos y colaboración entre clases.
- Separación entre lógica del juego, control e interfaz de usuario.
- Uso de herencia y polimorfismo cuando corresponde a la jerarquía de clases.
- Gestión de estados y elementos del juego.

