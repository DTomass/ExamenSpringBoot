# Wordle

Con este juego para la recuperacion de DDI busca hacerte reflexionar sobre temas profundos, disfrutalo Gorka:)

---
## Indice
1. [Pre-requisitos](#id1)
2. [Instalacion](#id2)
3. [Ejecucion](#id3)
4. [Funcionamiento](#id4)
5. [Estructura](#id5)
2. [Construido con](#id6)
3. [Autor](#id7)
4. [Licencia](#id8)
---

<div id='id1' />  

## Pre-requisitos 📋

* Tener Git instalado
* Tener Maven Instalado o Visual Studio Code con las herramientas Spring
* Tener libre el puerto 8080

<div id='id2' />

## Instalación 🔧

Lo unico que es necesario para instalarlo es tener la herramienta de [git](https://github.com/topics/git) instalada en el terminal, con esto ejecutaremos el comando:

```bash
git clone https://github.com/SalesianosZaragoza/recuperacion-wordle-DTomass.git
```
Veremos como se descarga la carpeta del proyecto con la que podremos trabajar, para ello, necesitaremos algun [IDE](https://es.wikipedia.org/wiki/Entorno_de_desarrollo_integrado) como [Eclipse](https://www.eclipse.org/downloads) o [Visual Studio Code](https://code.visualstudio.com), en mi caso he trabajado con este Ultimo.

<div id='id3' />  

## Ejecucion ⚙️

Para poder disfrutar de esta aventura grafica totalmente inmersiva es neceario ejecutar la aplicacion la cual se puede ejecutar de varia maneras:
* Usando [Maven](https://github.com/topics/maven) - Poniendonos en la carpeta del proyecto y ejecutando el siguiente comando.
```bash
mvn spring-boot:run
```
* Usando nuestro [IDE](https://es.wikipedia.org/wiki/Entorno_de_desarrollo_integrado) - En mi caso Visual Studio Code.

![Ejecucion](https://github.com/SalesianosZaragoza/recuperacion-wordle-DTomass/blob/main/imagenes/ejecucion.png)

Una vez ejecutado, sea de la forma que sea, tendremos nuestra aplicacion desplegada en nuestro localhost en el puerto 8080.

<div id='id4' />  

## Funcionamiento 🔩

Una vez se despliegue la aplicacion y accedamos a http://localhost:8080 veremos la pantalla de inicio del wordle. Simplista, pero con caracter.

![Funcionamiento1](https://github.com/SalesianosZaragoza/recuperacion-wordle-DTomass/blob/main/imagenes/wordleIni.png)

Cuando pulsemos en el boton de _Jugar_ pasaremos al segundo menu que nos recordara las normas del wordle en un card el cual podremos ocultar cuando queramos.

![Funcionamiento2](https://github.com/SalesianosZaragoza/recuperacion-wordle-DTomass/blob/main/imagenes/wordleIni2.png)
_Lo de diseñar no es lo mio, pido perdon :(_

Una vez estemos en este menu de inicio se nos abren tres caminos, o jugar al fin al wordle, volver al anterior menu, o jugar a un wordle decente, este ultimo boton nos redirijira a una web en la cual hay un wordle bien hecho, por si este nos cansa.

Si eliges la opcion de Empezar quizas pienses que ya podras jugar, pero no, te espera otro menu, esto se hace para mantener la tension del jugador, en este nuevo menu podemos ver lo siguiente:

![Funcionamiento3](https://github.com/SalesianosZaragoza/recuperacion-wordle-DTomass/blob/main/imagenes/previo.png)

* Un boton para volver al menu, un boton para empezar a jugar (Ahora si..) y un aviso con la siguiente informacion
* Que tipo de **Palabra** es - Un nombre, un verbo, un adjetivo...
* Numero de **Letras** que tiene - 3, 5, 8...
* Numero de **Intentos** que tienes - 20, 10, 6...

_La razon por la que se hay tantos botones para volver al menu principal es porque es desde este punto se setea la palabra a descubrir de forma aleatoria_
Se setea gracias al metodo del service _getPalabraAleatoria()_ del WordleController. Este metodo es llamado por el controlador al entrar al segundo menu.

``` java
package wordle.wordlegame.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wordle.wordlegame.model.Letra;
import wordle.wordlegame.model.palabra;
import wordle.wordlegame.repository.IPalabraRepo;

@Service
public class WordleService extends WordleAbs implements IWordleService{
    @Autowired
	IPalabraRepo repo;

	String palabraDescubrir;
	int intentosPalabra;
    
    @Override
	public void getPalabraAleatoria() {
		List<palabra> lista = repo.getListado();
		int random = new Random().ints(0, 10).findFirst().getAsInt();
		palabra palabra = lista.get(random);
		palabraDescubrir = palabra.getPalabra();
		intentosPalabra = palabra.getIntentos();
	}
}
```

De esta forma dejamos la palabra ya cargada para que cuando se entre al juego se pueda empezar directamente.

Hablando de eso, si desde este menu pulsamos el boton de **Dale** se nos abrira la propia vista del juego (Por fin...) en la cual podemos ver lo siguiente:

![Funcionamiento4](https://github.com/SalesianosZaragoza/recuperacion-wordle-DTomass/blob/main/imagenes/juego.png)

Aqui podemos ver varias cosas:

* El **input** del formulario donde podemos introducir la palabra.
* La tabla con el **Resultado** inicialmente vacia, obviamente.
* Los intentos que nos quedan.
* Un boton para volver al menu.

### Logica

Imaginemos que en el input del formulario metemos la palabra:

```
Valentin
```

En ese caso a traves del metodo post del controlador asociado al input comprueba si la palabra tiene el numero de letras necesario, despues se llama al metodo _comprobarPalabra()_ que asigna el color de la palabra segun la posicion como verde, almarillo o rojo. 

La sintaxis del metodo es la siguiente:
``` Java
@Override
    public List<Letra> comprobarPalabra(String palabraIntroducida) {
		if (palabraIntroducida.length()==palabraDescubrir.length()) {
			intentosPalabra--;
			return asignarColores(palabraIntroducida, palabraDescubrir);
		}else{
	    		return null;
		}    
}
```
El controlador vuelve a redirigirnos a la pagina, esta vez mostrando la tabla:

![Funcionamiento5](https://github.com/SalesianosZaragoza/recuperacion-wordle-DTomass/blob/main/imagenes/juego2.png)

Y si con la explicacion de antes de las reglas no te acuerdas de los colores, con la logica tampoco lo recuerdas o con simplemente usando el sentido comun no te da puedes seleccionar cada palabra y entonces aparecera una carta con un recordatorio de los colores. Que puede ser:

* Verde.  
![Funcionamiento6](https://github.com/SalesianosZaragoza/recuperacion-wordle-DTomass/blob/main/imagenes/tVerde.png)  
_Con un mensaje apelando por continuar ese camino :)_

* Amarillo.  
![Funcionamiento7](https://github.com/SalesianosZaragoza/recuperacion-wordle-DTomass/blob/main/imagenes/tAmarillo.png)  
_Con un mensaje de motivacion :)_

* Rojo.  
![Funcionamiento8](https://github.com/SalesianosZaragoza/recuperacion-wordle-DTomass/blob/main/imagenes/tRojo.png)  
_Con un mensaje con respeto referenciando el esfuerzo :)_

### Error
Que pasa si la palabra introducida no tiene el numero de letras referido o es null? Es una importante cuestion, la cual tiene una sencilla pero practica solucion, el controlador redirigira al usuario a una vista de error y se le explicara que debe hacer, no se contara como intento y volvera a jugar.

Se mostrara la siguiente vista.  
![Funcionamiento9](https://github.com/SalesianosZaragoza/recuperacion-wordle-DTomass/blob/main/imagenes/error.png)  

### Acierto
Y si he acertado la palabra? En ese caso se redirigira a otra vista que mostrara los intentos necesarios para conseguir resolverlo asi como un boton para ver cada uno de los intentos.

La vista es la siguiente.  
![Funcionamiento10](https://github.com/SalesianosZaragoza/recuperacion-wordle-DTomass/blob/main/imagenes/bien.png)   

Y los botones.  
![Funcionamiento9](https://github.com/SalesianosZaragoza/recuperacion-wordle-DTomass/blob/main/imagenes/bien2.png)  

* Uno lleva a la vista de intentos que es esta.
  ![Funcionamiento9](https://github.com/SalesianosZaragoza/recuperacion-wordle-DTomass/blob/main/imagenes/intentos.png) 
  En esta se pueden buscar los intentos por numero de intento o mostrarlos todos.  
  
* Otro vuelve al menu principal para volver a jugar, aunque con una vez ya esta bien.

### Sin intentos  
Y si no consigo averiguar la palabra en los intentos que me dan? En ese caso se te llevara a la vista de mal y se te daran unas motivadoras palabras para que vuelvas a intentarlo con mas ganas :).

![Funcionamiento9](https://github.com/SalesianosZaragoza/recuperacion-wordle-DTomass/blob/main/imagenes/mal.png) 

<div id='id5' />  

## Estructura del proyecto 📦

El proyecto se estructura en base al modelo MVC, se ha realizado con el IDE [Visual Studio Code](https://code.visualstudio.com) y cuenta con la siguiente estructura de directorios y archivos.  

![estructura](https://github.com/SalesianosZaragoza/recuperacion-wordle-DTomass/blob/main/imagenes/estructura.png)  

### Logica ⚙️

Vayamos parte por parte. toda la logica del proyecto se guarda dentro del directorio _src/main/java_ aqui se atienden las peticiones en los controllers, se guardan los repositorios de las palabras y se guarda la logica en los services, dentro de esto diferenciamos:

* Los controller que redireccionan a las diferentes paginas.  
  ![estructura2](https://github.com/SalesianosZaragoza/recuperacion-wordle-DTomass/blob/main/imagenes/controllers.png)  
  
* Los modelos de las palabras, las letras y los intentos.  
  ![estructura3](https://github.com/SalesianosZaragoza/recuperacion-wordle-DTomass/blob/main/imagenes/model.png)  
  
* Los repositorios que guardan las palabras a descubrir segun la dificultad y los intentos.  
  ![estructura4](https://github.com/SalesianosZaragoza/recuperacion-wordle-DTomass/blob/main/imagenes/repo.png)  
  
* Los services que guardan la logica de buscar las palabras, asignar los colores, etc.  
  ![estructura5](https://github.com/SalesianosZaragoza/recuperacion-wordle-DTomass/blob/main/imagenes/service.png)  

### Vistas 📄

Las vistas reflejan lo que el usuario ve, se encuetran en el directorio _src/main/resources_ junto con el application.properties que define los perfiles de dificultad, las vistas estan dentro de la carpeta templates.

Se dividen en las vistas para buscar las palabras y las vistas para el juego del wordle que estan en las carpetas de _palabras_ y _wordle_ respectivamnte, con estos tambien esta el index, que es la vista inicial o _raiz_. Cuenta con esta estructura.  

![estructura6](https://github.com/SalesianosZaragoza/recuperacion-wordle-DTomass/blob/main/imagenes/vistas.png) 

### Tests 🔧  

Para testear toda la aplicacion se han hecho tests de los siguientes apartados:

* Arranque de la aplicacion
* Controllers
* Services
* Repositories

Y cuenta con esta estructura.

![estructura7](https://github.com/SalesianosZaragoza/recuperacion-wordle-DTomass/blob/main/imagenes/test.png) 

Se ha testeado cada clase, cada repositorio, con sus metodos, cada service, cada clase abstracta y cada controlador, todos los tests han pasado, te puedes fiar de mi gorka :=)

![estructura8](https://github.com/SalesianosZaragoza/recuperacion-wordle-DTomass/blob/main/imagenes/testbien.png)  
_lo ves:)_

<div id='id6' />  

## Construido con 🛠️

_Las herramientas que utilice para crear el proyecto fueron:_

* [SpringBoot](https://spring.io/projects/spring-boot) - El framework web usado
* [Maven](https://maven.apache.org) - Manejador de dependencias
* [Visual Studio Code](https://code.visualstudio.com) - Usado como el IDE
* [Thymeleaf](https://www.thymeleaf.org) - Como motor de plantillas

<div id='id7' />  

## Autor ✒️

**Daniel Tomas Martinez** - tomas.madan21@zaragoza.salesianos.edu - 2º de Grado superior de Desarrollo de aplicaciones Multiplataforma (DAM).

<div id='id8' />  

## Licencia 📄

Este proyecto está bajo la Licencia (Ninguna, como no :)) - no busques archivos de licencia.md porque no hay.

---
⌨️ con ❤️ por [DTomass](https://github.com/DTomass) 
