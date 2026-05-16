# examen-final-ayd2

### datos: Elvis Lizandor Aguilar Tax

## comentario de refactorizacion

1. Las clase principal solo llama a las otras clases para que cada uno tenga una responsabilidad
2. Tambien crear interfaces para cada metodo, puesto que alguien mas lues puede implementar
3. Encapuslar donde hay muchas variabeles como en 

```java

public class UserControll {
    public String createUser(String name, String email, String phone,
                             String address, String city, String zip) {

    }

}

```

4. darles una unica responsibilidad
