<!--
Link a google docs para ver el informe: https://docs.google.com/document/d/13T3bP0Rvs13mjeTZq3B0dRh9En3OLh_HDWBtm41YBCA/edit?usp=sharing
-->

<h1>
    <center> [75.07 / 95.02]</center>
    <center> Algoritmos y programacion III</center>
</h1>

<h2><center> Trabajo practico 2: AlgoDefense</center></h2>
<br><br>

### Estudiantes:

| Nombre                   | Padron | Mail                 |
| ------------------------ | ------ | -------------------- |
| Otero Silvera Maximiliano| 108634 | motero@fi.uba.ar     |
| Pla Tomas Alberto        | 106705 | tpla@fi.uba.ar       |
| Oviedo Ignacio Sebastian | 109821 | ioviedo@fi.uba.ar    |
| Fernandez Lucas          | 109250 | lufernandez@fi.uba.ar|
| Velurtas Joaquin Mateo   | 109655 | jvelurtas@fi.uba.ar  |

### Tutor: Diego Sanchez
<br><br><br>
___________________________________________



## <center><strong> Supuestos </strong></center>

<dl>
    <dt> <h3 style="text-decoration: underline">Largada y Meta </h3> </dt>
        <dd align="justify">
            Se tomó a la primera pasarela registrada como la Largada y a la última en registrarse como la Meta.
        </dd>

<br>
    <dt> <h3 style="text-decoration: underline"> TrampaArenosa y Topo </h3> </dt>
        <dd align="justify">
            Un topo al pasar por una TrampaArenosa cambia su estado EsSubterraneo a NoEsSubterraneo y resulta vulnerable ante las torres.
        </dd>
<br>
    <dt> <h3 style="text-decoration: underline"> Parcela y defensa destruida </h3> </dt>
        <dd align="justify">
            Al destruirse una defensa, la parcela queda ocupada por la misma en su estado destruido por lo cual esa parcela queda ‘inutilizada’.
        </dd>
<br>
    <dt> <h3 style="text-decoration: underline"> Estadísticas no mencionadas </h3> </dt>
        <dd align="justify">
            Algunas estadísticas no mencionadas en el enunciado (ej. energía de Topo) se tomaron arbitrariamente. 
        </dd>
<br>
    <dt> <h3 style="text-decoration: underline"> Ataques de TrampaArenosa </h3> </dt>
        <dd align="justify">
            La TrampaArenosa resultaba muy complicada de utilizar ya que para poder ralentizar un enemigo este justo debia caer en la parcela en la cual estaba colocada la misma. Por lo cual decidimos en que si el enemigo al moverse pasa por la trampa este se vea ralentizado. 
        </dd>
</dl>

<br><br><br>

___

<center> <h2> <strong> Detalles de implementacion </strong></h2> </center>
<br>

<h3> <strong> Modelo </strong> </h3>

<p align="justify">
    El modelado para la solución de este problema consta de una clase Partida, con la cual interactúa el usuario a través de la GUI. Además de delegar a la clase Turno para que el juego actúe, la Partida está compuesta por un objeto Mapa y Turno, dependiendo de CreadorMapa para crear el mapa. 
</p>

<p align="justify">
    Mapa compuesto de parcelas, delega a la Parcela que le indica Partida la construcción de una Defensa, dependiendo de qué clase de Defensa y que clase de Parcela se trate se aceptará la defensa o se arroja una excepción. En caso de que esta defensa se construya correctamente, se agregara a la lista defensas de turno. 
</p>

<p align="justify">
    Parcela tiene una Posición para saber en qué parte del mapa está ubicada, además es una abstracción la cual heredan Tierra, Pasarela, Meta, Largada y Rocoso cumpliendo la relación ‘es un’. Las parcelas se encargan de contener los enemigos que se encuentran en tal posición.
</p>

