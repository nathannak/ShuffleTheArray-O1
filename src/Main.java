public class Main {


    public static void main(String[] args) throws InterruptedException {

        shuffle(new int[]{2,5,1,3,4,7}, 3);
        Thread.sleep(5000);

    }

    public static int[] shuffle(int[] nums, int n) {

        /*
        e.g. for i==0
        we push nums[i] 10 bits to left, so 2 becomes 2048
        then we add (vai |=) 3 to 2048 and get 2051
        in second for loop if you push nums[i] 10 bits to left
        you get 2 [purge 3 out of 2051]
        and if you and with 00000000001111111111 (1023)
        which means 0 all the 10 leftmost bits
        you purge 2 and get 3, this way we achieve O(1)
        in memory performance
        */

        for (int i = 0; i < n; ++i) {
            nums[i + n] |= (nums[i] << 10);
        }
        for (int i = 0; i < n; ++i) {
            nums[i * 2] = (nums[i + n]) >> 10;     // 11111111110000000000 == 0xFFC00
            nums[i * 2 + 1] = nums[i + n] & 0x003FF;        // 00000000001111111111 == 0x003FF == 1023
        }
        return nums;

    }


}
