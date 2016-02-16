/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades.conexion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;

public class VerificadorEntrada extends InputVerifier {

    public static enum TipoText {

        EMAIL, TEXT, CEDULA, NUMEROS
    }
    private Pattern pattern;
    private Matcher matcher;
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private int longitudCadena = 0;
    private boolean esObligatorio = false;
    private TipoText tipoValidacion;

    public VerificadorEntrada(boolean esObligatorio, TipoText tex) {
        tipoValidacion = tex;
        this.esObligatorio = esObligatorio;
    }

    public VerificadorEntrada(int longitudCadena, TipoText tipoValidacion) {
        this.longitudCadena = longitudCadena;
        this.tipoValidacion = tipoValidacion;
        this.esObligatorio = true;
    }

    private boolean esValidoEmail(String string) {
        switch (tipoValidacion) {
            case EMAIL:
                pattern = Pattern.compile(EMAIL_PATTERN);
                matcher = pattern.matcher(string);
                return matcher.matches();
            default:
                return false;
        }
    }



    public boolean esvalidadorDeCedula(String cedula) {
        boolean cedulaCorrecta = false;

        try {

            if (cedula.length() == 10) // ConstantesApp.LongitudCedula
            {
                int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
                if (tercerDigito < 6) {
                    // Coeficientes de validación cédula
                    // El decimo digito se lo considera dígito verificador
                    int[] coefValCedula = {2, 1, 2, 1, 2, 1, 2, 1, 2};
                    int verificador = Integer.parseInt(cedula.substring(9, 10));
                    int suma = 0;
                    int digito = 0;
                    for (int i = 0; i < (cedula.length() - 1); i++) {
                        digito = Integer.parseInt(cedula.substring(i, i + 1)) * coefValCedula[i];
                        suma += ((digito % 10) + (digito / 10));
                    }

                    if ((suma % 10 == 0) && (suma % 10 == verificador)) {
                        cedulaCorrecta = true;
                    } else if ((10 - (suma % 10)) == verificador) {
                        cedulaCorrecta = true;
                    } else {
                        cedulaCorrecta = false;
                    }
                } else {
                    cedulaCorrecta = false;
                }
            } else {
                cedulaCorrecta = false;
            }
        } catch (NumberFormatException nfe) {
            cedulaCorrecta = false;
        } catch (Exception err) {
            cedulaCorrecta = false;
        }

        if (!cedulaCorrecta) {
            System.out.println("La Cédula ingresada es Incorrecta");
        }
        return cedulaCorrecta;
    }

    @Override
    public boolean verify(JComponent input) {
        JTextComponent cmp = (JTextComponent) input;
        String texto = cmp.getText();

        if (esObligatorio && texto.isEmpty()) {
            JOptionPane.showMessageDialog(cmp.getParent(), "Este campo es Obligatorio");
            return false;
        } else {
            if (esObligatorio && texto.length() < longitudCadena) {
                JOptionPane.showMessageDialog(cmp.getParent(), "Este campo es minimo de :" + longitudCadena);
                return false;
            }
        }
        switch (tipoValidacion) {
            case EMAIL:
                if (!esValidoEmail(texto)) {
                    cmp.setText("");
                    JOptionPane.showMessageDialog(cmp.getParent(), "Email no Valido");
                    return false;
                }

                break;
            case CEDULA:

                if (!esvalidadorDeCedula(texto)){
                    cmp.setText("");
                    JOptionPane.showMessageDialog(cmp.getParent(), "Ingrese Cedulas Correctas");
                    return false;
                }
                    break;
        }
        return true;
    }
}
