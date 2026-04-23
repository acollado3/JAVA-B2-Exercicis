package edu.uoc.b2.tema1.ex05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ============================================================
 * EXERCICIO 05 / EXERCICI 05 — Patrón Observer / Patró Observer ⭐⭐⭐⭐⭐ (Difícil)
 * ============================================================
 *
 * [CAT] OBJECTIU: Implementa el patró Observer per a un sistema de notificacions.
 *       Un "canal" pot tenir múltiples "subscriptors". Quan el canal
 *       publica un missatge, TOTS els subscriptors el reben.
 *
 * [ES]  OBJETIVO: Implementa el patrón Observer para un sistema de notificaciones.
 *       Un "canal" puede tener múltiples "suscriptores". Cuando el canal
 *       publica un mensaje, TODOS los suscriptores lo reciben.
 *
 * PARTES A IMPLEMENTAR / PARTS A IMPLEMENTAR:
 * 1. Intefaz / Interfície Suscriptor (Observer)
 * 2. Clase CanalNotificacion (Subject / Observable)
 * 3. Implementación / Implementació LogSuscriptor
 * 4. Implementación / Implementació EmailSuscriptor
 * 5. Implementación / Implementació FiltroSuscriptor (decorador — filtra mensajes/missatges per palabra clave / paraula clau)
 *
 * DIAGRAMA:
 *
 * «interface» CanalNotificacion
 * Suscriptor ─────────────────
 * ────────────── ┌──────► suscriptores: List<Suscriptor>
 * + recibir(msg: String) │ + suscribir(s: Suscriptor)
 * ▲ │ + desvincular(s: Suscriptor)
 * │ │ + publicar(msg: String)
 * ┌─────┴──────────────┐ │
 * │ │ │
 * LogSuscriptor EmailSuscriptor FiltroSuscriptor (wraps another)
 * mensajes:List<String> emails:List<String> filtro:String + destino:Suscriptor
 */
public class SistemaNotificaciones {

    /**
     * [CAT] Interfície Observer — tot subscriptor ha d'implementar-la.
     * [ES]  Interfaz Observer — todo suscriptor debe implementarla.
     */
    public interface Suscriptor {
        /**
         * [CAT] Rep un missatge del canal al qual està subscrit.
         * [ES]  Recibe un mensaje del canal al que está suscrito.
         */
        void recibir(String mensaje);
    }

    /**
     * [CAT] Subject (Observable) — manté la llista de subscriptors i els notifica.
     * [ES]  Subject (Observable) — mantiene la lista de suscriptores y los notifica.
     */
    public static class CanalNotificacion {

        private final String nombre;
        // [ES]  TODO — Declara una List<Suscriptor> privada para almacenar los suscriptores
        // [CAT] TODO — Declara una List<Suscriptor> privada per emmagatzemar els subscriptors
        private final List<Suscriptor> suscriptores;

        public CanalNotificacion(String nombre) {
            this.nombre = nombre;
            // [ES]  TODO — inicializa la lista de suscriptores (new ArrayList<>())
            // [CAT] TODO — inicialitza la llista de subscriptors (new ArrayList<>())
            this.suscriptores = new ArrayList<>();
        }

        public String getNombre() {
            return nombre;
        }

        /**
         * [CAT] Afegeix un subscriptor. Si ja existeix, no l'afegeix de nou.
         * [ES]  Añade un suscriptor. Si ya existe, no lo añade de nuevo.
         */
        public void suscribir(Suscriptor suscriptor) {
            // [ES]  TODO — añade a la lista si no está ya (usa !suscriptores.contains(...))
            // [CAT] TODO — afegeix a la llista si no hi és ja (usa !suscriptores.contains(...))
            // throw new UnsupportedOperationException("TODO");
            if (suscriptor != null && !suscriptores.contains(suscriptor)) {
                suscriptores.add(suscriptor);
            }
        }

        /**
         * [CAT] Elimina un subscriptor. Si no existeix, no fa res.
         * [ES]  Elimina un suscriptor. Si no existe, no hace nada.
         */
        public void desvincular(Suscriptor suscriptor) {
            // [ES]  TODO — elimina de la lista
            // [CAT] TODO — elimina de la llista
            //throw new UnsupportedOperationException("TODO");
            suscriptores.remove(suscriptor);
        }

        /**
         * [CAT] Notifica TOTS els subscriptors amb el missatge.
         *       Format del missatge que rep cada subscriptor: "[NomCanal] missatge"
         *       Exemple: "[Esports] El Barça ha guanyat!"
         * [ES]  Notifica TODOS los suscriptores con el mensaje.
         *       Formato del mensaje que recibe cada suscriptor: "[NombreCanal] mensaje"
         *       Ejemplo: "[Deportes] ¡El Barça ha ganado!"
         */
        public void publicar(String mensaje) {
            // [ES]  TODO — itera suscriptores y llama recibir("[" + nombre + "] " + mensaje)
            // [CAT] TODO — itera subscriptors i crida recibir("[" + nombre + "] " + mensaje)
            //throw new UnsupportedOperationException("TODO");
            String mensajeFormatejat = "[" + nombre + "] " + mensaje;
            // Iterem sobre la llista per notificar a cadascú
            for (Suscriptor s : suscriptores) {
                s.recibir(mensajeFormatejat);
            }
        }

