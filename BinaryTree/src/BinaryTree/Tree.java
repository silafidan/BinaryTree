
package BinaryTree;

import java.util.Stack;

public class Tree {
    private Node root;

    public Tree() {
        root = null;
    }

    public void insert(long data) {
        Node newNode = new Node();
        //veriyi ekleme
        newNode.data = data;

        //ağaç boşsa
        if (root == null) {
            root = newNode;
        }
        //root  doluysa
        else {
            //roottan başlanır
            Node current = root;
            Node parent;
            while (true) {
                //parent->şu an dikkate alınan konum
                parent = current;
                //değer kökten sol alt ağaçta
                if (data < current.data) {
                    //current düğümünün sol alt ağacına geçilir
                    current = current.leftChild;
                    //sol alt ağac boş mu-> sol çocuk yok
                    //yer var->yeni düğüm burada yer almalıdır
                    if (current == null) {
                        //sola ekle
                        parent.leftChild = newNode;
                        return;
                    }
                }//end if
                //değer kökten büyükse veya eşitse sağ alt ağaçta
                else {
                    //current düğümünün sağ alt ağacına geçilir
                    current = current.rightChild;
                    //sağ alt ağaç boş mu-> sağ çocuk yok
                    //yer var->yeni düğüm burada yer almalıdır

                    if (current == null) {
                        //sağa ekle
                        parent.rightChild = newNode;
                        return;
                    }
                }//end else

            }//end while

        }//end else
    }//end insert

    public Node find(long data) {
        //root ile başlanır
        Node current = root;
        while (current.data != data) {
            if (data < current.data) {
                current = current.leftChild;
            } else {
                current = current.rightChild;
            }
            //çocuk düğümler yoksa yani boşsa
            if (current == null) {
                //sayı bulunamadı
                return null;
            }
        }
        //sayı bulunduysa
        return current;
    }

    public void traverse(int traverseType) {
        switch (traverseType) {
            case 1: {
                System.out.println("\n Preorder:");
                preOrder(root);
            }
            case 2: {
                System.out.println("\n InOrder:");
                inOrder(root);
            }
            case 3: {
                System.out.println("\n PostOrder");
                postOrder(root);
            }
        }
        System.out.println("");
    }

    public void preOrder(Node localRoot) {
        if (localRoot != null) {
            System.out.println(localRoot.data + " ");
            preOrder(localRoot.leftChild);
            preOrder(localRoot.rightChild);
        }
    }

    public void inOrder(Node localRoot) {
        if (localRoot != null) {
            //önce sol alt
            inOrder(localRoot.leftChild);
            //sonra kök
            System.out.println(localRoot.data + " ");
            //en son sağ alt
            inOrder(localRoot.rightChild);
        }
    }

    public void postOrder(Node localRoot) {
        if (localRoot != null) {
            //önce sol alt
            postOrder(localRoot.leftChild);
            //sonra sağ alt
            postOrder(localRoot.rightChild);
            //en son kök
            System.out.println(localRoot.data + " ");
        }
    }

    public Node minimum() {
        Node current = root;
        Node last = null;
        while (current != null) {
            //son ziyaret edilen düğümü sakla
            last = current;
            //sol çocuğa geç
            current = current.leftChild;
        }
        //en sol düğümdeyiz
        //bu düğüm ağacın en küçük elemanı
        return last;
    }

    public boolean delete(long key) {
        Node current = root;
        Node parent = root;

        boolean isLeftChild = true;

        //silinecek düğümün konumu bulunuyor
        while (current.data != key) {
            //silinecek düğümün parent'i
            parent = current;
            //aranan değer current.data dan küçük mü
            if (key < current.data) {
                //silinecek sol çocuk
                isLeftChild = true;
                current = current.leftChild;
            }
            //aranan değer current.data dan büyük mü
            else {
                isLeftChild = true;
                current = current.rightChild;
            }
            if (current == null) {
                return false;
            }
        }//end while
        //silincek düğüm bulundu
        if (current.leftChild == null && current.rightChild == null) {
            if (current == root) {
                root = null;
            } else if (isLeftChild) {
                parent.leftChild = null;
            } else {
                parent.rightChild = null;
            }
        } else if (current.rightChild == null) {
            if (current == root) {
                root = current.leftChild;
            } else if (isLeftChild) {
                parent.leftChild = current.leftChild;
            } else {
                parent.rightChild = current.leftChild;
            }
        } else if (current.leftChild == null) {
            if (current == root) {
                root = current.rightChild;
            } else if (isLeftChild) {
                parent.leftChild = current.rightChild;
            } else {
                parent.rightChild = current.rightChild;
            }
        }
        else{
            Node successor=getSuccessor(current);
            if (current==root){
                root=successor;
            }
            else if (isLeftChild){
                parent.leftChild=successor;
            }
            else {
                parent.rightChild=successor;

            }
            successor.leftChild=current.leftChild;
        }
        return true;

    } private Node getSuccessor(Node delNode){
        Node successorParent=delNode;
        Node successor=delNode;
        Node current=delNode.rightChild;

        while (current !=null){
            successorParent=successor;
            successor=current;
            current=current.leftChild;
        }
        if (successor !=delNode.rightChild){
            successorParent.leftChild=successor.rightChild;
            successor.rightChild=delNode.rightChild;
        }
        return successor;
    }
    public void displayTree() {
        Stack<Node> globalStack = new Stack<Node>();
        globalStack.push(root);
        int nBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println("........................................................................");

        while (isRowEmpty) {
            Stack<Node> localStack = new Stack<Node>();
            isRowEmpty = true;

            for (int j = 0; j < nBlanks; j++) {
                System.out.print(' ');

            }
            while (!globalStack.isEmpty()) {
                Node temp = (Node) globalStack.pop();
                if (temp != null) {
                    System.out.println(temp.data);

                    localStack.push(temp.leftChild);
                    localStack.push(temp.rightChild);


                    if (temp.leftChild != null || temp.rightChild != null) {
                        isRowEmpty = false;
                    }
                } else {
                    System.out.println("__");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < nBlanks * 2 - 2; j++) {
                    System.out.println(' ');

                }
            }
            System.out.println();
            nBlanks /= 2;
            while (!localStack.isEmpty()) {
                globalStack.push(localStack.pop());
            }
        }
        System.out.println("............................................................................");
    }
}
