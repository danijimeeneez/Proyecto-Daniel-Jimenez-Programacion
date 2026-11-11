import java.util.Scanner;

public class Juego {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        // Datos de los Pokemon
        String[] nombres = {
            "Charmander", "Bulbasaur", "Squirtle", "Ninetales",
            "Tangela", "Vaporeon", "Reshiram", "Shaymin", "Kyogre",
            "Greninja", "Arcanine", "Chikorita", "Ho-Oh",
            "Gyarados", "Sceptile"
        };

        String[] tipos = {
            "Fuego", "Planta", "Agua", "Fuego",
            "Planta", "Agua", "Fuego", "Planta", "Agua",
            "Agua", "Fuego", "Planta", "Fuego",
            "Agua", "Planta"
        };

        String[] nombresAtaques = {
            "Ascuas", "Rueda Fuego", "Lanzallamas",
            "Pistola Agua", "Burbuja", "Hidrobomba",
            "Latigo Cepa", "Hoja Afilada", "Energibola",
            "Placaje", "Ataque Rapido", "Golpe Cuerpo"
        };

        int[] potenciaAtaques = {
            20, 35, 50,
            20, 35, 50,
            20, 35, 50,
            15, 25, 40
        };

        int[][] ataquesPokemon = {
            {0, 1, 9},
            {6, 7, 9},
            {3, 4, 9},
            {0, 1, 9},
            {6, 7, 9},
            {3, 4, 9},
            {0, 2, 11},
            {6, 8, 9},
            {3, 5, 11},
            {3, 4, 10},
            {0, 2, 11},
            {6, 7, 9},
            {0, 2, 11},
            {3, 5, 11},
            {6, 8, 9}
        };

        // Estadisticas base: vida, ataque y defensa
        int[][] estadisticas = {
            {39, 52, 43},
            {45, 49, 49},
            {44, 48, 65},
            {73, 76, 75},
            {65, 55, 115},
            {75, 65, 60},
            {100, 120, 100},
            {100, 100, 100},
            {100, 150, 90},
            {72, 95, 67},
            {90, 110, 80},
            {45, 49, 65},
            {106, 130, 90},
            {95, 100, 79},
            {70, 110, 75}
        };

        final int PRECIO_CARAMELO = 10;

        // Equipo del jugador
        int[] equipo = new int[30];
        int[] nivel = new int[30];
        int[] experiencia = new int[30];
        int[] vidaActual = new int[30];
        int[] vidaMaxima = new int[30];
        int[] bonusAtaque = new int[30];
        int[] bonusDefensa = new int[30];
        int cantidad = 0;

        // Equipo de combate
        int[] equipoCombate = new int[3];
        int cantidadCombate = 0;

        // Equipo del rival
        int[] equipoRival = new int[4];
        int[] nivelRival = new int[4];
        int[] vidaActualRival = new int[4];
        int cantidadRival = 0;

        // Inventario
        int caramelos = 0;
        int monedas = 0;

        int opcionPrincipal;
        int opcionInicial;
        int opcion;

        boolean juegoActivo = true;
        boolean puebloAlcanzado = false;
        boolean rutaSegundoGimnasioExplorada = false;
        boolean cuevaFinalExplorada = false;

        // 0 = Nereo, 1 = Volkar, 2 = Damian
        int etapaHistoria = 0;

        int pokemonJugadorActual = 0;
        int pokemonRivalActual = 0;
        boolean combateActivo = true;

