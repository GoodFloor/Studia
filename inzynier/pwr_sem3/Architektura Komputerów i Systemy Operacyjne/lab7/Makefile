zad1: zad1.o
	ld -m elf_i386 zad1.o -o zad1
zad1.o: zad1.asm funkcje.asm
	nasm -f elf zad1.asm
zad2: zad2.o
	ld -m elf_i386 zad2.o -o zad2
zad2.o: zad2.asm funkcje.asm
	nasm -f elf zad2.asm
zad3: zad3.o
	ld -m elf_i386 zad3.o -o zad3
zad3.o: zad3.asm funkcje.asm
	nasm -f elf zad3.asm




zad7: zad7.o
	ld -m elf_i386 zad7.o -o zad7
zad7.o: zad7.asm zad7_funkcja.asm zad7_boot.asm funkcje.asm
	nasm -f elf zad7.asm
clear:
	rm -f *.o
	rm -f zad1
	rm -f zad2
	rm -f zad3
