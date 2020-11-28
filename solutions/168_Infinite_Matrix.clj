;; Infinite Matrix
;; Difficulty:	Medium
;; Topics:	seqs recursion math


;; In what follows, m, n, s, t denote nonnegative integers, f denotes a function that accepts two arguments and is defined for all nonnegative integers in both arguments.

;; In mathematics, the function f can be interpreted as an infinite matrix with infinitely many rows and columns that, when written, looks like an ordinary matrix but its rows and columns cannot be written down completely, so are terminated with ellipses. In Clojure, such infinite matrix can be represented as an infinite lazy sequence of infinite lazy sequences, where the inner sequences represent rows.

;; Write a function that accepts 1, 3 and 5 arguments

;;     with the argument f, it returns the infinite matrix A that has the entry in the i-th row and the j-th column equal to f(i,j) for i,j = 0,1,2,...;
;;     with the arguments f, m, n, it returns the infinite matrix B that equals the remainder of the matrix A after the removal of the first m rows and the first n columns;
;;     with the arguments f, m, n, s, t, it returns the finite s-by-t matrix that consists of the first t entries of each of the first s rows of the matrix B or, equivalently, that consists of the first s entries of each of the first t columns of the matrix B.

;; Special Restrictions
;; for
;; range
;; iterate
;; repeat
;; cycle
;; drop

(fn [f & rs] 
  (let [[m n s t] rs 
        [m n] [(or m 0) (or n 0)]
        fs (fn fs [k p] (lazy-seq (cons (f k p) (fs k (inc p)))))
        f2 (fn f2 [l] (lazy-seq (cons (fs l n) (f2 (inc l)))))]
    (if s (take s (map #(take t %) (f2 m))) (f2 m))))
