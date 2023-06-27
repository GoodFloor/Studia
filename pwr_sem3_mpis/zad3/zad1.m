n = 1000000;
X = randi(2, n, 1);
X(X == 2) = 0;
fileID = fopen('goodRNG.txt', 'w');
fprintf(fileID, '%i', X);
fclose(fileID);