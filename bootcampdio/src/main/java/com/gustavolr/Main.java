package com.gustavolr;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.gustavolr.dominio.Bootcamp;
import com.gustavolr.dominio.Curso;
import com.gustavolr.dominio.Dev;
import com.gustavolr.dominio.Mentoria;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static List<Curso> cursos = new ArrayList<>();
    private static List<Mentoria> mentorias = new ArrayList<>();
    private static List<Bootcamp> bootcamps = new ArrayList<>();
    private static List<Dev> devs = new ArrayList<>();

    public static void main(String[] args) {
        int opcao;

        limparTela();

        do {
            imprimirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir a nova linha

            limparTela();
            switch (opcao) {
                case 1:
                    criarCurso();
                    break;
                case 2:
                    criarMentoria();
                    break;
                case 3:
                    criarBootcamp();
                    break;
                case 4:
                    inscreverDevEmBootcamp();
                    break;
                case 5:
                    progredirDev();
                    break;
                case 6:
                    exibirStatusDevs();
                    break;
                case 7:
                    listarCursos();
                    break;
                case 8:
                    listarMentorias();
                    break;
                case 9:
                    listarBootcamps();
                    break;
                case 10:
                    listarDevs();
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

            System.out.println("Pressione \"Enter\" para continuar...");
            scanner.nextLine();
            limparTela();
        } while (opcao != 0);
    }

    public static void limparTela() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void imprimirMenu() {
        System.out.println("Menu:");
        System.out.println("1. Criar Curso");
        System.out.println("2. Criar Mentoria");
        System.out.println("3. Criar Bootcamp");
        System.out.println("4. Inscrever Dev em Bootcamp");
        System.out.println("5. Progredir Dev");
        System.out.println("6. Exibir Status dos Devs");
        System.out.println("7. Listar cursos");
        System.out.println("8. Listar mentorias");
        System.out.println("9. Listar bootcamps");
        System.out.println("10. Listar devs\n");
        System.out.println("0. Sair\n");
        System.out.print("Escolha uma opção: ");
    }

    private static void criarCurso() {
        Curso curso = new Curso();
        System.out.print("Título do curso: ");
        curso.setTitulo(scanner.nextLine());
        System.out.print("Descrição do curso: ");
        curso.setDescricao(scanner.nextLine());
        System.out.print("Carga horária: ");
        curso.setCargaHoraria(scanner.nextInt());
        scanner.nextLine();  // Consumir a nova linha

        cursos.add(curso);
        System.out.println("Curso criado com sucesso!\n");
    }

    private static void criarMentoria() {
        Mentoria mentoria = new Mentoria();
        System.out.print("Título da mentoria: ");
        mentoria.setTitulo(scanner.nextLine());
        System.out.print("Descrição da mentoria: ");
        mentoria.setDescricao(scanner.nextLine());
        mentoria.setData(LocalDate.now());

        mentorias.add(mentoria);
        System.out.println("Mentoria criada com sucesso!\n");
    }

    private static void criarBootcamp() {
        Bootcamp bootcamp = new Bootcamp();
        System.out.print("Nome do Bootcamp: ");
        bootcamp.setNome(scanner.nextLine());
        System.out.print("Descrição do Bootcamp: ");
        bootcamp.setDescricao(scanner.nextLine());

        System.out.println("Adicionar cursos e mentorias ao Bootcamp:");
        int opcao;
        do {
            System.out.println("1. Adicionar Curso");
            System.out.println("2. Adicionar Mentoria");
            System.out.println("0. Finalizar");
            opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir a nova linha

            switch (opcao) {
                case 1:
                    listarCursos();
                    System.out.print("Escolha o índice do curso: ");
                    int cursoIndex = scanner.nextInt();
                    scanner.nextLine();  // Consumir a nova linha
                    if (cursoIndex >= 0 && cursoIndex < cursos.size()) {
                        bootcamp.getConteudos().add(cursos.get(cursoIndex));
                    } else {
                        System.out.println("Índice inválido!");
                    }
                    break;
                case 2:
                    listarMentorias();
                    System.out.print("Escolha o índice da mentoria: ");
                    int mentoriaIndex = scanner.nextInt();
                    scanner.nextLine();  // Consumir a nova linha
                    if (mentoriaIndex >= 0 && mentoriaIndex < mentorias.size()) {
                        bootcamp.getConteudos().add(mentorias.get(mentoriaIndex));
                    } else {
                        System.out.println("Índice inválido!");
                    }
                    break;
                case 0:
                    System.out.println("Bootcamp criado com sucesso!\n");
                    bootcamps.add(bootcamp);
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void inscreverDevEmBootcamp() {
        Dev dev = new Dev();
        System.out.print("Nome do Dev: ");
        dev.setNome(scanner.nextLine());

        listarBootcamps();
        System.out.print("Escolha o índice do Bootcamp: ");
        int bootcampIndex = scanner.nextInt();
        scanner.nextLine();  // Consumir a nova linha

        if (bootcampIndex >= 0 && bootcampIndex < bootcamps.size()) {
            dev.inscreverBootcamp(bootcamps.get(bootcampIndex));
            devs.add(dev);
            System.out.println("Dev inscrito com sucesso!\n");
        } else {
            System.out.println("Índice inválido!");
        }
    }

    private static void progredirDev() {
        listarDevs();
        System.out.print("Escolha o índice do Dev: ");
        int devIndex = scanner.nextInt();
        scanner.nextLine();  // Consumir a nova linha

        if (devIndex >= 0 && devIndex < devs.size()) {
            devs.get(devIndex).progredir();
            System.out.println("Progresso realizado com sucesso!\n");
        } else {
            System.out.println("Índice inválido!");
        }
    }

    private static void exibirStatusDevs() {
        for (Dev dev : devs) {
            System.out.println("Dev: " + dev.getNome());
            System.out.println("Conteúdos Inscritos: " + dev.getConteudosInscritos());
            System.out.println("Conteúdos Concluídos: " + dev.getConteudosConcluidos());
            System.out.println("XP: " + dev.calcularTotalXp());
            System.out.println("-------");
        }
    }

    private static void listarCursos() {
        for (int i = 0; i < cursos.size(); i++) {
            System.out.println(i + ": " + cursos);
        }
    }

    private static void listarMentorias() {
        for (int i = 0; i < mentorias.size(); i++) {
            System.out.println(i + ": " + mentorias);
        }
    }

    private static void listarBootcamps() {
        for (int i = 0; i < bootcamps.size(); i++) {
            System.out.println(i + ": " + bootcamps);
        }
    }

    private static void listarDevs() {
        for (int i = 0; i < devs.size(); i++) {
            System.out.println(i + ": " + devs);
        }
    }
}