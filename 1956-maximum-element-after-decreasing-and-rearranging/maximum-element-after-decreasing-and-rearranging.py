class Solution(object):
    def maximumElementAfterDecrementingAndRearranging(self, arr):
        """
        :type arr: List[int]
        :rtype: int
        """
        arr.sort()
        prev = 1
        
        for i in range(1, len(arr)):
            prev = min(arr[i], prev + 1)
        
        return prev