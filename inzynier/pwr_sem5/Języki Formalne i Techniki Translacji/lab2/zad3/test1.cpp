Includy:
    #include "test/*asdf*/header.h"
    #include <stdio.h>
    #include <foo/*bar*/baz.h>

Zwykłe komentarze:
    Liniowy:"// Komentarz liniowy \
                Złamana linia"
             // Komentarz liniowy \
                Złamana linia
            
    Blokowy:"/*
                Komentarz blokowy
                o kilku linijkach
             */"
             /*
                Komentarz blokowy
                o kilku linijkach
             */

Komentarze Doxygen:
    Liniowy:"/// Komentarz liniowy Doxygen \
                 Złamana linia"
             /// Komentarz liniowy Doxygen \
                 Złamana linia

             "//! Komentarz liniowy Doxygen \
                 Złamana linia"
             //! Komentarz liniowy Doxygen \
                 Złamana linia
    
    Blokowy: "/**
               * Komentarz blokowy Doxygen
               * o kilku linijkach
             */"
             /**
               * Komentarz blokowy Doxygen
               * o kilku linijkach
             */

             "/*!
              * Komentarz blokowy Doxygen 
              * o kilku linijkach
             */"
             /*!
              * Komentarz blokowy Doxygen 
              * o kilku linijkach
             */

Koniec
