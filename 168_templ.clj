(fn [fr f & [m n s t]] 
  (-> #(fr (partial f %) %2)
      ((fn [fs] (partial fr #(fs % (or n 0)))))
      (#(% (or m 0)))
      (#(if s (take s (map (partial take t) %)) %))))
(fn fr [fu p] (lazy-seq (cons (fu p) (fr fu (inc p)))))
