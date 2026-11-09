package gocepat;

public class Graph {
    private final int numVertices;
    private final DynamicIntArray[] adjList;
    private final String[] nodeNames;

    public Graph(int numVertices) {
        this.numVertices = numVertices;
        this.adjList = new DynamicIntArray[numVertices];
        this.nodeNames = new String[numVertices];

        for (int i = 0; i < numVertices; i++) {
            this.adjList[i] = new DynamicIntArray(4);
            this.nodeNames[i] = "Simpul " + i;
        }
    }

    public void setNodeName(int id, String name) {
        if (id >= 0 && id < numVertices) {
            nodeNames[id] = name;
        }
    }

    public String getNodeName(int id) {
        if (id >= 0 && id < numVertices) {
            return nodeNames[id];
        }
        return "Unknown-" + id;
    }

    public void addEdge(int u, int v, boolean bidirectional) {
        if (u < 0 || u >= numVertices || v < 0 || v >= numVertices) {
            throw new IllegalArgumentException("ID Simpul di luar jangkauan!");
        }
        adjList[u].add(v);
        if (bidirectional) {
            adjList[v].add(u);
        }
    }

    public int getNumVertices() {
        return numVertices;
    }

    public DynamicIntArray getNeighbors(int u) {
        return adjList[u];
    }

    /**
     * Method bfsCariTerdekat(int asal, boolean[] adalahDriver)
     * Menelusuri graf secara BFS untuk mencari driver terdekat (hop minimum)
     * dan merekonstruksi rute penjemputan dari titik asal.
     */
    public HasilPencarian bfsCariTerdekat(int asal, boolean[] adalahDriver) {
        if (asal < 0 || asal >= numVertices) {
            return new HasilPencarian(-1, -1, new int[0], "Titik asal tidak valid");
        }

        boolean[] visited = new boolean[numVertices];
        int[] dist = new int[numVertices];
        int[] parent = new int[numVertices];

        for (int i = 0; i < numVertices; i++) {
            dist[i] = -1;
            parent[i] = -1;
        }

        ArrayQueue queue = new ArrayQueue(numVertices);

        visited[asal] = true;
        dist[asal] = 0;
        queue.enqueue(asal);

        if (adalahDriver != null && asal < adalahDriver.length && adalahDriver[asal]) {
            return new HasilPencarian(asal, 0, new int[] { asal }, "Driver berada tepat di titik penjemputan");
        }

        int foundDriverNode = -1;

        while (!queue.isEmpty()) {
            int curr = queue.dequeue();

            DynamicIntArray neighbors = adjList[curr];
            int nSize = neighbors.size();

            for (int i = 0; i < nSize; i++) {
                int neighbor = neighbors.get(i);

                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    dist[neighbor] = dist[curr] + 1;
                    parent[neighbor] = curr;

                    if (adalahDriver != null && neighbor < adalahDriver.length && adalahDriver[neighbor]) {
                        foundDriverNode = neighbor;
                        break;
                    }

                    queue.enqueue(neighbor);
                }
            }

            if (foundDriverNode != -1) {
                break;
            }
        }

        if (foundDriverNode == -1) {
            return new HasilPencarian(-1, -1, new int[0], "Tidak ada driver yang terjangkau");
        }

        // Rekonstruksi rute penjemputan dari titik asal ke driver
        int hopCount = dist[foundDriverNode];
        int[] path = new int[hopCount + 1];
        int currTrace = foundDriverNode;
        for (int p = hopCount; p >= 0; p--) {
            path[p] = currTrace;
            currTrace = parent[currTrace];
        }

        return new HasilPencarian(foundDriverNode, hopCount, path, "Sukses");
    }
}
