(fn [f & rs] 
  (let [[m n s t] (if rs rs [0 0])
        ft (fn ft [fu p] (lazy-seq (cons (fu p) (ft fu (inc p)))))
        f2 ((fn [fs] (partial ft #(fs % n))) #(ft (partial f %) %2))]
    (if s (take s (map #(take t %) (f2 m))) (f2 m))))
