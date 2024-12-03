import java.util.*;
class Grafo {
    private Map<Cidade, List<Estrada>> adjacencias;

    public Grafo() {
        this.adjacencias = new HashMap<>();
    }

    public void adicionarCidade(String nome) {
        Cidade cidade = new Cidade(nome);
        adjacencias.putIfAbsent(cidade, new ArrayList<>());
    }

    public void adicionarEstrada(String nomeOrigem, String nomeDestino, int distancia, int custo) {
        Cidade origem = encontrarCidade(nomeOrigem);
        Cidade destino = encontrarCidade(nomeDestino);

        if (origem != null && destino != null) {
            adjacencias.get(origem).add(new Estrada(origem, destino, distancia, custo));
            adjacencias.get(destino).add(new Estrada(destino, origem, distancia, custo)); // Bidirecional
        } else {
            System.out.println("Erro: Uma ou ambas as cidades não existem no grafo.");
        }
    }

    private Cidade encontrarCidade(String nome) {
        for (Cidade cidade : adjacencias.keySet()) {
            if (cidade.nome.equals(nome)) {
                return cidade;
            }
        }
        return null;
    }

    public List<Cidade> calcularMenorDistancia(String nomeOrigem, String nomeDestino) {
        Cidade origem = encontrarCidade(nomeOrigem);
        Cidade destino = encontrarCidade(nomeDestino);

        if (origem == null || destino == null) {
            System.out.println("Erro: Cidade de origem ou destino não encontrada.");
            return null;
        }

        Map<Cidade, Integer> distancia = new HashMap<>();
        Map<Cidade, Cidade> caminho = new HashMap<>();
        PriorityQueue<Cidade> fila = new PriorityQueue<>(Comparator.comparingInt(distancia::get));

        for (Cidade cidade : adjacencias.keySet()) {
            distancia.put(cidade, Integer.MAX_VALUE);
        }
        distancia.put(origem, 0);
        fila.add(origem);

        while (!fila.isEmpty()) {
            Cidade atual = fila.poll();

            if (atual.equals(destino)) {
                return construirCaminho(caminho, destino);
            }

            for (Estrada estrada : adjacencias.get(atual)) {
                int novaDistancia = distancia.get(atual) + estrada.distancia;

                if (novaDistancia < distancia.get(estrada.destino)) {
                    distancia.put(estrada.destino, novaDistancia);
                    caminho.put(estrada.destino, atual);
                    fila.add(estrada.destino);
                }
            }
        }
        return null; // Sem rota
    }

    private List<Cidade> construirCaminho(Map<Cidade, Cidade> caminho, Cidade destino) {
        LinkedList<Cidade> rota = new LinkedList<>();
        Cidade atual = destino;

        while (atual != null) {
            rota.addFirst(atual);
            atual = caminho.get(atual);
        }
        return rota;
    }

    public void exibirCidades() {
        System.out.println("Cidades cadastradas:");
        for (Cidade cidade : adjacencias.keySet()) {
            System.out.println("- " + cidade);
        }
    }

    public void exibirEstradas() {
        System.out.println("Estradas cadastradas:");
        for (Cidade cidade : adjacencias.keySet()) {
            for (Estrada estrada : adjacencias.get(cidade)) {
                System.out.println(estrada.origem + " -> " + estrada.destino +
                        " (Distância: " + estrada.distancia + " km, Custo: " + estrada.custo + ")");
            }
        }
    }
}