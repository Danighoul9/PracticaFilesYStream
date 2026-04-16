package Utils;


import Models.Paciente;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CsvLoader {
    public static List<Paciente> cargarPacientes(){
        Path archivo = Paths.get("src/main/resources/pacientes.csv");
        List<Paciente> pacientes = new ArrayList<>();

        try {
            //Leer todas las filas del fichero pacientes.csv
            List<String> filas = Files.readAllLines(archivo);

            //Eliminar la primera fila (ENCABEZADO)
            filas.removeFirst();

            //Convertir cada fila String a un objeto venta
            filas.forEach(l -> {
                List<String> campos = List.of(l.split(","));
                //Añadimos a List<Pacinente> un objeto nuevo Venta a partir de la fila
                pacientes.add(new Paciente(
                        Long.parseLong(campos.get(0)),
                        campos.get(1),
                        Integer.parseInt(campos.get(2)),
                        campos.get(3),
                        campos.get(4),
                        campos.get(5),
                        LocalDate.parse(campos.get(6)),
                        Integer.parseInt(campos.get(7)),
                        Boolean.parseBoolean(campos.get(8))
                ));
            }) ;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return pacientes;
    }
}