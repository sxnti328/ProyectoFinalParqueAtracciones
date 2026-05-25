package org.example.proyectofinalcodigo;

import org.example.proyectofinalcodigo.model.clases.*;
import org.example.proyectofinalcodigo.model.enums.TipoAtraccion;
import org.example.proyectofinalcodigo.model.enums.TipoTicket;
import org.example.proyectofinalcodigo.model.records.Notificacion;

import java.time.LocalDate;

public class Parque {

    private static ParqueDeAtraccion instancia;

    private Parque() {}

    public static ParqueDeAtraccion get() {
        if (instancia == null) {
            instancia = new ParqueDeAtraccion("Tech-Park UQ", "NIT-123-456", "Calle 100 # 50-30", 500);
            cargarDatosDemostracion();
        }
        return instancia;
    }

    private static void cargarDatosDemostracion() {

        //Zonas (las 5 zonas del mapa)
        Zona zonaAltura   = new Zona("Z01", "Mecanica/Altura",  "Atracciones mecanicas y de altura",  150);
        Zona zonaAcuatica = new Zona("Z02", "Acuatica",         "Toboganes y atracciones de agua",     80);
        Zona zonaInfantil = new Zona("Z03", "Infantil",         "Atracciones para los mas pequeños",   60);
        Zona zonaFamiliar = new Zona("Z04", "Familiar",         "Atracciones para toda la familia",   120);
        Zona zonaShow     = new Zona("Z05", "Show",             "Espectaculos y teatro en vivo",       200);

        // Atracciones
        Atraccion montanaRusa = new Atraccion("A01", "Montaña Rusa",
                TipoAtraccion.MECANICA_ALTURA, 24, 1.40, 12, 5000);
        Atraccion torreCaida  = new Atraccion("A02", "Torre de Caida",
                TipoAtraccion.MECANICA_ALTURA, 16, 1.50, 14, 8000);

        Atraccion tobogan = new Atraccion("A03", "Tobogan Acuatico",
                TipoAtraccion.ACUATICA, 20, 1.20, 8, 3000);
        Atraccion botes   = new Atraccion("A04", "Botes en Rio",
                TipoAtraccion.ACUATICA, 30, 1.10, 6, 2000);

        Atraccion carrusel = new Atraccion("A05", "Carrusel Magico",
                TipoAtraccion.INFANTIL, 20, 0.80, 3, 0);
        Atraccion miniTren = new Atraccion("A06", "Mini Tren",
                TipoAtraccion.INFANTIL, 30, 0.70, 2, 0);

        Atraccion rueda      = new Atraccion("A07", "Rueda de la Fortuna",
                TipoAtraccion.FAMILIAR, 40, 1.00, 5, 2500);
        Atraccion cochesChoc = new Atraccion("A08", "Carritos Chocones",
                TipoAtraccion.FAMILIAR, 20, 1.10, 7, 1500);

        Atraccion teatro = new Atraccion("A09", "Teatro Tech-Park",
                TipoAtraccion.SHOW, 150, 0.00, 0, 0);

        zonaAltura.agregarAtraccion(montanaRusa);
        zonaAltura.agregarAtraccion(torreCaida);
        zonaAcuatica.agregarAtraccion(tobogan);
        zonaAcuatica.agregarAtraccion(botes);
        zonaInfantil.agregarAtraccion(carrusel);
        zonaInfantil.agregarAtraccion(miniTren);
        zonaFamiliar.agregarAtraccion(rueda);
        zonaFamiliar.agregarAtraccion(cochesChoc);
        zonaShow.agregarAtraccion(teatro);

        instancia.agregarZona(zonaAltura);
        instancia.agregarZona(zonaAcuatica);
        instancia.agregarZona(zonaInfantil);
        instancia.agregarZona(zonaFamiliar);
        instancia.agregarZona(zonaShow);

        // Administrador
        Administrador admin = new Administrador("profe jhan", "1001001", 35, "ADM01", "Gerencia");
        instancia.agregarAdministrador(admin);

        // ── Operadores (uno por zona) ──────────────────────────────────────
        Operador op1 = new Operador("Carlos Lopez",  "2001001", 28, "EMP01", "Manana", "Z01");
        Operador op2 = new Operador("Maria Ruiz",    "2002002", 25, "EMP02", "Tarde",  "Z02");
        Operador op3 = new Operador("Pedro Suarez",  "2003003", 30, "EMP03", "Manana", "Z03");
        Operador op4 = new Operador("Luisa Vargas",  "2004004", 27, "EMP04", "Tarde",  "Z04");
        Operador op5 = new Operador("Jorge Mora",    "2005005", 32, "EMP05", "Noche",  "Z05");

        zonaAltura.agregarOperador(op1);
        zonaAcuatica.agregarOperador(op2);
        zonaInfantil.agregarOperador(op3);
        zonaFamiliar.agregarOperador(op4);
        zonaShow.agregarOperador(op5);

        instancia.agregarOperador(op1);
        instancia.agregarOperador(op2);
        instancia.agregarOperador(op3);
        instancia.agregarOperador(op4);
        instancia.agregarOperador(op5);

        //Visitantes
        Visitante v1 = new Visitante("danielillo",    "1", 25, 1.75, 100000,
                "3001234567", "Calle 10 # 5-30");
        Visitante v2 = new Visitante("Sofia Torres",  "2", 22, 1.62,  80000,
                "3009876543", "Cra 20 # 15-40");
        Visitante v3 = new Visitante("Luis Gomez",    "3", 10, 1.20,  30000,
                "3015555555", "Av 30 # 22-10");
        Visitante v4 = new Visitante("Maria Ramirez", "4", 30, 1.68,  60000,
                "3101234567", "Calle 5 # 8-20");
        Visitante v5 = new Visitante("Carlos Mendez", "5", 16, 1.55,  25000,
                "3207654321", "Cra 15 # 3-10");

        instancia.agregarVisitante(v1);
        instancia.agregarVisitante(v2);
        instancia.agregarVisitante(v3);
        instancia.agregarVisitante(v4);
        instancia.agregarVisitante(v5);

        // Juan ya tiene Fast-Pass
        instancia.venderTicket("3001001", TipoTicket.FAST_PASS, 60000, 1);
        // Sofia ya tiene General
        instancia.venderTicket("3002002", TipoTicket.GENERAL, 25000, 1);

        // Favoritas pre-cargadas
        v1.agregarFavorita("A01");
        v1.agregarFavorita("A07");
        v2.agregarFavorita("A07");
        v2.agregarFavorita("A03");

        // Notificaciones pre-cargadas
        v1.agregarNotificacion(new Notificacion("BIENVENIDA",
                "Bienvenido al Tech-Park UQ, Disfruta tu visita!", LocalDate.now()));
        v2.agregarNotificacion(new Notificacion("SHOW",
                "El show en Teatro Tech-Park comienza en 30 minutos.", LocalDate.now()));
        v4.agregarNotificacion(new Notificacion("PROMO",
                "Hoy recarga tu saldo y accede a descuentos en atracciones.", LocalDate.now()));
    }
}
