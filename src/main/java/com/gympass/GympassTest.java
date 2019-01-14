package com.gympass;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.gympass.model.ProvaPiloto;
import com.gympass.model.VoltaPiloto;
import com.gympass.util.SortByHour;
import com.gympass.util.SortByLaptime;
import com.gympass.util.SortByRacetime;

public class GympassTest {

    public static void main(String[] args) {
        List<VoltaPiloto> voltas = new ArrayList<VoltaPiloto>();
        try {
            voltas = processarArquivo();
        } catch (IOException e) {
            e.printStackTrace();
        }
        processarCorrida(voltas);
    }

    private static List<VoltaPiloto> processarArquivo() throws IOException {
        List<VoltaPiloto> voltas = new ArrayList<VoltaPiloto>();
        Path path = Paths.get("race.txt");
        try (Stream<String> stream = Files.lines(path)) {
            voltas = stream.skip(1).map(line -> line.split("\\s+|\t\\s*"))
                    .map(data -> {
                        return mapVoltaPiloto(data);
                    }).collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("ERRO AO PROCESSAR O ARQUIVO DE LOG");
            throw e;
        }
        return voltas;
    }

    private static VoltaPiloto mapVoltaPiloto(String[] data) {
        VoltaPiloto voltaPiloto = new VoltaPiloto();
        try {
            SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm:ss.SSS");
            voltaPiloto.setHora(sdfHora.parse(data[0]));
            voltaPiloto.setNumeroPiloto(Integer.parseInt(data[1]));
            voltaPiloto.setNomePiloto(data[2]);
            voltaPiloto.setNumeroVolta(Integer.parseInt(data[3]));
            SimpleDateFormat sdfVolta = new SimpleDateFormat("mm:ss.SSS");
            voltaPiloto.setTempoVolta(sdfVolta.parse(data[4]));
            voltaPiloto.setVelocidadeMediaVolta(
                    Double.parseDouble(data[5].replaceAll(",", ".")));
        } catch (ParseException e) {
            System.err.println("ERRO AO REALIZAR PARSE DE DATA");
            e.printStackTrace();
        }
        return voltaPiloto;
    }

    private static void processarCorrida(List<VoltaPiloto> voltas) {
        // Ordenando as voltas da corrida pelo horario de término da volta
        Collections.sort(voltas, new SortByHour());

        Map<Integer, ProvaPiloto> prova = new HashMap<Integer, ProvaPiloto>();
        Map<Integer, List<VoltaPiloto>> voltasProva = new HashMap<Integer, List<VoltaPiloto>>();

        Integer maximoVoltasCompletadas = 0;

        for (VoltaPiloto voltaPiloto : voltas) {
            if (voltasProva.containsKey(voltaPiloto.getNumeroPiloto())) {
                voltasProva.get(voltaPiloto.getNumeroPiloto()).add(voltaPiloto);
                ProvaPiloto pp = prova.get(voltaPiloto.getNumeroPiloto());
                pp.setNumeroVoltasCompletadas(voltaPiloto.getNumeroVolta());
                pp.setTempoTotalProva(pp.getTempoTotalProva()
                        + voltaPiloto.getTempoVolta().getTime());
                prova.put(voltaPiloto.getNumeroPiloto(), pp);
                if (voltaPiloto.getNumeroVolta() > maximoVoltasCompletadas) {
                    maximoVoltasCompletadas = voltaPiloto.getNumeroVolta();
                }
            } else {
                List<VoltaPiloto> voltaList = new ArrayList<VoltaPiloto>();
                voltaList.add(voltaPiloto);
                voltasProva.put(voltaPiloto.getNumeroPiloto(), voltaList);
                prova.put(voltaPiloto.getNumeroPiloto(),
                        new ProvaPiloto(voltaPiloto.getNumeroPiloto(),
                                voltaPiloto.getTempoVolta().getTime(),
                                voltaPiloto.getNumeroVolta()));
            }
        }

        Collection<ProvaPiloto> provas = prova.values();

        List<ProvaPiloto> chegada = new ArrayList<ProvaPiloto>(provas);
        List<ProvaPiloto> retardatarios = new ArrayList<ProvaPiloto>();

        // Ordena pelo tempo de prova de cada piloto
        Collections.sort(chegada, new SortByRacetime());
        SimpleDateFormat sdfHora = new SimpleDateFormat("mm:ss.SSS");

        for (ProvaPiloto grid : chegada) {
            if (!grid.getNumeroVoltasCompletadas()
                    .equals(maximoVoltasCompletadas)) {
                retardatarios.add(grid);
            }
        }

        for (ProvaPiloto retardatario : retardatarios) {
            // Remove o retardatario do inicio da lista e adiciona ao final
            chegada.remove(retardatario);
            chegada.add(retardatario);
        }

        System.out.println(
                "Posição Chegada       Código Piloto  Nome Piloto        Qtde Voltas Completadas Tempo Total de Prova");
        for (ProvaPiloto grid : chegada) {
            // Imprime grid de chegada
            System.out.println((1 + chegada.indexOf(grid))
                    + "                     " + grid.getNumeroPiloto()
                    + "             " + obterNomePiloto(grid, voltasProva)
                    + "                     "
                    + grid.getNumeroVoltasCompletadas() + "            "
                    + sdfHora.format(new Date(grid.getTempoTotalProva())));
        }

        // Melhor volta da corrida
        Collections.sort(voltas, new SortByLaptime());
        System.out.println("MELHOR VOLTA DA CORRIDA: "
                + voltas.get(0).getNumeroPiloto() + " - "
                + voltas.get(0).getNomePiloto() + "  " + "Número volta: "
                + voltas.get(0).getNumeroVolta() + "   Tempo volta: "
                + sdfHora.format(voltas.get(0).getTempoVolta()));

        // Melhor volta de cada piloto
        for (Integer numeroPiloto : voltasProva.keySet()) {
            Collections.sort(voltasProva.get(numeroPiloto),
                    new SortByLaptime());
            System.out.println("MELHOR VOLTA DE CADA PILOTO: "
                    + voltasProva.get(numeroPiloto).get(0).getNumeroPiloto()
                    + " - "
                    + voltasProva.get(numeroPiloto).get(0).getNomePiloto()
                    + "  " + "Número volta: "
                    + voltasProva.get(numeroPiloto).get(0).getNumeroVolta()
                    + "   Tempo volta: " + sdfHora.format(voltasProva
                            .get(numeroPiloto).get(0).getTempoVolta()));
        }

    }

    private static String obterNomePiloto(ProvaPiloto piloto,
            Map<Integer, List<VoltaPiloto>> voltasPiloto) {
        String nomePiloto = voltasPiloto.get(piloto.getNumeroPiloto()).get(0)
                .getNomePiloto();
        return nomePiloto;
    }
}