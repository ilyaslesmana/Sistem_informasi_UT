package Struktur_data.Tugas.Tugas_3;

import java.util.Stack;
import java.util.Queue;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class T3_searching {

    static class Graph {
        int jumlahNode;
        List<List<Integer>> adjencyList;

        public Graph(int node) {
            this.jumlahNode = node;
            adjencyList = new ArrayList<>();
            for (int i = 0; i < node; i++) {
                adjencyList.add(new ArrayList<>());
            }
        }

        public void buatEdge(int parent, int child) {
            adjencyList.get(parent).add(child);
        }
    }

    public static void main(String[] args) {

        int totalNode = 10;
        Graph graf = new Graph(totalNode);

        graf.buatEdge(0, 1);
        graf.buatEdge(0, 2);
        graf.buatEdge(1, 3);
        graf.buatEdge(1, 4);
        graf.buatEdge(2, 5);
        graf.buatEdge(2, 6);
        graf.buatEdge(3, 7);
        graf.buatEdge(4, 8);
        graf.buatEdge(5, 9);

        int starNode = 0;
        int target = 8;

        System.out.println("=======================================================");
        System.out.println("1. Algoritma DFS ");
        System.out.println("=======================================================");
        algoritmaDFS(graf, starNode, target);

        System.out.println("=======================================================");
        System.out.println("2. Algoritma BFS ");
        System.out.println("=======================================================");
        algoritmaBFS(graf, starNode, target);
    }

    public static void algoritmaDFS(Graph graf, int star, int target) {

        boolean[] visited = new boolean[graf.jumlahNode];

        Stack<Integer> stacklifo = new Stack<>();

        stacklifo.push(star);
        System.out.println("\n-> PUSH node a" + star + " kedalam stacllifo");
        while (!stacklifo.isEmpty()) {
            int nodeSekarang = stacklifo.pop();
            System.out.println("-> POP node a" + nodeSekarang + " dari atas stack");
            System.out.println("isi Stack sekarang " + stacklifo);
            if (!visited[nodeSekarang]) {
                visited[nodeSekarang] = true;
                System.out.println("[DFS] Scaning node a" + nodeSekarang);
                if (nodeSekarang == target) {
                    System.out.println(">>> Data di temukan node a" + nodeSekarang + " dengan algoritma DFS");
                    return;

                }
                List<Integer> child = graf.adjencyList.get(nodeSekarang);
                for (int i = child.size() - 1; i >= 0; i--) {
                    int nodeChild = child.get(i);
                    if (!visited[nodeChild]) {
                        stacklifo.push(nodeChild);
                        System.out.println("-------------> Push node child " + nodeChild + " kedalam Stack");
                    }
                }
            }

        }
    }

    public static void algoritmaBFS(Graph graf, int star, int target) {
        boolean[] visited = new boolean[graf.jumlahNode];

        Queue<Integer> queueFifo = new LinkedList<>();
        queueFifo.add(star);
        visited[star] = true;

        while (!queueFifo.isEmpty()) {
            int nodeSekarang = queueFifo.remove();
            System.out.println("\n-> REMOVE node a" + nodeSekarang + " dari depan Antrean");
            System.out.println("[BFS] Scaning node a" + nodeSekarang);

            if (nodeSekarang == target) {
                System.out.println(">>> DATA di temukan node a" + nodeSekarang + " dengan metode BFS");
                return;

            }
            for (int nodeChild : graf.adjencyList.get(nodeSekarang)) {
                if (!visited[nodeChild]) {
                    visited[nodeChild] = true;
                    queueFifo.add(nodeChild);
                    System.out.println(" ----------> ADD node a" + nodeChild + "kedalam antrean");

                }
            }

        }
    }

}
