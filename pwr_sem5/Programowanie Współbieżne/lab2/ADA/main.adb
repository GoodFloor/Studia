with Ada.Text_IO; use Ada.Text_IO;
with Ada.Integer_Text_IO; use Ada.Integer_Text_IO;
with Ada.Numerics.Discrete_Random;

procedure Main is

-- Stałe
   M : constant Integer := 5;
   N : constant Integer := 4;
   K : constant Integer := 4;
   D : constant Duration := 18.0;
   Z : constant Duration := 18.0;
   ProbabilityOfTravel : constant Integer := 90;
   ProbabilityOfBirth : constant Integer := 50;
   ProbabilityOfRogue : constant Integer := 50;
   ProbabilityOfDanger : constant Integer := 50;
   DrawCross : constant String := "┼";
   DrawHorizontalSolid : constant String := "─";
   DrawHorizontalBlured : constant String := " ";
   DrawVerticalSolid : constant String := "│";
   DrawVerticalBlured : constant String := " ";

-- Deklaracja tasków
   task TPhotographer;
   task type TGridNode is
      entry PrintRequest (Value : out Integer; EastBlur : out Boolean; SouthBlur : out Boolean);
      entry IncomingAgents (Id : in Integer; Direction : in Integer; Response : out Integer);
      entry IncomingRogueAgents (Id : in Integer; Direction : in Integer; Response : out Boolean);
      entry IncomingDanger (Id : in Integer; Response : out Boolean);
      entry LeavingAgents (Direction : in Integer);
      entry LeavingRogueAgents (Direction : in Integer);
      entry LeavingDanger;
   end TGridNode;
   task type TAgent is
      entry Initialize (X : in Integer; Y : in Integer; Id : in Integer);   
      entry Freeze (F : in Boolean);   
   end TAgent;
   task type TRogueAgent is
      entry Initialize (X : in Integer; Y : in Integer; Id : in Integer);
      entry Move (Direction : out Integer);
   end TRogueAgent;
   task type TDanger is
      entry Initialize (X : in Integer; Y : in Integer);
   end TDanger;
   task TAgentsGenerator is
      entry Initialize;
   end TAgentsGenerator;
   task TRogueAgentsGenerator is
      entry Initialize;
   end TRogueAgentsGenerator;
   task TDangerGenerator is
      entry Initialize;
   end TDangerGenerator;

-- Zmienne
   Grid : array(0..M-1, 0..N-1) of TGridNode;
   Agents : array(0..K-1) of TAgent;
   RogueAgents : array(0..M*N-1) of TRogueAgent;
   Dangers : array(0..M*N-1) of TDanger;

