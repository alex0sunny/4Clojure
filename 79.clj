; Problem 79 Triangle Minimal Path
(fn [tri] 
  (let [process-raw (fn [sums raw] 
          (apply map #(min %1 %2) (map #(map + % raw)
            (vector (cons (first sums) sums) (conj (vec sums) (last sums))))))]
     (apply min (reduce process-raw tri))))
