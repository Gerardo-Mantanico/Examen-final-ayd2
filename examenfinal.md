
#Estudiante: Luis Gerardo Marcelino Tax Mantanico 
#Carnet  201930539



# Examen Final - Analisis y Diseno de Sistemas 2

**Duracion:** 1 hora 30 minutos  
**Curso:** Ingenieria en Ciencias y Sistemas  
**Temas:** SOLID, Patrones de Diseño , Pruebas Unitarias, Refactorizacion

---

## Contexto

La empresa Code'n bugs tiene un sistema de notificaciones legacy que actualmente solo envia emails. El codigo actual funciona en produccion, pero es dificil de mantener y extender porque mezcla multiples responsabilidades en una sola clase.

La empresa ha decidido expandir su infraestructura para soportar multiples canales de notificacion (email, SMS y push notifications) y ha contratado servicios de terceros para ello. Estos servicios externos tienen interfaces incompatibles con el sistema actual.

Tu mision es refactorizar el sistema existente para hacerlo extensible, mantenible y testeable, aplicando los principios SOLID y los patrones de diseño.

El codigo legacy y los servicios externos ya estan proporcionados en el proyecto. No debes modificar las clases existentes en los paquetes `egacy` y `external`. Toda tu implementacion debe ir en el paquete `refactored`.

---

## Requerimientos del Examen

### Parte 1 – Pruebas Unitarias

Escribe pruebas unitarias para la clase legacy `NotificationService`

---

### Parte 2 – Refactorizacion con Patrones de Diseño

Crea una nueva version del sistema en el paquete `com.examen.refactored` que cumpla con los siguientes requisitos:

- Permite construir notificaciones de forma flexible y legible
- Valida campos obligatorios al momento de construir
- Provee valores por defecto para campos opcionales
- Permite agregar nuevos formatos sin modificar el código existente
- Integra los servicios externos (SMS y Push) a una interfaz común
- Maneja los casos donde un usuario no tiene teléfono o dispositivo registrado

**Aplica principios SOLID**

IMPORTANTE: Debes documentar las decisiones en tu refactorización.
