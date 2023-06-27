N = 10000;
p = 0.5;
P = zeros(50000,1);

for i = 1:50000

    r=rand(N,1);
    X=(r>p)*2-1;

    s=0;
    l=0;
    for j = 1:N
        if s < 0
            s = s + X(j);
            continue
        end
        s = s + X(j);

        if s < 0
            continue
        end
        l = l+1;
    end
    P(i) = l / N;
end

% Histogramy
histogram(P,20, 'Normalization','pdf');
hold on;

x = 0:.01:1;
plot(x, 1./(pi.*sqrt(x.*(1-x))));

title("N= " + N);
xlabel("wartość");
ylabel("liczba");

hold off;
legend('wyniki eksperymentów', 'rozkład arcsin', 'Location', 'north');
format='zad3-%d';
saveas(gcf, sprintf(format,N), 'png');


