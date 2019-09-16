//Program Written By Rafal Koziel (main function)

#include <stdio.h>
#include <string.h>
#include "sortAndShuffle.h"
#include <time.h>
#include <stdlib.h>

int main(void)
{
    //NAMING ALL VARIABLES

    srand(time(NULL));         //command that enables use of "random numbers"
    char artists[4][80];       //2D array that stores up to 4 artists
    char sortedArtists[4][80]; //2D array that stores sorted artists
    char songsArtist1[3][80];
    char songsArtist2[3][80];   //2D arrays that store list of songs of each artist
    char songsArtist3[3][80];
    char songsArtist4[3][80];
    int numOfArtists =0;
    char allsongs[12][80];      //2D array that stores all songs
    int i,j;                    //used as counters in loop
    int z=0;
    int number1=0;
    int number2=0;              //integer storing number of songs for each artist
    int number3=0;
    int number4=0;
    int all;                    //integer that calculates number of all songs

//READING INPUT


for(i=0;i<=3;i++)               //For loop that reads user input and stores information in arrays
{
   printf("Insert name of an artist number %d: ",i+1);
   fgets(artists[i],80,stdin);
   if(artists[i][0]=='\n')                              //This part gets artist name and saves it in artists[i]
        break;                                          //Loop is broken if no artist is inputted by user
        artists[i][strlen(artists[i])-1]='\0';
   if(i==0)
   {
       for(j=0;j<=2;j++)
       {
          printf("\tInsert name of a song number %d for %s: ",j+1,artists[i]);
          fgets(songsArtist1[j],80,stdin);      //This part stores all songs for artist number 1
          if(songsArtist1[j][0]=='\n')
            break;
          number1++;            //number of songs for artist 1 is incremented each time song of that artist is inputted
       }
   }
   if(i==1)
   {
       for(j=0;j<=2;j++)
       {
          printf("\tInsert name of a song number %d for %s :",j+1,artists[i]);
          fgets(songsArtist2[j],79,stdin);      //This part stores all songs for artist number 2
          if(songsArtist2[j][0]=='\n')
            break;
          number2++;              //number of songs for artist 2 is incremented each time song of that artist is inputted
       }
   }
   if(i==2)
   {
       for(j=0;j<=2;j++)
       {
          printf("\tInsert name of a song number %d for %s :",j+1,artists[i]);
          fgets(songsArtist3[j],79,stdin);      //This part stores all songs for artist number 3
          if(songsArtist3[j][0]=='\n')
            break;
          number3++;            //  //number of songs for artist 3 is incremented each time song of that artist is inputted
       }
   }
   if(i==3)
   {
       for(j=0;j<=2;j++)
       {
          printf("\tInsert name of song number %d for %s :",j+1,artists[i]);
          fgets(songsArtist4[j],79,stdin);      //This part stores all songs for artist number 4
          if(songsArtist4[j][0]=='\n')
            break;
          number4++;                  //number of songs for artist 4 is incremented each time song of that artist is inputted
       }
   }
   numOfArtists++; //number of artists is incremented each time to keep count of total number of artists

}

//SORTING SONGS AND ARTIST ALPHABETICALLY

for(i=0;i<numOfArtists;i++) //for loop that copies artists from  array artists to array sorted artists
{                           //sorted artists is later used to sort artists alphabetically
    strcpy(sortedArtists[i],artists[i]);
}

sortArtists(sortedArtists,numOfArtists); //function call, sorts artists in sortedArtists array alphabetically

sortSongs(songsArtist1,number1);     //function call, sorts all songs of each artist alphabetically
sortSongs(songsArtist2,number2);
sortSongs(songsArtist3,number3);
sortSongs(songsArtist4,number4);

//PRINTING ALL ARTISTS AND THEIR SONGS IN ALPHABETICAL ORDER

printf("List of artists and their songs in alphabetical order:\n");//prints out artists and their songs in alphabetical order

for(i=0;i<=numOfArtists-1;i++)      //for loop that links artists with their songs
{
   printf("Artist number %d:\n%s\n",i+1,sortedArtists[i]); //if sortedArtists[i] is the same as artists[0]
   if(strcmp(sortedArtists[i],artists[0])==0)              //Then we know that it is artist number 1
   {                                                       //So we print songs of artist number 1
       for(j=0;j<=number1-1;j++)
       {
          printf("     -%s",songsArtist1[j]);
       }
   }
   if(strcmp(sortedArtists[i],artists[1])==0)       //Same as previous example
   {                                                //This time if sortedArtist[i]==artists[i]
       for(j=0;j<=number2-1;j++)                    //Then we know that it is Artist number 2
       {
          printf("      -%s",songsArtist2[j]);
       }
   }
   if(strcmp(sortedArtists[i],artists[2])==0)       //Same as previous if statement
   {
       for(j=0;j<=number1-1;j++)
       {
          printf("      -%s",songsArtist3[j]);
       }
   }
   if(strcmp(sortedArtists[i],artists[3])==0)   //same as previous if statement
   {
       for(j=0;j<=number1-1;j++)
       {
          printf("      -%s",songsArtist4[j]);
       }
   }
}

//CREATING ONE ARRAY WITH ALL SONGS

//This segment copies all songs into 1 array so it is easier to shuffle it
if(numOfArtists>=1)
{
        copysongs(allsongs,songsArtist1,number1); //calls function that copies songs of artist number 1 to allsongs
}
if(numOfArtists>=2)
{
       copysongs(allsongs,songsArtist2,number2);  //calls function that copies songs of artist number 1 to allsongs
}
if(numOfArtists>=3)
{
       copysongs(allsongs,songsArtist3,number3);//calls function that copies songs of artist number 1 to allsongs
}
if(numOfArtists>=4)
{
    copysongs(allsongs,songsArtist4,number4);   //calls function that copies songs of artist number 1 to allsongs
}
all=number1+number2+number3+number4;    //This calculates the total number of all songs
printf("\nShuffled playlist:\n");

//SHUFFLING SONGS

shuffleSongs(allsongs,all); //function call, shuffles the songs in allsongs array

//PRINTING SHUFFLED PLAYLIST

while(z<2) //This while loop is used to print out shuffled array of songs twice so the requirements of the assignment are met
{

    for(i=0;i<all;i++)      //For loop that once again links artists with their songs
    {                       //This time it is other way around as it finds the right artist based on song name
        for(j=0;j<3;j++)
        {
            if(strcmp(allsongs[i],songsArtist1[j])==0)  //Compares songs to find right artist
                {
                   printf("%s -%s",artists[0],allsongs[i]); //if allsongs[i] is the same as any song in songsArtist1
                }                                          //We link this long with artist number 1 and print its name

        }
        for(j=0;j<3;j++)
        {
            if(strcmp(allsongs[i],songsArtist2[j])==0)  //same as previous if statement
                printf("%s -%s",artists[1],allsongs[i]);
        }
        for(j=0;j<3;j++)
        {
            if(strcmp(allsongs[i],songsArtist3[j])==0)  //same as previous if statement
                printf("%s -%s",artists[2],allsongs[i]);
        }
        for(j=0;j<3;j++)
        {
            if(strcmp(allsongs[i],songsArtist4[j])==0)  //same as previous if statement
                printf("%s -%s",artists[3],allsongs[i]);
        }
    }
z++;    //z is incremented and the loop happens one more time
}

  return 0;
}


