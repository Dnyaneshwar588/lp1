                                                  // FIFO

import java.util.*;

public class pagereplacement
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);

        // Input number of frames
        System.out.print("Enter number of frames: ");
        int framesCount = sc.nextInt();

        // Input number of pages
        System.out.print("Enter number of pages: ");
        int pagesCount = sc.nextInt();

        int pages[] = new int[pagesCount];
        System.out.println("Enter page reference string:");
        for (int i = 0; i < pagesCount; i++) 
        {
            pages[i] = sc.nextInt();
        }

        Queue<Integer> frameQueue = new LinkedList<>();
        Set<Integer> frameSet = new HashSet<>();
        int pageFaults = 0;

        System.out.println("\nPage Replacement Process:");
        for (int i = 0; i < pagesCount; i++) 
        {
            int page = pages[i];

            // Check if page is already in frame
            if (!frameSet.contains(page)) 
            {
                // Page Fault occurs
                pageFaults++;

                // If frame is full, remove oldest page (FIFO)
                if (frameQueue.size() == framesCount) 
                {
                    int removed = frameQueue.poll();
                    frameSet.remove(removed);
                }

                // Add new page
                frameQueue.add(page);
                frameSet.add(page);
            }

            // Display current frame status
            System.out.print("Step " + (i + 1) + " (Page " + page + "): ");
            for (int f : frameQueue) 
            {
                System.out.print(f + " ");
            }
            System.out.println();
        }

        System.out.println("\nTotal Page Faults: " + pageFaults);
        sc.close();
    }
}









                                        //LRU


// import java.util.*;

// public class LRU
// {
//     public static void main(String[] args) 
//     {
//         Scanner sc = new Scanner(System.in);

//         System.out.print("Enter number of frames: ");
//         int framesCount = sc.nextInt();

//         System.out.print("Enter number of pages: ");
//         int pagesCount = sc.nextInt();

//         int pages[] = new int[pagesCount];
//         System.out.println("Enter page reference string:");
//         for (int i = 0; i < pagesCount; i++) 
//         {
//             pages[i] = sc.nextInt();
//         }

//         int[] frames = new int[framesCount];
//         Arrays.fill(frames, -1); // initialize frames as empty
//         int pageFaults = 0;

//         System.out.println("\nPage Replacement Process:");
//         for (int i = 0; i < pagesCount; i++) 
//         {
//             int page = pages[i];
//             boolean found = false;

//             // Check if page is already in frame
//             for (int f : frames) 
//             {
//                 if (f == page) 
//                 {
//                     found = true;
//                     break;
//                 }
//             }

//             if (!found) 
//             { // Page fault
//                 pageFaults++;

//                 int replaceIndex = -1;

//                 // Find an empty frame first
//                 for (int j = 0; j < framesCount; j++) 
//                 {
//                     if (frames[j] == -1) 
//                     {
//                         replaceIndex = j;
//                         break;
//                     }
//                 }

//                 // If no empty frame, apply LRU replacement
//                 if (replaceIndex == -1) {
//                     int leastRecent = i, idx = -1;

//                     for (int j = 0; j < framesCount; j++) 
//                     {
//                         int lastUsed = -1;
//                         for (int k = i - 1; k >= 0; k--) 
//                         {
//                             if (pages[k] == frames[j]) 
//                             {
//                                 lastUsed = k;
//                                 break;
//                             }
//                         }
//                         if (lastUsed < leastRecent) 
//                         {
//                             leastRecent = lastUsed;
//                             idx = j;
//                         }
//                     }
//                     replaceIndex = idx;
//                 }

//                 // Replace page
//                 frames[replaceIndex] = page;
//             }

//             // Print current frame contents
//             System.out.print("Step " + (i + 1) + " (Page " + page + "): ");
//             for (int f : frames) 
//             {
//                 if (f != -1) System.out.print(f + " ");
//             }
//             System.out.println();
//         }

//         System.out.println("\nTotal Page Faults: " + pageFaults);
//         sc.close();
//     }
// }






                                          // optimal


// import java.util.*;

// public class Optimal 
// {
//     public static void main(String[] args) 
//     {
//         Scanner sc = new Scanner(System.in);

//         // Input number of frames
//         System.out.print("Enter number of frames: ");
//         int framesCount = sc.nextInt();

//         // Input number of pages
//         System.out.print("Enter number of pages: ");
//         int pagesCount = sc.nextInt();

//         int pages[] = new int[pagesCount];
//         System.out.println("Enter page reference string:");
//         for (int i = 0; i < pagesCount; i++) 
//         {
//             pages[i] = sc.nextInt();
//         }

//         List<Integer> frames = new ArrayList<>();
//         int pageFaults = 0;

//         System.out.println("\nPage Replacement Process:");
//         for (int i = 0; i < pagesCount; i++) 
//         {
//             int page = pages[i];

//             // If page already in frame, skip
//             if (frames.contains(page)) 
//             {
            
//                 System.out.print("Step " + (i + 1) + " (Page " + page + "): ");
//                 printFrames(frames);
//                 continue;
//             }

//             // Page fault
//             pageFaults++;

//             // If frame not full, just add
//             if (frames.size() < framesCount) 
//             {
            
//                 frames.add(page);
//             } else {
//                 // Find optimal page to replace
//                 int indexToReplace = findOptimal(frames, pages, i + 1);
//                 frames.set(indexToReplace, page);
//             }

//             // Display current frame status
//             System.out.print("Step " + (i + 1) + " (Page " + page + "): ");
//             printFrames(frames);
//         }

//         System.out.println("\nTotal Page Faults: " + pageFaults);
//         sc.close();
//     }

//     // Find the page that will not be used for the longest time in the future
//     private static int findOptimal(List<Integer> frames, int[] pages, int start) 
//     {
//         int farthest = start, indexToReplace = -1;

//         for (int i = 0; i < frames.size(); i++) 
//         {
//             int page = frames.get(i);
//             int j;
//             for (j = start; j < pages.length; j++) 
//             {
//                 if (pages[j] == page) 
//                 {
//                     if (j > farthest) 
//                     {
//                         farthest = j;
//                         indexToReplace = i;
//                     }
//                     break;
//                 }
//             }
//             // Page not found in future
//             if (j == pages.length) 
//             {
//                 return i;
//             }
//         }

//         return (indexToReplace == -1) ? 0 : indexToReplace;
//     }

//     private static void printFrames(List<Integer> frames) 
//     {
//         for (int f : frames) {
//             System.out.print(f + " ");
//         }
//         System.out.println();
//     }
// }
