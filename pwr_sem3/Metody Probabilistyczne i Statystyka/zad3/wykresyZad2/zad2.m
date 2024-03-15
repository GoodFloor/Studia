k = 1000000;    %Ilość prób dla każdego N
tabN = 5:5:30;
tabN = [tabN, 100];

for N = tabN
    tabS = zeros(k, 1);
    for i = 1:k
        X = randi(2, N, 1);
        X(X == 2) = -1;
        tabS(i) = sum(X);
    end
    %Generowanie histogramu
    histogram(tabS);
    title(strcat("Gęstość dla N = ", num2str(N)));
    xlabel('Suma');
    ylabel('Ilość wystąpień')
    exportgraphics(gcf, strcat('Histogram_', num2str(N), '.png'), 'Resolution', 300);

    %Generowanie dystrybuanty
    cdfplot(tabS);
    hold on;
    x = min(tabS):.1:max(tabS);
    pd = makedist('Normal', 'sigma', sqrt(N));
    plot(x, cdf(pd, x));
    legend('Dyst. zmiennej losowej', 'Dyst. rozkładu normalnego', 'Location', 'best');
    hold off;
    title(strcat("Porównanie dystrybuant dla N = ", num2str(N)));
    xlabel('Wartość');
    ylabel('Dystrybuanta');
    exportgraphics(gcf, strcat('Dystrybuanty_', num2str(N), '.png'), 'Resolution', 300);
end
