import java.io.*;
import java.util.*;
//k = 2 (TV)
public class Main {

  public static class HashMap<K, V> {
    private class HMNode {
      K key;
      V value;

      HMNode(K key, V value) {
        this.key = key;
        this.value = value;
      }
    }

    private int size; // n
    private LinkedList<HMNode>[] buckets; // N = buckets.length

    public HashMap() {
      initbuckets(4);
      size = 0;
    }

    private void initbuckets(int N) {
      buckets = new LinkedList[N];
      for (int bi = 0; bi < buckets.length; bi++) {
        buckets[bi] = new LinkedList<>();
      }
    }

    private int findBucketIdx(K key){
      int hc = key.hashCode();      //-infi to +infi
      int bi = Math.abs(hc) % buckets.length;

      return bi;
    }

    public int findDataIdxInBucket(K Keyy, int bi) {
            LinkedList<HMNode> temp = buckets[bi];
            if (temp.size() == 0)
                return -1;
            for (int i = 0; i < temp.size(); i++) {
                if (temp.get(i).key.equals(Keyy))
                    return i;
            }
            return -1;
        }

    private void rehashing()throws Exception{
      
    }

    public void put(K key, V value) throws Exception {
      int bi = findBucketIdx(key);        //bucketIdx
      int di = findDataIdxInBucket(key, bi);  //dataIdx

      if(di == -1){
        //insert
        LinkedList<HMNode> tmp = buckets[bi];
        HMNode node = new HMNode(key,value);
        tmp.add(node);    //addLast
        size++;
      }
      else{
        //update
        LinkedList<HMNode> tmp = buckets[bi];
        HMNode node = tmp.get(di);
        node.value = value;
      }


      double lambda = (size*1.0)/ buckets.length;
      if(lambda > 2){
        rehashing();
      }

    }

    public V get(K key) throws Exception {
      
    }

    public boolean containsKey(K key) {
      
    }

    public V remove(K key) throws Exception {
      
    }

    public ArrayList<K> keyset() throws Exception {
      
    }

    public int size() {
      
    }

   public void display() {
      System.out.println("Display Begins");
      for (int bi = 0; bi < buckets.length; bi++) {
        System.out.print("Bucket" + bi + " ");
        for (HMNode node : buckets[bi]) {
          System.out.print( node.key + "@" + node.value + " ");
        }
        System.out.println(".");
      }
      System.out.println("Display Ends");
  }
}

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    HashMap<String, Integer> map = new HashMap();

    String str = br.readLine();
    while (str.equals("quit") == false) {
      if (str.startsWith("put")) {
        String[] parts = str.split(" ");
        String key = parts[1];
        Integer val = Integer.parseInt(parts[2]);
        map.put(key, val);
      } else if (str.startsWith("get")) {
        String[] parts = str.split(" ");
        String key = parts[1];
        System.out.println(map.get(key));
      } else if (str.startsWith("containsKey")) {
        String[] parts = str.split(" ");
        String key = parts[1];
        System.out.println(map.containsKey(key));
      } else if (str.startsWith("remove")) {
        String[] parts = str.split(" ");
        String key = parts[1];
        System.out.println(map.remove(key));
      } else if (str.startsWith("size")) {
        System.out.println(map.size());
      } else if (str.startsWith("keyset")) {
        System.out.println(map.keyset());
      } else if (str.startsWith("display")) {
        map.display();
      }
      str = br.readLine();
    }
  }
}