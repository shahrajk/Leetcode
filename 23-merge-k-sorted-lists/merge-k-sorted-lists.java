import java.util.PriorityQueue;

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        // Min-heap to store nodes, ordered by node value
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(
            (a, b) -> a.val - b.val
        );

        // Add the head of each list to the min-heap
        for (ListNode node : lists) {
            if (node != null) {
                minHeap.offer(node);
            }
        }

        // Dummy head to build the merged list
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        // Extract the smallest node and add its next node to the heap
        while (!minHeap.isEmpty()) {
            ListNode smallest = minHeap.poll();
            current.next = smallest;
            current = current.next;

            if (smallest.next != null) {
                minHeap.offer(smallest.next);
            }
        }

        return dummy.next;
    }
}