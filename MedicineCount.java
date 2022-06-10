/*
  Problem statement:
  A hospital has N numbers of patients.
  Given an array, med[i] of N elements, med[i] describes the amount of medicine required by the ith patient. Initially, all the patients have zero amount of medicine and you want to provide them with medicines.
  You can perform two types of operations :
  In the 1st operation, you can choose any patient and increase the amount of medicine by 1.
  In 2nd operation, you can multiply the amount of medicine of all the N patients by 2.
  Calculate the minimum number of operations required to give exactly med[i] amount of medicine to all patients.
*/

public class MedicineCount{

    /**
      * Calculates the number of operations to give the amount of medication stored in med[].
      * Reasoning: Multiplying everything by two means we just need to worry about the largest number
      *  since, if a number has a value, it will get multiplied and it counts as a single operation.
      *  Also, since multiplying by 2 is equivalent to left shifting by 1, the length of the binary
      *  representation is how many multiplications it's undergone.
      *  Adding 1 to a number in base 2 is equivalent to adding 1 but the number of additions is 
      *  more obvious. So, we look at how many 1s are in the binary string to see how many additions
      *  this number has undergone. Unlike multiplying, we have to do this for every number because
      *  adding 1 is a single operation, not an aggregate operation.
      * @param N Was part of the original code, represents med.length
      * @param med The array of numbers representing medicine amounts
      * @returns The number of operations (x2, +1) that give the med array
      */
    public static int medicineOfPatients(int N,int[] med){
        // The count of 1s in a binary string
        int onesCount = countOnes(med[0]);
        // The binary string length of the largest number
        // Start off holding just the number in med itself, convert to binary length later
        int maxLength = med[0];

        // For each of the numbers, find out if it's the largest in med and count the ones
        //  in its binary string.
        for (int i = 1; i < med.length; i++) {
          maxLength = Math.max(maxLength, med[i]);
          onesCount += countOnes(med[i]);
        }

        // Find the length of the binary string of the largest number in med.
        maxLength = lengthOfBinaryString(maxLength);
        
        return maxLength + onesCount - 1;
    }
  
    public static int lengthOfBinaryString(int x) {
      return (int)(Math.log(x)/Math.log(2)) + 1;
    }

  public static int countOnes(int x) {
    int count = 0;
    while (x > 0) {
      if ((x & 1) == 1) {
        count++;
      }
      x = x >> 1;
    }

    return count;
  }
    
    /*
      ===============================================================
      Input | med                                         | Output
      ===============================================================
        3      2     1     2 	                                 4
        2      19    27 	                                     11
        3      14644 21646 34569                               35
        7      14454 14465 54466 84867 86653 86523 13431 	     74
        4      11111 11111 11111 11111 	                       49
        4      47    56    998   5656 	                       32
    */
    public static void main (String[]args){
        int[] med = new int[]{
          47, 56, 998, 5656 
        };

        System.out.print(medicineOfPatients(med.length,med));
    }
}