<p align="justify">
    En el caso de Tierra y Pasarela pueden aceptar una defensa (TorreBlanca (y sus subclases) y TrampaArenosa respectivamente, en caso contrario se arroja una excepción) la construcción de la misma es delegada a Construible (interfaz la cual implementan EsConstruible y NoEsConstruible) [Concluyendo con esta delegación en un patrón de diseño State, se utilizó este patrón ya que este tipo de Parcela tienen dos estados construible y no construible, y nos permite resolver de forma polimórfica este caso de la construcción].
</p>

<p align="justify">
    Por otro lado, Turno tiene un Jugador el cual está compuesto por Créditos al cual le delega todo lo relacionado a el dinero del juego, el Jugador en sí se encarga de recibir daño de Enemigo(s).
</p>

<p align="justify">
    Turno tiene un Camino el cual lo utiliza para aparecer los enemigos y pasarselo a Enemigo(s). Para saber qué enemigos aparecer Turno depende de CreadorEnemigos el cual requiere el número del turno para saber que tipo de enemigos y que cantidad crear.
</p>

<p align="justify">
    Camino está compuesto por Parcela(s), generalmente Pasarela(s) aunque hay excepciones las cuales utiliza un tipo de enemigo (Lechuza). Camino se encarga de delegar a las parcelas la administración de los enemigos, por su parte sabe mover a los enemigos a la parcela indicada.
</p>

<p align="justify">
    Turno además, tiene Enemigo(s) y Defensa(s) las cuales les indica en cada turno que deben actuar.
</p>

<p align="justify">
    Defensa es una abstracción que delega a la interfaz SaludDefensa la administración por turno de la misma (a SaludDefensa la implementan Operativa y Destruida) [Este caso también es una implementación del patrón State ya que tenemos ambos estados de la Defensa y necesitamos resolverlos de manera polimórfica]. A Defensa la heredan TorreBlanca y TrampaArenosa.
</p>

<p align="justify">
    TrampaArenosa delega a ObjetivoTrampa el buscar a qué objetivos atacar (lista de enemigos) y luego de tres veces turnos esta pasará a su estado Destruida.
    A TrampaArenosa la hereda NoTrampa [Patron NullObject, ya que cuando se agrega un enemigo a una pasarela la pasarela le envia el mensaje a la trampa de que debe atacar y tal vez no hay una trampa construida en tal pasarela]
</p>

<p align="justify">
    TorreBlanca delega a ObjetivoTorre el buscar a qué objetivo atacar (el más cercano y en rango a la misma), antes de poder atacar la torre se encuentra en un EstadoDesactivado y luego de X turnos se cambia a EstadoActivado, ambos estados saben administrar por turno que hacer [Otro caso de utilización del patrón State (interfaz de nombre Estado)]. A TorreBlanca la heredan NoTorre [Patrón NullObject, ya que hay casos en los que se requiere una torre como objetivo pero puede que el jugador no haya construido una torre aún] y TorrePlateada (una mejora en estadísticas de TorreBlanca).
</p>

<p align="justify">
    Por otro lado, Enemigo es una abstracción que utiliza el patrón State, al igual que las Defensa(s) la interfaz SaludEnemigo (que la implementan Vivo y Muerto), además implementa dos patrones Strategy, la interfaz Volador (que la implementan NoEsVolador y EsVolador) y la interfaz Subterráneo (que la implementan NoEsSubterraneo y EsSubterraneo). La razón de esta elección de patrones es resolver de forma polimórfica los casos.
</p>

<p align="justify">
    A Enemigo lo heredan Arania, NoEnemigo [patrón NullObject, misma razón que para NoTorre], Hormiga, Topo y Lechuza.
</p>

<p align="justify">
    Los enemigos para moverse delegan a su camino que los mueva tantas veces como velocidad les quede o lleguen a la meta.
    Por su parte los enemigos saben como les afectan las defensas del usuario (recibir danio o ser ralentizados).
</p>

<p align="justify">
    Lechuza tiene asociado ObjetivoLechuza ya que le delega la decisión de a que defensa atacar. Lechuza para saber por dónde moverse depende de Ruta, una interfaz la cual implementan RutaL y RutaH [Patron strategy]. Clases las cuales dependen de CreadorCaminoL y CreadorCaminoH respectivamente para otorgarle a la lechuza el camino que requiere.
