import java.util.List;
import java.util.ArrayList;

public class ListBinaryTree<E> {
    protected List<E> list;

    public ListBinaryTree() {
        list = new ArrayList<>();
    }

    public void add(E data) {
        list.add(data);
    }

    public void addAll(List<E> data) {
        list.addAll(data);
    }

    public int size() {
        return list.size();
    }

    public E getValueAt(int index) {
        if (index >= 0 && index < list.size())
            return list.get(index);
        else
            return null;
    }

    public int getParentIndex(int index) {
        if (index <= 0 || index >= list.size())
            return -1;
        else
            return (index - 1) / 2;
    }

    public int getLeftIndex(int parentIndex) {
        if(parentIndex < 0)
        {
            return -1;
        }
        int leftChildIndex = 2 * parentIndex + 1;
        if (leftChildIndex < list.size())
            return leftChildIndex;
        else
            return -1;
    }

    public int getRightIndex(int parentIndex) {
        if(parentIndex < 0)
        {
            return -1;
        }
        int rightChildIndex = 2 * parentIndex + 2;
        if (rightChildIndex < list.size())
            return rightChildIndex;
        else
            return -1;
    }

    public String preorder() {
        return "[" + preorderHelper(0) + "]";
    }

    private String preorderHelper(int index) {
        if (index >= list.size())
            return "";

        String ret = list.get(index).toString();
        String left = preorderHelper(2 * index + 1);
        String right = preorderHelper(2 * index + 2);
        if (!left.isEmpty()) {
            ret += ", " + left;
        }
        if(!right.isEmpty())
        {
            ret += ", " + right;
        }

        return ret;
    }

    public String inorder() {
        String ret = inorderHelper(0);
        if (!ret.isEmpty())
            ret = ret.substring(0, ret.length()-2);
        return "[" + ret + "]";
    }

    private String inorderHelper(int index) {
        if (index >= list.size())
            return "";

        String ret = "";
        String left = inorderHelper(2 * index + 1);
        if (!left.isEmpty())
            ret += left;

        ret += list.get(index).toString() + ", ";

        String right = inorderHelper(2 * index + 2);
        if (!right.isEmpty())
            ret += right;

        return ret;
    }

    public String postorder() {
        return "[" + postorderHelper(0) + "]";
    }

    private String postorderHelper(int index) {
        if (index >= list.size())
            return "";

        String ret = "";
        String left = postorderHelper(2 * index + 1);
        if (!left.isEmpty())
            ret += left + ", ";

        String right = postorderHelper(2 * index + 2);
        if (!right.isEmpty())
            ret += right + ", ";

        ret += list.get(index).toString();

        return ret;
    }

    public void swap(int indexA, int indexB) {
        if (indexA >= 0 && indexA < list.size() && indexB >= 0 && indexB < list.size()) {
            E temp = list.get(indexA);
            list.set(indexA, list.get(indexB));
            list.set(indexB, temp);
        }
    }
}
