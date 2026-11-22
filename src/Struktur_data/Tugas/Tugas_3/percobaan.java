package Struktur_data.Tugas.Tugas_3;

import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.List;

public class percobaan {

    static class Graph {

        int jumlahNode;
        List<List<Integer>> adjencyList;

        public Graph(int node) {
            this.jumlahNode = node;
            adjencyList = new ArrayList<>(node);

            for (int i = 0; i < node; i++) {
                adjencyList.add(new ArrayList<>());
            }
        }

        public void buatEdge(int parent, int child) {
            adjencyList.get(parent).add(child);
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
            int targetNode = 8;

            System.out.println("======================================================");
            System.out.println("1. Algoritma DFS [LIFO]");
            System.out.println("======================================================");
            algoritmaDFS(graf, starNode, targetNode);
            System.out.println("======================================================");
            System.out.println("2. Algoritma BFS [FIFO]");
            System.out.println("======================================================");
            algoritmaBFS(graf, starNode, targetNode);
        }

    }

    public static void algoritmaDFS(Graph graf, int star, int target) {

        boolean[] visited = new boolean[graf.jumlahNode];

        Stack<Integer> stacklifo = new Stack<>();
        stacklifo.push(star);
        System.out.println("\n -> PUSH root a " + star + " kedalam Stack");

        while (!stacklifo.isEmpty()) {
            int nodeSekarang = stacklifo.pop();
            System.out.println("\n-> POP node a " + nodeSekarang + "dari atas stack");
            System.out.println("-> isi stack sekarang " + stacklifo);

            if (!visited[nodeSekarang]) {
                visited[nodeSekarang] = true;
                System.err.println(" \n DFS [Scaning]" + nodeSekarang);
                if (nodeSekarang == target) {
                    System.err.println(">>> data ditemukan <<<" + target + " menggunakan metode DFS");
                    return;

                }
                List<Integer> child = graf.adjencyList.get(nodeSekarang);

                for (int i = child.size() - 1; i >= 0; i--) {

                    int nodechild = child.get(i);
                    if (!visited[nodechild]) {
                        stacklifo.push(nodechild);
                        System.err.println("---------Push node Child :" + nodechild + "kedalam Stack");
                    }
                }
            }
        }
    }

    public static void algoritmaBFS(Graph graf, int start, int target) {
        boolean[] visited = new boolean[graf.jumlahNode];

        Queue<Integer> queueFifo = new LinkedList<>();

        queueFifo.add(start);
        visited[start] = true;
        while (!queueFifo.isEmpty()) {
            int nodeSekarang = queueFifo.remove();
            System.out.println("\n -> REMOVE node a" + nodeSekarang + "dari depan antrean");
            System.out.println("\n-> [BFS} Scaning node a" + nodeSekarang);

            if (nodeSekarang == target) {
                System.out.println(">>> Data di temukan node a" + nodeSekarang);
                return;
            }
            for (int nodechild : graf.adjencyList.get(nodeSekarang)) {
                if (!visited[nodechild]) {
                    visited[nodechild] = true;
                    queueFifo.add(nodechild);
                    System.out.println("----> ADD node a" + nodechild + "kedalam stack");

                }
            }

        }
    }
}