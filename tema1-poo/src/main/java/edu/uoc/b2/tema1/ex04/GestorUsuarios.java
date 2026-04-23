package edu.uoc.b2.tema1.ex04;

import java.util.Optional;

/**
 * ============================================================
 * EXERCICIO 04 / EXERCICI 04 — Optional<T> ⭐⭐⭐⭐ (Medio-Alto / Mitjà-Alt)
 * ============================================================
 *
 * [CAT] OBJECTIU: Implementa els mètodes usant Optional correctament.
 *       - PROHIBIT usar optional.get() sense comprovar isPresent()
 *       - PROHIBIT retornar null — sempre retorna Optional.empty() si no hi ha valor
 *       - PROHIBIT capturar excepcions per retornar Optional.empty()
 *
 * [ES]  OBJETIVO: Implementa los métodos usando Optional correctamente.
 *       - PROHIBIDO usar optional.get() sin comprobar isPresent()
 *       - PROHIBIDO retornar null — siempre devuelve Optional.empty() si no hay valor
 *       - PROHIBIDO capturar excepciones para retornar Optional.empty()
 *
 * CONTEXT: Sistema de gestió d'usuaris amb dades opcionals / Sistema de gestión de usuarios con datos opcionales.
 */
public class GestorUsuarios {

    public record Direccion(String calle, String ciudad, String cp) {
    }

    public record Usuario(
            int id,
            String nombre,
            String email, // mai null / nunca null
            String telefono, // pot ser null / puede ser null
            Direccion direccion, // pot ser null / puede ser null
            Double descuento // pot ser null / puede ser null (0.0-1.0: 0%..100%)
    ) {
    }

    /**
     * [CAT] Cerca un usuari per ID. Retorna Optional.empty() si no existeix cap usuari amb aquell ID.
     * [ES]  Busca un usuario por ID. Devuelve Optional.empty() si no existe ningún usuario con ese ID.
     */
    public static Optional<Usuario> buscarPorId(java.util.List<Usuario> usuarios, int id) {
        // [ES]  TODO — stream().filter(u -> u.id() == id).findFirst()
        // [CAT] TODO — stream().filter(u -> u.id() == id).findFirst()
        //throw new UnsupportedOperationException("TODO");
        return usuarios.stream().filter(u -> u.id() == id).findFirst();
    }

    /**
     * [CAT] Retorna el telèfon de l'usuari encapsulat en Optional. Si el telèfon és null, retorna Optional.empty().
     * [ES]  Devuelve el teléfono del usuario encapsulado en Optional. Si el teléfono es null, devuelve Optional.empty().
     */
    public static Optional<String> getTelefono(Usuario usuario) {
        // [ES]  TODO — Optional.ofNullable(usuario.telefono())
        // [CAT] TODO — Optional.ofNullable(usuari.telefon())
        // throw new UnsupportedOperationException("TODO");
        return Optional.ofNullable(usuario.telefono());

    }

    /**
     * [CAT] Retorna la ciutat de l'adreça de l'usuari, o "Desconeguda" si no té adreça. Usa Optional.ofNullable + .map + .orElse
     * [ES]  Devuelve la ciudad de la dirección del usuario, o "Desconocida" si no tiene dirección. Usa Optional.ofNullable + .map + .orElse
     */
    public static String getCiudad(Usuario usuario) {
        // [ES]  TODO — Optional.ofNullable(usuario.direccion()).map(Direccion::ciudad).orElse("Desconocida")
        // [CAT] TODO — Optional.ofNullable(usuari.adreca()).map(Adreca::ciutat).orElse("Desconeguda")
        // throw new UnsupportedOperationException("TODO");
        return Optional.ofNullable(usuario.direccion()).map(Direccion::ciudad).orElse("Desconocida");
    }

    /**
     * [CAT] Calcula el preu final aplicant el descompte de l'usuari. Si l'usuari no té descompte (null), el preu no es modifica. Fórmula: preuFinal = preu * (1 - descompte)
     * [ES]  Calcula el precio final aplicando el descuento del usuario. Si el usuario no tiene descuento (null), el precio no se modifica. Fórmula: precioFinal = precio * (1 - descuento)
     */
    public static double calcularPrecioFinal(Usuario usuario, double precio) {
        // [ES]  TODO — Optional.ofNullable(usuario.descuento()).orElse(0.0) → aplica la fórmula
        // [CAT] TODO — Optional.ofNullable(usuari.descompte()).orElse(0.0) → aplica la fórmula
        // throw new UnsupportedOperationException("TODO");
        return (1.0 - (Optional.ofNullable(usuario.descuento()).orElse(0.0))) * precio;
    }

    /**
     * [CAT] Cerca un usuari per email (case-insensitive) i retorna el seu nom. Si no existeix cap usuari amb aquell email, llança NoSuchElementException. Usa .orElseThrow()
     * [ES]  Busca un usuario por email (case-insensitive) y devuelve su nombre. Si no existe ningún usuario con ese email, lanza NoSuchElementException. Usa .orElseThrow()
     */
    public static String nombrePorEmail(java.util.List<Usuario> usuarios, String email) {
        // [ES]  TODO — buscar por email + .map(Usuario::nombre) + .orElseThrow(...)
        // [CAT] TODO — cercar per email + .map(Usuari::nom) + .orElseThrow(...)
        // throw new UnsupportedOperationException("TODO");
        return usuarios.stream()
                .filter(u -> u.email().equalsIgnoreCase(email))
                .findFirst()
                .map(Usuario::nombre)
                .orElseThrow(() -> new java.util.NoSuchElementException("No se encontró ningún usuario con el email: " + email));
    }

    /**
     * [CAT] Retorna el primer usuari actiu que TINGUI descompte, si n'hi ha cap. Un usuari té descompte si el camp descompte és != null i > 0.
     * [ES]  Devuelve el primer usuario activo que TENGA descuento, si hay alguno. Un usuario tiene descuento si el campo descuento es != null y > 0.
     */
    public static Optional<Usuario> primeroConDescuento(java.util.List<Usuario> usuarios) {
        // [ES]  TODO — stream().filter(u -> u.descuento() != null && u.descuento() > 0).findFirst()
        // [CAT] TODO — stream().filter(u -> u.descompte() != null && u.descompte() > 0).findFirst()
        //throw new UnsupportedOperationException("TODO");
        return usuarios.stream()
                .filter(u -> u.descuento() != null && u.descuento() > 0)
                .findFirst();
    }
}
