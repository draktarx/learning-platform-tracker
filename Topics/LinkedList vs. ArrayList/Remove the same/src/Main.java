import java.util.*;

class ListOperations {
    public static void removeTheSame(LinkedList<String> linkedList, ArrayList<String> arrayList) {
        // write your code here
        for (int i = linkedList.size() - 1; i >= 0; i--) {
            if (linkedList.get(i).equals(arrayList.get(i))) {
                linkedList.remove(i);
                arrayList.remove(i);
            }
        }
    }
}