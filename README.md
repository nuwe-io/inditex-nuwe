# Introducción

INDITEX amplía su equipo, y por ello se ha creado un reto para encontrar el talento que necesitan incorporar a sus filas.

En este caso, el reto será de **Backend** con Java 21. Para poder desarrollar todo lo propuesto, que se detallará mas adelante, se proporcionará un proyecto sobre el que poder partir, donde se podrá encontrar:

- **Skeleton**: estructura básica que sirve como base para no partir desde cero. 
- **Unit Test**: se proporciona una serie de test unitarios para que los participantes puedan ir comprobando su progreso.
- `data.sql` y `schema.sql`: Archivos SQL que sirven como base para desarrollar la prueba. Incluye el esquema y datos de prueba. Se importa automáticamente. Se encuentran bajo `src/main/resources`.

## **Challenge**

Para este evento se proponen cinco fases:

1. La primera fase corresponde a completar la clase `PedidoRepository.java` en **/repositories** con consultas SQL.
2. La segunda fase corresponde a la primera parte del reto algorítmico. Habrá que completar las clases `Pedido.java` y `PedidoController.java`.
3. La tercera fase corresponde a la mejora de la aplicación utilizando **Red Hat Data Grid**.
4. La cuarta fase corresponde a la segunda parte del reto algorítmico, donde se añade un grado de dificultad extra a lo propuesto en la segunda fase.
5. La quinta fase corresponde a documentación donde se detallarán ciertas cosas del proyecto y se responderán las preguntas teóricas propuestas en la **Fase 4**.

### **Primera fase - SQL**

Se proporciona una API prácticamente completa como punto de partida.
En esta fase, los participantes tendrán que:

1. Completar las clases dentro de **/repositories** para poder hacer **consultas SQL** a la BBDD. Es necesario implementar las queries en SQL de `PedidoRepository.java`. En especifico los endpoints: `findByNombreProducto`,`findByNombreCliente` y `findByNombreClienteAndNombreProducto`, además de `findAll` y `findById`.


### **Segunda fase - API y primer algoritmo**

En esta parte se pide crear un algoritmo eficiente que resuelva el siguiente problema:

Inditex ha creado un sistema de Lockers repartidos por varios puntos. Para optimizar las rutas de reparto, se han restringido a un único viaje por conductor, por lo que este debe dejar todos los pedidos realizados en un único Locker que sea el más óptimo para **todos** los clientes. La idea es minimizar la distancia total que deben recorrer los clientes para recoger sus pedidos.

Ejemplo:

Tenemos tres clientes: A, B, C y cinco Lockers: 1, 2, 3, 4 y 5. Supongamos que A hace un pedido y su Locker más cercano es el 1, este es asignado. Sin embargo, el cliente B hace un nuevo pedido, por lo que el Locker debería cambiar al que sea más óptimo para A y B.

Para ello tendrán que:

1. Completar la clase `Pedido.java` dentro de **/entities**.
2. Terminar el desarrollo de la clase `PedidoController.Java` dentro de **/controllers**. Este endpoint debe tener el siguiente comportamiento:

	- Recibe datos: id del cliente e id del producto como parámetros de query. Actualiza el stock del producto.
	- Toma la dirección del cliente que realiza el pedido y actualiza de forma automática el Locker resultante del algoritmo creado.

**INFORMACIÓN A TENER EN CUENTA: todos los puntos(direcciones) estarán basados en un plano de coordenadas cartesianas.**

**La cuarta fase requerirá de un código funcional para poder ser desarrollada, en particular de aquello desarrollado en esta segunda fase para tomarlo como punto de partida.**

### **Tercera fase - Red Hat Data Grid**

En esta fase se propone integrar Data Grid de Red Hat para mejorar el rendimiento y la escalabilidad de la aplicación. Para poder llevar a cabo esta fase, es necesario haber completado las dos primeras y tener un código base limpio y funcional.

Se propone incluir esta funcionalidad extra a la capa de acceso a los datos (repositorios), y la siguientes acciones deberán ser llevadas a cabo:

1. Anotaciones de caché en repositorios
2. Configuración de caché
3. Identificación de consultas a cachear
4. Eliminar entradas que no se hayan consultado en 2 minutos [OPCIONAL]
5. Limitar la memoria máxima a X (valor a decidir) [OPCIONAL]
6. Pruebas y ajustes

En esta fase, también se evalúa la capacidad de los participantes de trabajar con un servicio sobre el que tendrán que documentarse sobre la marcha. Se proporciona una documentación inicial, pero será labor del participante encontrar todo aquello que necesite para el desarrollo de esta fase.

