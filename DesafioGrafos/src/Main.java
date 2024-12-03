import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Grafo grafo = new Grafo();

        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Adicionar cidade");
            System.out.println("2. Adicionar estrada");
            System.out.println("3. Calcular menor distância");
            System.out.println("4. Exibir cidades e estradas");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome da cidade: ");
                    String nomeCidade = scanner.nextLine();
                    grafo.adicionarCidade(nomeCidade);
                    System.out.println("Cidade adicionada com sucesso!");
                    break;

                case 2:
                    System.out.print("Digite o nome da cidade de origem: ");
                    String origem = scanner.nextLine();
                    System.out.print("Digite o nome da cidade de destino: ");
                    String destino = scanner.nextLine();
                    System.out.print("Digite a distância da estrada (em km): ");
                    int distancia = scanner.nextInt();
                    System.out.print("Digite o custo da estrada: ");
                    int custo = scanner.nextInt();
                    scanner.nextLine(); // Limpar buffer
                    grafo.adicionarEstrada(origem, destino, distancia, custo);
                    System.out.println("Estrada adicionada com sucesso!");
                    break;

                case 3:
                    System.out.print("Digite a cidade de origem: ");
                    String cidadeOrigem = scanner.nextLine();
                    System.out.print("Digite a cidade de destino: ");
                    String cidadeDestino = scanner.nextLine();
                    List<Cidade> rota = grafo.calcularMenorDistancia(cidadeOrigem, cidadeDestino);

                    if (rota != null) {
                        System.out.println("Menor distância entre " + cidadeOrigem + " e " + cidadeDestino + ": " + rota);
                    } else {
                        System.out.println("Não foi possível encontrar uma rota entre as cidades especificadas.");
                    }
                    break;

                case 4:
                    grafo.exibirCidades();
                    grafo.exibirEstradas();
                    break;

                case 5:
                    System.out.println("Saindo do programa...");
                    return;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }
}