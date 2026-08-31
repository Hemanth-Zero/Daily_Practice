class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        
        int minDist = Integer.MAX_VALUE;
        int maxDist = 0;

        int first = -1;
        int last = -1;

        int index = 1;

        ListNode prev = head;
        ListNode curr = head.next;

        while (curr.next != null) {

            if ((curr.val > prev.val && curr.val > curr.next.val) ||
                (curr.val < prev.val && curr.val < curr.next.val)) {

                if (first == -1) {
                    first = index;
                }

                if (last != -1) {
                    minDist = Math.min(minDist, index - last);
                }

                last = index;
                maxDist = last - first;
            }

            prev = curr;
            curr = curr.next;
            index++;
        }

        if (first == last) {
            return new int[] {-1, -1};
        }

        return new int[] {minDist, maxDist};
    }
}