        /**
         * [CAT] Retorna el nombre de subscriptors actuals.
         * [ES]  Devuelve el número de suscriptores actuales.
         */
        public int numeroSuscriptores() {
            // [ES]  TODO — devuelve el tamaño de la lista
            // [CAT] TODO — retorna la mida de la llista
            // throw new UnsupportedOperationException("TODO");
            return suscriptores.size();
        }
    }

    /**
     * [CAT] Subscriptor que guarda tots els missatges rebuts en una llista interna. Útil per a logs i tests.
     * [ES]  Suscriptor que guarda todos los mensajes recibidos en una lista interna. Útil para logs y tests.
     */
    public static class LogSuscriptor implements Suscriptor {

        // [ES]  TODO — Declara una List<String> privada para guardar los mensajes recibidos
        // [CAT] TODO — Declara una List<String> privada per guardar els missatges rebuts
        private final List<String> mensajesRecibidos = new ArrayList<>();
        @Override
        public void recibir(String mensaje) {
            // [ES]  TODO — añade el mensaje a la lista
            // [CAT] TODO — afegeix el missatge a la llista
            //throw new UnsupportedOperationException("TODO");
            mensajesRecibidos.add(mensaje);
        }

        /**
         * [CAT] Retorna tots els missatges rebuts (en ordre d'arribada).
         * [ES]  Devuelve todos los mensajes recibidos (en orden de llegada).
         */
        public List<String> getMensajesRecibidos() {
            // [ES]  TODO — devuelve una copia defensiva de la lista (Pista: Collections.unmodifiableList(...))
            // [CAT] TODO — retorna una còpia defensiva de la llista (Pista/Consell: Collections.unmodifiableList(...))
            // throw new UnsupportedOperationException("TODO");
            return Collections.unmodifiableList(mensajesRecibidos);
        }

        /**
         * [CAT] Retorna el nombre de missatges rebuts.
         * [ES]  Devuelve el número de mensajes recibidos.
         */
        public int contarMensajes() {
            // [ES]  TODO
            // [CAT] TODO
            //throw new UnsupportedOperationException("TODO");
            return mensajesRecibidos.size();
        }
    }

    /**
     * [CAT] Subscriptor que simula enviar un email (guarda una llista de "emails enviats").
     * [ES]  Suscriptor que simula enviar un email (guarda una lista de "emails enviados").
     */
    public static class EmailSuscriptor implements Suscriptor {

        private final String direccion;
        // [ES]  TODO — List<String> para guardar los emails enviados
        // [CAT] TODO — List<String> per guardar els emails enviats
        private final List<String> emailsEnviados= new ArrayList<>();

        public EmailSuscriptor(String direccion) {
            this.direccion = direccion;
            // [ES]  TODO — inicializa la lista
            // [CAT] TODO — inicialitza la llista
        }

        public String getDireccion() {
            return direccion;
        }

        @Override
        public void recibir(String mensaje) {
            // [ES]  TODO — guarda en la lista un String del formato: "To: " + direccion + " | " + mensaje
            // [CAT] TODO — guarda a la llista un String del format: "To: " + direccion + " | " + mensaje
            // throw new UnsupportedOperationException("TODO");
            emailsEnviados.add("To: " + direccion + " | " + mensaje);
        }

        public List<String> getEmailsEnviados() {
            // [ES]  TODO — devuelve la lista (copia defensiva)
            // [CAT] TODO — retorna la llista (còpia defensiva)
            //throw new UnsupportedOperationException("TODO");
            return Collections.unmodifiableList(emailsEnviados);
        }
    }

    /**
     * [CAT] Subscriptor decorador que FILTRA missatges per paraula clau.
     *       Només reenvia al subscriptor destí els missatges que CONTINGUIN la paraula clau (case-insensitive).
     * [ES]  Suscriptor decorador que FILTRA mensajes por palabra clave.
     *       Solo reenvía al suscriptor destino los mensajes que CONTENGAN la palabra clave (case-insensitive).
     */
    public static class FiltroSuscriptor implements Suscriptor {

        // [ES]  TODO — campo 'filtro: String' (palabra clave) y 'destino: Suscriptor' (suscriptor al que reenvía)
        // [CAT] TODO — camp 'filtro: String' (paraula clau) i 'destino: Suscriptor' (subscriptor al qual reenvia)
        private final String filtro;
        private final Suscriptor destino;
        public FiltroSuscriptor(String filtro, Suscriptor destino) {
            // [ES]  TODO — inicializa campos
            // [CAT] TODO — inicialitza camps
            this.destino=destino;
            this.filtro=filtro;
            //throw new UnsupportedOperationException("TODO");
        }

        @Override
        public void recibir(String mensaje) {
            // [ES]  TODO — si mensaje.toLowerCase().contains(filtro.toLowerCase()) → destino.recibir(mensaje)
            // [CAT] TODO — si mensaje.toLowerCase().contains(filtro.toLowerCase()) → destino.recibir(mensaje)
            //throw new UnsupportedOperationException("TODO");
            if(mensaje.toLowerCase().contains(filtro.toLowerCase()))
            {
                destino.recibir(mensaje);
            }
        }
    }
}
