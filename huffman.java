import java.util.*;
import static java.lang.System.out;
import java.util.Scanner;

public class huffman {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = 1;
        int input;
        Node[] node;
        node = new Node[33];
        for (int i = 0; i < 33; i++)
        {
            node[i] = new Node();
            node[i].value = 999999;
        }
        System.out.println("insert input,% to end");
        while (true) {

            input = scanner.nextInt();
            if (input == -999) {
                break;
            } else {
                node[n].value = input;
                n++;
            }
        }
        int p = n;
        Node root = new Node();
        heap Heap = new heap();
        Heap.initial(node);
        for (int i = 1; i < p - 1; i++) {
            Node insertNode = new Node();
            Node node1, node2;
            node1 = node[1];
            Heap.exMax(node, n);
            node2 = node[1];
            Heap.exMax(node, n);
            insertNode.value = node1.value + node2.value;
            insertNode.right = node2;
            insertNode.left = node1;
            node[n] = insertNode;
            Heap.initial(node);
            n++;
            if (i == p - 2) {
                root = insertNode;
            }
        }
        Node ptr = root;
        ptr.travel(ptr,"");
    }

}

class Node {
    int value;
    String code;
    Node left;
    Node right;

    public void travel(Node root,String code) {
        Node p=root;
        if(p != null) {
           
            travel(root.left,code+'0');
            travel(root.right,code+'1');
            if(p.left== null && p.right==null)
            System.out.println(root.value + "code : " + code);
        }
    }
}

class heap {

    public void initial(Node[] input) {
        int n = input.length / 2 - 1;
        for (int i = n; i >= 1; i--) {
            adjust(input, i, (n + 1) * 2);
        }

    }

    public void adjust(Node[] input, int pos, int n) {
        Node temp = input[pos];
        int j = 2 * pos;
        while (j <= n) {
            if (j < n)
                if (input[j].value > input[j + 1].value)
                    j = j + 1;
            if (temp.value <= input[j].value)
                break;
            else {
                input[j / 2] = input[j];
                j = 2 * j;
            }
            input[j / 2] = temp;
        }
    }

    public void exMax(Node[] input, int n) {
        // System.out.println(input[1].value);
        input[1] = input[n];
        n--;
        adjust(input, 1, n);
    }
}