[Data Grid Doc - General](https://access.redhat.com/documentation/es-es/red_hat_data_grid/8.4/html-single/data_grid_spring_boot_starter/index)

**Para esta fase, se puede añadir todo aquello que sea necesario al código base, sin alterar su comportamiento.**

**AVISO IMPORTANTE: en caso de quedarse atascado en este ejercicio, se recomienda pasar a la siguiente fase y retomarlo una vez se complete.**

### **Cuarta fase - Segundo algoritmo**

A modo de dificultad añadida, se plantea el mismo escenario que la **Fase 2** pero añadiendo obstáculos en el plano. Se proporcionarán puntos por los que el camino no puede pasar, por lo que ya no se podrán hacer caminos directos(líneas rectas) al encontrarse con uno de estos puntos, será necesario tomar otra ruta y estas deben ser planificadas alrededor de estos obstáculos. El Locker más óptimo podría variar respecto a los resultados obtenidos en la Fase 2. El objetivo final será:

Desarrollar un algoritmo capaz de encontrar el Locker más óptimo para todos los clientes de la misma forma que en la **Fase 2**, pero teniendo en cuenta los obstáculos. De esta forma se le da un toque más realista a la hora de crear una solución para este problema.

**INFORMACIÓN A TENER EN CUENTA: todos los puntos(direcciones) estarán basados en un plano de coordenadas cartesianas.**

**Información adicional**

El algoritmo debe tener en cuenta que ha de ser escalable, es decir, debe ser eficiente tanto para unos pocos puntos, como para millones de ellos.

Sea cual sea el algoritmo implementado, habrá que añadir en la documentación una respuesta a la siguientes preguntas:

- **¿Como montarías un algoritmo más óptimo computacionalmente que dé soporte a millones de clientes y pedidos?**
- **¿Puedes explicar la complejidad ciclomática del algoritmo que habéis desarrollado?**

### **Quinta fase - Documentación**

En la documentación se debe incluir lo siguiente:

- **Usage** del proyecto
- **Pasos de instalación** del proyecto para su correcto uso.
- **Lógica del algoritmo** empleado:
	- Nombre del algoritmo
	- Motivo del uso de dicho algoritmo
	- Cómo ayuda a la hora de escalar la aplicación
- **Respuestas a las preguntas propuestas en la Fase 4**

## **Tree del proyecto**
```bash
├── LICENSE
├── mvnw
├── mvnw.cmd
├── pom.xml
├── README.md
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── inditex
│   │   │           ├── controllers
│   │   │           │   ├── ClienteController.java
│   │   │           │   ├── LockerController.java
│   │   │           │   ├── ObstaculoController.java (2)
│   │   │           │   ├── PedidoController.java    (2)
│   │   │           │   └── ProductoController.java
│   │   │           ├── DataGridConfiguration.java
│   │   │           ├── entities
│   │   │           │   ├── Cliente.java
│   │   │           │   ├── Locker.java
│   │   │           │   ├── Obstaculo.java
│   │   │           │   ├── Pedido.java              (2)
│   │   │           │   └── Producto.java
│   │   │           ├── InditexApplication.java
│   │   │           ├── JacksonConfiguration.java
│   │   │           ├── repositories
│   │   │           │   ├── ClienteRepository.java
│   │   │           │   ├── LockerRepository.java
│   │   │           │   ├── ObstaculoRepository.java
│   │   │           │   ├── PedidoRepository.java    (1)
│   │   │           │   └── ProductoRepository.java
│   │   │           └── ServletInitializer.java
│   │   └── resources
│   │       ├── application.properties
│   │       ├── data.sql [Contiene los datos de ejemplo]
│   │       └── schema.sql [Contiene el esquema de BBDD]
│   └── test
│       ├── java
│       │   └── com
│       │       └── inditex
│       │           ├── ClienteControllerUnitTest.java
│       │           ├── ClienteJdbcUnitTest.java
│       │           ├── LockerControllerUnitTest.java
│       │           ├── LockerJdbcUnitTest.java
│       │           ├── ObstaculoControllerUnitTest.java
│       │           ├── ObstaculoJdbcUnitTest.java
│       │           ├── PedidoControllerUnitTest.java
│       │           ├── PedidoJdbcUnitTest.java
│       │           ├── ProductoControllerUnitTest.java
│       │           └── ProductoJdbcUnitTest.java
│       └── resources
│           ├── application.properties
│           └── testdata.sql
```
(1): clases a completar en la fase 1.

(2): clases a completar en la fase 2.

Se pueden crear tantas clases de utilidad como sean necesarias.

**El código proporcionado es completamente funcional y no hay que modificar nada durante las dos primeras fases. Tan sólo añadir todo aquello que falta para completar los objetivos propuestos.**

