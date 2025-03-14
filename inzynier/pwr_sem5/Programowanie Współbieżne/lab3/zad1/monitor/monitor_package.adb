with Ada.Text_IO; use Ada.Text_IO;
with Ada.Integer_Text_IO; use Ada.Integer_Text_IO;
with Ada.Numerics.Discrete_Random;
with Ada.Strings;
with Ada.Strings.Fixed;
package body Monitor_Package is
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
--  TakeFork
    procedure TakeFork (M : in out Monitor; I : Integer) is
    begin
        Locker.Lock;
        if M.Fork(I) /= 2 then
            Locker.Unlock;
            M.OkToEat(I).Wait;
            Locker.Lock;
        end if;
        M.Fork((I + 1) mod N) := M.Fork((I + 1) mod N) - 1;
        M.Fork((I - 1) mod N) := M.Fork((I - 1) mod N) - 1;
        Locker.Unlock;
    end TakeFork;

--  Release Fork
    procedure ReleaseFork (M : in out Monitor; I : Integer) is
    begin
        Locker.Lock;
        M.Fork((I + 1) mod N) := M.Fork((I + 1) mod N) + 1;
        M.Fork((I - 1) mod N) := M.Fork((I - 1) mod N) + 1;
        if M.Fork((I + 1) mod N) = 2 then
            M.OkToEat((I + 1) mod N).Signal;
        end if;
        if M.Fork((I - 1) mod N) = 2 then
            M.OkToEat((I - 1) mod N).Signal;
        end if;
        Locker.Unlock;
    end ReleaseFork;
end Monitor_Package;