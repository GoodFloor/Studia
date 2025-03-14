with Ada.Text_IO; use Ada.Text_IO;
with Ada.Integer_Text_IO; use Ada.Integer_Text_IO;
with Ada.Numerics.Discrete_Random;
with Ada.Strings;
with Ada.Strings.Fixed;
with Semaphores;

procedure Main is
    N : Integer := 10;
    Eating : array(1..N) of Boolean;
    PrintGuard : Semaphores.BinarySemaphore;
    Fork : array(1..N) of Semaphores.BinarySemaphore;
    procedure PrintEating;
    
    task type Philosopher is
        entry Init(I : Integer);
    end Philosopher;
    PhilosopherArray : array(1..N) of Philosopher;

    procedure PrintEating is
        NextFork : Integer;
        IsFirst : Boolean := True;
    begin
        for I in 1..N loop
            if Eating(I) then
                if IsFirst then
                    IsFirst := False;
                else
                    Put(", ");
                end if;
                Put("(");
                Put("W" & Ada.Strings.Fixed.Trim(Integer'Image(I), Ada.Strings.Left));
                Put(", ");
                Put("F" & Ada.Strings.Fixed.Trim(Integer'Image(I), Ada.Strings.Left));
                Put(", ");
                NextFork := (I mod N) + 1;
                Put("W" & Ada.Strings.Fixed.Trim(Integer'Image(NextFork), Ada.Strings.Left));
                Put(")");
            end if;
        end loop;
        Put_Line("");
    end PrintEating;

    task body Philosopher is
        ID : Integer;
        FirstFork : Integer;
        SecondFork : Integer;
        subtype DelayRange is Integer range 1..10;
        package RandDelay is new
            Ada.Numerics.Discrete_Random (DelayRange);
        use RandDelay;
        Gen : Generator;
    begin
        Reset(Gen);
        accept Init(I : Integer) do
            ID := I;
            FirstFork := I;
            SecondFork := (I mod N) + 1;
            if I = 1 then
                FirstFork := I + 1;
                SecondFork := I;
            end if;
        end Init;
        loop
            --  Myśl
            delay 0.1 * Random(Gen);

            Fork(FirstFork).Wait;
            Fork(SecondFork).Wait;
            --  Jedz
            PrintGuard.Wait;
            Eating(ID) := True;
            PrintEating;
            PrintGuard.Signal;
            delay 0.1 * Random(Gen);
            --  Koniec jedzenia
            PrintGuard.Wait;
            Eating(ID) := False;
            PrintEating;
            PrintGuard.Signal;

            Fork(SecondFork).Signal;
            Fork(FirstFork).Signal;
        end loop;
    end Philosopher;

begin
    for I in 1..N loop
        Eating(I) := False;
    end loop;
    for I in 1..N loop
        PhilosopherArray(I).Init(I);
    end loop;
end Main;
