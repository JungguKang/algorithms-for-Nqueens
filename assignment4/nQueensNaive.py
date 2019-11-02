from z3 import *
import time

# Number of Queens
print("N: ")
N = int(input())

start = time.time()
# Variables
X = [Int("x_%s" % (row)) for row in range(N)]

# Constraints
domain = [And(X[row] < N, X[row] >= 0) for row in range(N)]

rowConst = [Implies(i != j, X[i] != X[j]) for i in range(N) for j in range(N)]

digConst = [Implies(And(i != j, X[i] < X[j]), abs(i - j) != X[j] - X[i])
          for i in range(N) for j in range(N)]

eight_queens_c = domain + rowConst + digConst

s = Solver()
s.add(eight_queens_c)

if s.check() == sat:
    m = s.model()
    r = [m.evaluate(X[i]) for i in range(N)]
    print_matrix(r)

print("elapsed time: ", time.time() - start, " sec")

