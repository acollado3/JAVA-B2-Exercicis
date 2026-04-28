package edu.uoc.b2.tema6.ex02;



/**
 * ============================================================
 * EXERCICI 02 Excepcions / Excepciones — Custom exception wrapping ⭐⭐⭐
 * ============================================================
 *
 * [CAT] OBJECTIU: Crear un procés de validació simple que embolcalli (wrap)
 * errors estranys
 * i utilitzi una Checked Exception personalitzada `UsuarioInvalidoException`.
 * [ES] OBJETIVO: Crear un proceso de validación simple que envuelva (wrap)
 * errores extraños
 * y utilice una Checked Exception personalizada `UsuarioInvalidoException`.
 */
public class ValidadorUsuario {

    /**
     * [CAT] Valida i retorna l'edat extreta des d'un camp formatat de text.
     * [ES] Valida y retorna la edad extraída desde un campo formateado de texto.
     *
     * @param infoUsuario ex: "Nom,18" o "Maria,"
     * @throws UsuarioInvalidoException si l'string està corrupte o no té edat / si
     *                                  el string está corrupto o no tiene edad
     */
    public static int validarYExtraerEdad(String infoUsuario) throws UsuarioInvalidoException {
        // [ES] TODO —
        // 1. Si infoUsuario es nulo (null) o vacío, lanza
        // UsuarioInvalidoException("Usuario inválido por cadena vacía").
        // 2. Extrae la edad haciendo un .split(","). Si array length != 2, lanza
        // UsuarioInvalidoException.
        // 3. Haz Integer.parseInt(array[1]). Si salta NumberFormatException, atrápaloc
        // y tíralo embolcallado (wrap) en UsuarioInvalidoException("Edad inválida",
        // causa).
        // 4. Si edad < 18, también tira UsuarioInvalidoException.
        // 5. Retorna la edad extraída.
        //
        // [CAT] TODO —
        // 1. Si infoUsuario és nul (null) o buit, llança
        // UsuarioInvalidoException("Usuari invàlid per cadena buida").
        // 2. Extreu l'edat fent un .split(","). Si array length != 2, llança
        // UsuarioInvalidoException.
        // 3. Fes Integer.parseInt(array[1]). Si salta NumberFormatException, atrapa'l i
        // tira'l embolcallat (wrap) en UsuarioInvalidoException("Edat invàlida",
        // causa).
        // 4. Si edad < 18, també tira UsuarioInvalidoException.
        // 5. Retorna l'edat extreta.
        try
        {
            if(infoUsuario==null || infoUsuario.isEmpty())
            {
                throw new UsuarioInvalidoException("Usuario inválido por cadena vacía");
            }
            String[] array=infoUsuario.split(",");
            if(array.length!=2)
            {
                throw new UsuarioInvalidoException("Usuario inválido por formato incorrecto");
            }
            int edad=Integer.parseInt(array[1]);
            if(edad<18)
            {
                throw new UsuarioInvalidoException("Usuario inválido por edad menor de 18");
            }
            return edad;

        }
        catch (NumberFormatException ex)
        {
            throw new UsuarioInvalidoException("Edad inválida", ex);
        }


       // throw new UnsupportedOperationException("TODO");

    }
}
