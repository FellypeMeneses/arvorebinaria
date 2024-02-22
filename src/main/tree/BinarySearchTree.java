package tree;

import estrut.Tree;

public class BinarySearchTree implements Tree {
    private TreeNode root;

    @Override
    public boolean buscaElemento(int valor) {
        return buscaElementoRecursivo(root, valor);
    }

    private boolean buscaElementoRecursivo(TreeNode node, int valor) {
        if (node == null) {
            return false;
        }

        if (valor == node.data) {
            return true;
        } else if (valor < node.data) {
            return buscaElementoRecursivo(node.left, valor);
        } else {
            return buscaElementoRecursivo(node.right, valor);
        }
    }

    @Override
    public int minimo() {
        if (root == null) {
            throw new NullPointerException("Árvore vazia");
        }

        TreeNode current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.data;
    }

    @Override
    public int maximo() {
        if (root == null) {
            throw new NullPointerException("Árvore vazia");
        }

        TreeNode current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.data;
    }

    @Override
    public void insereElemento(int valor) {
        root = inserirRecursivo(root, valor);
    }

    private TreeNode inserirRecursivo(TreeNode node, int valor) {
        if (node == null) {
            return new TreeNode(valor);
        }

        if (valor < node.data) {
            node.left = inserirRecursivo(node.left, valor);
        } else if (valor > node.data) {
            node.right = inserirRecursivo(node.right, valor);
        }

        return node;
    }

    @Override
    public void remove(int valor) {
        root = removerRecursivo(root, valor);
    }

    private TreeNode removerRecursivo(TreeNode node, int valor) {
        if (node == null) {
            return node;
        }

        if (valor < node.data) {
            node.left = removerRecursivo(node.left, valor);
        } else if (valor > node.data) {
            node.right = removerRecursivo(node.right, valor);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            node.data = minValue(node.right);
            node.right = removerRecursivo(node.right, node.data);
        }

        return node;
    }

    private int minValue(TreeNode node) {
        int minValor = node.data;
        while (node.left != null) {
            minValor = node.left.data;
            node = node.left;
        }
        return minValor;
    }

    @Override
    public int[] preOrdem() {
        return preOrdemRecursivo(root);
    }

    private int[] preOrdemRecursivo(TreeNode node) {
        if (node == null) {
            return new int[] {};
        }

        int[] leftArray = preOrdemRecursivo(node.left);
        int[] rightArray = preOrdemRecursivo(node.right);
        int[] result = new int[leftArray.length + rightArray.length + 1];

        result[0] = node.data;
        System.arraycopy(leftArray, 0, result, 1, leftArray.length);
        System.arraycopy(rightArray, 0, result, leftArray.length + 1, rightArray.length);

        return result;
    }

    @Override
    public int[] emOrdem() {
        return emOrdemRecursivo(root);
    }

    private int[] emOrdemRecursivo(TreeNode node) {
        if (node == null) {
            return new int[] {};
        }

        int[] leftArray = emOrdemRecursivo(node.left);
        int[] rightArray = emOrdemRecursivo(node.right);
        int[] result = new int[leftArray.length + rightArray.length + 1];

        System.arraycopy(leftArray, 0, result, 0, leftArray.length);
        result[leftArray.length] = node.data;
        System.arraycopy(rightArray, 0, result, leftArray.length + 1, rightArray.length);

        return result;
    }

    @Override
    public int[] posOrdem() {
        return posOrdemRecursivo(root);
    }

    private int[] posOrdemRecursivo(TreeNode node) {
        if (node == null) {
            return new int[] {};
        }

        int[] leftArray = posOrdemRecursivo(node.left);
        int[] rightArray = posOrdemRecursivo(node.right);
        int[] result = new int[leftArray.length + rightArray.length + 1];

        System.arraycopy(leftArray, 0, result, 0, leftArray.length);
        System.arraycopy(rightArray, 0, result, leftArray.length, rightArray.length);
        result[leftArray.length + rightArray.length] = node.data;

        return result;
    }
}

class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;

    public TreeNode(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}
