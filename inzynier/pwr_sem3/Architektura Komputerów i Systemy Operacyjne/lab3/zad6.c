#include <stdio.h>

int main() {
    /*RGB (>16 000 000 kolorów)*/
    for (int r = 0; r < 256; r++)
    {
        for (int g = 0; g < 256; g++)
        {
            for (int b = 0; b < 256; b++)
            {
                printf("\033[38;2;%i;%i;%imHello World! ", r, g, b);
            }
            
        }
        
    }

    // /*256 kolorów*/
    // for (int i = 0; i < 256; i++)
    // {
    //     printf("\033[38;5;%imHello World! ", i);
    // }

    // /*8 podstawowych kolorów*/
    // for (int i = 30; i < 38; i++)
    // {
    //     printf("\033[%imHello World! ", i);
    // }

    // printf("\n");

    // /*8 dodatkowych (jasnych) kolorów*/
    // for (int i = 90; i < 98; i++)
    // {
    //     printf("\033[%imHello World! ", i);
    // }
    
    
}
