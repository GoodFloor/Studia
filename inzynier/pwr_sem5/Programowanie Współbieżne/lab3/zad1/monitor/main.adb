with Ada.Text_IO; use Ada.Text_IO;
with Ada.Integer_Text_IO; use Ada.Integer_Text_IO;
with Ada.Numerics.Discrete_Random;
with Ada.Strings;
with Ada.Strings.Fixed;
with Monitor_Package;

procedure Main is
    task Locker is
        entry Lock;
        entry Unlock;
    end Locker;

    N : Integer := Monitor_Package.N;
    M : Monitor_Package.Monitor;
    IsEating : array(0..N-1) of Boolean := (others => False);
    procedure PrintEating;
    
    task type Philosopher is
        entry Init(I : Integer);
    end Philosopher;
    PhilosopherArray : array(0..N-1) of Philosopher;

--  Locker
    task body Locker is
        IsUnlocked : Boolean := True;
    begin
        loop
            select
                when IsUnlocked => accept Lock do
                    IsUnlocked := False;
                end Lock;
            or
                accept Unlock do
                    IsUnlocked := True;
                end Unlock;
            end select;
        end loop;
    end Locker;

--  Wypisz jedzących
    procedure PrintEating is
        NextFork : Integer;
        IsFirst : Boolean := True;
    begin
        for I in 0..N-1 loop
            if IsEating(I) then
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
        Id : Integer;
        subtype DelayRange is Integer range 1..10;
        package RandDelay is new
            Ada.Numerics.Discrete_Random (DelayRange);
        use RandDelay;
        Gen : Generator;
    begin
        Reset(Gen);
        accept Init(I : Integer) do
            Id := I;
        end Init;
        loop
            --  Myśl
            delay 0.1 * Random(Gen);

            --  Jedz
            M.TakeFork(Id);

            Locker.Lock;
            IsEating(Id) := True;
            PrintEating;
            Locker.Unlock;
            delay 0.1 * Random(Gen);
            --  Koniec jedzenia
            Locker.Lock;
            IsEating(Id) := False;
            PrintEating;
            Locker.Unlock;

            M.ReleaseFork(Id);
        end loop;
    end Philosopher;

begin
    for I in 0..N-1 loop
        PhilosopherArray(I).Init(I);
    end loop;
end Main;
