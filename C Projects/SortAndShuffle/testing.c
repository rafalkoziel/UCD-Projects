//Written By Rafal Koziel

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "sortAndShuffle.h"
#include "CUnit/CUnit.h"
#include "CUnit/Basic.h"


void test_quickSort_positive(void){     //Tests sorting of array with positive integers
	int array[5]={3,5,1,2,4};           //array that is being sorted
	int test[5]={1,2,3,4,5};            //array with expected result
	quickSort(array,5);                 //Passing array to the sorting function
	CU_ASSERT_EQUAL(memcmp(array,test,sizeof(array)),0);//comparing if sorted function is the same as expected result

}
void test_quickSort_negative(void){ //Tests sorting of array with negative integers (same steps used as in function above)
	int array[5]={-5,-3,-4,-1,-2};
	int test[5]={-5,-4,-3,-2,-1};
	quickSort(array,5);
	CU_ASSERT_EQUAL(memcmp(array,test,sizeof(array)),0);

}
void test_quickSort_mixed(void){    //Tests sorting of array with both positive and negative integers
	int array[5]={-3,5,2,-1,0};     //Same steps as in functions above
	int test[5]={-3,-1,0,2,5};
	quickSort(array,5);
	CU_ASSERT_EQUAL(memcmp(array,test,sizeof(array)),0);

}
void test_quickSort_large(void){     //Test sorting of array with large numbers (positive and negative)
	int array[3]={345678,-3153515,66666666};    //Same steps as in functions above
	int test[3]={-3153515,345678,66666666};
	quickSort(array,3);
	CU_ASSERT_EQUAL(memcmp(array,test,sizeof(array)),0);

}
void test_shuffling_positive(void){ //Tests shuffling of array with positive integers
	int array[5]={2,4,5,1,3};       //array with positive integers to be shuffled
	fisherYates(array,5);           //Passing arguments into function
	CU_ASSERT_NOT_EQUAL(array[0],2);    //checks that array[0] is not equal to what it was before shuffling
	CU_ASSERT_NOT_EQUAL(array[1],4);    //same steps for other places in array
	CU_ASSERT_NOT_EQUAL(array[2],5);
	CU_ASSERT_NOT_EQUAL(array[3],1);
	CU_ASSERT_NOT_EQUAL(array[4],3);

}
void test_shuffling_negative(void){ //Tests shuffling of array with negative integers
	int array[5]={-3,-4,-5,-2,-1};  //array with negative integers to be shuffled
	fisherYates(array,5);           //Same steps as in function above
	CU_ASSERT_NOT_EQUAL(array[0],-3);
	CU_ASSERT_NOT_EQUAL(array[1],-4);
	CU_ASSERT_NOT_EQUAL(array[2],-5);
	CU_ASSERT_NOT_EQUAL(array[3],-2);
	CU_ASSERT_NOT_EQUAL(array[4],-1);

}
void test_shuffling_mixed(void){    //Tests shuffling of array with both positive and negative integers
	int array[5]={-3,2,4,-5,0};     //Array with both positive and negative integers
	fisherYates(array,5);            //Same steps as in function above
	CU_ASSERT_NOT_EQUAL(array[0],-3);
	CU_ASSERT_NOT_EQUAL(array[1],2);
	CU_ASSERT_NOT_EQUAL(array[2],4);
	CU_ASSERT_NOT_EQUAL(array[3],-5);
	CU_ASSERT_NOT_EQUAL(array[4],0);

}
void test_shuffling_large(void){    //Tests shuffling of array with large numbers, both positive and negative
	int array[3]={-3333333,66666666,5000000};  //Same steps as in a function above
	fisherYates(array,3);
	CU_ASSERT_NOT_EQUAL(array[0],-3333333);
	CU_ASSERT_NOT_EQUAL(array[1],66666666);
	CU_ASSERT_NOT_EQUAL(array[2],5000000);
}




int main() {
    CU_initialize_registry();
    CU_pSuite suite = CU_add_suite("quicksort_test", 0, 0); //initializes suite for quicksort tests
    CU_add_test(suite, "quickSort_positive", test_quickSort_positive); //All quicksort tests...
    CU_add_test(suite, "quickSort_negative", test_quickSort_negative);
    CU_add_test(suite, "quickSort_mixed", test_quickSort_mixed);
    CU_add_test(suite, "quickSort_large", test_quickSort_large);

    CU_pSuite suite2 = CU_add_suite("shuffling_test", 0, 0);   //initializes suite for shuffling tests
    CU_add_test(suite2, "shufflingSort_positive", test_shuffling_positive);//all shuffling tests...
    CU_add_test(suite2, "shufflingSort_negative", test_shuffling_negative);
    CU_add_test(suite2, "shufflingSort_mixed", test_shuffling_mixed);
    CU_add_test(suite2, "shufflingSort_large", test_shuffling_large);
    CU_basic_set_mode(CU_BRM_VERBOSE);
    CU_basic_run_tests();
    CU_cleanup_registry();

    return 0;
}






