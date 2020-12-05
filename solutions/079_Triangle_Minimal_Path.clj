; Problem 79 Triangle Minimal Path
; Difficulty:	Hard
; Topics:	graph-theory


; Write a function which calculates the sum of the minimal path through a triangle. 
; The triangle is represented as a collection of vectors. 
; The path should start at the top of the triangle and move to an adjacent number 
; on the next row until the bottom of the triangle is reached.

(fn [tri] 
  (let [process-raw (fn [sums raw] 
          (apply map #(min %1 %2) (map #(map + % raw)
            (vector (cons (first sums) sums) (conj (vec sums) (last sums))))))]
     (apply min (reduce process-raw tri))))
