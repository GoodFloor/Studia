%include    'funkcje.asm'

SECTION .data
msg1 db 'Podaj swoje imie: ', 0h 
msg2 db 'Witaj, ', 0h 

SECTION .bss
input: resb 16
intAsString: resb 16

SECTION .text
global _start

_start:
    ; mov eax, 14
    ; mov ebx, intAsString
    ; call intToString
    ; mov eax, intAsString
    mov edi, intAsString
    mov al, 13
    mov bl, 10
    mov dl, 0
    div bl
    add dl, 48
  
    mov byte[edi + 1], dl
    mov dl,0
    div bl
    add dl, 48
    mov byte[edi], dl
    mov byte[edi + 2], 0
    mov eax, intAsString
    call println
    ;mov ecx, 0

    ;call println 
    ;mov ebx, intAsString
    ;call intToString
    ;mov eax, intAsString
    ;call println
    ; mov eax, msg1
    ; call print
    ; mov edx, 255
    ; mov ecx, input
    ; mov ebx, 0
    ; mov eax, 3
    ; int 80h
    ; mov eax, msg2
    ; call print
    ; ;mov al, input[1]
    ; movsx eax, byte[input + 1]
    ; push eax
    ; mov eax, esp
    ; call print
    ; pop eax

    ; mov eax, 14
    ; call intPrintln
    
; readArg:
;     cmp ecx, 0h 
;     jz end
;     pop eax
;     call println
;     dec ecx
;     jmp readArg

end:
    call quit