</p>

<p align="justify">
    Por último, SingleLogger es el Logger del juego el cual implementa el patrón de diseño singleton para estar disponible globalmente a cambio de romper el principio de única responsabilidad.
</p>

<h3> <strong> Vista </strong> </h3>

<p align="justify">
    Al momento de comunicar a la vista y los controladores con el modelo, para mantener mvc, se propuso aplicar el patrón de diseño de Observers sin embargo al explorar las opciones decidimos que sería mejor trabajar mediante getters en donde la vista o el controlador le preguntaran el estado al modelo cada vez que deba actualizarse. Si bien esto compromete el principio de “tell dont ask”, la alternativa de utilizar Observers nos pareció más confusa en el código y generaba conflicto con el principio de responsabilidad única al agregar la responsabilidad de implementar un observer a cada clase que podía actualizar su estado.
    Aun sabiendo que violar Tell dont ask es mayor a violar Single responsability nos guiamos por cual opcion era mas simple de desarrollar.
</p>

<br><br><br>

___________

<center> <h2><strong> Excepciones </strong></h2> </center>

<br><br>

<dl>
    <dt> <h3 style="text-decoration: underline">CreditosInsuficientesError </h3> </dt>
        <dd align="justify">
            Esta excepción analiza el caso en el que los créditos a gastar superen a los créditos actuales, en caso afirmativo se lanza la excepción.
        </dd>

<br>
    <dt> <h3 style="text-decoration: underline"> DefensaEnTerrenoErroneoError </h3> </dt>
        <dd align="justify">
            Esta excepción analiza el caso en el que se quiera colocar una instancia de defensa de un tipo no compatible con la parcela, en caso afirmativo se lanza la excepción
        </dd>
<br>
    <dt> <h3 style="text-decoration: underline"> EnemigoNoRalentizableError </h3> </dt>
        <dd align="justify">
            Esta excepción la lanza un enemigo al querer ralentizarlo y no se pueda.
        </dd>
<br>
    <dt> <h3 style="text-decoration: underline"> GanarPartidaError </h3> </dt>
        <dd align="justify">
            Esta excepción analiza el caso en el que la lista de enemigos no tiene ningún enemigo restante, en caso afirmativo se lanza la excepción.
        </dd>
<br>
    <dt> <h3 style="text-decoration: underline"> PerderPartidaError </h3> </dt>
        <dd align="justify">
            Esta excepción analiza el caso en el que el jugador tiene vida menor o igual a cero, en caso afirmativo se lanza la excepción.
        </dd>
<br> 
    <dt> <h3 style="text-decoration: underline"> SpawnNoEnLargadaError </h3> </dt>
        <dd align="justify">
            Esta excepción la lanzan todas las parcelas excluyendo la Largada al querer utilizar el método aparecerEnemigos().
        </dd>
<br>
    <dt> <h3 style="text-decoration: underline"> TerrenoDeConstruccionInvalidoError </h3> </dt>
        <dd align="justify">
            Esta excepción la lanzan todas las parcelas excluyendo Tierra y Pasarela al querer utilizar el método construir().
        </dd>
</dl>

<br><br><br>

_______________

<center><h2><strong> Diagramas de Clase </strong></h2></center>

<br><br>

<h2 style="text-decoration: underline"> Partida </h2>

<img src="../diagrams/classes/Partida.png">

<h2 style="text-decoration: underline"> Turno </h2>

<img src="../diagrams/classes/Turno.png">

<h2 style="text-decoration: underline"> Jugador </h2>

<img src="../diagrams/classes/Jugador.png">

<h2 style="text-decoration: underline"> Mapa </h2>

<h3> Mapa </h3>

<img src="../diagrams/classes/Mapa.png">

<h3> Creadores de parcelas y Parser </h3>

<img src="../diagrams/classes/Creadores.png"  width="1000" height="250">

<h3> Camino </h3>

<img src="../diagrams/classes/Camino.png">

<h3> Posicion </h3>

<img src="../diagrams/classes/Posicion.png">

<h2 style="text-decoration: underline"> Parcelas </h2>

