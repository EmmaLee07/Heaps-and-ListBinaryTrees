public class Heap<E extends Comparable> extends ListBinaryTree<E>
{
    //Add data to this heap
    //Add data exactly like a ListBinaryTree, then propigate that value up the tree 
    //(using the addHelper method)
    @Override
    public void add(E data) {
        super.add(data); // Add data like ListBinaryTree
        addHelper(size() - 1); // Propagate up the tree
    }

    //Recursive helper method for add
    //Recusively swap the value at index and its parent while val is less than its parent
    private void addHelper(int index) {
        if (index <= 0) // Base case: reached the root
            return;

        int parentIndex = getParentIndex(index);
        if (parentIndex >= 0 && getValueAt(index).compareTo(getValueAt(parentIndex)) < 0) {
            swap(index, parentIndex); // Swap with parent
            addHelper(parentIndex); // Recursively check parent
        }
    }

    //returns true if the value at index is less than both of its children
    public boolean meetsHeapProperty(int index) {
        int leftChildIndex = getLeftIndex(index);
        int rightChildIndex = getRightIndex(index);

        if (leftChildIndex == -1 && rightChildIndex == -1) // Node has no children
            return true;

        boolean leftChildValid = leftChildIndex != -1 && getValueAt(index).compareTo(getValueAt(leftChildIndex)) <= 0;
        boolean rightChildValid = rightChildIndex != -1 && getValueAt(index).compareTo(getValueAt(rightChildIndex)) <= 0;

        return leftChildValid && rightChildValid;
    }

    //Helper method
    //Returns the index of the child of the specified node with the smallest value
    private int getSmallestChildIndex(int index) {
        int leftChildIndex = getLeftIndex(index);
        int rightChildIndex = getRightIndex(index);

        if (leftChildIndex == -1 && rightChildIndex == -1) // Node has no children
            return -1;
        else if (leftChildIndex == -1) // Only has right child
            return rightChildIndex;
        else if (rightChildIndex == -1) // Only has left child
            return leftChildIndex;
        else // Has both children
            return getValueAt(leftChildIndex).compareTo(getValueAt(rightChildIndex)) < 0 ? leftChildIndex : rightChildIndex;
    }

    //remove and return the value at the root of this heap
    //then reconstitute its heapness using the remove algorithm
    public E removeRoot() {
        if (size() == 0)
            return null;

        E rootValue = getValueAt(0);
        swap(0, size() - 1); // Swap root with the last value
        list.remove(size() - 1); // Remove the last value
        sink(0); // Reconstruct heap property
        return rootValue;
    }

    public void heapify() {
        for (int i = size() / 2 - 1; i >= 0; i--) {
            sink(i);
        }
    }

    //recursive helper method for heapify. 
    //This method "sinks" the value at index until it meets the heap property
    private void sink(int index) {
        if (index < 0 || index >= size()) // Base case: out of bounds
            return;

        while (!meetsHeapProperty(index)) {
            int smallestChildIndex = getSmallestChildIndex(index);
            if (smallestChildIndex != -1) {
                swap(index, smallestChildIndex);
                index = smallestChildIndex; // Move down the tree
            } else {
                break; // No children
            }
        }
    }

    //do not edit this method!
    public void shuffle()
    {
        for(int i=0; i<size(); i++)
            swap(i, (int)(Math.random() * size()));
    }
}