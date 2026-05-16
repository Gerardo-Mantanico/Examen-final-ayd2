#Estudiante: Luis Gerardo Marcelino Tax Mantanico 
#Carnet  201930539



# Decisiones de Refactorización - Principios SOLID

El codigo original tenia todo en un solo metodo, formateaba, enviaba y logeaba. Se refactorizo separando responsabilidades.

##  Single Responsibility
La clase `NotificationService` hacia demasiado. Se separo en:
- Formatters (`AlertFormatter`, etc.) solo formatean
- Senders (`EmailNotificationSender`, `SMSAdapter`, `PushAdapter`) solo envian
- `NotificationDispatcher` solo coordina

## Open/Closed
Antes para agregar un tipo nuevo habia que modificar los if-else. Ahora se crea una nueva clase que implemente `MessageFormatter` o `NotificationSender` sin tocar lo que ya funciona.

## Liskov Substitution
Cualquier sender puede usarse donde se espera un `NotificationSender` sin romper nada. Las implementaciones respetan el contrato de la interfaz.

## Interface Segregation
Se hicieron dos interfaces pequeñas en vez de una grande:
- `MessageFormatter` → solo `format()`
- `NotificationSender` → solo `send()`

## Dependency Inversion
El `NotificationDispatcher` recibe la lista de senders por constructor en vez de instanciarlos el mismo. Los adapters tambien reciben sus dependencias externas por constructor, lo que facilita el testeo.
