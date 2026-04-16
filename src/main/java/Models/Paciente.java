package Models;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Paciente {
    private long id;
    private String nombre;
    private int edad;
    private String sexo;
    private String municipio;
    private String especialidad;
    private LocalDate fechaConsulta;
    private int tiempoEsperaMin;
    private boolean derivado;


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("|| ID: ");
        sb.append(id);
        sb.append("| ").append(nombre);
        sb.append("| ").append(edad);
        sb.append("| ").append(sexo);
        sb.append("| ").append(municipio);
        sb.append("| ").append(especialidad);
        sb.append("| ").append(fechaConsulta);
        sb.append("| ").append(tiempoEsperaMin);
        sb.append("| ").append(derivado + " ||").append('\n');
        return sb.toString();
    }
}
