%for N = 5:5:30
N = 100;
    p = 0.5;
    S = zeros(10000,1);

    for i = 1:10000
    
        r=rand(N,1);
        X=(r>p)*2-1;
        s=sum(X);
    
        S(i)=s;
    end
    
    % Histogramy
    histogram(S);

    title("N= " + N);
    xlabel("wartość");
    ylabel("liczba");

    format='zad2a-%d';
    saveas(gcf, sprintf(format,N), 'png');
    
    % Dystrybuanta
    cdfplot(S);
    hold on;
    
    norm = makedist('Normal', 'mu', 0, 'sigma', sqrt(N));
    x= -N:.1:N;
    nCdf = cdf(norm,x);
    plot(x,nCdf);
    
    title("N= " + N);
    xlabel("wartość");
    ylabel("dystrybuanta");
    hold off;
    legend('wyniki eksperymentów', 'rozkład normalny', 'Location','southeast');
    
    format='zad2b-%d';
    saveas(gcf, sprintf(format,N), 'png');
%end


