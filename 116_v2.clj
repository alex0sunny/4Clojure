(fn fs [n] 
  (let [fp #(every? (comp pos? (partial mod %)) (range 2 %))
        c (some #(and (fp %) %) (drop (inc n) (range)))
        [b a] (filter fp (range n 1 -1))]
     (and (> n 2) (= n b) (= (- n a) (- c n)))))
