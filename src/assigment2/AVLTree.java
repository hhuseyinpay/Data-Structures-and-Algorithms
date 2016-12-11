package assigment2;

/**
 * Created by Hasan Hüseyin Pay on 4.12.2016.
 */
public class AVLTree {

    private AVLNode root;
    private int sumOfSmaller;
    private int keyOfRoot;

    private class AVLNode {
        private int key;
        private int balance;
        private AVLNode left, right, parent;

        AVLNode(int k, AVLNode p) {
            key = k;
            parent = p;
        }
    }

    public boolean insert(int key) {
        if (root == null)
            root = new AVLNode(key, null);
        else {
            AVLNode n = root;
            AVLNode parent;
            while (true) {
                if (n.key == key)
                    return false;

                parent = n;

                boolean goLeft = n.key > key;
                n = goLeft ? n.left : n.right;

                if (n == null) {
                    if (goLeft) {
                        parent.left = new AVLNode(key, parent);
                    } else {
                        parent.right = new AVLNode(key, parent);
                    }
                    rebalance(parent);
                    break;
                }
            }
        }
        return true;
    }

    private void rebalance(AVLNode n) {
        setBalance(n);

        if (n.balance == -2) {
            if (height(n.left.left) >= height(n.left.right))
                n = rotateRight(n);
            else
                n = rotateLeftThenRight(n);

        } else if (n.balance == 2) {
            if (height(n.right.right) >= height(n.right.left))
                n = rotateLeft(n);
            else
                n = rotateRightThenLeft(n);
        }

        if (n.parent != null) {
            rebalance(n.parent);
        } else {
            root = n;
        }
    }

    private AVLNode rotateLeft(AVLNode a) {

        AVLNode b = a.right;
        b.parent = a.parent;

        a.right = b.left;

        if (a.right != null)
            a.right.parent = a;

        b.left = a;
        a.parent = b;

        if (b.parent != null) {
            if (b.parent.right == a) {
                b.parent.right = b;
            } else {
                b.parent.left = b;
            }
        }
        setBalance(a, b);
        return b;
    }

    private AVLNode rotateRight(AVLNode a) {

        AVLNode b = a.left;
        b.parent = a.parent;

        a.left = b.right;

        if (a.left != null)
            a.left.parent = a;

        b.right = a;
        a.parent = b;

        if (b.parent != null) {
            if (b.parent.right == a) {
                b.parent.right = b;
            } else {
                b.parent.left = b;
            }
        }

        setBalance(a, b);

        return b;
    }

    private AVLNode rotateLeftThenRight(AVLNode n) {
        n.left = rotateLeft(n.left);
        return rotateRight(n);
    }

    private AVLNode rotateRightThenLeft(AVLNode n) {
        n.right = rotateRight(n.right);
        return rotateLeft(n);
    }

    private int height(AVLNode n) {
        if (n == null)
            return -1;
        return 1 + Math.max(height(n.left), height(n.right));
    }

    private void setBalance(AVLNode... AVLNodes) {
        for (AVLNode n : AVLNodes)
            n.balance = height(n.right) - height(n.left);
    }

    public boolean isEmpty() {
        return root == null ? true : false;
    }

    public int getMin() {
        if (isEmpty())
            return -99999;
        AVLNode temp = root;
        while (temp.left != null)
            temp = temp.left;

        return temp.key;
    }

    public int getMax() {
        if (isEmpty())
            return -9999;
        AVLNode temp = root;
        while (temp.right != null)
            temp = temp.right;

        return temp.key;
    }

    public int getAllSum() {
        if (isEmpty())
            return -9999;
        AVLNode temp = root;
        while (temp.right != null)
            temp = temp.right;
        return temp.key + getSumSmaller(temp);

    }

    public void inorder() {
        inorder(root);
    }

    private void inorder(AVLNode r) {
        if (r != null) {
            inorder(r.left);
            System.out.print(r.key + " ");
            inorder(r.right);
        }
    }

    public int getSumSmaller(AVLNode r) {
        sumOfSmaller = 0;
        keyOfRoot = r.key;
        sumSmaller(root);
        return sumOfSmaller;
    }

    private void sumSmaller(AVLNode r) {
        if (r != null) {
            sumSmaller(r.left);
            if (keyOfRoot > r.key)
                sumOfSmaller += r.key;
            sumSmaller(r.right);
        }
    }

    public AVLNode search(int s) {
        AVLNode temp = root;
        while (temp != null) {
            if (temp.key == s) {
                return temp;
            } else if (temp.key > s) {
                temp = temp.left;
            } else {
                temp = temp.right;
            }
        }
        return null;
    }
}