<h3> Parcela </h3>

<img src="../diagrams/classes/Parcelas.png"  width="900" height="350">

<h3> Construible </h3>

<img src="../diagrams/classes/Construible.png">

<h2 style="text-decoration: underline"> Defensas </h2>

<h3> Defensa </h3>

<img src="../diagrams/classes/Defensa.png">

<h3> Torres </h3>

<img src="../diagrams/classes/TorreBlanca.png">

<h3> TrampaArenosa </h3>

<img src="../diagrams/classes/TrampaArenosa.png">

<h2 style="text-decoration: underline"> Enemigos </h2>

<h3> Enemigo </h3>

<img src="../diagrams/classes/Enemigo.png"  width="1000" height="300">

<h3> Enemigos </h3>

<img src="../diagrams/classes/Enemigos.png"  width="1000" height="300">

<h2 style="text-decoration: underline"> Logger </h2>

<h3> SingleLogger </h3>

<img src="../diagrams/classes/Logger.png">

<br><br><br>

____________

<center><h2> Diagramas de Secuencia </h2></center>

<br><br>
<p align="justify">
    De los casos de uso planteados en el enunciado, rescatamos las siguientes secuencias interesantes:
</p>

<h2 style="text-decoration: underline"> Torres y trampas</h2>

<h3> Activacion de una torre </h3>

<img src="../diagrams/sequences/ActivacionTorreBlanca.png"  width="1000" height="400">

<h3> Hallar enemigo torres </h3>

<img src="../diagrams/sequences/HallarEnemigoTorres.png"  width="1000" height="400">

<h3> Hallar enemigos trampas </h3>

<img src="../diagrams/sequences/HallarEnemigosTrampa.png"  width="1000" height="400">

<h2 style="text-decoration: underline"> Construccion en las diferentes parcelas</h2>
<p>
    Meta, Largada y Rocoso unicamente arrojan una excepcion al recibir el mensaje construir(). Por lo cual decidimos prescindir de diagramar estos casos.
</p>

<h3> Construccion en Tierra </h3>

<img src="../diagrams/sequences/Construccion%20en%20Tierra.png"  width="1000" height="400">

<h3> Construccion en Pasarela </h3>

<img src="../diagrams/sequences/Construccion%20en%20Pasarela.png"  width="1000" height="400">

<h2 style="text-decoration: underline"> Enemigos </h2>

<h3> Muerte de un enemigo </h3>

<img src="../diagrams/sequences/MuerteEnemigo.png"  width="1000" height="400">

<h3> Movimiento de un enemigo </h3>

<p> Misma secuencia para todos los tipos de enemigos. En casos como la lechuza que sobreescribe mover() lo hace para utilizar un camino alterno al normal. O en casos como el Topo es para acelerar si debe hacerlo. </p>

<img src="../diagrams/sequences/MoverEnemigo.png" width="1000" height="400">

<h2 style="text-decoration: underline"> Analisis de excepciones </h2>

<h3> Analisis CreditosInsuficientesError </h3>

<img src="../diagrams/sequences/ComoAnalizaCreditosInsuficientes.png"  width="1000" height="400">


<h3> Analisis de ganar una partida </h3>

<img src="../diagrams/sequences/AnalisisDeGanarPartida.png"  width="1000" height="400">

<h3> Analisis de perder una partida </h3>

<img src="../diagrams/sequences/AnalisisPerderPartida.png"  width="1000" height="400">

<br><br><br>

____________

<center><h2> Diagramas de Estados </h2></center>

<br><br>

<h3> Estado de las torres </h3>

<img src="../diagrams/states/Estado%20Torres.png">

<h3> Salud de las defensas </h3>

<img src="../diagrams/states/Salud%20Defensa.png">

<h3> Salud de los enemigos </h3>

<img src="../diagrams/states/Salud%20Enemigo.png">

<h3> Construccion en Tierra/Pasarela </h3>

<img src="../diagrams/states/Construible.png">

<br><br><br>

____________

<center><h2> Diagramas de Paquetes </h2></center>

<br><br>


