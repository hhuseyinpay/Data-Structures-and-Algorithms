package assigment2;

import java.util.Stack;

/**
 * Created by Hasan Hüseyin Pay on 8.12.2016.
 */
public class AugmentAVLTree {


    private AugmentNode root;
    private int sumOfSmaller;
    private int keyOfRoot;

    private class AugmentNode {
        private int key;
        private int balance;
        private int augment;
        private AugmentNode left, right, parent;

        AugmentNode(int k, AugmentNode p) {
            key = k;
            parent = p;
            augment = 0;
        }

        AugmentNode(int k, AugmentNode p, int augment) {
            key = k;
            parent = p;
            this.augment = augment;
        }
    }

    public boolean insert(int key) {

        if (root == null)
            root = new AugmentNode(key, null);
        else {
            AugmentNode n = root;
            AugmentNode parent = null;
            boolean goLeft = false;
            boolean sideRight = root.key < key ? true : false;
            boolean flag;
            while (true) {
                if (n.key == key)
                    return false;

                parent = n;

                goLeft = n.key > key;
                n = goLeft ? n.left : n.right;

                if (n == null) {
                    if (goLeft) {
                        AugmentNode temp = parent;
                        flag = true;
                        while (temp != null) {
                            if (temp.key < key) {
                                parent.left = new AugmentNode(key, parent, temp.key + temp.augment);
                                flag = false;
                                break;
                            }
                            temp = temp.parent;
                        }
                        if (flag)
                            parent.left = new AugmentNode(key, parent);

                    } else {
                        parent.right = new AugmentNode(key, parent, parent.key + parent.augment);

                    }
                    augment2(root, key);
                    rebalance(parent);
                    break;
                }
            }

        }
        //augment();
        return true;
    }

    private void rebalance(AugmentNode n) {
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

    private AugmentNode rotateLeft(AugmentNode a) {

        AugmentNode b = a.right;
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

    private AugmentNode rotateRight(AugmentNode a) {

        AugmentNode b = a.left;
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

    private AugmentNode rotateLeftThenRight(AugmentNode n) {
        n.left = rotateLeft(n.left);
        return rotateRight(n);
    }

    private AugmentNode rotateRightThenLeft(AugmentNode n) {
        n.right = rotateRight(n.right);
        return rotateLeft(n);
    }

    private int height(AugmentNode n) {
        if (n == null)
            return -1;
        return 1 + Math.max(height(n.left), height(n.right));
    }

    private void setBalance(AugmentNode... AVLNodes) {
        for (AugmentNode n : AVLNodes)
            n.balance = height(n.right) - height(n.left);
    }


    public boolean isEmpty() {
        return root == null ? true : false;
    }

    public int getMin() {
        if (isEmpty())
            return -99999;
        AugmentNode temp = root;
        while (temp.left != null)
            temp = temp.left;

        return temp.key;
    }

    public int getMax() {
        if (isEmpty())
            return -9999;
        AugmentNode temp = root;
        while (temp.right != null)
            temp = temp.right;

        return temp.key;
    }

    public int getAllSumAugment() {
        if (isEmpty())
            return -9999;
        AugmentNode temp = root;
        while (temp.right != null)
            temp = temp.right;
        return temp.key + temp.augment;
    }

    public int getSumSmaller(AugmentNode r) {
        sumOfSmaller = 0;
        keyOfRoot = r.key;
        sumSmaller(root);
        return sumOfSmaller;
    }

    private void sumSmaller(AugmentNode r) {
        if (r != null) {
            sumSmaller(r.left);
            if (keyOfRoot > r.key)
                sumOfSmaller += r.key;
            sumSmaller(r.right);
        }
    }

    public void augment() {
        augment(root);
    }

    private void augment(AugmentNode r) {
        if (r != null) {
            augment(r.left);
            r.augment = getSumSmaller(r);
            augment(r.right);
        }
    }

    private void augment2(AugmentNode r, int key) {
        if (r != null) {
            augment(r.right);
            if (key > r.key)
                return;
            r.augment += key;
            augment(r.left);
        }
    }

    private void iterativeAugment(AugmentNode node) {
        Stack<AugmentNode> s = new Stack();
        while (node != null) {
            s.push(node);
            node = node.right;
        }

        while (!s.isEmpty() || node != null)
            if (node != null) {
                s.push(node);
                node = node.left;
            } else {
                node = s.pop();
                //visit(node)
                node = node.right;
            }
    }

    public AugmentNode search(int s) {
        AugmentNode temp = root;
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

    public void inorder() {
        inorder(root);
    }

    private void inorder(AugmentNode r) {
        if (r != null) {
            inorder(r.left);
            System.out.print(r.key + "(" + r.augment + ")    ");
            inorder(r.right);
        }
    }


}
