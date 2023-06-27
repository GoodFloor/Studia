%include 'funkcje.asm'

SECTION .text
global _start
_start:
    mov esi, 0x2137
    
    ;1. cyfra
    mov eax, 0xF000
    and eax, esi
    mov edx, 0
    mov ecx, 0x1000
    div ecx
    call intPrint

    ;2. cyfra
    mov eax, 0xF00
    and eax, esi
    mov edx, 0
    mov ecx, 0x100
    div ecx
    call intPrint

    ;3. cyfra
    mov eax, 0xF0
    and eax, esi
    mov edx, 0
    mov ecx, 0x10
    div ecx
    call intPrint

    ;4. cyfra
    mov eax, 0xF
    and eax, esi
    call intPrintln

    call quit