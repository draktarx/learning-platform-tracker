
class ListOperations {
    public static void mergeLists(LinkedList<String> linkedList, ArrayList<String> arrayList) {
        // write your code here
        linkedList.addAll(arrayList);
        System.out.println("The new size of LinkedList is " + linkedList.size());
    }
}