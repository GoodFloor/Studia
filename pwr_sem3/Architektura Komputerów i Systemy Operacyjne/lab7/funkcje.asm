;Pobranie integera z eax i zapis jako string zaczynający się w ebx  
intToString:
    push eax
    push ebx
    push ecx
    push edx
    push edi
    mov edi, ebx
    mov bl, 10
    mov ecx, 0
nextDigit:
    mov dl, 0
    div bl
    add dl, 48
    ;mov byte[edi + ecx], dl
    inc ecx
    cmp eax, 0
    jnz nextDigit


    ;mov byte[edi + ecx], 0
    pop edi
    pop edx
    pop ecx
    pop ebx
    pop eax
    ret
;     push edx
;     push ecx
;     mov edi, ebx
;     mov ebx, 10
;     mov ecx, 0    
; nextDigit:
;     mov edx, 0
;     inc ecx
;     idiv ebx
;     add edx, 48
;     push eax
;     cmp eax, 0
;     jnz nextDigit
;     mov edx, 0
; saveDigits:
;     dec ecx
;     mov eax, esp
;     mov byte[edi + edx], byte[esp]
;     pop eax
;     inc edx
;     cmp ecx, 0
;     jnz saveDigits
;     mov byte[edi + edx], 0
;     pop ecx
;     pop edx
;     ret

intPrint:
    push edx
    push ecx
    push ebx
    mov ebx, 10
    mov ecx, 0
nextInt:
    inc ecx
    mov edx, 0
    idiv ebx
    add edx, 48
    push edx
    cmp eax, 0
    jnz nextInt
printInt:
    mov eax, esp
    call print
    dec ecx
    pop eax
    cmp ecx, 0
    jnz printInt
    pop ebx
    pop ecx
    pop edx
    ret

intPrintln:
    call intPrint
    push eax
    mov eax, 0Ah
    push eax
    mov eax, esp
    call print
    pop eax
    pop eax
    ret

;Zapisz w eax długość stringa podanego w eax
strlen:
    push ebx
    mov ebx, eax
nextChar:
    cmp byte[eax], 0
    jz finished
    inc eax
    jmp nextChar
finished:
    sub eax, ebx
    pop ebx
    ret

;Wypisz string z eax na konsoli
print:
    push edx
    push ecx
    push ebx
    push eax
    call strlen
    mov edx, eax
    pop ecx
    ;mov ecx, eax
    mov ebx, 1
    mov eax, 4
    int 80h
    pop ebx
    pop ecx
    pop edx
    ret

;Wypisz string z eax i znak nowej linii
println:
    call print
    push eax
    mov eax, 0Ah
    push eax
    mov eax, esp
    call print
    pop eax
    pop eax
    ret

;Wyjdź z programu
quit:
    mov ebx, 0
    mov eax, 1
    int 80h
    ret