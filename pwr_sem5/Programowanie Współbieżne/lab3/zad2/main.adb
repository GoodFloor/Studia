with Ada.Text_IO; use Ada.Text_IO;
with Ada.Integer_Text_IO; use Ada.Integer_Text_IO;
with Ada.Numerics.Discrete_Random;
with Ada.Strings;
with Ada.Strings.Fixed;
with Monitor_Package;
procedure Main is
    task type Reader is
        entry Init (I : Integer);
    end Reader;
    task type Writer is
        entry Init (I : Integer);
    end Writer;
    task Locker is
        entry Lock;
        entry Unlock;
    end Locker;

    N : Integer := 2;
    M : Integer := 10;
    Monit : Monitor_Package.Monitor;
    Writers : array(0..N-1) of Writer;
    Readers : array(0..M-1) of Reader;
    ActiveWriters : array(0..N-1) of Boolean := (others => False);
    ActiveReaders : array(0..M-1) of Boolean := (others => False);

    procedure PrintWriters;
    procedure PrintReaders;

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

--  Reader
    task body Reader is
        Id : Integer;
        subtype DelayRange is Integer range 8..20;
        package RandDelay is new
            Ada.Numerics.Discrete_Random (DelayRange);
        use RandDelay;
        Gen : Generator;
    begin
        Reset(Gen);
        accept Init (I : Integer) do
            Id := I;
        end Init;
        loop
            delay 0.1 * Random(Gen);

            Monit.StartRead;

            Locker.Lock;
            ActiveReaders(Id) := True;
            PrintReaders;
            Locker.Unlock;

            delay 0.1 * Random(Gen);

            Monit.StopRead;

            Locker.Lock;
            ActiveReaders(Id) := False;
            PrintReaders;
            Locker.Unlock;
        end loop;
    end Reader;

--  Writer
    task body Writer is
        Id : Integer;
        subtype DelayRange is Integer range 1..10;
        package RandDelay is new
            Ada.Numerics.Discrete_Random (DelayRange);
        use RandDelay;
        Gen : Generator;
    begin
        Reset(Gen);
        accept Init (I : Integer) do
            Id := I;
        end Init;
        loop
            delay 0.1 * Random(Gen);

            Monit.StartWrite;

            Locker.Lock;
            ActiveWriters(Id) := True;
            PrintWriters;
            Locker.Unlock;

            delay 0.1 * Random(Gen);

            Monit.StopWrite;

            Locker.Lock;
            ActiveWriters(Id) := False;
            PrintWriters;
            Locker.Unlock;
        end loop;
    end Writer;

--  PrintWriters
    procedure PrintWriters is
        IsFirst : Boolean := True;
    begin
        Put("(");
        for I in 0..N-1 loop
            if ActiveWriters(I) then
                if not IsFirst then
                    Put(", ");
                else 
                    IsFirst := False;
                end if;
                Put("W" & Ada.Strings.Fixed.Trim(Integer'Image(I), Ada.Strings.Left));
            end if;
        end loop;
        Put_Line(")");
    end PrintWriters;
--  PrintReaders
    procedure PrintReaders is
        IsFirst : Boolean := True;
    begin
        Put("(");
        for I in 0..M-1 loop
            if ActiveReaders(I) then
                if not IsFirst then
                    Put(", ");
                else 
                    IsFirst := False;
                end if;
                Put("R" & Ada.Strings.Fixed.Trim(Integer'Image(I), Ada.Strings.Left));
            end if;
        end loop;
        Put_Line(")");
    end PrintReaders;
begin
    for I in 0..N-1 loop
        Writers(I).Init(I);
    end loop;
    for I in 0..M-1 loop
        Readers(I).Init(I);
    end loop;
end Main;