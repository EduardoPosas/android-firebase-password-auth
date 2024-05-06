# Autenticación por email y password (Firebase)

Es la implementación de un sistema de inicio de sesión por medio de usuario y contraseña. Se utiliza 
el SDK de autenticación de Firebase.

## Funcionamiento

Para autenticar un usuario en la aplicación, se requiere:
1. Obtener las credenciales del usuario (email, password)
2. Pasar las credenciales al SDK de autenticación de firebase
3. Ingresadas las credenciales correctas, se puede acceder a la información básica del perfil del 
usuario y controlar el acceso del usuario a información almacenada en otros productos de Firebase.

## Características

- Android con Kotlin
- Arquitectura MVVM
- Firebase con Corutinas y Flows
- Jetpack Compose para la UI
- Hilt para inyección de dependencias

## Pantallas

1. Login

<img alt="login_screen.jpg" src="app%2Fsrc%2Fmain%2Fres%2Fdrawable%2Flogin_screen.jpg" width="250"/>

2. Sign up

<img alt="signup_screen.jpg" src="app%2Fsrc%2Fmain%2Fres%2Fdrawable%2Fsignup_screen.jpg" width="250"/>

3. Profile

<img alt="profile_screen.jpg" src="app%2Fsrc%2Fmain%2Fres%2Fdrawable%2Fprofile_screen.jpg" width="250"/>

## Referencias

1. https://www.youtube.com/watch?v=LHh2_TXBmS8
2. https://firebase.blog/posts/2022/04/building-an-app-android-jetpack-compose-firebase




