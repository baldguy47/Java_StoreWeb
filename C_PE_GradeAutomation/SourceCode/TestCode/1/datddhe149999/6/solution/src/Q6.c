#include <stdio.h>
#include <unistd.h>
 
int main() {
   int n,i,j;
 
   scanf("%d",&n);   // khai bao so hang.
 
   
   for(i = 1; i <= n; i++) {
      for(j = 1; j <= n-i; j++)
         printf(" ");
 
      for(j = 1; j <= i; j++)
         printf("* ");
 
      printf("\n");
   }
   return 1;
}
