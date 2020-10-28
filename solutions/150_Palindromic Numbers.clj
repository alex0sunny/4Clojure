;; Palindromic Numbers
;; Difficulty:	Medium
;; Topics:	seqs math


;; A palindromic number is a number that is the same when written forwards or backwards (e.g., 3, 99, 14341).

;; Write a function which takes an integer n, as its only argument, and returns an increasing lazy sequence of all palindromic numbers that are not less than n.

;; The most simple solution will exceed the time limit!

(fn fnx [n] 
  (let [fd (fn [x] (map #(- (int %) 48) (str x)))
        fi #(Long. (apply str %))
        d (fd n)
        l (count d)
        p (take (quot (inc l) 2) d)
        fc #(fi (concat 
                  % ((if (odd? l) rest identity) (reverse %))))
        nn (fc p)]
    (concat (if (>= nn n) [nn])
            (lazy-seq (fnx (if (apply = 9 p) (+ nn 2)
                               (fc (fd (inc (fi p))))))))))
