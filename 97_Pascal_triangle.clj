(fn f [n] 
  (if (= n 1) [1]
      (concat [1] (map #(apply + %) (partition 2 1 (f (dec n)))) [1])))
