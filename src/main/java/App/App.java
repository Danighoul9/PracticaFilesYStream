package App;

import Models.Paciente;
import Services.ConsultasPacientes;
import Utils.CsvLoader;

import java.util.List;

public class App {
    static void main(String[] args) {

        //He añadido unos cuantos println para que se vea mas claro en el main y no se junten tanto
        //las consultas solicitadas :)

        //Cargar fichero ventas.csv
        List<Paciente> pacientes = CsvLoader.cargarPacientes();
        // pacientes.forEach(System.out::println);

        ConsultasPacientes cp = new ConsultasPacientes(pacientes);


        IO.println("=== Consulta 1: Pacientes mayores de 60 años ===");
        IO.println(cp.getPacientesMayoresde60());
        IO.println();

        IO.println("=== Consulta 2: Pacientes derivados ===");
        IO.println(cp.getPacientesDerivados());
        IO.println();

        IO.println("=== Consulta 3: Pacientes de Cardiología ===");
        IO.println(cp.getPacientesByEspecialidad());
        IO.println();

        IO.println("=== Consulta 4: Primera consulta de Sevilla ===");
        //Sale empty, supongo que porque no hay consultas en Sevilla
        IO.println(cp.getPrimeraConsultaMunicipio());
        IO.println();

        IO.println("=== Consulta 5:Pacientes con mayor tiempo de espera  ===");
        IO.println(cp.getPacientesMayorEspera());
        IO.println();

        IO.println("=== Consulta 6: Tiempo de espera medio general ===");
        IO.println(cp.getTiempoEsperaMedio() + " min");
        IO.println();

        IO.println("=== Consulta 7:  ===");
        IO.println( "Media: " + cp.getEstadisticasEdad().getAverage() + " años \n" +
                    "Edad mínima: " + cp.getEstadisticasEdad().getMin() + " años \n" +
                    "Edad máximaTotal: " + cp.getEstadisticasEdad().getMax() +" años ");
        IO.println();

        IO.println("=== Consulta 8: Consultas por especialidad ===");
        cp.getConsultasPorEspecialidad()
                .forEach((k,v) -> IO.println(k + ": " + v));
        IO.println();


        IO.println("=== Consulta 9: Pacientes por Municipio  ===");
        //Que locura no hay casi municipios repetidos...
        cp.getPacientesPorMunicipio()
                .forEach((k,v) -> IO.println(k + ": " + v));
        IO.println();

        IO.println("=== Consulta 10: Tiempo de espera por especialidad  ===");
        cp.getTiempoEsperaMedioByEspecialidad()
                .forEach((k,v) -> IO.println(k + ": " + v + " min"));
        IO.println();

        IO.println("=== Consulta 11: Numero de Pacientes derivados por Especialidad  ===");
        cp.getNumPacientesDerivadosByEspecialidad()
                .forEach((k,v) -> IO.println(k + ": " + v));
        IO.println();

        IO.println("=== Consulta 12: Ventas agrupadas por mes ===");
        cp.getVentasAgrupadasPorMes()
                .forEach((k,v) -> IO.println(k + ": " + v));
        IO.println();

        IO.println("=== Consulta 13: Especialidad con mas Pacientes derivados  ===");
        cp.getEspecialidadConMasPacientesDerivados();
        IO.println();

        IO.println("=== Consulta 14: Todos menores en Pediatría ¿? ===");
        IO.println("Todos los pacientes de Pediatría son menores de 15 años?: " + cp.isAllPacientesMenorDe15());
        IO.println();

        IO.println("=== Consulta 15:  ===");
        IO.println(cp.getPorcentajeDerivados() + "%");






    }
}
