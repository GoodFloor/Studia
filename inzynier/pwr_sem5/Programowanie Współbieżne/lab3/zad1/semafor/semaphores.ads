package Semaphores is
    task type BinarySemaphore is
        entry Wait;
        entry Signal;        
    end BinarySemaphore;
end Semaphores;