import java.util.*;

/*
1    Print the Middle of a given linked list
2   Flattening a linked list
3    Delete the elements in an linked list whose sum is equal to zero
4    Delete middle of linked list
5    Remove duplicate elements from sorted linked list
6    Add 1 to a number represented as a linked list
7    Reverse a linked list in groups of given size
8    Detect loop in linked list
9    Remove loop in linked list
10    Find nth node from the end of linked list
11    Function to check if a singly linked list is a palindrome
12    Reverse alternate k node in a singly linked list
13    Delete last occurrence of an item from linked list
14    Rotate a linked list.
15    Delete n nodes after m nodes of a linked list.
16    Merge a linked list into another linked list at alternate positions.
17    Write a function to delete a linked list.
18    Write a function to reverse the nodes of a linked list.
19    Why quicksort is preferred for arrays and merge sort for linked lists.
20    linked list in java
*/

class Solution {

    public ListNode deleteMiddle(ListNode head) {
        if (head == null || head.next == null)
            return null;
        ListNode midKaPre = midKaPreFun(head);
        midKaPre.next = midKaPre.next.next;
        return head;
    }

    public static ListNode midKaPreFun(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode pre = null, slow = head, fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            pre = slow;
            slow = slow.next;
        }
        return pre;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public boolean isPalindrome(ListNode head) {

        ListNode midNode = midNode(head);
        ListNode nHead = midNode.next;
        nHead = reverse(nHead);

        ListNode c1 = head, c2 = nHead;
        while (c2 != null) {
            if (c1.val != c2.val) {
                // Before returning the verdict just making second half in original form
                nHead = reverse(nHead);

                return false;
            }

            c1 = c1.next;
            c2 = c2.next;
        }
        nHead = reverse(nHead);
        return true;

    }

    public static ListNode reverse(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode frwd = cur.next;
            cur.next = pre;
            pre = cur;
            cur = frwd;
        }
        return pre;
    }

    public static ListNode midNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0)
            return null;

        return mergeKListsHelper(lists, 0, lists.length - 1);
    }

    public ListNode mergeKListsHelper(ListNode[] lists, int si, int ei) {
        if (si == ei)
            return lists[si];
        int mid = (si + ei) / 2;
        ListNode l1 = mergeKListsHelper(lists, si, mid);
        ListNode l2 = mergeKListsHelper(lists, mid + 1, ei);

        return mergeTwoLists(l1, l2);
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        ListNode ans = new ListNode(0);
        ListNode temp = ans;

        while (list1 != null && list2 != null) {
            if (list1.val > list2.val) {
                temp.next = new ListNode(list2.val);
                temp = temp.next;
                list2 = list2.next;
            } else {
                temp.next = new ListNode(list1.val);
                temp = temp.next;
                list1 = list1.next;
            }
        }

        while (list1 != null) {
            temp.next = new ListNode(list1.val);
            temp = temp.next;
            list1 = list1.next;
        }

        while (list2 != null) {
            temp.next = new ListNode(list2.val);
            temp = temp.next;
            list2 = list2.next;
        }

        return ans.next;
    }
}
