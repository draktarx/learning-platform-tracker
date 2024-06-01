class ListOperations {
    public static void transferAllElements(LinkedList<String> linkedList, ArrayList<String> arrayList) {
        // write your code here
        ArrayList<String> temp = new ArrayList<>();
        temp.addAll(arrayList);
        arrayList.clear();
        arrayList.addAll(linkedList);
        linkedList.clear();
        linkedList.addAll(temp);
    }
}