(fn [f & rs] 
  (let [[m n s t] rs 
        [m n] [(or m 0) (or n 0)]
        fs (fn fs [k p] (lazy-seq (cons (f k p) (fs k (inc p)))))
        f2 (fn f2 [l] (lazy-seq (cons (fs l n) (f2 (inc l)))))]
    (if s (take s (map #(take t %) (f2 m))) (f2 m))))
