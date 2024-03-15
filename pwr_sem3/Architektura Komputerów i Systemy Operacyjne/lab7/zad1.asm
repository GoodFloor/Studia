%include 'funkcje.asm'

SECTION .text
global _start
_start:
    mov eax, 123456
    mov ebx, 16
    mov ecx, 0
readDigit:
    mov edx, 0
    idiv ebx
    cmp edx, 9
    jg addMore
continue:
    add edx, 48
    push edx
    inc ecx
    cmp eax, 0
    jnz readDigit
printDigit:
    mov eax, esp
    call print
    pop edx
    dec ecx
    cmp ecx, 0
    jnz printDigit
    call quit
addMore:
    add edx, 7
    jmp continue



