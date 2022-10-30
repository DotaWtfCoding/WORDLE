/*
 * SumsOfTwoSets.java
 *
 * Version:
 *     17.0.3
 *
 * Revisions:
 *     1
 */

/**
 * Class that represents the shortest possible value from one set in another
 *
 * @author      Vishal Panchidi
 * @author      Neeraj Bandi
 */

public class SumOfTwoSets {
    // set2 used for input.
    public static int[] set2 = {2, 3, 7  };

    // set1 gets created by summation of set1
    public static int[] set1;

    // Array used for storing answer for every element from set1
    public static int[] storingShortestArithmeticAnswer = new int[set2.length];

    // variable used for storing the size of storingShortestArithmeticAnswer
    public static int answerSize = set2.length;

    /**
     * Used to create set1 from the data of set2
     * @param tempSet2 : input for creating setOne
     */
    public static int[] createSet1(int[] tempSet2){
        int summationOfSetA = 0;

        //Gets the maximum value possible in set1
        for(int index = 0; index < tempSet2.length; index++){
         summationOfSetA = summationOfSetA + tempSet2[index];
        }

        //Gets all the numbers till maximum value in set1
        int[] set1 = new int[summationOfSetA];
        for (int index=0; index < summationOfSetA; index++){
            set1[index] = index + 1;
        }
        return set1;
    }


    /**
     * Used to sort set2
     * @param set2 : input of the unsorted set2
     */
    public static int[] sortingSet2(int[] set2){
        //Sorting set2 in descending order
        for(int firstElement = 0; firstElement < set2.length; firstElement++){
            for (int nextElement = firstElement + 1; nextElement < set2.length; nextElement++){
                int tempElement = 0;
                    //Swapping
                if (set2[nextElement] > set2[firstElement]) {
                    tempElement = set2[firstElement];
                    set2[firstElement] = set2[nextElement];
                    set2[nextElement] = tempElement;
                }

            }
        }
        return(set2);
    }


    /**
     * Used to add Negative values in to set2
     * @param sortedSet2 : input for adding negative values of existing values in SetA
     */
    public static int[] addingNegationOfSet2(int[] sortedSet2){
        int set2WithNegativeValues[] = new int[sortedSet2.length*2];

        //copying the existing values
        for(int index = 0; index < sortedSet2.length; index++){
            set2WithNegativeValues[index] = sortedSet2[index];
        }
        //concatenating the new negative values
        for(int index = 0; index<sortedSet2.length; index++){
            set2WithNegativeValues[sortedSet2.length + index] = sortedSet2[index] * -1;
        }

        return set2WithNegativeValues;
    }


    /**
     * Used to iterate over all the elements of set1
     * and find possible arithmetic combinations from set2 for set1.
     * Helps to print the required the combination
     * @param set2 : input for all the values needed for combinations
     * @param set1 : input for all the values for which combinations are going to be found.
     */
    private void getCombinationsForAllValues(int[] set2, int[] set1)
    {
        for(int index = 0; index < set1.length; ++index){
            //temporary storage for combination
            int[] tempStore = new int[set2.length];
            //storage for size of combination
            int tempStoreSize = 0;
            int counter = 0;
            shortestSubset(set1[index], counter, tempStore, tempStoreSize);
            System.out.print((index+1) +" = " );

            // Checking if arithmetic combinations are present and
            // prints the combinations else prints that the combinations are not possible
            if(answerSize>0){
                for(int number = 0;number < answerSize; ++number){
                    System.out.print(storingShortestArithmeticAnswer[number]);
                    System.out.print(" ");
                }
            }else {
                System.out.print("not possible");
            }

            System.out.println();
            storingShortestArithmeticAnswer = new int[set2.length];
            answerSize=0;
        }
    }


    /**
     * Finds the combinations used to get to the target in the set1.
     * @param requiredNumber : the target
     * @param counter : counter
     * @param tempStore : input with current combination
     * @param tempStoreSize : size of the current combination
     */

    private void shortestSubset(int requiredNumber, int counter,int[] tempStore, int tempStoreSize)
    {
        // Checks if the combination has been found
        if (requiredNumber == 0 ) {
            storingShortestArithmeticAnswer = tempStore.clone();
            answerSize = tempStoreSize;
            return;
        }
        //Checks if there are no more combinations
        if (counter == set2.length) {
            return;
        }
        // add the number in temporary Storage
        tempStoreSize += 1;
        tempStore[tempStoreSize-1] = set2[counter];
        // here current number is considered in storage
        shortestSubset(requiredNumber-set2[counter],counter+1,tempStore,tempStoreSize);
        // remove the number added earlier
        tempStoreSize -= 1;
        shortestSubset(requiredNumber,counter+1,tempStore,tempStoreSize);

    }

    /**
     * main method of the code.
     * @param args : Unused in the code.
     */

    public static void main(String[]args){
        SumOfTwoSets s = new SumOfTwoSets();
        //creating set1 using set2
        set1 = s.createSet1(set2);
        // sorting set2 in descending order
        int[] sortedSet2 = s.sortingSet2(set2);
        // add the negative values in set2 e.g [7,3,2] -> [7,3,2,-7,-3,-2]
        int[] allRequiredValuesOfSet2 = s.addingNegationOfSet2(sortedSet2);
        //updating set2 with all the required values
        set2 = allRequiredValuesOfSet2;
        answerSize = set2.length;
        s.getCombinationsForAllValues(set2,set1);
    }

}