        // Menu principal
        while (juegoActivo) {

            System.out.println();
            System.out.println("================================");
            System.out.println("   ACADEMIA DE CRIATURAS");
            System.out.println("================================");
            System.out.println("1. Empezar partida");
            System.out.println("2. Salir");
            System.out.print("Elige una opcion: ");

            opcionPrincipal = teclado.nextInt();

            switch (opcionPrincipal) {

                case 1:

                    System.out.println();
                    System.out.println("Elige tu Pokemon inicial:");
                    System.out.println("1. Charmander");
                    System.out.println("2. Squirtle");
                    System.out.println("3. Bulbasaur");
                    System.out.print("Elige tu Pokemon: ");

                    opcionInicial = teclado.nextInt();

                    while (opcionInicial < 1 || opcionInicial > 3) {
                        System.out.println("Opcion no valida.");
                        System.out.print("Elige 1, 2 o 3: ");
                        opcionInicial = teclado.nextInt();
                    }

                    if (opcionInicial == 1) {
                        equipo[cantidad] = 0;
                    } else if (opcionInicial == 2) {
                        equipo[cantidad] = 2;
                    } else {
                        equipo[cantidad] = 1;
                    }

                    nivel[cantidad] = 1;
                    vidaActual[cantidad] = estadisticas[equipo[cantidad]][0];
                    vidaMaxima[cantidad] = estadisticas[equipo[cantidad]][0];
                    cantidad++;

                    System.out.println();
                    System.out.println("¡Bienvenido a tu aventura!");
                    System.out.println("Tu primer Pokemon es " + nombres[equipo[0]] + ".");

                    // Menu de la aventura
                    while (juegoActivo) {

                        System.out.println();
                        System.out.println("================================");
                        System.out.println("       MENU DE AVENTURA");
                        System.out.println("================================");
                        System.out.println("1. Seguir jugando");
                        System.out.println("2. Ver mis criaturas");
                        System.out.println("3. Ir a la tienda");
                        System.out.println("4. Ver inventario");
                        System.out.println("5. Preparar el equipo del combate");
                        System.out.println("6. Usar caramelo");
                        System.out.println("7. Salir del juego");
                        System.out.print("Elige una opcion: ");

                        opcion = teclado.nextInt();

                        switch (opcion) {

                            // =========================================
                            // HISTORIA PRINCIPAL
                            // =========================================

                            case 1: {
                                int etapaInicio = etapaHistoria;

                                // =====================================
                                // PRIMERA PARTE: BOSQUE Y RUINAS
                                // =====================================

                                if (!puebloAlcanzado) {

                                    System.out.println();
                                    System.out.println("Sigues avanzando en tu aventura...");
                                    System.out.println("Llegas a un bosque y encuentras");
                                    System.out.println("un arbusto que parece esconder algo.");

                                    char respuesta;

                                    do {
                                        System.out.println();
                                        System.out.println("¿Quieres mirar en el arbusto?");
                                        System.out.println("S = Mirar");
                                        System.out.println("N = Pasar de largo");
                                        System.out.print("Tu respuesta: ");

                                        respuesta = teclado.next().charAt(0);

                                        if (respuesta != 'S' && respuesta != 's' && respuesta != 'N' && respuesta != 'n') {
                                            System.out.println("Respuesta no valida. Escribe S o N.");
                                        }

                                    } while (respuesta != 'S' && respuesta != 's' && respuesta != 'N' && respuesta != 'n');

                                       if (respuesta == 'S' || respuesta == 's') {

                                        int evento = (int) (Math.random() * 12) + 1;

                                        switch (evento) {

                                            case 1:
                                                monedas = monedas + 10;
                                                System.out.println("Has encontrado 10 monedas.");
                                                break;

                                            case 2:
                                                monedas = monedas + 50;
                                                System.out.println("Has encontrado 50 monedas.");
                                                break;

                                            case 3:
                                                monedas = monedas + 100;
                                                System.out.println("Has encontrado 100 monedas.");
                                                break;

                                            case 4:
                                                monedas = monedas + 500;
                                                System.out.println("¡GUAU! Has encontrado 500 monedas.");
                                                break;

                                            case 5:
                                                caramelos = caramelos + 1;
                                                System.out.println("Has encontrado un caramelo.");
                                                break;

                                            case 6:
                                                caramelos = caramelos + 3;
                                                System.out.println("Has encontrado 3 caramelos.");
                                                break;

                                            case 7:
                                                caramelos = caramelos + 5;
                                                System.out.println("Has encontrado 5 caramelos.");
                                                break;

                                            case 8:
                                                caramelos = caramelos + 12;
                                                System.out.println("¡Has encontrado 12 caramelos!");
                                                break;

                                            case 9:
                                                System.out.println("Ladron: ¡Dame 50 monedas!");
                                                if (monedas >= 50) {
                                                    monedas = monedas - 50;
                                                    System.out.println("Te he quitado 50 monedas JAJAJA.");
                                                } else {
                                                    System.out.println("No tienes tantas monedas. Esta vez te has salvado.");
                                                }
                                                break;

                                            case 10:
                                                int pokemonEncontrado = (int) (Math.random() * nombres.length);

                                                System.out.println("¡Has encontrado a " + nombres[pokemonEncontrado] + "!");

                                                if (cantidad < equipo.length) {
                                                    equipo[cantidad] = pokemonEncontrado;
                                                    nivel[cantidad] = 1;
                                                    experiencia[cantidad] = 0;
                                                    vidaActual[cantidad] = estadisticas[pokemonEncontrado][0];
                                                    vidaMaxima[cantidad] = estadisticas[pokemonEncontrado][0];
                                                    cantidad++;
                                                    System.out.println(nombres[pokemonEncontrado] + " se ha unido a tu equipo.");
                                                } else {
                                                    System.out.println("Tu equipo esta lleno.");
                                                }
                                                break;


                                            case 11:
                                                int pokemonEncontrado2 = (int) (Math.random() * nombres.length);

                                                System.out.println("¡Has encontrado a " + nombres[pokemonEncontrado2] + "!");

                                                if (cantidad < equipo.length) {
                                                    equipo[cantidad] = pokemonEncontrado2;
                                                    nivel[cantidad] = 1;
                                                    experiencia[cantidad] = 0;
                                                    vidaActual[cantidad] = estadisticas[pokemonEncontrado2][0];
                                                    vidaMaxima[cantidad] = estadisticas[pokemonEncontrado2][0];
                                                    cantidad++;
                                                    System.out.println(nombres[pokemonEncontrado2] + " se ha unido a tu equipo.");
                                                } else {
                                                    System.out.println("Tu equipo esta lleno.");
                                                }
                                                break;

                                            case 12:
                                                int pokemonEncontrado3 = (int) (Math.random() * nombres.length);

                                                System.out.println("¡Has encontrado a " + nombres[pokemonEncontrado3] + "!");

                                                if (cantidad < equipo.length) {
                                                    equipo[cantidad] = pokemonEncontrado3;
                                                    nivel[cantidad] = 1;
                                                    experiencia[cantidad] = 0;
                                                    vidaActual[cantidad] = estadisticas[pokemonEncontrado3][0];
                                                    vidaMaxima[cantidad] = estadisticas[pokemonEncontrado3][0];
                                                    cantidad++;
                                                    System.out.println(nombres[pokemonEncontrado3] + " se ha unido a tu equipo.");
                                                } else {
                                                    System.out.println("Tu equipo esta lleno.");
                                                }
                                                break;
                                        }

                                    } else {
                                        System.out.println("Pasas de largo y continuas tu aventura.");
                                    }

                                    // SEGUNDO EVENTO: RUINAS
                                    System.out.println();
                                    System.out.println("Sigues avanzando en tu aventura...");
                                    System.out.println("Llegas a unas ruinas abandonadas.");
                                    System.out.println("Entras y encuentras un cofre lleno de polvo.");

                                    char respuesta2;

                                    do {
                                        System.out.println();
                                        System.out.println("¿Quieres abrir el cofre?");
                                        System.out.println("S = Abrir");
                                        System.out.println("N = Dejarlo ahi");
                                        System.out.print("Tu respuesta: ");

                                        respuesta2 = teclado.next().charAt(0);

                                        if (respuesta2 != 'S' && respuesta2 != 's' && respuesta2 != 'N' && respuesta2 != 'n') {
                                            System.out.println("Respuesta no valida. Escribe S o N.");
                                        }

                                    } while (respuesta2 != 'S' && respuesta2 != 's' && respuesta2 != 'N' && respuesta2 != 'n');

                                    if (respuesta2 == 'S' || respuesta2 == 's') {

                                        int eventoRuinas = (int) (Math.random() * 12) + 1;

                                        switch (eventoRuinas) {

                                            case 1:
                                                monedas = monedas + 50;
                                                System.out.println("Has encontrado 50 monedas.");
                                                break;

                                            case 2:
                                                monedas = monedas + 100;
                                                System.out.println("Has encontrado 100 monedas.");
                                                break;

                                            case 3:
                                                monedas = monedas + 500;
                                                System.out.println("¡Has encontrado 500 monedas!");
                                                break;

                                            case 4:
                                                caramelos = caramelos + 3;
                                                System.out.println("Has encontrado 3 caramelos.");
                                                break;

                                            case 5:
                                                caramelos = caramelos + 5;
                                                System.out.println("Has encontrado 5 caramelos.");
                                                break;

                                            case 6:
                                                caramelos = caramelos + 12;
                                                System.out.println("Has encontrado 12 caramelos.");
                                                break;

                                            case 7:
                                                System.out.println("Ladron: ¡Dame 50 monedas!");
                                                if (monedas >= 50) {
                                                    monedas = monedas - 50;
                                                    System.out.println("Te he quitado 50 monedas.");
                                                } else {
                                                    System.out.println("No tienes suficientes monedas.");
                                                }
                                                break;

                                            case 8:
                                                System.out.println("Ladron: ¡Dame 80 monedas!");
                                                if (monedas >= 80) {
                                                    monedas = monedas - 80;
                                                    System.out.println("Te he quitado 80 monedas.");
                                                } else {
                                                    System.out.println("No tienes suficientes monedas.");
                                                }
                                                break;
                                            
                                            case 9:

                                                int pokemonRuinas4 = (int) (Math.random() * nombres.length);

                                                System.out.println("¡Has encontrado a " + nombres[pokemonRuinas4] + "!");

                                                if (cantidad < equipo.length) {
                                                    equipo[cantidad] = pokemonRuinas4;
                                                    nivel[cantidad] = 1;
                                                    experiencia[cantidad] = 0;
                                                    vidaActual[cantidad] = estadisticas[pokemonRuinas4][0];
                                                    vidaMaxima[cantidad] = estadisticas[pokemonRuinas4][0];
                                                    cantidad++;
                                                    System.out.println(nombres[pokemonRuinas4] + " se ha unido a tu equipo.");
                                                } else {
                                                    System.out.println("Tu equipo esta lleno.");
                                                }
                                                break;

                                            case 10:
                                                int pokemonRuinas = (int) (Math.random() * nombres.length);

                                                System.out.println("¡Has encontrado a " + nombres[pokemonRuinas] + "!");

                                                if (cantidad < equipo.length) {
                                                    equipo[cantidad] = pokemonRuinas;
                                                    nivel[cantidad] = 1;
                                                    experiencia[cantidad] = 0;
                                                    vidaActual[cantidad] = estadisticas[pokemonRuinas][0];
                                                    vidaMaxima[cantidad] = estadisticas[pokemonRuinas][0];
                                                    cantidad++;
                                                    System.out.println(nombres[pokemonRuinas] + " se ha unido a tu equipo.");
                                                } else {
                                                    System.out.println("Tu equipo esta lleno.");
                                                }
                                                break;

                                            case 11:
                                                int pokemonRuinas2 = (int) (Math.random() * nombres.length);

                                                System.out.println("¡Has encontrado a " + nombres[pokemonRuinas2] + "!");

                                                if (cantidad < equipo.length) {
                                                    equipo[cantidad] = pokemonRuinas2;
                                                    nivel[cantidad] = 1;
                                                    experiencia[cantidad] = 0;
                                                    vidaActual[cantidad] = estadisticas[pokemonRuinas2][0];
                                                    vidaMaxima[cantidad] = estadisticas[pokemonRuinas2][0];
                                                    cantidad++;
                                                    System.out.println(nombres[pokemonRuinas2] + " se ha unido a tu equipo.");
                                                } else {
                                                    System.out.println("Tu equipo esta lleno.");
                                                }
                                                break;

                                            case 12:

                                                int pokemonRuinas3 = (int) (Math.random() * nombres.length);

                                                System.out.println("¡Has encontrado a " + nombres[pokemonRuinas3] + "!");

                                                if (cantidad < equipo.length) {
                                                    equipo[cantidad] = pokemonRuinas3;
                                                    nivel[cantidad] = 1;
                                                    experiencia[cantidad] = 0;
                                                    vidaActual[cantidad] = estadisticas[pokemonRuinas3][0];
                                                    vidaMaxima[cantidad] = estadisticas[pokemonRuinas3][0];
                                                    cantidad++;
                                                    System.out.println(nombres[pokemonRuinas3] + " se ha unido a tu equipo.");
                                                } else {
                                                    System.out.println("Tu equipo esta lleno.");
                                                }
                                                break;
                                        }

                                    } else {
                                        System.out.println("Dejas el cofre donde esta y continuas tu camino.");
                                    }

                                    System.out.println();
                                    System.out.println("Despues de explorar las ruinas, sigues tu camino...");
                                    System.out.println("A lo lejos ves un pueblo.");
                                    System.out.println("¡Has llegado a Pueblo de Roca!");
                                    System.out.println("Aqui se encuentra el primer gimnasio.");
                                    puebloAlcanzado = true;
                                }

                                // =====================================
                                // PUEBLO DE ROCA: SERVICIOS
                                // =====================================

                                if (etapaHistoria == 0) {

                                    boolean enPueblo = true;

                                    while (enPueblo) {

                                        System.out.println();
                                        System.out.println("================================");
                                        System.out.println("          PUEBLO DE ROCA");
                                        System.out.println("================================");
                                        System.out.println("1. Visitar el Centro Pokemon");
                                        System.out.println("2. Visitar la tienda");
                                        System.out.println("3. Ver inventario");
                                        System.out.println("4. Continuar hacia el primer gimnasio");
                                        System.out.print("Elige una opcion: ");

                                        int opcionPueblo = teclado.nextInt();

                                        switch (opcionPueblo) {

                                            case 1:
                                                System.out.println();
                                                System.out.println("=== CENTRO POKEMON ===");
                                                for (int i = 0; i < cantidad; i++) {
                                                    vidaActual[i] = vidaMaxima[i];
                                                }
                                                System.out.println("¡Todos tus Pokemon han recuperado su vida!");
                                                break;

                                            case 2:
                                                 System.out.println();
                                                System.out.println("======= BIENVENIDO A LA TIENDA ========");
                                                System.out.println("Tienes " + monedas + " monedas.");
                                                System.out.println();
                                                System.out.println("1. Un caramelo (" + PRECIO_CARAMELO + " monedas)");
                                                System.out.println("2. Pack de 10 caramelos (80 monedas)");
                                                System.out.println("3. Salir de la tienda");
                                                System.out.print("¿Que quieres comprar?: ");

                                                int opcionTienda = teclado.nextInt();

                                                if (opcionTienda == 1) {

                                                    if (monedas >= PRECIO_CARAMELO) {
                                                        caramelos = caramelos + 1;
                                                        monedas = monedas - PRECIO_CARAMELO;
                                                        System.out.println("Has comprado 1 caramelo.");
                                                        System.out.println("Ahora tienes " + caramelos + " caramelos y " + monedas + " monedas.");
                                                    } else {
                                                        System.out.println("No tienes monedas suficientes.");
                                                    }

                                                } else if (opcionTienda == 2) {

                                                    if (monedas >= 80) {
                                                        caramelos = caramelos + 10;
                                                        monedas = monedas - 80;
                                                        System.out.println("Has comprado 10 caramelos.");
                                                        System.out.println("Ahora tienes " + caramelos + " caramelos y " + monedas + " monedas.");
                                                    } else {
                                                        System.out.println("No tienes monedas suficientes.");
                                                    }

                                                } else if (opcionTienda == 3) {
                                                    System.out.println("Sales de la tienda.");
                                                } else {
                                                    System.out.println("Opcion no valida.");
                                                }
                                                break;

                                            case 3:
                                                System.out.println();
                                                System.out.println("=== INVENTARIO ===");
                                                System.out.println("Caramelos: " + caramelos);
                                                System.out.println("Monedas: " + monedas);
                                                break;

                                            case 4:
                                                enPueblo = false;
                                                break;

                                            default:
                                                System.out.println("Opcion no valida.");
                                                break;
                                        }
                                    }

                                    // =====================================
                                    // PRIMER GIMNASIO: NEREO
                                    // =====================================

                                    System.out.println();
                                    System.out.println("================================");
                                    System.out.println("          PRIMER GIMNASIO");
                                    System.out.println("================================");
                                    System.out.println("Entras al gimnasio y ves una enorme piscina en el centro.");
                                    System.out.println("Al fondo se encuentra Nereo, especialista en Pokemon de tipo Agua.");
                                    System.out.println("Nereo: Bienvenido. ¡Prepara a tus Pokemon porque vas a necesitar suerte!");

                                    equipoRival[0] = 2;
                                    equipoRival[1] = 5;

                                    nivelRival[0] = 1;
                                    nivelRival[1] = 1;

                                    cantidadRival = 2;

                                    for (int i = 0; i < cantidadRival; i++) {
                                        vidaActualRival[i] = estadisticas[equipoRival[i]][0];
                                    }

                                    if (cantidadCombate == 0) {
                                        System.out.println();
                                        System.out.println("No tienes ningun Pokemon preparado para combatir.");
                                        System.out.println("Vuelve al menu y prepara tu equipo.");
                                    } else {
                                        pokemonJugadorActual = 0;
                                        pokemonRivalActual = 0;
                                        combateActivo = true;

                                        double[][] efectividad = {
                                            {1.0, 0.5, 1.5},
                                            {1.5, 1.0, 0.5},
                                            {0.5, 1.5, 1.0}
                                        };

                                        System.out.println();
                                        System.out.println("=== EQUIPO DE NEREO ===");

                                        for (int i = 0; i < cantidadRival; i++) {
                                            System.out.println((i + 1) + ". " + nombres[equipoRival[i]]);
                                            System.out.println("Nivel: " + nivelRival[i]);
                                            System.out.println("Vida: " + vidaActualRival[i]);
                                        }

                                        System.out.println();
                                        System.out.println("¡COMIENZA EL COMBATE!");

                                        while (combateActivo) {

                                            if (pokemonJugadorActual >= cantidadCombate || pokemonRivalActual >= cantidadRival) {
                                                combateActivo = false;
                                                break;
                                            }

                                            int posicionJugador = equipoCombate[pokemonJugadorActual];
                                            int indicePokemonJugador = equipo[posicionJugador];
                                            int indicePokemonRival = equipoRival[pokemonRivalActual];

                                            System.out.println();
                                            System.out.println("--------------------------------");
                                            System.out.println("TU TURNO");
                                            System.out.println(nombres[indicePokemonJugador] + " - Vida: " + vidaActual[posicionJugador]);
                                            System.out.println(nombres[indicePokemonRival] + " - Vida: " + vidaActualRival[pokemonRivalActual]);

                                            System.out.println();
                                            System.out.println("Elige un ataque:");

                                            for (int i = 0; i < 3; i++) {
                                                int indiceAtaque = ataquesPokemon[indicePokemonJugador][i];
                                                System.out.println((i + 1) + ". " + nombresAtaques[indiceAtaque] + " - Potencia: " + potenciaAtaques[indiceAtaque]);
                                            }

                                            int opcionAtaque;

                                            do {
                                                System.out.print("Introduce el numero del ataque: ");
                                                opcionAtaque = teclado.nextInt();

                                                if (opcionAtaque < 1 || opcionAtaque > 3) {
                                                    System.out.println("Ataque no valido.");
                                                }
                                            } while (opcionAtaque < 1 || opcionAtaque > 3);

                                            int indiceAtaqueJugador = ataquesPokemon[indicePokemonJugador][opcionAtaque - 1];

                                            int tipoAtaqueJugador = -1;

                                            if (indiceAtaqueJugador <= 2) {
                                                tipoAtaqueJugador = 0;
                                            } else if (indiceAtaqueJugador <= 5) {
                                                tipoAtaqueJugador = 1;
                                            } else if (indiceAtaqueJugador <= 8) {
                                                tipoAtaqueJugador = 2;
                                            }

                                            int tipoDefensorRival = 0;

                                            switch (tipos[indicePokemonRival]) {
                                                case "Fuego":
                                                    tipoDefensorRival = 0;
                                                    break;
                                                case "Agua":
                                                    tipoDefensorRival = 1;
                                                    break;
                                                case "Planta":
                                                    tipoDefensorRival = 2;
                                                    break;
                                            }

                                            double multiplicadorJugador = 1.0;

                                            if (tipoAtaqueJugador != -1) {
                                                multiplicadorJugador = efectividad[tipoAtaqueJugador][tipoDefensorRival];
                                            }

                                            int danoJugador = (int) (
                                                    (potenciaAtaques[indiceAtaqueJugador]
                                                    + estadisticas[indicePokemonJugador][1]
                                                    + bonusAtaque[posicionJugador]
                                                    - estadisticas[indicePokemonRival][2] / 2.0)
                                                    * multiplicadorJugador
                                            );

                                            if (danoJugador < 1) {
                                                danoJugador = 1;
                                            }

                                            System.out.println();
                                            System.out.println(nombres[indicePokemonJugador] + " utiliza " + nombresAtaques[indiceAtaqueJugador] + "!");

                                            if (multiplicadorJugador > 1.0) {
                                                System.out.println("¡Es muy eficaz!");
                                            } else if (multiplicadorJugador < 1.0) {
                                                System.out.println("No es muy eficaz...");
                                            }

                                            System.out.println("Ha causado " + danoJugador + " puntos de daño.");

                                            vidaActualRival[pokemonRivalActual] -= danoJugador;

                                            if (vidaActualRival[pokemonRivalActual] < 0) {
                                                vidaActualRival[pokemonRivalActual] = 0;
                                            }

                                            if (vidaActualRival[pokemonRivalActual] == 0) {
                                                System.out.println(nombres[indicePokemonRival] + " ha sido debilitado.");
                                                pokemonRivalActual++;

                                                if (pokemonRivalActual >= cantidadRival) {
                                                    break;
                                                }

                                                System.out.println("Nereo: ¡Adelante, " + nombres[equipoRival[pokemonRivalActual]] + "!");
                                                continue;
                                            }

                                            // TURNO DE NEREO
                                            System.out.println();
                                            System.out.println("TURNO DE NEREO");

                                            int posicionAtaqueRival = (int) (Math.random() * 3);
                                            int indiceAtaqueRival = ataquesPokemon[indicePokemonRival][posicionAtaqueRival];

                                            int tipoAtaqueRival = -1;

                                            if (indiceAtaqueRival <= 2) {
                                                tipoAtaqueRival = 0;
                                            } else if (indiceAtaqueRival <= 5) {
                                                tipoAtaqueRival = 1;
                                            } else if (indiceAtaqueRival <= 8) {
                                                tipoAtaqueRival = 2;
                                            }

                                            int tipoDefensorJugador = 0;

                                            switch (tipos[indicePokemonJugador]) {
                                                case "Fuego":
                                                    tipoDefensorJugador = 0;
                                                    break;
                                                case "Agua":
                                                    tipoDefensorJugador = 1;
                                                    break;
                                                case "Planta":
                                                    tipoDefensorJugador = 2;
                                                    break;
                                            }

                                            double multiplicadorRival = 1.0;

                                            if (tipoAtaqueRival != -1) {
                                                multiplicadorRival = efectividad[tipoAtaqueRival][tipoDefensorJugador];
                                            }

                                            int danoRival = (int) (
                                                    (potenciaAtaques[indiceAtaqueRival]
                                                    + estadisticas[indicePokemonRival][1]
                                                    - (estadisticas[indicePokemonJugador][2]
                                                    + bonusDefensa[posicionJugador]) / 2.0)
                                                    * multiplicadorRival
                                            );

                                            if (danoRival < 1) {
                                                danoRival = 1;
                                            }

                                            System.out.println(nombres[indicePokemonRival] + " utiliza " + nombresAtaques[indiceAtaqueRival] + "!");

                                            if (multiplicadorRival > 1.0) {
                                                System.out.println("¡Es muy eficaz!");
                                            } else if (multiplicadorRival < 1.0) {
                                                System.out.println("No es muy eficaz...");
                                            }

                                            System.out.println("Ha causado " + danoRival + " puntos de daño.");

                                            vidaActual[posicionJugador] -= danoRival;

                                            if (vidaActual[posicionJugador] < 0) {
                                                vidaActual[posicionJugador] = 0;
                                            }

                                            if (vidaActual[posicionJugador] == 0) {
                                                System.out.println(nombres[indicePokemonJugador] + " ha sido debilitado.");
                                                pokemonJugadorActual++;

                                                if (pokemonJugadorActual >= cantidadCombate) {
                                                    System.out.println("¡Te has quedado sin Pokemon!");
                                                    break;
                                                }

                                                int siguientePokemon = equipo[equipoCombate[pokemonJugadorActual]];

                                                System.out.println("¡Adelante, " + nombres[siguientePokemon] + "!");
                                            }
                                        }

                                        System.out.println();
                                        System.out.println("========== FIN DEL COMBATE ==========");

                                        if (pokemonRivalActual >= cantidadRival) {

                                            System.out.println("¡HAS GANADO EL COMBATE!");
                                            System.out.println("Nereo: No puede ser... Has ganado.");
                                            System.out.println("Nereo: Has conseguido la primera medalla. Sigue hacia Ciudad Brasal.");

                                            etapaHistoria = 1;

                                            System.out.println();
                                            System.out.println("Tus Pokemon reciben 100 puntos de experiencia.");

                                            for (int i = 0; i < cantidadCombate; i++) {
                                                int posicionEquipo = equipoCombate[i];

                                                if (nivel[posicionEquipo] < 100) {
                                                    experiencia[posicionEquipo] += 100;

                                                    if (experiencia[posicionEquipo] >= 100) {
                                                        experiencia[posicionEquipo] -= 100;
                                                        nivel[posicionEquipo]++;

                                                        System.out.println(nombres[equipo[posicionEquipo]] + " ha subido al nivel " + nivel[posicionEquipo] + "!");

                                                      
                                                            vidaMaxima[posicionEquipo] += 3;
                                                            vidaActual[posicionEquipo] += 3;
                                                            bonusAtaque[posicionEquipo] += 3;
                                                            bonusDefensa[posicionEquipo] += 3;

                                                            System.out.println("¡Sus estadisticas han aumentado en 3 puntos!");
                                        
                                                    }
                                                } else {
                                                    System.out.println(nombres[equipo[posicionEquipo]] + " ya ha alcanzado el nivel maximo.");
                                                }

                                                System.out.println("Experiencia: " + experiencia[posicionEquipo] + "/100");
                                            }

                                        } else {
                                            System.out.println("HAS PERDIDO EL COMBATE...");
                                            System.out.println("Nereo: ¡Te falta mucho entrenamiento!");
                                            System.out.println("Puedes curarte y volver a intentarlo.");
                                        }
                                    }
                                }
                                // =====================================
                                // SEGUNDA PARTE: CAMINO A CIUDAD BRASAL
                                // =====================================

                                if (etapaInicio == 1 && etapaHistoria == 1) {

                                    if (!rutaSegundoGimnasioExplorada) {

                                        System.out.println();
                                        System.out.println("================================================");
                                        System.out.println("          EL CAMINO A CIUDAD BRASAL");
                                        System.out.println("================================================");
                                        System.out.println("Tras conseguir la primera medalla, abandonas Pueblo de Roca.");
                                        System.out.println("Tu siguiente destino es Ciudad Brasal, donde se encuentra");
                                        System.out.println("el segundo gimnasio de la region de Valthera.");
                                        System.out.println();
                                        System.out.println("Hannibal: Nereo era fuerte, pero no pienso detenerme aqui.");
                                        System.out.println("Mientras avanzas, el camino se estrecha entre las montañas.");
                                        System.out.println("Una cueva oscura aparece junto al sendero.");
                                        System.out.println("En la entrada hay unas cajas abandonadas y varias huellas.");

                                        // EVENTO 1: CUEVA DEL ECO
                                        System.out.println();
                                        System.out.println("================================================");
                                        System.out.println("                CUEVA DEL ECO");
                                        System.out.println("================================================");
                                        System.out.println("¿Quieres explorar la Cueva del Eco?");
                                        System.out.println("1. Entrar en la cueva");
                                        System.out.println("2. Continuar hacia Ciudad Brasal");
                                        System.out.print("Elige una opcion: ");

                                        int opcionCueva = teclado.nextInt();

                                        while (opcionCueva < 1 || opcionCueva > 2) {
                                            System.out.print("Opcion no valida. Elige 1 o 2: ");
                                            opcionCueva = teclado.nextInt();
                                        }

                                        if (opcionCueva == 1) {

                                            int hallazgo = (int) (Math.random() * 8) + 1;

                                            System.out.println();
                                            System.out.println("Te adentras en la cueva y encuentras algo entre las rocas...");

                                            switch (hallazgo) {

                                                case 1:
                                                    monedas = monedas + 100;
                                                    System.out.println("Has encontrado 100 monedas.");
                                                    break;

                                                case 2:
                                                    monedas = monedas + 200;
                                                    System.out.println("¡Un pequeño tesoro! Has encontrado 200 monedas.");
                                                    break;

                                                case 3:
                                                    caramelos = caramelos + 3;
                                                    System.out.println("Has encontrado 3 caramelos.");
                                                    break;

                                                case 4:
                                                    monedas = monedas + 50;
                                                    caramelos = caramelos + 2;
                                                    System.out.println("Has encontrado 50 monedas y 2 caramelos.");
                                                    break;

                                                case 5:
                                                    System.out.println("Un ladron aparece y te exige 50 monedas.");

                                                    if (monedas >= 50) {
                                                        monedas = monedas + 50;
                                                        System.out.println("El ladron huye con tus 50 monedas.");
                                                    } else {
                                                        System.out.println("No tienes 50 monedas. El ladron se marcha.");
                                                    }
                                                    break;

                                                case 6:

                                                    if (cantidad < equipo.length) {

                                                        int nuevoPokemon = (int) (Math.random() * nombres.length);

                                                        equipo[cantidad] = nuevoPokemon;
                                                        nivel[cantidad] = 1;
                                                        experiencia[cantidad] = 0;
                                                        vidaActual[cantidad] = estadisticas[nuevoPokemon][0];
                                                        vidaMaxima[cantidad] = estadisticas[nuevoPokemon][0];
                                                        cantidad++;

                                                        System.out.println("¡Has encontrado a " + nombres[nuevoPokemon] + " y se une a tu equipo!");

                                                    } else {
                                                        System.out.println("Has encontrado un Pokemon, pero tu equipo esta lleno.");
                                                    }
                                                    break;
                                                case 7:

                                                    if (cantidad < equipo.length) {

                                                        int nuevoPokemon = (int) (Math.random() * nombres.length);

                                                        equipo[cantidad] = nuevoPokemon;
                                                        nivel[cantidad] = 1;
                                                        experiencia[cantidad] = 0;
                                                        vidaActual[cantidad] = estadisticas[nuevoPokemon][0];
                                                        vidaMaxima[cantidad] = estadisticas[nuevoPokemon][0];
                                                        cantidad++;

                                                        System.out.println("¡Has encontrado a "
                                                                + nombres[nuevoPokemon]
                                                                + " y se une a tu equipo!");

                                                    } else {
                                                        System.out.println("Has encontrado un Pokemon, pero tu equipo esta lleno.");
                                                    }
                                                break;
                                                case 8:

                                                    if (cantidad < equipo.length) {

                                                        int nuevoPokemon = (int) (Math.random() * nombres.length);

                                                        equipo[cantidad] = nuevoPokemon;
                                                        nivel[cantidad] = 1;
                                                        experiencia[cantidad] = 0;
                                                        vidaActual[cantidad] = estadisticas[nuevoPokemon][0];
                                                        vidaMaxima[cantidad] = estadisticas[nuevoPokemon][0];
                                                        cantidad++;

                                                        System.out.println("¡Has encontrado a " + nombres[nuevoPokemon] + " y se une a tu equipo!");

                                                    } else {
                                                        System.out.println("Has encontrado un Pokemon, pero tu equipo esta lleno.");
                                                    }
                                                break;
                                            }

                                        } else {
                                            System.out.println("Decides no entrar y sigues tu camino.");
                                        }

                                        // EVENTO 2: LAGO DE LOS REFLEJOS
                                        System.out.println();
                                        System.out.println("================================================");
                                        System.out.println("             EL LAGO DE LOS REFLEJOS");
                                        System.out.println("================================================");
                                        System.out.println("Despues de dejar atras la cueva, continuas por un sendero");
                                        System.out.println("que desciende hasta un enorme lago.");
                                        System.out.println("El agua esta completamente tranquila y refleja las montañas.");
                                        System.out.println();
                                        System.out.println("De repente, algo brillante aparece flotando en medio del lago.");
                                        System.out.println("Parece una pequeña caja que se mueve lentamente con el agua.");
                                        System.out.println();
                                        System.out.println("Hannibal: ¿Que sera eso? Parece que podria contener algo...");

                                        char respuestaLago;

                                        do {
                                            System.out.println();
                                            System.out.println("¿Quieres acercarte para intentar coger el objeto?");
                                            System.out.println("S = Acercarme al objeto");
                                            System.out.println("N = Continuar mi camino");
                                            System.out.print("Tu respuesta: ");

                                            respuestaLago = teclado.next().charAt(0);

                                            if (respuestaLago != 'S' && respuestaLago != 's' && respuestaLago != 'N' && respuestaLago != 'n') {
                                                System.out.println("Respuesta no valida. Escribe S o N.");
                                            }

                                        } while (respuestaLago != 'S' && respuestaLago != 's' && respuestaLago != 'N' && respuestaLago != 'n');

                                        if (respuestaLago == 'S' || respuestaLago == 's') {

                                            System.out.println();
                                            System.out.println("Te acercas a la orilla y consigues recuperar la caja.");
                                            System.out.println("Al abrirla, descubres que contiene algo inesperado...");

                                            int hallazgoLago = (int) (Math.random() * 10) + 1;

                                            switch (hallazgoLago) {

                                                case 1:
                                                    monedas += 100;
                                                    System.out.println("¡Has encontrado 100 monedas dentro de la caja!");
                                                    break;

                                                case 2:
                                                    monedas += 300;
                                                    System.out.println("¡Increible! Has encontrado 300 monedas.");
                                                    break;

                                                case 3:
                                                    caramelos += 5;
                                                    System.out.println("Has encontrado 5 caramelos.");
                                                    break;

                                                case 4:
                                                    monedas += 50;
                                                    caramelos += 3;
                                                    System.out.println("Has encontrado 50 monedas y 3 caramelos.");
                                                    break;

                                                case 5:
                                                    System.out.println("¡La caja estaba preparada como una trampa!");
                                                    System.out.println("Un ladron aparece desde unos arbustos y te exige 100 monedas.");

                                                    if (monedas >= 100) {
                                                        monedas -= 100;
                                                        System.out.println("El ladron consigue escapar con tus 100 monedas.");
                                                    } else {
                                                        System.out.println("No tienes suficientes monedas y el ladron se marcha.");
                                                    }
                                                    break;

                                                case 6:
                                                    monedas += 200;
                                                    System.out.println("¡Has encontrado una bolsa escondida con 200 monedas!");
                                                    break;

                                                case 7:
                                                    caramelos += 8;
                                                    System.out.println("¡Que suerte! Has encontrado 8 caramelos.");
                                                    break;

                                                case 8:
                                                    int pokemonLago = (int) (Math.random() * nombres.length);

                                                    System.out.println("¡Has encontrado a " + nombres[pokemonLago] + "!");

                                                    if (cantidad < equipo.length) {
                                                        equipo[cantidad] = pokemonLago;
                                                        nivel[cantidad] = 1;
                                                        experiencia[cantidad] = 0;
                                                        vidaActual[cantidad] = estadisticas[pokemonLago][0];
                                                        vidaMaxima[cantidad] = estadisticas[pokemonLago][0];
                                                        cantidad++;

                                                        System.out.println(nombres[pokemonLago]
                                                                + " se ha unido a tu equipo.");
                                                    } else {
                                                        System.out.println("Tu equipo esta lleno. No puedes añadir mas Pokemon.");
                                                    }
                                                    break;
                                                case 9:
                                                    int pokemonLago2 = (int) (Math.random() * nombres.length);

                                                    System.out.println("¡Has encontrado a " + nombres[pokemonLago2] + "!");

                                                    if (cantidad < equipo.length) {
                                                        equipo[cantidad] = pokemonLago2;
                                                        nivel[cantidad] = 1;
                                                        experiencia[cantidad] = 0;
                                                        vidaActual[cantidad] = estadisticas[pokemonLago2][0];
                                                        vidaMaxima[cantidad] = estadisticas[pokemonLago2][0];
                                                        cantidad++;

                                                        System.out.println(nombres[pokemonLago2]
                                                                + " se ha unido a tu equipo.");
                                                    } else {
                                                        System.out.println("Tu equipo esta lleno. No puedes añadir mas Pokemon.");
                                                    }
                                                    break;
                                                case 10:

                                                    int pokemonLago3 = (int) (Math.random() * nombres.length);

                                                    System.out.println("¡Has encontrado a " + nombres[pokemonLago3] + "!");

                                                    if (cantidad < equipo.length) {
                                                        equipo[cantidad] = pokemonLago3;
                                                        nivel[cantidad] = 1;
                                                        experiencia[cantidad] = 0;
                                                        vidaActual[cantidad] = estadisticas[pokemonLago3][0];
                                                        vidaMaxima[cantidad] = estadisticas[pokemonLago3][0];
                                                        cantidad++;

                                                        System.out.println(nombres[pokemonLago3]
                                                                + " se ha unido a tu equipo.");
                                                    } else {
                                                        System.out.println("Tu equipo esta lleno. No puedes añadir mas Pokemon.");
                                                    }
                                                    break;
                                            }

                                        } else {
                                            System.out.println();
                                            System.out.println("Hannibal: No tengo tiempo para detenerme.");
                                            System.out.println("Decides dejar el objeto flotando y continuar tu camino.");
                                        }

                                        // ENCUENTRO CON DAMIAN
                                        System.out.println();
                                        System.out.println("Al abandonar el lago, escuchas una voz familiar.");
                                        System.out.println("Damián: Vaya, Hannibal. Veo que has conseguido una medalla.");
                                        System.out.println("Hannibal: Damián. ¿Desde cuando te dedicas a seguirme?");
                                        System.out.println("Damián: No te hagas ilusiones. Solo quiero asegurarme de que");
                                        System.out.println("no llegues a ser mejor entrenador que yo.");
                                        System.out.println("Hannibal: Si quieres demostrar algo, gana tus propias medallas.");
                                        System.out.println("Damián: Disfruta de tu pequeña victoria. En Ciudad Brasal");
                                        System.out.println("descubriras que todavia estas muy lejos de mi nivel.");
                                        System.out.println("Damián se marcha por un sendero distinto.");
                                        System.out.println();
                                        System.out.println("A lo lejos distingues las chimeneas de Ciudad Brasal.");
                                        System.out.println("¡Has llegado al pueblo del segundo gimnasio!");

                                        rutaSegundoGimnasioExplorada = true;
                                    }

                                    // =====================================
                                    // CIUDAD BRASAL: SERVICIOS
                                    // =====================================

                                    boolean enCiudadBrasal = true;

                                    while (enCiudadBrasal) {

                                        System.out.println();
                                        System.out.println("================================");
                                        System.out.println("          CIUDAD BRASAL");
                                        System.out.println("================================");
                                        System.out.println("1. Visitar el Centro Pokemon");
                                        System.out.println("2. Visitar la tienda");
                                        System.out.println("3. Ver inventario");
                                        System.out.println("4. Continuar hacia el segundo gimnasio");
                                        System.out.print("Elige una opcion: ");

                                        int opcionCiudad = teclado.nextInt();

                                        switch (opcionCiudad) {

                                            case 1:
                                                System.out.println();
                                                System.out.println("=== CENTRO POKEMON ===");

                                                for (int i = 0; i < cantidad; i++) {
                                                    vidaActual[i] = vidaMaxima[i];
                                                }

                                                System.out.println("¡Todos tus Pokemon han recuperado su vida!");
                                                break;

                                            case 2:
                                                System.out.println();
                                                System.out.println("======= BIENVENIDO A LA TIENDA ========");
                                                System.out.println("Tienes " + monedas + " monedas.");
                                                System.out.println();
                                                System.out.println("1. Un caramelo (" + PRECIO_CARAMELO + " monedas)");
                                                System.out.println("2. Pack de 10 caramelos (80 monedas)");
                                                System.out.println("3. Salir de la tienda");
                                                System.out.print("¿Que quieres comprar?: ");

                                                int opcionTienda = teclado.nextInt();

                                                if (opcionTienda == 1) {

                                                    if (monedas >= PRECIO_CARAMELO) {
                                                        caramelos = caramelos + 1;
                                                        monedas = monedas - PRECIO_CARAMELO;
                                                        System.out.println("Has comprado 1 caramelo.");
                                                        System.out.println("Ahora tienes " + caramelos + " caramelos y " + monedas + " monedas.");
                                                    } else {
                                                        System.out.println("No tienes monedas suficientes.");
                                                    }

                                                } else if (opcionTienda == 2) {

                                                    if (monedas >= 80) {
                                                        caramelos = caramelos + 10;
                                                        monedas = monedas - 80;
                                                        System.out.println("Has comprado 10 caramelos.");
                                                        System.out.println("Ahora tienes " + caramelos + " caramelos y " + monedas + " monedas.");
                                                    } else {
                                                        System.out.println("No tienes monedas suficientes.");
                                                    }

                                                } else if (opcionTienda == 3) {
                                                    System.out.println("Sales de la tienda.");
                                                } else {
                                                    System.out.println("Opcion no valida.");
                                                }

                                                break;

                                            // =========================================
                                            // INVENTARIO
                                            // =========================================

                                            case 3:

                                                System.out.println();
                                                System.out.println("=== INVENTARIO ===");
                                                System.out.println("Caramelos: " + caramelos);
                                                System.out.println("Monedas: " + monedas);

                                                break;

                                            // =========================================
                                            // PREPARAR EQUIPO DE COMBATE
                                            // =========================================

                                            case 4:

                                                System.out.println();
                                                System.out.println("=== EQUIPO DE COMBATE ===");

                                                if (cantidad == 0) {
                                                    System.out.println("Todavia no tienes ningun Pokemon.");
                                                    break;
                                                }

                                                cantidadCombate = 0;

                                                System.out.println("Estos son tus Pokemon:");

                                                for (int i = 0; i < cantidad; i++) {
                                                    System.out.println((i + 1) + ". " + nombres[equipo[i]]);
                                                }

                                                System.out.println("Puedes elegir hasta 3 Pokemon para combatir.");

                                                while (cantidadCombate < 3 && cantidadCombate < cantidad) {

                                                    int numero = 0;
                                                    boolean repetido;

                                                    do {
                                                        repetido = false;

                                                        System.out.println("Elige un Pokemon (introduce su numero):");
                                                        numero = teclado.nextInt();

                                                        if (numero < 1 || numero > cantidad) {
                                                            System.out.println("Numero no valido, prueba otra vez.");
                                                        } else {

                                                            for (int j = 0; j < cantidadCombate; j++) {
                                                                if (numero - 1 == equipoCombate[j]) {
                                                                    repetido = true;
                                                                    System.out.println("Ese Pokemon ya ha sido elegido.");
                                                                }
                                                            }
                                                        }

                                                    } while (numero < 1 || numero > cantidad || repetido);

                                                    equipoCombate[cantidadCombate] = numero - 1;
                                                    cantidadCombate++;

                                                    System.out.println("Pokemon añadido al equipo.");

                                                    if (cantidadCombate < 3 && cantidadCombate < cantidad) {

                                                        System.out.println("¿Quieres añadir otro Pokemon?");
                                                        System.out.println("1. Si");
                                                        System.out.println("2. No");

                                                        int opcionEquipo = teclado.nextInt();

                                                        if (opcionEquipo == 2) {
                                                            break;
                                                        }
                                                    }
                                                }

                                                System.out.println();
                                                System.out.println("=== TU EQUIPO DE COMBATE ===");

                                                for (int i = 0; i < cantidadCombate; i++) {
                                                    System.out.println((i + 1) + ". " + nombres[equipo[equipoCombate[i]]]);
                                                }

                                                 case 6:
                                                enCiudadBrasal = false;
                                                break;

                                            default:
                                                System.out.println("Opcion no valida.");
                                                break;
                                        }
                                    }

                                    // =====================================
                                    // SEGUNDO GIMNASIO: VOLKAR
                                    // =====================================

                                    System.out.println();
                                    System.out.println("================================");
                                    System.out.println("          SEGUNDO GIMNASIO");
                                    System.out.println("================================");
                                    System.out.println("Entras en un gimnasio rodeado de roca volcanica y rios de lava.");
                                    System.out.println("Volkar: Soy Volkar, lider de tipo Fuego de Ciudad Brasal.");
                                    System.out.println("Volkar: Nereo me ha hablado de ti, pero no creas que sera facil.");
                                    System.out.println("Volkar: ¡Mi equipo te hara arder! ¡QUE COMIENCE EL COMBATE!");

                                    equipoRival[0] = 0;
                                    equipoRival[1] = 3;
                                    equipoRival[2] = 10;

                                    nivelRival[0] = 3;
                                    nivelRival[1] = 4;
                                    nivelRival[2] = 4;

                                    cantidadRival = 3;

                                    for (int i = 0; i < cantidadRival; i++) {
                                        vidaActualRival[i] = estadisticas[equipoRival[i]][0];
                                    }

                                    if (cantidadCombate == 0) {
                                        System.out.println();
                                        System.out.println("No tienes ningun Pokemon preparado para combatir.");
                                        System.out.println("Vuelve al menu y prepara tu equipo.");
                                    } else {

                                        pokemonJugadorActual = 0;
                                        pokemonRivalActual = 0;
                                        combateActivo = true;

                                        double[][] efectividad = {
                                            {1.0, 0.5, 1.5},
                                            {1.5, 1.0, 0.5},
                                            {0.5, 1.5, 1.0}
                                        };

                                        System.out.println();
                                        System.out.println("=== EQUIPO DE VOLKAR ===");

                                        for (int i = 0; i < cantidadRival; i++) {
                                            System.out.println((i + 1) + ". " + nombres[equipoRival[i]]);
                                            System.out.println("Nivel: " + nivelRival[i]);
                                            System.out.println("Vida: " + vidaActualRival[i]);
                                        }

                                        System.out.println();
                                        System.out.println("¡COMIENZA EL COMBATE!");

                                        while (combateActivo) {

                                            if (pokemonJugadorActual >= cantidadCombate || pokemonRivalActual >= cantidadRival) {
                                                combateActivo = false;
                                                break;
                                            }

                                            int posicionJugador = equipoCombate[pokemonJugadorActual];
                                            int indicePokemonJugador = equipo[posicionJugador];
                                            int indicePokemonRival = equipoRival[pokemonRivalActual];

                                            System.out.println();
                                            System.out.println("--------------------------------");
                                            System.out.println("TU TURNO");
                                            System.out.println(nombres[indicePokemonJugador] + " - Vida: " + vidaActual[posicionJugador]);
                                            System.out.println(nombres[indicePokemonRival] + " - Vida: " + vidaActualRival[pokemonRivalActual]);

                                            System.out.println();
                                            System.out.println("Elige un ataque:");

                                            for (int i = 0; i < 3; i++) {
                                                int indiceAtaque = ataquesPokemon[indicePokemonJugador][i];
                                                System.out.println((i + 1) + ". " + nombresAtaques[indiceAtaque] + " - Potencia: " + potenciaAtaques[indiceAtaque]);
                                            }

                                            int opcionAtaque;

                                            do {
                                                System.out.print("Introduce el numero del ataque: ");
                                                opcionAtaque = teclado.nextInt();

                                                if (opcionAtaque < 1 || opcionAtaque > 3) {
                                                    System.out.println("Ataque no valido.");
                                                }
                                            } while (opcionAtaque < 1 || opcionAtaque > 3);

                                            int indiceAtaqueJugador = ataquesPokemon[indicePokemonJugador][opcionAtaque - 1];

                                            int tipoAtaqueJugador = -1;

                                            if (indiceAtaqueJugador <= 2) {
                                                tipoAtaqueJugador = 0;
                                            } else if (indiceAtaqueJugador <= 5) {
                                                tipoAtaqueJugador = 1;
                                            } else if (indiceAtaqueJugador <= 8) {
                                                tipoAtaqueJugador = 2;
                                            }

                                            int tipoDefensorRival = 0;

                                            switch (tipos[indicePokemonRival]) {
                                                case "Fuego":
                                                    tipoDefensorRival = 0;
                                                    break;
                                                case "Agua":
                                                    tipoDefensorRival = 1;
                                                    break;
                                                case "Planta":
                                                    tipoDefensorRival = 2;
                                                    break;
                                            }

                                            double multiplicadorJugador = 1.0;

                                            if (tipoAtaqueJugador != -1) {
                                                multiplicadorJugador = efectividad[tipoAtaqueJugador][tipoDefensorRival];
                                            }

                                            int danoJugador = (int) (
                                                    (potenciaAtaques[indiceAtaqueJugador]
                                                    + estadisticas[indicePokemonJugador][1]
                                                    + bonusAtaque[posicionJugador]
                                                    - estadisticas[indicePokemonRival][2] / 2.0)
                                                    * multiplicadorJugador
                                            );

                                            if (danoJugador < 1) {
                                                danoJugador = 1;
                                            }

                                            System.out.println();
                                            System.out.println(nombres[indicePokemonJugador] + " utiliza " + nombresAtaques[indiceAtaqueJugador] + "!");

                                            if (multiplicadorJugador > 1.0) {
                                                System.out.println("¡Es muy eficaz!");
                                            } else if (multiplicadorJugador < 1.0) {
                                                System.out.println("No es muy eficaz...");
                                            }

                                            System.out.println("Ha causado " + danoJugador + " puntos de daño.");

                                            vidaActualRival[pokemonRivalActual] -= danoJugador;

                                            if (vidaActualRival[pokemonRivalActual] < 0) {
                                                vidaActualRival[pokemonRivalActual] = 0;
                                            }

                                            if (vidaActualRival[pokemonRivalActual] == 0) {
                                                System.out.println(nombres[indicePokemonRival] + " ha sido debilitado.");
                                                pokemonRivalActual++;

                                                if (pokemonRivalActual >= cantidadRival) {
                                                    break;
                                                }

                                                System.out.println("Volkar: ¡Adelante, " + nombres[equipoRival[pokemonRivalActual]] + "!");
                                                continue;
                                            }

                                            // TURNO DE VOLKAR
                                            System.out.println();
                                            System.out.println("TURNO DE VOLKAR");

                                            int posicionAtaqueRival = (int) (Math.random() * 3);
                                            int indiceAtaqueRival = ataquesPokemon[indicePokemonRival][posicionAtaqueRival];

                                            int tipoAtaqueRival = -1;

                                            if (indiceAtaqueRival <= 2) {
                                                tipoAtaqueRival = 0;
                                            } else if (indiceAtaqueRival <= 5) {
                                                tipoAtaqueRival = 1;
                                            } else if (indiceAtaqueRival <= 8) {
                                                tipoAtaqueRival = 2;
                                            }

                                            int tipoDefensorJugador = 0;

                                            switch (tipos[indicePokemonJugador]) {
                                                case "Fuego":
                                                    tipoDefensorJugador = 0;
                                                    break;
                                                case "Agua":
                                                    tipoDefensorJugador = 1;
                                                    break;
                                                case "Planta":
                                                    tipoDefensorJugador = 2;
                                                    break;
                                            }

                                            double multiplicadorRival = 1.0;

                                            if (tipoAtaqueRival != -1) {
                                                multiplicadorRival = efectividad[tipoAtaqueRival][tipoDefensorJugador];
                                            }

                                            int danoRival = (int) (
                                                    (potenciaAtaques[indiceAtaqueRival]
                                                    + estadisticas[indicePokemonRival][1]
                                                    - (estadisticas[indicePokemonJugador][2]
                                                    + bonusDefensa[posicionJugador]) / 2.0)
                                                    * multiplicadorRival
                                            );

                                            if (danoRival < 1) {
                                                danoRival = 1;
                                            }

                                            System.out.println(nombres[indicePokemonRival] + " utiliza " + nombresAtaques[indiceAtaqueRival] + "!");

                                            if (multiplicadorRival > 1.0) {
                                                System.out.println("¡Es muy eficaz!");
                                            } else if (multiplicadorRival < 1.0) {
                                                System.out.println("No es muy eficaz...");
                                            }

                                            System.out.println("Ha causado " + danoRival + " puntos de daño.");

                                            vidaActual[posicionJugador] -= danoRival;

                                            if (vidaActual[posicionJugador] < 0) {
                                                vidaActual[posicionJugador] = 0;
                                            }

                                            if (vidaActual[posicionJugador] == 0) {
                                                System.out.println(nombres[indicePokemonJugador] + " ha sido debilitado.");
                                                pokemonJugadorActual++;

                                                if (pokemonJugadorActual >= cantidadCombate) {
                                                    System.out.println("¡Te has quedado sin Pokemon!");
                                                    break;
                                                }

                                                int siguientePokemon = equipo[equipoCombate[pokemonJugadorActual]];

                                                System.out.println("¡Adelante, " + nombres[siguientePokemon] + "!");
                                            }
                                        }

                                        System.out.println();
                                        System.out.println("========== FIN DEL COMBATE ==========");

                                        if (pokemonRivalActual >= cantidadRival) {

                                            System.out.println("¡HAS GANADO EL COMBATE!");
                                            System.out.println("Volkar: No puede ser... Has ganado.");
                                            System.out.println("Volkar: Has conseguido la segunda medalla. Ahora te espera Damián.");

                                            etapaHistoria = 2;

                                            System.out.println();
                                            System.out.println("Tus Pokemon reciben 100 puntos de experiencia.");

                                            for (int i = 0; i < cantidadCombate; i++) {
                                                int posicionEquipo = equipoCombate[i];

                                                if (nivel[posicionEquipo] < 100) {
                                                    experiencia[posicionEquipo] += 100;

                                                    if (experiencia[posicionEquipo] >= 100) {
                                                        experiencia[posicionEquipo] -= 100;
                                                        nivel[posicionEquipo]++;

                                                        System.out.println(nombres[equipo[posicionEquipo]] + " ha subido al nivel " + nivel[posicionEquipo] + "!");

                                                            vidaMaxima[posicionEquipo] += 3;
                                                            vidaActual[posicionEquipo] += 3;
                                                            bonusAtaque[posicionEquipo] += 3;
                                                            bonusDefensa[posicionEquipo] += 3;

                                                            System.out.println("¡Sus estadisticas han aumentado en 3 puntos!");
                                                        
                                                    }
                                                } else {
                                                    System.out.println(nombres[equipo[posicionEquipo]]
                                                            + " ya ha alcanzado el nivel maximo.");
                                                }

                                                System.out.println("Experiencia: "
                                                        + experiencia[posicionEquipo] + "/100");
                                            }

                                        } else {
                                            System.out.println("HAS PERDIDO EL COMBATE...");
                                            System.out.println("Volkar: ¡Te falta mucho entrenamiento!");
                                            System.out.println("Puedes curarte y volver a intentarlo.");
                                        }
                                    }
                                }
                                                                // =====================================
                                // TERCERA PARTE: CAMINO AL SANTUARIO
                                // =====================================

                                if (etapaInicio == 2 && etapaHistoria == 2) {

                                    // EXPLORACION DE LA CUEVA FINAL
                                    if (!cuevaFinalExplorada) {

                                        System.out.println();
                                        System.out.println("================================================");
                                        System.out.println("          LA CUEVA DE LAS SOMBRAS");
                                        System.out.println("================================================");
                                        System.out.println("Tras derrotar a Volkar, sigues el camino hacia el Santuario de Valthera.");
                                        System.out.println("Antes de llegar, descubres una cueva llena de minerales y cofres.");
                                        System.out.println("¿Quieres explorarla?");
                                        System.out.println("1. Explorar la cueva");
                                        System.out.println("2. Pasar de largo");
                                        System.out.print("Elige una opcion: ");

                                        int opcionCuevaFinal = teclado.nextInt();

                                        while (opcionCuevaFinal < 1 || opcionCuevaFinal > 2) {
                                            System.out.print("Opcion no valida. Elige 1 o 2: ");
                                            opcionCuevaFinal = teclado.nextInt();
                                        }

                                        if (opcionCuevaFinal == 1) {

                                            int hallazgoFinal = (int) (Math.random() * 10) + 1;

                                            switch (hallazgoFinal) {

                                                case 1:
                                                case 2:
                                                    monedas += 100;
                                                    System.out.println("Has encontrado 100 monedas.");
                                                    break;

                                                case 3:
                                                    monedas += 250;
                                                    System.out.println("¡Has encontrado 250 monedas!");
                                                    break;

                                                case 4:
                                                    caramelos += 5;
                                                    System.out.println("Has encontrado 5 caramelos.");
                                                    break;

                                                case 5:
                                                    monedas += 100;
                                                    caramelos += 3;
                                                    System.out.println("Has encontrado 100 monedas y 3 caramelos.");
                                                    break;

                                                case 6:
                                                    System.out.println("Un ladron intenta robarte 100 monedas.");

                                                    if (monedas >= 100) {
                                                        monedas -= 100;
                                                        System.out.println("El ladron consigue escapar con 100 monedas.");
                                                    } else {
                                                        System.out.println("No llevas suficientes monedas y el ladron se marcha.");
                                                    }
                                                    break;

                                                case 7:
                                                     if (cantidad < equipo.length) {

                                                        int nuevoPokemonFinal =
                                                                (int) (Math.random() * nombres.length);

                                                        equipo[cantidad] = nuevoPokemonFinal;
                                                        nivel[cantidad] = 1;
                                                        experiencia[cantidad] = 0;
                                                        vidaActual[cantidad] = estadisticas[nuevoPokemonFinal][0];
                                                        vidaMaxima[cantidad] = estadisticas[nuevoPokemonFinal][0];
                                                        cantidad++;

                                                        System.out.println("¡Has encontrado a " + nombres[nuevoPokemonFinal] + "!");
                                                        System.out.println(nombres[nuevoPokemonFinal] + " se ha unido a tu equipo.");

                                                    } else {
                                                        System.out.println("Has encontrado un Pokemon, pero tu equipo esta lleno.");
                                                    }
                                                    break;

                                                case 8:
                                                     if (cantidad < equipo.length) {

                                                        int nuevoPokemonFinal =
                                                                (int) (Math.random() * nombres.length);

                                                        equipo[cantidad] = nuevoPokemonFinal;
                                                        nivel[cantidad] = 1;
                                                        experiencia[cantidad] = 0;
                                                        vidaActual[cantidad] = estadisticas[nuevoPokemonFinal][0];
                                                        vidaMaxima[cantidad] = estadisticas[nuevoPokemonFinal][0];
                                                        cantidad++;

                                                        System.out.println("¡Has encontrado a " + nombres[nuevoPokemonFinal] + "!");
                                                        System.out.println(nombres[nuevoPokemonFinal] + " se ha unido a tu equipo.");

                                                    } else {
                                                        System.out.println("Has encontrado un Pokemon, pero tu equipo esta lleno.");
                                                    }
                                                    break;
                                                case 9:
                                                     if (cantidad < equipo.length) {

                                                        int nuevoPokemonFinal =
                                                                (int) (Math.random() * nombres.length);

                                                        equipo[cantidad] = nuevoPokemonFinal;
                                                        nivel[cantidad] = 1;
                                                        experiencia[cantidad] = 0;
                                                        vidaActual[cantidad] = estadisticas[nuevoPokemonFinal][0];
                                                        vidaMaxima[cantidad] = estadisticas[nuevoPokemonFinal][0];
                                                        cantidad++;

                                                        System.out.println("¡Has encontrado a " + nombres[nuevoPokemonFinal] + "!");
                                                        System.out.println(nombres[nuevoPokemonFinal] + " se ha unido a tu equipo.");

                                                    } else {
                                                        System.out.println("Has encontrado un Pokemon, pero tu equipo esta lleno.");
                                                    }
                                                    break;
                                                case 10:

                                                    if (cantidad < equipo.length) {

                                                        int nuevoPokemonFinal =
                                                                (int) (Math.random() * nombres.length);

                                                        equipo[cantidad] = nuevoPokemonFinal;
                                                        nivel[cantidad] = 1;
                                                        experiencia[cantidad] = 0;
                                                        vidaActual[cantidad] = estadisticas[nuevoPokemonFinal][0];
                                                        vidaMaxima[cantidad] = estadisticas[nuevoPokemonFinal][0];
                                                        cantidad++;

                                                        System.out.println("¡Has encontrado a " + nombres[nuevoPokemonFinal] + "!");
                                                        System.out.println(nombres[nuevoPokemonFinal] + " se ha unido a tu equipo.");

                                                    } else {
                                                        System.out.println("Has encontrado un Pokemon, pero tu equipo esta lleno.");
                                                    }
                                                    break;
                                            }

                                        } else {
                                            System.out.println("Decides reservar tus fuerzas y continuar.");
                                        }

                                        cuevaFinalExplorada = true;
                                    }

                                    // CURACION ANTES DEL COMBATE FINAL
                                    System.out.println();
                                    System.out.println("Antes de entrar en el santuario, encuentras un pequeño refugio.");
                                    System.out.println("Un cuidador se ofrece a curar a todo tu equipo.");
                                    System.out.println("¿Quieres que cure a tus Pokemon antes del combate contra Damián?");
                                    System.out.println("1. Si, curar a todo el equipo");
                                    System.out.println("2. No, continuar sin curarlos");
                                    System.out.print("Elige una opcion: ");

                                    int opcionCuracion = teclado.nextInt();

                                    while (opcionCuracion < 1 || opcionCuracion > 2) {
                                        System.out.print("Opcion no valida. Elige 1 o 2: ");
                                        opcionCuracion = teclado.nextInt();
                                    }

                                    if (opcionCuracion == 1) {

                                        for (int i = 0; i < cantidad; i++) {
                                            vidaActual[i] = vidaMaxima[i];
                                        }

                                        System.out.println("¡Todo tu equipo esta completamente curado!");

                                    } else {
                                        System.out.println("Decides guardar tus fuerzas y entrar tal como estas.");
                                    }

                                    // ENCUENTRO FINAL CON DAMIAN
                                    System.out.println();
                                    System.out.println("================================================");
                                    System.out.println("          EL CAMINO HACIA EL FINAL");
                                    System.out.println("================================================");
                                    System.out.println("Tras derrotar a Volkar, abandonas Ciudad Brasal.");
                                    System.out.println("El segundo gimnasio ha quedado atras. Solo queda una cosa:");
                                    System.out.println("resolver de una vez por todas tu rivalidad con Damián.");
                                    System.out.println();
                                    System.out.println("Hannibal: No voy a dejar que Damián decida hasta donde puedo llegar.");
                                    System.out.println("Al llegar al Puente de Ceniza, una figura te espera al otro lado.");
                                    System.out.println();
                                    System.out.println("Damián: Sabia que acabarias llegando hasta aqui.");
                                    System.out.println("Hannibal: Damián... ¿Que haces aqui?");
                                    System.out.println("Damián: Esperarte. No pienso permitir que me superes.");
                                    System.out.println("Damián: Antes eramos amigos, pero eso se acabo.");
                                    System.out.println("Damián: Hoy voy a demostrar quien es el mejor entrenador de Valthera.");
                                    System.out.println();
                                    System.out.println("Damián señala el antiguo Santuario de Valthera.");
                                    System.out.println("Damián: Entra. Aqui termina todo, Hannibal.");
                                    System.out.println("Cruzas las puertas del santuario y comienza el enfrentamiento definitivo.");
                                    System.out.println();

                                    // EQUIPO DEFINITIVO DE DAMIAN
                                    equipoRival[0] = 3;
                                    equipoRival[1] = 13;
                                    equipoRival[2] = 14;
                                    equipoRival[3] = 10;

                                    nivelRival[0] = 8;
                                    nivelRival[1] = 9;
                                    nivelRival[2] = 10;
                                    nivelRival[3] = 11;

                                    cantidadRival = 4;

                                    for (int i = 0; i < cantidadRival; i++) {
                                        vidaActualRival[i] = estadisticas[equipoRival[i]][0];
                                    }

                                    System.out.println("========== EQUIPO DE DAMIÁN ==========");

                                    for (int i = 0; i < cantidadRival; i++) {
                                        System.out.println((i + 1) + ". " + nombres[equipoRival[i]]);
                                        System.out.println("Nivel: " + nivelRival[i]);
                                        System.out.println("Vida: " + vidaActualRival[i]);
                                    }

                                    if (cantidadCombate == 0) {

                                        System.out.println();
                                        System.out.println("No tienes ningun Pokemon preparado para combatir.");
                                        System.out.println("Vuelve al menu y prepara tu equipo antes de enfrentarte a Damián.");

                                    } else {

                                        pokemonJugadorActual = 0;
                                        pokemonRivalActual = 0;
                                        combateActivo = true;

                                        double[][] efectividadFinal = {
                                            {1.0, 0.5, 1.5},
                                            {1.5, 1.0, 0.5},
                                            {0.5, 1.5, 1.0}
                                        };

                                        System.out.println();
                                        System.out.println("========== COMBATE FINAL ==========");

                                        while (combateActivo) {

                                            if (pokemonJugadorActual >= cantidadCombate || pokemonRivalActual >= cantidadRival) {
                                                combateActivo = false;
                                                break;
                                            }

                                            int posicionJugador = equipoCombate[pokemonJugadorActual];
                                            int indicePokemonJugador = equipo[posicionJugador];
                                            int indicePokemonRival = equipoRival[pokemonRivalActual];

                                            System.out.println();
                                            System.out.println("----------------------------------------");
                                            System.out.println("TU TURNO");
                                            System.out.println(nombres[indicePokemonJugador] + " - Vida: " + vidaActual[posicionJugador]);
                                            System.out.println(nombres[indicePokemonRival] + " de Damián - Vida: " + vidaActualRival[pokemonRivalActual]);

                                            System.out.println();
                                            System.out.println("Elige un ataque:");

                                            for (int i = 0; i < 3; i++) {
                                                int indiceAtaque = ataquesPokemon[indicePokemonJugador][i];

                                                System.out.println((i + 1) + ". " + nombresAtaques[indiceAtaque] + " - Potencia: " + potenciaAtaques[indiceAtaque]);
                                            }

                                            int opcionAtaqueFinal;

                                            do {
                                                System.out.print("Introduce el numero del ataque: ");
                                                opcionAtaqueFinal = teclado.nextInt();

                                                if (opcionAtaqueFinal < 1 || opcionAtaqueFinal > 3) {
                                                    System.out.println("Ataque no valido.");
                                                }

                                            } while (opcionAtaqueFinal < 1 || opcionAtaqueFinal > 3);

                                            int indiceAtaqueJugador =
                                                    ataquesPokemon[indicePokemonJugador][opcionAtaqueFinal - 1];

                                            int tipoAtaqueJugador = -1;

                                            if (indiceAtaqueJugador <= 2) {
                                                tipoAtaqueJugador = 0;
                                            } else if (indiceAtaqueJugador <= 5) {
                                                tipoAtaqueJugador = 1;
                                            } else if (indiceAtaqueJugador <= 8) {
                                                tipoAtaqueJugador = 2;
                                            }

                                            int tipoDefensorRival = 0;

                                            switch (tipos[indicePokemonRival]) {
                                                case "Fuego":
                                                    tipoDefensorRival = 0;
                                                    break;
                                                case "Agua":
                                                    tipoDefensorRival = 1;
                                                    break;
                                                case "Planta":
                                                    tipoDefensorRival = 2;
                                                    break;
                                            }

                                            double multiplicadorJugador = 1.0;

                                            if (tipoAtaqueJugador != -1) {
                                                multiplicadorJugador = efectividadFinal[tipoAtaqueJugador][tipoDefensorRival];
                                            }

                                            int danoJugador = (int) (
                                                    (potenciaAtaques[indiceAtaqueJugador]
                                                    + estadisticas[indicePokemonJugador][1]
                                                    + bonusAtaque[posicionJugador]
                                                    - estadisticas[indicePokemonRival][2] / 2.0)
                                                    * multiplicadorJugador
                                            );

                                            if (danoJugador < 1) {
                                                danoJugador = 1;
                                            }

                                            System.out.println(nombres[indicePokemonJugador] + " utiliza " + nombresAtaques[indiceAtaqueJugador] + "!");

                                            if (multiplicadorJugador > 1.0) {
                                                System.out.println("¡Es muy eficaz!");
                                            } else if (multiplicadorJugador < 1.0) {
                                                System.out.println("No es muy eficaz...");
                                            }

                                            System.out.println("Ha causado " + danoJugador + " puntos de daño.");

                                            vidaActualRival[pokemonRivalActual] -= danoJugador;

                                            if (vidaActualRival[pokemonRivalActual] < 0) {
                                                vidaActualRival[pokemonRivalActual] = 0;
                                            }

                                            if (vidaActualRival[pokemonRivalActual] == 0) {

                                                System.out.println(nombres[indicePokemonRival] + " de Damián ha sido debilitado.");

                                                pokemonRivalActual++;

                                                if (pokemonRivalActual >= cantidadRival) {
                                                    break;
                                                }

                                                System.out.println("Damián: ¡Adelante, " + nombres[equipoRival[pokemonRivalActual]] + "!");
                                                continue;
                                            }

                                            // TURNO DE DAMIAN
                                            System.out.println();
                                            System.out.println("TURNO DE DAMIÁN");

                                            int posicionAtaqueRival = (int) (Math.random() * 3);

                                            int indiceAtaqueRival = ataquesPokemon[indicePokemonRival][posicionAtaqueRival];

                                            int tipoAtaqueRival = -1;

                                            if (indiceAtaqueRival <= 2) {
                                                tipoAtaqueRival = 0;
                                            } else if (indiceAtaqueRival <= 5) {
                                                tipoAtaqueRival = 1;
                                            } else if (indiceAtaqueRival <= 8) {
                                                tipoAtaqueRival = 2;
                                            }

                                            int tipoDefensorJugador = 0;

                                            switch (tipos[indicePokemonJugador]) {
                                                case "Fuego":
                                                    tipoDefensorJugador = 0;
                                                    break;
                                                case "Agua":
                                                    tipoDefensorJugador = 1;
                                                    break;
                                                case "Planta":
                                                    tipoDefensorJugador = 2;
                                                    break;
                                            }

                                            double multiplicadorRival = 1.0;

                                            if (tipoAtaqueRival != -1) {
                                                multiplicadorRival = efectividadFinal[tipoAtaqueRival][tipoDefensorJugador];
                                            }

                                            int danoRival = (int) (
                                                    (potenciaAtaques[indiceAtaqueRival]
                                                    + estadisticas[indicePokemonRival][1]
                                                    - (estadisticas[indicePokemonJugador][2]
                                                    + bonusDefensa[posicionJugador]) / 2.0)
                                                    * multiplicadorRival
                                            );

                                            if (danoRival < 1) {
                                                danoRival = 1;
                                            }

                                            System.out.println(nombres[indicePokemonRival] + " utiliza " + nombresAtaques[indiceAtaqueRival] + "!");

                                            if (multiplicadorRival > 1.0) {
                                                System.out.println("¡Es muy eficaz!");
                                            } else if (multiplicadorRival < 1.0) {
                                                System.out.println("No es muy eficaz...");
                                            }

                                            System.out.println("Ha causado " + danoRival + " puntos de daño.");

                                            vidaActual[posicionJugador] -= danoRival;

                                            if (vidaActual[posicionJugador] < 0) {
                                                vidaActual[posicionJugador] = 0;
                                            }

                                            if (vidaActual[posicionJugador] == 0) {

                                                System.out.println(nombres[indicePokemonJugador] + " ha sido debilitado.");

                                                pokemonJugadorActual++;

                                                if (pokemonJugadorActual >= cantidadCombate) {
                                                    System.out.println("¡Te has quedado sin Pokemon!");
                                                    break;
                                                }

                                                int siguientePokemon = equipo[equipoCombate[pokemonJugadorActual]];

                                                System.out.println("¡Adelante, " + nombres[siguientePokemon] + "!");
                                            }
                                        }

                                        System.out.println();
                                        System.out.println("========== FIN DEL COMBATE FINAL ==========");

                                        if (pokemonRivalActual >= cantidadRival) {

                                            System.out.println("¡HAS DERROTADO A DAMIÁN!");
                                            System.out.println();
                                            System.out.println("Damián: No... No puede ser... ¿Como has podido vencerme?");
                                            System.out.println("Hannibal: Esto termina aqui, Damián. No tenias que convertirte en mi enemigo.");
                                            System.out.println("Damián: No necesito tu compasion. Esta derrota no cambia nada.");
                                            System.out.println();
                                            System.out.println("Damián se marcha del santuario sin mirar atras.");
                                            System.out.println("Has completado tu aventura por Valthera.");
                                            System.out.println("¡ENHORABUENA, HAS TERMINADO ACADEMIA DE CRIATURAS!");

                                            juegoActivo = false;

                                        } else {

                                            System.out.println("HAS PERDIDO EL COMBATE FINAL...");
                                            System.out.println("Damián: Te lo dije. No estabas preparado para derrotarme.");
                                            System.out.println("Retiras a tus Pokemon y decides recuperarte antes de volver a intentarlo.");

                                            for (int i = 0; i < cantidad; i++) {
                                                vidaActual[i] = vidaMaxima[i];
                                            }

                                            System.out.println("Tus Pokemon han recuperado toda su vida.");
                                            System.out.println("Puedes cambiar tu equipo y volver a enfrentarte a Damián.");
                                        }
                                    }
                                }

                                break;
                            }

                            // =========================================
                            // VER MIS CRIATURAS
                            // =========================================

                            case 2:

                                System.out.println();
                                System.out.println("=== MIS CRIATURAS ===");

                                if (cantidad == 0) {
                                    System.out.println("Todavia no tienes Pokemon.");
                                } else {

                                    for (int i = 0; i < cantidad; i++) {

                                        int indicePokemon = equipo[i];

                                        System.out.println();
                                        System.out.println(nombres[indicePokemon]);
                                        System.out.println("Tipo: " + tipos[indicePokemon]);
                                        System.out.println("Nivel: " + nivel[i]);
                                        System.out.println("Experiencia: " + experiencia[i] + "/100");
                                        System.out.println("Vida: " + vidaActual[i] + "/" + vidaMaxima[i]);
                                        System.out.println("Ataque: " + (estadisticas[indicePokemon][1] + bonusAtaque[i]));
                                        System.out.println("Defensa: " + (estadisticas[indicePokemon][2] + bonusDefensa[i]));
                                        System.out.println("Ataques:");

                                        for (int j = 0; j < 3; j++) {
                                            int indiceAtaque = ataquesPokemon[indicePokemon][j];

                                            System.out.println((j + 1) + ". " + nombresAtaques[indiceAtaque] + " (Potencia: " + potenciaAtaques[indiceAtaque] + ")");
                                        }
                                    }
                                }

                                break;

                            // =========================================
                            // TIENDA
                            // =========================================

                            case 3:

                                System.out.println();
                                System.out.println("======= BIENVENIDO A LA TIENDA ========");
                                System.out.println("Tienes " + monedas + " monedas.");
                                System.out.println();
                                System.out.println("1. Un caramelo (" + PRECIO_CARAMELO + " monedas)");
                                System.out.println("2. Pack de 10 caramelos (80 monedas)");
                                System.out.println("3. Salir de la tienda");
                                System.out.print("¿Que quieres comprar?: ");

                                int opcionTienda = teclado.nextInt();

                                if (opcionTienda == 1) {

                                    if (monedas >= PRECIO_CARAMELO) {
                                        caramelos = caramelos + 1;
                                        monedas = monedas - PRECIO_CARAMELO;
                                        System.out.println("Has comprado 1 caramelo.");
                                        System.out.println("Ahora tienes " + caramelos + " caramelos y " + monedas + " monedas.");
                                  } else {
                                        System.out.println("No tienes monedas suficientes.");
                                    }

                                } else if (opcionTienda == 2) {

                                    if (monedas >= 80) {
                                        caramelos = caramelos + 10;
                                        monedas = monedas - 80;
                                        System.out.println("Has comprado 10 caramelos.");
                                        System.out.println("Ahora tienes " + caramelos + " caramelos y " + monedas + " monedas.");
                                    } else {
                                        System.out.println("No tienes monedas suficientes.");
                                    }

                                } else if (opcionTienda == 3) {
                                    System.out.println("Sales de la tienda.");
                                } else {
                                    System.out.println("Opcion no valida.");
                                }

                                break;

                            // =========================================
                            // INVENTARIO
                            // =========================================

                            case 4:

                                System.out.println();
                                System.out.println("=== INVENTARIO ===");
                                System.out.println("Caramelos: " + caramelos);
                                System.out.println("Monedas: " + monedas);

                                break;

                            // =========================================
                            // PREPARAR EQUIPO DE COMBATE
                            // =========================================

                            case 5:

                                System.out.println();
                                System.out.println("=== EQUIPO DE COMBATE ===");

                                if (cantidad == 0) {
                                    System.out.println("Todavia no tienes ningun Pokemon.");
                                    break;
                                }

                                cantidadCombate = 0;

                                System.out.println("Estos son tus Pokemon:");

                                for (int i = 0; i < cantidad; i++) {
                                    System.out.println((i + 1) + ". " + nombres[equipo[i]]);
                                }

                                System.out.println("Puedes elegir hasta 3 Pokemon para combatir.");

                                while (cantidadCombate < 3 && cantidadCombate < cantidad) {

                                    int numero = 0;
                                    boolean repetido;

                                    do {
                                        repetido = false;

                                        System.out.println("Elige un Pokemon (introduce su numero):");
                                        numero = teclado.nextInt();

                                        if (numero < 1 || numero > cantidad) {
                                            System.out.println("Numero no valido, prueba otra vez.");
                                        } else {

                                            for (int j = 0; j < cantidadCombate; j++) {
                                                if (numero - 1 == equipoCombate[j]) {
                                                    repetido = true;
                                                    System.out.println("Ese Pokemon ya ha sido elegido.");
                                                }
                                            }
                                        }

                                    } while (numero < 1 || numero > cantidad || repetido);

                                    equipoCombate[cantidadCombate] = numero - 1;
                                    cantidadCombate++;

                                    System.out.println("Pokemon añadido al equipo.");

                                    if (cantidadCombate < 3 && cantidadCombate < cantidad) {

                                        int opcionEquipo;

                                        do {
                                            System.out.println("¿Quieres añadir otro Pokemon?");
                                            System.out.println("1. Si");
                                            System.out.println("2. No");

                                            opcionEquipo = teclado.nextInt();

                                            if (opcionEquipo != 1 && opcionEquipo != 2) {
                                                System.out.println("Opcion no valida. Elige 1 o 2.");
                                            }

                                        } while (opcionEquipo != 1 && opcionEquipo != 2);

                                        if (opcionEquipo == 2) {
                                            break;
                                        }
                                    }
                                }

                                System.out.println();
                                System.out.println("=== TU EQUIPO DE COMBATE ===");

                                for (int i = 0; i < cantidadCombate; i++) {
                                    System.out.println((i + 1) + ". " + nombres[equipo[equipoCombate[i]]]);
                                }

                                break;

                            case 6: 
                                System.out.println();
                                System.out.println("=== USAR CARAMELO ===");

                                if (caramelos == 0) {
                                    System.out.println("No tienes caramelos.");
                                } else {
                                    for (int i = 0; i < cantidad; i++) {
                                        System.out.println((i + 1) + ". " + nombres[equipo[i]] + " (Nivel " + nivel[i] + ")");
                                    }
                                    System.out.print("¿A cual das el caramelo? (0 para cancelar): ");
                                    int elegido = teclado.nextInt();

                                        while (elegido < 0 || elegido > cantidad) {
                                            System.out.print("Opcion no valida. Elige de nuevo: ");
                                            elegido = teclado.nextInt();
                                        }

                                        if (elegido > 0 && nivel[elegido] < 100) {
                                            int p = elegido - 1;

                                            nivel[p]++;
                                            caramelos--;

                                            vidaMaxima[p] += 3;
                                            vidaActual[p] += 3;
                                            bonusAtaque[p] += 3;
                                            bonusDefensa[p] += 3;

                                            System.out.println(nombres[equipo[p]] + " sube al nivel " + nivel[p] + ": Vida, Ataque y Defensa +3");
                                        }else{
                                            System.out.println("El Pokemon elegido ya está a nivel máximo");
                                        }
                                    
                                }

                            break;

                            // =========================================
                            // SALIR DEL JUEGO
                            // =========================================

                            case 7:

                                System.out.println();
                                System.out.println("Saliendo del juego...");
                                juegoActivo = false;

                                break;

                            default:

                                System.out.println("Opcion no valida.");
                                break;
                        }
                    }

                    break;

                case 2:

                    System.out.println("Saliendo del juego...");
                    juegoActivo = false;
                    break;

                default:

                    System.out.println("Opcion no valida.");
                    break;
            }
        }

        teclado.close();
    }
}