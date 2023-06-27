k = 5000;
tabN = [100, 1000, 10000];

for N = tabN
    tabS = zeros(k, 1);
    Pn = zeros(k, 1);
    for i = 1:k
        Dn = zeros(N, 1);
        Ln = 0;
        X = randi(2, N, 1);
        X(X == 2) = -1;
        currSum = 0;
        for j = 1:N
            currSum = currSum + X(j);
            if(currSum > 0 || (currSum == 0 && (currSum - X(j)) > 0))
                Ln = Ln + 1;
            end
        end
        tabS(i) = currSum;
        Pn(i) = Ln / N;
    end
    %Generowanie histogramu
    histogram(Pn, 20, 'Normalization', 'pdf');
    hold on;
    y = 0:.01:1;
    plot(y, 1./(pi.*sqrt(y.*(1-y))));
    hold off;
    title(strcat("Histogram dla N = ", num2str(N)));
    legend('Wynik eksperymentu', 'Rozkład arcsin', 'Location', 'best');
    xlabel('Pn');
    ylabel('Ilość wystąpień')
    exportgraphics(gcf, strcat('wykresyZad3/Histogram_', num2str(N), '.png'), 'Resolution', 300);
end
