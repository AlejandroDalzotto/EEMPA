# Varano
Repositorio para el Back-end de Varano.

## Descripción
Varano es una aplicación desarrollada para ayudar a las instituciones educativas a llevar sus datos de forma segura y fácil a través de una interfaz de usuario intuitiva y brindando diferentes herramientas para ayudar a la hora de buscar datos registrados.

## Tecnologías utilizadas

- Java
- Spring Framework
- MySQL

El repositorio al front end se encuentra [aquí.](https://github.com/AlejandroDalzotto/students-app-frontend.git)

## Desarrollado por

- [Martín Coronel](https://github.com/kjistik)
- [Waldo Cuevas](https://github.com/WaldoCuevas)
- [Alejandro Dalzotto](https://github.com/AlejandroDalzotto)

### Detalles que se pueden considerar implementar
- Cuando se calcula el promedio de las notas hay que calcularlas según el curso el cual se quiere generar un registro.
- Cuando se crea un alumno se debería agregar un registro académico con el estado de regular.
- Al cargar una calificación el alumno debería estar en el curso donde se imparte la materia (O haber estado), para esto se puede hacer uso de la tabla **academic_records**.
- En la tabla student_subjects se podría agregar un campo extra que sea un enum para el estado del alumno en la materia. Regular, Aprobada, Previa, y alguno más.
- Aún no se tiene en cuenta las notas de las materias para sacar el promedio de los alumnos

# Contemplaciones

Siempre se puede mejorar algo más, la perfección no se puede alcanzar, pero si se puede intentar quedar cerca de ella, eso es el porqué de este apartado o esta recopilación de algunas contemplaciones que le puedo hacer yo como desarrollador para mejorar la aplicación, tanto del lado del servidor como de cliente, ya que me ha tocado trabajar en ambos.
A continuación divido en `back-end` y `front-end` las contemplaciones que fui observando. Las mismas no son todas para crear cambios, sino simplemente a modo de "Esto puede evitarse la próxima vez".

## Back-end:

- Eliminación de datos a través de disparadores. Probablemente, si se sigue avanzando con este proyecto y hay oportunidad de obtener ganancias o una mínima paga por mantenimiento del mismo se puede implementar en la base de datos un disparador que elimine luego de cierto tiempo un registro que tenga su atributo `active` en `false`.
- Implementación de builders y mapstruct utilizando lombok. Esto por el simple hecho de que yo he distinguido entidades (tablas de la base de datos) de los objetos que recibe (request objects) y envía el servidor (DTO). Trabajar con mapstruct quita trabajo al desarrollador para convertir estos objetos a entidades y viceversa.
- Mejorar la consistencia en los datos. En esta última iteración se me ocurrió implementar una excepción personalizada llamada `DataInconsistencyException` la cual se llama cuando validamos que los datos que se intentan cargar hagan sentido entre ellos, por ejemplo, cuando un alumno se quiere registrar en una materia que no se imparte en su curso o cuando se quiere cargar un alumno a un examen de una materia la cual él no cursa o haya cursado.
- Seguridad. Como luego aclaro abajo esto es algo que corresponde a ambos lados (servidor y cliente). Lo que se debe implementar es básicamente usar los roles que tienen asignados los usuarios y bloquear los métodos de los controllers para que solo sean accesibles por ciertos roles, esto mínimamente debería estar implementado para aquellos métodos que agregan o modifican datos en la base de datos.

## Front-end:

- Seguridad. Aún queda mucho para mejorar en ese aspecto, en el servidor aunque los usuarios tienen un atributo 'rol' este simplemente está como decoración, ya que en sí todos los aspectos de la app son accesibles para cualquier usuario. Se podría hacer que los métodos (por lo menos los que sean para mutar datos, post, put, etc.) solo sean accesibles por administradores.
- Mejora en la estructura de código. Como siempre si bien a veces lo que consideramos "código limpio" puede ser subjetivo yo considero que en el cliente hay un montón de código que se puede y debe mejorarse. Lamentablemente, la disposición a ello es escasa debido a la no retribución de una recompensa a cambio de un buen servicio prestado, como bien se dice "Lo que poco vale mucho cuesta".
- Mejora en la estructura de carpetas. Personalmente, este punto es algo ya personal el organizar las carpetas, para este proyecto decidí usar una estructura que encontre en el ejemplo "learn" de la página de Next.js el cual en sí no me parece malo, pero al ser un proyecto ya grande y con tantos componentes luego de un tiempo se vuelve algo molesto ver tanto desorden (sobre todo por la carpeta `ui` que es la que contiene los componentes). Esto se resuelve fácil usando una carpeta 'src'.

<br />
<br />

<div style="text-align: center;">
<a href="https://github.com/AlejandroDalzotto">Escrito por Alejandro Dalzotto</a>
</div>

<br />
<br />