-- Definicja TPhotographer
   task body TPhotographer is
      Value : Integer;
      EastBlur : Boolean;
      SouthBlur : Boolean;
      SouthBlurArray : array(0..N-1) of Boolean;      
   begin
      loop
         Put_Line("");
         Put_Line("Obraz systemu:");
         -- Zatrzymanie wszystkich agentów
         for I in 0..K-1 loop
            Agents(I).Freeze(True);
         end loop;

         -- Pobranie i wydrukowanie węzłów
         -- Drukowanie górnej krawędzi planszy
         for J in 0..3*N loop
            case J mod 3 is
               when 0      => Put(DrawCross);
               when others => Put(DrawHorizontalSolid);
            end case;
         end loop;
         Put_Line("");

         for I in 0..M-1 loop
            -- Drukowanie lewej krawędzi rzędu
            Put(DrawVerticalSolid);
            for J in 0..N-1 loop
               -- Pobranie danych z węzła
               Grid(I, J).PrintRequest(Value, EastBlur, SouthBlur);

               -- Drukowanie wartości
               if Value = -3 then 
                  Put(" #");
               elsif Value = -2 then
                  Put(" *");
               elsif Value = -1 then
                  Put("  ");
               elsif Value < 10 then
                  Put("0");
                  Put(Value'Image(2..2));
               else
                  Put(Value'Image(2..3));
               end if;

               -- Drukowanie odpowiedniej krawędzi po prawej
               if EastBlur then
                  Put(DrawVerticalBlured);
               else
                  Put(DrawVerticalSolid);
               end if;

               -- Zapamiętanie jaką krawędź wypisać poniżej
               if SouthBlur then
                  SouthBlurArray(J) := True;
               end if;
            end loop;
            Put_Line("");

            -- Drukowanie dolnej krawędzi
            for J in 0..3*N loop
               case J mod 3 is
                  when 0 => Put(DrawCross);
                  when 1 => 
                     if SouthBlurArray(J / 3) then
                        Put(DrawHorizontalBlured);
                     else
                        Put(DrawHorizontalSolid);
                     end if;
                  when 2 =>
                     if SouthBlurArray(J / 3) then
                        Put(DrawHorizontalBlured);
                        SouthBlurArray(J / 3) := False;
                     else
                        Put(DrawHorizontalSolid);
                     end if;
                  when others => Put("?");
               end case;
            end loop;
            Put_Line("");
         end loop;
         -- Wznowienie wszystkich agentów
         for I in 0..K-1 loop
            Agents(I).Freeze(False);
         end loop;
         delay 1.0;
      end loop;
   end TPhotographer;

-- Definicja TGridNode
   task body TGridNode is
      Content : Integer := -1;
      RogueId : Integer := -1;
      DangerId : Integer := -1;
      RogueResponse : Integer;
      IsEastBlured : Boolean := False;
      IsSouthBlured : Boolean := False;
   begin
      loop
         select
            accept PrintRequest (Value : out Integer; EastBlur : out Boolean; SouthBlur : out Boolean) do
               Value := Content;
               EastBlur := IsEastBlured;
               SouthBlur := IsSouthBlured;
               IsEastBlured := False;
               IsSouthBlured := False;
            end PrintRequest;
         or
            accept IncomingAgents (Id : in Integer; Direction : in Integer; Response : out Integer) do
               -- Zagrożenie
               if Content = -3 then 
                  Content := -1;
                  Response := -1;
                  if Direction = 3 then
                     IsEastBlured := True;
                  elsif Direction = 0 then
                     IsSouthBlured := True;
                  end if;
               -- Dziki lokator
               elsif Content = -2 then 
                  RogueAgents(RogueId).Move(RogueResponse);
                  if RogueResponse < 0 then
                     Response := 0;
                  else
                     Content := Id;
                     Response := 1;
                     if Direction = 3 then
                        IsEastBlured := True;
                     elsif Direction = 0 then
                        IsSouthBlured := True;
                     end if;

                     if RogueResponse = 2 then
                        IsSouthBlured := True;
                     elsif RogueResponse = 1 then
                        IsEastBlured := True;
                     end if;
                  end if;
               -- Węzeł pusty
               elsif Content = -1 then 
                  Content := Id;
                  Response := 1;
                  if Direction = 3 then
                     IsEastBlured := True;
                  elsif Direction = 0 then
                     IsSouthBlured := True;
                  end if;
               -- Węzeł zajęty
               else
                  Response := 0;
               end if;
            end IncomingAgents;
         or
            accept LeavingAgents (Direction : in Integer) do
               Content := -1;
               if Direction = 2 then
                  IsSouthBlured := True;
               elsif Direction = 1 then
                  IsEastBlured := True;
               end if;
            end LeavingAgents;
         or
            accept IncomingRogueAgents (Id : in Integer; Direction : in Integer; Response : out Boolean) do
               -- Węzeł pusty
               if Content = -1 then 
                  Content := -2;
                  RogueId := Id;
                  Response := True;
                  if Direction = 3 then
                     IsEastBlured := True;
                  elsif Direction = 0 then
                     IsSouthBlured := True;
                  end if;
               -- Węzeł zajęty
               else
                  Response := False;
               end if;
            end IncomingRogueAgents;
         or 
            accept IncomingDanger (Id : in Integer; Response : out Boolean) do
               -- Węzeł pusty
               if Content = -1 then 
                  Content := -3;
                  DangerId := Id;
                  Response := True;
               -- Węzeł zajęty
               else
                  Response := False;
               end if;
            end IncomingDanger;
         or
            accept LeavingRogueAgents (Direction : in Integer) do
               if Content = -2 then
                  Content := -1;
                  if Direction = 2 then
                     IsSouthBlured := True;
                  elsif Direction = 1 then
                     IsEastBlured := True;
                  end if;
               end if;
            end LeavingRogueAgents;
         or
            accept LeavingDanger do
               if Content = -3 then
                  Content := -1;
               end if;
            end LeavingDanger;
         end select;
      end loop;
   end TGridNode;

-- Definicja TAgent
task body TAgent is
   IsInitialized : Boolean := False;
   IsFrozen : Boolean := False;
   IsDead : Boolean := False;
   Response : Integer;
   IdNum : Integer;
   XPos : Integer;
   YPos : Integer;
   NewX : Integer;
   NewY : Integer;
   MoveDirection : Integer;
   subtype PercentRange is Integer range 1..100;
   package RandPercent is new
      Ada.Numerics.Discrete_Random (PercentRange);
   use RandPercent;
   Gen : Generator;
   R : PercentRange;
begin
   Reset(Gen);
   -- Oczekiwanie na inicjalizację
   loop
      exit when IsInitialized;
      select
         accept Initialize (X : in Integer; Y : in Integer; Id : in Integer) do
            IsInitialized := True;
            XPos := X;
            YPos := Y;
            IdNum := Id;
         end Initialize;
      or
         accept Freeze (F : in Boolean) do
            IsFrozen := F;
         end Freeze;
      end select;
   end loop;

   -- Podróżowanie
   loop
      exit when IsDead;
      select
         accept Freeze (F : in Boolean) do
            IsFrozen := F;
         end Freeze;
      or
         delay 1.0;
         if not IsFrozen and then Random(Gen) <= ProbabilityOfTravel then
            NewX := XPos;
            NewY := YPos;
            R := Random(Gen);
            if R <= 25 then
               NewX := NewX + 1;
               MoveDirection := 2;
            elsif R <= 50 then
               NewX := NewX - 1;
               MoveDirection := 0;               
            elsif R <= 75 then
               NewY := NewY + 1;
               MoveDirection := 1;
            else
               NewY := NewY - 1;
               MoveDirection := 3;
            end if;
            if NewX = M then
               NewX := NewX - 2;
               MoveDirection := 0;
            elsif NewX < 0 then
               NewX := NewX + 2;
               MoveDirection := 2;
            elsif NewY = N then
               NewY := NewY - 2;
               MoveDirection := 3;
            elsif NewY < 0 then
               NewY := NewY + 2;
               MoveDirection := 1;
            end if;
            Grid(NewX, NewY).IncomingAgents(IdNum, MoveDirection, Response);
            if Response = 1 then
               Grid(XPos, YPos).LeavingAgents(MoveDirection);
               XPos := NewX;
               YPos := NewY;
            elsif Response = -1 then
               IsDead := True;
               Grid(XPos, YPos).LeavingAgents(MoveDirection);
            end if;
         end if;
      end select;
   end loop;
   loop
      accept Freeze (F : in Boolean) do
         IsFrozen := F;
      end Freeze;
   end loop;
end TAgent;

-- Definicja TRogueAgent
task body TRogueAgent is
   IsDead : Boolean := False;
   MoveResponse : Integer;
   MoveAllowed : Boolean;
   TTL : Duration;
   IdNum : Integer;
   XPos : Integer;
   YPos : Integer;
begin
   loop
      -- Oczekiwanie na inicjalizację
      accept Initialize (X : in Integer; Y : in Integer; Id : in Integer) do
         XPos := X;
         YPos := Y;
         IdNum := Id;
      end Initialize;

      TTL := D;
      loop
         exit when TTL <= 0.0;
         select
            accept Move (Direction : out Integer) do
               MoveResponse := -1;
               if MoveResponse = -1 and then XPos < M - 1 then
                  select
                     Grid(XPos+1, YPos).IncomingRogueAgents(IdNum, 2, MoveAllowed);
                     if MoveAllowed then
                        XPos := XPos + 1;
                        MoveResponse := 2;
                     end if;
                  or
                     delay 0.25;
                  end select;
               end if;
               if MoveResponse = -1 and then XPos > 0 then
                  select
                     Grid(XPos-1, YPos).IncomingRogueAgents(IdNum, 0, MoveAllowed);
                     if MoveAllowed then
                        XPos := XPos - 1;
                        MoveResponse := 0;
                     end if;
                  or
                     delay 0.25;
                  end select;
               end if;
               if MoveResponse = -1 and then YPos < N - 1 then
                  select
                     Grid(XPos, YPos+1).IncomingRogueAgents(IdNum, 1, MoveAllowed);
                     if MoveAllowed then
                        YPos := YPos + 1;
                        MoveResponse := 1;
                     end if;
                  or
                     delay 0.25;
                  end select;
               end if;
               if MoveResponse = -1 and then YPos > 0 then
                  select
                     Grid(XPos, YPos-1).IncomingRogueAgents(IdNum, 2, MoveAllowed);
                     if MoveAllowed then
                        YPos := YPos - 1;
                        MoveResponse := 3;
                     end if;
                  or
                     delay 0.25;
                  end select;
               end if;
               Direction := MoveResponse;
            end Move;
         or
            delay 1.0;
            TTl := TTL - 1.0;
         end select;
      end loop;
      Grid(XPos, YPos).LeavingRogueAgents(-1);
   end loop;
end TRogueAgent;

-- Definicja TDanger
task body TDanger is
   XPos : Integer;
   YPos : Integer;
begin
   loop
      -- Oczekiwanie na inicjalizację
      accept Initialize (X : in Integer; Y : in Integer) do
         XPos := X;
         YPos := Y;
      end Initialize;
      delay Z;
      Grid(XPos, YPos).LeavingDanger;
   end loop;
end TDanger;

-- Definicja TAgentsGenerator
task body TAgentsGenerator is
   Response : Integer;
   subtype PercentRange is Integer range 1..100;
   package RandPercent is new
      Ada.Numerics.Discrete_Random (PercentRange);
   use RandPercent;
   Gen : Generator;
   R : PercentRange;
   RX : Integer;
   RY : Integer;
   NumberOfAgents : Integer := 0;
begin
   accept Initialize;
   Reset(Gen);
   loop
      if Random(Gen) <= ProbabilityOfBirth then
         R := Random(Gen);
         RX := (R * (M - 1))/ 100;
         R := Random(Gen);
         RY := (R * (N - 1))/ 100;
         Grid(RX, RY).IncomingAgents(NumberOfAgents, -1, Response);
         if Response = 1 then
            Agents(NumberOfAgents).Initialize(RX, RY, NumberOfAgents);
            NumberOfAgents := NumberOfAgents + 1;
         end if;
      end if;
      exit when NumberOfAgents >= K;
      delay 1.0;
   end loop;
end TAgentsGenerator;

-- Definicja TRogoueAgentsGenerator
task body TRogueAgentsGenerator is
   Response : Boolean;
   subtype PercentRange is Integer range 1..100;
   package RandPercent is new
      Ada.Numerics.Discrete_Random (PercentRange);
   use RandPercent;
   Gen : Generator;
   R : PercentRange;
   RX : Integer;
   RY : Integer;
   RogueId : Integer := 0;
begin
   accept Initialize;
   Reset(Gen);
   loop
      if Random(Gen) <= ProbabilityOfRogue then
         R := Random(Gen);
         RX := (R * (M - 1))/ 100;
         R := Random(Gen);
         RY := (R * (N - 1))/ 100;
         Grid(RX, RY).IncomingRogueAgents(RogueId, -1, Response);
         if Response then
            RogueAgents(RogueId).Initialize(RX, RY, RogueId);
            RogueId := (RogueId + 1) mod (M * N);
         end if;
      end if;
      delay 1.0;
   end loop;
end TRogueAgentsGenerator;

-- Definicja TDangerGenerator
task body TDangerGenerator is
   Response : Boolean;
   subtype PercentRange is Integer range 1..100;
   package RandPercent is new
      Ada.Numerics.Discrete_Random (PercentRange);
   use RandPercent;
   Gen : Generator;
   R : PercentRange;
   RX : Integer;
   RY : Integer;
   DangerId : Integer := 0;
begin
   accept Initialize;
   Reset(Gen);
   loop
      if Random(Gen) <= ProbabilityOfDanger then
         R := Random(Gen);
         RX := (R * (M - 1))/ 100;
         R := Random(Gen);
         RY := (R * (N - 1))/ 100;
         Grid(RX, RY).IncomingDanger(DangerId, Response); 
         if Response then
            Dangers(DangerId).Initialize(RX, RY);
            DangerId := (DangerId + 1) mod (M*N);
         end if;
      end if;
      delay 1.0;
   end loop;
end TDangerGenerator;


begin
   TAgentsGenerator.Initialize;
   TRogueAgentsGenerator.Initialize;
   TDangerGenerator.Initialize;
   delay 60.0;
end Main;
