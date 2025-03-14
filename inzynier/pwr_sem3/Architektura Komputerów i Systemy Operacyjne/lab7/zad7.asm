%include    'funkcje.asm'
%include    'zad7_boot.asm'
%include    'zad7_funkcja.asm'

SECTION .text
global _start

_start:
    ;call SETCURSOR
    call quit