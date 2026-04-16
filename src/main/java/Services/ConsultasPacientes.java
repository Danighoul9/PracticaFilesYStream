package Services;

import Models.Paciente;

import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class ConsultasPacientes {

    private List<Paciente> pacientes;
    public ConsultasPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    /**Consulta 1 — Pacientes mayores de una edad dada
     Mostrar los pacientes con edad superior a 60 años, ordenados de mayor a menor edad.
     Streams a usar: filter , sorted*/
    public List<Paciente> getPacientesMayoresde60() {
        return pacientes.stream()
                .filter(p -> p.getEdad() > 60)
                .sorted(Comparator.comparing(Paciente::getEdad).reversed())
                .toList();
    }

    /**Consulta 2 — Pacientes derivados a otro especialista
    Obtener la lista de pacientes que han sido derivados, ordenados alfabéticamente por nombre.
    Streams a usar: filter , sorted*/
    public List<Paciente> getPacientesDerivados() {
        return pacientes.stream()
                .filter(Paciente::isDerivado)
                .sorted(Comparator.comparing(Paciente::getNombre))
                .toList();
    }

    /**Consulta 3 — Nombres de pacientes de una especialidad concreta
    Obtener los nombres de los pacientes atendidos en Cardiología , sin repeticiones.
    Streams a usar: filter , map , distinct*/
    public List<String> getPacientesByEspecialidad() {
        return pacientes.stream()
                .filter(p-> p.getEspecialidad().equals("Cardiología"))
                .map(Paciente::getNombre)
                .distinct()
                .toList();
    }

    /**Consulta 4 — Primera consulta encontrada de un municipio
    Buscar el primer paciente registrado del municipio Sevilla .
    Streams a usar: filter , findFirst*/
    public Optional<Paciente> getPrimeraConsultaMunicipio() {
        return pacientes.stream()
                .filter(p -> p.getMunicipio().equals("Sevilla"))
                .findFirst();
    }

    /**Consulta 5 — Pacientes con mayor tiempo de espera
    Mostrar los 10 pacientes con mayor tiempo de espera, de mayor a menor.
    Streams a usar: sorted , limit*/
    public List<Paciente> getPacientesMayorEspera(){
        return pacientes.stream()
                .sorted(Comparator.comparing(Paciente::getTiempoEsperaMin).reversed())
                .limit(10)
                .toList();
    }

    /**Consulta 6 — Tiempo de espera medio general
    Calcular el tiempo de espera medio de todos los pacientes.
    Streams a usar: mapToInt , average*/
    public Double getTiempoEsperaMedio(){
        return pacientes.stream()
                .mapToInt(Paciente::getTiempoEsperaMin)
                .average()
                .getAsDouble();
    }

    /**Consulta 7 — Estadísticas de edad
    Obtener las siguientes estadísticas sobre la edad de los pacientes:
    Media
    Edad mínima
    Edad máximaTotal de registros
    Streams a usar: mapToInt , summaryStatistics*/
    public IntSummaryStatistics getEstadisticasEdad(){
        return pacientes.stream()
                .mapToInt(Paciente::getEdad)
                .summaryStatistics();
    }

    /**Consulta 8 — Número de consultas por especialidad
    Crear un mapa donde la clave sea la especialidad y el valor sea el número de consultas
    realizadas de esa especialidad.
    Streams a usar: collect , groupingBy , counting*/
    public  Map<String,Long> getConsultasPorEspecialidad() {
        return pacientes.stream()
                .collect(Collectors.groupingBy(Paciente::getEspecialidad,
                                                Collectors.counting()));

    }

    /**Consulta 9 — Número de pacientes por municipio
    Crear un mapa donde la clave sea el municipio y el valor sea el número de pacientes atendidos
    en ese municipio.
    Streams a usar: collect , groupingBy , counting*/
    public Map<String,Long> getPacientesPorMunicipio() {
        return pacientes.stream()
                .collect(Collectors.groupingBy(Paciente::getMunicipio,
                        Collectors.counting()));
    }

    /**Consulta 10 — Tiempo de espera medio por especialidad
    Obtener el tiempo de espera medio de cada especialidad.
    Streams a usar: collect , groupingBy , averagingInt*/
    public Map<String,Double> getTiempoEsperaMedioByEspecialidad() {
        return pacientes.stream()
                .collect(Collectors.groupingBy(Paciente::getEspecialidad,
                         Collectors.averagingInt(Paciente::getTiempoEsperaMin)));
    }

    /**Consulta 11 — Número de pacientes derivados por especialidad
    Crear un mapa con el número de pacientes derivados agrupados por especialidad.
    Streams a usar: filter , collect , groupingBy , counting*/
    public Map<String,Long> getNumPacientesDerivadosByEspecialidad() {
        return pacientes.stream()
                .filter(Paciente::isDerivado)
                .collect(Collectors.groupingBy(Paciente::getEspecialidad,
                         Collectors.counting()));

    }

    /**Consulta 12 — Consultas agrupadas por mes
    Crear un mapa donde la clave sea el mes de consulta (valor numérico del 1 al 12) y el valor sea
    el número de consultas de ese mes.
    Streams a usar: collect , groupingBy , counting*/
    public Map<Month, Long> getVentasAgrupadasPorMes() {
        return pacientes.stream()
                .collect(Collectors.groupingBy(p -> p.getFechaConsulta().getMonth(),
                         Collectors.counting()));
    }

    /**Consulta 13 — Especialidad con más pacientes derivados
    Obtener la especialidad que más pacientes ha derivado.
    Streams a usar: filter , collect , groupingBy , counting , max*/
    public void getEspecialidadConMasPacientesDerivados() {
        //Literalmente lo he copiado del de ventas he cambiado algunas cosas y ha funcionado :)
        Map<String, Long> PacientesPorEspecialidad = pacientes.stream()
                .collect(Collectors.groupingBy(Paciente::getEspecialidad,
                        Collectors.counting()));

        PacientesPorEspecialidad.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .ifPresent(entry -> IO.println(entry.getKey()));
    }


    /**Consulta 14 — Comprobar si todos los pacientes de Pediatría son menores
    de 15 años
    Verificar si todos los pacientes atendidos en Pediatría tienen menos de 15 años.
    Streams a usar: filter , allMatch*/
    public boolean isAllPacientesMenorDe15(){
        return pacientes.stream()
                .filter(p -> p.getEspecialidad().equals("Pediatría"))
                .allMatch(p -> p.getEdad() < 15);
    }

    /**
     * Consulta 15 — Porcentaje de pacientes derivados
     * Calcular qué porcentaje del total de pacientes han sido derivados a otro especialista.
     * Streams a usar: filter , count
     */
    public long getPorcentajeDerivados(){
        Long PacientesDerivados = pacientes.stream()
                .filter(Paciente::isDerivado)
                .count();

        return (long) ((double) PacientesDerivados / pacientes.size() * 100.0);
    }

}
