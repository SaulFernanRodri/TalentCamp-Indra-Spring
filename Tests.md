
# Guía de Tipos de Pruebas y Tecnologías Utilizadas

En un proyecto de desarrollo de software, se implementan varios tipos de pruebas para asegurar que cada parte de la aplicación funcione correctamente, tanto de forma aislada como en conjunto con otros componentes. A continuación, se detallan los principales tipos de pruebas y las tecnologías comúnmente utilizadas para cada uno.

---

## 1. Pruebas Unitarias

### Descripción
Las pruebas unitarias verifican el comportamiento de métodos o funciones individuales en clases o módulos específicos, aislándolos de otros componentes. Estas pruebas son rápidas y se ejecutan sin dependencias externas. Comumente usasdas en los services.

### Tecnologías Comunes
- **JUnit**: Biblioteca de pruebas unitarias para Java, ampliamente utilizada en proyectos Spring.
- **Mockito**: Framework de mock para simular dependencias y probar la lógica interna de los métodos.
- **AssertJ**: Librería para mejorar las aserciones en Java, proporcionando una sintaxis fluida y más legible.

Se usan normalmente para testear los servicios.

---

## 2. Pruebas de Integración

### Descripción
Las pruebas de integración de componentes verifican que dos o más componentes trabajen correctamente juntos. Estas pruebas suelen centrarse en capas específicas, como controlador y servicio, o servicio y repositorio.

### Tecnologías Comunes
- **Spring Boot Test**: Proporciona un contexto de aplicación Spring para pruebas de integración.
- **MockMvc**: Herramienta para simular peticiones HTTP y probar controladores en Spring. Usado para pruebas a nivel de controlador.

Se usan tambien para comprobar todo el flujo de la petición desde el controlador hasta el repositorio.

---
