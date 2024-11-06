
# Clase `Optional` en Java

La clase `Optional` en Java se introdujo en **Java 8** para manejar valores que podrían ser `null`, ayudando a evitar `NullPointerException`. `Optional` permite representar explícitamente la ausencia de un valor y proporciona métodos seguros para trabajar con ellos.

## Creación de `Optional`

- **`Optional.empty()`**: Crea un `Optional` vacío.

  ```java
  Optional<String> emptyOptional = Optional.empty();
  ```

- **`Optional.of(value)`**: Crea un `Optional` con un valor no `null`.

  ```java
  Optional<String> optional = Optional.of("Hello");
  ```

- **`Optional.ofNullable(value)`**: Crea un `Optional`, que puede estar vacío si el valor es `null`.

  ```java
  Optional<String> optional = Optional.ofNullable(someNullableValue);
  ```

## Operaciones con `Optional`

- **`isPresent()`**: Verifica si hay un valor presente.

  ```java
  if (optional.isPresent()) { ... }
  ```

- **`ifPresent(Consumer)`**: Ejecuta código si hay un valor presente.

  ```java
  optional.ifPresent(value -> System.out.println(value));
  ```

- **`get()`**: Obtiene el valor, lanza excepción si está vacío.

  ```java
  String value = optional.get();
  ```

- **`orElse(defaultValue)`**: Obtiene el valor, o el valor por defecto si está vacío.

  ```java
  String value = optional.orElse("Valor por defecto");
  ```

- **`orElseGet(Supplier)`**: Similar a `orElse`, pero ejecuta un `Supplier` para obtener el valor por defecto.

  ```java
  String value = optional.orElseGet(() -> "Valor por defecto");
  ```

- **`orElseThrow(Supplier)`**: Lanza una excepción si está vacío.

  ```java
  String value = optional.orElseThrow(() -> new RuntimeException("Valor no presente"));
  ```

## Transformar valores

- **`map(Function)`**: Transforma el valor si está presente.

  ```java
  Optional<Integer> length = optional.map(String::length);
  ```

- **`flatMap(Function)`**: Transforma el valor y aplanar `Optional` anidados.

  ```java
  Optional<String> transformed = optional.flatMap(value -> Optional.of(value.toUpperCase()));
  ```

## Ejemplo de uso

```java
Optional<String> optional = Optional.ofNullable(obtenerNombre());
String nombre = optional.orElse("Nombre desconocido");
```

`Optional` hace el código más seguro y expresivo, evitando `null` y gestionando la ausencia de valor explícitamente.
