package ds;

public class SingleLinkedList<T> implements List<T> {
    /**
     * ZERO-BASED INDEXING
     */
    private Node head;
    private Node tail;

    @Override
    public T getItem(int i) {
        int pos = 0;
        Node cur = head;

        while (cur != null) {
            if (pos == i) {
                return (T) cur._item;
            }
            pos++;
            cur = cur._next;
        }
        return null;
    }

    public Node<T> getNode(int i) {
        int pos = 0;
        Node cur = head;

        while (cur != null) {
            if (pos == i) {
                return cur;
            }
            pos++;
            cur = cur._next;
        }
        return null;
    }

    @Override
    public boolean addItem(T item, int i) {
        Node node = new Node(item, null);
        if (i == 0) {
            return addHead(node);
        }
        Node cur = head._next;
        Node prev = head;
        int pos = 1;

        while (cur != null) {
            if (pos == i) {
                prev._next = node;
                node._next = cur;
                return true;
            }
            pos++;
            prev = cur;
            cur = cur._next;
        }
        return false;
    }

    @Override
    public boolean addItem(T item) {
        Node node = new Node(item, null);
        if (head == null) {
            return addHead(node);
        }
        Node cur = head;
        while (cur._next != null)
            cur = cur._next;
        cur._next = node;
        return true;
    }

    @Override
    public T removeItem(int i) {
        // going to assume zero-based indexing
        if (i == 0) {
            return removeHead();
        }
        Node cur = head._next;
        Node prev = head;
        int pos = 1;

        while (cur != null) {
            if (pos == i) {
                prev._next = cur._next;
                return (T) cur._item;
            }
            prev = cur;
            cur = cur._next;
            pos++;
        }
        return null;
    }

    private T removeHead() {
        Node cur = head;
        head = cur._next;
        return (T) cur._item;
    }

    private boolean addHead(Node node) {
        node._next = head;
        head = node;
        return true;
    }

    @Override
    public int length() {
        Node cur = head;
        int length = 0;

        while (cur != null) {
            length++;
            cur = cur._next;
        }
        return length;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public int find(T item) {
        Node cur = head;
        int pos = 0;

        while (cur != null) {
            if (item.equals(cur._item))
                return pos;
            pos++;
            cur = cur._next;
        }
        return -1;
    }

    @Override
    public boolean contains(T item) {
        int itemIndex = find(item);
        return itemIndex > -1;
    }

    protected class Node<T> {
        protected T _item;
        protected Node<T> _next;

        private Node(T item, Node<T> next) {
            _item = item;
            _next = next;
        }

        public String toString() {
            return String.format("[%s]", (_item == null ? "[]" : _item.toString()));
        }
    }

    public String toString() {
        String stringRep = "[ ";

        Node cur = head;
        int pos = 0;
        while (cur != null) {
            stringRep = stringRep + "[i=" + pos + " | item=" + cur._item + "] ==> ";
            pos++;
            cur = cur._next;
        }
        stringRep = stringRep + " ]";
        return stringRep;
    }

    public static void main(String[] args) {
        SingleLinkedList<String> sll = new SingleLinkedList<>();
        System.out.println(sll);
        sll.addItem("Hello");
        sll.addItem("World");
        System.out.println(sll);
        sll.addItem("Cruel", 1);
        System.out.println(sll);
        sll.addItem("Beautiful", 2);
        sll.removeItem(1);
        System.out.println(sll);

    }

}
