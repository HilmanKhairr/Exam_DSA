package gocepat;

public class GoCepatBfsSystem {

    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("        SIMULASI SISTEM DISPATCH TRANSPORTASI ONLINE GOCEPAT (BFS GRAPH)       ");
        System.out.println("================================================================================\n");

        int totalSimpul = 10;
        Graph graph = new Graph(totalSimpul);

        for (int i = 0; i < totalSimpul; i++) {
            graph.setNodeName(i, "Simpul " + i);
        }

        graph.addEdge(0, 1, true);
        graph.addEdge(0, 2, true);
        graph.addEdge(1, 3, true);
        graph.addEdge(2, 3, true);
        graph.addEdge(2, 4, true);
        graph.addEdge(3, 6, true);
        graph.addEdge(4, 5, true);
        graph.addEdge(4, 7, true);
        graph.addEdge(5, 7, true);
        graph.addEdge(6, 8, true);
        graph.addEdge(7, 8, true);
        graph.addEdge(8, 9, true);

        System.out.println("[1] TOPOLOGI GRAF (ADJACENCY LIST)");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println(String.format(" %-4s %-16s %s", "ID", "Nama Simpul", "Koneksi / Tetangga"));
        System.out.println("--------------------------------------------------------------------------------");
        for (int i = 0; i < totalSimpul; i++) {
            StringBuilder sb = new StringBuilder();
            DynamicIntArray n = graph.getNeighbors(i);
            for (int j = 0; j < n.size(); j++) {
                sb.append(n.get(j));
                if (j < n.size() - 1) {
                    sb.append(", ");
                }
            }
            System.out.println(
                    String.format(" [%d]  %-16s -> Simpul %s", i, graph.getNodeName(i), sb.toString()));
        }
        System.out.println("--------------------------------------------------------------------------------\n");

        boolean[] adalahDriver = new boolean[totalSimpul];
        adalahDriver[3] = true; // Driver 1: Simpul 3
        adalahDriver[5] = true; // Driver 2: Simpul 5
        adalahDriver[8] = true; // Driver 3: Simpul 8

        System.out.println("[2] POSISI DRIVER AKTIF DI JALAN");
        System.out.println("--------------------------------------------------------------------------------");
        int driverCount = 1;
        for (int i = 0; i < totalSimpul; i++) {
            if (adalahDriver[i]) {
                System.out.println(String.format("  * Driver %d : %s", driverCount++,
                        graph.getNodeName(i)));
            }
        }
        System.out.println("--------------------------------------------------------------------------------\n");
        System.out.println("[3] HASIL PENGUJIAN PENUGASAN DRIVER TERDEKAT (BFS)");
        System.out.println("================================================================================");
        System.out.println("KASUS 1: Penumpang memesan dari " + graph.getNodeName(0));
        System.out.println("--------------------------------------------------------------------------------");
        HasilPencarian hasil1 = graph.bfsCariTerdekat(0, adalahDriver);
        System.out.println("  * Driver Ditemukan      : " + graph.getNodeName(hasil1.driverNode));
        System.out.println("  * Jarak Minimum         : " + hasil1.hopDistance + " hop");
        System.out.print("  * Rute Penjemputan      : ");
        for (int i = 0; i < hasil1.path.length; i++) {
            int node = hasil1.path[i];
            System.out.print(graph.getNodeName(node) + (i < hasil1.path.length - 1 ? " -> " : ""));
        }
        System.out.println();

        System.out.println("--------------------------------------------------------------------------------\n");
        System.out.println("KASUS 2: Penumpang memesan dari " + graph.getNodeName(7));
        System.out.println("--------------------------------------------------------------------------------");
        HasilPencarian hasil2 = graph.bfsCariTerdekat(7, adalahDriver);

        System.out.println("  * Driver Ditemukan      : " + graph.getNodeName(hasil2.driverNode));
        System.out.println("  * Jarak Minimum         : " + hasil2.hopDistance + " hop");
        System.out.print("  * Rute Penjemputan      : ");
        for (int i = 0; i < hasil2.path.length; i++) {
            int node = hasil2.path[i];
            System.out.print(graph.getNodeName(node) + (i < hasil2.path.length - 1 ? " -> " : ""));
        }
        System.out.println();

        System.out.println("================================================================================");
    }
}
