#include <stdio.h>
#include <string.h>
#include "sortAndShuffle.h"
#include <time.h>
#include <stdlib.h>



//FUNCTION THAT SORTS ARTISTS
void sortArtists(char sortedArtists[][80] , int numOfArtists)
{
    //Code for this function was provided on csmoodle
    int i =0;
    int j=0;
    int minIndex = 0;
    int minIndexChanged = 0;

    char swap [80];

    for(i=0; i<(numOfArtists-1); i ++)  //this for loop sorts strings alphabetically
        {
        minIndex = i;
        for(j=i+1; j<numOfArtists; j++){
            if(strcmpi(sortedArtists[j], sortedArtists[minIndex]) < 0){
                minIndex = j;       //Based on if statement value of minIndex is updated
                minIndexChanged =1;
            }
        }

            if(minIndexChanged == 1)    //If minIndex was updated this follows
            {                           //And swaps string in sortedArtists[i] with sortedArtists[minindex]
                memset(swap, '$', 78);
                swap[79] = '\0';
                strcpy(swap,sortedArtists[i]);
                strcpy(sortedArtists[i], sortedArtists[minIndex]);
                strcpy(sortedArtists[minIndex], swap);
                minIndexChanged =0;
            }

    }
}
void sortSongs(char songsOfAnArtist[][80], int number)
{
    //Used the same algorithm as for sorting artists
    int i =0;
    int j=0;
    int minIndex = 0;
    int minIndexChanged = 0;

    char swap [80];

    for(i=0; i<(number-1); i ++)
        {
        minIndex = i;
        for(j=i+1; j<number; j++){
            if(strcmpi(songsOfAnArtist[j], songsOfAnArtist[minIndex]) < 0){
                minIndex = j;
                minIndexChanged =1;
            }
        }

            if(minIndexChanged == 1)
            {
                memset(swap, '$', 78);
                swap[79] = '\0';
                strcpy(swap,songsOfAnArtist[i]);
                strcpy(songsOfAnArtist[i], songsOfAnArtist[minIndex]);
                strcpy(songsOfAnArtist[minIndex], swap);
                minIndexChanged =0;
            }

    }
}
//This function copies songs into one array so it is easier to shuffle them
void copysongs(char allsongs[][80],char songartist[][80],int numberofsongs){
 int i;
 for(i=0;i<numberofsongs;i++)
 {
     strcpy(allsongs[count],songartist[i]); //Copies songartist[i] to allsongs and increases global variable count
     count++;                               //count is used to so songs of next artists will be copied after previous songs
 }                                          //without replacing them
}


//function that uses Fisher Yates algorithm to shuffle the playlist of songs
void shuffleSongs(char songsToBeShuffled[][80], int numOfSongs){
    //this code follows example of Fisher Yates algorithm provided in one of the lectures
    int i,j;
    char swap[80];
    for(i=1;i<numOfSongs;i++) //This loop happens till it reaches total number os songs
    {
        j=rand()%(i+1); //Chooses random number between 0 and i+1

        if(j!=i)   //if j is different than i the algorithm swaps string held in songsToBeShuffled[i] with songsToBeShuffled[j]
        {
            strcpy(swap,songsToBeShuffled[j]);
            strcpy(songsToBeShuffled[j],songsToBeShuffled[i]);
            strcpy(songsToBeShuffled[i],swap);
        }
    }
}
