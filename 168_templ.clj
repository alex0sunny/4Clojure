(fn [f & rs] 
  (let [[m n s t] rs 
        [m n] [(or m 0) (or n 0)]
        ft (fn ft [fu p] 
             (lazy-seq (cons (fu p) (ft fu (inc p)))))
        fs #(ft (partial f %) %2)
        f2 (partial ft #(fs % n))]
    (if s (take s (map #(take t %) (f2 m))) (f2 m))))
