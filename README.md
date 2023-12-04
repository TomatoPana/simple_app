# Proyecto de Desarrollo de dispositivos móviles

La siguiente aplicación móvil implementa un sistema básico de registro, donde se toman los datos de diferentes personas
y son guardadas en una pequeña base de datos para su almacenamiento. Como tal no existe un procesamiento de datos sino
que la aplicación realiza un rol de almacén de datos.

## Descripción básica

La aplicación no requiere de un inicio de sesión e inmediatamente es presentado en una actividad con tres fragmentos en
el: Un listado de todos los registros activos, una vista con contadores e información global de la base de datos y una
ultima vista con todos las imágenes de perfil creados.
En todas las vistas, al ser fragmentos está disponible un FAB (Floating Action Button o Botón de acción flotante) el
cual al ser presionado, abre una nueva actividad la cual presenta un formulario para el registro de nuevos datos.
En dicho formulario se solicitan datos básicos de la persona la cual quiere ser registrada en el sistema:

- Nombres: Obligatorio. Texto
- Apellidos: Obligatorio. Texto
- Fecha de nacimiento: Obligatorio. Texto
  - Se puede escribir en el formato YYYY-MM-DD o dar doble click para usar el diálogo de selección de fecha
- Hora de nacimiento: Opcional. Texto
  - Se puede escribir en formato 24Hrs HH:ss o dar doble click para usar el diálogo de selección de fecha
- Genero: Obligatorio. Spinner
  - En caso de seleccionar la opción "Otro", se desbloquea un cuadro de texto donde se requiere de introducir el genero deseado
- Nivel educativo CETI: Obligatorio. RadioGroup/Radio Button
- Añadir comentarios: Opcional. Toggle Button
  - Si se desean añadir comentarios, se desbloquea un TextArea para introducir comentarios, con un limite de 1000 caracteres.
- Preferencias: Opcional. CheckboxGroup/Checkbox
  - Existen 8 preferencias preestablecidos y aparte, una especial llamado "Ninguno" el cual desactiva todos los CheckBox y los omite de ser guardados.

Al final se presenta un botón para guardar los cambios. En caso de pulsar el botón de atrás, un dialogo de confirmación se abrirá para confirmar la acción. Además de eso, existe un menú superior con dos opciones:

- Cancelar: Realiza la misma acción que el botón de atrás. Si se presiona, se confirma la acción y se sale de la actividad
- Limpiar: Si se presiona, se solicita confirmación y de ser así, solo limpia todos los campos pero no se sale de la actividad.

En la actividad principal, en el fragmento de todos los registros, existe la posibilidad de darle click a cada registro, lo que abrirá la misma actividad de Crear un nuevo registro, con la diferencia de que todos los campos ya estarán llenos con los campos previamente creados y en solo lectura.

Si se mantiene presionado un registro en la actividad principal, se abre un menú cuyo contenido es solamente "Borrar registro", el cual, solicita confirmación, y de ser afirmativa, el registro se elimina.
