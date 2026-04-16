package App;

import Models.Paciente;
import Services.ConsultasPacientes;
import Utils.CsvLoader;

import java.util.List;

public class Main {
    static void main(String[] args) {
        //Cargar fichero ventas.csv
        List<Paciente> pacientes = CsvLoader.cargarPacientes();
        // pacientes.forEach(System.out::println);

        ConsultasPacientes cp = new ConsultasPacientes(pacientes);

        //1.
        IO.println(cp.getPacientesMayoresde60());




    }
}
