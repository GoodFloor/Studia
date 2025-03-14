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
--  StartRead
    procedure StartRead (M : in out Monitor) is
    begin
        Locker.Lock;
        if M.Writing or Non_Empty(M.OkToWrite) then
            Locker.Unlock;
            M.OkToRead.Wait;
            Locker.Lock;
        end if;
        M.Readers := M.Readers + 1;
        M.OkToRead.Signal;
        Locker.Unlock;
    end StartRead;
--  StopRead
    procedure StopRead (M : in out Monitor) is
    begin
        Locker.Lock;
        M.Readers := M.Readers - 1;
        if M.Readers = 0 then
            M.OkToWrite.Signal;
        end if;
        Locker.Unlock;
    end StopRead;
--  StartWrite
    procedure StartWrite (M : in out Monitor) is
    begin
        Locker.Lock;
        if M.Readers /= 0 or M.Writing then
            Locker.Unlock;
            M.OkToWrite.Wait;
            Locker.Lock;
        end if;
        M.Writing := True;
        Locker.Unlock;
    end StartWrite;
--  StopWrite
    procedure StopWrite (M : in out Monitor) is
    begin
        Locker.Lock;
        M.Writing := False;
        if Non_Empty(M.OkToRead) then
            M.OkToRead.Signal;
        else
            M.OkToWrite.Signal;
        end if;
        Locker.Unlock;
    end StopWrite;
end Monitor_Package;