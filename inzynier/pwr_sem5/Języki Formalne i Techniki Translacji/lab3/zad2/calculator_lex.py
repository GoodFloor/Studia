from sly import Lexer, Parser
from sly.lex import LexError

def invert(x, mod):
    if(x == 0):
        return 0
    if(x == 1):
        return 1
    n = (x * 2) % mod
    for i in range(2, mod):
        if(n == 1):
            return i
        n += x
        n %= mod
    return 0


MODULO = 1234577
err = False

class CalculatorLexer(Lexer):
    tokens = {NUMBER, ADD, SUB, MUL, DIV, POW, LPAREN, RPAREN, COM}
    ignore = ' \t'

    # Tokens
    NUMBER = r'\d+'
    ADD = r'\+'
    SUB = r'-'
    MUL = r'\*'
    DIV = r'/'
    POW = r'\^'
    LPAREN = r'\('
    RPAREN = r'\)'
    COM = r'^\#.*'

    # Ignore comments starting with '#'
    ignore_comment = r'^\#.*\n'

    # Handle line continuation '\'
    @_(r'\\')
    def ignore_line_continuation(self, t):
        self.lineno += 1

    # Ignore newline characters
    @_(r'\n+')
    def ignore_newline(self, t):
        self.lineno += t.value.count('\n')

class CalculatorParser(Parser):
    tokens = CalculatorLexer.tokens

    precedence = (
        ('left', ADD, SUB),
        ('left', MUL, DIV),
        ('left', POW),
        ('right', NEG)
    )

    def __init__(self):
        self.names = {}

    @_('exp1')
    def line(self, p):
        global err
        if err:
            err = False
        else:
            print(f"\nWynik: {p.exp1}")
        return p.exp1
    
    @_('COM')
    def line(self, p):
        return 0

    @_('exp2')
    def exp1(self, p):
        return p.exp2
    
    @_('exp1 ADD exp2')
    def exp1(self, p):
        print("+ ", end='')
        return (p.exp1 + p.exp2) % MODULO
    
    @_('exp1 SUB exp2')
    def exp1(self, p):
        print("- ", end='')
        return (p.exp1 - p.exp2 + MODULO) % MODULO
    
    @_('exp3')
    def exp2(self, p):
        return p.exp3
    
    @_('exp2 MUL exp3')
    def exp2(self, p):
        print("* ", end='')
        return (p.exp2 * p.exp3) % MODULO
    
    @_('exp2 DIV exp3')
    def exp2(self, p):
        print("/ ", end='')
        inv = invert(p.exp3, MODULO)
        if inv != 0:
            return (p.exp2 * inv) % MODULO
        else:
            print(f"\nBłąd: {p.exp3} nie jest odwracalne modulo {MODULO}!")
            global err
            err = True
            return 0
    
    @_('exp4')
    def exp3(self, p):
        return p.exp4
    
    @_('exp3 POW powexp4')
    def exp3(self, p):
        print("^ ", end='')
        result = 1
        for i in range(0, p.powexp4):
            result *= p.exp3
            result %= MODULO
        return result

    @_('NUMBER')
    def exp4(self, p):
        realVal = int(p.NUMBER)
        while realVal < 0:
            realVal += MODULO
        realVal %= MODULO
        print(realVal, end=' ')
        return realVal
    
    @_('LPAREN exp1 RPAREN')
    def exp4(self, p):
        return p.exp1
    
    @_('SUB exp4 %prec NEG')
    def exp4(self, p):
        realVal = MODULO - int(p.exp4)
        while realVal < 0:
            realVal += MODULO
        realVal %= MODULO
        print("~", end=' ')
        return realVal
    
    @_('NUMBER')
    def powexp4(self, p):
        realVal = int(p.NUMBER)
        while realVal < 0:
            realVal += (MODULO - 1)
        realVal %= (MODULO - 1)
        print(realVal, end=' ')
        return realVal
    
    @_('LPAREN powexp1 RPAREN')
    def powexp4(self, p):
        return p.powexp1
    
    @_('SUB powexp4 %prec NEG')
    def powexp4(self, p):
        realVal = (MODULO - 1) - int(p.powexp4)
        while realVal < 0:
            realVal += (MODULO - 1)
        realVal %= (MODULO - 1)
        print("~", end=' ')
        return realVal

    @_('powexp2')
    def powexp1(self, p):
        return p.powexp2
    
    @_('powexp1 ADD powexp2')
    def powexp1(self, p):
        print("+ ", end='')
        return (p.powexp1 + p.powexp2) % (MODULO - 1)
    
    @_('powexp1 SUB powexp2')
    def powexp1(self, p):
        print("- ", end='')
        return (p.powexp1 - p.powexp2 + (MODULO - 1)) % (MODULO - 1)
    
    @_('powexp4')
    def powexp2(self, p):
        return p.powexp4
    
    @_('powexp2 MUL powexp4')
    def powexp2(self, p):
        print("* ", end='')
        return (p.powexp2 * p.powexp4) % (MODULO - 1)
    
    @_('powexp2 DIV powexp4')
    def powexp2(self, p):
        print("/ ", end='')
        inv = invert(p.powexp4, MODULO - 1)
        if inv != 0:
            return (p.powexp2 * inv) % (MODULO - 1)
        else:
            print(f"\nBłąd: {p.powexp4} nie jest odwracalne modulo {(MODULO - 1)}!")
            global err
            err = True
            return 0

    def rpn(self, expr):
        if isinstance(expr, list):
            return expr
        else:
            return [expr]

if __name__ == '__main__':
    lexer = CalculatorLexer()
    parser = CalculatorParser()

    while True:
        try:
            text = input('')
        except EOFError:
            break
        try:
            if text:
                result = parser.parse(lexer.tokenize(text))
        except LexError:
            print("Błąd składni!")
