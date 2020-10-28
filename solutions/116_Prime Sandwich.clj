; Prime Sandwich
; Difficulty:	Medium
; Topics:	math
; A balanced prime is a prime number which is also the mean of the primes directly before and after it in the sequence of valid primes. Create a function which takes an integer n, and returns true iff it is a balanced prime.

(fn [n] 
  (and (> n 4)
    (let [fp      #(every? (comp pos? (partial mod %)) (range 2 %))
          fps     #(filter fp (range % (+ % %)))
          [c d]   (fps n)
          l       (- d c)
          [a b]   (fps (- n l))]
      (= n b c (+ a l)))))
