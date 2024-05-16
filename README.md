# FormApp

FormApp es una aplicación web desarrollada con Spring y Thymeleaf que demuestra el manejo de formularios, validaciones, editores personalizados, interceptores, servicios, y más.

## Características

- **Validaciones por campos**: Uso de anotaciones para validar campos como tamaño, formato de email, etc.
- **Vistas con Thymeleaf**: Generación de vistas dinámicas y manejo de elementos HTML como `select`, `radio`, `checkbox`, entre otros.
- **Métodos handle**: Métodos para recibir y procesar datos del formulario.
- **Manejo de errores**: Gestión de errores con anotaciones y archivos `errors.properties`, además de manejo de errores personalizados.
- **Model Attribute**: Mapeo de datos con el objeto `Model` para transferir datos entre el controlador y la vista.
- **Session Attributes**: Uso de `@SessionAttributes` para mantener datos en la sesión del usuario.
- **Data Binding**: Uso de `initBinder` para manejar tipos de datos como `Date` y obtener objetos completos mediante editores personalizados.
- **Anotaciones Personalizadas**: Creación y uso de anotaciones personalizadas para la validación y procesamiento de datos.
- **Persistencia de Datos**: Mantenimiento de datos previamente llenados en el formulario cuando hay errores de validación.
- **Diseño con CSS y Bootstrap**: Uso de CSS y Bootstrap para el diseño y estilización de la aplicación.
