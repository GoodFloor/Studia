package body Semaphores is
    task body BinarySemaphore is
        Sig : Boolean := True;
    begin
        loop
            select
                when Sig => accept Wait do
                    Sig := False;
                end Wait;
            or
                accept Signal do
                    Sig := True;
                end Signal;
            end select;
        end loop;
    end BinarySemaphore;
end Semaphores;