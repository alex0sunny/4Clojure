#(loop [s [%1] c 1]
    (if (some (partial = %2) s) c
      (recur
         (concat (map (partial * 2)   s) 
                 (map (partial * 1/2) s) 
                 (map (partial + 2)   s)) 
         (inc c))))
