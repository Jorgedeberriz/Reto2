Historia de usuario 1: Como usuario del sistema, quiero poder registrar nuevos clientes para poder incrementar nuestra base de datos.
Criterios de aceptación:
  Escenario 1.1: alta cliente con datos correctos (happy path)
    - Dado un usuario
    - Cuando el usuario quiere dar de alta un cliente con datos correctos
    - Entonces se da de alta el cliente en el repositorio
  Escenario 1.2: alta cliente con datos incorrectos (alternativo)
    - Dado un usuario
    - Cuando el usuario quiere dar de alta un cliente con un email incorrecto
    - Entonces salta una excepción ClienteException
  Escenario 1.3: alta cliente con datos incorrectos (alternativo)
    - Dado un usuario
    - Cuando el usuario quiere dar de alta un cliente con un NIF/CIF incorrecto
    - Entonces salta una excepción ClienteException
  Escenario 1.4: alta cliente con datos incorrectos (alternativo)
    - Dado un usuario
    - Cuando el usuario quiere dar de alta un cliente con fecha de alta incorrecta
    - Entonces salta una excepción DateTimeException

Casos de pruebas:
- Alta cliente con datos correctos
- El usuario, cuando quiere dar de alta un cliente con fecha de alta con formato incorrecto da un DateTimeException
- El usuario, cuando quiere dar de alta un cliente con nombre incorrecto le da un ClienteException
- El usuario, cuando quiere dar de alta un cliente con mail incorrecto le da un ClienteException
- El usuario, cuando quiere dar de alta un cliente con direccion incorrecta le da un ClienteException
- El usuario, cuando quiere dar de alta un cliente de tipo Personal con NIF incorrecto le da un Exception
- El usuario, cuando quiere dar de alta un cliente de tipo Empresa con CIF incorrecto le da un Exception