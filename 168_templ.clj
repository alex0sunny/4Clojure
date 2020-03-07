(fn [fr f & [m n s t]] 
  (let [f2 ((fn [fs] (partial fr #(fs % (or n 0)))) #(fr (partial f %) %2))]
    (if s (take s (map #(take t %) (f2 (or m 0)))) (f2 (or m 0)))))
(fn fr [fu p] (lazy-seq (cons (fu p) (fr fu (inc p)))))
