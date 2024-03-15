%include 'funkcje.asm'

SECTION .bss
numberArr: resb 100000

SECTION .text
global _start
_start:
    mov ecx, 2
    mov eax, msg
    mov byte[numberArr], 0
    mov byte[numberArr + 1], 0
initArr:
    mov byte[numberArr + ecx], 1
    inc ecx
    cmp ecx, 100000
    jl initArr
    mov ecx, 1
nextNumber:
    inc ecx
    cmp ecx, 100000
    jge endLoop
    cmp byte[numberArr + ecx], 0
    je nextNumber
    mov edx, ecx
insideLoop:
    add edx, ecx
    cmp edx, 100000
    jge nextNumber
    mov byte[numberArr + edx], 0
    jmp insideLoop
endLoop:
    mov ecx, 0
printLoop:
    inc ecx
    cmp ecx, 100000
    jge quitApp
    cmp byte[numberArr + ecx], 0
    jz printLoop
    mov eax, ecx
    call intPrintln
    jmp printLoop
quitApp:
    call quit
